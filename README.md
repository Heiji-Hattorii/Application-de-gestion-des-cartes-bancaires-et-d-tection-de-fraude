# Application-de-gestion-des-cartes-bancaires-et-d-tection-de-fraude
# Gestion des cartes bancaires et détection de fraude

## Contexte
Application Java 17 pour gérer les cartes bancaires (débit, crédit, prépayée), suivre les transactions et détecter automatiquement les fraudes (ex. opérations suspectes ou dépassement de plafond).

---

## Architecture
- **UI** : interface textuelle pour interagir avec l’utilisateur.  
- **Service** : logique métier (gestion cartes, opérations, alertes de fraude, rapports).  
- **Entity** : objets persistants (`Client`, `Carte`, `OperationCarte`, `AlerteFraude`).  
- **DAO** : accès à la base de données (CRUD).  
- **Utils** : fonctions transversales (fraude, dates, génération de numéros de carte).  

---

## Fonctionnalités
- Créer un client  
- Émettre une carte (débit, crédit, prépayée)  
- Effectuer une opération (achat, retrait, paiement en ligne)  
- Consulter l’historique d’une carte  
- Lancer l’analyse des fraudes  
- Bloquer ou suspendre une carte  

---

## Techniques
- Java 17, record, sealed, Stream API, Optional  
- JDBC + MySQL  
- Architecture en couches (Entity, DAO, Service, UI)  
- Gestion des exceptions  
