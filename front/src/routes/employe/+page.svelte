<script lang="ts">
    import { onMount } from 'svelte';
    import { deleteEmploye, getAllEmploye } from '../../utils/api';
    import type { Employe } from '../../types';
	import { goto } from '$app/navigation';

    let employes: Employe[] = [];

    onMount(async () => {
        employes = await getAllEmploye();
    });

    function voirEmploye(id: number) {
        goto('/employe/detail/' + id);
    }

    function editEmploye(id: number) {
        goto(`/employe/edit/${id}`);
    }

    function supprimerEmploye(id: number) {
        if (confirm(`Êtes-vous sûr de vouloir supprimer l'employé avec l'ID ${id} ?`)) {
            deleteEmploye(id);
            alert(`Employé ${id} supprimé`);
            window.location.reload();
        }
    }

    function ajouterEmploye() {
        goto('/employe/new');
    }
</script>

<h1 class="text-2xl font-bold mb-6">Liste des employés</h1>
<button
    class="mb-4 bg-green-500 text-white px-4 py-2 rounded hover:bg-green-600 transition"
    on:click={ajouterEmploye}
>
    Ajouter un employé
</button>
<table class="min-w-full bg-white rounded shadow">
    <thead>
        <tr>
            <th class="py-2 px-4 border-b text-left">Nom</th>
            <th class="py-2 px-4 border-b text-left">occupation</th>
            <th class="py-2 px-4 border-b text-left">Email</th>
            <th class="py-2 px-4 border-b text-left">téléphone</th>
            <th class="py-2 px-4 border-b text-left">Actions</th>
        </tr>
    </thead>
    <tbody>
        {#each employes as employe}
            <tr>
                <td class="py-2 px-4 border-b align-middle">{employe.name}</td>
                <td class="py-2 px-4 border-b align-middle">{employe.occupation}</td>
                <td class="py-2 px-4 border-b align-middle">{employe.email}</td>
                <td class="py-2 px-4 border-b align-middle">{employe.telephone}</td>
                <td class="py-2 px-4 border-b flex gap-2 align-middle">
                    <button class="bg-blue-500 text-white px-3 py-1 rounded hover:bg-blue-600" on:click={() => voirEmploye(employe.id)}>Voir</button>
                    <button class="bg-yellow-500 text-white px-3 py-1 rounded hover:bg-yellow-600" on:click={() => editEmploye(employe.id)}>Edit</button>
                    <button class="bg-red-500 text-white px-3 py-1 rounded hover:bg-red-600" on:click={() => supprimerEmploye(employe.id)}>Supprimer</button>
                </td>
            </tr>
        {/each}
    </tbody>
</table>