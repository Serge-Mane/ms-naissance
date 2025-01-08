package tech.sam.ms_naissances.profiles;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
* controleur pour gerer les operations sur les profiles*/
@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("profiles")
public class ProfilesControleur {
    private  final ProfilesServicce profilesServicce;

    @PostMapping
    public void create(@RequestBody Profile profile){
        log.info("creation du compte {} ", profile.getEmail());
        this.profilesServicce.create(profile);
    }
}
