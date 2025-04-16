<!-- src/routes/new-ticket/+page.svelte -->
<script lang="ts">
	import { goto } from '$app/navigation';
	import { createTicket } from '$lib/api/tickets';
	import Nav from '../../../components/Nav.svelte';
	import Header from '../../../components/Header.svelte';
	import SubmitButton from '../../../components/SubmitButton.svelte';

	let email = '';
	let title = '';
	let description = '';

	async function handleCreateTicket(event: Event) {
		event.preventDefault();

		try {
			await createTicket({ email, title, description });
			goto('/tickets');
		} catch (err) {
			console.error(err);
			alert('El correo electrónico no existe o es incorrecto.');
		}
	}
</script>

<div class="flex h-screen bg-green-50 overflow-hidden">
	<Nav />

	<div class="flex flex-1 flex-col items-center">
		<Header />

		<div class="my-5 w-300 rounded-md bg-white px-10 py-6 shadow">
			<h1 class="mb-4 text-center text-3xl font-medium text-gray-800">Create a new incidence</h1>

			<form on:submit={handleCreateTicket} class="space-y-4">
				<div>
					<!-- svelte-ignore a11y_label_has_associated_control -->
					<label class="block text-sm font-medium text-gray-700">Email</label>
					<input
						type="email"
						bind:value={email}
						required
						placeholder="Your Email"
						class="mt-1 block w-full rounded border border-gray-300 px-3 py-2 shadow-sm"
					/>
				</div>

				<div>
					<!-- svelte-ignore a11y_label_has_associated_control -->
					<label class="block text-sm font-medium text-gray-700">Title</label>
					<input
						type="text"
						bind:value={title}
						required
						placeholder="Write your problem"
						class="mt-1 block w-full rounded border border-gray-300 px-3 py-2 shadow-sm"
					/>
				</div>

				<div>
					<!-- svelte-ignore a11y_label_has_associated_control -->
					<label class="block text-sm font-medium text-gray-700">Description</label>
					<textarea
						bind:value={description}
						required
						placeholder="Description of the problem"
						class="mt-1 block w-full h-60 resize-none rounded border border-gray-300 px-3 py-2 shadow-sm"
					></textarea>
				</div>

				<div class="flex justify-end">
					<SubmitButton text="Create ticket" />
				</div>
			</form>
		</div>
	</div>
</div>

<style>
	.shadow {
		box-shadow: 0 15px 20px rgba(0, 0, 0, 0.1); /* Sombra exterior sutil */
	}

	input:focus,
	textarea:focus {
		border-color: #00aa6f !important;
		outline: none !important;
	}
</style>
