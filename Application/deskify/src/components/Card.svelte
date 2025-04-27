<!-- src/components/Card.svelte -->
<script lang="ts">
    export let className: string = ''; // Clase personalizada para permitir estilos adicionales
    export let children: any; // Contenido dentro de la tarjeta
  </script>
  
  <div class={`bg-white shadow rounded-lg overflow-hidden ${className}`}>
    {#if children}
      <div class="flex flex-col">{children}</div>
    {/if}
  </div>
  
  <style>
    div {
      box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
      background-color: white;
      padding: 1.5rem;
      border-radius: 8px;
      overflow: hidden;
    }
  </style>
  