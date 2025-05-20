// src/hooks.server.ts
import { redirect } from '@sveltejs/kit';
import type { HandleServerError } from '@sveltejs/kit';

export const handleError: HandleServerError = ({ error, event }) => {
    const status = (error as any)?.status || 500;
    const message = (error as any)?.message || 'Ocurrió un error inesperado';

    // Registra el error para depuración
    console.error(`Error ${status} en ${event.url.pathname}:`, error);

    // Redirigir a la página de error correspondiente
    throw redirect(303, `/error?status=${status}&message=${encodeURIComponent(message)}`);
};