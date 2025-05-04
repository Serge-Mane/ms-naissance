package tech.sam.ms_naissances.profiles;

import org.springframework.stereotype.Component;

@Component
public class ProfilesMapper {
    public Profile dtoToEntity(ProfileDTO dto) {
        Profile entity = new Profile();
        entity.setPassword(dto.getPassword());
        entity.setPhone(dto.getPhone());
        entity.setEmail(dto.getEmail());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        return entity;
    }

    public ProfileDTO entityToDto(Profile entity) {
        ProfileDTO profileDTO =  ProfileDTO
                .builder()
                .civility(entity.getCivility())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .birthDate(entity.getBirthDate())
                .build();
                    if(entity.getRole() != null) {
                            profileDTO.setRole(entity.getRole().getName());
                    }
            return profileDTO;
    }
}
