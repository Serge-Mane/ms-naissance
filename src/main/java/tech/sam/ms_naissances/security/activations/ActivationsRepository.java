package tech.sam.ms_naissances.security.activations;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivationsRepository extends JpaRepository<Activation,Integer> {
}
