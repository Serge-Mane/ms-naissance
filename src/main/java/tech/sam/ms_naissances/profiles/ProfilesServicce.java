package tech.sam.ms_naissances.profiles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;

public class ProfilesServicce {
    Logger logger= LoggerFactory.getLogger(ProfilesControleur.class);

    public void create(Profile profile){
        logger.info("Nouveau  compte  avec l'email {}", profile.getEmail());
    }
}
