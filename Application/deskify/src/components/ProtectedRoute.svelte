<script lang="ts">
	import { isAuthenticated } from '$lib/api/auth';
	import { goto } from '$app/navigation';
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