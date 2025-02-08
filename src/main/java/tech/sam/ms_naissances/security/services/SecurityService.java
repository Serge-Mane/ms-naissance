package tech.sam.ms_naissances.security.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import tech.sam.ms_naissances.profiles.Profile;
import tech.sam.ms_naissances.profiles.ProfilesRepository;

import java.util.Optional;

@AllArgsConstructor
@Component
public class SecurityService {
    private final ProfilesRepository profilesRepository;

    //pour connaitre l'utilisateur connecté et avoir toutes ces informations
    public Profile getCurrentUser() {
   Jwt jwt=(Jwt) SecurityContextHolder
                        .getContext()
                        //avoir l'utilisateur authentifier
                        .getAuthentication()
                        //avoir les informations de l'utilisateur connecté
                        .getPrincipal();
   String email=jwt.getSubject();
   //apres avoir eu les infos du user connecté on les compare a celles qu'on a dans la bd voir si ca correspond
   Optional<Profile>profileOptional=this.profilesRepository.findByEmail(email);
   return profileOptional.orElseThrow(()->new RuntimeException("Aucune Entite ne correspond aux parametres fournis"));
 }
}
