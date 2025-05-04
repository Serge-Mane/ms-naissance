package tech.sam.ms_naissances.profiles;


import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tech.sam.ms_naissances.security.services.SecurityService;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class ProfilesService {
    private final ProfilesRepository profilesRepository;
    private final ProfilesMapper profilesMapper;
    private SecurityService securityService;

    public Set<ProfileDTO> search() {

        List<Profile> profiles = this.profilesRepository.findAll();
        return profiles.stream().map(this.profilesMapper::entityToDto).collect(Collectors.toSet());
    }

    public Profile read(int id) {
        Optional<Profile> profileOptional = this.profilesRepository.findById(id);
        return profileOptional.orElseThrow(() -> new EntityNotFoundException("Aucune entité ne correspond aux paramètres fournis"));
    }

    public Profile update(int id, Profile profile) {
        Profile profileInDatabase = this.read(id);

        profileInDatabase.setFirstName(profile.getFirstName());
        profileInDatabase.setLastName(profile.getLastName());
        profileInDatabase.setEmail(profile.getEmail());
        profileInDatabase.setPhone(profile.getPhone());

        profileInDatabase = this.profilesRepository.save(profileInDatabase);
        return profileInDatabase;
    }

    public void delete(int id) {
        Profile profile = this.read(id);
        this.profilesRepository.delete(profile);
    }

    public Profile createIfNotExists(Profile profile) {
        if(StringUtils.isEmpty(profile.getEmail())) {
            return this.profilesRepository.save(profile);
        }
        Optional<Profile> optionalProfile = this.profilesRepository.findByEmail(profile.getEmail());
        if (optionalProfile.isEmpty()) {
            profile = this.profilesRepository.save(profile);
        } else {
            profile = optionalProfile.get();
        }
        return profile;
    }

    public ProfileDTO getCurrentUser() {
        Profile profile = this.securityService.getCurrentUser();
        return this.profilesMapper.entityToDto(profile);
    }
}
