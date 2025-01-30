package tech.sam.ms_naissances.security;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

@Configuration
public class ApplicationSecurity {
    private RsaKeys rsaKeys;

    //pour encoder le token on a besoin de la cle privee et celle public
    @Bean
    JwtEncoder jwtEncoder(){
        final JWK jwk= new RSAKey.Builder(this.rsaKeys.rsaPublicKey())
                .privateKey(this.rsaKeys.rsaPrivateKey())
                .build();
        JWKSource<SecurityContext> jwkSource= new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwkSource);
    }

    //pour decoder on a juste besoin de la cle publique
    @Bean
    JwtDecoder jwtDecoder(){
        return NimbusJwtDecoder
                .withPublicKey(this.rsaKeys.rsaPublicKey())
                .build();
    }
}
