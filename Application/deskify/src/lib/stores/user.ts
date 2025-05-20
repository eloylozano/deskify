// src/lib/stores/user.ts
import { writable } from 'svelte/store';
import { browser } from '$app/environment';

export interface User {
    id: number;
    firstName: string;
    middleName?: string;
    lastName: string;
    phoneNumber?: string;
    email: string;
    company?: string;
    profilePictureUrl?: string;
    role: {
        id: number;
        name: string;
    };
    createdAt: string;
    updatedAt?: string;
}

// Función segura para obtener el token
const getToken = () => {
    if (!browser) return null;
    return localStorage.getItem("token") || sessionStorage.getItem("authToken");
};

// Inicializar con datos del sessionStorage si existen
const initialUser = browser ? JSON.parse(sessionStorage.getItem('userData') || 'null') : null;

export const user = writable<User | null>(initialUser);

export async function fetchUser(): Promise<User | null> {
    if (!browser) return null;

    const token = getToken();

    if (!token) {
        console.error("Token no encontrado.");
        user.set(null);
        return null;
    }

    try {
        const res = await fetch(`http://localhost:8080/api/auth/me`, {
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json'
            }
        });

        if (!res.ok) {
            console.error('Failed to fetch user data:', res.statusText);
            user.set(null);
            sessionStorage.removeItem('userData');
            return null;
        }
        const data = await res.json();
        user.set(data);
        sessionStorage.setItem('userData', JSON.stringify(data));
        return data;
    } catch (e) {
        console.error('Failed to fetch user', e);
        user.set(null);
        sessionStorage.removeItem('userData');
        return null;
    }
}

export function clearUser() {
    user.set(null);
    if (browser) {
        sessionStorage.removeItem('userData');
    }
}

// Función para obtener el ID del usuario actual
export function getUserId(): number | null {
    let userId: number | null = null;
    if (browser) {
        const userData = sessionStorage.getItem('userData');
        if (userData) {
            const user = JSON.parse(userData);
            userId = user?.id || null;
        }
    }
    return userId;
}