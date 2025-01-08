package tech.sam.ms_naissances.profiles;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

/*
* controleur pour gerer les operations sur les profiles*/
@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("profiles")
public class ProfilesControleur {
    private  final ProfilesServicce profilesServicce;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public void create(@RequestBody Profile profile){
        log.info("creation du compte {} ", profile.getEmail());
        this.profilesServicce.create(profile);
    }

    @GetMapping(produces  = APPLICATION_JSON_VALUE)
    public List<Profile> search(){
        return this.profilesServicce.search();
    }


    @GetMapping(path = "{id}")
    public Profile read (@PathVariable int id){
        return this.profilesServicce.read(id);
    }
}
