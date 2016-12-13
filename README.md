# MiniProjet-2

## Installation et exécution
Charger le projet dans votre IDE préféré (Intellij, Eclipse...). Aucun librairie externe n'est nécessaire pour exécuter le jeu.
Dans le dossier source (`src`), cherchez le fichier `Program.java` se trouvant dans le paquet `platform`.
Cette classe contient une méthode `main(String)` ; de là votre IDE devrait correctement gérer l'exécution du programme.

Assurez vous de posséder la version java `1.7` (ou supérieure), sans quoi le programme ne pourra pas s'exécuter.
## Commandes

 * **→** : Se déplacer à droite
 * **←** : Se déplacer à gauche
 * **↑** : Sauter
 * **E** : Activer ou désactiver le levier
 * **B** : Souffler
 * **ESPACE** : Lancer des boules de feu
 * **ECHAP** : Retour au menu de sélection des niveaux

## Niveaux

Les niveaux rendus sont **Level 4** et **Level 5**.

Liste des niveaux sélectionnables depuis le **menu** du jeu. Lorsqu'un niveau est terminé, son icône devient **dorée**.

Vous pouvez **revenir** au menu de sélection à tout moment en appuyant sur la touche **ECHAP**.

### Niveau 1 : "A l'abordage!"
Bienvenue dans le monde merveilleux de notre jeu. C'est aussi l'occasion de découvrir le Jumper, qui va vous accompagner tout au long de cette aventure. :-)

### Niveau 2 : "Effet de levier"
Tiens, mais à quoi sert donc ce levier? Essayez de l’activer pour voir. La porte s’ouvre merveilleusement vers le prochain niveau. Un petit détour sera probablement nécessaire si vous souhaitez ne pas vous blesser en chemin.

### Niveau 3 : "De toutes les couleurs"
Si vous ne bougez pas, vous prendrez probablement la clé rouge en atterrissant. Et si vous avez l’oeil, vous remarquerez que le bloc rouge disparait aussitôt. Vous en déduisez que pour faire disparaitre les autres blocs et sortir de ce niveau, il vous faut prendre les autres clés. Faites attention en prenant la clé bleue, plusieurs pièges soigneusement préparés sont au rendez-vous. Et n’oubliez pas d’activer le levier pour ouvrir la porte, sinon vous resterez coincé en bas!

Vous pouvez recommencer le niveau à tout moment en revenant au menu avec**ECHAP**.

Si vous arrivez à sortir de ce niveau sans égratignure, vous commencez vraiment à devenir bon. :-)

### Niveau 4 : "Le casse-tête chinois" - niveau officiel 1
Les choses commencent à se corser... Dans ce château lugubre, l’observation est de mise. Foncer tête baissée n'est clairement pas la meilleure stratégie.

Au début du niveau, vous n'avez qu'une possibilité : utiliser les tremplins pour vous propulser jusqu'en haut. Vous arrivez alors au niveau d'un levier qui semble faire bouger certaines plateformes lorsqu'il est activé, tout en conditionnant l’ouverture de la porte.   Que pouvez-vous bien faire?


Scénario typique 1: Vous suivez votre intuition et activez le levier.
Vous prenez la clé orange grâce à la plateforme métallique. Vous vous y attendiez, le bloc orange disparait, vous êtes un peu plus proche de la victoire. Que se passe-t-il si vous allumez la torche avec une boule de feu? Malheur, le passage se referme sur vous! Il ne vous reste plus qu’à éteindre la torche, et à refaire un tour pour désactiver le levier, vu qu’il n’y a aucune autre possibilité. Cette fois-ci, en allumant la torche, comme par magie, la clé bleue devient accessible. Après l’avoir prise, il reste une dernière étape, qui consiste à réactiver le levier, afin d’ouvrir la porte de sortie, puis à rebrousser chemin en faisant attention à ne pas vous blesser. Ouf, c’est enfin terminé! 

Scénario typique 2: Vous faites le malin et n’activez pas le levier.
Effectivement, c’était la meilleure chose à faire, bravo! En allumant la torche, vous libérez l’accès aux clés bleue et orange. Après les avoir prises, vous faites comme à la fin du scénario 1, en activant le levier et en rebroussant chemin.

Notez qu’activer le levier initialement est donc un piège, qui ne fait que rendre les choses plus difficiles.

### Level 5 : "L’alignement final" - niveau officiel 2
Mais où est donc passée la sortie? Serait-elle derrière ce bloc de pierre, lui-même couvert par une torche? Comment y accéder?

Dans ce niveau final, les blocs droit et gauche se déplacent vers le centre à condition que le levier correspondant pointe vers une torche allumée, on parle alors d’"alignement". Quant au bloc central, il nécessite l’alignement des leviers droit et gauche à la fois. Enfin, il ne faut pas oublier d’éteindre TOUTES les torches, afin de pouvoir ouvrir la porte de sortie… Alors, vous avez trouvé comment faire?

Une des façons de sortir de ce niveau est la suivante:
-Allumer les torches droite et gauche
-Pointer chaque levier vers sa torche allumée
-Allumer la torche centrale
-Pointer les leviers vers la torche centrale
-Éteindre les torches droite et gauche
(Astuce: Monter sur les plateformes métalliques!)
-Sauter vers la porte de sortie tout en éteignant la torche centrale… voilà, c’est gagné!

Il existe d’autres façons de sortir de ce niveau, libre à vous de les essayer à vos risques et périls. :-)


