<script lang="ts">
	import CustomPasswordInput from './CustomPasswordInput.svelte';
	import { createEventDispatcher } from 'svelte';
	import { onMount } from 'svelte';
	const dispatch = createEventDispatcher();
	const getToken = () => {
		return localStorage.getItem('token') || sessionStorage.getItem('authToken');
	};
	let currentPassword = '';
	let newPassword = '';
	let confirmNewPassword = '';
	let isLoading = false;
	let error = '';
	let success = '';

	async function handleSubmit() {
		error = '';
		success = '';

		if (newPassword !== confirmNewPassword) {
			error = 'Passwords must match.';
			return;
		}
		if (newPassword.length < 6) {
			error = 'Password must be at least 6 characters';
			return;
		}

		if (newPassword === currentPassword) {
			error = 'New password must be different from current password';
			return;
		}
		isLoading = true;

		const token = getToken();
		console.log(token);
		try {
			const res = await fetch('http://localhost:8080/api/auth/change-password', {
				method: 'POST',
				headers: {
					'Content-Type': 'application/json',
					Authorization: `Bearer ${token}`
				},
				body: JSON.stringify({
					currentPassword,
					newPassword
				})
			});

			if (!res.ok) {
				const errorData = await res.json();
				error = errorData.message || 'Failed to change password';
				return;
			}

			success = 'Password changed successfully';
			close(); // Cierra el modal después de éxito
		} catch (e) {
			error = 'Connection error';
		} finally {
			isLoading = false;
		}
	}

	function close() {
		dispatch('close');
	}
</script>

<div class="bg-opacity-50 fixed inset-0 z-50 flex items-center justify-center bg-[#04160f9a]">
	<div class="relative w-96 rounded-lg bg-white p-6">
		<button
			on:click={close}
			class="absolute top-2 right-4 cursor-pointer text-xl font-bold transition duration-250 ease-in-out hover:text-red-400"
			>&times;</button
		>
		<h3 class="mb-4 text-lg font-semibold">Change Password</h3>

		{#if error}
			<div class="mb-2 text-red-600">{error}</div>
		{/if}

		{#if success}
			<div class="mb-2 text-green-600">{success}</div>
		{/if}

		<form on:submit|preventDefault={handleSubmit} class="space-y-4">
			<div>
				<label class="mb-1 block text-sm font-medium">Current Password</label>
				<CustomPasswordInput
					name="password"
					id="password"
					tooltip="Write your password"
					bind:value={currentPassword}
					required
				/>
			</div>
			<div>
				<label class="mb-1 block text-sm font-medium">New Password</label>
				<CustomPasswordInput
					name="password"
					id="password"
					tooltip="Write your password"
					bind:value={newPassword}
					required
					minlength="6"
				/>
			</div>
			<div>
				<label class="mb-1 block text-sm font-medium">Confirm new password</label>
				<CustomPasswordInput
					name="password"
					id="password"
					tooltip="Write your password"
					bind:value={confirmNewPassword}
					required
					minlength="6"
				/>
			</div>

			<div class="flex justify-center">
				<button type="submit" class="btn-primary" disabled={isLoading}>
					{#if isLoading}
						Updating...
					{:else}
						Update
					{/if}
				</button>
			</div>
		</form>
	</div>
</div>

<style>
	.input-field {
		width: 100%;
		padding: 0.5rem;
		border-radius: 0.375rem;
		border: 1px solid #ccc;
	}

	.btn-primary {
		background-color: #00a750;
		color: white;
		padding: 0.5rem 1rem;
		border-radius: 0.375rem;
		font-weight: 600;
		cursor: pointer;
	}

	.btn-primary:disabled {
		opacity: 0.6;
		cursor: not-allowed;
	}
</style>
