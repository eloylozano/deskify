<script lang="ts">
	import { goto } from '$app/navigation';
	import { isAuthenticated } from '$lib/api/login';
	import { onMount } from 'svelte';
	
	onMount(() => {
		if (!isAuthenticated()) {
			goto('/login');
		}
	});
	
</script>

{#if isAuthenticated()}
	<slot />
{:else}
	<p>Redirigiendo al login...</p>
{/if}