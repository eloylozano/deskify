<script lang="ts">
	import ProtectedRoute from './../../components/ProtectedRoute.svelte';
	import GradientTitle from '../../components/GradientTitle.svelte';
	import Header from '../../components/Header.svelte';
	import Nav from '../../components/Nav.svelte';
	import PayPalButtons from '../../components/PayPalButtons.svelte';
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

	function selectPlan(plan: Plan) {
		selectedPlan = plan;
	}

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
	
</script>

<ProtectedRoute>
	<div class="flex overflow-hidden bg-emerald-50">
		<Nav />
		<div class="w-full">
			<Header text="Subscriptions" />
	
			<div class="container mx-auto py-10">
				<Title title="Choose your plan" additionalClass="text-center" />
	
				<div class="grid grid-cols-1 gap-6 sm:grid-cols-2 md:grid-cols-3">
					{#each plans as plan}
						<!-- svelte-ignore a11y_click_events_have_key_events -->
						<!-- svelte-ignore a11y_no_static_element_interactions -->
						<div
							class="flex h-auto transform flex-col justify-between rounded-xl bg-gray-100 p-5 shadow-lg transition-all duration-300 hover:scale-105 hover:shadow-xl {selectedPlan?.id ===
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
									€/
									<span class="text-sm font-medium">
										{plan.selectedDuration === 1
											? '1 Month'
											: plan.selectedDuration === 3
												? '3 Months'
												: '1 Year'}
									</span>
								</p>
	
								<div class="flex justify-evenly">
									<button
										type="button"
										on:click|stopPropagation={() => selectDuration(plan, 1)}
										class="rounded-full px-4 py-2 text-sm text-white transition-colors hover:bg-emerald-700 {plan.selectedDuration ===
										1
											? 'bg-emerald-800 shadow-lg'
											: 'bg-emerald-600'}"
									>
										1 Month
									</button>
									<button
										type="button"
										on:click|stopPropagation={() => selectDuration(plan, 3)}
										class="rounded-full px-4 py-2 text-sm text-white transition-colors hover:bg-emerald-700 {plan.selectedDuration ===
										3
											? 'bg-emerald-800 shadow-lg'
											: 'bg-emerald-600'}"
									>
										3 Months
									</button>
									<button
										type="button"
										on:click|stopPropagation={() => selectDuration(plan, 12)}
										class="rounded-full px-4 py-2 text-sm text-white transition-colors hover:bg-emerald-700 {plan.selectedDuration ===
										12
											? 'bg-emerald-800 shadow-lg'
											: 'bg-emerald-600'}"
									>
										1 Year
									</button>
								</div>
	
								{#if selectedPlan?.id === plan.id}
									<div class="mt-4 flex justify-center gap-2">
										<button
											type="button"
											on:click|stopPropagation={deselectPlan}
											class="rounded-full bg-gray-500 px-4 py-2 text-white transition-colors hover:bg-gray-600"
										>
											Cancel
										</button>
									</div>
									<PayPalButtons {selectedPlan} />
								{/if}
							</div>
						</div>
					{/each}
				</div>
			</div>
		</div>
	</div>
</ProtectedRoute>
