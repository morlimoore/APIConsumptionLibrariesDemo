package com.morlimoore.APIConsumptionLibrariesDemo.models;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
public class User implements Serializable {

    @NotBlank(message = "First name cannot be blank")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    private String lastName;

    @Email(message = "Enter a valid email address")
    @NotBlank(message = "Email cannot be blank")
    private String email;

    @Override
    public String toString() {
        return "{" +
                "\"firstName\": \"" + firstName + '"' +
                ", \"lastName\": \"" + lastName + '"' +
                ", \"email\": \"" + email + '"' +
                '}';
    }
}