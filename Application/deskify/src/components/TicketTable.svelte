<script lang="ts">
  import { onMount } from 'svelte';
  import { getTickets } from '$lib/api/tickets';
  
  // Definimos la interfaz para el ticket
  interface Category {
    id?: number;
    name?: string;
  }

  interface Priority {
    id?: number;
    name?: string;
  }

  interface Ticket {
    id: number;
    title: string;
    description: string;
    category?: Category;
    priority?: Priority;
    createdAt: string | Date;
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
</script>

<div class="p-4">
  {#if isLoading}
    <div class="text-center py-4">Loading tickets...</div>
  {:else if error}
    <div class="text-red-500 py-4">Error: {error}</div>
  {:else}
    <div class="overflow-x-auto">
      <table class="min-w-full bg-white rounded-lg overflow-hidden">
        <thead class="bg-gray-100">
          <tr>
            <th class="py-2 px-4 text-left">ID</th>
            <th class="py-2 px-4 text-left">Title</th>
            <th class="py-2 px-4 text-left">Description</th>
            <th class="py-2 px-4 text-left">Category</th>
            <th class="py-2 px-4 text-left">Priority</th>
            <th class="py-2 px-4 text-left">Created At</th>
          </tr>
        </thead>
        <tbody>
          {#each tickets as ticket (ticket.id)}
            <tr class="border-t hover:bg-gray-50">
              <td class="py-2 px-4">{ticket.id}</td>
              <td class="py-2 px-4">{ticket.title}</td>
              <td class="py-2 px-4 max-w-xs truncate">{ticket.description}</td>
              <td class="py-2 px-4">{ticket.category?.name || 'N/A'}</td>
              <td class="py-2 px-4">{ticket.priority?.name || 'N/A'}</td>
              <td class="py-2 px-4">{new Date(ticket.createdAt).toLocaleString()}</td>
            </tr>
          {/each}
        </tbody>
      </table>
    </div>
  {/if}
</div>