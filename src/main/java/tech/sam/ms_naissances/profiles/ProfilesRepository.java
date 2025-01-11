package tech.sam.ms_naissances.profiles;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfilesRepository extends JpaRepository<Profile,Integer> {
    //select * from profile where email=email fourni en parametre
    Optional<Profile> findByEmail(String email);
}
