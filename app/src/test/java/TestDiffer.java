import hexlet.code.Differ;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class TestDiffer {

    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName)
                .toAbsolutePath().normalize();
    }

    private static String readFixtureFormat(String formatName) throws IOException {
        Path absolutePath = getFixturePath("formats/" + formatName);
        return Files.readString(absolutePath);
    }

    @Test
    void stylishTest() throws IOException {
        String format = "stylish";
        String filepath1 = getFixturePath("innerFile1.json").toString();
        String filepath2 = getFixturePath("innerFile2.json").toString();
        assertEquals(readFixtureFormat(format), Differ.generate(filepath1, filepath2));

        String filepath3 = getFixturePath("innerFile1.yaml").toString();
        String filepath4 = getFixturePath("innerFile2.yaml").toString();
        assertEquals(readFixtureFormat(format), Differ.generate(filepath3, filepath4));
    }

    @Test
    void plainTest() throws IOException {
        String format = "plain";
        String filepath1 = getFixturePath("innerFile1.json").toString();
        String filepath2 = getFixturePath("innerFile2.json").toString();
        assertEquals(readFixtureFormat(format), Differ.generate(filepath1, filepath2, format));

        String filepath3 = getFixturePath("innerFile1.yaml").toString();
        String filepath4 = getFixturePath("innerFile2.yaml").toString();
        assertEquals(readFixtureFormat(format), Differ.generate(filepath3, filepath4, format));
    }

    @Test
    void jsonTest() throws IOException {
        String format = "json";
        String filepath1 = getFixturePath("innerFile1.json").toString();
        String filepath2 = getFixturePath("innerFile2.json").toString();
        assertEquals(readFixtureFormat(format), Differ.generate(filepath1, filepath2, format));

        String filepath3 = getFixturePath("innerFile1.yaml").toString();
        String filepath4 = getFixturePath("innerFile2.yaml").toString();
        assertEquals(readFixtureFormat(format), Differ.generate(filepath3, filepath4, format));
    }

    @Test
    void defaultTest() throws IOException {
        String filepath1 = getFixturePath("innerFile1.json").toString();
        String filepath2 = getFixturePath("innerFile2.json").toString();
        assertEquals(readFixtureFormat("stylish"), Differ.generate(filepath1, filepath2));

        String filepath3 = getFixturePath("innerFile1.yaml").toString();
        String filepath4 = getFixturePath("innerFile2.yaml").toString();
        assertEquals(readFixtureFormat("stylish"), Differ.generate(filepath3, filepath4));
    }
}
