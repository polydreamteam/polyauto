Bienvenue sur le site PolyAuto par la polydreamteam composée de : 
- Cluniat Philippine 
- Baccuet Anthony
- Renard Jonathan
- Chlebowski Quentin
- Ensuque Élie
- Cellard Fabien

Pour tester le projet nous vous invitons à récupérer dans un premier temps la configuration wildfly qui se trouve dans le fichier ressources et à éxecuter le script
SQL qui se trouve lui aussi dans ce dossier.

Vous trouverez aussi dans ce dossier les archives pré-compilé du projet que vous n'aurez plus qu'à déposez dans vos déploiements. Pour les fichiers déjà compilés, les utilisateurs mysql sont :
Pour le client : login:polyauto et mdp:polyauto
Pour le serveur : login:polyautojms et mdp:polyautojms

Il faut aussi avoir créer un utilisateur jmsuser avec comme mot de passe jmsepul98! ayant les droits d'ecrire dans le topic. En utilisant le fichier standalone.xml fourni, il faut ajouter le role guest à jmsuser
dans le fichier standalone/configuration/application-roles.properties (fichier également présent dans le dossier ressource de ce projet)

Lancez votre serveur wildfly et une fois que celui-ci est opérationnel rendez vous sur le lien suivant :

/!\ Site optimisé sur Chrome, des problèmes peuvent survenir sur Firefox
https://polydreamteam.github.io/polyauto-front/

Pour accéder au backoffice, il faut accéder au lien suivant :
localhost:8080/PolyAuto/admin/login

Si vous souhaitez recompiler ou explorer les sources java nous vous invitons à aller voir dans les dossier Client et Serveur.
Ces deux dossiers sont des projets intellij qu'il faut ouvrir séparément.

Le dossier Web contient tout à lui toute l'architecture front de notre site qui a été réalisé avec Angular.

