package tech.sam.ms_naissances.shared.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.sam.ms_naissances.shared.entities.Address;

public interface AddressesRepository extends JpaRepository<Address,Integer> {
}
