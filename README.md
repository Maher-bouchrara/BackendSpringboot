# Backend Spring Boot + PostgreSQL

Backend REST API avec 58 endpoints, 6 entités, Services CRUD et PostgreSQL via Docker.

## 🚀 Démarrage Rapide (4 Étapes)

### 1️⃣ Lancer PostgreSQL (30 secondes)
```bash
sudo docker compose -f Docker-compose.yaml up -d
```
Vérification: `sudo docker ps`

### 2️⃣ Télécharger les dépendances (3-5 minutes)
```bash
./mvnw clean install
```

### 3️⃣ Lancer l'application (2-3 minutes)
```bash
./mvnw spring-boot:run
```
✅ Accessible sur: **http://localhost:8080**

### 4️⃣ Tester les APIs (1 minute)
```bash
curl http://localhost:8080/api/domains
```

---

## 📊 Architecture

```
Controllers (58 endpoints) → Services (68 méthodes CRUD) → Repositories → PostgreSQL (Docker)
```

## 📋 Endpoints (58 Total)

| Entité | Endpoints | Exemple |
|--------|-----------|---------|
| **Users** | 8 | `GET /api/users`, `POST /api/users/{id}` |
| **Roles** | 6 | `GET /api/roles`, `POST /api/roles` |
| **Researchers** | 10 | `GET /api/researchers`, `POST /api/researchers` |
| **Publications** | 10 | `GET /api/publications`, `POST /api/publications` |
| **Domains** | 7 | `GET /api/domains`, `POST /api/domains` |
| **Announcements** | 7 | `GET /api/announcements`, `POST /api/announcements` |

---

## 💻 Exemples Requêtes

### Créer un Domain
```bash
curl -X POST http://localhost:8080/api/domains \
  -H "Content-Type: application/json" \
  -d '{"name":"IT","description":"Domain"}'
```

### Créer un User
```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{"firstName":"John","lastName":"Doe","email":"john@example.com","username":"john","password":"pass123","enabled":true}'
```

### Lister tous
```bash
curl http://localhost:8080/api/users
curl http://localhost:8080/api/domains
curl http://localhost:8080/api/researchers
```

---

## ⚙️ Configuration

| Paramètre | Valeur |
|-----------|--------|
| App Port | 8080 |
| DB Host | localhost |
| DB Port | 5432 |
| DB Name | webproject_db |
| DB User | postgres |
| DB Password | postgres |

---

## 🛠️ Commandes Utiles

```bash
# Arrêter PostgreSQL
sudo docker compose down

# Voir les logs PostgreSQL
sudo docker logs webproject_db_container

# Voir les conteneurs actifs
sudo docker ps

# Accéder à PostgreSQL
sudo docker exec -it webproject_db_container psql -U postgres -d webproject_db

# Compiler sans tests
./mvnw clean install -DskipTests
```

---

## 📁 Structure

```
src/main/java/com/project/webProject/
├── controllers/     (6 fichiers - 58 endpoints)
├── services/        (6 fichiers - 68 méthodes)
├── repositories/    (6 fichiers - accès BD)
├── entities/        (6 entités)
└── enums/           (énumérations)
```

---

## ⚠️ Important

✅ PostgreSQL AVANT l'application  
✅ Tables créées automatiquement  
✅ CORS activé  
✅ Logging SQL activé  

**Total: 18 fichiers Java | 58 endpoints | 6 entités | ✅ Prêt**

