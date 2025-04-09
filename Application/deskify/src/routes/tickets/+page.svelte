<script>
	import Header from './../../components/Header.svelte';
	import Nav from '../../components/Nav.svelte';
	import TicketTable from '../../components/TicketTable.svelte';
</script>

<div class="flex h-[100vh] bg-green-50">
	<Nav />
	<div class="w-full">
		<Header></Header>
		<TicketTable></TicketTable>
	</div>
</div>

<style>
</style>
