// $lib/api/login.ts

import { loginApi } from './auth';
import { clearUser, fetchUser } from "$lib/stores/user";

interface LoginResponse {
    token: string;
    userId: number;
    error?: string;
}

/**
 * Inicia sesión y guarda el token en sessionStorage
 * @param {string} email - El correo del usuario
 * @param {string} password - La contraseña del usuario
 * @returns {Promise<LoginResponse>} Respuesta del servidor
 */
export async function login(email: string, password: string): Promise<LoginResponse> {
    try {
        const data = await loginApi(email, password);
        console.log(data);
        if (data.error) {
            return {
                token: '',
                userId: 0,
                error: data.error || 'Login Error'
            };
        }

        // Guardar token y datos del usuario en sessionStorage
        sessionStorage.setItem('authToken', data.token);
        sessionStorage.setItem('userId', data.userId.toString());

        await fetchUser();
        console.log("Login API response:", data);

        return {
            token: data.token,
            userId: data.userId
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


/**
 * Verifica si hay un usuario autenticado
 * @returns {boolean} True si hay un token válido
 */
export function isAuthenticated() {
    if (typeof window === 'undefined') return false; // No estamos en el navegador

    const token = sessionStorage.getItem('authToken');
    return !!token;
}

/**
 * Obtiene el token de autenticación
 * @returns {string | null} Token de autenticación
 */
export function getAuthToken(): string | null {
    return sessionStorage.getItem('authToken');
}

/**
 * Obtiene el ID del usuario autenticado
 * @returns {number | null} ID del usuario
 */
export function getUserId(): number | null {
    const userId = sessionStorage.getItem('userId');
    return userId ? parseInt(userId) : null;
}

/**
 * Cierra la sesión del usuario
 */
export function logout(): void {
    sessionStorage.removeItem('authToken');
    sessionStorage.removeItem('userId');
    clearUser();
}
