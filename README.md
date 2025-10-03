# Design Pattern Décorateur

## I. Catégorie et Définition

Le Pattern Décorateur appartient à la catégorie **Structure** des modèles de conception du GoF (Gang of Four).

### Définition et Objectif

L'objectif principal du Pattern Décorateur est d'attacher dynamiquement des responsabilités supplémentaires à un objet.

1. **Extension Dynamique** : Au moment de l'exécution, un objet peut se voir ajouter de nouveaux comportements ou des opérations.
2. **Alternative Souple à l'Héritage** : Il fournit une alternative souple à la dérivation (l'héritage) pour étendre les fonctionnalités d'un objet. L'héritage seul peut conduire à une "explosion de classes" et à une conception rigide si l'on tente de représenter toutes les combinaisons de fonctionnalités.

Le Décorateur permet d'isoler les responsabilités supplémentaires d'un objet.

---

## II. Structure et Mécanisme

Le Pattern Décorateur s'appuie sur une combinaison de deux concepts fondamentaux de la POO pour fonctionner : l'héritage (extends) et la composition/agrégation (relation "A-UN" ou d'instance).

### A. Les Rôles Clés

1. **Composant Abstrait** (Boisson ou ComposantAbstrait) :
   - Définit l'interface (ou la classe abstraite) commune pour les objets de base (composants) et les décorateurs.
   - Déclare les méthodes principales, souvent abstraites (ex. : `coup()`, `operation()`).

2. **Composant Concret** (Sumatra, Expresso) :
   - Implémentation de base qui fournit les fonctionnalités initiales (par exemple, le prix de base d'une boisson).

3. **Décorateur Abstrait** (DecorateurAbstrait ou DecorateurIngredient) :
   - **Héritage** : Hérite du Composant Abstrait (ou l'implémente). Cela permet au décorateur d'être traité comme un composant, autorisant le chaînage.
   - **Composition** : Contient une référence vers un objet Composant Abstrait (par exemple, `protected Boisson boisson`). Cette référence est initialisée via un constructeur avec paramètre.

4. **Décorateurs Concrets** (Chocolat, Caramel) :
   - Classes qui ajoutent des responsabilités spécifiques. Elles héritent du Décorateur Abstrait.

### B. La Mécanique de Décoration (Chaînage)

Le client manipule une variable de type Composant Abstrait. En réalité, cet objet peut être un Composant Concret ou un Décorateur Concret.

1. **Création du Chaîne** :
   - La variable de référence du composant est successivement réaffectée avec de nouveaux décorateurs qui "enveloppent" l'objet précédent.

2. **Délégation et Récursivité** :
   - Lorsqu'une méthode décorée est appelée (ex. : `cout()` ou `operation()`) :
     - Le Décorateur Concret exécute son propre comportement supplémentaire (le "décor").
     - Il délègue ensuite l'appel à la même méthode de l'objet qu'il enveloppe (via l'appel `boisson.cout()`).
     - Ceci se produit récursivement le long de la chaîne jusqu'à ce que le Composant Concret d'origine soit atteint à la fin de la chaîne.

Dans l'exemple des boissons et des suppléments : le coût total est calculé en appelant la méthode `coup()` sur l'objet final, qui additionne le prix de son propre ingrédient au prix du composant qu'il décore, remontant ainsi la chaîne jusqu'à la boisson de base.

---

## III. Utilité et Applications

L'un des principaux principes de conception satisfaits par ce pattern est le **Principe Ouvert-Fermé** : les classes doivent être ouvertes à l’extension (on peut ajouter de nouveaux décorateurs) et fermées à la modification (on ne touche pas aux classes de composants de base).

### Applications Notables

- **Ajout de Responsabilités Métier** :
  - Le pattern permet d'ajouter des fonctionnalités supplémentaires (comme des traces de log, une gestion de buffer, ou des fonctionnalités métier spécifiques) à un objet sans modifier sa classe.

- **API Java I/O** :
  - L'API d'entrée/sortie (Input/Output) de Java est un exemple classique d'utilisation du Pattern Décorateur. Des objets sont chaînés (par exemple, `ZipOutputStream` décorant un autre flux) pour ajouter des fonctionnalités comme la compression ou la lecture tamponnée à la volée.

Le Décorateur est donc la solution privilégiée lorsque l'on souhaite étendre les fonctionnalités d'un objet en cours d'exécution sans engendrer une explosion combinatoire de classes dérivées.