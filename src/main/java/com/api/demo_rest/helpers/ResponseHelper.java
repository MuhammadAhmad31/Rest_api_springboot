package com.api.demo_rest.helpers;

import java.util.HashMap;
import java.util.Map;

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
}
