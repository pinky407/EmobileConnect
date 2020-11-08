package com.emobile.model;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable{

    @Id
    @Column(name = "request_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long requestId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zip_code")
    private long zipCode;

    @Column(name = "documentList")
    private String documentList;

    @Column(name = "status")
    private String status;

    @Column(name = "plan_name")
    private String planName;

    @Column(name = "assignedNumber")
    private String assignedNumber;
}
