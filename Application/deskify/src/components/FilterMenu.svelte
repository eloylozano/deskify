<script lang="ts">
    import SubmitButton from './SubmitButton.svelte';
    
    // Opciones para los filtros
    export let statusOptions: any[] = [];
    export let priorityOptions: any[] = [];
    export let categoryOptions: any[] = [];
    export let agentOptions: any[] = [];
    
    // Valores seleccionados
    export let selectedStatus = { id: 0 };
    export let selectedPriority = { id: 0 };
    export let selectedCategory = { id: 0 };
    export let selectedAgent = { id: 0 };
    export let selectedDateRange = '';
    
    // Estados
    export let isLoadingAgents = false;
    export let agentError = '';
    
    // Función para manejar el filtrado
    export let handleFilter: () => void;
    
    // Función para limpiar filtros
    function clearFilters() {
      selectedStatus = { id: 0 };
      selectedPriority = { id: 0 };
      selectedCategory = { id: 0 };
      selectedAgent = { id: 0 };
      selectedDateRange = '';
      handleFilter();
    }
  </script>
  
  <div class="w-64 overflow-y-auto border-r border-gray-200 bg-white p-4">
    <h3 class="mb-4 text-lg font-medium">Filter Tickets</h3>
  
    <div class="space-y-4">
      <!-- Filtro por estado -->
      <div>
        <label class="mb-1 block text-sm font-medium text-gray-700">
          Status
          <select
            bind:value={selectedStatus.id}
            class="select-field w-full rounded border border-gray-300 px-3 py-2 text-sm"
          >
            <option value={0}>All statuses</option>
            {#each statusOptions as option}
              <option value={option.id}>{option.name}</option>
            {/each}
          </select>
        </label>
      </div>
  
      <!-- Filtro por prioridad -->
      <div>
        <label class="mb-1 block text-sm font-medium text-gray-700">
          Priority
          <select
            bind:value={selectedPriority.id}
            class="select-field w-full rounded border border-gray-300 px-3 py-2 text-sm"
          >
            <option value={0}>All priorities</option>
            {#each priorityOptions as option}
              <option value={option.id}>{option.name}</option>
            {/each}
          </select>
        </label>
      </div>
  
      <!-- Filtro por categoría -->
      <div>
        <label class="mb-1 block text-sm font-medium text-gray-700">
          Category
          <select
            bind:value={selectedCategory.id}
            class="select-field w-full rounded border border-gray-300 px-3 py-2 text-sm"
          >
            <option value={0}>All categories</option>
            {#each categoryOptions as option}
              <option value={option.id}>{option.name}</option>
            {/each}
          </select>
        </label>
      </div>
  
      <!-- Filtro por agente -->
      <div>
        <label class="mb-1 block text-sm font-medium text-gray-700">
          Assigned Agent
          {#if isLoadingAgents}
            <p class="text-sm text-gray-500">Loading agents...</p>
          {:else}
            <select
              bind:value={selectedAgent.id}
              class="select-field w-full rounded border border-gray-300 px-3 py-2 text-sm"
            >
              <option value={0}>All agents</option>
              {#each agentOptions as agent}
                <option value={agent.id}>{agent.fullName}</option>
              {/each}
            </select>
          {/if}
          {#if agentError}
            <p class="text-xs text-red-500">{agentError}</p>
          {/if}
        </label>
      </div>
  
      <!-- Filtro por rango de fechas -->
      <div>
        <label class="mb-1 block text-sm font-medium text-gray-700">
          Date Range
          <select
            bind:value={selectedDateRange}
            class="select-field w-full rounded border border-gray-300 px-3 py-2 text-sm"
          >
            <option value="">All time</option>
            <option value="today">Today</option>
            <option value="week">This week</option>
            <option value="month">This month</option>
            <option value="year">This year</option>
          </select>
        </label>
      </div>
  
      <!-- Botones de acción -->
      <div class="flex space-x-2 pt-2">
        <SubmitButton
          text="Apply Filters"
          additionalClass="w-full justify-center"
          on:click={handleFilter}
        />
        <button
          on:click={clearFilters}
          class="w-full rounded-md border border-gray-300 bg-white px-4 py-2 text-sm font-medium text-gray-700 shadow-sm hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-emerald-500"
        >
          Clear
        </button>
      </div>
    </div>
  </div>
  
  <style>
    .select-field {
      width: 100%;
      padding: 8px;
      border: 1px solid #e5e7eb;
      border-radius: 6px;
      background-color: white;
      transition: all 0.2s ease;
      appearance: none;
      -webkit-appearance: none;
      -moz-appearance: none;
    }
  
    .select-field:focus {
      border-color: #10b981;
      outline: none;
      box-shadow: 0 0 0 3px rgba(16, 185, 129, 0.1);
    }
  </style>