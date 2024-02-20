package com.api.demo_rest.helpers;

import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class ResponseHelper {
    public static Map<String, Object> createResponse(String message, Object data, Integer code, String status) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("data", data);
        response.put("code", code);
        response.put("status", status);
        return response;
    }

    public static Map<String, Object> createErrorResponse(String message, String error, Integer code, String status) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("error", error);
        response.put("code", code);
        response.put("status", status);
        return response;
    }

    public static List<String> getErrors(Errors errors) {
        List<String> errorList = new ArrayList<>();

        errors.getAllErrors().forEach(error -> {
            String errorMessage = error.getDefaultMessage();
            errorList.add(errorMessage);
        });

        return errorList;
    }


    public static Map<String, Object> ValidationErrorResponse(Errors errors) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("message", "Validation error");
        errorResponse.put("code", HttpStatus.BAD_REQUEST.value());
        errorResponse.put("status", "error");
        errorResponse.put("errors", ResponseHelper.getErrors(errors));

        return errorResponse;
    }

}
