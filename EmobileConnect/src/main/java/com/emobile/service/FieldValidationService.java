package com.emobile.service;

import com.emobile.service.dto.NewUserRequestDto;
import com.emobile.util.GenericResponse;

public interface FieldValidationService {

     GenericResponse validate(NewUserRequestDto newUser);

}
