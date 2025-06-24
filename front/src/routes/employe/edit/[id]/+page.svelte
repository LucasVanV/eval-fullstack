<script lang="ts">
    import EmployeForm from '../../../../components/EmployeForm.svelte';
    import { goto } from '$app/navigation';
    import { updateEmploye } from '../../../../utils/api';
    import { page } from '$app/stores';
    import { get } from 'svelte/store';

    // Récupère l'id depuis l'URL (ex: /employe/edit/3)
    const id = Number(get(page).params.id);

    async function handleSubmit(event: CustomEvent<{ employe: any }>) {
        const { employe } = event.detail;
        await updateEmploye(id, employe);
        goto('/employe');
    }

    function handleCancel() {
        goto('/employe');
    }
</script>

<h1 class="text-2xl font-bold mb-6">Modifier un employé</h1>
<EmployeForm {id} on:submit={handleSubmit} on:cancel={handleCancel} />