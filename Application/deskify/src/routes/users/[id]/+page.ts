import { error } from '@sveltejs/kit';

export async function load({ params, fetch }) {
    try {
        const [userResponse, statsResponse] = await Promise.all([
            fetch(`http://localhost:8080/user/${params.id}`),
            fetch(`http://localhost:8080/user/${params.id}/stats`)
        ]);

        if (!userResponse.ok) throw error(userResponse.status, 'Usuario no encontrado');
        if (!statsResponse.ok) throw error(statsResponse.status, 'No se pudieron obtener stats');

        return {
            user: await userResponse.json(),
            stats: await statsResponse.json() 
        };
    } catch (err) {
        throw error(500, 'Error al cargar datos');
    }
}
