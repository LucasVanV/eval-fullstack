<script lang="ts">
    import EmployeForm from '../../../components/EmployeForm.svelte';
    import { goto } from '$app/navigation';
    import { createEmploye } from '../../../utils/api';

    async function handleSubmit(event: CustomEvent<{ employe: any }>) {
        const { employe } = event.detail;
        await createEmploye(employe);
        goto('/employe');
    }

    function handleCancel() {
        goto('/employe');
    }
</script>

<h1 class="text-2xl font-bold mb-6">Ajouter un employé</h1>
<EmployeForm on:submit={handleSubmit} on:cancel={handleCancel} />