package tech.sam.ms_naissances.shared.exceptions;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

//cette annotation veut dire ce controller est particulier il gere toutes les erreurs de notre application
@Slf4j
@ControllerAdvice
public class ApplicationControllerAdvice {

    /*
    cette methode gere les erreurs EntityNotFoundException
    * cette methode veut dire partout dns le  projet ou ya erreur EntityNotFoundException il faut
    * renvoyer ca ici c'est elle qui est charger de ca*/
    @ResponseStatus(value = NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public @ResponseBody ErrorsEntity entityNotFoundExceptionHandler(EntityNotFoundException exception){
        log.error("Erreur {} ",exception.getMessage(),exception);
        return  new ErrorsEntity(
                LocalDateTime.now(),
                NOT_FOUND.value(),
                null,exception.getMessage());
    }

    //cette methode gere toutes les erreurs RuntimeException
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public @ResponseBody ErrorsEntity runtimeExceptionHandler(RuntimeException exception){
        log.error("Erreur {} ",exception.getMessage(),exception);
        return  new ErrorsEntity(
                LocalDateTime.now(),
                BAD_REQUEST.value(),
                null,exception.getMessage());
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public @ResponseBody ErrorsEntity dataIntegrityViolationExceptionHandler(DataIntegrityViolationException exception){
        log.error("Erreur {} ",exception.getMessage(),exception);
        return  new ErrorsEntity(
                LocalDateTime.now(),
                BAD_REQUEST.value(),
                null,
                "Une donnée que vous avez saisie est invalide");
    }
}
