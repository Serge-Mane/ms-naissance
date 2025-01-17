package tech.sam.ms_naissances.security.activations;


import jakarta.persistence.*;
import lombok.*;
import tech.sam.ms_naissances.profiles.Profile;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "activations")
public class Activation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //il est transient parce qu'il ne seras pas sauvegarder dans la bd mais transmis au user
    @Transient
    private  int userCode;
    private String code;
    private Boolean active;
    private LocalDateTime creation;
    private LocalDateTime desactivation;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.DETACH})
    @JoinColumn(name="profiles_id")
    private Profile profiles;
}
