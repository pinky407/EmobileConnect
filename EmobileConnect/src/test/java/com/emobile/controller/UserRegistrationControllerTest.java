package com.emobile.controller;

import com.emobile.service.FieldValidationService;
import com.emobile.service.StoreUserService;
import com.emobile.service.dto.NewUserRequestDto;
import com.emobile.util.GenericResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class UserRegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FieldValidationService fieldValidationService;

    @MockBean
    private StoreUserService storeUserService;

    @InjectMocks
    private UserRegistrationController userRegistrationController;

    @BeforeEach
    public void setupMvc() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(userRegistrationController).build();
    }

    @Test
    public void addNewUserShouldReturnOkOnGoodPayLoad() throws Exception {
        NewUserRequestDto newUserRequestDto = new NewUserRequestDto();
        GenericResponse genericResponse = GenericResponse.createSuccessResponse();
        when(fieldValidationService.validate(any(NewUserRequestDto.class))).thenReturn(genericResponse);
        when(storeUserService.saveUser(any(NewUserRequestDto.class))).thenReturn(genericResponse);
        mockMvc.perform(post("/emobile/newuser")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(newUserRequestDto.toString()))
                .andExpect(status().isOk());
    }

    @Test
    public void addNewUserShouldReturnBadRequestOnInvalidPayLoad() throws Exception {
        NewUserRequestDto newUserRequestDto = new NewUserRequestDto();
        GenericResponse genericResponse = GenericResponse.createErrorResponse("|Invalid mobile number");
        when(fieldValidationService.validate(any(NewUserRequestDto.class))).thenReturn(genericResponse);
        when(storeUserService.saveUser(any(NewUserRequestDto.class))).thenReturn(genericResponse);
        mockMvc.perform(post("/emobile/newuser")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(newUserRequestDto.toString()))
                .andExpect(status().isBadRequest());
    }


}
