<!-- src/routes/error/+page.svelte -->
<script lang="ts">
    import { page } from '$app/stores';
	import ErrorPage from '../../components/ErrorPage.svelte';

    // Obtener parámetros de la URL
    $: status = Number($page.url.searchParams.get('status')) || 500;
    $: message = $page.url.searchParams.get('message') || 'Ocurrió un error inesperado';

    // Mapeo de imágenes por código de error
    const errorImages: Record<number, string> = {
        400: '/Error400.svg',
        401: '/Error401.svg',
        403: '/Error403.svg',
        404: '/Error404.svg',
        500: '/Error500.svg',
        503: '/Error503.svg',
        504: '/Error504.svg'
    };

    $: image = errorImages[status] || '/Error400.svg';
    $: title = getTitle(status);
    $: description = getDescription(status, message);

    function getTitle(status: number): string {
        const titles: Record<number, string> = {
            400: 'Something went wrong with your request.',
            401: 'Unauthorized access.',
            403: 'Access denied.',
            404: 'Page not found.',
            500: 'Internal Server Error.',
            503: 'Service Unavailable.',
            504: 'Gateway Timeout.'
        };
        return titles[status] || titles[400];
    }

    function getDescription(status: number, customMessage: string): string {
        const defaultDescriptions: Record<number, string> = {
            400: 'It appears the information you submitted is invalid or incomplete.',
            401: 'You need to log in or provide valid credentials to access this page.',
            403: 'You don\'t have permission to view this page.',
            404: 'The page you requested could not be found.',
            500: 'Something broke on our end. We\'re working on it.',
            503: 'Our services are temporarily down for maintenance.',
            504: 'The server took too long to respond.'
        };

        return customMessage !== 'Ocurrió un error inesperado' 
            ? customMessage 
            : defaultDescriptions[status] || defaultDescriptions[400];
    }
</script>

<ErrorPage {image} {title} {description} />