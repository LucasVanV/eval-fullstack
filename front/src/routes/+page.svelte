<script lang="ts">
	import { onMount } from "svelte";
	import { getAllCandidat, getAllEmploye } from "../utils/api";

    let nbEmployes = -1;
    let nbCandidats = -1;

    onMount(async () => {
        try {
            const dataCandidat = await getAllCandidat();
            const dataEmploye = await getAllEmploye();
            nbEmployes = dataEmploye.length;
            nbCandidats = dataCandidat.length;
        } catch (e) {
            console.error(e);
        }
    });
</script>

<h1 class="text-3xl font-bold mb-8">Accueil</h1>
<div class="flex gap-8">
    <div class="bg-white rounded-lg shadow p-8 flex-1 text-center">
        <div class="text-5xl font-bold text-blue-600 mb-2">{nbEmployes}</div>
        <div class="text-lg text-gray-700">Employés</div>
    </div>
    <div class="bg-white rounded-lg shadow p-8 flex-1 text-center">
        <div class="text-5xl font-bold text-green-600 mb-2">{nbCandidats}</div>
        <div class="text-lg text-gray-700">Candidats</div>
    </div>
</div>
