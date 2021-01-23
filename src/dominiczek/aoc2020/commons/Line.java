package dominiczek.aoc2020.commons;

import java.util.ArrayList;
import java.util.List;

public class Line {
    private String line ="";
    private List<String> lines = new ArrayList<>();

    public Line() {

    }

    public void append(String newLine) {
        line+=newLine;
        lines.add(newLine);
    }

    public String getLine() {
        return line;
    }

    public List<String> getLines() {
        return lines;
    }

    public int getStringLength() {
        return line.length();
    }

    public int getNumberOfElements() {
        return lines.size();
    }
}
