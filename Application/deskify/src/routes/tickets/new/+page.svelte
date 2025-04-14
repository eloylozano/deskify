<!-- src/routes/new-ticket/+page.svelte (o como lo tengas tú) -->
<script lang="ts">
	import { goto } from '$app/navigation';
	import Header from '../../../components/Header.svelte';
	import Nav from '../../../components/Nav.svelte';

	let title = '';
	let description = '';
	let priority: 'Baja' | 'Media' | 'Alta' = 'Media';

	function createTicket() {
		console.log({ title, description, priority });
		goto('/tickets');
	}
</script>

<div class="flex h-screen bg-green-50">
	<Nav />

	<div class="flex flex-1 flex-col items-center">
		<Header />

        <div class="bg-white rounded-sm my-5 w-300 px-10 py-6">
           <h1 class="text-3xl text-gray-800 font-medium">Create a new incidence</h1> 
        </div>
        

	</div>
</div>

<style>
	:global(.bg-primary) {
		background-color: #01c883;
	}
	:global(.focus\:ring-primary:focus) {
		--tw-ring-color: #01c883;
	}
</style>
