<script>
	import Button from '../components/Button.svelte';
	import Title from '../components/Title.svelte';

	export let error;
</script>

{#if error?.status === 400}
	<div class="w-screen h-screen overflow-hidden flex flex-col lg:flex-row items-center justify-center px-4 lg:px-16 py-10 gap-10 box-border">
		<img src="/Error400.svg" alt="Error400" class="max-w-full w-auto h-auto lg:max-w-[45%]" />
		<div class="flex flex-col justify-center items-center lg:items-end gap-10 text-center lg:text-right max-w-[90vw] lg:max-w-[45%]">
			<h1 class="text-3xl lg:text-6xl text-[#3a3a3a]">Something went wrong with your request.</h1>
			<p class="text-[#858585] text-lg lg:text-xl italic">
				It appears the information you submitted is invalid or incomplete. Please verify the details and try again. If you need assistance, please don't hesitate to contact us.
			</p>
			<Button additionalClass="text-lg lg:text-2xl py-3" text="Back home" href="/" />
		</div>
	</div>
{:else}
	<div class="w-screen h-screen overflow-hidden flex flex-col lg:flex-row items-center justify-center px-4 lg:px-16 py-10 gap-10 box-border">
		<img src="/Error404.svg" alt="Error404" class="max-w-full w-auto h-auto lg:max-w-[45%]" />
		<div class="flex flex-col justify-center items-center lg:items-end gap-10 text-center lg:text-right max-w-[90vw] lg:max-w-[45%]">
			<h1 class="text-3xl lg:text-6xl text-[#3a3a3a]">Oops! We couldn't find what you were looking for.</h1>
			<p class="text-[#858585] text-lg lg:text-xl italic">
				This page doesn't exist, but don't worry, the others are waiting for you. Keep exploring and find what you're looking for!
			</p>
			<Button additionalClass="text-lg lg:text-2xl py-3" text="Back home" href="/" />
		</div>
	</div>
{/if}
