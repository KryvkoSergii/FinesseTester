package ua.com.smiddle.core.util.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Added by A.Osadchuk on 25.03.2016 at 12:23.
 * Project: AgentScripting
 */
public class JacksonUtil {
    private final static ObjectMapper JACKSON_MAPPER = new ObjectMapper();


    //Methods
    public static String objectToJson(Object obj) throws JsonProcessingException {
        synchronized (JACKSON_MAPPER) {
            return JACKSON_MAPPER.writeValueAsString(obj);
        }
    }

    public static Object jsonToObject(String json, Class clazz) throws IOException {
        synchronized (JACKSON_MAPPER) {
            return JACKSON_MAPPER.readValue(json, clazz);
        }
    }

    public static <T> T unknownJsonStructureToObject(String json, Class<T> clazz) throws IOException {
        synchronized (JACKSON_MAPPER) {
            return JACKSON_MAPPER.readValue(json, clazz);
        }
    }

    public static String unknownObjectToJson(Object o) throws IOException {
        synchronized (JACKSON_MAPPER) {
            return JACKSON_MAPPER.writeValueAsString(o);
        }
    }
}
