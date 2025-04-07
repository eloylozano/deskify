<script>
    let tickets = [];
    let isLoading = true;
    let error = null;
    let filters = {
      agent: "Todos",
      status: "",
      category: "",
      created: "Último mes"
    };
  
    // Función para obtener tickets desde tu API Spring
    async function fetchTickets() {
      try {
        const response = await fetch('http://tu-api-spring/tickets');
        if (!response.ok) throw new Error('Error al cargar tickets');
        tickets = await response.json();
      } catch (err) {
        error = err.message;
      } finally {
        isLoading = false;
      }
    }
  
    // Llama a la API al cargar el componente
    fetchTickets();
  
    // Función para aplicar filtros
    function applyFilters() {
      fetchTickets(); // En una implementación real, pasarías los filtros como query params
    }
  </script>
  
  <div class="container mx-auto px-4 py-6">
    <h1 class="text-2xl font-bold mb-6">Todos los Tickets - deskify</h1>
  
    <!-- Filtros -->
    <div class="flex items-center gap-4 mb-4">
      <label class="flex items-center gap-2">
        <input type="checkbox" class="custom-checkbox" />
        <span>Ordenar por: <strong>Últ. modificado</strong></span>
      </label>
  
      <select class="filter-select" bind:value={filters.agent}>
        <option value="Todos">Agente: Todos</option>
        <option value="Carlos">Carlos</option>
        <option value="Jorge">Jorge</option>
        <!-- Más agentes -->
      </select>
  
      <select class="filter-select" bind:value={filters.status}>
        <option value="">Estado: Cualquiera</option>
        <option value="Pendiente">Pendiente</option>
        <option value="Abierto">Abierto</option>
        <!-- Más estados -->
      </select>
  
      <select class="filter-select" bind:value={filters.category}>
        <option value="">Categoría: Todas</option>
        <option value="Soporte técnico">Soporte técnico</option>
        <option value="Facturación">Facturación</option>
        <!-- Más categorías -->
      </select>
  
      <select class="filter-select" bind:value={filters.created}>
        <option value="Último mes">Creado: Último mes</option>
        <option value="Última semana">Última semana</option>
        <option value="Últimas 24h">Últimas 24h</option>
      </select>
  
      <button 
        on:click={applyFilters}
        class="bg-[#01c883] text-white px-4 py-1 rounded hover:bg-[#01a873] transition"
      >
        Aplicar
      </button>
    </div>
  
    {#if isLoading}
      <div class="text-center py-8">Cargando tickets...</div>
    {:else if error}
      <div class="text-red-500 py-8">{error}</div>
    {:else}
      <!-- Tabla de tickets -->
      <div class="overflow-x-auto">
        <table class="min-w-full bg-white rounded-lg overflow-hidden">
          <thead class="bg-gray-100">
            <tr>
              <th class="px-4 py-3 text-left">Creador</th>
              <th class="px-4 py-3 text-left">Título</th>
              <th class="px-4 py-3 text-left">Prioridad</th>
              <th class="px-4 py-3 text-left">Estado</th>
              <th class="px-4 py-3 text-left">Categoría</th>
              <th class="px-4 py-3 text-left">Agente</th>
              <th class="px-4 py-3 text-left">Creado</th>
              <th class="px-4 py-3 text-left">Modificado</th>
            </tr>
          </thead>
          <tbody class="divide-y divide-gray-200">
            {#each tickets as ticket}
              <tr class="hover:bg-gray-50 transition">
                <td class="px-4 py-3">{ticket.creator}</td>
                <td class="px-4 py-3 font-medium">
                  <a href="#" class="text-[#01c883] hover:underline">{ticket.title}</a>
                </td>
                <td class="px-4 py-3">
                  <span class={`priority-${ticket.priority.toLowerCase()} px-2 py-1 rounded-full text-xs`}>
                    {ticket.priority}
                  </span>
                </td>
                <td class="px-4 py-3">{ticket.status}</td>
                <td class="px-4 py-3">{ticket.category}</td>
                <td class="px-4 py-3">{ticket.agent}</td>
                <td class="px-4 py-3">{ticket.created}</td>
                <td class="px-4 py-3">{ticket.modified}</td>
              </tr>
            {/each}
          </tbody>
        </table>
      </div>
    {/if}
  </div>
  
  <style>
    .custom-checkbox {
      appearance: none;
      width: 16px;
      height: 16px;
      border: 2px solid #ccc;
      border-radius: 3px;
      position: relative;
      cursor: pointer;
    }
    
    .custom-checkbox:checked {
      background-color: #01c883;
      border-color: #01c883;
    }
    
    .custom-checkbox:checked::after {
      content: '✓';
      position: absolute;
      color: white;
      font-size: 10px;
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);
    }
    
    .filter-select {
      border: 1px solid #ddd;
      border-radius: 4px;
      padding: 4px 8px;
      background-color: white;
      cursor: pointer;
    }
    
    .priority-urgente {
      background-color: #ff4444;
      color: white;
    }
    
    .priority-alta {
      background-color: #ffbb33;
      color: white;
    }
    
    .priority-media {
      background-color: #33b5e5;
      color: white;
    }
    
    .priority-baja {
      background-color: #00c851;
      color: white;
    }
  </style>