<script>
	export let width = 'default'; 
</script>

<hr
	class={`mb-7 h-1 rounded-full ${width === 'full' ? 'w-full' : width === 'half' ? 'w-1/2' : 'w-40'}`}
/>

<style>
	hr {
		background: linear-gradient(45deg, #00ff7a, #00aa6f 100%);
		border: none;
	}
</style>
