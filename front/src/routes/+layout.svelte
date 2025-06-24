<script lang="ts">
    import '../app.css';
</script>

<div class="flex min-h-screen">
    <nav class="w-64 bg-gray-800 text-white flex flex-col p-6">
        <div class="mb-8 text-2xl font-bold">Gestion RH</div>
        <a href="/" class="mb-4 px-4 py-2 rounded hover:bg-gray-700 transition">Accueil</a>
        <a href="/employe" class="mb-4 px-4 py-2 rounded hover:bg-gray-700 transition">Employé</a>
        <a href="/candidat" class="px-4 py-2 rounded hover:bg-gray-700 transition">Candidat</a>
    </nav>

    <main class="flex-1 p-8 bg-gray-100">
        <slot />
    </main>
</div>
