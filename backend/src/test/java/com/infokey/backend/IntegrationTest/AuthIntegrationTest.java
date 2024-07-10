package com.infokey.backend.IntegrationTest;

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
import com.infokey.backend.User.request.UserAccountLogin;
import com.infokey.backend.User.request.UserAccountRegister;

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
        UserAccountRegister newUserAccount = new UserAccountRegister("username", "username@gmail.com", "Password_1");
        mockMvc.perform(post("/api/v1/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(newUserAccount)))
                .andExpect(status().isCreated());
    }

    @Test
    @DirtiesContext
    void RegisterUserDuplicateEmailWithCorrectRequirementReturnConflictResponse() throws Exception {
        UserAccountRegister newUserAccount = new UserAccountRegister("username", "username@gmail.com", "Password_1");
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
    void RegisterUserWithIncorrectShortLengthReturnBadRequest() throws Exception {
        UserAccountRegister newUserAccount = new UserAccountRegister("username", "username@gmail.com", "Pa1#");
        mockMvc.perform(post("/api/v1/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(newUserAccount)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DirtiesContext
    void RegisterUserWithIncorrectNoLowercaseReturnBadRequest() throws Exception {
        UserAccountRegister newUserAccount = new UserAccountRegister("username", "username@gmail.com", "PASSWORD_123");
        mockMvc.perform(post("/api/v1/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(newUserAccount)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DirtiesContext
    void RegisterUserWithIncorrectNoUppercaseReturnBadRequest() throws Exception {
        UserAccountRegister newUserAccount = new UserAccountRegister("username", "username@gmail.com", "password_123");
        mockMvc.perform(post("/api/v1/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(newUserAccount)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DirtiesContext
    void RegisterUserWithIncorrectNoNumericReturnBadRequest() throws Exception {
        UserAccountRegister newUserAccount = new UserAccountRegister("username", "username@gmail.com", "password_ONE");
        mockMvc.perform(post("/api/v1/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(newUserAccount)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DirtiesContext
    void RegisterUserWithIncorrectNoSpecialCharacterReturnBadRequest() throws Exception {
        UserAccountRegister newUserAccount = new UserAccountRegister("username", "username@gmail.com", "Password123");
        mockMvc.perform(post("/api/v1/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(newUserAccount)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DirtiesContext
    void LoginWithCorrectCredentialsReturnOk() throws Exception {
        UserAccountRegister newUserAccount = new UserAccountRegister("username", "username@gmail.com", "Password_1");
        mockMvc.perform(post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(newUserAccount)))
                .andExpect(status().isCreated());

        UserAccountLogin login = new UserAccountLogin("username@gmail.com", "Password_1");
        
        mockMvc.perform(post("/api/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(login)))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @DirtiesContext
    void LoginWithIncorrectCredentialReturnsUnAuthorized() throws Exception {
        UserAccountRegister newUserAccount = new UserAccountRegister("username", "username@gmail.com", "Password_1");
        mockMvc.perform(post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(newUserAccount)))
                .andExpect(status().isCreated());

        // uses wrong email
        UserAccountLogin login1 = new UserAccountLogin("WrongUsername@gmail.com", "Password_1");
        
        mockMvc.perform(post("/api/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(login1)))
                .andExpect(status().isUnauthorized());

        // uses wrong password
        UserAccountLogin login2 = new UserAccountLogin("username@gmail.com", "Password_");
        
        mockMvc.perform(post("/api/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(login2)))
                .andExpect(status().isUnauthorized());
    }
}
