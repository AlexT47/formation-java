package alext.formation.competences.competences;

import alext.formation.competences.exceptions.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CompetenceService {
    
    private final CompetenceRepository competenceRepository;

    public CompetenceService(CompetenceRepository competenceRepository) {
        this.competenceRepository = competenceRepository;
    }
    
    
    /**
     * Sauvegarde une compétence et la persiste.
     * Retourne les données de la compétence avec l'id.
     * @param competence a sauvegarder
     * @return compétence sauvegarder avec son id
     */
    public Competence creer(Competence competence) {
        return competenceRepository.save(competence);
    }
    
    
    /**
     * Remplace les données d'une compétence dans la base de données en fonction de l'id de la compétence.
     * @param competence nouvelles données de la compétence.
     * @return compétence dans l'etat de la base de données.
     */
    public Competence modifier(Competence competence) {
        return competenceRepository.save(competence);
    }
    
    /**
     * Retourne une compétence en fonction de son id.
     * @param id de la compétence retournée
     * @return la compétence si elle existe.
     * @throws ResponseStatusException si aucune compétence ne porte cet id.
     */
    public Competence findById(Long id) {
        return competenceRepository.findById(id).orElseThrow(()->new NotFoundException("Aucune compétence ne porte l'id "+id));
    }
    
    
    /**
     * Retourne la liste des compétences sauvegardées en base de données.
     * @return liste de compétences.
     */
    public Iterable<Competence> findAll() {
        return competenceRepository.findAll();
    }
    
    
    /**
     * Supprime une compétence en fonction de son id.
     * @param id de la compétence.
     */
    public void deleteById(Long id) {
        competenceRepository.deleteById(id);
    }

    /**
     * Ajoute une condition a la compétences.
     * @param idCompetence id de la compétence qui accepte la nouvelle condtition d'obtention.
     * @param conditionCompetence nouveau condition de la compétence.
     * @throws ResponseStatusException NOT_FOUND si aucune competence ne porte cet id.
     */
    public Competence ajouterCondition(Long idCompetence, ConditionCompetence conditionCompetence){
        Competence competence = this.findById(idCompetence);
        conditionCompetence.setCompetence(competence);
        competence.getConditionCompetences().add(conditionCompetence);
        return this.modifier(competence);
    }
}
