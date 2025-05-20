<!-- src/routes/tickets/[id]/+page.svelte -->
<script lang="ts">
	import { goto } from '$app/navigation';
	import Header from '../../../components/Header.svelte';
	import SubHeader from '../../../components/SubHeader.svelte';
	import TicketComment from '../../../components/TicketComment.svelte';
	import Nav from '../../../components/Nav.svelte';
	import { onMount } from 'svelte';
	import { getAgents } from '$lib/api/users';
	import TicketPanel from '../../../components/TicketPanel.svelte';
	import UserCard from '../../../components/UserCard.svelte';
	import { updateTicketStatus } from '$lib/api/tickets';
	import ProtectedRoute from '../../../components/ProtectedRoute.svelte';

	export let data;

	// Datos iniciales
	let selectedPriority = {
		id: data.ticket.priority?.id || 0,
		name: data.ticket.priority?.name || ''
	};
	let selectedStatus = {
		id: data.ticket.currentStatus?.id || 0,
		name: data.ticket.currentStatus?.statusName || ''
	};
	let selectedCategory = {
		id: data.ticket.category?.id || 0,
		name: data.ticket.category?.name || ''
	};
	let selectedAgent = {
		id: data.ticket.agent?.id || 0,
		name: data.ticket.agent?.agentName || ''
	};

	let agentOptions: Array<{
		id: number;
		firstName: string;
		lastName: string;
		fullName: string;
		email: string;
	}> = [];
	let isLoadingAgents = false;
	let agentError = '';

	// Cargar agentes al montar el componente
	onMount(async () => {
		try {
			isLoadingAgents = true;
			const agents = await getAgents();

			agentOptions = agents.map((agent) => ({
				id: agent.id,
				firstName: agent.firstName,
				lastName: agent.lastName,
				email: agent.email,
				fullName: `${agent.firstName} ${agent.lastName}`
			}));

			// Seleccionar el agente actual si existe
			if (data.ticket.agent?.agentId) {
				const currentAgent = agentOptions.find((a) => a.id === data.ticket.agent.agentId);
				if (currentAgent) {
					selectedAgent = {
						id: currentAgent.id,
						name: currentAgent.fullName
					};
				}
			}
		} catch (error) {
			console.error('Error loading agents:', error);
			agentError = 'Failed to load agents';
		} finally {
			isLoadingAgents = false;
		}
	});

	// Opciones con ID y nombre
	const statusOptions = [
		{ id: 1, name: 'Open' },
		{ id: 2, name: 'In progress' },
		{ id: 3, name: 'Pending' },
		{ id: 4, name: 'Closed' },
		{ id: 5, name: 'Solved' }
	];

	const priorityOptions = [
		{ id: 1, name: 'Low' },
		{ id: 2, name: 'Medium' },
		{ id: 3, name: 'High' },
		{ id: 4, name: 'Urgent' }
	];

	const categoryOptions = [
		{ id: 1, name: 'Technical Support' },
		{ id: 2, name: 'Billing' },
		{ id: 3, name: 'Account Management' },
		{ id: 4, name: 'General Inquiry' },
		{ id: 5, name: 'Network Issues' },
		{ id: 6, name: 'Security' },
		{ id: 7, name: 'Software Installation' },
		{ id: 8, name: 'Feature Request' }
	];

	async function handleStatusUpdate() {
	try {
		if (
			selectedStatus.id === 0 ||
			selectedPriority.id === 0 ||
			selectedCategory.id === 0 ||
			selectedAgent.id === 0
		) {
			throw new Error('Please select values for all fields before updating.');
		}

		const updateData = {
			ticketId: data.ticket.id,
			statusId: selectedStatus.id,
			priorityId: selectedPriority.id,
			categoryId: selectedCategory.id,
			userId: selectedAgent.id
		};

		await updateTicketStatus(updateData);

		// Actualiza el estado local del ticket
		data.ticket.currentStatus = {
			statusId: selectedStatus.id,
			statusName: statusOptions.find((s) => s.id === selectedStatus.id)?.name || ''
		};

		data.ticket.priority = priorityOptions.find((p) => p.id === selectedPriority.id);
		data.ticket.category = categoryOptions.find((c) => c.id === selectedCategory.id);
		data.ticket.agent = {
			id: selectedAgent.id,
			agentName: agentOptions.find((a) => a.id === selectedAgent.id)?.fullName || '',
			mail: '' // Agrega el mail si es necesario
		};

		data.ticket.updatedAt = new Date().toISOString();

		alert('Ticket updated successfully!');
	} catch (error) {
		console.error('Error updating ticket:', error);
		alert(error instanceof Error ? error.message : 'Failed to update ticket');
	}
}


	let isPanelVisible = true;

	function togglePanel() {
		isPanelVisible = !isPanelVisible;
	}

	function goToProfile(userId: string) {
		goto(`/users/${userId}`);
	}
	function getInitials(name: string): string {
		const names = name.split(' ');
		let initials = names[0].substring(0, 1).toUpperCase();

		if (names.length > 1) {
			initials += names[names.length - 1].substring(0, 1).toUpperCase();
		}

		return initials;
	}
	console.log(data.ticket);
</script>

<ProtectedRoute>
	<div class="flex h-screen overflow-hidden bg-emerald-100">
		<Nav />

		<div class="flex flex-1 flex-col">
			<Header search={false} text="Ticket #{data.ticket.id}" />
			<SubHeader showSelect={false} {isPanelVisible} {togglePanel} collapse={true} />
			<div class="flex flex-1 overflow-hidden">
				<!-- Contenido principal del ticket -->
				<div class="flex-1 overflow-y-auto p-6">
					<div class="mx-auto max-w-4xl">
						<div class="mb-6 flex items-center justify-end">
							<span class="text-sm text-gray-500">
								{#if data.ticket.updatedAt}
									Last modified: {new Date(data.ticket.updatedAt).toLocaleDateString()}
								{:else}
									Not modified yet
								{/if}
							</span>
						</div>

						<div class="mb-6 rounded-lg bg-white p-6 shadow">
							<div class="mb-2 flex gap-3">
								{#if data.ticket.client.profilePictureUrl || data.ticket.client.profilePictureUrl !== null}
									<img
										src={`${import.meta.env.VITE_API_URL}/uploads/profiles/${data.ticket.client.profilePictureUrl}`}
										alt={data.ticket.client.clientName}
										class="h-12 w-12 rounded-full border-2 border-emerald-600"
									/>
								{:else}
									<div
										class="flex h-12 w-12 items-center justify-center rounded-full border-2 border-emerald-600 bg-gray-300"
									>
										<span class="text-md font-bold text-white"
											>{getInitials(data.ticket.client.clientName)}</span
										>
									</div>
								{/if}
								<div class="my-1">
									<h2 class="font-medium text-gray-500">
										Created by
										<UserCard
											user={{
												name: data.ticket.client.clientName,
												email: data.ticket.client.mail
											}}
											href={`/users/${data.ticket.client.id}`}
										>
											{data.ticket.client.clientName}
										</UserCard>
									</h2>
									<p class="text-xs text-gray-400">
										{new Date(data.ticket.createdAt).toLocaleString()}
									</p>
								</div>
							</div>

							<h2 class="mb-4 text-xl font-semibold">
								{data.ticket.title} <span class="text-gray-400">#{data.ticket.id}</span>
							</h2>

							<div class="prose max-w-none">
								<p>{data.ticket.description}</p>
							</div>
						</div>

						<!-- Sección de comentarios -->
						<div class="rounded-lg bg-white p-6 shadow">
							<h3 class="mb-4 text-lg font-medium">Respuesta del agente</h3>

							{#if data.ticket.comments.length > 0}
								{#each data.ticket.comments as comment}
									<div class="mb-4 border-b border-gray-100 pb-4 last:border-0">
										<div class="mb-2 flex items-start justify-between">
											<div class="flex items-center gap-3">
												{#if comment.profilePictureUrl}
													<img
														src={`${import.meta.env.VITE_API_URL}/uploads/profiles/${comment.profilePictureUrl}`}
														class="h-12 w-12 rounded-full border-2 border-emerald-600"
														alt={comment.userFullName}
													/>
												{:else}
													<div
														class="flex h-12 w-12 items-center justify-center rounded-full border-2 border-emerald-600 bg-gray-300"
													>
														<span class="text-md font-bold text-white"
															>{getInitials(comment.userFullName)}</span
														>
													</div>
												{/if}
												<UserCard
													user={{
														name: comment.userFullName,
														email: comment.userEmail
													}}
													href={`/users/${comment.userId}`}
												>
													{comment.userFullName}
												</UserCard>
											</div>
											<span class="text-xs text-gray-400"
												>{new Date(comment.writtenOn).toLocaleString()}</span
											>
										</div>
										<p class="text-gray-700">{comment.commentText}</p>
									</div>
								{/each}
							{:else}
								<p class="text-gray-500">No hay respuestas aún</p>
							{/if}
						</div>
						<TicketComment on:submitted={() => location.reload()} ticketId={data.ticket.id} />
					</div>
				</div>

				<!-- Panel derecho de opciones -->
				{#if isPanelVisible}
					<TicketPanel
						bind:selectedStatus
						bind:selectedPriority
						bind:selectedCategory
						bind:selectedAgent
						{statusOptions}
						{priorityOptions}
						{categoryOptions}
						{agentOptions}
						{isLoadingAgents}
						{agentError}
						{data}
						{handleStatusUpdate}
					/>
				{/if}
			</div>
		</div>
	</div>
</ProtectedRoute>

<style>
	.shadow {
		box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
	}
</style>
