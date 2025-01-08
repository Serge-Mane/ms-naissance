package tech.sam.ms_naissances.profiles;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
* controleur pour gerer les operations sur les profiles*/
@RestController
@RequestMapping("profiles")
public class ProfilesControleur {

    @PostMapping()
    public void create(@RequestBody Profile profile){
    System.out.println("creation du compte "+ profile.getEmail());
    }
}
