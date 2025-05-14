<script lang="ts">
	import { createEventDispatcher } from 'svelte';
	import { fetchUser, user } from '$lib/stores/user';
	import { get } from 'svelte/store';
	import { browser } from '$app/environment';
	import { onMount } from 'svelte';
	import { goto } from '$app/navigation';
	import type { User } from '$lib/stores/user';
	import { getAuthToken } from '$lib/api/login';

	// Tipos para las props y eventos
	interface $$Props {
		search?: boolean;
		text?: string;
	}

	interface $$Events {
		search: string;
	}

	const dispatch = createEventDispatcher<$$Events>();

	export let search: boolean = true;
	export let text: string = '';
	export let data: { user?: User } = {};

	let showSearch = false;
	let searchQuery = '';
	let userLoaded = false;

	onMount(async () => {
		if (browser) {
			const token = getAuthToken();
			if (token && !get(user)) {
				await fetchUser();
			}
			userLoaded = true;
		}
	});

	function toggleSearch() {
		showSearch = !showSearch;
		if (!showSearch) {
			searchQuery = '';
			dispatch('search', '');
		}
	}

	function handleSearch(e: Event) {
		const target = e.target as HTMLInputElement;
		searchQuery = target.value;
		dispatch('search', searchQuery);
	}
	export function isAuthenticated(): boolean {
		if (typeof window === 'undefined') return false;
		const token = sessionStorage.getItem('authToken');
		return !!token;
	}
	function goToUserProfile() {
		const userId = sessionStorage.getItem('userId');
		if (userId) {
			goto(`/users/${userId}`);
		} else {
			console.error('No hay usuario logueado');
		}
	}
</script>

<div class="w-full">
	<div id="top" class="flex items-center justify-between bg-[#01c883] px-5 py-4">
		<div>
			<p class="font-medium text-white">{text} - deskify</p>
		</div>

		<div class="flex items-center gap-5">
			{#if search}
				<div class="flex items-center">
					{#if showSearch}
						<!-- svelte-ignore a11y_autofocus -->
						<input
							type="text"
							bind:value={searchQuery}
							on:input={handleSearch}
							placeholder="Search..."
							class="input-field mr-2 h-10 rounded-md px-3 py-1 transition-all duration-300 focus:ring-2 focus:ring-white focus:outline-none"
							autofocus
						/>
					{:else}
						<!-- Icono Lupa -->
						<button on:click={toggleSearch} aria-label="Search">
							<svg
								xmlns="http://www.w3.org/2000/svg"
								class="h-7 w-7 text-white"
								viewBox="0 0 24 24"
							>
								<path
									fill="currentColor"
									d="M9.5 16q-2.725 0-4.612-1.888T3 9.5t1.888-4.612T9.5 3t4.613 1.888T16 9.5q0 1.1-.35 2.075T14.7 13.3l5.6 5.6q.275.275.275.7t-.275.7t-.7.275t-.7-.275l-5.6-5.6q-.75.6-1.725.95T9.5 16m0-2q1.875 0 3.188-1.312T14 9.5t-1.312-3.187T9.5 5T6.313 6.313T5 9.5t1.313 3.188T9.5 14"
								/>
							</svg>
						</button>
					{/if}
				</div>
			{/if}

			<a href="/tickets/new" aria-label="Create ticket">
				<svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6 text-white" viewBox="0 0 48 48">
					<g fill="none" stroke="#fff" stroke-linejoin="round" stroke-width="4">
						<rect width="36" height="36" x="6" y="6" rx="3" />
						<path stroke-linecap="round" d="M24 16v16m-8-8h16" />
					</g>
				</svg>
			</a>

			<!-- Imagen de perfil del usuario logeado -->
			{#if userLoaded}
				{#if isAuthenticated()}
					<button
						on:click={goToUserProfile}
						aria-label="User profile"
						class="rounded-full focus:ring-2 focus:ring-white focus:ring-offset-2 focus:ring-offset-[#01c883] focus:outline-none"
					>
						<!-- svelte-ignore a11y_img_redundant_alt -->
						<img
							src={($user || data.user)?.profilePictureUrl || '/default-profile.jpg'}
							alt="Profile picture"
							class="h-10 w-10 rounded-full object-cover"
						/>
					</button>
				{:else}
					<!-- Si no hay usuario pero ya se cargó -->
					<button
						on:click={() => goto('/login')}
						aria-label="Login"
						class="rounded-full focus:ring-2 focus:ring-white focus:ring-offset-2 focus:ring-offset-[#01c883] focus:outline-none"
					>
						<!-- svelte-ignore a11y_img_redundant_alt -->
						<img src="/default-profile.jpg" alt="Profile picture" class="h-10 w-10 rounded-full" />
					</button>
				{/if}
			{:else}
				<!-- Mientras carga -->
				<div class="h-10 w-10 animate-pulse rounded-full bg-gray-200"></div>
			{/if}
		</div>
	</div>
</div>

<style>
	.input-field {
		border: none;
		border-radius: 8px;
		box-shadow: inset 2px 2px 10px 2px rgba(78, 78, 78, 0.15);
		transition: box-shadow 0.3s ease;
	}

	.input-field:focus {
		border-color: #00aa6f;
		box-shadow:
			inset 0 2px 4px rgba(0, 0, 0, 0.2),
			0 0 5px #00a750;
		outline: none;
	}
</style>
