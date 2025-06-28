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

---

# Login Page
- La page de connexion constitue une étape essentielle dans l'accès personnalisé à l'application. Elle permet aux utilisateurs, qu’ils soient clients ou administrateurs, de s’authentifier via leur adresse email et mot de passe.
![login1](https://github.com/user-attachments/assets/fbe24e62-1882-4791-8d7e-352ca86acff4)
![login2](https://github.com/user-attachments/assets/d6367194-ebfe-45e4-9148-44f90c665b19)

---

# Refister Page
- La page d'inscription permet aux nouveaux utilisateurs de créer un compte personnel afin d'accéder aux fonctionnalités de l'application.
![register1](https://github.com/user-attachments/assets/9c09101b-cdd1-432e-b274-e56c57347734)
![register2](https://github.com/user-attachments/assets/f78c8dab-6063-4d50-9dcc-c3d8c2262f55)

---

# Home Page
- La page d’accueil constitue le cœur de l’application e-commerce. Elle propose une interface riche, intuitive et personnalisable, regroupant l’ensemble des produits de soins coréens disponibles à l’achat.
- 
![home1](https://github.com/user-attachments/assets/95d56d10-5a36-42fc-aad2-648bb2c26987)
![home2](https://github.com/user-attachments/assets/4a37df02-4993-41a8-b0c1-5363e7baf960)
![home3](https://github.com/user-attachments/assets/bea7a4b5-b678-48c7-938b-b6769787c6a3)
![home4](https://github.com/user-attachments/assets/cbef8298-d9d7-4a45-819f-6b9a178aec37)

- Par défaut, l'application utilise un thème Light adapté aux produits cosmétiques, L'utilisateur peut changer de thème vers un mode Calme selon ses préférences visuelles ou l’environnement lumineux.
  
![home5](https://github.com/user-attachments/assets/b37e0e67-ce60-42a7-9297-728a91fbc016)

- Thème Calme
![home6](https://github.com/user-attachments/assets/037c1fe6-fac6-4bac-8bad-8f64ca52f2c1)

- L’application est disponible en anglais par défaut, avec possibilité de changer vers français ou arabe
![home7](https://github.com/user-attachments/assets/7b206a5b-af85-4c59-921a-af089c513b35)

---

- Français
![home8](https://github.com/user-attachments/assets/9f8187c1-3e7a-414b-bb24-bed5898597e7)

- Arabe
![home9](https://github.com/user-attachments/assets/00ec4744-828f-4bc6-8d41-15b872259328)
















