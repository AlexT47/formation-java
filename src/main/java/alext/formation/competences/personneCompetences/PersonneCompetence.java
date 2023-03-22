package alext.formation.competences.personneCompetences;

import alext.formation.competences.competences.Competence;
import alext.formation.competences.competences.NiveauCompetence;
import alext.formation.competences.personnes.Personne;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PersonneCompetence {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "competence_id")
    private Competence competence;

    @ManyToOne
    @JoinColumn(name = "personne_id")
    @JsonIgnore
    private Personne personne;

    private NiveauCompetence niveauCompetence;

}
