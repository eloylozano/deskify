<script>
	export let name = '';
	export let id = '';
	export let placeholder = '';
	export let value = '';
	export let tooltip = ''; 
</script>

<!-- svelte-ignore element_invalid_self_closing_tag -->
<textarea
	bind:value
	name={name}
	id={id}
	placeholder={placeholder}
	title={tooltip}
	class="textarea-field w-full h-60 px-3 resize-none"
	required
	{...$$restProps}
/>

<style>
	.textarea-field {
		border: none;
		border-radius: 8px;
		box-shadow: inset 2px 2px 10px 2px rgba(78, 78, 78, 0.15);
		transition: box-shadow 0.3s ease;
	}

	.textarea-field:focus {
		border-color: #00aa6f;
		box-shadow:
			inset 0 2px 4px rgba(0, 0, 0, 0.1),
			0 0 5px #00a750;
		outline: none;
	}
</style>
