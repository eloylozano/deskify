<script>
	export let title = '';
	export let desc = '';
	export let additionalClass = '';
</script>

<h1 class={`text-5xl leading-16 font-light text-[#3a3a3aº] ${additionalClass}`}>
	{title}
</h1>
<p class="mt-8 text-center text-lg leading-6">{desc}</p>
