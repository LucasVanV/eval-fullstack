<script lang="ts">
    import CandidatForm from '../../../components/candidatForm.svelte';
    import { goto } from '$app/navigation';
    import { createCandidat } from '../../../utils/api';

    async function handleSubmit(event: CustomEvent<{ candidat: any }>) {
        const { candidat } = event.detail;
        await createCandidat(candidat);
        goto('/candidat');
    }

    function handleCancel() {
        goto('/candidat');
    }
</script>

<h1 class="text-2xl font-bold mb-6">Ajouter un candidat</h1>
<CandidatForm on:submit={handleSubmit} on:cancel={handleCancel} />