<script>
	// @ts-ignore
	import ErrorPage from '../components/ErrorPage.svelte';


	export let error;

	console.log('Error recibido en +error.svelte:', error);
</script>

{#if error?.status === 400}
	<ErrorPage
		image="/Error400.svg"
		title="Something went wrong with your request."
		description="It appears the information you submitted is invalid or incomplete. Please verify the details and try again. If you need assistance, please don't hesitate to contact us."
	/>
{:else if error?.status === 401}
	<ErrorPage
		image="/Error401.svg"
		title="Unauthorized access."
		description="You need to log in or provide valid credentials to access this page."
	/>
{:else if error?.status === 403}
	<ErrorPage
		image="/Error403.svg"
		title="Access denied."
		description="You don’t have permission to view this page. Please contact your administrator if you think this is an error."
	/>
{:else if error?.status === 500}
	<ErrorPage
		image="/Error500.svg"
		title="Internal Server Error."
		description="Something broke on our end. We're working on it. Please try again later."
	/>
{:else if error?.status === 503}
	<ErrorPage
		image="/Error503.svg"
		title="Service Unavailable."
		description="Our services are temporarily down for maintenance or are overloaded. Please check back soon."
	/>
{:else if error?.status === 504}
	<ErrorPage
		image="/Error504.svg"
		title="Gateway Timeout."
		description="The server took too long to respond. Please refresh or try again later."
	/>
{:else}
	<ErrorPage
		image="/Error404.svg"
		title="Oops! We couldn't find what you were looking for."
		description="This page doesn't exist, but don't worry, the others are waiting for you. Keep exploring and find what you're looking for!"
	/>
{/if}
