package tech.sam.ms_naissances.shared.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.sam.ms_naissances.shared.entities.Status;

import java.util.Optional;

public interface StatusRepository extends JpaRepository<Status,Integer> {
    Optional<Status> findByName(String name);
}
