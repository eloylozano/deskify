<script lang="ts">
	import { onMount } from 'svelte';

	export let user: {
		id?: number;
		name: string;
		email: string;
		profilePictureUrl?: string;
	};
	export let href: string;

	let tooltipElement: HTMLDivElement;
	let show = false;
	let tooltipPosition = 'top';

	function calculatePosition() {
		if (!tooltipElement) return;

		const rect = tooltipElement.getBoundingClientRect();

		// Verificar espacio disponible
		const spaceAbove = rect.top;
		const spaceBelow = window.innerHeight - rect.bottom;
		const spaceLeft = rect.left;
		const spaceRight = window.innerWidth - rect.right;

		// Determinar la mejor posición
		if (spaceBelow < 200 && spaceAbove > 200) {
			tooltipPosition = 'top';
		} else {
			tooltipPosition = 'bottom';
		}

		// Ajustar horizontalmente si está cerca del borde derecho
		if (spaceRight < 250) {
			tooltipPosition += '-left';
		} else if (spaceLeft < 250) {
			tooltipPosition += '-right';
		} else {
			tooltipPosition += '-center';
		}
	}
	function getInitials(name: string): string {
		const names = name.split(' ');
		let initials = names[0].substring(0, 1).toUpperCase();
		
		if (names.length > 1) {
			initials += names[names.length - 1].substring(0, 1).toUpperCase();
		}
		
		return initials;
	}
</script>

<!-- svelte-ignore a11y_no_static_element_interactions -->
<div
	class="relative inline-block"
	bind:this={tooltipElement}
	on:mouseenter={() => {
		show = true;
		calculatePosition();
	}}
	on:mouseleave={() => (show = false)}
>
	<a {href} class="cursor-pointer font-medium text-emerald-600 underline hover:underline">
		<slot />
	</a>

	{#if show}
		<div
			class="absolute z-50 w-fit rounded-lg border border-gray-200 bg-white p-3 shadow-lg
                 {tooltipPosition.includes('top') ? 'bottom-full mb-1' : 'top-full mt-1'}
                 {tooltipPosition.includes('left')
				? 'right-0'
				: tooltipPosition.includes('right')
					? 'left-0'
					: 'left-1/2 -translate-x-1/2 transform'}"
		>
			<div class="flex items-center space-x-2">
				{#if user.profilePictureUrl}
					<img src={user.profilePictureUrl} alt={user.name} class="h-10 w-10 rounded-full" />
				{:else}
					<div class="flex h-10 w-10 items-center justify-center rounded-full bg-emerald-100">
						<span class="text-sm font-medium text-emerald-800">{getInitials(user.name)}</span>
					</div>
				{/if}
				<div>
					<p class="font-medium text-gray-900">{user.name}</p>
					<p class="text-sm text-gray-500">{user.email}</p>
				</div>
			</div>
		</div>
	{/if}
</div>

<style>
	html,
	body {
		overflow-x: hidden;
	}
	.group:hover .group-hover\:block {
		display: block;
	}
	/* Añade esto a tu estilo existente */
	.tooltip-container {
		position: relative;
		display: inline-block;
	}

	.tooltip-content {
		visibility: hidden;
		width: 220px;
		background-color: white;
		color: #333;
		text-align: center;
		border-radius: 6px;
		padding: 10px;
		position: absolute;
		z-index: 1;
		bottom: 125%;
		left: 50%;
		transform: translateX(-50%);
		opacity: 0;
		transition: opacity 0.3s;
		box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
		border: 1px solid #e2e8f0;
	}

	.tooltip-container:hover .tooltip-content {
		visibility: visible;
		opacity: 1;
	}

	.tooltip-arrow {
		position: absolute;
		top: 100%;
		left: 50%;
		margin-left: -5px;
		border-width: 5px;
		border-style: solid;
		border-color: white transparent transparent transparent;
	}
</style>
