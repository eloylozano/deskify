<script lang="ts">
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

			if (!window.paypal) throw new Error('PayPal SDK no está disponible');

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
						const details = await actions.order.capture();
						console.log('Pago completado por', details.payer.name?.given_name);
						alert(`Gracias, ${details.payer.name?.given_name}! Tu pago fue exitoso.`);
					},
					onError: (err) => {
						console.error('Error durante el pago:', err);
						alert('Ocurrió un error al inicializar PayPal.');
					}
				})
				.render(paypalContainer);

			paypalButtonsRendered = true;
		} catch (error) {
			console.error('Error al renderizar botones de PayPal:', error);
			alert('No se pudo cargar el sistema de pagos.');
		}
	}

	let lastRenderedPlanId: number | null = null;

	$: if (selectedPlan && paypalContainer && selectedPlan.id !== lastRenderedPlanId) {
		lastRenderedPlanId = selectedPlan.id;
		renderPayPalButtons();
	}
</script>

<div class="mt-4 flex justify-center gap-2" bind:this={paypalContainer}></div>
