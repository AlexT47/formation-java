package alext.formation.competences.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Controller Advice permettant la gestion des exceptions NotFoundexception générés pas l'API.
 * Quand un controller retourne une exception de type NotFoundException, la méthode
 * {@link CompetenceControllerAdvice ::handleException} est execute.  <br>
 * L'utilisation d'un controllerAdvice permet de gérer le message de retour en cas d'exception mais aussi d'automatiser
 * la logique de gestion dans le cas de cette exception.
 */
@Slf4j // <=> private static final Logger log = LoggerFactory.getLogger(NotFoundException.class);
@ControllerAdvice
public class CompetenceControllerAdvice {


    /**
     * Méthode appelée quand une exception de type NoFoundException est levé, mais pas géré par le code.
     * @param exception instance de l'exception levée
     * @return un objet json à sérialiser pour le retour à l'utilisateur ayant fait la requête.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value= {CompetenceException.class})
    public ResponseEntity<CompetenceResponse> handleException(RuntimeException exception){
        log.warn(exception.getMessage());

        return new ResponseEntity<>(
                new CompetenceResponse(exception.getMessage()),
                HttpStatus.BAD_REQUEST);
    }
}
