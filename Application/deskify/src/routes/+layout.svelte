<!-- src/routes/+layout.svelte -->
<script lang="ts">
	import '../app.css';
	import { goto } from '$app/navigation';
	import { page } from '$app/stores';
	import { onMount } from 'svelte';
	import { fetchUser } from '$lib/stores/user';
	import { isAuthenticated } from '$lib/api/login';

	// Verificar autenticación en el cliente
	if (typeof window !== 'undefined') {
		const publicRoutes = ['/login', '/register'];
		if (!isAuthenticated() && !publicRoutes.includes($page.url.pathname)) {
			goto(`/login`);
		}
	}

	onMount(() => {
		fetchUser();
	});
</script>

<slot />
