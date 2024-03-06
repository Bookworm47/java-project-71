package hexlet.code;

import java.io.IOException;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.TreeSet;

import static hexlet.code.formatters.FormattersSettings.NEWOBJECT;
import static hexlet.code.formatters.FormattersSettings.OLDOBJECT;


public class DifferFinder {
    public static List<Map<String, Object>> findDifference(Map<String, Object> firstFile,
                                                           Map<String, Object> secondFile)
            throws IOException {
        Set<String> allKeys = new TreeSet<>(firstFile.keySet());
        allKeys.addAll(secondFile.keySet());
        List<Map<String, Object>> resultList = new ArrayList<>();
        String keyKey = "key";
        String typeKey = "type";

        for (String key : allKeys) {
            Object firstValue = firstFile.get(key);
            Object secondValue = secondFile.get(key);
            Map<String, Object> changeMap = new LinkedHashMap<>();
            if (firstFile.containsKey(key) && secondFile.containsKey(key)) {
                if (firstValue == null && secondValue == null) {
                    changeMap.put(keyKey, key);
                    changeMap.put(typeKey, ChangeStatus.CHANGE);
                    changeMap.put(OLDOBJECT, firstValue);
                } else if ((firstValue == null || secondValue == null) || !firstValue.equals(secondValue)) {
                    changeMap.put(keyKey, key);
                    changeMap.put(typeKey, ChangeStatus.CHANGE);
                    changeMap.put(OLDOBJECT, firstValue);
                    changeMap.put(NEWOBJECT, secondValue);
                } else {
                    changeMap.put(keyKey, key);
                    changeMap.put(typeKey, ChangeStatus.SAME);
                    changeMap.put(OLDOBJECT, firstValue);
                }
            } else if (secondFile.containsKey(key)) {
                changeMap.put(keyKey, key);
                changeMap.put(typeKey, ChangeStatus.ADD);
                changeMap.put(NEWOBJECT, secondValue);
            } else {
                changeMap.put(keyKey, key);
                changeMap.put(typeKey, ChangeStatus.DELETE);
                changeMap.put(OLDOBJECT, firstValue);
            }
            resultList.add(changeMap);
        }

        return resultList;
    }
}
