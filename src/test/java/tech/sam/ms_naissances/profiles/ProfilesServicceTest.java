package tech.sam.ms_naissances.profiles;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProfilesServicceTest {

    @Mock
    ProfilesRepository profilesRepository;
    @InjectMocks
    ProfilesServicce profilesServicce;

    @Test
    void shouldReturnAllProfiles() {
        //Arrange veut dire quand on fait ca on retourne une liste de profile
        when(this.profilesRepository.findAll()).thenReturn(
                List.of(
                     Profile.builder()
                             .email("test@test.com")
                             .firstName("test")
                             .lastName("UNKWON")
                             .build()
                )
        );

        //Act
        Set<ProfilesDTO> profileList=this.profilesServicce.search();

        //Assert
        assertEquals(1,profileList.size());
    }
}