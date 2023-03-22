package alext.formation.competences.personnes;

import alext.formation.competences.personneCompetences.PersonneCompetence;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Personne {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private LocalDate dateNaissance;
    @OneToMany(mappedBy = "personne", cascade = CascadeType.ALL)
    private List<PersonneCompetence> competences = new ArrayList<>();
}
