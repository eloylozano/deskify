// src/routes/+page.server.ts
import type { PageServerLoad } from './$types';
import { redirect } from '@sveltejs/kit';

interface UserSession {
    id: number;
    email: string;
    firstName: string;
    lastName: string;
    role: {
        id: number;
        name: string;
    };
    profilePictureUrl?: string;
}

export const load: PageServerLoad = async ({ cookies, locals, fetch }) => {
    const token = cookies.get('auth-token');
    
    // Si no hay token, retornar user null
    if (!token) {
        return { user: null };
    }

    try {
        const apiResponse = await fetch('http://localhost:8080/api/auth/me', {
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json'
            }
        });

        if (!apiResponse.ok) {
            // Token inválido o expirado
            cookies.delete('auth-token', { path: '/' });
            return { user: null };
        }

        const userData = await apiResponse.json();

        // Mapear los datos a nuestra interfaz UserSession
        const sessionUser: UserSession = {
            id: userData.id,
            email: userData.email,
            firstName: userData.firstName,
            lastName: userData.lastName,
            role: userData.role,
            profilePictureUrl: userData.profilePictureUrl
        };

        // Asignar a locals para disponibilidad global
        locals.user = sessionUser;

        return {
            user: sessionUser
        };

    } catch (error) {
        console.error('Error fetching user data:', error);
        // En caso de error, limpiar la sesión
        cookies.delete('auth-token', { path: '/' });
        return { user: null };
    }
};