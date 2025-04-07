
<script>
	export let text = '';
	export let href = '';
	export let additionalClass = '';
</script>

<a
  class={`btn px-12 py-2 rounded-full cursor-pointer flex text-white flex items-center w-fit ${additionalClass}`}
  href={href}
>
  {text}
</a>

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
