package com.infokey.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infokey.backend.User.UserAccount;
import com.infokey.backend.User.UserAccountLogin;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    private String asJsonString(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DirtiesContext
    void RegisterUserSuccessfullyWithCorrectRequirementReturnOkResponse() throws Exception {
        UserAccount newUserAccount = new UserAccount(null, "username", "username@gmail.com", "Password_1");
        mockMvc.perform(post("/api/v1/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(newUserAccount)))
                .andExpect(status().isCreated());
    }

    @Test
    @DirtiesContext
    void RegisterUserDuplicateEmailWithCorrectRequirementReturnConflictResponse() throws Exception {
        UserAccount newUserAccount = new UserAccount(null, "username", "username@gmail.com", "Password_1");
        mockMvc.perform(post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(newUserAccount)));

        mockMvc.perform(post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(newUserAccount)))
                .andExpect(status().isConflict());
    }

    @Test
    @DirtiesContext
    void RegisterUserWithIncorrectPasswordRequirementReturnBadRequest() throws Exception {
        UserAccount newUserAccount = new UserAccount(null, "username", "username@gmail.com", "Pa");
        mockMvc.perform(post("/api/v1/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(newUserAccount)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DirtiesContext
    void LoginWithCorrectCredentialsReturnOk() throws Exception {
        UserAccount newUserAccount = new UserAccount(null, "username", "username@gmail.com", "Password_1");
        mockMvc.perform(post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(newUserAccount)))
                .andExpect(status().isCreated());

        UserAccountLogin login = new UserAccountLogin("username", "Password_1");
        
        mockMvc.perform(post("/api/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(login)))
                .andExpect(status().isOk())
                .andReturn();
    }
}
