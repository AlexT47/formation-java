package alext.formation.competences.equipes;

import alext.formation.competences.personnes.Personne;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface EquipeRepository extends CrudRepository<Equipe, Long> {
    Optional<Equipe> findByNom(String nom);
    /*
    SELECT *
    FROM equipes
    JOIN personnes ON ...
    WHERE personnes.id == ??
     */
    List<Equipe> findAllByMembresId(Long id);
}
