package tech.sam.ms_naissances.security.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import tech.sam.ms_naissances.profiles.Profile;

import java.util.Optional;

@AllArgsConstructor
@Component
public class SecurityService {

    //pour connaitre l'utilisateur connecté
    public Profile getCurrentUser() {
       return
               (Profile)SecurityContextHolder
                        .getContext()
                        //avoir l'utilisateur authentifier
                        .getAuthentication()
                        //avoir les informations de l'utilisateur connecté
                        .getPrincipal();
 }
}
