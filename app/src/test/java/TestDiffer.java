import hexlet.code.Differ;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestDiffer {
    String resultJson;
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
    }
    @Test
    void differTest() throws IOException {
        String filepath1 = getFixturePath("file1.json").toString();
        String filepath2 = getFixturePath("file2.json").toString();
        assertEquals(resultJson, Differ.generate(filepath1, filepath2));

        String filepath3 = getFixturePath("file1.yml").toString();
        String filepath4 = getFixturePath("file2.yml").toString();
        assertEquals(resultJson, Differ.generate(filepath1, filepath2));
    }
}
