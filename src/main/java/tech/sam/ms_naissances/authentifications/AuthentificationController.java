package tech.sam.ms_naissances.authentifications;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import tech.sam.ms_naissances.profiles.ProfilesDTO;
import tech.sam.ms_naissances.security.token.JWTService;

import java.util.Map;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(consumes = APPLICATION_JSON_VALUE)
public class AuthentificationController {
    private final AuthentificationService authentificationService;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "sign-up")
    public void create(@RequestBody ProfilesDTO profilesDTO){
        this.authentificationService.create(profilesDTO);
    }


    @PostMapping(path = "sign-in")
    public @ResponseBody Map<String,String> login(@RequestBody Map<String,String> parameters){
        Authentication authentication= this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        parameters.get("email"),
                        parameters.get("password")
                )
        );
        String bearer=jwtService.generate(authentication);
        return Map.of("bearer",bearer);
    }


    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping(path = "activate")
    public void activate(@RequestBody Map<String,String> parameters){
        this.authentificationService.activate(parameters);
    }
}
