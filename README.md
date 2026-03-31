"# 🚀 Backend Spring Boot - Plateforme de Gestion de Publications Scientifiques

## 📖 Description du Projet

Ce projet est un backend Spring Boot conçu pour gérer une plateforme collaborative de publications scientifiques. Il permet aux chercheurs de partager leurs travaux de recherche, de collaborer avec d'autres chercheurs, et de gérer des annonces liées à leurs domaines de recherche.

### Fonctionnalités principales
- **Gestion des utilisateurs**: Inscription, authentification, et gestion des rôles (Admin, Modérateur, Utilisateur)
- **Gestion des chercheurs**: Profils de chercheurs avec biographie, institution, position, et identifiant ORCID
- **Gestion des publications**: Création, édition et publication d'articles scientifiques avec statuts (brouillon, publié)
- **Domaines de recherche**: Catégorisation et organisation des chercheurs et publications par domaine
- **Système d'annonces**: Les modérateurs peuvent créer et gérer des annonces pour les utilisateurs
- **Sécurité**: Authentification et autorisation intégrées avec Spring Security

---

## 🛠️ Installation et Setup

### Prérequis
- **Java 21** ou supérieur
- **Maven** 3.6+
- **Docker** et **Docker Compose**
- **PostgreSQL 16** (lancé automatiquement avec Docker)

### Étapes d'installation

#### 1️⃣ Cloner le projet
```bash
git clone <repository-url>
cd webProject
```

#### 2️⃣ Lancer la base de données PostgreSQL
```bash
docker-compose up -d
```
Cela lance un conteneur PostgreSQL avec:
- Base de données: `ia_technology_db`
- Utilisateur: `postgres`
- Mot de passe: `postgres`
- Port: `5432`

#### 3️⃣ Compiler le projet
```bash
mvn clean install
```

#### 4️⃣ Lancer l'application
```bash
mvn spring-boot:run
```

L'application sera accessible sur `http://localhost:8080`

#### 5️⃣ Arrêter la base de données (optionnel)
```bash
docker-compose down
```

---

## 🏗️ Structure du Projet et Modèles

### Architecture générale
```
src/
├── main/
│   ├── java/com/project/webProject/
│   │   ├── WebProjectApplication.java          # Point d'entrée Spring Boot
│   │   ├── controllers/                        # Contrôleurs REST
│   │   ├── entities/                           # Entités JPA/Hibernate
│   │   ├── enums/                              # Énumérations
│   │   ├── repositories/                       # Interfaces de données
│   │   └── services/                           # Logique métier
│   └── resources/
│       ├── application.properties               # Configuration
│       ├── static/                              # Fichiers statiques
│       └── templates/                           # Templates Thymeleaf
└── test/
    └── java/                                    # Tests unitaires
```

### Modèles de données

#### 👤 **User** (Utilisateur)
Représente un compte utilisateur du système.

**Champs:**
- `id`: Identifiant unique (auto-généré)
- `firstName`: Prénom (50 caractères)
- `lastName`: Nom (50 caractères)
- `email`: Email unique (80 caractères)
- `username`: Nom d'utilisateur unique (50 caractères)
- `password`: Mot de passe chiffré
- `enabled`: Statut du compte (true par défaut)
- `createdAt`: Date de création
- `updatedAt`: Date de dernière modification

**Relations:**
- `roles`: Liste de rôles (ManyToMany) - ROLE_ADMIN, ROLE_MODERATOR, ROLE_USER
- `researcher`: Profil de chercheur lié (OneToOne, optionnel)

---

#### 🔬 **Researcher** (Chercheur)
Profil détaillé d'un chercheur avec informations académiques.

**Champs:**
- `id`: Identifiant unique
- `firstName`, `lastName`: Nom et prénom
- `email`: Email du chercheur
- `institution`: Université/Institut (100 caractères)
- `position`: Titre académique (100 caractères)
- `bio`: Biographie complète (texte long)
- `photoUrl`: URL de la photo de profil
- `orcidId`: Identifiant ORCID (100 caractères)
- `createdAt`: Date de création

**Relations:**
- `domain`: Domaine de recherche principal (ManyToOne)
- `user`: Compte utilisateur lié (OneToOne, optionnel)
- `publications`: Liste de publications (ManyToMany)

---

#### 📄 **Publication** (Publication Scientifique)
Représente un article ou travail de recherche.

**Champs:**
- `id`: Identifiant unique
- `title`: Titre de l'article (255 caractères)
- `abstractText`: Résumé/Abstract (texte long)
- `keywords`: Mots-clés (texte long)
- `doi`: Identifiant DOI (100 caractères)
- `pdfUrl`: URL du fichier PDF
- `journal`: Nom de la revue (255 caractères)
- `publicationDate`: Date de publication
- `status`: Statut (DRAFT, PUBLISHED, etc. - DRAFT par défaut)
- `createdAt`: Date de création
- `updatedAt`: Date de modification

**Relations:**
- `domain`: Domaine de recherche (ManyToOne)
- `researchers`: Auteurs de la publication (ManyToMany)

---

#### 🏷️ **Domain** (Domaine de Recherche)
Catégories de recherche pour organiser les chercheurs et publications.

**Champs:**
- `id`: Identifiant unique
- `name`: Nom du domaine, unique (100 caractères)
- `description`: Description du domaine (texte long)

**Relations:**
- `researchers`: Chercheurs du domaine (OneToMany)
- `publications`: Publications du domaine (OneToMany)

---

#### 🎭 **Role** (Rôle)
Rôles disponibles dans le système.

**Champs:**
- `id`: Identifiant unique
- `name`: Nom du rôle (enum)

**Rôles disponibles:**
- `ROLE_ADMIN`: Accès administrateur complet
- `ROLE_MODERATOR`: Peut modérer le contenu et créer des annonces
- `ROLE_USER`: Utilisateur standard

---

#### 📢 **Announcement** (Annonce)
Annonces publiées par les modérateurs.

**Champs:**
- `id`: Identifiant unique
- `title`: Titre de l'annonce (200 caractères)
- `content`: Contenu de l'annonce (texte long)
- `pinned`: Si l'annonce est épinglée/mise en avant (false par défaut)
- `createdAt`: Date de création
- `updatedAt`: Date de modification

**Relations:**
- `createdBy`: Utilisateur (modérateur) qui a créé l'annonce (ManyToOne)

---

### 🗄️ Diagramme des relations

```
User ──────────┬────────────> Role (ManyToMany)
               └────────────> Researcher (OneToOne)
                                    │
Researcher ────────────────> Domain (ManyToOne)
               │
               └────────────> Publication (ManyToMany)
                                    │
                           Domain (ManyToOne)

Announcement ──────────────> User (ManyToOne - createdBy)
```

---

## 🔧 Configuration

### application.properties
```properties
spring.application.name=webProject
```

### Technologies utilisées
- **Spring Boot 4.0.5**: Framework web moderne
- **Spring Data JPA**: ORM avec Hibernate
- **Spring Security**: Authentification et autorisation
- **PostgreSQL 16**: Base de données relationnelle
- **Lombok**: Réduction du boilerplate code
- **Maven**: Gestionnaire de dépendances et build

---

## 📝 Notes de développement

- Les timestamps (`createdAt`, `updatedAt`) sont gérés automatiquement par Hibernate
- Tous les utilisateurs et chercheurs doivent avoir des emails et usernames uniques
- Les publications commencent en état DRAFT par défaut
- Les relations sont configurées en `LAZY` loading pour optimiser les performances
- Les données sensibles (mots de passe) doivent être hachées avant stockage

---

## 📞 Contacts et Support

Pour toute question ou problème, veuillez contacter l'équipe de développement.

---

**Dernière mise à jour**: Mars 2026" 
