package tech.sam.ms_naissances.profiles;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import tech.sam.ms_naissances.shared.entities.Address;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//builder: est utiliser pour pouvoir retourner la liste des profiles dans ProfilesServicesTest
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "profiles")
public class Profile implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    private Civility civility;
    private String firstName;
    private  String lastName;
    private  String email;
    private String phone;
    private String password;
    private Boolean active=false;
    /*@ManyToOne: Une adresse appartient a plusieur user.
    * {CascadeType.MERGE}: pour dire a la creation du user l'adresse existe deja dans a bd puis on fait merge le user
    * CascadeType.DETACH: pour dire quand on supprime le user on ne supprime pas l'adresse */
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "addresses_id")
    private Address address;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "roles_id")
    private Role role;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities=new ArrayList<>();
        //ajout des roles
        authorities.add(new SimpleGrantedAuthority("ROLE_"+ this.role.getName().toUpperCase()));

        //ajout des permissions
        for (Permission permission:this.role.getPermissions()){
            authorities.add(new SimpleGrantedAuthority(permission.getName().toUpperCase()));
        }
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.active;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.active;
    }

    @Override
    public boolean isEnabled() {
        return this.active;
    }
}
