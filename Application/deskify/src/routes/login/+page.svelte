<script >
	import GradientTitle from '../../components/GradientTitle.svelte';
	import Line from '../../components/Line.svelte';
	import Nav from '../../components/Nav.svelte';
	import Title from '../../components/Title.svelte';
	import Button from '../../components/Button.svelte';
</script>

<div class="flex">
	<Nav />
	<div id="login-container" class="flex w-full flex-col items-center pt-10">
		<GradientTitle text="Deskify" />
		<Title title="Welcome Back" />
		<Line />
		<form
			class="form-container  mt-5 flex w-95 flex-col gap-5 items-center rounded-3xl bg-white p-6"
			action="post"
		>
			<GradientTitle text="Log in" />
			<div class="flex w-full flex-col gap-8 justify-between">
				<input type="text" name="email" id="email" placeholder="Email" class="input-field w-full" />
				<input
					type="password"
					name="password"
					id="password"
					placeholder="Password"
					class="input-field w-full"
				/>
			</div>
			<Button text="Submit" />
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
		box-shadow: 0 15px 20px rgba(0, 0, 0, 0.1); /* Sombra exterior sutil */
	}

	.input-field {
		padding: 8px;
		border: none;
		border-radius: 8px;
		box-shadow: inset 2px 2px 10px 2px rgba(78, 78, 78, 0.1); /* Sombra interior */
		transition: box-shadow 0.3s ease;
	}

	.input-field:focus {
		border-color: #00aa6f;
		box-shadow:
			inset 0 2px 4px rgba(0, 0, 0, 0.1),
			0 0 5px #00ff7a80; /* Añade sombra exterior cuando el input está en foco */
		outline: none;
	}

	.bg-white {
		background-color: rgba(255, 255, 255, 0.85);
	}
</style>
