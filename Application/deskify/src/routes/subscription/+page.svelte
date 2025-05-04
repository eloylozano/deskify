<script lang="ts">
	import GradientTitle from '../../components/GradientTitle.svelte';
	import Header from '../../components/Header.svelte';
	import Nav from '../../components/Nav.svelte';
	import Title from '../../components/Title.svelte';

	type Plan = {
		id: number;
		name: string;
		description: string;
		price: number | null;
		duration: string;
		features: string[];
		selectedDuration: number;
	};

	export let plans: Plan[] = [
		{
			id: 1,
			name: 'Basic',
			description: 'Basic plan with limited features, suitable for casual users.',
			price: 9.95,
			duration: '30 days',
			features: [
				'Up to 3 open tickets simultaneously.',
				'Standard support response < 48h.',
				'No access to advanced customization.',
				'Individual users only (no team).'
			],
			selectedDuration: 1
		},
		{
			id: 2,
			name: 'Standard',
			description: 'Intermediate plan with more features and access to priority support.',
			price: 14.99,
			duration: '30 days',
			features: [
				'Up to 10 open tickets simultaneously.',
				'Priority support response < 24h.',
				'Extended ticket history (up to 6 months).',
				'Allows 2 additional users.',
				'Basic charts and trends for tickets.'
			],
			selectedDuration: 1
		},
		{
			id: 3,
			name: 'Premium',
			description: 'Premium plan with full access to all features and priority support.',
			price: 19.95,
			duration: '30 days',
			features: [
				'Unlimited tickets.',
				'Ultra-priority support.',
				'Access to templates for common ticket resolutions.',
				'Advanced integrations (e.g., Slack, Trello).',
				'Multiple users with assigned roles.',
				'Comprehensive analytics with key metrics.',
				'Unlimited access to ticket history.'
			],
			selectedDuration: 1
		}
	];

	let selectedPlan: Plan | null = null;

	// Función para seleccionar un plan
	function selectPlan(plan: Plan) {
		selectedPlan = plan;
	}

	// Función para deseleccionar un plan
	function deselectPlan() {
		selectedPlan = null;
	}

	function selectDuration(plan: Plan, duration: number) {
		plans = plans.map((p) => {
			if (p.id === plan.id) {
				return { ...p, selectedDuration: duration };
			}
			return p;
		});
	}

	function getUpdatedPrice(plan: Plan) {
		if (!plan.price) return 0;
		if (plan.selectedDuration === 1) return plan.price;
		if (plan.selectedDuration === 3) return plan.price * 2.5;
		if (plan.selectedDuration === 12) return plan.price * 11;
		return plan.price;
	}

	// Función para manejar el pago
	async function handlePayment() {
		if (!selectedPlan) return;

		// Aquí iría la lógica para procesar el pago
		console.log('Processing payment for:', {
			plan: selectedPlan.name,
			duration: selectedPlan.selectedDuration,
			price: getUpdatedPrice(selectedPlan)
		});

		// Redirigir a PayPal o mostrar formulario de pago
	}
</script>

<div class="flex overflow-hidden bg-emerald-50">
	<Nav />

	<div class="w-full">
		<Header />

		<div class="container mx-auto py-10">
			<Title title="Choose your plan" additionalClass="text-center"></Title>

			<div class="grid grid-cols-1 gap-6 sm:grid-cols-2 md:grid-cols-3">
				{#each plans as plan}
					<!-- svelte-ignore a11y_click_events_have_key_events -->
					<!-- svelte-ignore a11y_no_static_element_interactions -->
					<div
						class="flex h-[400px] transform cursor-pointer flex-col justify-between rounded-xl bg-gray-100 p-5 shadow-lg transition-all duration-300 hover:scale-105 hover:shadow-xl {selectedPlan?.id ===
						plan.id
							? 'ring-2 ring-emerald-500'
							: ''}"
						on:click={() => selectPlan(plan)}
					>
						<GradientTitle text={plan.name} additionalClass="mx-auto" />
						<ul class="list-disc pl-5 text-sm text-gray-700">
							{#each plan.features as feature}
								<li>{feature}</li>
							{/each}
						</ul>
						<div>
							<p class="my-4 text-center text-3xl font-light text-gray-800">
								<span class="text-3xl font-light">{getUpdatedPrice(plan).toFixed(2)}</span>
								€/<span class="text-sm font-medium"
									>{plan.selectedDuration === 1
										? '1 Month'
										: plan.selectedDuration === 3
											? '3 Months'
											: '1 Year'}</span
								>
							</p>
							<div class="flex justify-evenly">
								<button
									type="button"
									on:click|stopPropagation={() => selectDuration(plan, 1)}
									class="rounded-full bg-emerald-600 px-4 py-2 text-sm text-white transition-colors hover:bg-emerald-700 {plan.selectedDuration ===
									1
										? 'bg-emerald-800 shadow-lg'
										: ''}"
									data-duration="month"
									>1 Month
								</button>
								<button
									type="button"
									on:click|stopPropagation={() => selectDuration(plan, 3)}
									class="rounded-full bg-emerald-600 px-4 py-2 text-sm text-white transition-colors hover:bg-emerald-700 {plan.selectedDuration ===
									3
										? 'bg-emerald-800 shadow-lg'
										: ''}"
									data-duration="3 months"
									>3 Months
								</button>
								<button
									type="button"
									on:click|stopPropagation={() => selectDuration(plan, 12)}
									class="rounded-full bg-emerald-600 px-4 py-2 text-sm text-white transition-colors hover:bg-emerald-700 {plan.selectedDuration ===
									12
										? 'bg-emerald-800 shadow-lg'
										: ''}"
									data-duration="year"
									>1 Year
								</button>
							</div>
							{#if selectedPlan?.id === plan.id}
								<div class="mt-4 flex justify-center gap-2">
									<button
										type="button"
										on:click|stopPropagation={deselectPlan}
										class="cursor-pointer rounded-full bg-gray-500 px-4 py-2 text-white transition-colors hover:bg-gray-600"
										>Cancel</button
									>
									<button
										type="button"
										on:click|stopPropagation={handlePayment}
										class="flex cursor-pointer items-center rounded-full border-1 border-yellow-600 bg-yellow-500 px-6 py-2 font-bold text-blue-500 italic transition-colors hover:bg-yellow-700"
										><svg
											xmlns="http://www.w3.org/2000/svg"
											width="33.91"
											class="me-2 h-5 w-5"
											viewBox="0 0 256 302"
											><path
												fill="#27346a"
												d="M217.168 23.507C203.234 7.625 178.046.816 145.823.816h-93.52A13.39 13.39 0 0 0 39.076 12.11L.136 259.077c-.774 4.87 2.997 9.28 7.933 9.28h57.736l14.5-91.971l-.45 2.88c1.033-6.501 6.593-11.296 13.177-11.296h27.436c53.898 0 96.101-21.892 108.429-85.221c.366-1.873.683-3.696.957-5.477q-2.334-1.236 0 0c3.671-23.407-.025-39.34-12.686-53.765"
											/><path
												fill="#27346a"
												d="M102.397 68.84a11.7 11.7 0 0 1 5.053-1.14h73.318c8.682 0 16.78.565 24.18 1.756a102 102 0 0 1 6.177 1.182a90 90 0 0 1 8.59 2.347c3.638 1.215 7.026 2.63 10.14 4.287c3.67-23.416-.026-39.34-12.687-53.765C203.226 7.625 178.046.816 145.823.816H52.295C45.71.816 40.108 5.61 39.076 12.11L.136 259.068c-.774 4.878 2.997 9.282 7.925 9.282h57.744L95.888 77.58a11.72 11.72 0 0 1 6.509-8.74"
											/><path
												fill="#2790c3"
												d="M228.897 82.749c-12.328 63.32-54.53 85.221-108.429 85.221H93.024c-6.584 0-12.145 4.795-13.168 11.296L61.817 293.621c-.674 4.262 2.622 8.124 6.934 8.124h48.67a11.71 11.71 0 0 0 11.563-9.88l.474-2.48l9.173-58.136l.591-3.213a11.71 11.71 0 0 1 11.562-9.88h7.284c47.147 0 84.064-19.154 94.852-74.55c4.503-23.15 2.173-42.478-9.739-56.054c-3.613-4.112-8.1-7.508-13.327-10.28c-.283 1.79-.59 3.604-.957 5.477"
											/><path
												fill="#1f264f"
												d="M216.952 72.128a90 90 0 0 0-5.818-1.49a110 110 0 0 0-6.177-1.174c-7.408-1.199-15.5-1.765-24.19-1.765h-73.309a11.6 11.6 0 0 0-5.053 1.149a11.68 11.68 0 0 0-6.51 8.74l-15.582 98.798l-.45 2.88c1.025-6.501 6.585-11.296 13.17-11.296h27.444c53.898 0 96.1-21.892 108.428-85.221c.367-1.873.675-3.688.958-5.477q-4.682-2.47-10.14-4.279a83 83 0 0 0-2.77-.865"
											/></svg
										><span class="text-blue-600">Pay</span>Pal</button
									>
								</div>
							{/if}
						</div>
					</div>
				{/each}
			</div>
		</div>
	</div>
</div>
