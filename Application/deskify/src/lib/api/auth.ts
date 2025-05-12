// $lib/api/auth.ts

const API_URL = import.meta.env.VITE_API_URL || 'http://localhost:8080';

interface LoginResponse {
    token: string;
    userId: number;
    error?: string;
}

interface LoginData {
    email: string;
    password: string;
}

/**
 * Inicia sesión y guarda el token en sessionStorage
 * @param {LoginData} credentials - Credenciales de usuario
 * @returns {Promise<LoginResponse>} Respuesta del servidor
 */
export async function login(credentials: LoginData): Promise<LoginResponse> {
    try {
        const response = await fetch(`${API_URL}/api/auth/login`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(credentials),
        });

        const data = await response.json();

        if (!response.ok) {
            return {
                token: '',
                userId: 0,
                error: data.message || 'Login Error'
            };
        }

        // Guardar token y userId en sessionStorage
        sessionStorage.setItem('authToken', data.token);
        sessionStorage.setItem('userId', data.userId.toString());

        return {
            token: data.token,
            userId: data.userId
        };

    } catch (error) {
        console.error('Login error:', error);
        return {
            token: '',
            userId: 0,
            error: 'Conexion Error'
        };
    }
}

/**
 * Verifica si hay un usuario autenticado
 * @returns {boolean} True si hay un token válido
 */
export function isAuthenticated() {
    if (typeof window === 'undefined') return false; // No estamos en el navegador

    const token = sessionStorage.getItem('authToken'); // Cambiado de 'token' a 'authToken'
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
// $lib/api/auth.ts
export function logout(): void {
    sessionStorage.removeItem('authToken');
    sessionStorage.removeItem('userId');
   
}