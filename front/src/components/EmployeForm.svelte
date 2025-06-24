<script lang="ts">
    import { createEventDispatcher, onMount } from 'svelte';
    import type { Employe } from '../types';
    import { getEmployeById } from '../utils/api';

    export let employe: Partial<Employe> = {
        id: undefined,
        name: '',
        numeroIdentification: '',
        adresse: '',
        dateNaissance: '',
        email: '',
        telephone: '',
        occupation: '',
        salaire: '',
        debutContrat: '',
        finContrat: '',
        observation: ''
    };
    export let id: number | undefined = undefined;

    const dispatch = createEventDispatcher();

    // Si un id est passé, on va chercher l'employé et on pré-remplit
    onMount(async () => {
        if (id !== undefined) {
            const data = await getEmployeById(id);
            employe = { ...data };
        }
    });

    function handleSubmit(e: Event) {
        e.preventDefault();
        dispatch('submit', { employe });
    }
    function handleCancel() {
        dispatch('cancel');
    }
</script>

<form class="space-y-4" on:submit|preventDefault={handleSubmit}>
    <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
        <div>
            <label class="block mb-1 font-semibold" for="employe-name">Nom</label>
            <input id="employe-name" class="w-full border rounded px-3 py-2" bind:value={employe.name} required />
        </div>
        <div>
            <label class="block mb-1 font-semibold" for="employe-numeroIdentification">Numéro d'identification</label>
            <input id="employe-numeroIdentification" class="w-full border rounded px-3 py-2" bind:value={employe.numeroIdentification} required/>
        </div>
        <div>
            <label class="block mb-1 font-semibold" for="employe-adresse">Adresse</label>
            <input id="employe-adresse" class="w-full border rounded px-3 py-2" bind:value={employe.adresse} required/>
        </div>
        <div>
            <label class="block mb-1 font-semibold" for="employe-dateNaissance">Date de naissance</label>
            <input id="employe-dateNaissance" class="w-full border rounded px-3 py-2" type="date" bind:value={employe.dateNaissance} required/>
        </div>
        <div>
            <label class="block mb-1 font-semibold" for="employe-email">Adresse email</label>
            <input id="employe-email" class="w-full border rounded px-3 py-2" type="email" bind:value={employe.email} required/>
        </div>
        <div>
            <label class="block mb-1 font-semibold" for="employe-telephone">Numéro de téléphone</label>
            <input id="employe-telephone" class="w-full border rounded px-3 py-2" bind:value={employe.telephone} required/>
        </div>
        <div>
            <label class="block mb-1 font-semibold" for="employe-occupation">Occupation/Poste</label>
            <input id="employe-occupation" class="w-full border rounded px-3 py-2" bind:value={employe.occupation} required/>
        </div>
        <div>
            <label class="block mb-1 font-semibold" for="employe-salaire">Salaire</label>
            <input id="employe-salaire" type="number" class="w-full border rounded px-3 py-2" bind:value={employe.salaire} required/>
        </div>
        <div>
            <label class="block mb-1 font-semibold" for="employe-debutContrat">Début du contrat</label>
            <input id="employe-debutContrat" class="w-full border rounded px-3 py-2" type="date" bind:value={employe.debutContrat} required/>
        </div>
        <div>
            <label class="block mb-1 font-semibold" for="employe-finContrat">Fin du contrat</label>
            <input id="employe-finContrat" class="w-full border rounded px-3 py-2" type="date" bind:value={employe.finContrat}/>
        </div>
    </div>
    <div>
        <label class="block mb-1 font-semibold" for="employe-observation">Observations</label>
        <textarea id="employe-observation" class="w-full border rounded px-3 py-2" rows="3" bind:value={employe.observation}></textarea>
    </div>
    <div class="flex gap-2 mt-4">
        <button class="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700" type="submit">
            Sauvegarder l'employé
        </button>
        <button class="bg-red-600 text-white px-4 py-2 rounded hover:bg-red-700" type="button" on:click={handleCancel}>
            Annuler
        </button>
    </div>
</form>