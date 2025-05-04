<script lang="ts">
	import { createEventDispatcher } from 'svelte';

	interface User {
		id: number;
		firstName: string;
		middleName?: string;
		lastName: string;
		profilePictureUrl?: string;
	}

	export let user: User;
	export let size = 24;

	const dispatch = createEventDispatcher();

	let imageError = false;
	let showEditIcon = false;
	let showModal = false;
	let fileError = '';
	let selectedFile: File | null = null;
	let previewUrl = '';
	let successMessage = '';

	const getInitials = (user: User): string => {
		const firstNameInitial = user.firstName?.[0]?.toUpperCase() || '';
		const lastNameInitial = user.lastName?.[0]?.toUpperCase() || '';
		return `${firstNameInitial}${lastNameInitial}`;
	};

	const handleFileChange = (event: Event) => {
		const target = event.target as HTMLInputElement;
		const file = target.files?.[0];
		fileError = '';

		if (!file) return;

		if (file.size > 5 * 1024 * 1024) {
			fileError = 'El archivo no puede pesar más de 5MB';
			return;
		}

		if (!file.type.match('image.*')) {
			fileError = 'Solo se permiten archivos de imagen';
			return;
		}

		selectedFile = file;

		const reader = new FileReader();
		reader.onload = (e) => {
			previewUrl = (e.target as FileReader).result as string;
		};
		reader.readAsDataURL(file);
	};

	const uploadImage = async () => {
		if (!selectedFile || !user?.id) return;

		const formData = new FormData();
		formData.append('file', selectedFile);

		try {
			const response = await fetch(
				`${import.meta.env.VITE_API_URL}/user/update/${user.id}/profile-picture`,
				{
					method: 'POST',
					body: formData
				}
			);

			if (!response.ok) {
				throw new Error('Error while uploading');
			}

			const updatedUser = await response.json();
			dispatch('update', updatedUser);
			location.reload();
			showModal = false;
			selectedFile = null;
			previewUrl = '';
			fileError = '';
		} catch (error: unknown) {
			if (error instanceof Error) {
				fileError = error.message;
			} else {
				fileError = 'Unknown error!';
			}
		}
	};

	const cancelUpload = () => {
		showModal = false;
		selectedFile = null;
		previewUrl = '';
		fileError = '';
	};
</script>

<div class="relative flex flex-col items-center gap-4">
	<!-- Contenedor de la imagen con hover -->
	<!-- svelte-ignore a11y_click_events_have_key_events -->
	<!-- svelte-ignore a11y_no_static_element_interactions -->
	<div
		class="relative cursor-pointer"
		on:mouseenter={() => (showEditIcon = true)}
		on:mouseleave={() => (showEditIcon = false)}
		on:click={() => (showModal = true)}
	>
		{#if user.profilePictureUrl && !imageError}
			<div
				class={`flex h-${size} w-${size} overflow-hidden rounded-full border-3 border-emerald-500`}
			>
				<img
					src={`${import.meta.env.VITE_API_URL}/uploads/profiles/${user.profilePictureUrl}`}
					alt="Foto de perfil"
					class="h-full w-full object-cover"
					on:error={() => (imageError = true)}
				/>
			</div>
		{:else}
			<div
				class={`flex h-23 w-23 items-center justify-center rounded-full bg-gray-300 text-4xl font-bold text-white`}
			>
				{getInitials(user)}
			</div>
		{/if}

		<!-- Icono de edición (aparece en hover) -->
		{#if showEditIcon}
			<div
				class="bg-opacity-30 absolute inset-0 flex items-center justify-center rounded-full bg-gray-700 opacity-85"
			>
				<svg
					xmlns="http://www.w3.org/2000/svg"
					class="h-8 w-8 text-white"
					fill="none"
					viewBox="0 0 24 24"
					stroke="currentColor"
				>
					<path
						stroke-linecap="round"
						stroke-linejoin="round"
						stroke-width="2"
						d="M15.232 5.232l3.536 3.536m-2.036-5.036a2.5 2.5 0 113.536 3.536L6.5 21.036H3v-3.572L16.732 3.732z"
					/>
				</svg>
			</div>
		{/if} 
	</div>

	<h1 class="mb-6 text-3xl font-bold">
		Welcome {user.firstName}
		{user.middleName}
		{user.lastName}
	</h1>
</div>

<!-- Modal para subir foto -->
{#if showModal}
	<div class="bg-opacity-50 fixed inset-0 z-50 flex items-center justify-center bg-[#161616cb] p-4">
		<div class="w-full max-w-md rounded-lg bg-white p-6">
			<h2 class="mb-4 text-xl font-bold">Change profile pic</h2>

			{#if previewUrl}
				<div class="mb-4 flex justify-center"> 
					<img
						src={previewUrl}
						alt="Previsualización"
						class="h-40 w-40 rounded-full border border-gray-300 object-cover"
					/>
				</div>
			{/if}

			<div class="mb-4">
				<!-- svelte-ignore a11y_label_has_associated_control -->
				<label class="mb-2 block text-sm font-medium text-gray-700">Select image (max 5MB)</label>
				<input
					type="file"
					accept="image/*"
					on:change={handleFileChange}
					class="focus:ring-opacity-50 focus:ring-opacity-50 block w-full
                            text-sm text-gray-500 transition-all duration-300
                            ease-in-out file:mr-4 file:rounded-md file:border-0
                            file:bg-emerald-100 file:px-4 file:py-2 file:text-sm
                            file:font-semibold file:text-emerald-700 hover:file:bg-emerald-400 hover:file:text-white
                            focus:ring-2 focus:ring-emerald-500 focus:outline-none"
				/>
				{#if fileError}
					<p class="mt-2 text-sm text-red-600">{fileError}</p>
				{/if}
			</div>

			<div class="flex justify-end gap-3">
				<button
					on:click={cancelUpload}
					class="rounded-md bg-gray-200 px-4 py-2 text-gray-800 transition hover:bg-gray-300"
				>
					Cancel
				</button>
				<button
					on:click={uploadImage}
					disabled={!selectedFile || fileError}
					class="rounded-md bg-emerald-500 px-4 py-2 text-white transition hover:bg-emerald-600 disabled:cursor-not-allowed disabled:bg-emerald-300"
				>
					Upload
				</button>
			</div>
		</div>
	</div>
{/if}
