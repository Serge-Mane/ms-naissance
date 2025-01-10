package tech.sam.ms_naissances.profiles;

import jakarta.persistence.*;
import lombok.*;
import tech.sam.ms_naissances.shared.entities.Address;

//builder: est utiliser pour pouvoir retourner la liste des profiles dans ProfilesServicesTest
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "profiles")
public class Profile {
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
    /*@ManyToOne: Une adresse appartient a plusieur user.
    * {CascadeType.MERGE}: pour dire a la creation du user l'adresse existe deja dans a bd puis on fait merge le user
    * CascadeType.DETACH: pour dire quand on supprime le user on ne supprime pas l'adresse */
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "addresses_id")
    private Address address;
}
