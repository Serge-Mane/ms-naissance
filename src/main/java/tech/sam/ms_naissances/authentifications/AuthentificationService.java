package tech.sam.ms_naissances.authentifications;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tech.sam.ms_naissances.profiles.Profile;
import tech.sam.ms_naissances.profiles.ProfilesDTO;
import tech.sam.ms_naissances.profiles.ProfilesMapper;
import tech.sam.ms_naissances.profiles.ProfilesRepository;
import tech.sam.ms_naissances.shared.services.ValidationServices;

@Service
@Slf4j
@AllArgsConstructor
public class AuthentificationService {
    private final ProfilesMapper profilesMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ValidationServices validationServices;
    private final ProfilesRepository profilesRepository;

    public void create(ProfilesDTO profilesDTO){
        log.info("Nouveau  compte  avec l'email {}", profilesDTO.email());

        //ici on a pas d'adresse parce que quand l'utilisateur cree son compte il ne met pas son adresse

        Profile profile=this.profilesMapper.dtoEntity(profilesDTO);

        //je reccupere le mot de passe en claire que l'utilisateur nous a donner
        String userPassword=profile.getPassword();
        //Puis j'encode le mot de passe
        String encodedPassword=this.passwordEncoder.encode(userPassword);
        //le nouveau profile
        profile.setPassword(encodedPassword);

        //a la creation du profile il faut valider d'abord l'email et le phone
        this.validationServices.validateEmail(profile.getEmail());
        this.validationServices.validatePhone(profile.getPhone());
        this.profilesRepository.save(profile);
    }
}
