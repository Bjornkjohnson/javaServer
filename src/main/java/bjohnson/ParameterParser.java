package bjohnson;

import java.util.HashMap;
import java.util.Map;

public class ParameterParser {
    private HashMap<String, String> decoder;

    ParameterParser(HashMap<String, String> decoder) {
        this.decoder = decoder;
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
