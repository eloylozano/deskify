<script>
	/**
	 * @type {any[]}
	 */
	export let faqs = [];

	let searchQuery = '';

	$: filteredFaqs = faqs.filter(
		(faq) =>
			faq.question.toLowerCase().includes(searchQuery.toLowerCase()) ||
			faq.answer.toLowerCase().includes(searchQuery.toLowerCase())
	);
</script>

<div class="flex flex-col items-center gap-6 pt-10 px-3">
	<!-- svelte-ignore a11y_img_redundant_alt -->
	<img src="/FAQs00.png" alt="FAQ image" aria-label="FAQ Image" class="mx-auto h-110" />

	<div class="relative w-[90vw] max-w-xl mb-12">
		<input
			bind:value={searchQuery}
			type="text"
			placeholder="Search FAQs..."
			class="search w-full rounded-full border border-gray-300 px-5 py-3 shadow-sm transition-all focus:ring-2 focus:ring-cyan-400 focus:outline-none"
		/>
		<!-- svelte-ignore a11y_consider_explicit_label -->
		<button
			class="absolute top-1/2 right-3 -translate-y-1/2 transform text-gray-500"
			on:click={() => {}}
		>
			<i class="fas fa-search"></i>
		</button>
	</div>

	<!-- Título e introducción -->
	<div class="h-full w-full bg-white px-6 py-15 shadow-2xl lg:px-30">
		<h1 class="pb-12 text-center text-4xl font-bold text-[#363636] lg:text-5xl">
			Frequently Asked Questions
		</h1>
		<p class="text-center font-medium text-[#858585] italic">
			In this section, you'll find answers to the most frequently asked questions about our
			platform, services, and how it works. From details on how to register to information about our
			plans and advanced features, our goal is to address any concerns you may have.
			<br /> If you can't find what you're looking for, don't hesitate to contact us directly; we'll
			be happy to assist you.<br /><br />
			Explore the FAQs and clear up any questions in no time!
		</p>

		<!-- Lista de FAQs -->
		<div class="mt-12 space-y-6">
			{#if filteredFaqs.length > 0}
				{#each filteredFaqs as faq}
					<div class="rounded-lg bg-gray-100 p-6 shadow-md">
						<h3 class="text-xl font-semibold text-[#363636] pb-8">{faq.question}</h3>
						<p class="text-[#858585]">{faq.answer}</p>
						{#if faq.image}
							<!-- svelte-ignore a11y_img_redundant_alt -->
							<img src={faq.image} alt="Related image" class="mx-auto mt-4 h-100 " />
						{/if}
					</div>
				{/each}
			{:else}
				<p class="text-center text-[#858585]">No results found for your query.</p>
			{/if}
		</div>
	</div>
</div>

<style>
	@import url('https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css');

	.search:focus {
		border-color: #00aa6f;
		box-shadow:
			inset 0 2px 4px rgba(0, 0, 0, 0.4),
			0 0 5px #00a750;
		outline: none;
	}
</style>
