package tech.sam.ms_naissances.declarations;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.sam.ms_naissances.profiles.Profile;
import tech.sam.ms_naissances.profiles.ProfilesServicce;
import tech.sam.ms_naissances.security.services.SecurityService;
import tech.sam.ms_naissances.shared.entities.Company;
import tech.sam.ms_naissances.shared.repositories.CompaniesRepository;
import tech.sam.ms_naissances.shared.services.CompaniesService;
import tech.sam.ms_naissances.shared.services.StatusService;

@AllArgsConstructor
@Service
public class DeclarationsService {
    private final CompaniesService companiesService;
    private final StatusService statusService;
    private final ProfilesServicce profilesServicce;
    private final SecurityService securityService;
    private final DeclarationsRepository declarationsRepository;

    public void create(Declaration declaration){
        //reccuperation des informations du parent connecté
        Profile firstParent=this.securityService.getCurrentUser();
        declaration.setFirstParent(firstParent);

        //on crée le second parent
        Profile secondParent=this.profilesServicce.createIfNotExists(declaration.getSecondParent());
        declaration.setSecondParent(secondParent);

        //on crée le first parent
        Profile child=this.profilesServicce.createIfNotExists(declaration.getChild());
        declaration.setSecondParent(child);

        //on crée le company
        Company company=this.companiesService.createIfNotExist(declaration.getCompany());
        declaration.setCompany(company);

        //on a les infos du second parent et du fils
        String name=String.format(
                "Declaration de %s %s pour %s %s",
                firstParent.getFirstName(),
                firstParent.getLastName(),
                child.getFirstName(),
                child.getLastName()
        );
        declaration.setName(name);
        //on sauvegarde la declaration
        this.declarationsRepository.save(declaration);
    }

}
