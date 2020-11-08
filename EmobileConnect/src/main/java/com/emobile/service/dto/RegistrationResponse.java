package com.emobile.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationResponse {

    @JsonProperty("status")
    private String status;

    @JsonProperty("request_id")
    private long requestId;

}
