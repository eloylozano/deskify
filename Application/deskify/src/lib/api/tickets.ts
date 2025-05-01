// lib/api/tickets.ts

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

export async function createTicket(ticketData: {
  email: string;
  title: string;
  description: string;
}) {
  try {
    const response = await fetch(`${API_URL}/ticket/create`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(ticketData)
    });
    if (!response.ok) {
      throw new Error(`HTTP error! status: ${response.status}`);
    }

    return await response.json();
  } catch (error) {
    console.error('Error creating ticket:', error);
    throw error;
  }
}

export async function getTicketById(id: number) {
  try {
    const response = await fetch(`${API_URL}/ticket/${id}`);
    if (!response.ok) {
      throw new Error(`HTTP error! status: ${response.status}`);
    }
    return await response.json();
  } catch (error) {
    console.error(`Error fetching ticket ${id}:`, error);
    throw error;
  }
}

export async function updateTicketStatus(updateData: {
  ticketId: number;
  statusId: number;
  priorityId: number;
  categoryId: number;
  userId: number;
}) {
  console.log('Sending update:', updateData); // Añade esto para debug

  const response = await fetch(`${API_URL}/ticket/update`, {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(updateData)
  });

  if (!response.ok) {
    const errorText = await response.text();
    console.error('Update error response:', errorText);
    throw new Error(`Failed to update ticket: ${errorText}`);
  }

  return await response.json();
}

export async function createComment(ticketId: number, email: string, commentText: string) {
  try {
    const body = {
      userEmail: email,
      commentText: commentText
    };

    const response = await fetch(`${API_URL}/ticket/${ticketId}/comment`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(body),
    });

    if (!response.ok) {
      const errorData = await response.json().catch(() => ({}));
      const errorMessage = errorData.message || `HTTP error! status: ${response.status}`;
      throw new Error(errorMessage);
    }


    return await response.json();
  } catch (error) {
    console.error('Error creating comment:', error);
    throw error;
  }
}