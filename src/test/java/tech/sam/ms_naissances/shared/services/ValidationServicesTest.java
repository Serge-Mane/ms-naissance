package tech.sam.ms_naissances.shared.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ValidationServicesTest {

    @InjectMocks
    ValidationServices validationServices;

    //Pour faire un test unitaire on a trois parties
    @Test
    void shouldTestThatEmailIsValid() {
        //1-definir les variables(Arrange)
        String email="test@email.com";

        //2-Effectuer e test (Act)
        this.validationServices.validateEmail(email);

        //3-Afficher les resultats (Assert)
        //On ne returne rien parce que la methode est void
    }

    @Test
    void validatePhone() {
    }
}