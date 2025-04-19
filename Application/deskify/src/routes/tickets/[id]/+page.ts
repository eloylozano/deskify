import { getTicketById } from '$lib/api/tickets';
import { error } from '@sveltejs/kit';

export async function load({ params }) {
    try {
        const ticket = await getTicketById(Number(params.id));
        
        if (!ticket) {
            throw error(404, 'Ticket no encontrado');
        }

        return {
            ticket
        };
    } catch (err) {
        throw error(500, 'Error al cargar el ticket');
    }
}
