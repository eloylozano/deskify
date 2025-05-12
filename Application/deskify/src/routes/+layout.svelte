<!-- src/routes/+layout.svelte -->
<script lang="ts">
	import '../app.css';
	import { isAuthenticated } from '$lib/api/auth';
	import { goto } from '$app/navigation';
	import { page } from '$app/stores';

	// Verificar autenticación en el cliente
	if (typeof window !== 'undefined') {
		const publicRoutes = ['/login', '/register'];
		if (!isAuthenticated() && !publicRoutes.includes($page.url.pathname)) {
			goto(`/login`);
		}
	}
</script>

<slot />