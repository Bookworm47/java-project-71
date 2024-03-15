package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.formatters.FormatterInterface;
import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;


public class Formatter {
    public static FormatterInterface chooseFormat(String formatName)
            throws JsonProcessingException {
        FormatterInterface formatResult;
        switch (formatName) {
            case "plain":
                formatResult = new Plain();
                break;
            case "json":
                formatResult = new Json();
                break;
            case "stylish":
                formatResult = new Stylish();
                break;
            default:
                throw new IllegalArgumentException("There is no such formatter");
        }
        return formatResult;
    }
}
