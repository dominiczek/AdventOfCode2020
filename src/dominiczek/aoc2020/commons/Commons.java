package dominiczek.aoc2020.commons;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Commons {
    public static List<String> readLines(String fileName) throws IOException {
        return Files.readAllLines(Paths.get(fileName));
    }

    public static List<Long> readLinesAsNumbers(String fileName) throws IOException {
        return Files.readAllLines(Paths.get(fileName)).stream().map(s -> Long.valueOf(s)).collect(Collectors.toList());
    }

    public static List<Line> readLinesWithSeparator(String fileName) throws IOException {
        List<String> rawLines = Files.readAllLines(Paths.get(fileName));
        rawLines.add("");

        Line current = new Line();

        List<Line> lines = new ArrayList<>();

        for(String line:rawLines) {
            if (line.equals("")) {
                lines.add(current);
                current = new Line();
            } else {
                current.append(line);
            }
        }

        return lines;
    }
}
