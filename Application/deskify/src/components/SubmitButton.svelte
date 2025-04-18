<script lang="ts">
  import { createEventDispatcher } from 'svelte';
  export let additionalClass = '';
  export let text = '';
  const dispatch = createEventDispatcher();

  // Para que el botón actualice el formulario, se puede emitir el evento o dejarlo vacío
  const handleClick = () => {
    dispatch('click'); // Solo emitir el evento si realmente se necesita
  };
</script>

<button
  type="button"
  on:click={handleClick}  
  class={`btn px-12 py-2 rounded-full cursor-pointer flex text-white items-center w-fit ${additionalClass}`}
>
  {text}
</button>

<style>
  .btn {
    background: linear-gradient(45deg, #00ff7a, #00aa6f 100%);
    transition:
      background 0.3s ease-in,
      transform 0.3s ease,
      box-shadow 0.3s ease;
  }

  .btn:hover {
    background: linear-gradient(45deg, #00aa6f, #02353c 100%);
    transform: scale(1.05);
    box-shadow: 0px 4px 20px rgba(0, 0, 0, 0.2);
  }
</style>
