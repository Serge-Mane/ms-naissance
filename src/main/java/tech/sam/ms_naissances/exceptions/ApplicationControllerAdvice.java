package tech.sam.ms_naissances.exceptions;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.NOT_FOUND;

//cette annotation veut dire ce controller est particulier il gere toutes les erreurs de notre application
@Slf4j
@ControllerAdvice
public class ApplicationControllerAdvice {

    /*
    * cette methode veut dire partout dns le  projet ou ya erreur EntityNotFoundException il faut
    * renvoyer ca ici c'est elle qui est charger de ca*/
    @ResponseStatus(value = NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public @ResponseBody ErrorsEntity entityNotFoundException(EntityNotFoundException exception){
        log.error("Erreur {} ",exception.getMessage(),exception);
        return  new ErrorsEntity(
                LocalDateTime.now(),
                NOT_FOUND.value(),
                null,exception.getMessage());
    }
}
