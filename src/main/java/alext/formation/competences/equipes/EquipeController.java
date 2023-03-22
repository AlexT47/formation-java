package alext.formation.competences.equipes;

import alext.formation.competences.personnes.Personne;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipes")
public class EquipeController {

    private final EquipeService service;

    public EquipeController(EquipeService service) {
        this.service = service;
    }

    @PostMapping
    public Equipe creer(@RequestBody Equipe equipe) {
        return service.creer(equipe);
    }

    @GetMapping("{id}")
    public Equipe findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    public Iterable<Equipe> findAll() {
        return service.findAll();
    }

    @PutMapping()
    public Equipe modifier(@RequestBody Equipe equipe) {
        return service.modifier(equipe);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    @GetMapping("/nom/{nom}")
    public Equipe findByNom(@PathVariable String nom) {
        return service.findByNom(nom);
    }

    @PutMapping("{idEquipe}/membres")
    public void ajouterMembre(@PathVariable Long idEquipe,@RequestBody Personne personne) {
        service.ajouterMembre(idEquipe, personne);
    }

    @PutMapping("{idEquipe}/membres/{idPersonne}")
    public void ajouterMembre(@PathVariable Long idEquipe,@PathVariable Long idPersonne) {
        service.ajouterMembre(idEquipe, idPersonne);
    }

    @DeleteMapping("{idEquipe}/membres/{idPersonne}")
    public void supprimerMembre(Long idEquipe, Long idPersonne) {
        service.supprimerMembre(idEquipe, idPersonne);
    }
}
