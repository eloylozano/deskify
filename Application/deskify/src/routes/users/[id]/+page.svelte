<script lang="ts">
	import { error } from '@sveltejs/kit';
	import SubHeader from '../../../components/SubHeader.svelte';
	import Header from '../../../components/Header.svelte';
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

	async function handleSubmit() {
		try {
			await updateUser(user.id, user);
			goto('/users'); // o recargar: location.reload()
		} catch (err) {
			alert('Error al actualizar el usuario');
		}
	}
</script>

<div class="flex h-[100vh] overflow-hidden bg-green-100">
	<Nav></Nav>
	<div class="w-full">
		<Header showSearchIcon={false}></Header>
		<div class="container mx-auto px-4 py-8">
			<div class="grid grid-cols-1 gap-8 lg:grid-cols-2">
				<!-- Columna izquierda - Datos personales -->
				<div class="rounded-lg bg-white p-6 shadow">
					<div class="flex flex-col items-center gap-4">
						{#if user.profilePictureUrl}
							<img
								src={user.profilePictureUrl}
								alt="Foto de perfil"
								class="h-24 w-24 rounded-full"
							/>
						{:else}
							<div
								class="flex h-24 w-24 items-center justify-center rounded-full bg-gray-300 text-4xl font-bold text-white"
							>
								{user.firstName?.charAt(0)}{user.middleName?.charAt(0)}
							</div>
						{/if}

						<h1 class="mb-6 text-3xl font-bold">Editar datos de {user.firstName}</h1>
					</div>

					<form class="space-y-4 px-12" on:submit|preventDefault={handleSubmit}>
						<div>
							<label class="text-sm text-gray-500">Nombre</label>
							<input class="w-full rounded border px-4 py-2" bind:value={user.firstName} />
						</div>

						<div class="flex gap-4">
							<div class="w-1/2">
								<label class="text-sm text-gray-500">Apellido 1</label>
								<input class="w-full rounded border px-4 py-2" bind:value={user.middleName} />
							</div>

							<div class="w-1/2">
								<label class="text-sm text-gray-500">Apellido 2</label>
								<input class="w-full rounded border px-4 py-2" bind:value={user.lastName} />
							</div>
						</div>

						<div class="flex gap-4">
							<div class="w-1/2">
								<label class="text-sm text-gray-500">Correo electrónico</label>
								<input
									class="w-full rounded border px-4 py-2"
									bind:value={user.email}
									type="email"
								/>
							</div>

							<div class="w-1/2">
								<label class="text-sm text-gray-500">Teléfono</label>
								<input class="w-full rounded border px-4 py-2" bind:value={user.phoneNumber} />
							</div>
						</div>

						<div class="flex gap-4">
							<div class="w-1/2">
								<label class="text-sm text-gray-500">Rol</label>
								<select class="w-full rounded border px-4 py-2" bind:value={user.roleName}>
									{#each roles as role}
										<option value={role}>{role}</option>
									{/each}
								</select>
							</div>

							<div class="w-1/2">
								<label class="text-sm text-gray-500">Empresa</label>
								<input class="w-full rounded border px-4 py-2" bind:value={user.company} />
							</div>
						</div>

						<div class="mt-12 flex justify-center">
							<SubmitButton text="Update"></SubmitButton>
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
