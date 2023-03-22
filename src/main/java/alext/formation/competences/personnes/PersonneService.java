package alext.formation.competences.personnes;

import alext.formation.competences.competences.Competence;
import alext.formation.competences.competences.CompetenceService;
import alext.formation.competences.competences.ConditionCompetence;
import alext.formation.competences.exceptions.CompetenceException;
import alext.formation.competences.exceptions.NotFoundException;
import alext.formation.competences.personneCompetences.PersonneCompetence;
import alext.formation.competences.personneCompetences.PersonneCompetenceService;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonneService {
    private final PersonneRepository personneRepository;
    private final CompetenceService competenceService;
    private final PersonneCompetenceService personneCompetenceService;

    public PersonneService(
            PersonneRepository repository,
            CompetenceService competenceService,
            PersonneCompetenceService personneCompetenceService){
        this.personneRepository = repository;
        this.competenceService = competenceService;
        this.personneCompetenceService = personneCompetenceService;
    }

    /**
     * Sauvegarde une personne et la persiste.
     * Retourne les données de la personne avec l'id.
     * @param personne a sauvegarder
     * @return personne sauvegarder avec son id
     */
    public Personne creer(Personne personne){
        return this.personneRepository.save(personne);
    }

    /**
     * Retourne une personne en fonction de son id.
     * @param id de la personne retournée
     * @return la personne si elle existe.
     * @throws ResponseStatusException si aucune personne ne porte cet id.
     */
    public Personne findById(Long id){
        return this.personneRepository.findById(id).orElseThrow(()->new NotFoundException("Aucune personne ne porte l'id "+id));
    }

    /**
     * Remplace les données d'une personne dans la base de données en fonction de l'id de la personne.
     * @param personne nouvelles données de la personne.
     * @return personne dans l'etat de la base de données.
     */
    public Personne modifier(Personne personne){
        return this.personneRepository.save(personne);
    }

    /**
     * Supprime une personne en fonction de son id.
     * @param id de la personne.
     */
    public void deleteById(Long id){
        this.personneRepository.deleteById(id);
    }

    /**
     * Retourne la liste des utilisateurs en fonction de leurs noms ou prenoms;
     * @param nom ou prenom de la personne
     * @return liste de personne portant comme nom ou prenom le paramètre.
     */
    public List<Personne> findByNomOrPrenom(String nom) {
        return personneRepository.findByNomOrPrenom(nom, nom);
    }

    /**
     * Retourne la liste des personnes sauvegardées en base de données.
     * @return liste de personnes.
     */
    public Iterable<Personne> findAll() {
        return personneRepository.findAll();
    }

    public Iterable<Personne> findPersonneByNote(int note) {
        Iterable<Personne> personnes = this.findAll();
        ArrayList<Personne> result = new ArrayList<>();

        for (Personne personne : personnes) {
            Iterable<PersonneCompetence> personneCompetences = personne.getCompetences();
            for (PersonneCompetence personneCompetence : personneCompetences) {
                if (personneCompetence.getNiveauCompetence().getNote() >= note) {
                    result.add(personne);
                    continue;
                }
            };
        };

        return result;
    }

    public Personne ajouterCompetence(Long idPersonne, PersonneCompetence personneCompetence) {
        Personne personne = this.findById(idPersonne);
        Competence competence = this.competenceService.findById(personneCompetence.getCompetence().getId());

        // On vérifie que la personne possède les conditions pour avoir la compétences.
        verificationConditions(idPersonne, competence);

        personneCompetence.setPersonne(personne);
        personne.getCompetences().add(personneCompetence);

        return this.modifier(personne);
    }


    protected void verificationConditions(Long idPersonne, Competence competence) {
        List<ConditionCompetence> conditionCompetences = competence.getConditionCompetences();
        conditionCompetences.forEach(conditionCompetence -> {
            PersonneCompetence personneCompetence = this.personneCompetenceService.findOneByPersonneIdAndCompetenceId(
                    idPersonne,
                    conditionCompetence.getCondition().getId()
            );

            if (conditionCompetence.getNiveauCompetence().getNote() > personneCompetence.getNiveauCompetence().getNote()) {
                throw new CompetenceException("La personne manque de niveau dans la formation " + conditionCompetence.getCondition().getNom());
            }
        });
    }

}
