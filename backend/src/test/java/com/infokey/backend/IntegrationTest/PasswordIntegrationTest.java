package com.infokey.backend.IntegrationTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infokey.backend.Password.response.GeneratePasswordResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PasswordIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Test
    void generatePasswordReturnsOK() throws Exception {
        mockMvc.perform(get("/api/v1/password/generate")
                        .param("length", "8")
                        .param("lower", "true")
                        .param("upper", "true")
                        .param("number", "true")
                        .param("special", "true"))
                .andExpect(status().isOk());
    }

    @Test
    void generatePasswordWithLowerEnabled() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/v1/password/generate")
                        .param("length", "8")
                        .param("lower", "true")
                        .param("upper", "false")
                        .param("number", "false")
                        .param("special", "false"))
                .andExpect(status().isOk())
                .andReturn();
        
        String serializedResponse = result.getResponse().getContentAsString();
        GeneratePasswordResponse response = mapper.readValue(serializedResponse, GeneratePasswordResponse.class);
        assertTrue(response.generatedPassword().matches("^.*[a-z]+.*$"));
    }

    @Test
    void generatePasswordWithSpecialEnabled() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/v1/password/generate")
                        .param("length", "8")
                        .param("lower", "true")
                        .param("upper", "false")
                        .param("number", "false")
                        .param("special", "true"))
                .andExpect(status().isOk())
                .andReturn();
        
        String serializedResponse = result.getResponse().getContentAsString();
        GeneratePasswordResponse response = mapper.readValue(serializedResponse, GeneratePasswordResponse.class);
        assertTrue(response.generatedPassword().matches("^.*[!@#\\$%\\^\\&*\\)\\(+=._-`~{}\\[\\]:;\"'<>,/?|\\\\]+.*$"));
    }

    @Test
    void generatePasswordWithUpperEnabled() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/v1/password/generate")
                        .param("length", "8")
                        .param("lower", "true")
                        .param("upper", "true")
                        .param("number", "false")
                        .param("special", "false"))
                .andExpect(status().isOk())
                .andReturn();
        
        String serializedResponse = result.getResponse().getContentAsString();
        GeneratePasswordResponse response = mapper.readValue(serializedResponse, GeneratePasswordResponse.class);
        assertTrue(response.generatedPassword().matches("^.*[A-Z]+.*$"));
    }

    @Test
    void generatePasswordWithNumberEnabled() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/v1/password/generate")
                        .param("length", "8")
                        .param("lower", "true")
                        .param("upper", "true")
                        .param("number", "true")
                        .param("special", "true"))
                .andExpect(status().isOk())
                .andReturn();
        
        String serializedResponse = result.getResponse().getContentAsString();
        GeneratePasswordResponse response = mapper.readValue(serializedResponse, GeneratePasswordResponse.class);
        assertTrue(response.generatedPassword().matches("^.*[0-9]+.*$"));
    }
    
}
