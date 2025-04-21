<script lang="ts">
import SubmitButton from './SubmitButton.svelte';
	export let statusOptions: any[] = [];
	export let priorityOptions: any[] = [];
	export let categoryOptions: any[] = [];
	export let agentOptions: any[] = [];
	export let selectedStatus;
	export let selectedPriority;
	export let selectedCategory;
	export let selectedAgent;
	export let isLoadingAgents = false;
	export let agentError = '';
	export let handleStatusUpdate: () => void;

	// Detalles del ticket
	export let data: {
		ticket: {
			id: number;
			createdAt: string;
			client: {
				clientName: string;
				mail: string;
			};
		};
	};
</script>

<div class="w-64 overflow-y-auto border-l border-gray-200 bg-white p-4">
	<h3 class="mb-4 font-medium">Update ticket</h3>

	<div class="space-y-4">
		<div>
			<label class="mb-1 block text-sm font-medium text-gray-700">
				Status
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
			<label class="mb-1 block text-sm font-medium text-gray-700">
				Priority
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
			<label class="mb-1 block text-sm font-medium text-gray-700">
				Category
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
			<label class="mb-1 block text-sm font-medium text-gray-700">
				Assign agent
				{#if isLoadingAgents}
					<p class="text-sm text-gray-500">Loading agents...</p>
				{:else}
					<select
						bind:value={selectedAgent.id}
						class="select-field w-full rounded border border-gray-300 px-3 py-2 text-sm"
					>
						<option value={0}>Select agent</option>
						{#each agentOptions as agent}
							<option value={agent.id}>{agent.fullName}</option>
						{/each}
					</select>
				{/if}
				{#if agentError}
					<p class="text-xs text-red-500">{agentError}</p>
				{/if}
			</label>
		</div>

        <div class="flex justify-center">
            <SubmitButton
                text="Update"
                additionalClass="w-full justify-center"
                on:click={handleStatusUpdate}
            />
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

<style>
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
