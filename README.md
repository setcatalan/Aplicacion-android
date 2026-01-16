En aquesta part del projecte s'ha implementat un apartat de cerca i visualització de videojocs, l'objectiu del qual és mostrar una llista inicial de jocs i permetre a l'usuari filtrar per nom i per categoria.
A més, s'ha treballat l'splash screen de l'aplicació per millorar l'experiència inicial.
S'ha desenvolupat una pantalla dedicada a la visualització de videojocs amb els elements següents:

Títol de la secció
Camp de cerca per filtrar jocs per nom
Botons de categoria (Tots, Acció, RPG, Aventura)
RecyclerView per mostrar la llista de jocs
Disseny adaptatiu usant ConstraintLayout

Per simular el funcionament de l'app sense base de dades ni API, s'ha creat una llista mock de videojocs, que es carrega automàticament en iniciar la pantalla.
Això permet provar cerca i filtres

S'utilitza un RecyclerView per mostrar els jocs de manera eficient.

La pantalla permet:
Filtrar per text mentre s'escriu al cercador
Filtrar per categoria usant botons
Combinar ambdós filtres simultàniament
La lògica de filtratge es gestiona a la pròpia JocsActivity, separant clarament:
Dades originals
Dades filtrats
