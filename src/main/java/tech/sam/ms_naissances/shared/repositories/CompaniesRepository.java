package tech.sam.ms_naissances.shared.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.sam.ms_naissances.shared.entities.Company;

import java.util.Optional;

public interface CompaniesRepository extends JpaRepository<Company,Integer> {
    Optional<Company> findByname(String name);
}
