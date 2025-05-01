<script lang="ts">
	import { goto } from '$app/navigation';
	import { createTicket } from '$lib/api/tickets';
	import Nav from '../../../components/Nav.svelte';
	import Header from '../../../components/Header.svelte';
	import SubmitButton from '../../../components/SubmitButton.svelte';
	import CustomInput from '../../../components/CustomInput.svelte';
	import CustomTextarea from '../../../components/CustomTextarea.svelte';

	let email = '';
	let title = '';
	let description = '';
	let isLoading = false;
	let errorMessage = '';

	async function handleCreateTicket(event: Event) {
		event.preventDefault();
		isLoading = true;
		errorMessage = '';

		try {
			const response = await createTicket({ email, title, description });
			console.log('Ticket created:', response); // Para depuración
			await goto('/tickets');
		} catch (err) {
			console.error('Error:', err);
			errorMessage = err.message || 'El correo electrónico no existe o es incorrecto.';
		} finally {
			isLoading = false;
		}
	}
</script>

<div class="flex h-screen overflow-hidden bg-emerald-100">
	<Nav />

	<div class="flex flex-1 flex-col items-center overflow-y-auto">
		<Header text="Create new ticket" />

		<div class="my-5 w-300 rounded-md bg-white px-10 py-6 shadow">
			<h1 class="mb-4 text-center text-3xl font-medium text-gray-800">Create a new incidence</h1>

			{#if errorMessage}
				<div class="mb-4 rounded bg-red-100 p-4 text-red-700">
					{errorMessage}
				</div>
			{/if}

			<form on:submit|preventDefault={handleCreateTicket} class="space-y-4">
				<div>
					<label for="email" class="block pb-2 text-sm font-medium text-gray-700">Email</label>
					<CustomInput
						type="email"
						id="email"
						name="email"
						placeholder="Your Email"
						bind:value={email}
						required
					/>
				</div>

				<div>
					<label for="title" class="block pb-2 text-sm font-medium text-gray-700">Title</label>
					<CustomInput
						type="text"
						id="title"
						name="title"
						placeholder="Write your problem"
						bind:value={title}
						required
					/>
				</div>

				<div>
					<label for="description" class="block pb-2 text-sm font-medium text-gray-700"
						>Description</label
					>
					<CustomTextarea
						id="description"
						name="description"
						placeholder="Description of the problem"
						bind:value={description}
					/>
				</div>

				<div class="flex justify-end">
					<SubmitButton text={isLoading ? 'Creating...' : 'Create ticket'} disabled={isLoading} />
				</div>
			</form>
		</div>
	</div>
</div>

<style>
	.shadow {
		box-shadow: 0 15px 20px rgba(0, 0, 0, 0.1);
	}
</style>
