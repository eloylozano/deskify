<!-- src/routes/tickets/[id]/+page.svelte -->
<script lang="ts">
	import { goto } from '$app/navigation';
	import Header from '../../../components/Header.svelte';
	import { updateTicketStatus } from '$lib/api/tickets';
	import SubHeader from '../../../components/SubHeader.svelte';
	import TicketComment from '../../../components/TicketComment.svelte';
	import Nav from '../../../components/Nav.svelte';
	import SubmitButton from '../../../components/SubmitButton.svelte';
	import { onMount } from 'svelte';
	import { getAgents } from '$lib/api/users';

	export let data;

	// Datos iniciales
	let selectedPriority = {
		id: data.ticket.priority?.id || 0,
		name: data.ticket.priority?.name || ''
	};
	let selectedStatus = {
		id: data.ticket.currentStatus?.statusId || 0,
		name: data.ticket.currentStatus?.statusName || ''
	};
	let selectedCategory = {
		id: data.ticket.category?.id || 0,
		name: data.ticket.category?.name || ''
	};
	let selectedAgent = {
		id: data.ticket.agent?.agentId || 0,
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
		{ id: 4, name: 'Solved' },
		{ id: 5, name: 'Closed' }
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
        // Validaciones
        if (!selectedAgent.id || selectedAgent.id === 0) {
            throw new Error('Please select an agent');
        }
        if (selectedStatus.id === 0) {
            throw new Error('Please select a status');
        }
        if (selectedPriority.id === 0) {
            throw new Error('Please select a priority');
        }
        if (selectedCategory.id === 0) {
            throw new Error('Please select a category');
        }

        // Actualización
        const updatedTicket = await updateTicketStatus({
            ticketId: data.ticket.id,
            statusId: selectedStatus.id,
            priorityId: selectedPriority.id,
            categoryId: selectedCategory.id,
            userId: selectedAgent.id
        });

        // Actualizar estado local
        data.ticket.priority = priorityOptions.find(p => p.id === selectedPriority.id);
        data.ticket.currentStatus = {
            statusId: selectedStatus.id,
            statusName: statusOptions.find(s => s.id === selectedStatus.id)?.name || ''
        };
        data.ticket.category = categoryOptions.find(c => c.id === selectedCategory.id);
        data.ticket.agent = {
            agentId: selectedAgent.id,
            agentName: agentOptions.find(a => a.id === selectedAgent.id)?.fullName || ''
        };
        data.ticket.updatedAt = new Date().toISOString();

        // Feedback al usuario
        alert('Ticket updated successfully!');
    } catch (error) {
        console.error('Error updating ticket:', error);
        alert(error.message || 'Failed to update ticket');
    }
}

	let isPanelVisible = true;

	function togglePanel() {
		isPanelVisible = !isPanelVisible;
	}

	function goToProfile(userId: string) {
		goto(`/users/${userId}`);
	}
</script>

<div class="flex h-screen overflow-hidden bg-emerald-100">
	<Nav />

	<div class="flex flex-1 flex-col">
		<Header search={false} text="Ticket #{data.ticket.id}" />
		<SubHeader showSelect={false} {isPanelVisible} {togglePanel} />
		<div class="flex flex-1 overflow-hidden">
			<!-- Contenido principal del ticket -->
			<div class="flex-1 overflow-y-auto p-6">
				<div class="mx-auto max-w-4xl">
					<div class="mb-6 flex items-center justify-end">
						<span class="text-sm text-gray-500"
							>Last modified: {new Date(data.ticket.updatedAt).toLocaleDateString()}</span
						>
					</div>

					<div class="mb-6 rounded-lg bg-white p-6 shadow">
						<div class="mb- flex gap-3">
							<img
								src="/default-profile.jpg"
								class="h-12 w-12 rounded-full border-2 border-emerald-600"
								alt="Profile pic"
							/>
							<div class="my-1">
								<h2 class="text-sm font-medium text-gray-500">
									Created by
									<!-- Hacemos que el nombre del creador sea un enlace -->
									<a
										href="#"
										on:click={() => goToProfile(data.ticket.client.id)}
										class="text-emerald-600 hover:underline"
									>
										{data.ticket.client.clientName}
									</a>
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
										<div class="flex items-center">
											<img
												src="/default-profile.jpg"
												alt={comment.userFullName}
												class="mr-2 h-8 w-8 rounded-full"
											/>
											<!-- Hacemos que el nombre del creador sea un enlace -->
											<a
												href="#"
												on:click={() => goToProfile(comment.userId)}
												class="text-emerald-600 hover:underline"
											>
												{comment.userFullName}
											</a>
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
					<TicketComment />
				</div>
			</div>

			<!-- Panel derecho de opciones -->
			{#if isPanelVisible}
				<div class="w-64 overflow-y-auto border-l border-gray-200 bg-white p-4">
					<h3 class="mb-4 font-medium">Update ticket</h3>

					<div class="space-y-4">
						<div>
							<label class="mb-1 block text-sm font-medium text-gray-700"
								>Status
								<select
									bind:value={selectedStatus.id}
									class="select-field w-full rounded border border-gray-300 px-3 py-2 text-sm"
								>
									<option value={0} disabled>Select status</option>
									{#each statusOptions as option}
										<option value={option.id}>{option.name}</option>
									{/each}
								</select>
							</label>
						</div>

						<div>
							<label class="mb-1 block text-sm font-medium text-gray-700"
								>Priority
								<select
									bind:value={selectedPriority.id}
									class="select-field w-full rounded border border-gray-300 px-3 py-2 text-sm"
								>
									<option value={0}>Select priority</option>
									{#each priorityOptions as option}
										<option value={option.id}>{option.name}</option>
									{/each}
								</select>
							</label>
						</div>

						<div>
							<label class="mb-1 block text-sm font-medium text-gray-700"
								>Category
								<select
									bind:value={selectedCategory.id}
									class="select-field w-full rounded border border-gray-300 px-3 py-2 text-sm"
								>
									<option value={0}>Select category</option>
									{#each categoryOptions as option}
										<option value={option.id}>{option.name}</option>
									{/each}
								</select>
							</label>
						</div>

						<div>
							<label class="mb-1 block text-sm font-medium text-gray-700">Assign to</label>
							{#if isLoadingAgents}
								<div class="select-field w-full rounded border border-gray-300 px-3 py-2 text-sm">
									Loading agents...
								</div>
							{:else if agentError}
								<div class="text-sm text-red-500">{agentError}</div>
							{:else}
								<select
									bind:value={selectedAgent.id}
									class="select-field w-full rounded border border-gray-300 px-3 py-2 text-sm"
								>
									<option value={0}>Select an agent</option>
									{#each agentOptions as agent}
										<option value={agent.id}>{agent.fullName}</option>
									{/each}
								</select>
							{/if}
						</div>

						<div class="flex justify-center">
							<SubmitButton on:click={handleStatusUpdate} text="Update"></SubmitButton>
						</div>
					</div>

					<div class="mt-6 border-t border-gray-200 pt-6">
						<h3 class="mb-2 font-medium">More details</h3>
						<div class="space-y-2 text-sm">
							<div class="flex justify-between">
								<span class="text-gray-500">ID:</span>
								<span>#{data.ticket.id}</span>
							</div>
							<div class="flex justify-between">
								<span class="text-gray-500">Created at:</span>
								<span>{new Date(data.ticket.createdAt).toLocaleDateString()}</span>
							</div>
							<div class="flex justify-between">
								<span class="text-gray-500">Client:</span>
								<span>{data.ticket.client.clientName}</span>
							</div>
							<div class="flex justify-between">
								<span class="text-gray-500">Email:</span>
								<span>{data.ticket.client.mail}</span>
							</div>
						</div>
					</div>
				</div>
			{/if}
		</div>
	</div>
</div>

<style>
	.shadow {
		box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
	}

	select:focus,
	textarea:focus {
		border-color: #01c883;
		outline: none;
	}

	.select-field {
		width: 100%;
		padding: 8px;
		border: none;
		border-radius: 8px;
		box-shadow: inset 2px 2px 10px 2px rgba(78, 78, 78, 0.25);
		background-color: rgba(255, 255, 255, 0.85);
		transition: box-shadow 0.3s ease;
		appearance: none;
		-webkit-appearance: none;
		-moz-appearance: none;
	}

	.select-field:focus {
		border-color: #00aa6f;
		box-shadow:
			inset 0 2px 4px rgba(0, 0, 0, 0.4),
			0 0 5px #00a750;
		outline: none;
	}
</style>
