package tech.sam.ms_naissances.profiles;

import org.springframework.stereotype.Component;

@Component
public class ProfilesMapping {

    public Profile dtoEntity(ProfilesDTO dto){
        Profile entity=new Profile();
        entity.setPassword(dto.password());
        entity.setPhone(dto.phone());
        entity.setEmail(dto.email());
        entity.setFirstName(dto.firstName());
        entity.setLastName(dto.lastName());
        return entity;
    }
}
