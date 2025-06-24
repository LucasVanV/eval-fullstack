<script lang="ts">
    import { onMount } from 'svelte';
    import { getCandidatById, deleteCandidat } from '../../../../utils/api';
    import { goto } from '$app/navigation';
    import { page } from '$app/stores';
    import { get } from 'svelte/store';
    import type { Candidat } from '../../../../types';

    let candidat: Candidat | null = null;
    const id = Number(get(page).params.id);

    onMount(async () => {
        candidat = await getCandidatById(id);
    });

    function editCandidat() {
        goto(`/candidat/edit/${id}`);
    }

    function handleDelete() {
        if (confirm("Supprimer le candidat ?")) {
            deleteCandidat(id);
            goto('/candidat');
        }
    }
</script>

<h1 class="text-3xl font-bold mb-4">Détails du candidat</h1>
<div class="flex gap-2 mb-4">
    <button class="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700" on:click={editCandidat}>
        Mettre à jour les données
    </button>
    <button class="bg-red-600 text-white px-4 py-2 rounded hover:bg-red-700" on:click={handleDelete}>
        Supprimer l'employé
    </button>
</div>

{#if candidat}
    <table class="min-w-full mb-6">
        <tbody>
            <tr><th class="text-left pr-4">Nom</th><td>{candidat.nom}</td></tr>
            <tr><th class="text-left pr-4">Numéro identification</th><td>{candidat.numeroIdentification}</td></tr>
            <tr><th class="text-left pr-4">Date de naissance</th><td>{candidat.dateNaissance}</td></tr>
            <tr><th class="text-left pr-4">Adresse</th><td>{candidat.adresse}</td></tr>
            <tr><th class="text-left pr-4">Email</th><td>{candidat.email}</td></tr>
            <tr><th class="text-left pr-4">Téléphone</th><td>{candidat.numeroTelephone}</td></tr>
            <tr><th class="text-left pr-4">Note (0-10)</th><td>{candidat.evaluation}</td></tr>
            <tr><th class="text-left pr-4">Domaine technique</th><td>{candidat.domaineTechnique}</td></tr>
            <tr><th class="text-left pr-4">Date entretien</th><td>{candidat.dateEntretien}</td></tr>       
            <tr><th class="text-left pr-4">Observations</th><td>{candidat.observation}</td></tr>
        </tbody>
    </table>
{:else}
    <div>Chargement...</div>
{/if}