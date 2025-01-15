package tech.sam.ms_naissances.authentifications;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tech.sam.ms_naissances.profiles.Profile;
import tech.sam.ms_naissances.profiles.ProfilesDTO;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@AllArgsConstructor
@RestController
@RequestMapping(consumes = APPLICATION_JSON_VALUE)
public class AuthentificationController {
    private final AuthentificationService authentificationService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "sign-up")
    public void create(@RequestBody ProfilesDTO profilesDTO){
        this.authentificationService.create(profilesDTO);
    }
}
