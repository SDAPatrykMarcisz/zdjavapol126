package pl.sda.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import pl.sda.service.PersonService;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = PersonController.class)
class PersonControllerIT {

    @MockBean
    private PersonService personService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCreatePersonWhenValidRequestBody() throws Exception {
        mockMvc.perform(post("/persons"))
        .andExpect(status().isOk());
    }
}
