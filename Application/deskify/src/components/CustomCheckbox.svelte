<script lang="ts">
	import { createEventDispatcher } from 'svelte';
	export let checked = false;
	export let onChange: (checked: boolean) => void;

	const dispatch = createEventDispatcher();

	function handleChange(event: Event) {
		const value = (event.target as HTMLInputElement).checked;
		if (onChange) onChange(value);
		dispatch('change', value);
	}
</script>

<input
	type="checkbox"
	class="custom-checkbox accent-primary h-4 w-4 rounded focus:ring-2 focus:ring-offset-2"
	bind:checked
	on:change={handleChange}
/>

<style>
	.custom-checkbox {
		appearance: none;
		width: 18px;
		height: 18px;
		border: 2px solid #ccc;
		border-radius: 3px;
		outline: none;
		transition: all 0.2s;
		position: relative;
	}

	.custom-checkbox:focus {
		outline: none;
		box-shadow: none;
	}

	.custom-checkbox:checked {
		background-color: #01c883;
		border-color: #01c883;
	}

	.custom-checkbox:checked::after {
		content: '✓';
		position: absolute;
		color: white;
		font-size: 12px;
		top: 50%;
		left: 50%;
		transform: translate(-50%, -50%);
	}
</style>
