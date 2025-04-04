// tailwind.config.js
module.exports = {
	content: [
		'./src/**/*.{html,js,svelte,ts}' // Asegúrate de incluir tus archivos Svelte y TypeScript
	],
	theme: {
		extend: {
			colors: {
				primary: '#1D4ED8', // Azul
				secondary: '#9333EA', // Morado
				accent: '#F59E0B', // Amarillo
				background: '#F3F4F6', // Gris claro
				text: '#333333' // Gris oscuro
			}
		}
	},
	plugins: []
};
