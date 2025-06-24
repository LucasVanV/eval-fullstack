<script lang="ts">
    import { createEventDispatcher, onMount } from 'svelte';
    import type { Candidat } from '../types';
    import { getCandidatById } from '../utils/api';

    export let candidat: Partial<Candidat> = {
        id: undefined,
        nom: '',
        numeroIdentification: '',
        adresse: '',
        dateNaissance: '',
        email: '',
        numeroTelephone: '',
        domaineTechnique: '',
        evaluation: 0,
        dateEntretien: '',
        observation: ''
    };
    export let id: number | undefined = undefined;

    const dispatch = createEventDispatcher();

    onMount(async () => {
        if (id !== undefined) {
            const data = await getCandidatById(id);
            candidat = { ...data };
        }
    });

    function handleSubmit(e: Event) {
        e.preventDefault();
        dispatch('submit', { candidat });
    }
    function handleCancel() {
        dispatch('cancel');
    }
</script>

<form class="space-y-4" on:submit|preventDefault={handleSubmit}>
    <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
        <div>
            <label class="block mb-1 font-semibold" for="candidat-nom">Nom</label>
            <input id="candidat-nom" class="w-full border rounded px-3 py-2" bind:value={candidat.nom} required />
        </div>
        <div>
            <label class="block mb-1 font-semibold" for="candidat-numeroIdentification">Numéro d'identification</label>
            <input id="candidat-numeroIdentification" class="w-full border rounded px-3 py-2" bind:value={candidat.numeroIdentification} required/>
        </div>
        <div>
            <label class="block mb-1 font-semibold" for="candidat-adresse">Adresse</label>
            <input id="candidat-adresse" class="w-full border rounded px-3 py-2" bind:value={candidat.adresse} required/>
        </div>
        <div>
            <label class="block mb-1 font-semibold" for="candidat-dateNaissance">Date de naissance</label>
            <input id="candidat-dateNaissance" class="w-full border rounded px-3 py-2" type="date" bind:value={candidat.dateNaissance} required/>
        </div>
        <div>
            <label class="block mb-1 font-semibold" for="candidat-email">Adresse email</label>
            <input id="candidat-email" class="w-full border rounded px-3 py-2" type="email" bind:value={candidat.email} required/>
        </div>
        <div>
            <label class="block mb-1 font-semibold" for="candidat-numeroTelephone">Numéro de téléphone</label>
            <input id="candidat-numeroTelephone" class="w-full border rounded px-3 py-2" bind:value={candidat.numeroTelephone} required/>
        </div>
        <div>
            <label class="block mb-1 font-semibold" for="candidat-domaineTechnique">Domaine technique</label>
            <input id="candidat-domaineTechnique" class="w-full border rounded px-3 py-2" bind:value={candidat.domaineTechnique} required/>
        </div>
        <div>
            <label class="block mb-1 font-semibold" for="candidat-evaluation">Évaluation</label>
            <input id="candidat-evaluation" type="number" min="0" max="10" class="w-full border rounded px-3 py-2" bind:value={candidat.evaluation} required/>
        </div>
        <div>
            <label class="block mb-1 font-semibold" for="candidat-dateEntretien">Date entretien</label>
            <input id="candidat-dateEntretien" class="w-full border rounded px-3 py-2" type="date" bind:value={candidat.dateEntretien}/>
        </div>
    </div>
    <div>
        <label class="block mb-1 font-semibold" for="candidat-observation">Observations</label>
        <textarea id="candidat-observation" class="w-full border rounded px-3 py-2" rows="3" bind:value={candidat.observation}></textarea>
    </div>
    <div class="flex gap-2 mt-4">
        <button class="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700" type="submit">
            Sauvegarder le candidat
        </button>
        <button class="bg-red-600 text-white px-4 py-2 rounded hover:bg-red-700" type="button" on:click={handleCancel}>
            Annuler
        </button>
    </div>
</form>