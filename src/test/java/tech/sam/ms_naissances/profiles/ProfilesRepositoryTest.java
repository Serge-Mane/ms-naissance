package tech.sam.ms_naissances.profiles;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;


@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class ProfilesRepositoryTest {
    @Autowired
    ProfilesRepository profilesRepository;

    @BeforeEach
    void setUp(){
        //Arrange
        Profile profileOne=Profile.builder().email("one@email.test").build();
        Profile profileTwo=Profile.builder().email("two@email.test").build();
        this.profilesRepository.saveAll(List.of(profileOne,profileTwo));
    }

    @AfterEach
    void reset(){
        //Act
        this.profilesRepository.deleteAll();
    }

    @Test
    void shouldReturnListOfProfiles(){

        //Act
        List<Profile> profiles=this.profilesRepository.findAll();

        //Assert
        Assertions.assertEquals(2,profiles.size());
    }


    @Test
    void shouldReturnProfilesByEmail(){

        //Act
        Optional<Profile> profile=this.profilesRepository.findByEmail("two@email.test");

        //Assert
        Assertions.assertTrue(profile.isPresent());
    }

}