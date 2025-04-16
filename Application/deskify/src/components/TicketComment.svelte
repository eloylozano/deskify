<script lang="ts">
	import { onMount } from 'svelte';
	import { createComment } from '$lib/api/tickets'; // Esto asume que tienes una función 'createComment' en tu archivo de API
	import SubmitButton from './SubmitButton.svelte';

	let email: string = '';
	let commentText: string = '';
	let ticketId: number = 1; // Asumimos que el ticketId es 1, ajusta según sea necesario
	let errorMessage: string = '';
	let successMessage: string = '';

	// Función para enviar el comentario
	const submitComment = async () => {
		if (!email || !commentText) {
			errorMessage = 'Complete all fields';
			return;
		}

		try {
			await createComment(ticketId, email, commentText);
			successMessage = 'Succeed!';
			email = '';
			commentText = ''; // Limpiar los campos
		} catch (error) {
			errorMessage = 'This mail does not exist';
		}
	};
</script>

<div class="rounded-lg bg-white my-6 p-6 shadow">
	<h3 class="mb-4 text-lg font-medium">Agent answer</h3>

	{#if errorMessage}
		<p class="text-red-500">{errorMessage}</p>
	{/if}

	{#if successMessage}
		<p class="text-green-500">{successMessage}</p>
	{/if}

	<!-- Formulario de comentario -->
	<div class="mt-4 gap-3 flex flex-col items-end">
		<input
			type="email"
			placeholder="Email"
			bind:value={email}
			class="mb-2 w-full rounded border border-gray-300 px-3 py-2"
		/>
		<textarea
			bind:value={commentText}
			placeholder="Write an answer..."
			class="w-full rounded border h-40 border-gray-300 px-3 py-2 resize-none"
		></textarea>
		<SubmitButton text="Submit answer" on:click={submitComment}>
		</SubmitButton>
	</div>
</div>

<style>
	/* Estilos adicionales para el formulario */
	input,
	textarea {
		outline: none;
	}
	button {
		cursor: pointer;
	}
</style>
