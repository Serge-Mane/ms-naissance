package tech.sam.ms_naissances.profiles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
* controleur pour gerer les operations sur les profiles*/
@RestController
@RequestMapping("profiles")
public class ProfilesControleur {
    @Autowired
    private ProfilesServicce profilesServicce;
    Logger logger= LoggerFactory.getLogger(ProfilesControleur.class);

    @PostMapping()
    public void create(@RequestBody Profile profile){
        logger.info("creation du compte "+ profile.getEmail());
        this.profilesServicce.create(profile);
    }
}
