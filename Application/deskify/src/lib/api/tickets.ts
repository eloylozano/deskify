const API_URL = import.meta.env.VITE_API_URL;

export async function getTickets() {
    try {
        const response = await fetch(`${API_URL}/ticket/all`);
      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }
      return await response.json();
    } catch (error) {
      console.error('Error fetching tickets:', error);
      throw error;
    }
  }