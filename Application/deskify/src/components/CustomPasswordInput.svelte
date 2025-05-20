<!-- CustomPasswordInput.svelte -->
<script lang="ts">
	export let name = '';
	export let id = '';
	export let placeholder = '';
	export let value = '';
	export let required = true;
	export let tooltip = '';
	let showPassword = false;

	function togglePasswordVisibility() {
		showPassword = !showPassword; 
	}
</script>

<div class="password-input-container relative w-full">
	<input
		type={showPassword ? 'text' : 'password'}
		{name}
		title={tooltip}
		{id}
		{placeholder}
		{required}
		bind:value
		class="input-field h-10 w-full px-3 pr-10"
		{...$$restProps}
	/>

	<button
		type="button"
		on:click={togglePasswordVisibility}
		class="absolute top-1/2 right-3 -translate-y-1/2 transform focus:outline-none"
		tabindex="-1"
	>
		{#if showPassword}
			<!-- Icono de ojo tachado (visible) -->
			<svg
				xmlns="http://www.w3.org/2000/svg"
				class="mr-3 h-5 w-5 text-gray-500"
				fill="none"
				viewBox="0 0 24 24"
				stroke="currentColor"
			>
				<path
					stroke-linecap="round"
					stroke-linejoin="round"
					stroke-width="2"
					d="M15 12a3 3 0 11-6 0 3 3 0 016 0z"
				/>
				<path
					stroke-linecap="round"
					stroke-linejoin="round"
					stroke-width="2"
					d="M2.458 12C3.732 7.943 7.523 5 12 5c4.478 0 8.268 2.943 9.542 7-1.274 4.057-5.064 7-9.542 7-4.477 0-8.268-2.943-9.542-7z"
				/>
			</svg>
		{:else}
			<!-- Icono de ojo cerrado (no visible) -->
			<svg
				xmlns="http://www.w3.org/2000/svg"
				class="mr-3 h-5 w-5 text-gray-500"
				fill="none"
				viewBox="0 0 24 24"
				stroke="currentColor"
			>
				<path
					stroke-linecap="round"
					stroke-linejoin="round"
					stroke-width="2"
					d="M13.875 18.825A10.05 10.05 0 0112 19c-4.478 0-8.268-2.943-9.543-7a9.97 9.97 0 011.563-3.029m5.858.908a3 3 0 114.243 4.243M9.878 9.878l4.242 4.242M9.88 9.88l-3.29-3.29m7.532 7.532l3.29 3.29M3 3l3.59 3.59m0 0A9.953 9.953 0 0112 5c4.478 0 8.268 2.943 9.543 7a10.025 10.025 0 01-4.132 5.411m0 0L21 21"
				/>
			</svg>
		{/if}
	</button>
</div>

<style>
	.password-input-container {
		display: flex;
		align-items: center;
	}

	.input-field {
		border: none;
		border-radius: 8px;
		box-shadow: inset 2px 2px 10px 2px rgba(78, 78, 78, 0.15);
		transition: box-shadow 0.3s ease;
	}

	.input-field:focus {
		border-color: #00aa6f;
		box-shadow:
			inset 0 2px 4px rgba(0, 0, 0, 0.2),
			0 0 5px #00a750;
		outline: none;
	}

	button {
		background: none;
		border: none;
		cursor: pointer;
	}
</style>
