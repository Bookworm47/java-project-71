package hexlet.code;

import java.io.IOException;
import java.util.List;
import java.util.Map;



public class Differ {
    public static String generate(String firstFilePath, String secondFilePath, String formatName) throws IOException {
        String result;
        List<Map<String, Object>> resultDiffList = DifferFinder.findDifference(firstFilePath, secondFilePath);
        result = Formatter.chooseFormat(resultDiffList, formatName);
        return result;
    }

    public static String generate(String firstFilePath, String secondFilePath) throws IOException {
        String result;
        String formatName = "stylish";
        List<Map<String, Object>> resultDiffList = DifferFinder.findDifference(firstFilePath, secondFilePath);
        result = Formatter.chooseFormat(resultDiffList, formatName);
        return result;
    }
}
