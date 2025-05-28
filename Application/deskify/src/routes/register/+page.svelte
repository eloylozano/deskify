<script lang="ts">
	import GradientTitle from './../../components/GradientTitle.svelte';
	import { registerUser } from '$lib/api/register';
	import Title from '../../components/Title.svelte';
	import SubmitButton from '../../components/SubmitButton.svelte';
	import CustomInput from '../../components/CustomInput.svelte';
	import Nav from '../../components/Nav.svelte';
	import Line from '../../components/Line.svelte';
	import CustomPasswordInput from '../../components/CustomPasswordInput.svelte';


	let email = '';
	let password = '';
	let confirmPassword = '';
	let loading = false;
	let register = false;
	let error = '';
	let success = '';

	async function handleSubmit(event: Event) {
		event.preventDefault();
		error = '';
		success = '';

		if (password !== confirmPassword) {
			error = 'The passwords do not match';
			return;
		}

		if (password.length < 6) {
			error = 'The password must be at least 6 characters long.';
			return;
		}

		loading = true;

		try {
			const result = await registerUser({ email, password });

			if (result.error) {
				error = result.error;
				return;
			}

			success = result.message || 'Registration successful! Redirecting...';
			setTimeout(() => {
				window.location.href = '/login';
			}, 1000);
		} catch (err) {
			error = 'Unexpected error. Please try again.';
			console.error(err);
		} finally {
			loading = false;
		}
	}
</script>

<div class="flex">
	<Nav {register} />
	<div id="login-container" class="flex w-full flex-col items-center pt-10">
		<GradientTitle text="Deskify" />
		<Title title="Welcome to Deskify!" additionalClass="w-1/2 text-center" />
		<Line />
		<form
			class="form-container mt-5 flex w-95 flex-col gap-5 rounded-3xl bg-white p-6"
			on:submit={handleSubmit}
		>
			<GradientTitle text="Create new account" additionalClass="text-center" />

			{#if error}
				<div class="rounded-md bg-red-100 p-3 text-red-700">
					{error}
				</div>
			{/if}

			{#if success}
				<div class="rounded-md bg-green-100 p-3 text-green-700">
					{success}
				</div>
			{/if}

			<div class="flex w-full flex-col justify-between gap-8">
				<CustomInput
					type="email"
					name="email"
					id="email"
					placeholder="Email"
					bind:value={email}
					required
				/>
				<CustomPasswordInput
					name="password"
					id="password"
					placeholder="Enter your password"
					bind:value={password}
					required
				/>
				<CustomPasswordInput
					name="password"
					id="password"
					placeholder="Enter your password"
					bind:value={confirmPassword}
					required
				/>
			</div>
			<p class="text-left text-sm text-gray-600">
				Have an account? <a class="text-emerald-600 underline" href="/login">Log in</a>.
			</p>

			<SubmitButton
				text={loading ? 'Processing...' : 'Submit'}
				additionalClass="mx-auto"
				disabled={loading}
			/>
		</form>
	</div>
</div>

<style>
	#login-container {
		background-image: url('/backgrounds/Login.jpg');
		background-size: cover;
		background-repeat: no-repeat;
		background-position: center;
		min-height: 100vh;
	}
</style>
