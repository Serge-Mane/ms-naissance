package tech.sam.ms_naissances.profiles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ProfilesServicce {
    private final ProfilesRepository profilesRepository;
    Logger logger= LoggerFactory.getLogger(ProfilesControleur.class);

    public ProfilesServicce(ProfilesRepository profilesRepository) {
        this.profilesRepository = profilesRepository;
    }

    public void create(Profile profile){
        logger.info("Nouveau  compte  avec l'email {}", profile.getEmail());
        this.profilesRepository.save(profile);
    }
}
