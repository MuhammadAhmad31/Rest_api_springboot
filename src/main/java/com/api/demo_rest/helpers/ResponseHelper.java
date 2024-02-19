package com.api.demo_rest.helpers;

import java.util.HashMap;
import java.util.Map;

public class ResponseHelper {
    public static Map<String, Object> createResponse(String message, Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("data", data);
        return response;
    }

    public static Map<String, Object> createErrorResponse(String message, String error) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("error", error);
        return response;
    }
}
