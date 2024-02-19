package hexlet.code;

import java.util.List;
import java.util.Map;

public class StylishFormatter {
    public static String stylish(Map<String, List<Object>> differ) {
        StringBuilder jsonDifferStylishResult = new StringBuilder();
        String result;
        Object partOfResultDiff;
        jsonDifferStylishResult.append("{" + "\n");
        for (Map.Entry<String, List<Object>> diff : differ.entrySet()) {
            String key = diff.getKey();
            partOfResultDiff = diff.getValue().get(1);
            switch ((ChangeStatus) diff.getValue().get(0)) {
                case SAME:
                    jsonDifferStylishResult.append("   ").append(key).append(": ").
                            append(partOfResultDiff).append("\n");
                    break;
                case CHANGE:
                    Object partOfResultDiff2 = diff.getValue().get(2);
                    jsonDifferStylishResult.append(" - ").append(key).append(": ").
                            append(partOfResultDiff).append("\n");
                    jsonDifferStylishResult.append(" + ").append(key).append(": ").
                            append(partOfResultDiff2).append("\n");
                    break;
                case ADD:
                    jsonDifferStylishResult.append(" + ").append(key).append(": ").
                            append(partOfResultDiff).append("\n");
                    break;
                case DELETE:
                    jsonDifferStylishResult.append(" - ").append(key).append(": ").
                            append(partOfResultDiff).append("\n");
                    break;
                default: break;
            }
        }
        jsonDifferStylishResult.append("}");
        result = jsonDifferStylishResult.toString();
        return result;
    }
}
