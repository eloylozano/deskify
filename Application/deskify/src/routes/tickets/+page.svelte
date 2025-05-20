<script lang="ts">
	import Header from './../../components/Header.svelte';
	import Nav from '../../components/Nav.svelte';
	import TicketTable from '../../components/TicketTable.svelte';

	let searchTerm = '';

	function handleSearch(event: CustomEvent<string>) {
		searchTerm = event.detail;
	}
</script>

<div class="flex h-[100vh] overflow-hidden bg-emerald-100">
	<Nav />
	<div class="w-full">
		<Header on:search={handleSearch} text="All tickets" />
		<TicketTable {searchTerm}  />
	</div>
</div>
