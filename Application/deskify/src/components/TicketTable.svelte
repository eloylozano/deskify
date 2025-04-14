<script lang="ts">
	import { onMount } from 'svelte';
	import { getTickets } from '$lib/api/tickets';
	import CustomCheckbox from './CustomCheckbox.svelte';
	import Loading from './Loading.svelte';
	import TicketListHeader from './TicketListHeader.svelte';

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

	// Función para formatear la fecha relativa
	function formatRelativeTime(dateString: string | Date): string {
		const date = new Date(dateString);
		const now = new Date();
		const diffInSeconds = Math.floor((now.getTime() - date.getTime()) / 1000);

		if (diffInSeconds < 60) {
			return 'hace unos segundos';
		} else if (diffInSeconds < 3600) {
			const minutes = Math.floor(diffInSeconds / 60);
			return `hace ${minutes}m`;
		} else if (diffInSeconds < 86400) {
			const hours = Math.floor(diffInSeconds / 3600);
			return `hace ${hours}h`;
		} else {
			const days = Math.floor(diffInSeconds / 86400);
			return `hace ${days}d`;
		}
	}
</script>

<TicketListHeader></TicketListHeader>
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
										>Título</th
									>
									<th class="w-[100px] px-4 py-3 text-left text-sm font-semibold text-gray-700"
										>Prioridad</th
									>
									<th class="w-[120px] px-4 py-3 text-left text-sm font-semibold text-gray-700"
										>Estado</th
									>
									<th class="w-[150px] px-4 py-3 text-left text-sm font-semibold text-gray-700"
										>Categoría</th
									>
									<th class="w-[150px] px-4 py-3 text-left text-sm font-semibold text-gray-700"
										>Agente</th
									>
									<th class="w-[120px] px-4 py-3 text-left text-sm font-semibold text-gray-700"
										>Creado</th
									>
									<th class="w-[120px] px-4 py-3 text-left text-sm font-semibold text-gray-700"
										>Modificado</th
									>
								</tr>
							</thead>
						</table>
					</div>
				</div>

				<!-- Cuerpo de la tabla con scroll -->
				<div class="flex-1 overflow-x-auto overflow-y-auto">
					<table class="mt-3 w-full divide-y divide-gray-200">
						<tbody class="divide-y divide-gray-200 bg-white">
							{#each tickets as ticket (ticket.id)}
								<tr class="hover:bg-gray-50">
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
						  {ticket.priority?.name === 'Urgente'
												? 'bg-red-100 text-red-800 uppercase'
												: ticket.priority?.name === 'Alta'
													? 'bg-yellow-100 text-yellow-800'
													: ticket.priority?.name === 'Media'
														? 'bg-green-100 text-green-800'
														: 'bg-blue-100 text-blue-800'}"
										>
											{ticket.priority?.name || 'N/A'}
										</span>
									</td>
									<td class="w-[100px] px-4 py-3 text-sm whitespace-nowrap text-gray-900">
										<span
											class="inline-flex items-center gap-1 rounded-full px-2 text-xs leading-5 font-semibold
						  {ticket.currentStatus?.statusName === 'Abierto'
												? 'bg-purple-100 text-purple-800'
												: ticket.currentStatus?.statusName === 'En Progreso'
													? 'bg-blue-100 text-blue-800'
													: ticket.currentStatus?.statusName === 'Pendiente'
														? 'bg-amber-100 text-amber-800'
														: ticket.currentStatus?.statusName === 'Cerrado'
															? 'bg-gray-100 text-gray-800'
															: ticket.currentStatus?.statusName === 'Resuelto'
																? 'bg-green-100 text-green-800'
																: 'bg-pink-100 text-pink-800'}"
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
										{ticket.category?.name || 'N/A'}
									</td>
									<td class="w-[150px] px-4 py-3 text-sm whitespace-nowrap text-gray-900">
										<div class="flex items-center gap-2">
											<!-- svelte-ignore a11y_img_redundant_alt -->
											<img
												src={ticket.agent?.profilePictureUrl || '/default-profile.jpg'}
												alt="Profile picture"
												class="h-8 w-8 rounded-full"
											/>
											<span>{ticket.agent?.agentName || 'Sin asignar'}</span>
										</div>
									</td>

									<td class="w-[120px] px-4 py-3 text-sm whitespace-nowrap text-gray-900">
										{formatRelativeTime(ticket.createdAt)}
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
