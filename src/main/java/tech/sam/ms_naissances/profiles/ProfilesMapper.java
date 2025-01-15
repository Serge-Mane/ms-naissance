package tech.sam.ms_naissances.profiles;

import org.springframework.stereotype.Component;

@Component
public class ProfilesMapper {

    public Profile dtoEntity(ProfilesDTO dto){
        Profile entity=new Profile();
        entity.setPassword(dto.password());
        entity.setPhone(dto.phone());
        entity.setEmail(dto.email());
        entity.setFirstName(dto.firstName());
        entity.setLastName(dto.lastName());
        return entity;
    }

    //c'est pour dire on choisit les donnees qu'il doit afficher exple password=null
    public  ProfilesDTO entityDTO(Profile entity){
        return new ProfilesDTO(
                entity.getCivility(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getPhone(),
                null
        );
    }
}
