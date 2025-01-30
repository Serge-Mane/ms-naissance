package tech.sam.ms_naissances.security;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import tech.sam.ms_naissances.authentifications.AuthentificationService;

@EnableWebSecurity
@Configuration
public class ApplicationSecurity {
    private final RsaKeys rsaKeys;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthentificationService authentificationService;

    public ApplicationSecurity(RsaKeys rsaKeys, BCryptPasswordEncoder passwordEncoder,AuthentificationService authentificationService) {
        this.rsaKeys = rsaKeys;
        this.passwordEncoder = passwordEncoder;
        this.authentificationService = authentificationService;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return
                httpSecurity
                        .cors(Customizer.withDefaults())
                        .csrf(AbstractHttpConfigurer::disable)
                        .headers(AbstractHttpConfigurer::disable)
                        .authorizeHttpRequests(
                                customizer->
                                        customizer
                                                .requestMatchers(HttpMethod.POST,"/sign-in").permitAll()
                                                .requestMatchers(HttpMethod.POST,"/sign-up").permitAll()
                                                .requestMatchers(HttpMethod.POST,"/activate").permitAll()
                                                .anyRequest().authenticated()
                        )
                        //c pour dire on a pas a laisser une requete qui etait deja passé on teste toutes les requetes
                        .sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        //c dire chaque fois que le user nous transmette un jwt c spring security qui se charge et de verifier et valider pour nous
                        .oauth2ResourceServer(oauth->oauth.jwt(Customizer.withDefaults()))
                        .build();
    }

    /*AuthenticationProvider met a disposition de spring tout ce dont qu'il a besoin pour authentifier le user
    et cette AuthenticationProvider est utiliser par AuthenticationManager qui est un element gobal pour authentifier
    tous les user*/
    @Bean
    AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(this.passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(this.authentificationService);
        return daoAuthenticationProvider;
    }

    /* AuthenticationManager qui est un element gobal pour authentifier
    tous les user en appellant AuthenticationProvider qui authentifie un user */
    @Bean
    AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(this.authenticationProvider());
        return authenticationManagerBuilder.build();
    }

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
