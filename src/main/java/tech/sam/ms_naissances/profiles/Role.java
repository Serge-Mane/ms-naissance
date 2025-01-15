package tech.sam.ms_naissances.profiles;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private  String name;
    private  String description;

    @ManyToMany
            @JoinTable(
                    name = "roles_permissions",
                    joinColumns = @JoinColumn(name = "roles_id"),
                    inverseJoinColumns = @JoinColumn(name = "permissions_id")
            )
    private List<Permission> permissions;
}
