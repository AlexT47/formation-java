package alext.formation.competences.competences;

import alext.formation.competences.equipes.EquipeService;
import alext.formation.competences.personnes.Personne;
import alext.formation.competences.personnes.PersonneService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/competences")
public class CompetenceController {

    private final CompetenceService service;

    public CompetenceController(CompetenceService service){
        this.service = service;
    }

    @PostMapping("")
    public Competence creer(@RequestBody Competence competence) {
        return service.creer(competence);
    }

    @PutMapping("")
    public Competence modifier(@RequestBody Competence competence) {
        return service.modifier(competence);
    }

    @GetMapping("{id}")
    public Competence findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("")
    public Iterable<Competence> findAll() {
        return service.findAll();
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    @PutMapping("{idCompetence}/conditions")
    public Competence ajouterMembre(@PathVariable Long idCompetence,@RequestBody ConditionCompetence conditionCompetence) {
        return service.ajouterCondition(idCompetence, conditionCompetence);
    }
}
