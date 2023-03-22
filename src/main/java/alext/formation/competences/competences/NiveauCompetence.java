package alext.formation.competences.competences;

import lombok.Getter;
import lombok.Setter;

public enum NiveauCompetence {
    NIVEAU_ZERO(0, "Aucune connaissance"),
    NIVEAU_UN(1, "Connait le nom de la compétence"),
    NIVEAU_DEUX(2, "Connait le nom de la compétence et sa definition"),
    NIVEAU_TROIS(3, "Connait le nom de la compétence, sa definition et ce qu'elle fait"),
    NIVEAU_QUATRE(4, "Connait le nom de la compétence, sa definition, ce qu'elle fait et comment elle fonctionne"),
    NIVEAU_CINQ(5, "Sait utiliser de manière basique la compétence"),
    NIVEAU_SIX(6, "Sait utiliser de manière intermédiaire la compétence"),
    NIVEAU_SEPT(7, "Sait utiliser de manière avancée la compétence"),
    NIVEAU_HUIT(8, "Sait utiliser de manière experte la compétence"),
    NIVEAU_NEUF(9, "Maitrise la compétence"),
    NIVEAU_DIX(10, "Sait enseigner la compétence a la personne qui la créé");



    private final int note;
    private String libelle;

    private NiveauCompetence(int note, String libelle) {
        this.note = note;
        this.libelle = libelle;
    }


    public int getNote() {
        return this.note;
    }
}
