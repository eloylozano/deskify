<script lang="ts">
	import { createEventDispatcher } from 'svelte';

	export let showSelect = true;
	export let isPanelVisible = true;
	export let collapse = false;
	export let togglePanel = () => {};
	export let mode: 'tickets' | 'users' = 'tickets'; // Nueva prop para diferenciar modos

	const dispatch = createEventDispatcher();
	let sortOption = '1'; // Valor por defecto

	function handleSortChange() {
		dispatch('sortChange', sortOption);
	}
</script>

<div id="bot" class="flex items-center justify-between bg-gray-100 px-5 py-3">
	<div class="flex items-center">
		{#if showSelect}
			<p class="ml-2">
				<span class="font-medium text-[#858585]">Ordenar por:</span>
			</p>

			<select
				class="custom-select w-40 cursor-pointer font-medium text-[#252525]"
				bind:value={sortOption}
				on:change={handleSortChange}
			>
				{#if mode === 'tickets'}
					<option value="1">Last Modified</option>
					<option value="2">Priority</option>
					<option value="3">Status</option>
					<option value="4">Agent</option>
				{:else}
					<option value="1">Last Modified</option>
					<option value="2">Name (A-Z)</option>
					<option value="3">Company</option>
					<option value="4">Role</option>
					<option value="5">Email</option>
				{/if}
			</select>
		{/if}
	</div>

	<div class="flex items-center gap-3">
		<!-- <p class="font-medium text-[#858585]">1-30 de 99</p> -->

		<!-- <div class="flex justify-between gap-3 rounded-sm border-2 border-[#858585] p-1">
			<svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 rotate-180" viewBox="0 0 24 24">
				<path
					fill="#bebebe"
					d="M9.31 6.71a.996.996 0 0 0 0 1.41L13.19 12l-3.88 3.88a.996.996 0 1 0 1.41 1.41l4.59-4.59a.996.996 0 0 0 0-1.41L10.72 6.7c-.38-.38-1.02-.38-1.41.01"
					stroke-width="0.5"
					stroke="#bebebe"
				/>
			</svg>

			<svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" viewBox="0 0 24 24">
				<path
					fill="#bebebe"
					d="M9.31 6.71a.996.996 0 0 0 0 1.41L13.19 12l-3.88 3.88a.996.996 0 1 0 1.41 1.41l4.59-4.59a.996.996 0 0 0 0-1.41L10.72 6.7c-.38-.38-1.02-.38-1.41.01"
					stroke-width="0.5"
					stroke="#bebebe"
				/>
			</svg>
		</div> -->

		<!-- Botón de colapsar -->
		{#if collapse}
			<div class="rounded-sm border-2 border-[#858585] p-[2px] transition-transform duration-300">
				<svg
					xmlns="http://www.w3.org/2000/svg"
					class="h-6 w-6 transform cursor-pointer transition-transform duration-300"
					viewBox="0 0 24 24"
					class:is-rotated={!isPanelVisible}
					on:click={togglePanel}
					aria-label="collapse"
				>
					<g
						fill="none"
						stroke="#858585"
						stroke-linecap="round"
						stroke-linejoin="round"
						stroke-width="2"
					>
						<path
							d="M4 6a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H6a2 2 0 0 1-2-2zm11-2v16"
						/>
						<path d="m9 10l2 2l-2 2" />
					</g>
				</svg>
			</div>
		{/if}
	</div>
</div>

<style>
	.custom-select {
		border: none;
		border-radius: 6px;
		padding: 6px 10px;
		background-color: transparent;
		appearance: none;
		outline: none;
		background-image: url("data:image/svg+xml;charset=US-ASCII,%3Csvg xmlns='http://www.w3.org/2000/svg' width='12' height='12' viewBox='0 0 20 20'%3E%3Cpath fill='%2301c833' d='M5.516 7.548l4.484 4.484 4.484-4.484 1.032 1.032-5.516 5.516-5.516-5.516z'/%3E%3C/svg%3E");
		background-repeat: no-repeat;
		background-position: right 0.5rem center;
		background-size: 1rem;
	}

	.custom-select option {
		background-color: white;
		color: #252525;
		padding: 8px 12px;
		font-size: 14px;
	}

	.custom-select option:hover {
		background-color: #01c883;
		color: white;
	}

	.custom-select option:checked {
		background-color: #01c883;
		color: white;
	}

	.custom-select:focus {
		outline: none;
		box-shadow: none;
		border-color: transparent;
	}

	#bot {
		box-shadow: 0px 3px 5px 1px rgba(0, 0, 0, 0.2);
	}

	.is-rotated {
		transform: scaleX(-1);
	}
</style>
