package alext.formation.competences.personnes;

import alext.formation.competences.equipes.Equipe;
import alext.formation.competences.equipes.EquipeService;
import alext.formation.competences.personneCompetences.PersonneCompetence;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personnes")
public class PersonneController {

    private final PersonneService service;
    private final EquipeService equipeService;

    public PersonneController(PersonneService service, EquipeService equipeService){
        this.service = service;
        this.equipeService = equipeService;
    }

    @PostMapping("")
    public Personne creer(@RequestBody Personne personne) {
        return service.creer(personne);
    }

    @GetMapping("{id}")
    public Personne findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping
    public Personne modifier(@RequestBody Personne personne) {
        return service.modifier(personne);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    @GetMapping("nom/{nom}")
    public List<Personne> findByNomOrPrenom(@PathVariable String nom) {
        return service.findByNomOrPrenom(nom);
    }

    @GetMapping("")
    public Iterable<Personne> findAll() {
        return service.findAll();
    }

    @GetMapping("{id}/equipes")
    public List<Equipe> findAllByMembersId(@PathVariable Long id) {
        return this.equipeService.findAllByMembersId(id);
    }


    @PostMapping("{id}/competence")
    public Personne ajouterCompetence(@RequestBody PersonneCompetence personneCompetence, @PathVariable Long id) {
        return service.ajouterCompetence(id, personneCompetence);
    }

    @GetMapping("/niveau/{note}")
    public Iterable<Personne> findCompetenceSuperieurNote(@PathVariable int note) {
        return service.findPersonneByNote(note);
    }

}
