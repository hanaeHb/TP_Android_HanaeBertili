# APPLICATION MOBILE ECOMMERCE SKIN CARE "여자_SKIN"

Une application mobile de e-commerce dédiée aux produits de soins coréens (K-Beauty), permettant aux utilisatrices de découvrir, filtrer et acheter des produits de beauté de manière intuitive et moderne.

---

## Fonctionnalités

- Parcourir les produits de beauté coréens (par catégorie, marque...)
- Ajouter aux favoris
- Ajouter au panier et passer une commande
- Suivre les commandes
- Authentification utilisateur (inscription / connexion)
- Espace admin : voir les utilisateurs et les commandes
- Application multilingue (français, arabe, coréen)
- Design moderne avec Jetpack Compose

---

## Technologies utilisées

- **Kotlin** + **Jetpack Compose** pour l'UI
- **MVI** pour la gestion des intentions utilisateur
- **Hilt** pour l’injection de dépendances
- **Navigation Compose** pour la navigation entre les écrans
- **Coil** pour le chargement d’images
- **Retrofit + Gson** pour les appels API (si connecté à une base distante)

---

## Installation et Dépendances

Assurez-vous que les dépendances suivantes sont présentes dans `build.gradle` (Module) :

```kotlin
// Jetpack Compose UI
implementation("androidx.compose.ui:ui:1.4.0")
implementation("androidx.compose.foundation:foundation:1.4.0")
implementation("androidx.compose.material:material:1.4.0")
implementation("androidx.compose.material:material-icons-extended:1.6.1")
implementation("androidx.compose.ui:ui-text-google-fonts:1.7.8")

// Chargement d’images
implementation("io.coil-kt:coil-compose:2.2.2")

// Hilt - Injection de dépendances
implementation("com.google.dagger:hilt-android:2.56.1")
kapt("com.google.dagger:hilt-compiler:2.56.1")
implementation("androidx.hilt:hilt-navigation-compose:1.1.0")

// API REST
implementation("com.squareup.retrofit2:retrofit:3.0.0")
implementation("com.squareup.retrofit2:converter-gson:3.0.0")
implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")
```
---

# Lancement de l’application

Voici les étapes pour exécuter l’application mobile sur un appareil réel ou un émulateur :

### 1. Cloner le projet

```bash
git clone https://github.com/hanaeHb/TP_Android_HanaeBertili.git
```
---

## 2. Ouvrir le projet
- Ouvrir Android Studio

- Cliquer sur "Open" et sélectionner le dossier du projet

- Attendre la synchronisation de Gradle

---

## 3. Brancher l’appareil (ou utiliser un émulateur)

- Activer le mode développeur sur votre téléphone

- Activer le débogage USB

- Brancher l’appareil via câble USB

- Sinon, lancer un émulateur Android (API 30+)

---

## 4. Lancer l’application
- Sélectionner l’appareil dans Android Studio

- Cliquer sur Run pour exécuter l’application

---

# Logo d'application
- Le flocon de neige symbolise la pureté, la fraîcheur et la douceur, des qualités liées à une peau saine et lumineuse. Le nom "여자_SKIN" est symbolique : "여자" signifie femme en coréen et "SKIN" veut dire peau en anglais, ce qui montre que l’application est dédiée aux femmes et aux soins de la peau.

![WhatsApp Image 2025-06-28 à 00 10 56_c1423f6f](https://github.com/user-attachments/assets/1ca42bcc-69e5-45ad-9e57-211f77325446)





