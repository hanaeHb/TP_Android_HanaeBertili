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
  
![home1](https://github.com/user-attachments/assets/95d56d10-5a36-42fc-aad2-648bb2c26987)
![home2](https://github.com/user-attachments/assets/4a37df02-4993-41a8-b0c1-5363e7baf960)
![home3](https://github.com/user-attachments/assets/bea7a4b5-b678-48c7-938b-b6769787c6a3)
![home4](https://github.com/user-attachments/assets/cbef8298-d9d7-4a45-819f-6b9a178aec37)

---

- Par défaut, l'application utilise un thème Light adapté aux produits cosmétiques, L'utilisateur peut changer de thème vers un mode Calme selon ses préférences visuelles ou l’environnement lumineux.
    
![home5](https://github.com/user-attachments/assets/b37e0e67-ce60-42a7-9297-728a91fbc016)

## Thème Calme
  
![home6](https://github.com/user-attachments/assets/037c1fe6-fac6-4bac-8bad-8f64ca52f2c1)

---

- L’application est disponible en anglais par défaut, avec possibilité de changer vers français ou arabe
  
![home7](https://github.com/user-attachments/assets/7b206a5b-af85-4c59-921a-af089c513b35)

---

## Français
  
![home8](https://github.com/user-attachments/assets/9f8187c1-3e7a-414b-bb24-bed5898597e7)

## Arabe
  
![home9](https://github.com/user-attachments/assets/7460f721-183e-4573-8302-3655af356b04)

---

# Product Details Page

- La page de détails permet à l’utilisateur de consulter toutes les informations importantes concernant un produit spécifique de soins coréens. Elle est conçue pour offrir une vue claire, complète et attrayante du produit sélectionné.

![detaills1](https://github.com/user-attachments/assets/dee2d00c-3522-43e1-9d60-bf20bdb1b9f4)
![detaills2](https://github.com/user-attachments/assets/88f2660a-b63a-457e-9588-b2e35b006134)

---

# Favorite Page

- La page des favoris permet à l’utilisateur de retrouver tous les produits qu’il a ajoutés à sa liste de souhaits pour y accéder plus rapidement et les consulter à tout moment.

![favorite1](https://github.com/user-attachments/assets/aae4c050-9142-470f-b444-73193abc39f7)

---

# Category Page

- La page Catalogue permet à l’utilisateur d’explorer facilement tous les produits de l’application en parcourant les marques (brands) disponibles, puis les catégories associées à chaque marque.

## Brands Page

![category1](https://github.com/user-attachments/assets/82e7a982-262a-4790-94e1-bf186f1157d1)

---

## Brand's Category Page

![category2](https://github.com/user-attachments/assets/9a5cc7bb-b366-4fa4-8e24-72a1fc371a09)

---

## Page displaying all products belonging to this Brand and Category

![category3](https://github.com/user-attachments/assets/ae4fe819-b289-487b-a77a-c6cb2ce36b17)

---

# Cart Page

- La page Panier permet à l’utilisateur de gérer sa sélection de produits avant de passer à la commande. Elle inclut la gestion dynamique des quantités, des prix, et du stock.

![cart1](https://github.com/user-attachments/assets/23cd4c20-768b-48f9-904e-eb4315bf6338)

---

- Lorsqu’un utilisateur essaie de choisir une quantité supérieure au stock, l’application affiche  un message d’erreur clair :

![cart2](https://github.com/user-attachments/assets/b6eb61d3-794f-4046-95ad-4e4582e30547)

---

# Checkout Page

- La page Checkout permet à l’utilisateur de finaliser sa commande en remplissant ses informations, en personnalisant l’emballage avec un cadeau, puis en effectuant le paiement.

![checkout1](https://github.com/user-attachments/assets/5b7ac870-c8c2-44b1-ac06-e1d2e1323c80)
![checkout2](https://github.com/user-attachments/assets/c173e870-8205-4d10-8ad0-9bf1fa19d5e5)
![checkout3](https://github.com/user-attachments/assets/6c78ab76-e4c9-4dc4-ac2b-935df884bdcc)
![checkout4](https://github.com/user-attachments/assets/8169d154-b953-4a70-962e-2da85ed9400f)

---

# Tracking Page

- La page Suivi de commande permet au client de suivre en temps réel l’évolution de sa commande, depuis la validation jusqu’à la livraison finale.

![tracking1](https://github.com/user-attachments/assets/e5d2eab2-7767-4a7a-b6b5-f5b79ee83af9)
![image](https://github.com/user-attachments/assets/3c621267-5f8d-4f33-af5c-1468827d9b25)
![tracking5](https://github.com/user-attachments/assets/aa2b2fda-dea3-4354-a4da-a0d76ce893d1)
![tracking4](https://github.com/user-attachments/assets/cb26eb3a-d64e-45ac-8f3e-f614514e2ba3)

- Estimation de livraison : Un message au-dessus de la timeline informe le client de la date estimée d’arrivée

![tracking2](https://github.com/user-attachments/assets/520c72bc-4a13-4928-8bab-31e3ad8c84ac)

---

# Admin Page

- La page Admin est une interface réservée à l’administrateur de l’application. Elle permet de gérer les utilisateurs, de surveiller les achats, et de suivre les stocks en temps réel.

## Users

- Pour chaque utilisateur, l’admin peut bloquer le compte, Si un utilisateur est bloqué, il ne peut plus se connecter ni se réinscrire avec le même email.

![admin1](https://github.com/user-attachments/assets/fd9d88b5-0365-48d2-91e8-58e618d2661b)

---

## User's Products

- L’admin peut voir tous les produits achetés par chaque utilisateur : Information du produit, Quantité achetée 

![admin2](https://github.com/user-attachments/assets/960fc6a1-8909-4c61-bec8-d428d8a26b97)

---

## Products

- Affichage de la liste complète des produits disponibles dans l’application

![admin4](https://github.com/user-attachments/assets/cbd6891f-3eab-461b-9bac-b3819aadc2d6)

## Products's Customer

- Pour chaque produit (par Button Show Buyers), Voir les utilisateurs qui ont acheté ce produit

![admin5](https://github.com/user-attachments/assets/fa7cc4dd-2f8c-4ddd-a2e2-4584f60837e8)




























