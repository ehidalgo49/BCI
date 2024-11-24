package com.bci.bciapi.controller;

import com.bci.bciapi.core.model.User;
import com.bci.bciapi.core.port.UserPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserPort userPort;

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setName("Juan Pérez");
        user.setEmail("juan.perez@colombia.com");
        user.setPassword("Password123!");
    }

    @Test
    public void testRegisterUser_Success() throws Exception {
        Mockito.when(userPort.registerUser(Mockito.any(User.class))).thenReturn(user);

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Juan Pérez\", \"email\": \"juan.perez@colombia.com\", \"password\": \"Password123!\", \"phones\": []}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void testRegisterUser_BadRequest() throws Exception {
        // Enviar datos inválidos
        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"\", \"email\": \"invalid-email\", \"password\": \"\"}"))
                .andExpect(status().isBadRequest());
    }
}
