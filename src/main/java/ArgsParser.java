import java.util.Arrays;

import static java.lang.Integer.parseInt;

public class ArgsParser {
    private static String[] rawArgs;

    ArgsParser(String[] rawArgs) {
        this.rawArgs = rawArgs.clone();
    }

    private boolean containsFlag(String flag){
        return Arrays.asList(rawArgs).contains(flag);
    }

    private String extractParam(String flag) {
        int index = Arrays.asList(rawArgs).indexOf(flag);
        return rawArgs[index + 1];
    }

    public int getPort() {
        String port = "5000";
        if (containsFlag("-p")){
            port = extractParam("-p");
        }

        return parseInt(port);
    }

    public String getDirectory() {
        String directory = "PUBLIC_DIR";
        if (containsFlag("-d")){
            directory = extractParam("-d");
        }

        return directory;
    }

}
