// $lib/api/users.ts


const API_URL = import.meta.env.VITE_API_URL;

export async function updateUser(id: number, userData: Record<string, any>) {
	try {
		const res = await fetch(`${API_URL}/user/update/${id}`, {
			method: 'PUT',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(userData)
		});

		if (!res.ok) {
			throw new Error('Error updating user');
		}

		return await res.json();
	} catch (err) {
		console.error(err);
		throw err;
	}
}
