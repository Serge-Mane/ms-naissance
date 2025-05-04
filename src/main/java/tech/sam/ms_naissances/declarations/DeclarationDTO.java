package tech.sam.ms_naissances.declarations;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tech.sam.ms_naissances.profiles.ProfileDTO;
import tech.sam.ms_naissances.shared.entities.Company;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class DeclarationDTO {
    private int id;
    private String name;
    private String description;
    private String comment;
    private LocalDateTime registered;
    private ProfileDTO firstParent;

    private ProfileDTO secondParent;
    private ProfileDTO child;
    private Company company;
    private List<DeclarationStatus> statuses;
}
