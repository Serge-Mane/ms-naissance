package tech.sam.ms_naissances.shared.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import tech.sam.ms_naissances.shared.entities.Status;
import tech.sam.ms_naissances.shared.repositories.StatusRepository;

import java.util.Map;

@AllArgsConstructor
@Component
public class StatusService {
    private final StatusRepository statusRepository;
//rechercher un status par son nom
    public Status search(Map<String,Object> parameters){
        String name=(String) parameters.get("name");
        return this.statusRepository.findByName(name)
                .orElseThrow(()->new RuntimeException("Status inconnu"));
    }
}
