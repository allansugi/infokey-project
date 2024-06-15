package com.infokey.backend.IntegrationTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infokey.backend.User.dto.UserAccount;
import com.infokey.backend.User.request.UserAccountLogin;
import com.infokey.backend.Vault.request.NewVaultAccountItemRequest;

@SpringBootTest
@AutoConfigureMockMvc
public class VaultAccountIntegrationTest {
    
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

    
    /**
     * register user and login to get the token
     * @return
     * @throws Exception
     */
    private String getToken() throws Exception {
        UserAccount newUserAccount = new UserAccount(null, "username", "username@gmail.com", "Password_1");
        mockMvc.perform(post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(newUserAccount)))
                .andExpect(status().isCreated());

        UserAccountLogin login = new UserAccountLogin("username@gmail.com", "Password_1");
        
        MvcResult result = mockMvc.perform(post("/api/v1/auth/login")
                                    .contentType(MediaType.APPLICATION_JSON)
                                            .content(asJsonString(login)))
                                    .andExpect(status().isOk())
                                    .andReturn();
        
        return result.getResponse().getContentAsString();
    }

    @Test
    void AccessVaultControllerWithoutBearerTokenReturnUnAuthorized() throws Exception {
        NewVaultAccountItemRequest item = new NewVaultAccountItemRequest("name", "username", "password");
        mockMvc.perform(post("/api/v1/vaults")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(item)))
            .andExpect(status().isUnauthorized());
    }

    @Test
    void CreateNewVaultAccountItemWithValidBearerTokenReturnNoContent() throws Exception {
        String token = getToken();
        NewVaultAccountItemRequest item = new NewVaultAccountItemRequest("name", "username", "password");
        mockMvc.perform(post("/api/v1/vaults")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(item))
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isNoContent());
    }

    @Test
    @DirtiesContext
    void GetVaultAccountWithValidBearerTokenReturnOk() throws Exception {
        String token = getToken();

        NewVaultAccountItemRequest item = new NewVaultAccountItemRequest("name", "username", "password");
        mockMvc.perform(post("/api/v1/vaults")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(item))
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isNoContent());

        
        mockMvc.perform(get("/api/v1/vaults")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }
}
