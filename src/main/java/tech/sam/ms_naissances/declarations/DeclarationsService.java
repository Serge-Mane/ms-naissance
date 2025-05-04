package tech.sam.ms_naissances.declarations;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.sam.ms_naissances.notifications.EmailsService;
import tech.sam.ms_naissances.profiles.Profile;
import tech.sam.ms_naissances.profiles.ProfilesService;
import tech.sam.ms_naissances.security.services.SecurityService;
import tech.sam.ms_naissances.shared.entities.Company;
import tech.sam.ms_naissances.shared.entities.Status;
import tech.sam.ms_naissances.shared.services.CompaniesService;
import tech.sam.ms_naissances.shared.services.StatusService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DeclarationsService {
    private final EmailsService emailsService;
    private final DeclarationsMapper declarationsMapper;
    private final CompaniesService companiesService;
    private final StatusService statusService;
    private final ProfilesService profilesService;
    private final SecurityService securityService;
    private final DeclarationsRepository declarationsRepository;
    private final DeclarationsStatusRepository declarationsStatusRepository;

    public void create(Declaration declaration) {

        Profile firstParent = this.securityService.getCurrentUser();
        declaration.setFirstParent(firstParent);

        Profile secondParent = this.profilesService.createIfNotExists(declaration.getSecondParent());
        declaration.setSecondParent(secondParent);

        Profile child = this.profilesService.createIfNotExists(declaration.getChild());
        declaration.setChild(child);

        Company company = this.companiesService.createIfNotExist(declaration.getCompany());
        declaration.setCompany(company);

        String name = String.format(
              "Déclaration de %s %s pour %s %s",
              firstParent.getFirstName(),
              firstParent.getLastName(),
              child.getFirstName(),
              child.getLastName()
        );
        declaration.setName(name);

        declaration = this.declarationsRepository.save(declaration);
        Status status = this.statusService.search(Map.of("name", "NEW"));
        DeclarationStatus declarationStatus = DeclarationStatus.builder()
                .status(status)
                .declaration(declaration)
                .registered(LocalDateTime.now())
                .build();
        this.declarationsStatusRepository.save(declarationStatus);
    }

    public List<DeclarationDTO> search() {
        Profile profile = this.securityService.getCurrentUser();
        String email = profile.getEmail();
        String role = profile.getRole().getName();
        List<Declaration> declarations;
        if(role.equals("ADMINISTRATOR") || role.equals("AGENT")) {
            declarations = this.declarationsRepository.findAll();
        } else {
            declarations = this.declarationsRepository.findCurrentUserDeclarations(email);
        }
        return declarations.stream().map(
                declarationsMapper::entityToDTO).collect(Collectors.toList());
    }

    public void updateStatus(int id, Map<String, String> params) {
        Declaration declaration = this.declarationsRepository.findById(id).orElseThrow(() -> new RuntimeException("Entité indisponible"));

        Status status = this.statusService.search(Map.of("name", params.get("status")));
        DeclarationStatus declarationStatus = DeclarationStatus.builder()
                .status(status)
                .declaration(declaration)
                .registered(LocalDateTime.now())
                .build();
         declarationStatus = this.declarationsStatusRepository.save(declarationStatus);
        this.emailsService.sendStatusNotification(declarationStatus);
    }
}
