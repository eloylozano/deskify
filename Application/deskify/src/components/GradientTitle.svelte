
<script>
	export let additionalClass = '';
	export let text = '';
</script>

<h3 class={`uppercase font-medium text-2xl  text-gradient ${additionalClass}`}>{text}</h3>

<style>
  .text-gradient {
    background-image: linear-gradient(90deg, #00ff7a, #00aa6f 60%);
    -webkit-background-clip: text;
    color: transparent;
  }
</style>
