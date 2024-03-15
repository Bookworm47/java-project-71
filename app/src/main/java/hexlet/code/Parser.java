package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.Map;

public class Parser {
    public static Map<String, Object> parser(String data, String type) throws NullPointerException,
            JsonProcessingException {
        ObjectMapper objectMapper;

        if (type.equalsIgnoreCase("json")) {
            objectMapper = new ObjectMapper();
        } else {
            objectMapper = new YAMLMapper();
        }
        Map<String, Object> parsingResult = objectMapper.readValue(data, Map.class);
        return parsingResult;
    }
}
