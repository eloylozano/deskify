<script lang="ts">
	import Header from '../../components/Header.svelte';
	import Nav from '../../components/Nav.svelte';
	import CustomCheckbox from '../../components/CustomCheckbox.svelte';

	let darkMode = true;
	let notificationsEnabled = true;
	let emailFrequency = 'weekly';
	let selectedLanguage = 'es';
	let profileVisibility = 'public';
</script>

<div class="flex h-[100vh] overflow-hidden bg-emerald-100 dark:bg-gray-900">
	<Nav />
	<div class="w-full">
		<Header text="Settings" />

		<div class="container mx-auto px-4 py-8">
			<h1 class="mb-8 text-3xl font-bold text-gray-800 dark:text-white">Configuration</h1>

			<div class="grid grid-cols-1 gap-8 lg:grid-cols-2">
				<!-- Sección de Apariencia -->
				<div class="rounded-lg bg-white p-6 shadow dark:bg-gray-800">
					<h2 class="mb-4 text-xl font-semibold text-gray-800 dark:text-white">Theme</h2>

					<div class="space-y-4">
						<div class="flex items-center justify-between">
							<div>
								<h3 class="text-lg font-medium text-gray-700 dark:text-gray-300">Dark mode</h3>
								<p class="text-sm text-gray-500 dark:text-gray-400">
									Activate dark theme for a better nighttime experience
								</p>
							</div>
							<label class="relative inline-flex cursor-pointer items-center">
								<input type="checkbox" bind:checked={darkMode} class="peer sr-only" />
								<div
									class="peer h-6 w-11 rounded-full bg-gray-200 peer-checked:bg-emerald-500 peer-focus:outline-none after:absolute after:top-[2px] after:left-[2px] after:h-5 after:w-5 after:rounded-full after:border after:border-gray-300 after:bg-white after:transition-all after:content-[''] peer-checked:after:translate-x-full peer-checked:after:border-white dark:border-gray-600 dark:bg-gray-700"
								></div>
							</label>
						</div>

						<div class="border-t border-gray-200 pt-4 dark:border-gray-700">
							<h3 class="text-lg font-medium text-gray-700 dark:text-gray-300">Accent Color</h3>
							<div class="mt-3 flex gap-3">
								<button
									class="h-8 w-8 rounded-full bg-emerald-500 ring-2 ring-emerald-500 ring-offset-2"
								></button>
								<button
									class="h-8 w-8 rounded-full bg-blue-500 hover:ring-2 hover:ring-blue-500 hover:ring-offset-2"
								></button>
								<button
									class="h-8 w-8 rounded-full bg-purple-500 hover:ring-2 hover:ring-purple-500 hover:ring-offset-2"
								></button>
								<button
									class="h-8 w-8 rounded-full bg-amber-500 hover:ring-2 hover:ring-amber-500 hover:ring-offset-2"
								></button>
							</div>
						</div>
					</div>
				</div>

				<!-- Sección de Notificaciones -->
				<div class="rounded-lg bg-white p-6 shadow dark:bg-gray-800">
					<h2 class="mb-4 text-xl font-semibold text-gray-800 dark:text-white">Notifications</h2>

					<div class="space-y-4">
						<div class="flex items-center justify-between">
							<div>
								<h3 class="text-lg font-medium text-gray-700 dark:text-gray-300">
									Email Notifications
								</h3>
								<p class="text-sm text-gray-500 dark:text-gray-400">
									Receive important updates by email
								</p>
							</div>
							<label class="relative inline-flex cursor-pointer items-center">
								<input type="checkbox" bind:checked={notificationsEnabled} class="peer sr-only" />
								<div
									class="peer h-6 w-11 rounded-full bg-gray-200 peer-checked:bg-emerald-500 peer-focus:outline-none after:absolute after:top-[2px] after:left-[2px] after:h-5 after:w-5 after:rounded-full after:border after:border-gray-300 after:bg-white after:transition-all after:content-[''] peer-checked:after:translate-x-full peer-checked:after:border-white dark:border-gray-600 dark:bg-gray-700"
								></div>
							</label>
						</div>

						<div class="border-t border-gray-200 pt-4 dark:border-gray-700">
							<h3 class="text-lg font-medium text-gray-700 dark:text-gray-300">
								Email frequency
							</h3>
							<select
								bind:value={emailFrequency}
								class="mt-2 block w-full rounded-md border border-gray-300 bg-white px-3 py-2 shadow-sm focus:border-emerald-500 focus:ring-emerald-500 focus:outline-none dark:border-gray-600 dark:bg-gray-700 dark:text-white"
							>
								<option value="daily">Daily</option>
								<option value="weekly">Weekly</option>
								<option value="monthly">Monthly</option>
							</select>
						</div>
					</div>
				</div>

				<!-- Sección de Idioma -->
				<div class="rounded-lg bg-white p-6 shadow dark:bg-gray-800">
					<h2 class="mb-4 text-xl font-semibold text-gray-800 dark:text-white">Language</h2>

					<div>
						<select
							bind:value={selectedLanguage}
							class="block w-full rounded-md border border-gray-300 bg-white px-3 py-2 shadow-sm focus:border-emerald-500 focus:ring-emerald-500 focus:outline-none dark:border-gray-600 dark:bg-gray-700 dark:text-white"
						>
							<option value="es">Español</option>
							<option value="en">English</option>
							<option value="fr">Français</option>
							<option value="de">Deutsch</option>
						</select>
					</div>
				</div>

				<!-- Sección de Privacidad -->
				<div class="rounded-lg bg-white p-6 shadow dark:bg-gray-800">
					<h2 class="mb-4 text-xl font-semibold text-gray-800 dark:text-white">Privacy</h2>

					<div class="space-y-4">
						<div>
							<h3 class="text-lg font-medium text-gray-700 dark:text-gray-300">
								Profily Visibility
							</h3>
							<div class="mt-2 space-y-2">
								<div class="flex items-center">
									<input
										id="public"
										bind:group={profileVisibility}
										type="radio"
										value="public"
										class="h-4 w-4 border-gray-300 text-emerald-500 focus:ring-emerald-500 dark:border-gray-600 dark:bg-gray-700"
									/>
									<label
										for="public"
										class="ml-3 block text-sm font-medium text-gray-700 dark:text-gray-300"
										>Public (Everyone can see your profile)</label
									>
								</div>
								<div class="flex items-center">
									<input
										id="private"
										bind:group={profileVisibility}
										type="radio"
										value="private"
										class="h-4 w-4 border-gray-300 text-emerald-500 focus:ring-emerald-500 dark:border-gray-600 dark:bg-gray-700"
									/>
									<label
										for="private"
										class="ml-3 block text-sm font-medium text-gray-700 dark:text-gray-300"
										>Private (Registered users only)</label
									>
								</div>
								<div class="flex items-center">
									<input
										id="hidden"
										bind:group={profileVisibility}
										type="radio"
										value="hidden"
										class="h-4 w-4 border-gray-300 text-emerald-500 focus:ring-emerald-500 dark:border-gray-600 dark:bg-gray-700"
									/>
									<label
										for="hidden"
										class="ml-3 block text-sm font-medium text-gray-700 dark:text-gray-300"
										>Hidden (Only you can see your profile)</label
									>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- Botón de Guardar (no funcional) -->
			<div class="mt-8 flex justify-end">
				<button
					class="rounded-md bg-emerald-500 px-4 py-2 text-sm font-medium text-white shadow-sm hover:bg-emerald-600 focus:ring-2 focus:ring-emerald-500 focus:ring-offset-2 focus:outline-none dark:focus:ring-offset-gray-800"
				>
					Save
				</button>
			</div>
		</div>
	</div>
</div>

<style>
	/* Estilos para el modo oscuro (solo visual) */
	.dark .dark\:bg-gray-900 {
		background-color: #111827;
	}
	.dark .dark\:bg-gray-800 {
		background-color: #1f2937;
	}
	.dark .dark\:text-white {
		color: #ffffff;
	}
	.dark .dark\:text-gray-300 {
		color: #d1d5db;
	}
	.dark .dark\:text-gray-400 {
		color: #9ca3af;
	}
	.dark .dark\:border-gray-700 {
		border-color: #374151;
	}
	.dark .dark\:border-gray-600 {
		border-color: #4b5563;
	}
	.dark .dark\:focus\:ring-offset-gray-800:focus {
		--tw-ring-offset-color: #1f2937;
	}
</style>
