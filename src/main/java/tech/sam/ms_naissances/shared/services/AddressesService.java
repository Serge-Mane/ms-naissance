package tech.sam.ms_naissances.shared.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import tech.sam.ms_naissances.shared.entities.Address;
import tech.sam.ms_naissances.shared.repositories.AddressesRepository;


@AllArgsConstructor
@Component
public class AddressesService {
    private  final AddressesRepository addressesRepository;

    public Address create(Address address){
        return this.addressesRepository.save(address);
    }
}
