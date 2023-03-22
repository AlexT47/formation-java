package alext.formation.competences.personnes;

import alext.formation.competences.equipes.Equipe;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Repository pour la persistance des personnes dans la base de données.
 */
public interface PersonneRepository extends CrudRepository<Personne, Long> {
    List<Personne> findByNomOrPrenom(String nom, String prenom);
}
