<script lang="ts">
    export let selectedStatus: string = '';
    export let selectedPriority: string = '';
    export let selectedCategory: string = '';
    export let selectedAgent: string = '';
    
    export let onStatusChange: (value: string) => void;
    export let onPriorityChange: (value: string) => void;
    export let onCategoryChange: (value: string) => void;
    export let onAgentChange: (value: string) => void;
    
    const statusOptions = [
        { value: '', label: 'All Statuses' },
        { value: 'Open', label: 'Open' },
        { value: 'In Progress', label: 'In Progress' },
        { value: 'Pending', label: 'Pending' },
        { value: 'Closed', label: 'Closed' },
        { value: 'Solved', label: 'Solved' }
    ];
    
    const priorityOptions = [
        { value: '', label: 'All Priorities' },
        { value: 'Urgent', label: 'Urgent' },
        { value: 'High', label: 'High' },
        { value: 'Medium', label: 'Medium' },
        { value: 'Low', label: 'Low' }
    ];
    
    const categoryOptions = [
        { value: '', label: 'All Categories' },
        { value: 'Bug', label: 'Bug' },
        { value: 'Feature Request', label: 'Feature Request' },
        { value: 'Support', label: 'Support' },
        { value: 'Other', label: 'Other' }
    ];
    
    const agentOptions = [
        { value: '', label: 'All Agents' },
        { value: '1', label: 'John Doe' },
        { value: '2', label: 'Jane Smith' },
        { value: '3', label: 'Mike Johnson' }
    ];
</script>

<div class="space-y-4">
    <!-- Filtro de Estado -->
    <div>
        <label class="block text-sm font-medium text-gray-700 mb-1">Status</label>
        <select
            class="w-full rounded-md border-gray-300 shadow-sm focus:border-emerald-500 focus:ring-emerald-500 text-sm"
            bind:value={selectedStatus}
            on:change={() => onStatusChange(selectedStatus)}
        >
            {#each statusOptions as option}
                <option value={option.value}>{option.label}</option>
            {/each}
        </select>
    </div>
    
    <!-- Filtro de Prioridad -->
    <div>
        <label class="block text-sm font-medium text-gray-700 mb-1">Priority</label>
        <select
            class="w-full rounded-md border-gray-300 shadow-sm focus:border-emerald-500 focus:ring-emerald-500 text-sm"
            bind:value={selectedPriority}
            on:change={() => onPriorityChange(selectedPriority)}
        >
            {#each priorityOptions as option}
                <option value={option.value}>{option.label}</option>
            {/each}
        </select>
    </div>
    
    <!-- Filtro de Categoría -->
    <div>
        <label class="block text-sm font-medium text-gray-700 mb-1">Category</label>
        <select
            class="w-full rounded-md border-gray-300 shadow-sm focus:border-emerald-500 focus:ring-emerald-500 text-sm"
            bind:value={selectedCategory}
            on:change={() => onCategoryChange(selectedCategory)}
        >
            {#each categoryOptions as option}
                <option value={option.value}>{option.label}</option>
            {/each}
        </select>
    </div>
    
    <!-- Filtro de Agente -->
    <div>
        <label class="block text-sm font-medium text-gray-700 mb-1">Assigned Agent</label>
        <select
            class="w-full rounded-md border-gray-300 shadow-sm focus:border-emerald-500 focus:ring-emerald-500 text-sm"
            bind:value={selectedAgent}
            on:change={() => onAgentChange(selectedAgent)}
        >
            {#each agentOptions as option}
                <option value={option.value}>{option.label}</option>
            {/each}
        </select>
    </div>
    
    <!-- Botón para limpiar filtros -->
    <button
        class="w-full mt-4 py-2 px-4 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-emerald-600 hover:bg-emerald-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-emerald-500"
        on:click={() => {
            selectedStatus = '';
            selectedPriority = '';
            selectedCategory = '';
            selectedAgent = '';
            onStatusChange('');
            onPriorityChange('');
            onCategoryChange('');
            onAgentChange('');
        }}
    >
        Clear All Filters
    </button>
</div>

<style>
    select {
        background-image: url("data:image/svg+xml,%3csvg xmlns='http://www.w3.org/2000/svg' fill='none' viewBox='0 0 20 20'%3e%3cpath stroke='%236b7280' stroke-linecap='round' stroke-linejoin='round' stroke-width='1.5' d='M6 8l4 4 4-4'/%3e%3c/svg%3e");
        background-position: right 0.5rem center;
        background-repeat: no-repeat;
        background-size: 1.5em 1.5em;
        padding-right: 2.5rem;
        -webkit-print-color-adjust: exact;
        print-color-adjust: exact;
    }
</style>