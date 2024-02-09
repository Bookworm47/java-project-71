package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parser(String filePath) throws IOException, NullPointerException {
        ObjectMapper objectMapper = null;
        Path path = Paths.get(filePath).toAbsolutePath().normalize();
        System.out.println(path.toString());
        String fileResult = Files.readString(path);

        if (filePath.endsWith(".json")) {
            objectMapper = new ObjectMapper();
        } else if (filePath.endsWith(".yaml") || filePath.endsWith(".yml")) {
            objectMapper = new YAMLMapper();
        }
        Map<String, Object> parsingResult = objectMapper.readValue(fileResult, Map.class);
        return parsingResult;
    }
}
