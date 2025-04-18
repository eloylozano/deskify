<!-- CustomInput.svelte -->
<script>
	export let type = '';
	export let name = '';
	export let id = '';
	export let placeholder = '';
	export let value = ''; // importante para bind:value
	
</script>

<input
    type={type}
    name={name}
    id={id}
    placeholder={placeholder}
    class="input-field w-full px-3 h-10"
    bind:value
    {...$$restProps}
/>

<style>
	.input-field {
		border: none;
		border-radius: 8px;
		box-shadow: inset 2px 2px 10px 2px rgba(78, 78, 78, 0.25);
		transition: box-shadow 0.3s ease;
	}

	.input-field:focus {
		border-color: #00aa6f;
		box-shadow:
			inset 0 2px 4px rgba(0, 0, 0, 0.4),
			0 0 5px #00a750;
		outline: none;
	}
</style>
