package alext.formation.competences.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CompetenceException extends RuntimeException {

    public CompetenceException(String message) {
        super(message);
    }
}
