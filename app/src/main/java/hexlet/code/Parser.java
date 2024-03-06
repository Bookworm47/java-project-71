package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.Map;

public class Parser {
    public static Map<String, Object> parser(String file, String type) throws NullPointerException,
            JsonProcessingException {
        ObjectMapper objectMapper = new YAMLMapper();

        if (type.equalsIgnoreCase("json")) {
            objectMapper = new ObjectMapper();
        } else if (file.equalsIgnoreCase("yaml") || file.equalsIgnoreCase("yml")) {
            objectMapper = new YAMLMapper();
        }
        Map<String, Object> parsingResult = objectMapper.readValue(file, Map.class);
        return parsingResult;
    }
}
