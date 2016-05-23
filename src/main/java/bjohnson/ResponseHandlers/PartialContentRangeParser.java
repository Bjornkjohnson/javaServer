package bjohnson.ResponseHandlers;

import bjohnson.Request;

public class PartialContentRangeParser {
    private final Request request;
    private final int fileLength;
    private int start;
    private int end;

    public PartialContentRangeParser(Request request, int length) {
        this.request = request;
        this.fileLength = length;
        end = length - 1;
        parseRange();
    }

    private void parseRange() {
        String rangeString = request.getHeaders().get("Range");
        String range[] = rangeString.split("=")[1].split("");
        if (range.length == 3) {
            start = Integer.parseInt(range[0]);
            end = Integer.parseInt(range[2]) + 1;
        } else if (range[0].equals("-")) {
            start = end - Integer.parseInt(range[1]);
        } else if (range[1].equals("-")) {
            start = Integer.parseInt(range[0]);

        }

    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}
