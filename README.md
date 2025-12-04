# 🚌 Smart City Mobility Service - REST API

[![Java](https://img.shields.io/badge/Java-17-blue.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.0-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![MongoDB](https://img.shields.io/badge/MongoDB-6.0-green.svg)](https://www.mongodb.com/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

## 📋 Table des Matières
- [Aperçu du Projet](#-aperçu-du-projet)
- [Fonctionnalités](#-fonctionnalités)
- [Architecture](#-architecture)
- [Technologies Utilisées](#-technologies-utilisées)
- [Installation](#-installation)
- [API Endpoints](#-api-endpoints)
- [Exemples d'Utilisation](#-exemples-dutilisation)
- [Structure du Projet](#-structure-du-projet)
- [Contribuer](#-contribuer)
- [Auteurs](#-auteurs)
- [Licence](#-licence)

## 🏙️ Aperçu du Projet

Ce projet fait partie d'une **plateforme de services urbains intelligents** développée dans le cadre du cours **Service Oriented Computing** (3ème année Ingénierie GINF - 2025/2026).

**Service REST** pour la mobilité intelligente en Tunisie, permettant aux citoyens d'accéder aux informations de transport public en temps réel.

### 🎯 Contexte
- **Matière** : Service Oriented Computing
- **Année** : 2025-2026
- **Thème** : Plateforme intelligente de services urbains interopérables
- **Université** : Ecole Nationale d'Ingénieurs de Carthage (ENICarthage)

## ✨ Fonctionnalités

### 🚍 Gestion des Transports
- ✅ Consultation des lignes de transport (Métro, Bus, Train, Tram)
- ✅ Recherche par type, numéro de ligne, ou station
- ✅ Affichage des horaires en temps réel
- ✅ Suivi des retards et annulations
- ✅ État du trafic en direct

### 🔧 Fonctionnalités Techniques
- ✅ API RESTful complète avec Spring Boot
- ✅ Persistance des données avec MongoDB
- ✅ Documentation OpenAPI/Swagger intégrée
- ✅ Conteneurisation Docker
- ✅ Tests unitaires et d'intégration
- ✅ Validation des données
- ✅ Gestion des erreurs centralisée

### 📊 Données Tunisiennes
- ✅ Dataset réaliste des transports tunisiens
- ✅ Lignes TGM, Métro Léger de Tunis
- ✅ Bus urbains (28, 35, 50, 63, 202)
- ✅ Trains banlieue (Tunis-Rades, Tunis-Bizerte)
- ✅ Situations de trafic typiques (manifestations, matchs, travaux)


## 🛠️ Technologies Utilisées

| Technologie | Version | Description |
|------------|---------|-------------|
| **Java** | 17 | Langage de programmation principal |
| **Spring Boot** | 3.1.0 | Framework backend |
| **Spring Data MongoDB** | 3.1.0 | Persistance des données |
| **MongoDB** | 6.0+ | Base de données NoSQL |
| **SpringDoc OpenAPI** | 2.1.0 | Documentation API |
| **Lombok** | 1.18.28 | Réduction du code boilerplate |
| **Docker** | 24.0+ | Conteneurisation |
| **Maven** | 3.8+ | Gestion des dépendances |
| **JUnit 5** | 5.9+ | Tests unitaires |

## 🚀 Installation

### Prérequis
- Java 17 ou supérieur
- Maven 3.8+
- MongoDB 6.0+
- Docker (optionnel)

### Exécution Locale
```bash
# 1. Cloner le projet
git clone https://github.com/azizsnd/mobility.git
cd mobility

# 2. Démarrer MongoDB
# Sur Windows/Linux/Mac, selon ton OS
mongod

# 3. Compiler et exécuter
mvn clean install
mvn spring-boot:run

# L'application sera disponible sur : http://localhost:8080
```
## 🐳 Docker & Conteneurisation

### Prérequis
- Docker Desktop 24.0+ (ou Docker Engine + Docker Compose)
- 4GB RAM minimum

#Commandes Docker utiles
```bash
# Construire l'image
docker build -t mobility-service .

# Lancer l'application seule
docker run -p 8080:8080 mobility-service

# Voir les logs
docker-compose logs -f mobility-app

# Arrêter les services
docker-compose down

# Nettoyer tout
docker-compose down -v --rmi all

# Accéder à MongoDB
docker exec -it mobility_mongodb mongosh city_mobility_db
```
