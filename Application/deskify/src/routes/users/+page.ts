// src/routes/users/+page.ts
import { error } from '@sveltejs/kit';

export async function load() {
	try {
		const res = await fetch('http://localhost:8080/user/all');
		if (!res.ok) throw new Error('No se pudieron obtener los usuarios');
		const users = await res.json();
		return { users };
	} catch (err) {
		throw error(500, 'Error cargando usuarios');
	}
}
