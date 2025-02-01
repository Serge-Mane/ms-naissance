package tech.sam.ms_naissances.security.token;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@AllArgsConstructor
@Component
public class JWTService {

    private  final JwtEncoder jwtEncoder;

    public String generate(Authentication authentication){
        Instant now = Instant.now();
        JwtClaimsSet jwtClaimsSet=JwtClaimsSet
                .builder()
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.HOURS))
                .subject(authentication.getName())
                .claim("username",authentication.getName())
                .issuer("self")
                .build();
        return this.jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
    }
}
