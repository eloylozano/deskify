<script>
	import { goto } from '$app/navigation'; // Esto es de SvelteKit para redirigir
	import Nav from '../../components/Nav.svelte';
	let username = '';
	let password = '';
	let errorMessage = '';
</script>

<div class="flex">
	<Nav />
	<div id="login-container" class="w-full flex flex-col items-center justify-center">
		<h1>Deskify</h1>
        <h2>Bienvenido de vuelta</h2>
        <hr>
	</div>
</div>

<style>
	#login-container {
		background-image: url('/backgrounds/Login.jpg');
		background-size: cover;
        background-repeat: no-repeat;
        background-position: center;
	}
</style>
