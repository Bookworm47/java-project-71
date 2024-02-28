import hexlet.code.Differ;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class TestDiffer {
    private String result1;
    private String result2;
    private String result3;
    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName)
                .toAbsolutePath().normalize();
    }
    @BeforeEach
    void createResultJson() {
        result1 = "Property 'chars2' was updated. From [complex value] to false\n"
                + "Property 'checked' was updated. From false to true\n"
                + "Property 'default' was updated. From null to [complex value]\n"
                + "Property 'id' was updated. From 45 to null\n"
                + "Property 'key1' was removed\n"
                + "Property 'key2' was added with value: 'value2'\n"
                + "Property 'numbers2' was updated. From [complex value] to [complex value]\n"
                + "Property 'numbers3' was removed\n"
                + "Property 'numbers4' was added with value: [complex value]\n"
                + "Property 'obj1' was added with value: [complex value]\n"
                + "Property 'setting1' was updated. From 'Some value' to 'Another value'\n"
                + "Property 'setting2' was updated. From 200 to 300\n"
                + "Property 'setting3' was updated. From true to 'none'";
        result2 = "{\n"
                + "    chars1: [a, b, c]\n"
                + "  - chars2: [d, e, f]\n"
                + "  + chars2: false\n"
                + "  - checked: false\n"
                + "  + checked: true\n"
                + "  - default: null\n"
                + "  + default: [value1, value2]\n"
                + "  - id: 45\n"
                + "  + id: null\n"
                + "  - key1: value1\n"
                + "  + key2: value2\n"
                + "    numbers1: [1, 2, 3, 4]\n"
                + "  - numbers2: [2, 3, 4, 5]\n"
                + "  + numbers2: [22, 33, 44, 55]\n"
                + "  - numbers3: [3, 4, 5]\n"
                + "  + numbers4: [4, 5, 6]\n"
                + "  + obj1: {nestedKey=value, isNested=true}\n"
                + "  - setting1: Some value\n"
                + "  + setting1: Another value\n"
                + "  - setting2: 200\n"
                + "  + setting2: 300\n"
                + "  - setting3: true\n"
                + "  + setting3: none\n"
                + "}";
        result3 = "[{\"chars1\":\"SAME\",\"OldObject\":[\"a\",\"b\",\"c\"]},"
                + "{\"chars2\":\"CHANGE\",\"OldObject\":[\"d\",\"e\",\"f\"],\"NewObject\":false},"
                + "{\"checked\":\"CHANGE\",\"OldObject\":false,\"NewObject\":true},"
                + "{\"default\":\"CHANGE\",\"OldObject\":null,\"NewObject\":[\"value1\",\"value2\"]},"
                + "{\"id\":\"CHANGE\",\"OldObject\":45,\"NewObject\":null},"
                + "{\"key1\":\"DELETE\",\"OldObject\":\"value1\"},"
                + "{\"key2\":\"ADD\",\"NewObject\":\"value2\"},{\"numbers1\":\"SAME\",\"OldObject\":[1,2,3,4]},"
                + "{\"numbers2\":\"CHANGE\",\"OldObject\":[2,3,4,5],\"NewObject\":[22,33,44,55]},"
                + "{\"numbers3\":\"DELETE\",\"OldObject\":[3,4,5]},{\"numbers4\":\"ADD\",\"NewObject\":[4,5,6]},"
                + "{\"obj1\":\"ADD\",\"NewObject\":{\"nestedKey\":\"value\",\"isNested\":true}},"
                + "{\"setting1\":\"CHANGE\",\"OldObject\":\"Some value\",\"NewObject\":\"Another value\"},"
                + "{\"setting2\":\"CHANGE\",\"OldObject\":200,\"NewObject\":300},"
                + "{\"setting3\":\"CHANGE\",\"OldObject\":true,\"NewObject\":\"none\"}]";
    }
    @Test
    void stylishTest() throws IOException {
        String filepath1 = getFixturePath("innerFile1.json").toString();
        String filepath2 = getFixturePath("innerFile2.json").toString();
        assertEquals(result2, Differ.generate(filepath1, filepath2));

        String filepath3 = getFixturePath("innerFile1.yaml").toString();
        String filepath4 = getFixturePath("innerFile2.yaml").toString();
        assertEquals(result2, Differ.generate(filepath3, filepath4));
    }
    @Test
    void plainTest() throws IOException {
        String format = "plain";
        String filepath1 = getFixturePath("innerFile1.json").toString();
        String filepath2 = getFixturePath("innerFile2.json").toString();
        assertEquals(result1, Differ.generate(filepath1, filepath2, format));

        String filepath3 = getFixturePath("innerFile1.yaml").toString();
        String filepath4 = getFixturePath("innerFile2.yaml").toString();
        assertEquals(result1, Differ.generate(filepath3, filepath4, format));
    }
    @Test
    void jsonTest() throws IOException {
        String format = "json";
        String filepath1 = getFixturePath("innerFile1.json").toString();
        String filepath2 = getFixturePath("innerFile2.json").toString();
        assertEquals(result3, Differ.generate(filepath1, filepath2, format));

        String filepath3 = getFixturePath("innerFile1.yaml").toString();
        String filepath4 = getFixturePath("innerFile2.yaml").toString();
        assertEquals(result3, Differ.generate(filepath3, filepath4, format));
    }
}
