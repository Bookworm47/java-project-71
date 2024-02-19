import hexlet.code.Differ;
import hexlet.code.StylishFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestDiffer {
    String resultJson;
    String resultJson2;
    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName)
                .toAbsolutePath().normalize();
    }
    @BeforeEach
    void createResultJson() {
        resultJson = "{\n"
                + " - follow: false\n"
                + "   host: hexlet.io\n"
                + " - proxy: 123.234.53.22\n"
                + " - timeout: 50\n"
                + " + timeout: 20\n"
                + " + verbose: true\n"
                + "}";
        resultJson2 = "{\n"
                + "   chars1: [a, b, c]\n"
                + " - chars2: [d, e, f]\n"
                + " + chars2: false\n"
                + " - checked: false\n"
                + " + checked: true\n"
                + " - default: null\n"
                + " + default: [value1, value2]\n"
                + " - id: 45\n"
                + " + id: null\n"
                + " - key1: value1\n"
                + " + key2: value2\n"
                + "   numbers1: [1, 2, 3, 4]\n"
                + " - numbers2: [2, 3, 4, 5]\n"
                + " + numbers2: [22, 33, 44, 55]\n"
                + " - numbers3: [3, 4, 5]\n"
                + " + numbers4: [4, 5, 6]\n"
                + " + obj1: {nestedKey=value, isNested=true}\n"
                + " - setting1: Some value\n"
                + " + setting1: Another value\n"
                + " - setting2: 200\n"
                + " + setting2: 300\n"
                + " - setting3: true\n"
                + " + setting3: none\n"
                + "}";
    }
    @Test
    void differTest() throws IOException {
//        String filepath1 = getFixturePath("file1.json").toString();
//        String filepath2 = getFixturePath("file2.json").toString();
//        assertEquals(resultJson, Differ.generate(filepath1, filepath2));
//
//        String filepath3 = getFixturePath("file1.yml").toString();
//        String filepath4 = getFixturePath("file2.yml").toString();
//        assertEquals(resultJson, Differ.generate(filepath3, filepath4));

        String filepath5 = getFixturePath("innerFile1.json").toString();
        String filepath6 = getFixturePath("innerFile2.json").toString();
        assertEquals(resultJson2, StylishFormatter.stylish(Differ.generate(filepath5, filepath6)));

        String filepath7 = getFixturePath("innerFile1.yaml").toString();
        String filepath8 = getFixturePath("innerFile2.yaml").toString();
        assertEquals(resultJson2, StylishFormatter.stylish(Differ.generate(filepath7, filepath8)));
    }
}
