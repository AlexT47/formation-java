package alext.formation.competences.competences;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
public class ConditionCompetence {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private NiveauCompetence niveauCompetence;

    @ManyToOne
    @JoinColumn(name = "competence_id")
    @JsonIgnore
    private Competence competence;


    @ManyToOne
    @JoinColumn(name = "condition_id")
    private Competence condition;
}
