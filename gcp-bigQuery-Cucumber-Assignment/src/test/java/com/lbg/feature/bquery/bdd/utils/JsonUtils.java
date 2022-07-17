package com.lbg.feature.bquery.bdd.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.util.Optional;

public final class JsonUtils {

    private static final ThreadLocal<ObjectMapper> OBJECT_MAPPER_THREAD_LOCAL = ThreadLocal
            .withInitial(() -> new ObjectMapper());

    private JsonUtils() {
    }

    private static final ObjectMapper get() {
        return OBJECT_MAPPER_THREAD_LOCAL.get();
    }

    public static ObjectNode createObjectNode() {
        return get().createObjectNode();
    }

    public static String toString(Object object) {
        return Optional.ofNullable(object).map(val -> ExceptionHandler.unchecked(() -> get().writeValueAsString(val),
                ex -> ExceptionHandler.throwException(ex))).orElse("");
    }

    public static final <T> T toClass(String object, Class<?> zassType) {
        return ExceptionHandler.unchecked(() -> get().readerFor(zassType).readValue(object),
                ex -> ExceptionHandler.throwException(ex));
    }

    public static final JsonNode toClass(Object object) {
        return ExceptionHandler.unchecked(() -> get().convertValue(object, JsonNode.class),
                ex -> ExceptionHandler.throwException(ex));
    }

    public static JsonNode toJson(String payload) {
        return ExceptionHandler.unchecked(() -> get().readTree(payload), ex -> ExceptionHandler.throwException(ex));
    }

    public static JsonNode readTree(File file) {
        return ExceptionHandler.unchecked(() -> get().readTree(file), ex -> ExceptionHandler.throwException(ex));
    }
}
