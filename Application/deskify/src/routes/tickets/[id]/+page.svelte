<script lang="ts">
    import { getTicketById } from '$lib/api/tickets';
    import { error } from '@sveltejs/kit';

    // SvelteKit automáticamente pasa el params con el id
    export let data;

    // Formatear fechas
    function formatDate(dateString: string) {
        return new Date(dateString).toLocaleString();
    }
</script>

<div class="max-w-4xl mx-auto p-6">
    {#if data.ticket}
        <div class="bg-white rounded-lg shadow-md p-6">
            <!-- Encabezado del ticket -->
            <div class="flex justify-between items-start mb-6">
                <div>
                    <h1 class="text-2xl font-bold text-gray-800">Ticket #{data.ticket.id}: {data.ticket.title}</h1>
                    <p class="text-gray-600">Creado: {formatDate(data.ticket.createdAt)}</p>
                </div>
                <div class="flex items-center space-x-4">
                    <span class={`px-3 py-1 rounded-full text-sm font-medium 
                        ${data.ticket.priority.name === 'High' ? 'bg-yellow-100 text-yellow-800' :
                          data.ticket.priority.name === 'Urgent' ? 'bg-red-100 text-red-800' :
                          'bg-blue-100 text-blue-800'}`}>
                        {data.ticket.priority.name}
                    </span>
                    <span class={`px-3 py-1 rounded-full text-sm font-medium 
                        ${data.ticket.currentStatus.statusName === 'Open' ? 'bg-green-100 text-green-800' :
                          'bg-gray-100 text-gray-800'}`}>
                        {data.ticket.currentStatus.statusName}
                    </span>
                </div>
            </div>

            <!-- Información del ticket -->
            <div class="grid grid-cols-1 md:grid-cols-2 gap-6 mb-8">
                <div>
                    <h2 class="text-lg font-semibold mb-2">Categoría</h2>
                    <p>{data.ticket.category.name}</p>
                    <p class="text-gray-600 text-sm">{data.ticket.category.description}</p>
                </div>
                <div>
                    <h2 class="text-lg font-semibold mb-2">Prioridad</h2>
                    <p>{data.ticket.priority.name}</p>
                    <p class="text-gray-600 text-sm">{data.ticket.priority.description}</p>
                </div>
                <div>
                    <h2 class="text-lg font-semibold mb-2">Cliente</h2>
                    <div class="flex items-center space-x-3">
                        <img 
                            src={data.ticket.client.profilePictureUrl || '/default-profile.jpg'} 
                            alt={data.ticket.client.clientName}
                            class="w-10 h-10 rounded-full"
                        />
                        <div>
                            <p>{data.ticket.client.clientName}</p>
                            <p class="text-gray-600 text-sm">{data.ticket.client.mail}</p>
                        </div>
                    </div>
                </div>
                <div>
                    <h2 class="text-lg font-semibold mb-2">Agente asignado</h2>
                    <div class="flex items-center space-x-3">
                        <img 
                            src={data.ticket.agent.profilePictureUrl || '/default-profile.jpg'} 
                            alt={data.ticket.agent.agentName}
                            class="w-10 h-10 rounded-full"
                        />
                        <div>
                            <p>{data.ticket.agent.agentName}</p>
                            <p class="text-gray-600 text-sm">{data.ticket.agent.mail}</p>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Descripción -->
            <div class="mb-8">
                <h2 class="text-lg font-semibold mb-2">Descripción</h2>
                <div class="bg-gray-50 p-4 rounded-lg">
                    <p class="whitespace-pre-line">{data.ticket.description}</p>
                </div>
            </div>

            <!-- Comentarios -->
            <div>
                <h2 class="text-lg font-semibold mb-4">Comentarios ({data.ticket.comments.length})</h2>
                <div class="space-y-4">
                    {#each data.ticket.comments as comment}
                        <div class="border-l-4 border-blue-500 pl-4 py-2">
                            <div class="flex justify-between items-start mb-1">
                                <div class="flex items-center space-x-2">
                                    <img 
                                        src="/default-profile.jpg" 
                                        alt={comment.userFullName}
                                        class="w-8 h-8 rounded-full"
                                    />
                                    <span class="font-medium">{comment.userFullName}</span>
                                </div>
                                <span class="text-sm text-gray-500">{formatDate(comment.writtenOn)}</span>
                            </div>
                            <p class="text-gray-700 pl-10">{comment.commentText}</p>
                        </div>
                    {:else}
                        <p class="text-gray-500">No hay comentarios aún</p>
                    {/each}
                </div>
            </div>
        </div>
    {:else}
        <div class="text-center py-10">
            <p class="text-gray-500">No se pudo cargar la información del ticket</p>
        </div>
    {/if}
</div>