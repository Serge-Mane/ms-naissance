package tech.sam.ms_naissances.authentifications;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tech.sam.ms_naissances.notifications.EmailsService;
import tech.sam.ms_naissances.notifications.MailpitClient;
import tech.sam.ms_naissances.profiles.*;
import tech.sam.ms_naissances.security.activations.Activation;
import tech.sam.ms_naissances.security.activations.ActivationsService;
import tech.sam.ms_naissances.shared.services.ValidationServices;

import java.util.Map;

@Service
@Slf4j
@AllArgsConstructor
public class AuthentificationService implements UserDetailsService {
    private final ProfilesMapper profilesMapper;
    private final EmailsService emailsService;
    private final RolesRepository rolesRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ValidationServices validationServices;
    private final ProfilesRepository profilesRepository;
    private final ActivationsService activationsService;

    public void create(ProfileDTO profilesDTO){
        log.info("Nouveau  compte  avec l'email {}", profilesDTO.getEmail());

        //ici on a pas d'adresse parce que quand l'utilisateur cree son compte il ne met pas son adresse

        Profile profile=this.profilesMapper.dtoToEntity(profilesDTO);

        //je reccupere le mot de passe en claire que l'utilisateur nous a donner
        String userPassword=profile.getPassword();
        //Puis j'encode le mot de passe
        String encodedPassword=this.passwordEncoder.encode(userPassword);
        //le nouveau profile
        profile.setPassword(encodedPassword);

        //a la creation du profile on associe le role public qui est dans la bd a l'utilisateur par defaut
        Role role=this.rolesRepository.findByName("PUBLIC");
        profile.setRole(role);

        //a la creation du profile il faut valider d'abord l'email et le phone
        this.validationServices.validateEmail(profile.getEmail());
        this.validationServices.validatePhone(profile.getPhone());
        //je reccupere le profile creer
        profile=this.profilesRepository.save(profile);

        Activation activation=this.activationsService.create(profile);

        log.info("le code d'activation pour {} est {}",profile.getEmail(),activation.getUserCode());

        this.emailsService.send(
                Map.of(
                        "email",profile.getEmail(),
                        "name",String.format("%s %s",profile.getFirstName(),profile.getLastName()),
                        "code",""+ activation.getUserCode(),
                        "template","activation-code.ftl"
                )
        );


    }

    public void activate(Map<String, String> parameters) {
        Profile profile=this.activationsService.validateAnReturnProfile(parameters);
        profile.setActive(true);
        this.profilesRepository.save(profile);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.profilesRepository.findByEmail(username).orElseThrow(()->new RuntimeException("Aucun n'utilisateur ne corespond aux criteres saisie"));
    }
}
