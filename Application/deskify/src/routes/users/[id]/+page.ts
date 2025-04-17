import { error } from '@sveltejs/kit';

export async function load({ params }) {
    try {
        const response = await fetch(`http://localhost:8080/user/${params.id}`);
        
        if (!response.ok) {
            throw error(response.status, 'Usuario no encontrado');
        }

        const user = await response.json();

        return {
            user
        };
    } catch (err) {
        throw error(500, 'Error al cargar los datos del usuario');
    }
}