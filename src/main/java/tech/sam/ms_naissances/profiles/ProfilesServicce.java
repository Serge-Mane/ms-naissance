package tech.sam.ms_naissances.profiles;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tech.sam.ms_naissances.shared.entities.Address;
import tech.sam.ms_naissances.shared.services.AddressesService;
import tech.sam.ms_naissances.shared.services.ValidationServices;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Slf4j
@Service
public class ProfilesServicce {
    private final AddressesService addressesService;
    private final ProfilesRepository profilesRepository;
    private final ValidationServices validationServices;

    public void create(Profile profile){
        log.info("Nouveau  compte  avec l'email {}", profile.getEmail());

        //je recupere d'abord une adresse
        if (profile.getAddress() != null){
            Address address=this.addressesService.create(profile.getAddress());
            profile.setAddress(address);
        }


        //a la creation du profile il faut valider d'abord l'email et le phone
        this.validationServices.validateEmail(profile.getEmail());
        this.validationServices.validatePhone(profile.getPhone());
        this.profilesRepository.save(profile);
    }

    public List<Profile> search() {
        return this.profilesRepository.findAll();
    }

    //Optional: veut dire si profile existe retourne le sinon retourne l'erreur
    public Profile read(int id) {
        Optional<Profile> profileOptional= this.profilesRepository.findById(id);
        return profileOptional.orElseThrow(() ->new EntityNotFoundException(
                "Aucune valeur ne correspond aux parametres fournis"));
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
