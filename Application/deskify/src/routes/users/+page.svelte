<script lang="ts">
	import Header from '../../components/Header.svelte';
	import Nav from '../../components/Nav.svelte';
	import ProtectedRoute from '../../components/ProtectedRoute.svelte';
	import UsersTable from '../../components/UsersTable.svelte';

	export let data;
	let searchTerm = '';

	function handleSearch(event: CustomEvent<string>) {
		searchTerm = event.detail;
	}
</script>

<ProtectedRoute>
	<div class="flex h-[100vh] overflow-hidden bg-green-100">
		<Nav />
		<div class="w-full">
			<Header on:search={handleSearch} text="All users" />
			<UsersTable {data} {searchTerm} />
		</div>
	</div>
</ProtectedRoute>
