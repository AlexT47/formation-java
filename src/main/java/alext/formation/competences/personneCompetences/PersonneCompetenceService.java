package alext.formation.competences.personneCompetences;

import alext.formation.competences.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PersonneCompetenceService {

    private PersonneCompetenceRepository personneCompetenceRepository;

    public PersonneCompetenceService(PersonneCompetenceRepository personneCompetenceRepository) {
        this.personneCompetenceRepository = personneCompetenceRepository;
    }

    public PersonneCompetence findOneByPersonneIdAndCompetenceId(Long idPersonne, Long competenceId) {
        return this.personneCompetenceRepository.findOneByPersonneIdAndCompetenceId(idPersonne, competenceId)
                .orElseThrow(() -> new NotFoundException("Il manque une compétences pour la personne."));
    }
}
