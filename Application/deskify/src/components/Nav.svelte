<script>
	
</script>

<nav class="flex h-screen w-fit flex-col bg-[#02353C] p-5 text-white">
	<div>
		<a href="/login" aria-label="Login page">
			<img src="/favicon.png" alt="Deskify's Logo" class="mx-auto h-10 w-10" />
		</a>
	</div>

	<div class="mx-auto mt-10 flex h-full flex-col justify-between">
		<ul class="mt mx-auto flex flex-col gap-8">
			<li>
				<!-- Dashboard -->
				<a href="/dashboard" aria-label="dashboard page">
					<svg xmlns="http://www.w3.org/2000/svg" class="h-7 w-7" viewBox="0 0 24 24">
						<path fill="currentColor" d="M21 21H3v-2h18zM8 10H4v7h4zm6-4h-4v11h4zm6-3h-4v14h4z"
						></path>
					</svg>
				</a>
			</li>
			<li>
				<!-- Lista tickets -->
				<a href="/tickets" aria-label="tickets page">
					<svg xmlns="http://www.w3.org/2000/svg" class="h-7 w-7" viewBox="0 0 24 24">
						<path
							fill="currentColor"
							d="M5 6a1 1 0 0 0-1 1v10a1 1 0 0 0 1 1h7.17a3.001 3.001 0 0 1 5.66 0H19a1 1 0 0 0 1-1V7a1 1 0 0 0-1-1h-1.17a3.001 3.001 0 0 1-5.66 0zM2 7a3 3 0 0 1 3-3h8a1 1 0 0 1 1 1a1 1 0 1 0 2 0a1 1 0 0 1 1-1h2a3 3 0 0 1 3 3v10a3 3 0 0 1-3 3h-2a1 1 0 0 1-1-1a1 1 0 1 0-2 0a1 1 0 0 1-1 1H5a3 3 0 0 1-3-3zm13 2a1 1 0 0 1 1 1v.5a1 1 0 1 1-2 0V10a1 1 0 0 1 1-1m1 4.5a1 1 0 1 0-2 0v.5a1 1 0 1 0 2 0z"
						></path>
					</svg>
				</a>
			</li>
			<li>
				<!-- Lista Usuarios -->
				<a href="/users" aria-label="users page">
					<svg xmlns="http://www.w3.org/2000/svg" class="h-7 w-7" viewBox="0 0 24 24">
						<path
							fill="currentColor"
							d="M6 17c0-2 4-3.1 6-3.1s6 1.1 6 3.1v1H6m9-9a3 3 0 0 1-3 3a3 3 0 0 1-3-3a3 3 0 0 1 3-3a3 3 0 0 1 3 3M3 5v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2V5a2 2 0 0 0-2-2H5a2 2 0 0 0-2 2"
						></path>
					</svg>
				</a>
			</li>
			<li>
				<!-- FAQs -->
				<a href="/faqs" aria-label="faqs page">
					<svg xmlns="http://www.w3.org/2000/svg" class="h-7 w-7" viewBox="0 0 512 512">
						<path
							fill="currentColor"
							d="M334.434 206.171c0 13.516-3.435 25.318-10.288 35.397c-5.65 8.47-15.12 17.649-28.436 27.534c-7.664 5.247-12.711 10.184-15.126 14.823c-3.04 5.648-4.54 14.113-4.54 25.409h-42.666c0-17.137 1.824-29.64 5.454-37.504c4.23-9.483 13.407-19.064 27.521-28.743c6.664-5.045 11.503-10.183 14.529-15.425c3.625-5.852 5.449-12.503 5.449-19.966c0-11.899-3.539-20.766-10.594-26.624c-5.636-4.228-12.502-6.345-20.569-6.345c-13.108 0-22.59 4.339-28.436 13.009c-4.236 6.45-6.36 14.719-6.36 24.8v.304h-45.361c0-26.422 8.36-46.382 25.09-59.898c14.12-11.283 31.574-16.94 52.34-16.94c18.16 0 34.092 3.533 47.798 10.588c22.803 11.703 34.195 31.572 34.195 59.581m134.9 49.83c0 117.82-95.513 213.333-213.334 213.333c-117.82 0-213.333-95.513-213.333-213.334S138.18 42.667 256 42.667S469.334 138.179 469.334 256m-42.667 0c0-94.107-76.561-170.667-170.667-170.667S85.334 161.894 85.334 256S161.894 426.667 256 426.667S426.667 350.106 426.667 256m-170.668 69.333c-14.728 0-26.667 11.938-26.667 26.666s11.94 26.667 26.667 26.667s26.667-11.939 26.667-26.667s-11.94-26.666-26.667-26.666"
						></path>
					</svg>
				</a>
			</li>
			<li>
				<!-- Settings -->
				<a href="/settings" aria-label="settings page">
					<svg xmlns="http://www.w3.org/2000/svg" class="h-7 w-7" viewBox="0 0 24 24">
						<path
							fill="currentColor"
							d="M19.14 12.94c.04-.3.06-.61.06-.94c0-.32-.02-.64-.07-.94l2.03-1.58a.49.49 0 0 0 .12-.61l-1.92-3.32a.49.49 0 0 0-.59-.22l-2.39.96c-.5-.38-1.03-.7-1.62-.94l-.36-2.54a.484.484 0 0 0-.48-.41h-3.84c-.24 0-.43.17-.47.41l-.36 2.54c-.59.24-1.13.57-1.62.94l-2.39-.96c-.22-.08-.47 0-.59.22L2.74 8.87c-.12.21-.08.47.12.61l2.03 1.58c-.05.3-.09.63-.09.94s.02.64.07.94l-2.03 1.58a.49.49 0 0 0-.12.61l1.92 3.32c.12.22.37.29.59.22l2.39-.96c.5.38 1.03.7 1.62.94l.36 2.54c.05.24.24.41.48.41h3.84c.24 0 .44-.17.47-.41l.36-2.54c.59-.24 1.13-.56 1.62-.94l2.39.96c.22.08.47 0 .59-.22l1.92-3.32c.12-.22.07-.47-.12-.61zM12 15.6c-1.98 0-3.6-1.62-3.6-3.6s1.62-3.6 3.6-3.6s3.6 1.62 3.6 3.6s-1.62 3.6-3.6 3.6"
						></path>
					</svg>
				</a>
			</li>
		</ul>

		<!-- Icono User Account -->
		<a href="/user" aria-label="user page">
			<svg xmlns="http://www.w3.org/2000/svg" class="h-7 w-7" viewBox="0 0 24 24"
				><g
					fill="none"
					stroke="#fff"
					stroke-linecap="round"
					stroke-linejoin="round"
					stroke-width="2"
					><circle cx="12" cy="8" r="5" /><path d="M20 21a8 8 0 1 0-16 0m16 0a8 8 0 1 0-16 0" /></g
				></svg
			>
			
		</a>
	</div>
</nav>
