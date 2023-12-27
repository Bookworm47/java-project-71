package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeSet;
import java.util.SortedSet;

public class Differ {

    public static String generate(String firstFilePath, String secondFilePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String result;
        String js1;
        String js2;
        //Создаем объект файл из JSON по переданному пути
        if (Paths.get(firstFilePath).toFile().exists()) {
            js1 = Files.readString(Paths.get(firstFilePath));
            js2 = Files.readString(Paths.get(secondFilePath));
        } else {
            js1 = Files.readString(Paths.get("/home/konstantin/study"
                    + "/Hexlet_projects/java-project-71/app/src/main/resources/", firstFilePath));
            js2 = Files.readString(Paths.get("/home/konstantin/study"
                    + "/Hexlet_projects/java-project-71/app/src/main/resources/", secondFilePath));
        }
//        File firstFile = new File(firstFilePath);
//        File secondFile = new File(secondFilePath);

        //Создаем Map из переданных JSON
        Map<String, Object> firstJSONFile = objectMapper.readValue(js1, Map.class);
        Map<String, Object> secondJSONFile = objectMapper.readValue(js2, new TypeReference<Map<String, Object>>() { });
        SortedSet<String> allKeys = new TreeSet<>(firstJSONFile.keySet());
        allKeys.addAll(secondJSONFile.keySet());
        LinkedList<String> resultList = new LinkedList<>();

        for (String key : allKeys) {
            Object firstValue = firstJSONFile.get(key);
            Object secondValue = secondJSONFile.get(key);
            if (firstJSONFile.containsKey(key) && secondJSONFile.containsKey(key)) {
                if (firstValue.equals(secondValue)) {
                    resultList.add("   " + key + ": " + firstValue);
                } else {
                    resultList.add(" - " + key + ": " + firstValue);
                    resultList.add(" + " + key + ": " + secondValue);
                }
            } else if (secondJSONFile.containsKey(key)) {
                resultList.add(" + " + key + ": " + secondValue);
            } else {
                resultList.add(" - " + key + ": " + firstValue);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{" + "\n");
        for (String resultValue :resultList) {
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
