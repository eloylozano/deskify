// $lib/api/users.ts


const API_URL = import.meta.env.VITE_API_URL;

export async function updateUser(id: number, userData: Record<string, any>) {
    try {
        const res = await fetch(`${API_URL}/user/update/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                id: userData.id,
                profilePictureUrl: userData.profilePictureUrl,
                firstName: userData.firstName,
                middleName: userData.middleName,
                lastName: userData.lastName,
                phoneNumber: userData.phoneNumber,
                email: userData.email,
                company: userData.company,
                roleName: userData.roleName,
                activeSubscription: userData.activeSubscription, // Si es necesario
                // Agrega aquí otros campos que quieras actualizar
            }),
        });

        if (!res.ok) {
            throw new Error('Error updating user');
        }

        return await res.json();
    } catch (err) {
        console.error(err);
        throw err;
    }
}



export async function getAgents(): Promise<Array<{
    id: number;
    firstName: string;
    middleName: string | null;
    lastName: string;
    email: string;
}>> {
    const response = await fetch(`${API_URL}/user/agents`);
    
    if (!response.ok) {
        throw new Error('Failed to fetch agents');
    }
    
    return await response.json();
}