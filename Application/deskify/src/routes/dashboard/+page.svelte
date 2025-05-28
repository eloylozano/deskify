<script lang="ts">
	import { onMount } from 'svelte';
	import Header from '../../components/Header.svelte';
	import Nav from '../../components/Nav.svelte';
	import ProtectedRoute from '../../components/ProtectedRoute.svelte';

	let ticketsAbiertos = 0;
	let ticketsResueltos = 0;
	let tiempoPromedioResolucion = 3.34;
	let agentesActivos = 4;

	let estadoTickets = {
		abierto: 0,
		cerrado: 0,
		pendiente: 0,
		enProgreso: 0,
		resuelto: 0
	};

	onMount(async () => {
		try {
			const res = await fetch('http://localhost:8080/ticket/summary');
			const data = await res.json();

			ticketsAbiertos = data.totalTickets;
			ticketsResueltos = data.statusCounts.Solved;

			estadoTickets.abierto = data.statusCounts.Open;
			estadoTickets.cerrado = data.statusCounts.Closed;
			estadoTickets.pendiente = data.statusCounts.Pending;
			estadoTickets.enProgreso = data.statusCounts['In Progress'];
			estadoTickets.resuelto = data.statusCounts.Solved;
		} catch (error) {
			console.error('Error al obtener los datos:', error);
		}
	});
</script>

<ProtectedRoute>
	<div class="flex overflow-hidden bg-emerald-50">
		<Nav />

		<div class="w-full">
			<Header text="Dashboard" />

			<div class="p-5">
				<!-- Estadísticas principales -->
				<div class="grid grid-cols-1 gap-6 sm:grid-cols-2 md:grid-cols-3">
					<div class="rounded-lg bg-white p-4 shadow-xl">
						<h3 class="text-lg font-semibold text-gray-800">Open Tickets</h3>
						<p class="text-3xl font-bold text-gray-900">{ticketsAbiertos}</p>
					</div>
					<div class="rounded-lg bg-white p-4 shadow-xl">
						<h3 class="text-lg font-semibold text-gray-800">Solved Tickets</h3>
						<p class="text-3xl font-bold text-gray-900">{ticketsResueltos}</p>
					</div>
					<div class="rounded-lg bg-white p-4 shadow-xl">
						<h3 class="text-lg font-semibold text-gray-800">Avg. Time to Solve</h3>
						<p class="text-3xl font-bold text-gray-900">{tiempoPromedioResolucion} h</p>
					</div>
				</div>

				<!-- Estadísticas de agentes -->
				<div class="mt-6 rounded-lg bg-white p-6 shadow-xl">
					<h3 class="text-xl font-semibold text-gray-800">Active Agents</h3>
					<p class="text-2xl font-bold text-gray-900">{agentesActivos} agents working</p>
				</div>

				<!-- Gráfica de estado de tickets -->
				<div class="mt-6 rounded-lg bg-white p-6 shadow-xl">
					<h3 class="text-xl font-semibold text-gray-800">Ticket by Status</h3>
					<div class="mt-4 flex justify-between gap-6">
						<!-- Tickets Abiertos -->
						<div class="relative flex-1 text-center">
							<div class="h-24 overflow-hidden rounded-lg bg-yellow-300">
								<div
									class="h-full bg-yellow-500 transition-all duration-500 ease-out"
									style="width: {(estadoTickets.abierto /
										(estadoTickets.abierto +
											estadoTickets.cerrado +
											estadoTickets.pendiente +
											estadoTickets.enProgreso)) *
										100}%"
								></div>
							</div>
							<p
								class="absolute top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 transform font-bold text-gray-950"
							>
								{estadoTickets.abierto} Open
							</p>
						</div>

						<!-- Tickets Cerrados -->
						<div class="relative flex-1 text-center">
							<div class="h-24 overflow-hidden rounded-lg bg-green-300">
								<div
									class="h-full bg-green-500 transition-all duration-500 ease-out"
									style="width: {(estadoTickets.cerrado /
										(estadoTickets.abierto +
											estadoTickets.cerrado +
											estadoTickets.pendiente +
											estadoTickets.enProgreso)) *
										100}%"
								></div>
							</div>
							<p
								class="absolute top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 transform font-bold text-gray-950"
							>
								{estadoTickets.cerrado} Closed
							</p>
						</div>

						<!-- Tickets Pendientes -->
						<div class="relative flex-1 text-center">
							<div class="h-24 overflow-hidden rounded-lg bg-gray-300">
								<div
									class="h-full bg-gray-500 transition-all duration-500 ease-out"
									style="width: {(estadoTickets.pendiente /
										(estadoTickets.abierto +
											estadoTickets.cerrado +
											estadoTickets.pendiente +
											estadoTickets.enProgreso)) *
										100}%"
								></div>
							</div>
							<p
								class="absolute top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 transform font-bold text-gray-950"
							>
								{estadoTickets.pendiente} Pending
							</p>
						</div>

						<!-- Tickets en Progreso -->
						<div class="relative flex-1 text-center">
							<div class="h-24 overflow-hidden rounded-lg bg-blue-300">
								<div
									class="h-full bg-blue-500 transition-all duration-500 ease-out"
									style="width: {(estadoTickets.enProgreso /
										(estadoTickets.abierto +
											estadoTickets.cerrado +
											estadoTickets.pendiente +
											estadoTickets.enProgreso)) *
										100}%"
								></div>
							</div>
							<p
								class="absolute top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 transform font-bold text-gray-950"
							>
								{estadoTickets.enProgreso} In Progress
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</ProtectedRoute>

<style>
	.bg-gradient-to-r {
		background-image: linear-gradient(to right, #81e6d9, #6ee7b7);
	}
	.shadow-xl {
		box-shadow: 0 10px 20px rgba(0, 0, 0, 0.15);
	}
	.bg-yellow-300 {
		background-color: #fef08a;
	}
	.bg-green-300 {
		background-color: #bbf7d0;
	}
	.bg-gray-300 {
		background-color: #e5e7eb;
	}
	.bg-blue-300 {
		background-color: #93c5fd;
	}
	.text-gray-800 {
		color: #2d3748;
	}
	.text-gray-900 {
		color: #1a202c;
	}
	.font-semibold {
		font-weight: 600;
	}
	.font-bold {
		font-weight: 700;
	}
	.text-3xl {
		font-size: 2rem;
	}
	.text-xl {
		font-size: 1.25rem;
	}
	.text-2xl {
		font-size: 1.5rem;
	}
	.p-4 {
		padding: 1rem;
	}
	.p-6 {
		padding: 1.5rem;
	}
</style>
