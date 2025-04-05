<script>
</script>

<div class="w-full">
	<div id="top" class="bg-[#01c883] p-5">
		<p class="font-medium text-white">All tickets - deskify</p>
	</div>
	<div id="bot" class=" flex items-center bg-gray-100 px-5 py-3">
		<input type="checkbox" class="custom-checkbox me-2 cursor-pointer rounded-xs border-gray-400" />
		<p><span class="font-medium text-[#858585]">Order by:</span></p>
		<select class="w-40 cursor-pointer border-gray-400 px-2 font-medium text-[#252525]">
			<option value="1">Last Modified</option>
			<option value="2">Priority</option>
			<option value="3">Status</option>
			<option value="4">Agent</option>
		</select>
	</div>
</div>

<style>
	select {
		border: none;
		background-color: #00ffa600;
	}

	#bot {
		box-shadow: 0px 3px 5px 1px rgba(0, 0, 0, 0.2);
	}

	.custom-checkbox {
		-webkit-appearance: none;
		-moz-appearance: none;
		appearance: none;
		width: 18px;
		height: 18px;
		border: 2px solid #ccc;
		border-radius: 3px;
		outline: none;
		transition: all 0.2s;
		position: relative;
	}
	.custom-checkbox:focus {
		outline: none;
		box-shadow: none;
	}

	.custom-checkbox:checked {
		background-color: #01c883;
		border-color: #01c883;
	}

	.custom-checkbox:checked::after {
		content: '✓';
		position: absolute;
		color: white;
		font-size: 12px;
		top: 50%;
		left: 50%;
		transform: translate(-50%, -50%);
	}
</style>
