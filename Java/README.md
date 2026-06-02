# Gilded Rose - Refactoring & Feature Conjured

Ce projet contient une version nettoyée, lisible et évolutive du **Gilded Rose Kata** réalisée par mes soins en Java, incluant la nouvelle fonctionnalité **Conjured**.

## Structure du Code

Le code a été entièrement refactorisé pour supprimer les `if/else` imbriqués et isoler la logique par type de produit.

Chaque type d'item possède sa propre méthode dédiée, ce qui rend le code séquentiel et très facile à maintenir.

## Suite de Tests

Le projet contient une suite de tests unitaires structurée avec `@Nested` sous JUnit 5. Chaque composant possède son propre groupe de tests pour valider les comportements nominaux et les cas spéciaux.

Bonne visite,
Yasser
