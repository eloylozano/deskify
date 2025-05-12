<script lang="ts">
	import GradientTitle from '../../components/GradientTitle.svelte';
	import Line from '../../components/Line.svelte';
	import Nav from '../../components/Nav.svelte';
	import Title from '../../components/Title.svelte';
	import Button from '../../components/Button.svelte';
	import CustomInput from '../../components/CustomInput.svelte';
	import { login, isAuthenticated } from '$lib/api/auth';
	import SubmitButton from '../../components/SubmitButton.svelte';
	import CustomPasswordInput from '../../components/CustomPasswordInput.svelte';
	import { goto } from '$app/navigation';
	import { page } from '$app/stores';

	let register = false;
	let email = '';
	let password = '';
	let loading = false;
	let error = '';

	if (isAuthenticated()) {
		goto($page.url.searchParams.get('returnTo') || '/');
	}

	async function handleSubmit(event: Event) {
		event.preventDefault();
		error = '';
		loading = true;

		try {
			const result = await login({ email, password });

			if (result.error) {
				error = result.error;
				return;
			}

			const returnTo = $page.url.searchParams.get('returnTo') || '/dashboard';
			goto(returnTo);
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
		<Title title="Welcome Back" />
		<Line />
		<form
			class="form-container mt-5 flex w-95 flex-col gap-5 rounded-3xl bg-white p-6"
			on:submit={handleSubmit}
		>
			<GradientTitle text="Log in" additionalClass="text-center" />

			{#if error}
				<div class="rounded-md bg-red-100 p-3 text-red-700">
					{error}
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
			</div>
			<p class="text-left text-sm text-gray-600">
				Don't have an account? Create new one <a class="text-emerald-600 underline" href="/register"
					>here</a
				>.
			</p>
			<SubmitButton
				text={loading ? 'Signing in...' : 'Sign In'}
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

	.form-container {
		box-shadow: 0 15px 20px rgba(0, 0, 0, 0.1);
	}

	.bg-white {
		background-color: rgba(255, 255, 255, 0.85);
	}
</style>
