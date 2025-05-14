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
    roleName?: string;
    error?: string;
}

export async function loginApi(email: string, password: string): Promise<LoginApiResponse> {
    const requestBody = { email, password };

    try {
        const response = await fetch(`${API_URL}/api/auth/login`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(requestBody)
        });

        const responseData = await response.json();

        if (!response.ok) {
            return {
                token: '',
                userId: 0,
                error: responseData.message || 'Login failed'
            };
        }

        // Transformar role a roleName si fuera necesario
        const roleName = typeof responseData.role === 'object' && responseData.role?.name
            ? responseData.role.name
            : responseData.role;

        return {
            ...responseData,
            roleName
        };

    } catch (error: any) {
        console.error('API login error:', error);
        return {
            token: '',
            userId: 0,
            error: 'Server unavailable or invalid response'
        };
    }
}
