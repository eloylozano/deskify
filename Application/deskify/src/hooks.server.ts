// src/hooks.server.ts
import { redirect } from '@sveltejs/kit';
import type { HandleServerError } from '@sveltejs/kit';

export const handleError: HandleServerError = ({ error, event }) => {
    const status = (error as any)?.status || 500;
    const message = (error as any)?.message || 'Ocurrió un error inesperado';

    throw redirect(303, `/error?status=${status}&message=${encodeURIComponent(message)}`);
};