<script>
</script>

<div class="w-full">
	<div id="top" class="flex items-center justify-between bg-[#01c883] px-5 py-4">
		<div>
			<p class="font-medium text-white">All tickets - deskify</p>
		</div>

		<div class="flex items-center gap-5">
			<svg xmlns="http://www.w3.org/2000/svg" class="h-7 w-7 text-white" viewBox="0 0 24 24"
				><path
					fill="currentColor"
					d="M9.5 16q-2.725 0-4.612-1.888T3 9.5t1.888-4.612T9.5 3t4.613 1.888T16 9.5q0 1.1-.35 2.075T14.7 13.3l5.6 5.6q.275.275.275.7t-.275.7t-.7.275t-.7-.275l-5.6-5.6q-.75.6-1.725.95T9.5 16m0-2q1.875 0 3.188-1.312T14 9.5t-1.312-3.187T9.5 5T6.313 6.313T5 9.5t1.313 3.188T9.5 14"
				/></svg
			>
			<svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6 text-white" viewBox="0 0 48 48"
				><g fill="none" stroke="#fff" stroke-linejoin="round" stroke-width="4"
					><rect width="36" height="36" x="6" y="6" rx="3" /><path
						stroke-linecap="round"
						d="M24 16v16m-8-8h16"
					/></g
				></svg
			>
			<!-- svelte-ignore a11y_img_redundant_alt -->
			<img src="/default-profile.jpg" alt="Profile picture" class="h-10 w-10 rounded-full" />
		</div>
	</div>
	<div id="bot" class="flex justify-between items-center bg-gray-100 px-5 py-3">
		<div class="flex items-center">
			<input type="checkbox" class="custom-checkbox me-2 cursor-pointer" />
			<p><span class="font-medium text-[#858585]">Order by:</span></p>
			<select class="custom-select w-40 cursor-pointer font-medium text-[#252525]">
				<option value="1">Last Modified</option>
				<option value="2">Priority</option>
				<option value="3">Status</option>
				<option value="4">Agent</option>
			</select>
		</div>
		<div class="flex gap-3 items-center">
			<p class="font-medium text-[#858585]">1-30 de 99</p>
			<div class="flex border-2 rounded-sm border-[#858585]">
				<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path fill="#fff" d="M9.31 6.71a.996.996 0 0 0 0 1.41L13.19 12l-3.88 3.88a.996.996 0 1 0 1.41 1.41l4.59-4.59a.996.996 0 0 0 0-1.41L10.72 6.7c-.38-.38-1.02-.38-1.41.01" stroke-width="0.5" stroke="#fff"/></svg>
				<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path fill="#fff" d="M9.31 6.71a.996.996 0 0 0 0 1.41L13.19 12l-3.88 3.88a.996.996 0 1 0 1.41 1.41l4.59-4.59a.996.996 0 0 0 0-1.41L10.72 6.7c-.38-.38-1.02-.38-1.41.01" stroke-width="0.5" stroke="#fff"/></svg>
			</div>
			<svg xmlns="http://www.w3.org/2000/svg" class="h-7 w-7" viewBox="0 0 24 24"><g fill="none" stroke="#858585" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"><path d="M4 6a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H6a2 2 0 0 1-2-2zm11-2v16"/><path d="m9 10l2 2l-2 2"/></g></svg>
		</div>
	</div>
</div>

<style>
	.custom-select {
		border: none;
		border-radius: 6px;
		padding: 6px 10px;
		background-color: transparent;
		appearance: none;
		outline: none;
		background-image: url("data:image/svg+xml;charset=US-ASCII,%3Csvg xmlns='http://www.w3.org/2000/svg' width='12' height='12' viewBox='0 0 20 20'%3E%3Cpath fill='%2301c833' d='M5.516 7.548l4.484 4.484 4.484-4.484 1.032 1.032-5.516 5.516-5.516-5.516z'/%3E%3C/svg%3E");
		background-repeat: no-repeat;
		background-position: right 0.5rem center;
		background-size: 1rem;
	}

	/* Estilos para los options */
	.custom-select option {
		background-color: white;
		color: #252525;
		padding: 8px 12px;
		font-size: 14px;
	}

	/* Estilo cuando se pasa el mouse sobre un option */
	.custom-select option:hover {
		background-color: #01c883;
		color: white;
	}

	/* Estilo para el option seleccionado */
	.custom-select option:checked {
		background-color: #01c883;
		color: white;
	}

	.custom-select:focus {
		outline: none;
		box-shadow: none;
		border-color: transparent;
	}

	#bot {
		box-shadow: 0px 3px 5px 1px rgba(0, 0, 0, 0.2);
	}

	.custom-checkbox {
		appearance: none;
		width: 18px;
		height: 18px;
		border: 2px solid #ccc;
		border-radius: 3px;
		outline: none;
		transition: all 0.2s;
		position: relative;
	}
	.custom-checkbox:focus {
		outline: none;
		box-shadow: none;
	}
	.custom-checkbox:checked {
		background-color: #01c883;
		border-color: #01c883;
	}
	.custom-checkbox:checked::after {
		content: '✓';
		position: absolute;
		color: white;
		font-size: 12px;
		top: 50%;
		left: 50%;
		transform: translate(-50%, -50%);
	}
</style>
