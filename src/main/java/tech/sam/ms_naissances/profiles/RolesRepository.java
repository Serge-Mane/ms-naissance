package tech.sam.ms_naissances.profiles;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Role,Integer> {
    Role findByName(String name);
}
