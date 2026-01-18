### <b>Aplicació</b>
#### Set Catalán i Izan Duarte

---

# Activitat 07-Menú-Llista-Filtre

En aquesta part del projecte s'ha implementat un apartat de cerca i visualització de videojocs, l'objectiu del qual és mostrar una llista inicial de jocs i permetre a l'usuari filtrar per nom i per categoria.
A més, s'ha treballat l'splash screen de l'aplicació per millorar l'experiència inicial.

S'ha desenvolupat una pantalla dedicada a la visualització de videojocs amb els elements següents:
- Títol de la secció
- Camp de cerca per filtrar jocs per nom
- Botons de categoria (Tots, Acció, RPG, Aventura)
- RecyclerView per mostrar la llista de jocs
- Disseny adaptatiu usant ConstraintLayout

Per simular el funcionament de l'app sense base de dades ni API, s'ha creat una llista mock de videojocs, que es carrega automàticament en iniciar la pantalla. Això ens permet provar facilment la cerca i els filtres.

S'utilitza un RecyclerView per mostrar els jocs de manera eficient.

La pantalla permet:
- Filtrar per text mentre s'escriu al cercador
- Filtrar per categoria usant botons
- Combinar ambdós filtres simultàniament
- La lògica de filtratge es gestiona a la pròpia JocsActivity, separant clarament:
    - Dades originals
    - Dades filtrades

També hem implementat l'apartat de fòrum utilitzant la mateixa filosofia de components reutilitzables i gestió optimitzada de llistes.

Igual que en la secció de jocs, s'utilitza una llista mock de comentaris a través d'un DataSource, permetent:
- Provar totes les funcionalitats sense dependre d'API externes
- Simular diferents escenaris d'ús (molts comentaris, cap comentari, etc.)
- Mantenir una separació clara entre dades i presentació

Amb el filtre podem escriure de quin joc volem veure els comentaris, però en un futur podriem implementar noves formes de filtra els comentaris.

Si cliques en un comentari, pots accedir a una pàgina amb el contingut del comentari i més comentaris.