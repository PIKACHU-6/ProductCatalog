package com.solution.product_catalog.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JacksonConvertor {

    public static <T> T jsonFileToObject(String fileName, Class<T> type) throws URISyntaxException, IOException {
        return new ObjectMapper().readValue(getFileAsString(fileName), type);
    }

    public static byte[] getFileAsString(String fileName) throws URISyntaxException, IOException {
        return Files.readAllBytes(Paths.get(ClassLoader.getSystemResource(fileName).toURI()));
    }

    public static <T> T stringToModel(String data, Class<T> type) throws JsonProcessingException {
        return new ObjectMapper().readValue(data, type);
    }

    public static String getFileAsRealString(String fileName) throws URISyntaxException, IOException {
        return new String(getFileAsString(fileName), StandardCharsets.UTF_8);
    }
}
