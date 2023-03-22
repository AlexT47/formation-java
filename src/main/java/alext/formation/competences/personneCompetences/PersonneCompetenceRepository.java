package alext.formation.competences.personneCompetences;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PersonneCompetenceRepository extends CrudRepository<PersonneCompetence, Long> {
    Optional<PersonneCompetence> findOneByPersonneIdAndCompetenceId(long personneId, Long competenceId);
}
