package tech.sam.ms_naissances.shared.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import tech.sam.ms_naissances.shared.entities.Company;
import tech.sam.ms_naissances.shared.repositories.CompaniesRepository;

import java.util.Optional;

@AllArgsConstructor
@Component
public class CompaniesService {
    private final CompaniesRepository companiesRepository;
//on recherche un company par son nom s'il n'existe  pas on le cree sinon on le retourne
    public Company createIfNotExist(Company company){
        Optional<Company> optionalCompany=this.companiesRepository.findByname(company.getName());
        if(optionalCompany.isEmpty()){
            company=this.companiesRepository.save(company);
        }else {
            company=optionalCompany.get();
        }
        return company;
    }
}
