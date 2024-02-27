package hexlet.code.formatters;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

public class Json {

    public static String jsonFormatter(List<Map<String, Object>> differ) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String resultJsonFormatter = objectMapper.writeValueAsString(differ);
        return resultJsonFormatter;
    }
}
