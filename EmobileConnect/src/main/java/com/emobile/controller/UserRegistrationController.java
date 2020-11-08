package com.emobile.controller;


import com.emobile.service.FieldValidationService;
import com.emobile.service.StoreUserService;
import com.emobile.service.dto.NewUserRequestDto;
import com.emobile.util.GenericResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class UserRegistrationController {

    private final Logger log = LoggerFactory.getLogger(UserRegistrationController.class);

    @Autowired
    private FieldValidationService fieldValidationService;

    @Autowired
    private StoreUserService storeUserService;


    @PostMapping(value = "/newuser")
    public ResponseEntity<GenericResponse> addNewUser(@RequestBody NewUserRequestDto newUserRequest) {
        GenericResponse fieldValidationResult;
        fieldValidationResult = fieldValidationService.validate(newUserRequest);

        if(fieldValidationResult.getSuccess()){
        return new ResponseEntity<>(storeUserService.saveUser(newUserRequest), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(fieldValidationResult, HttpStatus.BAD_REQUEST);
        }

    }

}
