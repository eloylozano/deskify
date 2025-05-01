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
	import ProfilePictureUpload from '../../../components/ProfilePictureUpload.svelte';

	export let data;
	const apiUrl = import.meta.env.VITE_API_URL;

	interface User {
		id: number;
		firstName: string;
		middleName: string;
		lastName: string;
		email: string;
		phoneNumber: string;
		activeSubscription?: string;
		profilePictureUrl?: string;
		roleName: string;
		company?: string;
		createdAt: string;
		updatedAt: string;
		planName?: string;
	}

	let user: User = {} as User;
	let roles = ['Admin', 'Supervisor', 'Manager', 'Agent', 'User'];
	let isLoading = false;
	let imageError = false;
	let loadingStats = true;

	let ticketsAsignados: number;
	let ticketsAbiertos: number;
	let ticketsResueltos: number;

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

	onMount(async () => {
		user = { ...$page.data.user };
		ticketsAsignados = $page.data.stats.ticketsAsignados;
		ticketsAbiertos = $page.data.stats.ticketsAbiertos;
		ticketsResueltos = $page.data.stats.ticketsResueltos;
	});

	function getInitials(user: User): string {
		const firstName = user.firstName || '';
		const middleName = user.middleName || '';
		const lastName = user.lastName || '';

		const initials = `${firstName[0] || ''}${middleName[0] || ''}${lastName[0] || ''}`;

		return initials.toUpperCase();
	}

	function formatDate(dateString: string) {
		return new Date(dateString).toLocaleDateString('es-ES');
	}
</script>

<div class="flex h-[100vh] overflow-hidden bg-green-100">
	<Nav></Nav>
	<div class="w-full">
		<Header search={false} text={user.firstName}></Header>
		<div class="container mx-auto px-4 py-8">
			<div class="grid grid-cols-1 gap-8 lg:grid-cols-2">
				<!-- Columna izquierda - Datos personales -->
				<div class="rounded-lg bg-gray-50 p-6 shadow">
					<div class="flex flex-col items-center gap-4">
						<ProfilePictureUpload {user} size={24} on:upload={(e) => handleUpload(e.detail)} />
						
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

				<div class="rounded-lg bg-white p-6 shadow">
					<h2 class="mb-4 text-center text-xl font-semibold">User Stats</h2>
					<table class="w-full text-sm text-gray-700">
						<tbody class="divide-y divide-gray-100">
							<tr>
								<td class="py-3 text-gray-500">Plan</td>
								<td class="py-3 text-right font-medium">{user.activeSubscription?.plan.name}</td>
							</tr>
							<tr>
								<td class="py-3 text-gray-500">Created at</td>
								<td class="py-3 text-right font-medium">{formatDate(user.createdAt)}</td>
							</tr>
							<tr>
								<td class="py-3 text-gray-500">Updated at</td>
								<td class="py-3 text-right font-medium">{formatDate(user.updatedAt)}</td>
							</tr>
							<tr>
								<td class="py-3 text-gray-500">Assigned Tickets</td>
								<td class="py-3 text-right font-medium">{ticketsAsignados}</td>
							</tr>
							<tr>
								<td class="py-3 text-gray-500">Open Tickets</td>
								<td class="py-3 text-right font-medium">{ticketsAbiertos}</td>
							</tr>
							<tr>
								<td class="py-3 text-gray-500">Solved Tickets</td>
								<td class="py-3 text-right font-medium">{ticketsResueltos}</td>
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
