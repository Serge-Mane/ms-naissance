package tech.sam.ms_naissances.declarations;

import jakarta.persistence.*;
import lombok.*;
import tech.sam.ms_naissances.profiles.Profile;
import tech.sam.ms_naissances.shared.entities.Company;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "declarations")
public class Declaration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private String comment;
    private LocalDateTime registered;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "first_parent_id")
    private Profile firstParent;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "second_parent_id")
    private Profile secondParent;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "child_id")
    private Profile child;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "declaration", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private List<DeclarationStatus> statuses;
}
