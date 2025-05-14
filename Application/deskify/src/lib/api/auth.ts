// $lib/api/auth.ts
const API_URL = import.meta.env.VITE_API_URL || 'http://localhost:8080';

interface LoginApiResponse {
    token: string;
    userId: number;
    firstName?: string;
    lastName?: string;
    email?: string;
    phoneNumber?: string;
    company?: string;
    profilePictureUrl?: string;
    role?: any;
    error?: string;
}

export async function loginApi(email: string, password: string): Promise<LoginApiResponse> {
    try {
        // Asegúrate que el objeto es plano y simple
        const requestBody = {
            email: email,
            password: password
        };

        console.log("Enviando a API:", JSON.stringify(requestBody));

        const response = await fetch(`${API_URL}/api/auth/login`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(requestBody) // Objeto simple
        });

        if (!response.ok) {
            const errorData = await response.json();
            throw new Error(errorData.message || 'Error');
        }

        return await response.json();
    } catch (error) {
        console.error('API Error:', error);
        throw error;
    }
}