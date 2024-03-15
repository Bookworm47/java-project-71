package hexlet.code;

import hexlet.code.formatters.FormatterInterface;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;



public class Differ {
    private static String getData(String filePath) throws IOException {
        String data = "";
        Path path = Paths.get(filePath).toAbsolutePath().normalize();
        data = Files.readString(path);
        return data;
    }
    private static String getFormat(String filePath) {
        String format = "";
        if (filePath.endsWith(".json")) {
            format = "json";
        } else if (filePath.endsWith(".yaml") || filePath.endsWith(".yml")) {
            format = "yaml";
        }
        return format;
    }

    public static String generate(String firstFilePath, String secondFilePath, String formatName) throws IOException {
        String result;

        String firstData = getData(firstFilePath);
        String firstFormat = getFormat(firstFilePath);
        String secondData = getData(secondFilePath);
        String secondFormat = getFormat(secondFilePath);

        Map<String, Object> firstFile = Parser.parser(firstData, firstFormat);
        Map<String, Object> secondFile = Parser.parser(secondData, secondFormat);
        List<Map<String, Object>> resultDiffList = DifferFinder.findDifference(firstFile, secondFile);
        FormatterInterface format = Formatter.chooseFormat(formatName);
        result = format.format(resultDiffList);
        return result;
    }

    public static String generate(String firstFilePath, String secondFilePath) throws IOException {
        String defaultFormatter = "stylish";
        return generate(firstFilePath, secondFilePath, defaultFormatter);
    }
}
