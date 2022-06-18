
## 📚 Enoncé

1. Le système d'alarme ne peut avoir qu'un seul client gestionnaire. Par défaut son nom est "Client" et son mot de passe
   est "1234".
2. Le client gestionnaire peut ajouter et effacer des "habitants" au système. Par défaut il est déjà un habitant.
3. Le client gestionnaire peut renoncer à ses droits de gestionnaire (et redevenir un habitant normal) en spécifiant le
   nom du nouveau client gestionnaire.
4. Le client gestionnaire peut spécifier que l'appartement est composée de zones (typiquement des pièces).
5. Chaque zone a un nom, et une courte description.
6. Une zone peut contenir d'autres zones (typiquement dans une maison sur plusieurs étages, le client peut décider de
   créer une zone par étage, et chaque étage a des zones qui sont les pièces de l'étage)
7. Chaque dispositif du système d'alarme doit être associée à une zone
8. Le client gestionnaire doit pouvoir associer/modifier l'association d'un dispositif à une zone
9. Le client gestionnaire doit pouvoir effacer une zone
10. Le client pouvoir doit pouvoir connaître tous les dispositifs dans une zone donnée et pouvoir activer/désactiver (en
    groupe) les dispositifs d'une zone donnée.
11. Chaque dispositif, lorsqu'il est déclenché (cela en concerne pas les sirènes mais seulement les dispositifs qui
    décèlent une intrusion) doit envoyer un message de déclenchement à la centrale d'alarme qui déclenche des actions d'
    alerte
12. Les actions d'alerte (éditable par le client gestionnaire) peuvent être l'allumage des sirènes, l'envoi de sms aux
    numéros renseignés dans le système, l'enregistrement des images des caméras, etc...

## 🎩 Scénarios

- Création des habitants
- Création des zones
- Gestion des dispositifs

## 💾 Persistance

Grâce aux fichiers type ```XXX.txt```

## 🚀 Run

Pour utiliser le programme :

- Build le programme via le compilateur de Eclipse / Intellij IDEA
