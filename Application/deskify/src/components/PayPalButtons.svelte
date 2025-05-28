<script lang="ts">
	import { redirect } from '@sveltejs/kit';
	import { onMount } from 'svelte';

	export let selectedPlan: {
		id: number;
		name: string;
		description: string;
		price: number | null;
		duration: string;
		features: string[];
		selectedDuration: number;
	} | null;

	let paypalContainer: HTMLDivElement;
	let paypalButtonsRendered = false;

	function getUpdatedPrice(plan) {
		if (!plan.price) return 0;
		if (plan.selectedDuration === 1) return plan.price;
		if (plan.selectedDuration === 3) return plan.price * 2.5;
		if (plan.selectedDuration === 12) return plan.price * 11;
		return plan.price;
	}

	async function loadPayPalSDK() {
		return new Promise<void>((resolve, reject) => {
			if (window.paypal) {
				resolve();
				return;
			}

			// Sandbox URL // https://sandbox.paypal.com

			// Sandbox Region // ES
			// Email // sb-e9pw041911004@business.example.com

			// Password // 5"2fm4M>

			const script = document.createElement('script');
			script.src = `https://www.paypal.com/sdk/js?client-id=AeIWSDrAeBSFwiR65BR2KtUQKZ8EgV6or_zPX4CPf-xM-foa5VGqwKz0BFERZz8K6NngacWEvfnCDGhL&currency=EUR`;
			script.onload = () => resolve();
			script.onerror = () => reject(new Error('Failed to load PayPal SDK'));
			document.head.appendChild(script);
		});
	}

	async function renderPayPalButtons() {
		if (!selectedPlan || !paypalContainer) return;

		try {
			await loadPayPalSDK();

			if (paypalButtonsRendered) {
				paypalContainer.innerHTML = '';
			}

			const price = getUpdatedPrice(selectedPlan).toFixed(2);

			if (!window.paypal) throw new Error('PayPal SDK is not available');

			window.paypal
				.Buttons({
					style: {
						layout: 'vertical',
						color: 'blue',
						shape: 'pill',
						label: 'paypal'
					},
					createOrder: (data, actions) => {
						return actions.order.create({
							purchase_units: [
								{
									amount: {
										value: price,
										currency_code: 'EUR'
									},
									description: `${selectedPlan.name} plan (${selectedPlan.selectedDuration} month${selectedPlan.selectedDuration > 1 ? 's' : ''})`
								}
							]
						});
					},
					onApprove: async (data, actions) => {
						try {
							const details = await actions.order.capture();
							console.log('Payment completed by', details.payer.name?.given_name);
							alert(`Thanks, ${details.payer.name?.given_name}! Your payment was approved.`);

							const now = new Date();
							const startDateTime = now.toISOString();

							const durationDays = parseInt(selectedPlan.duration) || 30; 

							const endDate = new Date(now);
							endDate.setDate(endDate.getDate() + durationDays);
							const endDateTime = endDate.toISOString();
							const email = sessionStorage.getItem('email');

							if (!email) {
								throw new Error('No se encontró el email en sessionStorage');
							}

							const subscriptionDTO = {
								user: {
									email
								},
								plan: {
									name: selectedPlan.name
								},
								startDateTime,
								active: true
							};

							const response = await fetch(
								`${import.meta.env.VITE_API_URL}/subscriptions/subscript`,
								{
									method: 'POST',
									headers: { 'Content-Type': 'application/json' },
									body: JSON.stringify(subscriptionDTO)
								}
							);

							if (!response.ok) {
								const errorText = await response.text();
								throw new Error(`Error creating subscription: ${errorText}`);
							}

							const createdSubscription = await response.json();
							console.log('Subscription created:', createdSubscription);

							history.back();
						} catch (err) {
							console.error('Error during payment or subscription creation:', err);
							alert('Error procesando el pago o creando la suscripción.');
						}
					},
					onError: (err) => {
						console.error('Error during payment:', err);
						alert('OAn error occurred while initializing PayPal.');
					}
				})
				.render(paypalContainer);

			paypalButtonsRendered = true;
		} catch (error) {
			console.error('Error rendering PayPal buttons:', error);
			alert('The payment system could not be loaded.');
		}
	}

	let lastRenderedPlanId: number | null = null;

	$: if (selectedPlan && paypalContainer && selectedPlan.id !== lastRenderedPlanId) {
		lastRenderedPlanId = selectedPlan.id;
		renderPayPalButtons();
	}
</script>

<div class="mt-4 flex justify-center gap-2" bind:this={paypalContainer}></div>
