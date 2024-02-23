package hexlet.code;

import java.io.IOException;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.TreeSet;


public class DifferFinder {
    private static final String OLDOBJECT = "OldObject";
    private static final String NEWOBJECT = "NewObject";
    public static List<Map<String, Object>> findDifference(String firstFilePath, String secondFilePath)
            throws IOException {
        Map<String, Object> firstFile = Parser.parser(firstFilePath);
        Map<String, Object> secondFile = Parser.parser(secondFilePath);
        Set<String> allKeys = new TreeSet<>(firstFile.keySet());
        allKeys.addAll(secondFile.keySet());
        List<Map<String, Object>> resultList = new ArrayList<>();

        for (String key : allKeys) {
            Object firstValue = firstFile.get(key);
            Object secondValue = secondFile.get(key);
            Map<String, Object> changeMap = new LinkedHashMap<>();
            if (firstFile.containsKey(key) && secondFile.containsKey(key)) {
                if (firstValue == null && secondValue == null) {
                    changeMap.put(key, ChangeStatus.CHANGE);
                    changeMap.put(OLDOBJECT, firstValue);
                } else if ((firstValue == null || secondValue == null) || !firstValue.equals(secondValue)) {
                    changeMap.put(key, ChangeStatus.CHANGE);
                    changeMap.put(OLDOBJECT, firstValue);
                    changeMap.put(NEWOBJECT, secondValue);
                } else {
                    changeMap.put(key, ChangeStatus.SAME);
                    changeMap.put(OLDOBJECT, firstValue);
                }
            } else if (secondFile.containsKey(key)) {
                changeMap.put(key, ChangeStatus.ADD);
                changeMap.put(NEWOBJECT, secondValue);
            } else {
                changeMap.put(key, ChangeStatus.DELETE);
                changeMap.put(OLDOBJECT, firstValue);
            }
            resultList.add(changeMap);
        }

        return resultList;
    }
}
