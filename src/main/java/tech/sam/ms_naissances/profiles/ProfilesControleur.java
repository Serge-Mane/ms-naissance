package tech.sam.ms_naissances.profiles;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

/*
* controleur pour gerer les operations sur les profiles*/
@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("profiles")
public class ProfilesControleur {
    private  final ProfilesServicce profilesServicce;


    @GetMapping(produces  = APPLICATION_JSON_VALUE)
    public Set<ProfilesDTO> search(){
        return this.profilesServicce.search();
    }


    @GetMapping(path = "{id}")
    public Profile read (@PathVariable int id){
        return this.profilesServicce.read(id);
    }

    @PutMapping(path = "{id}")
    public Profile update(@PathVariable int id,@RequestBody Profile profile){
        return  this.profilesServicce.update(id,profile);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "{id}")
    public  void delete(@PathVariable int id){
        this.profilesServicce.delete(id);
    }
}
