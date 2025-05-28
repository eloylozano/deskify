// $lib/api/login.ts

import { loginApi } from './auth';
import { clearUser, fetchUser } from "$lib/stores/user";

interface LoginResponse {
    token: string;
    userId: number;
    firstName?: string;
    lastName?: string;
    email?: string;
    phoneNumber?: string;
    company?: string;
    profilePictureUrl?: string;
    roleName?: string;
    error?: string;
}

export async function login(email: string, password: string): Promise<LoginResponse> {
    try {
        const data = await loginApi(email, password);
        if (data.error) {
            return {
                token: '',
                userId: 0,
                error: data.error || 'Login Error'
            };
        }

        // Guardar datos del usuario en sessionStorage
        sessionStorage.setItem('authToken', data.token);
        sessionStorage.setItem('userId', data.userId.toString());
        sessionStorage.setItem('firstName', data.firstName ?? 'Sin nombre');
        sessionStorage.setItem('lastName', data.lastName ?? 'Sin apellido');
        sessionStorage.setItem('email', data.email ?? 'Sin email');
        sessionStorage.setItem('phoneNumber', data.phoneNumber ?? 'Sin teléfono');
        sessionStorage.setItem('company', data.company ?? 'Sin empresa');
        sessionStorage.setItem('profilePictureUrl', data.profilePictureUrl ?? 'default.jpg');
        sessionStorage.setItem('roleName', data.roleName ?? 'Sin rol');



        await fetchUser();

        return {
            token: data.token,
            userId: data.userId,
            firstName: data.firstName,
            lastName: data.lastName,
            email: data.email,
            phoneNumber: data.phoneNumber,
            company: data.company,
            profilePictureUrl: data.profilePictureUrl,
            roleName: data.roleName
        };

    } catch (error) {
        console.error('Login error:', error);
        return {
            token: '',
            userId: 0,
            error: 'Invalid Credentials'
        };
    }
}

export function isAuthenticated(): boolean {
    if (typeof window === 'undefined') return false;
    const token = sessionStorage.getItem('authToken');
    return !!token;
}

export function getAuthToken(): string | null {
    return sessionStorage.getItem('authToken');
}

export function getUserId(): number | null {
    const userId = sessionStorage.getItem('userId');
    return userId ? parseInt(userId) : null;
}


export function getCurrentUser() {
    return {
        token: sessionStorage.getItem('authToken'),
        userId: parseInt(sessionStorage.getItem('userId') || '0'),
        firstName: sessionStorage.getItem('firstName'),
        lastName: sessionStorage.getItem('lastName'),
        email: sessionStorage.getItem('email'),
        phoneNumber: sessionStorage.getItem('phoneNumber'),
        company: sessionStorage.getItem('company'),
        profilePictureUrl: sessionStorage.getItem('profilePictureUrl'),
        roleName: sessionStorage.getItem('roleName')
    };
}


export function logout(): void {
    sessionStorage.removeItem('authToken');
    sessionStorage.removeItem('userId');
    sessionStorage.removeItem('firstName');
    sessionStorage.removeItem('lastName');
    sessionStorage.removeItem('email');
    sessionStorage.removeItem('phoneNumber');
    sessionStorage.removeItem('company');
    sessionStorage.removeItem('profilePictureUrl');
    sessionStorage.removeItem('roleName');
    clearUser();
}
