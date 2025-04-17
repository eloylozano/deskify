<script lang="ts">
	import Header from '../../components/Header.svelte';
	import Nav from '../../components/Nav.svelte';
	import UsersTable from '../../components/UsersTable.svelte';

	export let data;

</script>

<div class="flex h-[100vh] bg-green-100 overflow-hidden">
	<Nav />
	<div class="w-full">
		<Header/>
        <UsersTable {data} />
    </div>
</div>
