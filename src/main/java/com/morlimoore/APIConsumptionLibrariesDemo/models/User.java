package com.morlimoore.APIConsumptionLibrariesDemo.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class User implements Serializable {

    private String firstName;
    private String lastName;
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