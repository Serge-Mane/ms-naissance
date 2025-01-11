package tech.sam.ms_naissances.profiles;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(ProfilesControleur.class)
class ProfilesControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProfilesServicce profilesServicce;

    @BeforeEach
    void setUp() {
        Profile profileOne =  Profile.builder().email("one@email.com").build();
        Profile profileTwo = Profile.builder().email("two@email.com").build();
        when(profilesServicce.search()).thenReturn(List.of(profileOne, profileTwo));
    }
//
//    @DisplayName("Lire une liste de profiles")
//    @Test
//    void search() throws Exception {
//        this.mockMvc.perform(get("/profiles"))
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(content().string(containsString("one@email.test")))
//                .andExpect(content().string(containsString("two@email.test")));
//    }

    @DisplayName("Créer un profile")
    @Test
    void create () throws Exception {
        // Arrange
        Profile profileOne = Profile.builder().email("one@email.test").build();
        String profileAsString = new ObjectMapper().writeValueAsString(profileOne);

        // Act / Assert
        this.mockMvc
                .perform(
                        post("/profiles")
                                .content(profileAsString)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(status().isCreated());
    }

}