<script lang="ts">
	import { onMount } from 'svelte';
	import { getTickets } from '$lib/api/tickets';
	import CustomCheckbox from './CustomCheckbox.svelte';
	import Loading from './Loading.svelte';
	import SubHeader from './SubHeader.svelte';
	interface User {
		id?: number;
		name?: string;
	}

	interface Category {
		id?: number;
		name?: string;
	}

	interface Priority {
		id?: number;
		name?: string;
	}

	interface Status {
		id?: number;
		statusName?: string;
	}

	interface Agent {
		id?: number;
		agentName?: string;
		profilePictureUrl?: string;
	}

	interface Ticket {
		id: number;
		title: string;
		description: string;
		createdBy?: User;
		priority?: Priority;
		currentStatus?: Status;
		category?: Category;
		agent?: Agent;
		createdAt: string | Date;
		updatedAt?: string | Date;
	}

	let tickets: Ticket[] = [];
	let selectedTickets: number[] = [];
	let selectAll: boolean = false;
	let isLoading = true;
	let imageError = false;
	let error: string | null = null;

	function toggleSelectAll(value: boolean) {
		selectAll = value;
		if (value) {
			selectedTickets = tickets.map((ticket) => ticket.id);
		} else {
			selectedTickets = [];
		}
	}

	function toggleSelectTicket(id: number, value: boolean) {
		if (value) {
			selectedTickets = [...selectedTickets, id];
		} else {
			selectedTickets = selectedTickets.filter((t) => t !== id);
		}
		selectAll = selectedTickets.length === tickets.length;
	}

	onMount(async () => {
		try {
			tickets = await getTickets();
		} catch (e: unknown) {
			error = e instanceof Error ? e.message : 'Unknown error occurred';
		} finally {
			isLoading = false;
		}
	});

	function formatDate(dateString: string | Date): string {
		const date = new Date(dateString);
		const day = date.getDate().toString().padStart(2, '0');
		const month = (date.getMonth() + 1).toString().padStart(2, '0');
		const year = date.getFullYear();
		return `${day}/${month}/${year}`;
	}
	function formatRelativeTime(dateString: string | Date): string {
		const date = new Date(dateString);
		const now = new Date();
		const diffInSeconds = Math.floor((now.getTime() - date.getTime()) / 1000);

		if (diffInSeconds < 60) {
			return 'a few seconds ago';
		} else if (diffInSeconds < 3600) {
			const minutes = Math.floor(diffInSeconds / 60);
			return `${minutes} minutes ago`;
		} else if (diffInSeconds < 86400) {
			const hours = Math.floor(diffInSeconds / 3600);
			return `${hours} hours ago`;
		} else {
			const days = Math.floor(diffInSeconds / 86400);
			return `${days} days ago`;
		}
	}

	export let searchTerm = '';
	$: filteredTickets = searchTerm
		? tickets.filter(
				(ticket) =>
					ticket.title.toLowerCase().includes(searchTerm.toLowerCase()) ||
					ticket.id.toString().includes(searchTerm) ||
					ticket.priority?.name?.toLowerCase().includes(searchTerm.toLowerCase()) ||
					ticket.currentStatus?.statusName?.toLowerCase().includes(searchTerm.toLowerCase()) ||
					ticket.category?.name?.toLowerCase().includes(searchTerm.toLowerCase()) ||
					ticket.agent?.agentName?.toLowerCase().includes(searchTerm.toLowerCase())
			)
		: tickets;

	$: sortedTickets = [...filteredTickets].sort((a, b) => {
		if (sortOption === '1') {
			return (
				new Date(b.updatedAt ?? b.createdAt).getTime() -
				new Date(a.updatedAt ?? a.createdAt).getTime()
			);
		}
		if (sortOption === '2') {
			return (a.priority?.name ?? '').localeCompare(b.priority?.name ?? '');
		}
		if (sortOption === '3') {
			return (b.currentStatus?.statusName ?? '').localeCompare(a.currentStatus?.statusName ?? '');
		}
		if (sortOption === '4') {
			return (a.agent?.agentName ?? '').localeCompare(b.agent?.agentName ?? '');
		}
		return 0;
	});

	let sortOption = '1'; // Last Modified por defecto

	let isPanelVisible = true;

	function togglePanel() {
		isPanelVisible = !isPanelVisible;
	}

	// Filtros seleccionados
	let selectedStatus = '';
	let selectedPriority = '';
	let selectedCategory = '';
	let selectedAgent = '';
	// Opciones para los filtros
</script>

<SubHeader on:sortChange={(e) => (sortOption = e.detail)} {isPanelVisible} {togglePanel} />

<div class="flex h-full flex-col overflow-hidden">
	{#if isLoading}
		<div
			class="flex h-full flex-col items-center justify-center py-4 text-center font-medium text-gray-800"
		>
			<Loading />
			Loading tickets...
		</div>
	{:else if error}
		<div class="py-4 text-red-500">Error: {error}</div>
	{:else}
		<div class="flex w-full flex-1 flex-col overflow-hidden">
			<!-- Contenedor de tabla con scroll vertical -->
			<div class="flex h-full w-full flex-col overflow-hidden p-3">
				<!-- Encabezado fijo -->
				<div class="sticky top-0 z-10 mr-4 min-w-full border-b border-gray-200 bg-white">
					<div class="overflow-x-auto">
						<table class="w-full">
							<thead>
								<tr>
									<th class="w-[20px] px-4 py-3 text-left text-sm font-semibold text-gray-700">
										<CustomCheckbox bind:checked={selectAll} onChange={toggleSelectAll} />
									</th>
									<th class="w-[200px] px-4 py-3 text-left text-sm font-semibold text-gray-700"
										>Title</th
									>
									<th class="w-[100px] px-4 py-3 text-left text-sm font-semibold text-gray-700"
										>Priority</th
									>
									<th class="w-[120px] px-4 py-3 text-left text-sm font-semibold text-gray-700"
										>Status</th
									>
									<th class="w-[150px] px-4 py-3 text-left text-sm font-semibold text-gray-700"
										>Category</th
									>
									<th class="w-[150px] px-4 py-3 text-left text-sm font-semibold text-gray-700"
										>Agent</th
									>
									<th class="w-[120px] px-4 py-3 text-left text-sm font-semibold text-gray-700"
										>Created at</th
									>
									<th class="w-[120px] px-4 py-3 text-left text-sm font-semibold text-gray-700"
										>Updated</th
									>
								</tr>
							</thead>
						</table>
					</div>
				</div>

				<!-- Cuerpo de la tabla con scroll -->
				<div class="mb-32 flex-1 overflow-x-hidden overflow-y-auto">
					<table class="mt-3 w-full divide-y divide-gray-200">
						<tbody class="divide-y divide-gray-200 bg-white">
							{#each sortedTickets as ticket (ticket.id)}
								<tr
									class="cursor-pointer transition hover:scale-[1.012] hover:bg-gray-50"
									on:click={() => (window.location.href = `/tickets/${ticket.id}`)}
								>
									<td class="w-[40px] px-4 py-3 text-sm whitespace-nowrap text-gray-900">
										<CustomCheckbox
											checked={selectedTickets.includes(ticket.id)}
											onChange={(value) => toggleSelectTicket(ticket.id, value)}
										/>
									</td>
									<td
										class="w-[200px] px-4 py-3 text-sm font-medium whitespace-nowrap text-gray-900"
									>
										<div class="flex flex-row">
											<span class="pr-2 text-gray-500">#{ticket.id} - </span>
											<span class="max-w-[130px] truncate">{ticket.title}</span>
										</div>
									</td>
									<td class="w-[100px] px-4 py-3 text-sm whitespace-nowrap text-gray-900">
										<span
											class="inline-flex rounded-full px-2 text-xs leading-5 font-semibold
							  {ticket.priority?.name === 'Urgent'
												? 'bg-red-100 text-red-800 uppercase'
												: ticket.priority?.name === 'High'
													? 'bg-yellow-100 text-yellow-800'
													: ticket.priority?.name === 'Medium'
														? 'bg-green-100 text-green-800'
														: ticket.priority?.name === 'Low'
															? 'bg-blue-100 text-blue-800'
															: 'bg-gray-100 text-gray-800'}"
										>
											{ticket.priority?.name || 'No Priority'}
										</span>
									</td>
									<td class="w-[100px] px-4 py-3 text-sm whitespace-nowrap text-gray-900">
										<span
											class="inline-flex items-center gap-1 rounded-full px-2 text-xs leading-5 font-semibold
							  {ticket.currentStatus?.statusName === 'Abierto'
												? 'bg-purple-100 text-purple-800'
												: ticket.currentStatus?.statusName === 'In Progress'
													? 'bg-blue-100 text-blue-800'
													: ticket.currentStatus?.statusName === 'Pending'
														? 'bg-orange-100 text-orange-800'
														: ticket.currentStatus?.statusName === 'Solved'
															? 'bg-teal-100 text-teal-800'
															: ticket.currentStatus?.statusName === 'Closed'
																? 'bg-rose-100 text-rose-800'
																: ticket.currentStatus?.statusName === 'Open'
																	? 'bg-purple-100 text-purple-800'
																	: 'bg-gray-100 text-gray-800'}"
										>
											<svg xmlns="http://www.w3.org/2000/svg" class="h-3 w-3" viewBox="0 0 24 24"
												><path
													fill="currentColor"
													d="M20.372 3.86v10.88a.75.75 0 0 1-.43.68a11.85 11.85 0 0 1-4.64 1.16a5.9 5.9 0 0 1-3.71-1.21a4.45 4.45 0 0 0-2.85-1a7.16 7.16 0 0 0-3.61.73v6.13a.75.75 0 1 1-1.5 0V3.86a1 1 0 0 1 0-.15a.76.76 0 0 1 .31-.47c.38-.32 1.73-1.22 4.78-1.22a5.87 5.87 0 0 1 3.68 1.22a4.46 4.46 0 0 0 2.85 1a10.3 10.3 0 0 0 4-1a.74.74 0 0 1 .72.05a.73.73 0 0 1 .4.57"
												/></svg
											>
											{ticket.currentStatus?.statusName || 'No Status'}
										</span>
									</td>

									<td class="w-[150px] px-4 py-3 text-sm whitespace-nowrap text-gray-900">
										{ticket.category?.name || 'No Category'}
									</td>
									<td class="w-[150px] px-4 py-3 text-sm whitespace-nowrap text-gray-900">
										<div class="flex items-center gap-2">
											{#if ticket.agent?.profilePictureUrl && !imageError}
												<!-- svelte-ignore a11y_img_redundant_alt -->
												<img
													src={`${import.meta.env.VITE_API_URL}/uploads/profiles/${ticket.agent?.profilePictureUrl}`}
													alt="Profile picture"
													class="h-8 w-8 rounded-full border-1 border-emerald-300"
													on:error={() => (imageError = true)}
												/>
											{:else}
												<div
													class="flex h-8 w-8 items-center justify-center rounded-full bg-gray-300 text-sm font-bold text-white"
												>
													<!-- svelte-ignore a11y_img_redundant_alt -->
													<img
														src="/default-profile.jpg"
														class="h-8 w-8 rounded-full border-1 border-emerald-300"
														alt="Profile picture"
													/>
												</div>
											{/if}
											<span>{ticket.agent?.agentName || 'Sin asignar'}</span>
										</div>
									</td>

									<td class="w-[120px] px-4 py-3 text-sm whitespace-nowrap text-gray-900">
										{formatDate(ticket.createdAt)}
									</td>
									<td class="w-[120px] px-4 py-3 text-sm whitespace-nowrap text-gray-900">
										{ticket.updatedAt ? formatRelativeTime(ticket.updatedAt) : 'No modificado'}
									</td>
								</tr>
							{/each}
						</tbody>
					</table>
				</div>
			</div>
		</div>
	{/if}
</div>
