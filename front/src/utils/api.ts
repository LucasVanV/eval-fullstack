import type { Candidat, Employe } from '../types';

type Method = 'GET' | 'POST' | 'PUT' | 'DELETE';

const API_BASE_URL = 'http://localhost:8080/api';

export async function apiFetch<T>(
	url: string,
	options: { method?: Method; body?: unknown; headers?: Record<string, string> } = {}
): Promise<T> {
	const { method = 'GET', body, headers = {} } = options;

	const res = await fetch(API_BASE_URL + url, {
		method,
		headers: {
			'Content-Type': 'application/json',
			...headers
		},
		body: body ? JSON.stringify(body) : undefined
	});

	if (!res.ok) {
		const errorText = await res.text();
		throw new Error(errorText || 'Erreur API');
	}
	const data = await res.json();
	return data;
}

export function getAllEmploye() {
	return apiFetch<Employe[]>('/employes');
}

export function getEmployeById(id: number) {
	return apiFetch<Employe>('/employes/' + id);
}

export function createEmploye(employe: Employe) {
	return apiFetch('/employes', { method: 'POST', body: employe });
}

export function deleteEmploye(id: number) {
	return apiFetch('/employes/' + id, { method: 'DELETE' });
}

export function updateEmploye(id: number, employe: Employe) {
	return apiFetch(`/employes/${id}`, { method: 'PUT', body: employe });
}

export function createAbsence(employeId: number, jour: string) {
	return apiFetch('/absences', {
		method: 'POST',
		body: {
			jour,
			employe: { id: employeId }
		}
	});
}

export function createConge(employeId: number, dateDebut: string, dateFin: string) {
	return apiFetch('/conges', {
		method: 'POST',
		body: {
			dateDebut,
			dateFin,
			employe: { id: employeId }
		}
	});
}

export function getAllCandidat() {
	return apiFetch<Candidat[]>('/candidats');
}

export function getCandidatById(id: number) {
	return apiFetch<Candidat>('/candidats/' + id);
}

export function createCandidat(candidat: Candidat) {
	return apiFetch('/candidats', { method: 'POST', body: candidat });
}

export function deleteCandidat(id: number) {
	return apiFetch('/candidats/' + id, { method: 'DELETE' });
}

export function updateCandidat(id: number, candidat: Candidat) {
	return apiFetch(`/candidats/${id}`, { method: 'PUT', body: candidat });
}
