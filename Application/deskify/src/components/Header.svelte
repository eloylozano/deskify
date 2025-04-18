<script>
	import { createEventDispatcher } from 'svelte';
	import CustomInput from './CustomInput.svelte';

	const dispatch = createEventDispatcher();

	let showSearch = false;
	let searchQuery = '';

	function toggleSearch() {
		showSearch = !showSearch;
		if (!showSearch) {
			searchQuery = '';
			dispatch('search', '');
		}
	}

	function handleSearch(e) {
		searchQuery = e.target.value;
		dispatch('search', searchQuery);
	}

	let isChecked = false;

	export let showSearchIcon = true;
</script>

<div class="w-full">
	<div id="top" class="flex items-center justify-between bg-[#01c883] px-5 py-4">
		<div>
			<p class="font-medium text-white">All tickets - deskify</p>
		</div>

		<div class="flex items-center gap-5">
			<!-- Contenedor de búsqueda -->
			<div class="flex items-center">
				{#if showSearch}
					<!-- svelte-ignore a11y_autofocus -->
					<CustomInput
					type="text"
					bind:value={searchQuery}
					on:input={handleSearch}
					placeholder="Search..."
					autofocus
				/>

				{:else}
					<!-- Icono Lupa -->
					<!-- svelte-ignore a11y_consider_explicit_label -->

					{#if showSearchIcon}
						<button on:click={toggleSearch}>
							<svg xmlns="http://www.w3.org/2000/svg" class="h-7 w-7 text-white" viewBox="0 0 24 24"
								><path
									fill="currentColor"
									d="M9.5 16q-2.725 0-4.612-1.888T3 9.5t1.888-4.612T9.5 3t4.613 1.888T16 9.5q0 1.1-.35 2.075T14.7 13.3l5.6 5.6q.275.275.275.7t-.275.7t-.7.275t-.7-.275l-5.6-5.6q-.75.6-1.725.95T9.5 16m0-2q1.875 0 3.188-1.312T14 9.5t-1.312-3.187T9.5 5T6.313 6.313T5 9.5t1.313 3.188T9.5 14"
								/></svg
							>
						</button>
					{/if}
				{/if}
			</div>

			<a href="/tickets/new" aria-label="Create ticket">
				<svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6 text-white" viewBox="0 0 48 48"
					><g fill="none" stroke="#fff" stroke-linejoin="round" stroke-width="4"
						><rect width="36" height="36" x="6" y="6" rx="3" /><path
							stroke-linecap="round"
							d="M24 16v16m-8-8h16"
						/></g
					></svg
				>
			</a>
			<!-- svelte-ignore a11y_img_redundant_alt -->
			<img src="/default-profile.jpg" alt="Profile picture" class="h-10 w-10 rounded-full" />
		</div>
	</div>
</div>
