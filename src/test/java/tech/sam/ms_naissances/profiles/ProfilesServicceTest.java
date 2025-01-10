package tech.sam.ms_naissances.profiles;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ProfilesServicceTest {

    @Mock
    ProfilesRepository profilesRepository;
    @InjectMocks
    ProfilesServicce profilesServicce;

    @Test
    void shouldReturnAllProfiles() {
        //Arrange on a rien parce qu'on rien a passer dans notre parametre

        //Act
        List<Profile> profileList=this.profilesServicce.search();

        //Assert
        assertTrue(profileList.isEmpty());
    }
}