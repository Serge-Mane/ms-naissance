package tech.sam.ms_naissances.security.activations;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ActivationsRepository extends JpaRepository<Activation,Integer> {
    List<Activation> findAllByActiveAndDesactivationAfter(Boolean active, LocalDateTime desactivation);
}
