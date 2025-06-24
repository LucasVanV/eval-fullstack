<script lang="ts">
    import { onMount } from 'svelte';
    import { getAllCandidat } from '../../utils/api';
    import type { Candidat } from '../../types';
    import { goto } from '$app/navigation';

    let candidats: Candidat[] = [];

    onMount(async () => {
        candidats = await getAllCandidat();
    });

    function ajouterCandidat() {
        goto('/candidat/new');
    }

    function voirCandidat(id: number) {
        goto(`/candidat/detail/${id}`);
    }

    function editCandidat(id: number) {
        goto(`/candidat/edit/${id}`);
    }

    function supprimerCandidat(id: number) {
        if (confirm("Supprimer ce candidat ?")) {
            // Ajoute ici l'appel à ton API pour supprimer, puis refresh la liste
        }
    }
</script>

<h1 class="text-2xl font-bold mb-6">Liste des candidats</h1>
<button
    class="mb-4 bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700 transition"
    on:click={ajouterCandidat}
>
    Ajouter un candidat
</button>
<table class="min-w-full bg-white rounded shadow">
    <thead>
        <tr>
            <th class="py-2 px-4 border-b text-left">Nom</th>
            <th class="py-2 px-4 border-b text-left">Domaine technique</th>
            <th class="py-2 px-4 border-b text-left">Email</th>
            <th class="py-2 px-4 border-b text-left">Téléphone</th>
            <th class="py-2 px-4 border-b text-left">Actions</th>
        </tr>
    </thead>
    <tbody>
        {#each candidats as candidat}
            <tr>
                <td class="py-2 px-4 border-b">{candidat.nom}</td>
                <td class="py-2 px-4 border-b">{candidat.domaineTechnique}</td>
                <td class="py-2 px-4 border-b">{candidat.email}</td>
                <td class="py-2 px-4 border-b">{candidat.numeroTelephone}</td>
                <td class="py-2 px-4 border-b flex gap-2">
                    <button class="bg-green-500 text-white px-3 py-1 rounded hover:bg-green-600" on:click={() => voirCandidat(candidat.id)}>Voir</button>
                    <button class="bg-blue-600 text-white px-3 py-1 rounded hover:bg-blue-700" on:click={() => editCandidat(candidat.id)}>Mettre à jour</button>
                    <button class="bg-red-500 text-white px-3 py-1 rounded hover:bg-red-600" on:click={() => supprimerCandidat(candidat.id)}>Supprimer</button>
                </td>
            </tr>
        {/each}
    </tbody>
</table>