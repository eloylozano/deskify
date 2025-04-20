<script lang="ts">
	import Header from '../../components/Header.svelte';
	import Nav from '../../components/Nav.svelte';

	const chartOptions = {
		chart: {
			id: 'usuarios-activos',
			toolbar: { show: false },
		},
		xaxis: {
			categories: ['Ene', 'Feb', 'Mar', 'Abr', 'May']
		},
		stroke: {
			curve: 'smooth'
		},
		colors: ['#0ea5e9']
	};

	const chartSeries = [
		{
			name: 'Usuarios activos',
			data: [10, 23, 15, 40, 30]
		}
	];
</script>

<div class="flex h-[100vh] overflow-hidden bg-green-100">
	<Nav />
	<div class="w-full">
		<Header text="Dashboard" />

		<!-- Contenedor de gráfica -->
		<div class="p-6">
			<div class="bg-white rounded-2xl p-6 shadow w-full max-w-4xl mx-auto">
				
			</div>
		</div>
	</div>
</div>
