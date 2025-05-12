// $lib/api/register.ts

import { goto } from "$app/navigation";

const API_URL = import.meta.env.VITE_API_URL || 'http://localhost:8080';

interface RegisterResponse {
    message?: string;
    error?: string;
}

interface RegisterData {
    email: string;
    password: string;
}

/**
 * Registra un nuevo usuario en el sistema
 * @param {RegisterData} userData - Datos del usuario a registrar
 * @returns {Promise<RegisterResponse>} Respuesta del servidor
 */
export async function registerUser(userData: RegisterData): Promise<RegisterResponse> {
    try {
        const response = await fetch(`${API_URL}/api/auth/register`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                email: userData.email,
                password: userData.password
            }),
        });

        if (!response.ok) {
            // Si la respuesta no es OK, intentamos parsear el mensaje de error
            const errorData = await response.json().catch(() => ({}));
            return {
                error: errorData.message || 'Registration error. Please try again.'
            };
        }

        // Registro exitoso
        return {
            message: 'User successfully registered'
        };


    } catch (error) {
        console.error('Error en registerUser:', error);
        return {
            error: 'Connection error. Please check your internet connection.'
        };
    }
}

/**
 * Verifica si un email ya está registrado
 * @param {string} email - Email a verificar
 * @returns {Promise<{exists: boolean, error?: string}>} 
 */
export async function checkEmailExists(email: string): Promise<{exists: boolean, error?: string}> {
    try {
        const response = await fetch(`${API_URL}/api/auth/check-email?email=${encodeURIComponent(email)}`);
        
        if (!response.ok) {
            throw new Error('Error verifying email');
        }

        const data = await response.json();
        return { exists: data.exists };

    } catch (error) {
        console.error('Error in checkEmailExists:', error);
        return {
            exists: false,
            error: 'Email could not be verified. Please try again.'
        };
    }
}

// Tipos para TypeScript
export interface UserRegistration {
    email: string;
    password: string;
    confirmPassword: string;
}