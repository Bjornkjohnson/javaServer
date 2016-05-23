package bjohnson.ResponseHandlers;

public class PartialContentRangeParser {
    private final int fileLength;
    private int start;
    private int end;
    private String rawRange;

    public PartialContentRangeParser(String rawRange, int length) {
        this.rawRange = rawRange;
        this.fileLength = length;
        end = length;
        parseRange();
    }

    private void parseRange() {
        String range[] = rawRange.split("=")[1].split("");
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
