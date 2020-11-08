package com.emobile.service.impl;

import com.emobile.service.FieldValidationService;
import com.emobile.service.dto.NewUserRequestDto;
import com.emobile.util.GenericResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class FieldValidationServiceImpl implements FieldValidationService {
    private final Logger log = LoggerFactory.getLogger(FieldValidationServiceImpl.class);


@Override
    public GenericResponse validate(NewUserRequestDto newUser){
        GenericResponse genericResponse = GenericResponse.createSuccessResponse();
        try {
            if (!validateMobileNumber(newUser.getMobileNumber())) {
                genericResponse.failResponse();
                genericResponse.addMessage("|Mobile Number Validation failed");
            }
            if (!validateEmail(newUser.getEmail())) {
                genericResponse.failResponse();
                genericResponse.addMessage("|Email Validation failed");
            }
            if (!StringUtils.hasText(newUser.getFirstName())) {
                genericResponse.failResponse();
                genericResponse.addMessage("|First Name cant be Null");
            }
            if (!StringUtils.hasText(newUser.getState())) {
                genericResponse.failResponse();
                genericResponse.addMessage("|State cant be Null");
            }
            if (!StringUtils.hasText(newUser.getCity())) {
                genericResponse.failResponse();
                genericResponse.addMessage("|City cant be Null");
            }
        }catch(Exception e){
            genericResponse.failResponse();
            genericResponse.addMessage("Exception had occured while storing user"+e);
        }
        return  genericResponse;
    }


    public boolean validateMobileNumber(String mobileNumber){
        // validate phone numbers of format "1234567890"
        if (mobileNumber.matches("\\d{10}"))
            return true;
            // validating phone number with -, . or spaces
        else if (mobileNumber.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}"))
            return true;

        else if (mobileNumber.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}"))
            return true;
            // return false if nothing matches the input
        else
            return false;
    }

    public boolean validateEmail(String email){
        //email validation as permitted by RFC 5322
        String validEmail = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

        if(email.matches(validEmail)){
            return true;
        }else
            return false;
    }


}
