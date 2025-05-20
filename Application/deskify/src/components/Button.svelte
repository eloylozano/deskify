<script>
	import { createEventDispatcher } from "svelte";

	export let text = '';
	export let href = '';
	export let additionalClass = '';
	const dispatch = createEventDispatcher();

	const handleClick = () => {
		dispatch('click'); // Solo emitir el evento si realmente se necesita
	};
</script>

<a
	on:click={handleClick}
	class={`btn flex w-fit cursor-pointer items-center rounded-full px-12 py-2 text-white ${additionalClass}`}
	{href}
>
	{text}
</a>

<style>
	.btn {
		background: linear-gradient(45deg, #00ff7a, #00aa6f 100%);
		transition:
			background 0.3s ease-in,
			transform 0.3s ease,
			box-shadow 0.3s ease;
	}

	.btn:hover {
		background: linear-gradient(45deg, #00aa6f, #02353c 100%);
		transform: scale(1.05);
		box-shadow: 0px 4px 20px rgba(0, 0, 0, 0.2);
	}
</style>
