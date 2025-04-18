<script>
	import GradientTitle from '../../components/GradientTitle.svelte';
	import Line from '../../components/Line.svelte';
	import Nav from '../../components/Nav.svelte';
	import Title from '../../components/Title.svelte';
	import Button from '../../components/Button.svelte';
	import CustomInput from '../../components/CustomInput.svelte';
</script>

<div class="flex">
	<Nav />
	<div id="login-container" class="flex w-full flex-col items-center pt-10">
		<GradientTitle text="Deskify" />
		<Title title="Welcome Back" />
		<Line />
		<form
			class="form-container mt-5 flex w-95 flex-col items-center gap-5 rounded-3xl bg-white p-6"
			action="post"
		>
			<GradientTitle text="Log in" />
			<div class="flex w-full flex-col justify-between gap-8">
				<CustomInput type="text" name="email" id="email" placeholder="Email"></CustomInput>
				<CustomInput type="password" name="password" id="password" placeholder="Password"
				></CustomInput>
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

	.bg-white {
		background-color: rgba(255, 255, 255, 0.85);
	}
</style>
