Bienvenue sur le site PolyAuto par la polydreamteam compos�e de : 
- Cluniat Philippine 
- Baccuet Anthony
- Renard Jonathan
- Chlebowski Quentin
- Ensuque �lie
- Cellard Fabien

Pour tester le projet nous vous invitons � r�cup�rer dans un premier temps la configuration wildfly qui se trouve dans le fichier ressources et � �xecuter le script
SQL qui se trouve lui aussi dans ce dossier.

Vous trouverez aussi dans ce dossier les archives pr�-compil� du projet que vous n'aurez plus qu'� d�posez dans vos d�ploiements. Pour les fichiers d�j� compil�s, les utilisateurs mysql sont :
Pour le client : login:polyauto et mdp:polyauto
Pour le serveur : login:polyautojms et mdp:polyautojms

Il faut aussi avoir cr�er un utilisateur jmsuser avec comme mot de passe jmsepul98! ayant les droits d'ecrire dans le topic. En utilisant le fichier standalone.xml fourni, il faut ajouter le role guest � jmsuser
dans le fichier standalone/configuration/application-roles.properties (fichier �galement pr�sent dans le dossier ressource de ce projet)

Lancez votre serveur wildfly et une fois que celui-ci est op�rationnel rendez vous sur le lien suivant :

/!\ Site optimis� sur Chrome, des probl�mes peuvent survenir sur Firefox
https://polydreamteam.github.io/polyauto-front/

Pour acc�der au backoffice, il faut acc�der au lien suivant :
localhost:8080/PolyAuto/admin/login

Si vous souhaitez recompiler ou explorer les sources java nous vous invitons � aller voir dans les dossier Client et Serveur.
Ces deux dossiers sont des projets intellij qu'il faut ouvrir s�par�ment.

Le dossier Web contient tout � lui toute l'architecture front de notre site qui a �t� r�alis� avec Angular.

