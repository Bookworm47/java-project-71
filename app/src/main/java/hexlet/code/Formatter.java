package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static String chooseFormat(List<Map<String, Object>> differ, String formatName)
            throws JsonProcessingException {
        String formatResult;
        switch (formatName) {
            case "plain":
                formatResult = Plain.plain(differ);
                break;
            case "json":
                formatResult = Json.jsonFormatter(differ);
                break;
            case "stylish":
            default:
                formatResult = Stylish.stylish(differ);
        }
        return formatResult;
    }
}
