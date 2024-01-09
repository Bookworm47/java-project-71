import hexlet.code.Differ;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

public class TestDiffer {

    @Test
    void differTest() throws IOException {
        String resultJson = "{\n"
                + " - follow: false\n"
                + "   host: hexlet.io\n"
                + " - proxy: 123.234.53.22\n"
                + " - timeout: 50\n"
                + " + timeout: 20\n"
                + " + verbose: true\n"
                + "}";
        assertEquals(resultJson, Differ.generate("file1.json", "file2.json"));
    }
}
