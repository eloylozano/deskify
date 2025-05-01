<script lang="ts">
	import { page } from '$app/stores';
	import CustomCheckbox from './CustomCheckbox.svelte';
	import Loading from './Loading.svelte';
	import SubHeader from './SubHeader.svelte';
	export let data: {
		users: Array<{
			id: number;
			profilePictureUrl: string | null;
			firstName: string;
			middleName: string;
			lastName: string;
			phoneNumber: string;
			email: string;
			company: string | null;
			roleName: string;
			planName: string | null;
			createdAt: string;
			updatedAt: string;
		}>;
	};

	let selectedUsers: number[] = [];
	let selectAll: boolean = false;
	let error: string | null = null;

	function toggleSelectAll(value: boolean) {
		selectAll = value;
		if (value) {
			selectedUsers = data.users.map((user) => user.id);
		} else {
			selectedUsers = [];
		}
	}

	function toggleSelectUser(id: number, value: boolean) {
		if (value) {
			selectedUsers = [...selectedUsers, id];
		} else {
			selectedUsers = selectedUsers.filter((t) => t !== id);
		}
		selectAll = selectedUsers.length === data.users.length;
	}

	function formatRelativeTime(dateString: string | Date): string {
		const date = new Date(dateString);
		const now = new Date();
		const diffInSeconds = Math.floor((now.getTime() - date.getTime()) / 1000);

		if (diffInSeconds < 60) {
			return 'a few seconds ago';
		} else if (diffInSeconds < 3600) {
			const minutes = Math.floor(diffInSeconds / 60);
			return `${minutes} minutes ago`;
		} else if (diffInSeconds < 86400) {
			const hours = Math.floor(diffInSeconds / 3600);
			return `${hours} hours ago`;
		} else {
			const days = Math.floor(diffInSeconds / 86400);
			return `${days} days ago`;
		}
	}

	export let searchTerm = '';
	$: filteredUsers = searchTerm
		? data.users.filter(
				(user) =>
					`${user.firstName} ${user.lastName}`.toLowerCase().includes(searchTerm.toLowerCase()) ||
					user.email.toLowerCase().includes(searchTerm.toLowerCase()) ||
					user.phoneNumber.includes(searchTerm) ||
					(user.company?.toLowerCase().includes(searchTerm.toLowerCase()) ?? false) ||
					user.roleName.toLowerCase().includes(searchTerm.toLowerCase())
			)
		: data.users;
	$: sortedUsers = [...filteredUsers].sort((a, b) => {
		if (sortOption === '1') {
			return new Date(b.updatedAt).getTime() - new Date(a.updatedAt).getTime();
		}
		if (sortOption === '2') {
			return `${a.firstName} ${a.lastName}`.localeCompare(`${b.firstName} ${b.lastName}`);
		}
		if (sortOption === '3') {
			return (a.company ?? '').localeCompare(b.company ?? '');
		}
		if (sortOption === '4') {
			return a.roleName.localeCompare(b.roleName);
		}
		if (sortOption === '5') {
			return a.email.localeCompare(b.email);
		}
		return 0;
	});

	let sortOption = '1';
	function getSafeId(user: any, index: number): string | number {
		return user.id ?? `${user.email}-${index}`;
	}
	function formatDate(dateString: string | Date): string {
		const date = new Date(dateString);
		const day = date.getDate().toString().padStart(2, '0');
		const month = (date.getMonth() + 1).toString().padStart(2, '0');
		const year = date.getFullYear();
		return `${day}/${month}/${year}`;
	}
	let imageError = false;
	interface User {
		firstName: string;
		middleName: string;
		lastName: string;
		profilePictureUrl?: string | null;
	}
	function getInitials(user: User) {
		const first = user.firstName?.charAt(0) ?? '';
		const last = user.lastName?.charAt(0) ?? '';
		return `${first}${last}`.toUpperCase();
	}
</script>

<SubHeader mode="users" on:sortChange={(e) => (sortOption = e.detail)} />

<div class="flex h-full flex-col overflow-hidden">
	{#if $page.status === 500}
		<div class="py-4 text-red-500">Error</div>
	{:else if !data?.users}
		<div
			class="flex h-full flex-col items-center justify-center py-4 text-center font-medium text-gray-800"
		>
			<Loading />
			Loading Users...
		</div>
	{:else}
		<div class="flex w-full flex-1 flex-col overflow-hidden">
			<!-- Contenedor de tabla con scroll vertical -->
			<div class="flex h-full w-full flex-col overflow-hidden p-3">
				<!-- Encabezado fijo -->
				<div class="sticky top-0 z-10 mr-4 min-w-full border-b border-gray-200 bg-white">
					<div class="overflow-x-auto">
						<table class="w-full">
							<thead>
								<tr>
									<th class="w-[20px] px-4 py-3 text-left text-sm font-semibold text-gray-700">
										<CustomCheckbox bind:checked={selectAll} onChange={toggleSelectAll} />
									</th>
									<th class="w-[200px] px-4 py-3 text-left text-sm font-semibold text-gray-700">
										Contact
									</th>
									<th class="w-[150px] px-4 py-3 text-left text-sm font-semibold text-gray-700">
										Company
									</th>
									<th class="w-[120px] px-4 py-3 text-left text-sm font-semibold text-gray-700">
										Role
									</th>
									<th class="w-[200px] px-4 py-3 text-left text-sm font-semibold text-gray-700">
										Email
									</th>
									<th class="w-[120px] px-4 py-3 text-left text-sm font-semibold text-gray-700">
										Phone Number
									</th>
									<th class="w-[120px] px-4 py-3 text-left text-sm font-semibold text-gray-700">
										Created At
									</th>
									<th class="w-[120px] px-4 py-3 text-left text-sm font-semibold text-gray-700">
										Updated
									</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>

				<!-- Cuerpo de la tabla con scroll -->
				<div class="mb-32 flex-1 overflow-x-hidden overflow-y-auto">
					<table class="mt-3 w-full divide-y divide-gray-200">
						<tbody class="divide-y divide-gray-200 bg-white">
							{#each sortedUsers as user, index (getSafeId(user, index))}
								<tr
									class="cursor-pointer transition hover:scale-[1.012] hover:bg-gray-50"
									on:click={() => {
										console.log('Clicked user:', user);
										window.location.href = `/users/${user.id}`;
									}}
								>
									<td class="w-[40px] px-4 py-3 text-sm whitespace-nowrap text-gray-900">
										<CustomCheckbox
											checked={selectedUsers.includes(user.id)}
											onChange={(value) => toggleSelectUser(user.id, value)}
										/>
									</td>
									<td
										class="w-[200px] px-4 py-3 text-sm font-medium whitespace-nowrap text-gray-900"
									>
										<div class="flex items-center gap-2">
											{#if user.profilePictureUrl && !imageError}
												<img
													src={`${import.meta.env.VITE_API_URL}/uploads/profiles/${user.profilePictureUrl}`}
													alt="Foto de perfil"
													class="h-8 w-8 rounded-full object-cover"
													on:error={() => (imageError = true)}
												/>
											{:else}
												<div
													class="flex h-8 w-8 items-center justify-center rounded-full bg-gray-300 text-sm font-bold text-white"
												>
													{getInitials(user)}
												</div>
											{/if}

											<span>{user.firstName} {user.lastName}</span>
										</div>
									</td>
									<td class="w-[150px] px-4 py-3 text-sm whitespace-nowrap text-gray-900">
										{user.company || '-'}
									</td>
									<td class="w-[120px] px-4 py-3 text-sm whitespace-nowrap text-gray-900">
										<span
											class="inline-flex rounded-full px-2 text-xs leading-5 font-semibold
											{user.roleName === 'Admin'
												? 'bg-purple-100 text-purple-800'
												: user.roleName === 'Manager'
													? 'bg-blue-100 text-blue-800'
													: user.roleName === 'Supervisor'
														? 'bg-green-100 text-green-800'
														: user.roleName === 'Agent'
															? 'bg-yellow-100 text-yellow-800'
															: 'bg-gray-100 text-gray-800'}"
										>
											{user.roleName}
										</span>
									</td>
									<td class="w-[200px] px-4 py-3 text-sm whitespace-nowrap text-gray-900">
										{user.email}
									</td>
									<td class="w-[120px] px-4 py-3 text-sm whitespace-nowrap text-gray-900">
										{user.phoneNumber}
									</td>
									<td class="w-[120px] px-4 py-3 text-sm whitespace-nowrap text-gray-900">
										{formatDate(user.createdAt)}
									</td>
									<td class="w-[120px] px-4 py-3 text-sm whitespace-nowrap text-gray-900">
										{formatRelativeTime(user.updatedAt)}
									</td>
								</tr>
							{/each}
						</tbody>
					</table>
				</div>
			</div>
		</div>
	{/if}
</div>
