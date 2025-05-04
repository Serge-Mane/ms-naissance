package tech.sam.ms_naissances.profiles;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
    Controlleur pour gérer les opérations
    sur les profiles
 */
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping({"profiles"})
public class ProfilesController {

    private final ProfilesService profilesService;


    @GetMapping(path = "read", produces = APPLICATION_JSON_VALUE)
    public ProfileDTO getCurrentUser() {
        return this.profilesService.getCurrentUser();
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public Set<ProfileDTO> search() {
        return this.profilesService.search();
    }

    @GetMapping(path = "{id}")
    public Profile read(@PathVariable int id) {
        return this.profilesService.read(id);
    }

    @PutMapping(path = "{id}")
    public Profile update(@PathVariable int id, @RequestBody Profile profile) {
        return this.profilesService.update(id, profile);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable(name = "id") int id) {
        this.profilesService.delete(id);
    }
}
