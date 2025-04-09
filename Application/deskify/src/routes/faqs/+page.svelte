<script>
	import Nav from '../../components/Nav.svelte';

	// Lista de preguntas frecuentes con imágenes (puedes reemplazar esto con los datos reales)
	let faqs = [
		{
			question: 'How do I register?',
			answer: "You can register by clicking on the 'Register' button.",
			image: '/path/to/register-image.png' // Ruta de la imagen relacionada
		},
		{
			question: "What is the platform's main feature?",
			answer: 'Our platform provides real-time communication.',
			image: '/path/to/feature-image.png'
		},
		{
			question: 'How do I change my password?',
			answer: "Go to your settings and click on 'Change Password'.",
			image: '/path/to/password-change-image.png'
		}
		// Agrega más preguntas e imágenes aquí...
	];

	let searchQuery = ''; // Almacena lo que el usuario escribe

	// Filtrar FAQs según el texto de búsqueda
	$: filteredFaqs = faqs.filter(
		(faq) =>
			faq.question.toLowerCase().includes(searchQuery.toLowerCase()) ||
			faq.answer.toLowerCase().includes(searchQuery.toLowerCase())
	);
</script>

<div class="flex h-[100vh] overflow-y-auto bg-green-50">
	<Nav />
	<div class="flex w-full flex-col items-center">
		<div class="flex flex-col items-center gap-6 py-10">
			<!-- Imagen FAQ -->
			<!-- svelte-ignore a11y_img_redundant_alt -->
			<img src="/FAQs00.png" alt="FAQ image" aria-label="FAQ Image" class="mx-auto h-110" />

			<!-- Barra de búsqueda con lupa -->
			<div class="relative w-[90vw] max-w-xl">
				<input
					bind:value={searchQuery}
					type="text"
					placeholder="Search FAQs..."
					class="w-full rounded-full border border-gray-300 px-5 py-3 shadow-sm transition-all focus:ring-2 focus:ring-cyan-400 focus:outline-none"
				/>
				<button
					class="absolute top-1/2 right-3 -translate-y-1/2 transform text-gray-500"
					on:click={() => {}}
				>
					<i class="fas fa-search"></i>
					<!-- Lupa usando Font Awesome -->
				</button>
			</div>
		</div>

		<!-- Contenido FAQ -->
		<div class="h-full w-full bg-white px-6 py-15 lg:px-30">
			<h1 class="pb-12 text-center text-4xl font-bold text-[#363636] lg:text-5xl">
				Frequently Asked Questions
			</h1>
			<p class="text-center font-medium text-[#858585] italic">
				In this section, you'll find answers to the most frequently asked questions about our
				platform, services, and how it works. From details on how to register to information about
				our plans and advanced features, our goal is to address any concerns you may have. If you
				can't find what you're looking for, don't hesitate to contact us directly; we'll be happy to
				assist you.<br />
				Explore the FAQs and clear up any questions in no time!
			</p>

			<!-- Mostrar las FAQs filtradas -->
            <div class="mt-12 space-y-6">
                {#if filteredFaqs.length > 0}
                    {#each filteredFaqs as faq}
                        <div class="rounded-lg bg-gray-100 p-6 shadow-md">
                            <h3 class="text-xl font-semibold text-[#363636]">{faq.question}</h3>
                            <p class="text-[#858585]">{faq.answer}</p>
        
                            <!-- Mostrar imagen si está disponible -->
                            {#if faq.image}
                                <img src={faq.image} alt="Related image" class="mx-auto mt-4 max-w-full h-auto" />
                            {/if}
                        </div>
                    {/each}
                {:else}
                    <p class="text-center text-[#858585]">No results found for your query.</p>
                {/if}
            </div>
		</div>
	</div>
</div>

<style>
	/* Asegúrate de que Font Awesome esté disponible para usar el ícono de lupa */
	@import url('https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css');
</style>
