package hexlet.code;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.TreeMap;


public class Differ {

    public static Map<String, List<Object>> generate(String firstFilePath, String secondFilePath) throws IOException {
        Map<String, Object> firstFile = Parser.parser(firstFilePath);
        Map<String, Object> secondFile = Parser.parser(secondFilePath);
        Set<String> allKeys = new TreeSet<>(firstFile.keySet());
        allKeys.addAll(secondFile.keySet());
        Map<String, List<Object>> resultMap = new TreeMap<>();

        for (String key : allKeys) {
            Object firstValue = firstFile.get(key);
            Object secondValue = secondFile.get(key);
            List<Object> changeList = new ArrayList<>();
            if (firstFile.containsKey(key) && secondFile.containsKey(key)) {
                if (firstValue == null && secondValue == null) {
                    changeList.add(ChangeStatus.SAME);
                    changeList.add(firstValue);
                } else if ((firstValue == null || secondValue == null) || !firstValue.equals(secondValue)) {
                    changeList.add(ChangeStatus.CHANGE);
                    changeList.add(firstValue);
                    changeList.add(secondValue);
                } else {
                    changeList.add(ChangeStatus.SAME);
                    changeList.add(firstValue);
                }
            } else if (secondFile.containsKey(key)) {
                changeList.add(ChangeStatus.ADD);
                changeList.add(secondValue);
            } else {
                changeList.add(ChangeStatus.DELETE);
                changeList.add(firstValue);
            }
            resultMap.put(key, changeList);
        }

        return resultMap;
    }
}
