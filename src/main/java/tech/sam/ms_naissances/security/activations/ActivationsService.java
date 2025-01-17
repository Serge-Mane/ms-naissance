package tech.sam.ms_naissances.security.activations;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tech.sam.ms_naissances.profiles.Profile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@AllArgsConstructor
@Service
public class ActivationsService {
    private BCryptPasswordEncoder passwordEncoder;
    private ActivationsRepository activationsRepository;

    //on a profile en parametre parce que pour activer son compte on reccupere d'abord le profile a actiiver
    public  Activation create(Profile profile){
        Random random=new Random();
        int useCode= 100000 + random.nextInt(900000);
        Activation activation=Activation.builder()
                .active(Boolean.TRUE)
                .userCode(useCode)
                .code(passwordEncoder.encode(""+ useCode))
                .creation(LocalDateTime.now())
                .desactivation(LocalDateTime.now().plusMinutes(5))
                .profiles(profile)
                .build();
        return this.activationsRepository.save(activation);
    }
}
