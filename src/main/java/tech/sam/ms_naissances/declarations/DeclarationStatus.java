package tech.sam.ms_naissances.declarations;


import jakarta.persistence.*;
import lombok.*;
import tech.sam.ms_naissances.profiles.Profile;
import tech.sam.ms_naissances.shared.entities.Status;

import java.time.LocalDateTime;


@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "declarations_status")
public class DeclarationStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String comment;
    @Column(name = "creation")
    private LocalDateTime registered;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.DETACH})
    @JoinColumn(name = "declarations_id")
    private Declaration declaration;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.DETACH})
    @JoinColumn(name = "status_id")
    private Status status;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.DETACH})
    @JoinColumn(name = " agents_id")
    private Profile agent;
}
