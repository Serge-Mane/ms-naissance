package tech.sam.ms_naissances.shared.services;

import org.springframework.stereotype.Component;

@Component
public class ValidationServices {

    public void validateEmail(String email){
        if(email==null){
            throw new RuntimeException("Le mail est requis");
        }

        if(email.indexOf('@') == -1 ){
            throw new RuntimeException("Le mail est invalide");
        }
//pour dire si le mail est null et le point est appres le @ c'est invalide
        if(email.indexOf('.') == -1 && email.indexOf('.') < email.indexOf('@') ){
            throw new RuntimeException("Le mail est invalide");
        }

    }

    public void validatePhone(String phone){
        if(phone==null){
            throw new RuntimeException("Le telephone est invalide");
        }

        if(phone.length() > 22){
            throw new RuntimeException("Le telephone est invalide");
        }
    }
}
