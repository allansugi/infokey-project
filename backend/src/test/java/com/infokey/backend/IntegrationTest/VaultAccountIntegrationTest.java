package com.infokey.backend.IntegrationTest;

import com.infokey.backend.Vault.request.UpdateVaultAccountItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infokey.backend.User.request.UserAccountLogin;
import com.infokey.backend.User.request.UserAccountRegister;
import com.infokey.backend.Vault.request.NewVaultAccountItemRequest;

import java.util.ArrayList;
import java.util.List;

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
     * @param token bearer token containing the user id
     * @param times number of account to be created
     * @throws Exception request error
     */
    private List<String> createMultipleVaultAccountItems(String token, int times) throws Exception {
        List<String> locations = new ArrayList<>();
        for (int i = 0; i < times; i++) {
            String name = "name" + (i+1);
            String username = "username" + (i+1);
            String password = "password" + (i+1);
            NewVaultAccountItemRequest item = new NewVaultAccountItemRequest(name, username, password);
            MvcResult result = mockMvc.perform(post("/api/v1/vaults")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(asJsonString(item))
                                        .header("Authorization", "Bearer " + token))
                                        .andReturn();
            locations.add(result.getResponse().getHeader("Location"));
        }
        return locations;
    }

    /**
     * register user and login to get the token
     * @return bearer token
     * @throws Exception request error
     */
    private String getToken() throws Exception {
        UserAccountRegister newUserAccount = new UserAccountRegister("username", "username@gmail.com", "Password_1");
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
    @DirtiesContext
    void CreateNewVaultAccountItemWithValidBearerTokenReturnNoContent() throws Exception {
        String token = getToken();
        NewVaultAccountItemRequest item = new NewVaultAccountItemRequest("name", "username", "password");
        MvcResult result = mockMvc.perform(post("/api/v1/vaults")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(asJsonString(item))
                                    .header("Authorization", "Bearer " + token))
                                    .andExpect(status().isCreated())
                                    .andReturn();

        String accountLocationUrl = result.getResponse().getHeader("Location");
        assert accountLocationUrl != null;
        mockMvc.perform(get(accountLocationUrl)
                        .header("Authorization", "Bearer " + token))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andExpect(jsonPath("name").value(item.name()))
                        .andExpect(jsonPath("username").value("username"))
                        .andExpect(jsonPath("password").value(item.password()));
    }

    @Test
    @DirtiesContext
    void GetVaultAccountWithInvalidIdReturnNotFound() throws Exception {
        String token = getToken();
        createMultipleVaultAccountItems(token, 1);
        mockMvc.perform(get("/api/v1/vaults/123").header("Authorization", "Bearer " + token))
                                        .andExpect(status().isNotFound());
    }

    @Test
    @DirtiesContext
    void GetVaultAccountWithValidBearerTokenReturnOk() throws Exception {
        String token = getToken();
        createMultipleVaultAccountItems(token, 3);
        mockMvc.perform(get("/api/v1/vaults")
                .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    @DirtiesContext
    void updateVaultAccountWithValidBearerTokenReturnNoContent() throws Exception {
        // add account into vault
        String token = getToken();
        List<String> vaultAccountLocations = createMultipleVaultAccountItems(token, 1);

        // update account
        UpdateVaultAccountItem updateItem = new UpdateVaultAccountItem("updateName", "updateUsername", "updatePassword");
        String locationUrl = vaultAccountLocations.get(0);
        mockMvc.perform(put(locationUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(updateItem))
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isNoContent());

        // get the account
        mockMvc.perform(get(locationUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(updateItem))
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value(updateItem.name()))
                .andExpect(jsonPath("username").value(updateItem.username()))
                .andExpect(jsonPath("password").value(updateItem.password()));
    }

    @Test
    @DirtiesContext
    void updateNonExistentAccountIdFromVaultReturnsNotFound() throws Exception {
        String token = getToken();
        createMultipleVaultAccountItems(token, 2);
        String invalidId = "123";
        UpdateVaultAccountItem updateItem = new UpdateVaultAccountItem("updateName", "updateUsername", "updatePassword");
        mockMvc.perform(put("/api/v1/vaults/" + invalidId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updateItem))
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isNotFound());
    }

    @Test
    @DirtiesContext
    void deleteAccountFromVaultReturnsNoContent() throws Exception {
        String token = getToken();
        List<String> vaultAccountLocations = createMultipleVaultAccountItems(token, 2);
        String request = vaultAccountLocations.get(0);
        mockMvc.perform(delete(request)
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isNoContent());

        // check number of accounts
        mockMvc.perform(get("/api/v1/vaults")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    @DirtiesContext
    void deleteNonExistentAccountIdFromVaultReturnsNotFound() throws Exception {
        String token = getToken();
        createMultipleVaultAccountItems(token, 2);
        String invalidId = "123";
        mockMvc.perform(delete("/api/v1/vaults/" + invalidId)
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isNotFound());
    }
}
