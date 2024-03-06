package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class Differ {
    private static Map<String, String> getInfoFromPath(String filePath) throws IOException {
        Map<String, String> result = new HashMap<>();
        Path path = Paths.get(filePath).toAbsolutePath().normalize();
        String fileResult = Files.readString(path);
        result.put("data", fileResult);
        if (filePath.endsWith(".json")) {
            result.put("format", "json");
        } else if (filePath.endsWith(".yaml") || filePath.endsWith(".yml")) {
            result.put("format", "yaml");
        }

        return result;
    }

    public static String generate(String firstFilePath, String secondFilePath, String formatName) throws IOException {
        String result;
//        String firstFormat = null;
//        String secondFormat = null;
//        Path path1 = Paths.get(firstFilePath).toAbsolutePath().normalize();
//        String firstFileResult = Files.readString(path1);
//        Path path2 = Paths.get(secondFilePath).toAbsolutePath().normalize();
//        String secondFileResult = Files.readString(path2);
//
//        if (firstFilePath.endsWith(".json")) {
//            firstFormat = "json";
//        } else if (firstFilePath.endsWith(".yaml") || firstFilePath.endsWith(".yml")) {
//            firstFormat = "yml";
//        }
//
//        if (secondFilePath.endsWith(".json")) {
//            secondFormat = "json";
//        } else if (secondFilePath.endsWith(".yaml") || secondFilePath.endsWith(".yml")) {
//            secondFormat = "yml";
//        }
        Map<String, String> firstFileMap = getInfoFromPath(firstFilePath);
        Map<String, String> secondFileMap = getInfoFromPath(secondFilePath);
        Map<String, Object> firstFile = Parser.parser(firstFileMap.get("data"), firstFileMap.get("format"));
        Map<String, Object> secondFile = Parser.parser(secondFileMap.get("data"), secondFileMap.get("format"));
        List<Map<String, Object>> resultDiffList = DifferFinder.findDifference(firstFile, secondFile);
        result = Formatter.chooseFormat(resultDiffList, formatName);
        return result;
    }

    public static String generate(String firstFilePath, String secondFilePath) throws IOException {
        String defaultFormatter = "stylish";
        return generate(firstFilePath, secondFilePath, defaultFormatter);
    }
}
