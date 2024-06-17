package com.infokey.backend.IntegrationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.infokey.backend.User.request.UserAccountLogin;
import com.infokey.backend.User.request.UserAccountRegister;
import com.infokey.backend.Vault.request.NewVaultAccountItemRequest;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class VaultAccountTest {
    
    @Autowired
    private TestRestTemplate restTemplate;

    /**
     * register user and login to get the token
     * @return
     * @throws Exception
     */
    private String getToken() throws Exception {
        UserAccountRegister newUserAccount = new UserAccountRegister("username", "username@gmail.com", "Password_1");
        restTemplate.postForEntity("/api/v1/auth/register", newUserAccount, Void.class);
        UserAccountLogin login = new UserAccountLogin("username@gmail.com", "Password_1");
        ResponseEntity<String> loginResponse = restTemplate.postForEntity("/api/v1/auth/login", login, String.class);
        return loginResponse.getBody();
    }

    @Test
    void AccessVaultControllerWithoutBearerTokenReturnUnAuthorized() throws Exception {
        NewVaultAccountItemRequest item = new NewVaultAccountItemRequest("name", "username", "password");
        ResponseEntity<Void> createResponse = restTemplate.postForEntity("/api/v1/vaults", item, Void.class);
        assertEquals(createResponse.getStatusCode(), HttpStatus.UNAUTHORIZED);
    }
}
