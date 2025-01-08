package tech.sam.ms_naissances.profiles;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Slf4j
@Service
public class ProfilesServicce {
    private final ProfilesRepository profilesRepository;

    public void create(Profile profile){
        log.info("Nouveau  compte  avec l'email {}", profile.getEmail());
        this.profilesRepository.save(profile);
    }

    public List<Profile> search() {
        return this.profilesRepository.findAll();
    }

    //Optional: veut dire si profile existe retourne le sinon retourne null
    public Profile read(int id) {
        Optional<Profile> profileOptional= this.profilesRepository.findById(id);
        return profileOptional.orElse(null);
    }

    public Profile update(int id, Profile profile) {
        //d'abord je reccuper le profile dans la bd
        Profile profileInDatabas=this.read(id);

        //je mets a jour le profile qui etait dans la bd
        profileInDatabas.setFirstName(profile.getFirstName());
        profileInDatabas.setLastName(profile.getLastName());
        profileInDatabas.setEmail(profile.getEmail());
        profileInDatabas.setPhone(profile.getPhone());

        //je sauvegarde le profile mise a jour puis je retourne le nouveau profile
        this.profilesRepository.save(profileInDatabas);
        return profileInDatabas;
    }

    public void delete(int id){
        Profile profile=this.read(id);
        this.profilesRepository.delete(profile);
    }
}
