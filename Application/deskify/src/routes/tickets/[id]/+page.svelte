<!-- src/routes/tickets/[id]/+page.svelte -->
<script lang="ts">
	import { goto } from '$app/navigation';
	import Header from '../../../components/Header.svelte';
	import Nav from '../../../components/Nav.svelte';
	import { updateTicketStatus } from '$lib/api/tickets';
	import SubHeader from '../../../components/SubHeader.svelte';
	import TicketComment from '../../../components/TicketComment.svelte';
	import SubmitButton from '../../../components/SubmitButton.svelte';

	export let data;

	let selectedPriority = data.ticket.priority?.name || '';
	let selectedStatus = data.ticket.currentStatus?.statusName || '';
	let selectedCategory = data.ticket.category?.name || '';
	let selectedAgent = data.ticket.agent?.agentName || '';

	const statusOptions = ['Open', 'In progress', 'Pending', 'Solved', 'Closed'];
	const priorityOptions = ['Low', 'Medium', 'High', 'Urgent'];
	const categoryOptions = ['Facturación', 'Soporte técnico', 'Cuenta', 'Otros'];
	const agentOptions = ['Carlos', 'María', 'Juan', 'Ana'];

	async function handleStatusUpdate() {
		try {
			await updateTicketStatus({
				id: data.ticket.id,
				status: selectedStatus,
				priority: selectedPriority,
				category: selectedCategory,
				agent: selectedAgent
			});
			// Actualizar la página para reflejar los cambios
			goto(`/tickets/${data.ticket.id}`, { replaceState: true });
		} catch (error) {
			console.error('Error updating ticket:', error);
			alert('No se pudo actualizar el ticket');
		}
	}
</script>

<div class="flex h-screen overflow-hidden bg-gray-50">
	<Nav />

	<div class="flex flex-1 flex-col">
		<Header />
		<SubHeader />

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
									Creado por {data.ticket.client.clientName}
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
											<span class="font-medium">{comment.userFullName}</span>
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
			<div class="w-64 overflow-y-auto border-l border-gray-200 bg-white p-4">
				<h3 class="mb-4 font-medium">Update ticket</h3>

				<div class="space-y-4">
					<div>
						<label class="mb-1 block text-sm font-medium text-gray-700">Status</label>
						<select
							bind:value={selectedStatus}
							class="w-full rounded border border-gray-300 px-3 py-2 text-sm"
						>
							{#each statusOptions as option}
								<option value={option}>{option}</option>
							{/each}
						</select>
					</div>

					<div>
						<label class="mb-1 block text-sm font-medium text-gray-700">Priority</label>
						<select
							bind:value={selectedPriority}
							class="w-full rounded border border-gray-300 px-3 py-2 text-sm"
						>
							{#each priorityOptions as option}
								<option value={option}>{option}</option>
							{/each}
						</select>
					</div>

					<div>
						<label class="mb-1 block text-sm font-medium text-gray-700">Category</label>
						<select
							bind:value={selectedCategory}
							class="w-full rounded border border-gray-300 px-3 py-2 text-sm"
						>
							{#each categoryOptions as option}
								<option value={option}>{option}</option>
							{/each}
						</select>
					</div>

					<div>
						<label class="mb-1 block text-sm font-medium text-gray-700">Assign to</label>
						<select
							bind:value={selectedAgent}
							class="w-full rounded border border-gray-300 px-3 py-2 text-sm"
						>
							{#each agentOptions as option}
								<option value={option}>{option}</option>
							{/each}
						</select>
					</div>

					<div class="flex justify-center"><SubmitButton on:click={handleStatusUpdate} text="Update"></SubmitButton></div>
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
</style>
