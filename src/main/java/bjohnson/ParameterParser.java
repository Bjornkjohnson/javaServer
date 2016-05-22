package bjohnson;

import java.util.HashMap;
import java.util.Map;

public class ParameterParser {
    private HashMap<String, String> decoder;

    ParameterParser() {
        decoder = new HashMap<>();
        decoder.put("\\%20", " ");
        decoder.put("\\%2C", ",");
        decoder.put("\\%3C", "<");
        decoder.put("\\%3E", ">");
        decoder.put("\\%3D", "=");
        decoder.put("\\%3B", ";");
        decoder.put("\\%2B", "+");
        decoder.put("\\%26", "&");
        decoder.put("\\%40", "@");
        decoder.put("\\%23", "#");
        decoder.put("\\%24", "\\$");
        decoder.put("\\%5B", "[");
        decoder.put("\\%5D", "]");
        decoder.put("\\%3A", ":");
        decoder.put("\\%22", "\"");
        decoder.put("\\%3F", "?");
    }


    public String[] parseParameters(String rawRequest) {
        String rawParams = removeRoute(rawRequest);
        String splitParams[] = splitParams(rawParams);

        for (int i = 0; i < splitParams.length; i++){
            splitParams[i] = decodeParams(splitParams[i]);
        }

        return splitParams;

    }

    private String removeRoute(String rawRequest) {
        return rawRequest.split("\\?")[1];
    }

    private String decodeParams(String param) {
        for (Map.Entry<String,String> entry : decoder.entrySet()) {
            param = param.replaceAll(entry.getKey(), entry.getValue());
        }

        return param;
    }

    private String[] splitParams(String rawParams) {
        return rawParams.split("&");

    }
}
