package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;



@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "checksum 4.0",
        description = "Compares two configuration files and shows a difference.")

public class App implements Callable {

    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
//            defaultValue = "stylish")
    private String format;

    @Parameters(paramLabel = "filepath1", defaultValue = "Hello, picocli",
            description = "path to first file")
    private String filepath1;

    @Parameters(paramLabel = "filepath2", defaultValue = "Hello, picocli",
            description = "path to second file")
    private String filepath2;


    @Override
    public Integer call() throws Exception {
        String result = Differ.generate(filepath1, filepath2);
        System.out.println(result);
        return 0;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}

