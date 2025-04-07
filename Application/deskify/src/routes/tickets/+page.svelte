<script>
	import TicketTable from './../../components/TicketTable.svelte';
	import Header from '../../components/Header.svelte';
	import Nav from '../../components/Nav.svelte';
</script>

<div class="flex h-[100vh] bg-green-50">
	<Nav />
	<div class="w-full" >
		<Header />
		<TicketTable />
	</div>
</div>

<style>
</style>
