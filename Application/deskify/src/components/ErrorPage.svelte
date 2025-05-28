<script>
	import Button from "./Button.svelte";

	export let image;
	export let title;
	export let description;
</script>

<div class="w-screen h-screen overflow-hidden flex flex-col lg:flex-row items-center justify-center px-4 lg:px-16 py-10 gap-10 box-border">
	<!-- svelte-ignore a11y_img_redundant_alt -->
	<img src={image} alt="Error image" class="max-w-full w-auto h-auto lg:max-w-[45%]" />
	<div class="flex flex-col justify-center items-center lg:items-end gap-10 text-center lg:text-right max-w-[90vw] lg:max-w-[45%]">
		<h1 class="text-3xl lg:text-6xl text-[#3a3a3a]">{title}</h1>
		<p class="text-[#858585] text-lg lg:text-xl italic">{description}</p>
		<Button additionalClass="text-lg lg:text-2xl py-3" text="Back home" href="/" />
	</div>
</div>

