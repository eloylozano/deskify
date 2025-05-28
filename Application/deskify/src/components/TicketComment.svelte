<script lang="ts">
	import { onMount } from 'svelte';
	import { createComment } from '$lib/api/tickets'; 
	import SubmitButton from './SubmitButton.svelte';
	import { createEventDispatcher } from 'svelte';

	let email: string = '';
	let commentText: string = '';
	export let ticketId: number;
	let errorMessage: string = '';
	let successMessage: string = '';
	const dispatch = createEventDispatcher();

	const submitComment = async () => {
		if (!email || !commentText) {
			errorMessage = 'Complete all fields';
			return;
		}

		try {
			await createComment(ticketId, email, commentText);
			successMessage = 'Succeed!';
			email = '';
			commentText = ''; 
		} catch (error) {
			errorMessage = 'This mail does not exist';
		}
		dispatch('submitted');
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
	input,
	textarea {
		outline: none;
	}

	input {
		border: none;
		border-radius: 8px;
		box-shadow: inset 2px 2px 10px 2px rgba(78, 78, 78, 0.15);
		transition: box-shadow 0.3s ease;
	}

	input:focus {
		border-color: #00aa6f;
		box-shadow:
			inset 0 2px 4px rgba(0, 0, 0, 0.2),
			0 0 5px #00a750;
		outline: none;
	}

	textarea {
		border: none;
		border-radius: 8px;
		box-shadow: inset 2px 2px 10px 2px rgba(78, 78, 78, 0.15);
		transition: box-shadow 0.3s ease;
	}

	textarea:focus {
		border-color: #00aa6f;
		box-shadow:
			inset 0 2px 4px rgba(0, 0, 0, 0.1),
			0 0 5px #00a750;
		outline: none;
	}


</style>
