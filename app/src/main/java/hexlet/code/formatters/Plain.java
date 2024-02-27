package hexlet.code.formatters;

import hexlet.code.ChangeStatus;

import java.util.List;
import java.util.Map;

import static hexlet.code.formatters.FormattersSettings.NEWOBJECT;
import static hexlet.code.formatters.FormattersSettings.OLDOBJECT;

public class Plain {

    public static String plain(List<Map<String, Object>> differ) {
        StringBuilder jsonDifferPlainResult = new StringBuilder();
        String result;
        for (Map<String, Object> diff : differ) {
            String key = diff.keySet().iterator().next();
            ChangeStatus diffStatus = (ChangeStatus) diff.get(key);
            switch (diffStatus) {
                case CHANGE:
                    jsonDifferPlainResult.append(String.format("Property '%s' was updated. From %s to %s\n",
                            key, complexValueCheck(diff.get(OLDOBJECT)), complexValueCheck(diff.get(NEWOBJECT))));
                    break;
                case ADD:
                    jsonDifferPlainResult.append(String.format("Property '%s' was added with value: %s\n",
                            key, complexValueCheck(diff.get(NEWOBJECT))));
                    break;
                case DELETE:
                    jsonDifferPlainResult.append(String.format("Property '%s' was removed\n", key));
                    break;
                case SAME:
                default:
                    break;
            }
        }
        jsonDifferPlainResult.delete(jsonDifferPlainResult.lastIndexOf("\n"),
                jsonDifferPlainResult.length());
        result = jsonDifferPlainResult.toString();
        return result;
    }

    private static String complexValueCheck(Object obj) {
        String result;
        if (obj == null) {
            result = null;
        } else if (obj instanceof String) {
            result = String.format("'%s'", obj);
        } else if (obj instanceof Map || obj instanceof List) {
            result = "[complex value]";
        } else {
            result = obj.toString();
        }
        return result;
    }
}
