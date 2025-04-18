<script lang="ts">
	import Header from './../../components/Header.svelte';
	import Nav from '../../components/Nav.svelte';
	import TicketTable from '../../components/TicketTable.svelte';
	
	let searchTerm: string = '';

	function handleSearch(event: CustomEvent<string>) {
		searchTerm = event.detail;
	}
	
</script>

<div class="flex h-[100vh] bg-green-100 overflow-hidden">
	<Nav />
	<div class="w-full">
		<Header on:search={handleSearch} />
		<TicketTable {searchTerm} />
	</div>
</div>

<style>
</style>