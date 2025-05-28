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
            cookies.delete('auth-token', { path: '/' });
            return { user: null };
        }

        const userData = await apiResponse.json();

        const sessionUser: UserSession = {
            id: userData.id,
            email: userData.email,
            firstName: userData.firstName,
            lastName: userData.lastName,
            role: userData.role,
            profilePictureUrl: userData.profilePictureUrl
        };

        locals.user = sessionUser;

        return {
            user: sessionUser
        };

    } catch (error) {
        console.error('Error fetching user data:', error);
        cookies.delete('auth-token', { path: '/' });
        return { user: null };
    }
};