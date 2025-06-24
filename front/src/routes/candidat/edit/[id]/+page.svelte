<script lang="ts">
    import CandidatForm from '../../../../components/candidatForm.svelte';
    import { goto } from '$app/navigation';
    import { updateCandidat } from '../../../../utils/api';
    import { page } from '$app/stores';
    import { get } from 'svelte/store';

    const id = Number(get(page).params.id);

    async function handleSubmit(event: CustomEvent<{ candidat: any }>) {
        const { candidat } = event.detail;
        await updateCandidat(id, candidat);
        goto('/candidat');
    }

    function handleCancel() {
        goto('/candidat');
    }
</script>

<h1 class="text-2xl font-bold mb-6">Modifier un candidat</h1>
<CandidatForm {id} on:submit={handleSubmit} on:cancel={handleCancel} />