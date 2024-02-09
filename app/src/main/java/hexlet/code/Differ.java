package hexlet.code;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeSet;
import java.util.SortedSet;

public class Differ {

    public static String generate(String firstFilePath, String secondFilePath) throws IOException {
        String result;
        Map<String, Object> firstFile = Parser.parser(firstFilePath);
        Map<String, Object> secondFile = Parser.parser(secondFilePath);
        SortedSet<String> allKeys = new TreeSet<>(firstFile.keySet());
        allKeys.addAll(secondFile.keySet());
        LinkedList<String> resultList = new LinkedList<>();

        for (String key : allKeys) {
            Object firstValue = firstFile.get(key);
            Object secondValue = secondFile.get(key);
            if (firstFile.containsKey(key) && secondFile.containsKey(key)) {
                if (firstValue.equals(secondValue)) {
                    resultList.add("   " + key + ": " + firstValue);
                } else {
                    resultList.add(" - " + key + ": " + firstValue);
                    resultList.add(" + " + key + ": " + secondValue);
                }
            } else if (secondFile.containsKey(key)) {
                resultList.add(" + " + key + ": " + secondValue);
            } else {
                resultList.add(" - " + key + ": " + firstValue);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{" + "\n");
        for (String resultValue : resultList) {
            stringBuilder.append(resultValue);
            stringBuilder.append("\n");
        }
        stringBuilder.append("}");
        result = stringBuilder.toString();

        //SortedSet
        //Собрать ключи в один SortedSet из обоих карт
        //Обойти каждый ключ и понять тип изменения
        //Может быть сделать через ENUM
        //Собарть в мапу

        return result;
    }
}
