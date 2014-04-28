Gradient: 
========
##### _Taking it to the next level._

Gradient is a fork of Ancestra Remake, with the emphasis on optimal performance, minimal memory consumption, and fault-tolerance. (May be rewritten in the future)

#### Encore une autre reprise d'Ancestra ... Qu'est ce qu'il y a de différent ici ?
Oui, encore un autre fork d'Ancestra. Mais dans ce fork, ce ne sont pas les fonctionnalités qui sont la priorité. En effet, la code-base d'Ancestra est la pire que j'ai jamais vu, au sens propre du terme. Mais déjà, trop a été fait dans Ancestra, donc, tout refaire à zéro serait pas très malin. Ansi, j'essaierai avec les développeurs qui souhaitent y contribuer, à rendre le code plus _malin_.

#### La liste TODO du fork :
Pas grand chose, j'ajouterai plus en avançant, mais si vous avez des propositions, n'hésitez pas à _forker_ le repo, ou ouvrir une _issue_ sur le sujet.

* Use proper containers whenever possible.

* Use cached PreparedStatements, and provide smart cache for templates like Maps, Items, etc. _By Smart Cache, I mean instead of loading all Items for examples, keep records of stats about how many times an item was request/loaded from database, and cache items according to these stats._

* Better handling of errors and exceptions, with extensive logging system. _Faut-tolerance_

* Split the SQLManager into several classes for better navigation, and better performance also in some points;

* Split the PacketsManager, use a per-Action basis: Every packet processing task is a runnable that runs on the client thread or in a thread pool.

* Use Non-Blocking networking with Apache MINA or Netty. _(Need to dig deeper into their functionning to decide)_

* Scripting to handle IA: In JVM Javascript engine with well-designed API. _Still need to work on the API_

#### Fork-it, let me Pull it :D
Ne soyez pas timide.
