package com.petstore.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class JsonUtil {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static <T> String writeValueAsString(T value) throws JsonProcessingException {
        return MAPPER.writeValueAsString(value);
    }

    public static <T> List<T> readListValue(String body, Class<T> clazz) throws JsonProcessingException {
        JavaType listType = MAPPER.getTypeFactory().constructCollectionType(List.class, clazz);
        return MAPPER.readValue(body, listType);
    }

    public static <T> T readValue(String body, Class<T> valueType) throws JsonProcessingException {
        return MAPPER.readValue(body, valueType);
    }
}
