package com.api.demo_rest.helpers;

import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;

import java.util.*;

public class ResponseHelper {

    private static final String DEFAULT_ERROR_MESSAGE = "Validation error";
    private static final int DEFAULT_ERROR_CODE = HttpStatus.BAD_REQUEST.value();
    private static final String DEFAULT_ERROR_STATUS = "error";


    private static Map<String, Object> createBaseResponse(String message, Integer code, String status) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("code", code);
        response.put("status", status);
        return response;
    }

    public static Map<String, Object> createResponse(String message, Object data, Integer code, String status) {
        Map<String, Object> response = createBaseResponse(message, code, status);
        response.put("data", data);
        return response;
    }

    public static Map<String, Object> createErrorResponse(String message, String error, Integer code, String status) {
        Map<String, Object> response = createBaseResponse(message, code, status);
        response.put("error", error);
        return response;
    }

    public static List<String> getErrors(Errors errors) {
        List<String> errorList = new ArrayList<>();
        errors.getAllErrors().forEach(error -> errorList.add(error.getDefaultMessage()));
        return Collections.unmodifiableList(errorList);
    }

    public static Map<String, Object> createValidationErrorResponse(Errors errors) {
        Map<String, Object> errorResponse = createBaseResponse(DEFAULT_ERROR_MESSAGE, DEFAULT_ERROR_CODE, DEFAULT_ERROR_STATUS);
        errorResponse.put("errors", Collections.unmodifiableList(getErrors(errors)));
        return errorResponse;
    }
}
