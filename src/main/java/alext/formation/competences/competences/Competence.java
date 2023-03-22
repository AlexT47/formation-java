package alext.formation.competences.competences;

import alext.formation.competences.personneCompetences.PersonneCompetence;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString

public class Competence {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nom;

    private String description;

    @OneToMany(mappedBy = "competence", cascade = CascadeType.ALL)
    private List<ConditionCompetence> conditionCompetences = new ArrayList<>();

    @OneToMany(mappedBy = "competence")
    @JsonIgnore
    private List<PersonneCompetence> personnes = new ArrayList<>();
}
