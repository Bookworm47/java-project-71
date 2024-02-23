package hexlet.code;

import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static String chooseFormat(List<Map<String, Object>> differ, String formatName) {
        String formatResult;
        switch (formatName) {
            case "plain":
                formatResult = Plain.plain(differ);
                break;
            case "stylish":
            default:
                formatResult = Stylish.stylish(differ);
        }
        return formatResult;
    }
}
