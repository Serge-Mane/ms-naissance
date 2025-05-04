package tech.sam.ms_naissances.security.token;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;


@AllArgsConstructor
@Component
public class JWTService {
    private final JwtEncoder jwtEncoder;
    public String generate(Authentication authentication) {
        Instant now = Instant.now();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Set<String> scopes = authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
        JwtClaimsSet jwtClaimsSet = JwtClaimsSet
                .builder()
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.HOURS))
                .subject(authentication.getName())
                .claim("username", authentication.getName())
                .claim("scp", scopes)
                .issuer("self")
                .build();
        return this.jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
    }
}
