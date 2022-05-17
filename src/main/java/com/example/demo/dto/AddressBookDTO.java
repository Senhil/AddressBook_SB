package com.example.demo.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

//Created AddressBookDTO and add validations so while accepting data it gets validated
@Data
public class AddressBookDTO {
    @NotEmpty(message = "First name cant be empty")
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z]{2,}$", message = "Employee firstName is Invalid")
    private String firstName;

    @NotEmpty(message = "Last name cant be empty")
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z]{2,}$", message = "Employee firstName is Invalid")
    private String lastName;

    @Email
    private String email;

    @NotNull(message = "Phone number cant be empty")
    private long phoneNumber;

    @NotEmpty(message = "city cant be empty")
    private String city;

    @NotEmpty(message = "State cant be empty")
    private String state;
    @NotNull(message = "zip cant be empty")
    private Integer zip;


    public AddressBookDTO() {
        super();
    }
}