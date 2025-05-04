package tech.sam.ms_naissances.declarations;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import tech.sam.ms_naissances.profiles.ProfilesMapper;

@AllArgsConstructor
@Component
public class DeclarationsMapper {
    private ProfilesMapper profilesMapper;
    public DeclarationDTO entityToDTO(Declaration declaration) {
        return DeclarationDTO
                .builder()
                .id(declaration.getId())
                .name(declaration.getName())
                .description(declaration.getDescription())
                .comment(declaration.getComment())
                .registered(declaration.getRegistered())
                .firstParent(profilesMapper.entityToDto(declaration.getFirstParent()))
                .secondParent(profilesMapper.entityToDto(declaration.getSecondParent()))
                .child(profilesMapper.entityToDto(declaration.getChild()))
                .company(declaration.getCompany())
                .statuses(declaration.getStatuses())
                .build();
    }
}
