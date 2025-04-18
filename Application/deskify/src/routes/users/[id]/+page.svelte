<script lang="ts">
	import { error } from '@sveltejs/kit';
	import SubHeader from '../../../components/SubHeader.svelte';
	import Header from '../../../components/Header.svelte';
	import CustomInput from '../../../components/CustomInput.svelte';
	import Nav from '../../../components/Nav.svelte';

	import { updateUser } from '$lib/api/users';
	import { page } from '$app/stores';
	import { onMount } from 'svelte';
	import { goto } from '$app/navigation';
	import SubmitButton from '../../../components/SubmitButton.svelte';

	export let data;

	// Formatear fecha
	function formatDate(dateString: string) {
		const date = new Date(dateString);
		return date.toLocaleDateString('es-ES');
	}

	interface User {
		id: number;
		firstName: string;
		middleName: string;
		lastName: string;
		email: string;
		phoneNumber: string;
		profilePictureUrl?: string;
		roleName: string;
		company?: string;
		createdAt: string;
		planName?: string;
	}

	let user: User = {} as User; // Asignar el tipo User

	let roles = ['Admin', 'Supervisor', 'Manager', 'Agent', 'User']; // Puedes traerlo de la API si quieres

	// Cargar datos al inicio
	onMount(() => {
		// Clonamos el objeto para edición
		user = { ...$page.data.user };
	});

	let isLoading = false;

	async function handleSubmit() {
		try {
			isLoading = true;
			await updateUser(user.id, user);
			goto('/users');
		} catch (err) {
			alert('Error al actualizar el usuario');
		} finally {
			isLoading = false;
		}
	}

	let imageError = false;

	function getInitials(user: User) {
		const first = user.firstName?.charAt(0) ?? '';
		const middle = user.middleName?.charAt(0) ?? '';
		return `${first}${middle}`.toUpperCase();
	}
</script>

<div class="flex h-[100vh] overflow-hidden bg-green-100">
	<Nav></Nav>
	<div class="w-full">
		<Header showSearchIcon={false}></Header>
		<div class="container mx-auto px-4 py-8">
			<div class="grid grid-cols-1 gap-8 lg:grid-cols-2">
				<!-- Columna izquierda - Datos personales -->
				<div class="rounded-lg bg-gray-50 p-6 shadow">
					<div class="flex flex-col items-center gap-4">
						{#if user.profilePictureUrl && !imageError}
							<img
								src={user.profilePictureUrl}
								alt="Foto de perfil"
								class="h-24 w-24 rounded-full"
								on:error={() => (imageError = true)}
							/>
						{:else}
							<div
								class="flex h-24 w-24 items-center justify-center rounded-full bg-gray-300 text-4xl font-bold text-white"
							>
								{getInitials(user)}
							</div>
						{/if}

						<h1 class="mb-6 text-3xl font-bold">
							Welcome {user.firstName}
							{user.middleName}
							{user.lastName}
						</h1>
					</div>

					<form class="space-y-4 px-12" on:submit|preventDefault={handleSubmit}>
						<div>
							<label class="text-sm text-gray-500"
								>First Name
								<CustomInput bind:value={user.firstName} additionalClass="" /></label
							>
						</div>

						<div class="flex gap-4">
							<div class="w-1/2">
								<label class="text-sm text-gray-500"
									>Middle Name
									<CustomInput bind:value={user.middleName} additionalClass="" /></label
								>
							</div>

							<div class="w-1/2">
								<label class="text-sm text-gray-500"
									>Last Name
									<CustomInput bind:value={user.lastName} additionalClass="" /></label
								>
							</div>
						</div>

						<div class="flex gap-4">
							<div class="w-1/2">
								<label class="text-sm text-gray-500"
									>Email
									<CustomInput type="email" bind:value={user.email} additionalClass="" /></label
								>
							</div>

							<div class="w-1/2">
								<label class="text-sm text-gray-500"
									>Phone number
									<CustomInput bind:value={user.phoneNumber} additionalClass="" /></label
								>
							</div>
						</div>

						<div class="flex gap-4">
							<div class="w-1/2">
								<label class="text-sm text-gray-500"
									>Role
									<select class="select-field" bind:value={user.roleName}>
										{#each roles as role}
											<option value={role}>{role}</option>
										{/each}
									</select></label
								>
							</div>

							<div class="w-1/2">
								<label class="text-sm text-gray-500"
									>Company
									<CustomInput bind:value={user.company} additionalClass="" /></label
								>
							</div>
						</div>

						<div class="mt-12 flex justify-center">
							{#if isLoading}
								<div class="flex items-center">
									<svg
										class="mr-2 h-5 w-5 animate-spin text-green-500"
										xmlns="http://www.w3.org/2000/svg"
										fill="none"
										viewBox="0 0 24 24"
										stroke="currentColor"
									>
										<circle cx="12" cy="12" r="10" stroke="currentColor" stroke-width="4"></circle>
										<path stroke="currentColor" fill="none" d="M4 12a8 8 0 0116 0"></path>
									</svg>
									<span class="text-gray-600">Updating...</span>
								</div>
							{:else}
								<SubmitButton text="Update" on:click={handleSubmit} />
							{/if}
						</div>
					</form>
				</div>

				<!-- Columna derecha - Estadísticas -->
				<div class="rounded-lg bg-white p-6 shadow">
					<h2 class="mb-4 text-center text-xl font-semibold">Estadísticas</h2>

					<table class="w-full text-sm text-gray-700">
						<tbody class="divide-y divide-gray-100">
							<tr>
								<td class="py-3 text-gray-500">Tickets resueltos</td>
								<td class="py-3 text-right font-medium">56</td>
							</tr>
							<tr>
								<td class="py-3 text-gray-500">Creado en</td>
								<td class="py-3 text-right font-medium">{formatDate(data.user.createdAt)}</td>
							</tr>
							<tr>
								<td class="py-3 text-gray-500">Tickets asignados</td>
								<td class="py-3 text-right font-medium">18</td>
							</tr>
							<tr>
								<td class="py-3 text-gray-500">Tickets abiertos</td>
								<td class="py-3 text-right font-medium">3</td>
							</tr>
							<tr>
								<td class="py-3 text-gray-500">Tiempo medio respuesta</td>
								<td class="py-3 text-right font-medium">6h 34min</td>
							</tr>
							<tr>
								<td class="py-3 text-gray-500">Últ. actividad</td>
								<td class="py-3 text-right font-medium">Respuesta en ticket #34</td>
							</tr>
							<tr>
								<td class="py-3 text-gray-500">Tickets máx/día</td>
								<td class="py-3 text-right font-medium">12 tickets</td>
							</tr>
							<tr>
								<td class="py-3 text-gray-500">Plan actual</td>
								<td class="py-3 text-right font-medium"
									>{data.user.planName || 'No especificado'}</td
								>
							</tr>
							<tr>
								<td class="py-3 text-gray-500">Último acceso a la plataforma</td>
								<td class="py-3 text-right font-medium">28/12/2024</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>

<style>
	.select-field {
		width: 100%;
		padding: 8px;
		border: none;
		border-radius: 8px;
		box-shadow: inset 2px 2px 10px 2px rgba(78, 78, 78, 0.25);
		background-color: rgba(255, 255, 255, 0.85);
		transition: box-shadow 0.3s ease;
		appearance: none;
		-webkit-appearance: none;
		-moz-appearance: none;
	}

	.select-field:focus {
		border-color: #00aa6f;
		box-shadow:
			inset 0 2px 4px rgba(0, 0, 0, 0.4),
			0 0 5px #00a750;
		outline: none;
	}
</style>
