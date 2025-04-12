<script lang="ts">
  import { onMount } from 'svelte';
  import { getTickets } from '$lib/api/tickets';

  interface User {
    id?: number;
    name?: string;
  }

  interface Category {
    id?: number;
    name?: string;
  }

  interface Priority {
    id?: number;
    name?: string;
  }

  interface Status {
    id?: number;
    name?: string;
  }

  interface Agent {
    id?: number;
    name?: string;
  }

  interface Ticket {
    id: number;
    title: string;
    description: string;
    createdBy?: User;
    priority?: Priority;
    status?: Status;
    category?: Category;
    agent?: Agent;
    createdAt: string | Date;
    updatedAt?: string | Date;
  }

  let tickets: Ticket[] = [];
  let isLoading: boolean = true;
  let error: string | null = null;

  onMount(async () => {
    try {
      tickets = await getTickets();
    } catch (e: unknown) {
      error = e instanceof Error ? e.message : 'Unknown error occurred';
    } finally {
      isLoading = false;
    }
  });

  // Función para formatear la fecha relativa
  function formatRelativeTime(dateString: string | Date): string {
    const date = new Date(dateString);
    const now = new Date();
    const diffInSeconds = Math.floor((now.getTime() - date.getTime()) / 1000);
    
    if (diffInSeconds < 60) {
      return 'hace unos segundos';
    } else if (diffInSeconds < 3600) {
      const minutes = Math.floor(diffInSeconds / 60);
      return `hace ${minutes}m`;
    } else if (diffInSeconds < 86400) {
      const hours = Math.floor(diffInSeconds / 3600);
      return `hace ${hours}h`;
    } else {
      const days = Math.floor(diffInSeconds / 86400);
      return `hace ${days}d`;
    }
  }
</script>

<div class="flex h-full flex-col overflow-hidden">
  {#if isLoading}
    <div class="py-4 text-center">Cargando tickets...</div>
  {:else if error}
    <div class="py-4 text-red-500">Error: {error}</div>
  {:else}
    <div class="flex flex-1 flex-col overflow-hidden">
      <!-- Contenedor de tabla con scroll vertical -->
      <div class="flex flex-col h-full overflow-hidden">
        <!-- Encabezado fijo -->
        <div class="sticky top-0 z-10 bg-white border-b border-gray-200">
          <table class="min-w-full">
            <thead>
              <tr>
                <th class="w-[5%] px-4 py-3 text-left text-sm font-semibold text-gray-700">Creador</th>
                <th class="w-[18%] px-4 py-3 text-left text-sm font-semibold text-gray-700">Título</th>
                <th class="w-[10%] px-4 py-3 text-left text-sm font-semibold text-gray-700">Prioridad</th>
                <th class="w-[10%] px-4 py-3 text-left text-sm font-semibold text-gray-700">Estado</th>
                <th class="w-[15%] px-4 py-3 text-left text-sm font-semibold text-gray-700">Categoría</th>
                <th class="w-[15%] px-4 py-3 text-left text-sm font-semibold text-gray-700">Agente</th>
                <th class="w-[10%] px-4 py-3 text-left text-sm font-semibold text-gray-700">Creado</th>
                <th class="w-[10%] px-4 py-3 text-left text-sm font-semibold text-gray-700">Modificado</th>
              </tr>
            </thead>
          </table>
        </div>

        <!-- Cuerpo de la tabla con scroll -->
        <div class="flex-1 overflow-y-auto">
          <table class="min-w-full divide-y divide-gray-200">
            <tbody class="divide-y divide-gray-200 bg-white">
              {#each tickets as ticket (ticket.id)}
                <tr class="hover:bg-gray-50">
                  <td class="w-[5%] px-4 py-3 text-sm whitespace-nowrap text-gray-900">
                    {ticket.createdBy?.name || 'N/A'}
                  </td>
                  <td class="w-[18%] px-4 py-3 text-sm font-medium whitespace-nowrap text-gray-900">
                    <span class="truncate max-w-[180px] block">{ticket.title}</span>
                  </td>
                  <td class="w-[10%] px-4 py-3 text-sm whitespace-nowrap text-gray-900">
                    <span
                      class="inline-flex rounded-full px-2 text-xs leading-5 font-semibold
                      {ticket.priority?.name === 'Alta'
                        ? 'bg-red-100 text-red-800'
                        : ticket.priority?.name === 'Media'
                          ? 'bg-yellow-100 text-yellow-800'
                          : 'bg-green-100 text-green-800'}"
                    >
                      {ticket.priority?.name || 'N/A'}
                    </span>
                  </td>
                  <td class="w-[10%] px-4 py-3 text-sm whitespace-nowrap text-gray-900">
                    <span
                      class="inline-flex rounded-full px-2 text-xs leading-5 font-semibold
                      {ticket.status?.name === 'Abierto'
                        ? 'bg-blue-100 text-blue-800'
                        : ticket.status?.name === 'En progreso'
                          ? 'bg-purple-100 text-purple-800'
                          : 'bg-gray-100 text-gray-800'}"
                    >
                      {ticket.status?.name || 'N/A'}
                    </span>
                  </td>
                  <td class="w-[15%] px-4 py-3 text-sm whitespace-nowrap text-gray-900">
                    {ticket.category?.name || 'N/A'}
                  </td>
                  <td class="w-[15%] px-4 py-3 text-sm whitespace-nowrap text-gray-900">
                    {ticket.agent?.name || 'Sin asignar'}
                  </td>
                  <td class="w-[10%] px-4 py-3 text-sm whitespace-nowrap text-gray-900">
                    {formatRelativeTime(ticket.createdAt)}
                  </td>
                  <td class="w-[10%] px-4 py-3 text-sm whitespace-nowrap text-gray-900">
                    {ticket.updatedAt ? formatRelativeTime(ticket.updatedAt) : 'No modificado'}
                  </td>
                </tr>
              {/each}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  {/if}
</div>