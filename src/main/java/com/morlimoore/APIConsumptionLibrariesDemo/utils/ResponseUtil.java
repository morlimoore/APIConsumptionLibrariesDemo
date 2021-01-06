package com.morlimoore.APIConsumptionLibrariesDemo.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {

    public static ResponseEntity<String> createResponse(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(message);
    }
}
