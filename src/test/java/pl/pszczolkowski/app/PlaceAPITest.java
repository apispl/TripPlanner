package pl.pszczolkowski.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PlaceAPITest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void contextLoads(){
        assertThat(mockMvc).isNotNull();
        assertThat(objectMapper).isNotNull();
    }

    @Test
    void shouldGetPlace() throws Exception {
        mockMvc.perform(get("/places/{input}", "fuertaventura"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Is.is("Fuerteventura")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.adress", Is.is("Fuerteventura, Las Palmas de Gran Canaria, Hiszpania")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.locationLat", Is.is(28.35874366760254)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.photoHeight", Is.is(459.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.photoWidth", Is.is(816.0)));
    }

    @Test
    void shouldNotGetPlace() throws Exception {
        mockMvc.perform(get("/places/{input}", " "))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldgetPhoto() throws Exception {
        mockMvc.perform(get("/places/photos/{photoReference}", "CmRaAAAAWcqAm8tJBcew2UHuaai2QhOBb6yM8jgDQN8Vbba52VOh9zncyTFbTjGS4n" +
                "PD8ccMynOaV0qqJj_fUPIl76z8TKMxV-F-XjGBQSsz-MGAJnvLkw87XizBDtct5Lz7sdm4EhAKHbi3n8wE_HDLxFLThp6aGhQ1A_1k2b1c6psps-rR6EJpnlZ7MQ"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.IMAGE_JPEG));
    }
}
