package alext.formation.competences.equipes;

import alext.formation.competences.exceptions.NotFoundException;
import alext.formation.competences.personnes.Personne;
import alext.formation.competences.personnes.PersonneService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Service
public class EquipeService {
    private final EquipeRepository repository;
    private final PersonneService personneService;

    public EquipeService(EquipeRepository repository, PersonneService personneService) {
        this.repository = repository;
        this.personneService = personneService;
    }

    /**
     * Sauvegarde une nouvelle equipe dans la base de données
     * @param equipe à sauvegarder
     * @return equipe sauvegarder avec son id.
     */
    public Equipe creer(Equipe equipe){
        return this.repository.save(equipe);
    }

    /**
     * Recherche une equipe en fonction de son id.
     * @param id de l'équipe à rechercher
     * @return l'équipe portant l'id passé en paramètre
     * @throws org.springframework.web.server.ResponseStatusException si aucune équipe ne porte cet id
     */
    public Equipe findById(Long id){
        return this.repository.findById(id).orElseThrow(()->new NotFoundException("Aucune équipe ne porte l'id "+id));
    }

    /**
     * Retourne la liste des équipes.
     * @return la liste de toutes les équipes.
     */
    public Iterable<Equipe> findAll(){
        return this.repository.findAll();
    }

    /**
     * Remplace les données d'une équipe dans la base de données en fonction de son id.
     * @param equipe nouvelles données à sauvegarder
     * @return l'équipe avec les nouvelles données.
     */
    public Equipe modifier(Equipe equipe){
        return this.repository.save(equipe);
    }

    /**
     * supprime une équipe en fonction de son id.
     * @param id de l'équipe à sauvegarder.
     */
    public void deleteById(Long id){
        this.repository.deleteById(id);
    }

    /**
     * Retourne l'équipe qui possède ce nom.
     * @param nom de l'équipe à rechercher.
     * @return l'équipe qui possède le nom passé en paramètre.
     * @throws ResponseStatusException NOT_FOUND si aucune équipe ne porte cet id.
     */
    public Equipe findByNom(String nom){
        return this.repository.findByNom(nom).orElseThrow(()->new NotFoundException("Aucune équipe ne porte le nom "+nom));
    }

    /**
     * Ajoute un membre a l'équipe.
     * @param idEquipe id de l'équipe qui accepte le nouveau membre.
     * @param personne nouveau membre de l'équipe.
     * @throws ResponseStatusException NOT_FOUND si aucune équipe ne porte cet id.
     */
    public void ajouterMembre(Long idEquipe, Personne personne){
        Equipe equipe = this.findById(idEquipe);
        equipe.getMembres().add(personne);
        this.modifier(equipe);
    }

    /**
     * Ajoute une personne à une équipe en focntion de l'id de la personne et l'id de l'équipe.
     * @param idEquipe id de l'équipe
     * @param idPersonne id de la personne
     * @throws ResponseStatusException NOT_FOUND si l'id de l'équipe pou de la personne n'est pas valide.
     */
    public void ajouterMembre(Long idEquipe, Long idPersonne){
        Personne personne = this.personneService.findById(idPersonne);
        this.ajouterMembre(idEquipe, personne);
    }

    /**
     * Supprime un membre d'une équipe en fonction de l'id de l'équipe et de l'id de la personne.
     * @param idEquipe id de l'équipe.
     * @param idPersonne id du membre à supprimer.
     * @throws ResponseStatusException NOT_FOUND si l'id de l'équipe n'est pas valide.
     */
    public void supprimerMembre(Long idEquipe, Long idPersonne){
        Equipe equipe = this.findById(idEquipe);
        equipe.getMembres().removeIf(personne-> Objects.equals(personne.getId(), idPersonne));
        this.modifier(equipe);
    }

    /**
     * change le representant de l'équipe en fonction de l'id de l'équipe et l'id du représentant.
     * @param idEquipe id de l'équipe cible.
     * @param idRepresentant id du représentant cible.
     * @throws ResponseStatusException NOT_FOUND si le l'id du représentant ou l'id de l'équipe n'est pas valide.
     */
    public void modifierRepresentant(Long idEquipe, Long idRepresentant){
        Equipe equipe = this.findById(idEquipe);
        Personne representant = this.personneService.findById(idRepresentant);
        modifier(equipe);
    }

    /**
     * Retourne la liste des équipes qui possèdent le membre portant l'id.
     * @param id du membre.
     * @return liste des équipes possédant le membre portant l'id.
     */
    public List<Equipe> findAllByMembersId(Long id){
        return this.repository.findAllByMembresId(id);
    }


}
