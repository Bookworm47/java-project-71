package hexlet.code.formatters;

import hexlet.code.ChangeStatus;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static hexlet.code.formatters.FormattersSettings.NEWOBJECT;
import static hexlet.code.formatters.FormattersSettings.OLDOBJECT;

public class Stylish implements FormatterInterface {
    public String format(List<Map<String, Object>> differ) {
        StringBuilder jsonDifferStylishResult = new StringBuilder();
        String result;
        jsonDifferStylishResult.append("{" + "\n");
        for (Map<String, Object> diff : differ) {
            String key = diff.get("key").toString();
            ChangeStatus diffStatus = (ChangeStatus) diff.get("type");
            switch (Objects.requireNonNull(diffStatus)) {
                case SAME:
                    jsonDifferStylishResult.append("    ").append(key).append(": ").
                            append(diff.get(OLDOBJECT)).append("\n");
                    break;
                case CHANGE:
                    jsonDifferStylishResult.append("  - ").append(key).append(": ").
                            append(diff.get(OLDOBJECT)).append("\n");
                    jsonDifferStylishResult.append("  + ").append(key).append(": ").
                            append(diff.get(NEWOBJECT)).append("\n");
                    break;
                case ADD:
                    jsonDifferStylishResult.append("  + ").append(key).append(": ").
                            append(diff.get(NEWOBJECT)).append("\n");
                    break;
                case DELETE:
                    jsonDifferStylishResult.append("  - ").append(key).append(": ").
                            append(diff.get(OLDOBJECT)).append("\n");
                    break;
                default:
                    break;
            }
        }
        jsonDifferStylishResult.append("}");
        result = jsonDifferStylishResult.toString();
        return result;
    }
}
