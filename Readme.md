Alexis LELOUP & Simon LECORDIER

# Rapport TP3 MMM

## Introduction

Le TP3 consiste en la mise en place de Firebase et sa synchronisation. Ayant déjà utilisé Firestore durant la Hackaton, nous avons choisi d’utiliser Realtime Database.
Le corriger des TP1 et 2 étant disponible, nous avons choisi de nous en inspiré pour réaliser ce TP3, ayant peu de temps à cause de l’alternance.
Notre TP3 est donc une application de taches en temps réel et non une application d’ajout de clients, le système reste cependant le même. Une démo est disponible dans les fichiers joints.

## Présentation
![Premier fragment](https://i.ibb.co/QvnjB8H/pic1.png)

La page d’accueil contient la liste des taches à réaliser, il est possible de supprimer les taches en les « slidant ».

![Second fragment](https://i.ibb.co/xzLpqYF/pic2.png)

La page d’ajout dispose d’un menu pour réinitialiser les champs des formulaires.

![Console Firebase Realtime Database](https://i.ibb.co/zHtsVfz/pic3.png)

Nous utilisons une base de données en temps réel, les données de la base de données sont écoutées en temps réel, une modification sur celle-ci affecte donc l’application.

## Mise en oeuvre

L’application utilise deux fragments correspondants aux deux vues, ceux-ci sont liés à une classe DAO gérant les interactions avec Firebase. Dans le premier fragment, le RecyclerView utilise un adapter « NoteListAdapter » permettant les interactions avec le RecyclerView par l’utilisateur.

![Arborescence de l’application](https://i.ibb.co/X8xmTFN/pic4.png)