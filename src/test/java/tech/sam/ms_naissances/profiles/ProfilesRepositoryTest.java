package tech.sam.ms_naissances.profiles;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;


@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class ProfilesRepositoryTest {
    @Autowired
    ProfilesRepository profilesRepository;

    @Test
    void shouldReturnListOfProfiles(){
        //Arrange
        Profile profileOne=Profile.builder().email("one@email.test").build();
        Profile profileTwo=Profile.builder().email("two@email.test").build();
        this.profilesRepository.saveAll(List.of(profileOne,profileTwo));

        //Act
        List<Profile> profiles=this.profilesRepository.findAll();

        //Assert
        Assertions.assertEquals(2,profiles.size());
    }

}