# Gestion RH - Projet Fullstack

Ce projet est une application de gestion des employés et candidats, réalisée en **Spring Boot** pour le back-end et **SvelteKit** pour le front-end.

## Structure du projet

```
back/   # API REST Spring Boot (Java)
front/  # Application web SvelteKit (TypeScript)
```

---

## Démarrage rapide

### Prérequis

- Java 17+
- Node.js 18+
- Maven

---

### Lancer le back-end (Spring Boot)

```sh
cd back
./mvnw spring-boot:run
```

L'API sera disponible sur [http://localhost:8080](http://localhost:8080).

---

### Lancer le front-end (SvelteKit)

```sh
cd front
npm install
npm run dev
```

L'application sera disponible sur [http://localhost:5173](http://localhost:5173).

---

## Fonctionnalités

- Gestion des employés
- Gestion des candidats
- Attribution de congés et absences aux employés

---

## Développement

- **Back** : [back/](back/)
- **Front** : [front/](front/)

---

## Auteur

Lucas Vanvooren
