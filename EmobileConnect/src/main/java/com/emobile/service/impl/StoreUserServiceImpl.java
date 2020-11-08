package com.emobile.service.impl;

import com.emobile.model.User;
import com.emobile.repository.UserRepository;
import com.emobile.service.StoreUserService;
import com.emobile.service.dto.NewUserRequestDto;
import com.emobile.service.dto.RegistrationResponse;
import com.emobile.util.ApplicationUtil;
import com.emobile.util.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreUserServiceImpl implements StoreUserService {

    @Autowired
    private UserRepository userRepository;

    public GenericResponse saveUser(NewUserRequestDto newUserRequest){
        User user = new User();
        GenericResponse genericResponse = GenericResponse.createSuccessResponse();
        try {
            ApplicationUtil.copyProperties(newUserRequest, user);
            user.setStatus("PENDING");
            User storedUser = userRepository.save(user);
            genericResponse.setResponse(buildResponse(storedUser));
        }catch (Exception e){
            genericResponse.failResponse();
            genericResponse.addMessage("Exception had occured while storing user"+e);
        }
        return genericResponse;
    }

    public RegistrationResponse buildResponse(User storedUser){
        RegistrationResponse response = new RegistrationResponse();
        ApplicationUtil.copyProperties(storedUser, response);
        return response;
    }


}
