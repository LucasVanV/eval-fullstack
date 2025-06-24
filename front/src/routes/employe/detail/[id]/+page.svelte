<script lang="ts">
    import { onMount } from 'svelte';
    import { getEmployeById, deleteEmploye, createAbsence, createConge } from '../../../../utils/api';
    import { goto } from '$app/navigation';
    import { page } from '$app/stores';
    import { get } from 'svelte/store';
    import type { Employe } from '../../../../types';

    let employe: Employe | null = null;
    const id = Number(get(page).params.id);

    onMount(async () => {
        employe = await getEmployeById(id);
    });

    function editEmploye() {
        goto(`/employe/edit/${id}`);
    }

    function handleDelete() {
        if (confirm("Supprimer l'employé ?")) {
            deleteEmploye(id);
            goto('/employe');
        }
    }

    let showCongeModal = false;
    let showAbsenceModal = false;

    let congeDebut = '';
    let congeFin = '';

    let absenceJour = '';

    function handleConges() {
        showCongeModal = true;
    }

    function handleAbsence() {
        showAbsenceModal = true;
    }

    function closeCongeModal() {
        showCongeModal = false;
        congeDebut = '';
        congeFin = '';
    }

    function closeAbsenceModal() {
        showAbsenceModal = false;
        absenceJour = '';
    }

    async function submitConge() {
        if (employe && congeDebut && congeFin) {
            await createConge(employe.id, congeDebut, congeFin);
            closeCongeModal();
            window.location.reload();
        }
    }

    async function submitAbsence() {
        if (employe && absenceJour) {
            await createAbsence(employe.id, absenceJour);
            closeAbsenceModal();
            window.location.reload();
        }
    }

    let showConges = false;
    let showAbsences = false;
</script>

<h1 class="text-3xl font-bold mb-4">Détails de l'employé</h1>
<div class="flex gap-2 mb-4">
    <button class="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700" on:click={editEmploye}>
        Mettre à jour les données
    </button>
    <button class="bg-sky-500 text-white px-4 py-2 rounded hover:bg-sky-600" on:click={handleConges}>
        Attribuer des congés
    </button>
    <button class="bg-yellow-400 text-white px-4 py-2 rounded hover:bg-yellow-500" on:click={handleAbsence}>
        Signaler une absence
    </button>
    <button class="bg-red-600 text-white px-4 py-2 rounded hover:bg-red-700" on:click={handleDelete}>
        Supprimer l'employé
    </button>
</div>

{#if employe}
    <table class="min-w-full mb-6">
        <tbody>
            <tr><th class="text-left pr-4">Nom</th><td>{employe.name}</td></tr>
            <tr><th class="text-left pr-4">Occupation</th><td>{employe.occupation}</td></tr>
            <tr><th class="text-left pr-4">Salaire</th><td>{employe.salaire}</td></tr>
            <tr><th class="text-left pr-4">Début du contrat</th><td>{employe.debutContrat}</td></tr>
            <tr><th class="text-left pr-4">Fin du contrat</th><td>{employe.finContrat}</td></tr>
            <tr><th class="text-left pr-4">Numéro d'identification</th><td>{employe.numeroIdentification}</td></tr>
            <tr><th class="text-left pr-4">Date de naissance</th><td>{employe.dateNaissance}</td></tr>
            <tr><th class="text-left pr-4">Adresse</th><td>{employe.adresse}</td></tr>
            <tr><th class="text-left pr-4">Email</th><td>{employe.email}</td></tr>
            <tr><th class="text-left pr-4">Téléphone</th><td>{employe.telephone}</td></tr>
            <tr><th class="text-left pr-4">Observations</th><td>{employe.observation}</td></tr>
        </tbody>
    </table>

    <div
        class="bg-blue-100 rounded p-4 mb-4 cursor-pointer select-none"
        on:click={() => showConges = !showConges}
    >
        <div class="font-semibold mb-2 flex items-center gap-2">
            <span>Congés ({employe.conges?.length ?? 0})</span>
            <span>{showConges ? '▲' : '▼'}</span>
        </div>
        {#if showConges}
            <table class="w-full mt-2">
                <thead>
                    <tr>
                        <th class="text-left">Début</th>
                        <th class="text-left">Fin</th>
                    </tr>
                </thead>
                <tbody>
                    {#if employe.conges && employe.conges.length > 0}
                        {#each employe.conges as conge}
                            <tr>
                                <td>{conge.dateDebut}</td>
                                <td>{conge.dateFin}</td>
                            </tr>
                        {/each}
                    {:else}
                        <tr>
                            <td colspan="2" class="italic text-gray-500">Aucun congé</td>
                        </tr>
                    {/if}
                </tbody>
            </table>
        {/if}
    </div>

    <div
        class="bg-blue-100 rounded p-4 cursor-pointer select-none"
        on:click={() => showAbsences = !showAbsences}
    >
        <div class="font-semibold mb-2 flex items-center gap-2">
            <span>Absences ({employe.absences?.length ?? 0})</span>
            <span>{showAbsences ? '▲' : '▼'}</span>
        </div>
        {#if showAbsences}
            <table class="w-full mt-2">
                <thead>
                    <tr>
                        <th class="text-left">Jour</th>
                    </tr>
                </thead>
                <tbody>
                    {#if employe.absences && employe.absences.length > 0}
                        {#each employe.absences as absence}
                            <tr>
                                <td>{absence.jour}</td>
                            </tr>
                        {/each}
                    {:else}
                        <tr>
                            <td class="italic text-gray-500">Aucune absence</td>
                        </tr>
                    {/if}
                </tbody>
            </table>
        {/if}
    </div>
{:else}
    <div>Chargement...</div>
{/if}

{#if showCongeModal}
    <div class="fixed inset-0 bg-black bg-opacity-40 backdrop-blur flex items-center justify-center z-50">
        <div class="bg-white rounded shadow-lg p-6 w-full max-w-md">
            <h2 class="text-xl font-bold mb-4">Attribuer un congé</h2>
            <div class="mb-4">
                <label class="block mb-1" for="conge-debut">Début</label>
                <input id="conge-debut" type="date" class="w-full border rounded px-3 py-2" bind:value={congeDebut} />
            </div>
            <div class="mb-4">
                <label class="block mb-1" for="conge-fin">Fin</label>
                <input id="conge-fin" type="date" class="w-full border rounded px-3 py-2" bind:value={congeFin} />
            </div>
            <div class="flex gap-2 justify-end">
                <button class="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700" on:click={submitConge}>Valider</button>
                <button class="bg-gray-300 px-4 py-2 rounded hover:bg-gray-400" on:click={closeCongeModal}>Annuler</button>
            </div>
        </div>
    </div>
{/if}

{#if showAbsenceModal}
    <div class="fixed inset-0 bg-black bg-opacity-40 backdrop-blur flex items-center justify-center z-50">
        <div class="bg-white rounded shadow-lg p-6 w-full max-w-md">
            <h2 class="text-xl font-bold mb-4">Signaler une absence</h2>
            <div class="mb-4">
                <label class="block mb-1" for="absence-jour">Jour</label>
                <input id="absence-jour" type="date" class="w-full border rounded px-3 py-2" bind:value={absenceJour} />
            </div>
            <div class="flex gap-2 justify-end">
                <button class="bg-yellow-500 text-white px-4 py-2 rounded hover:bg-yellow-600" on:click={submitAbsence}>Valider</button>
                <button class="bg-gray-300 px-4 py-2 rounded hover:bg-gray-400" on:click={closeAbsenceModal}>Annuler</button>
            </div>
        </div>
    </div>
{/if}