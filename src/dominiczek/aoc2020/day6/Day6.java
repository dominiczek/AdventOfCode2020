package dominiczek.aoc2020.day6;

import dominiczek.aoc2020.commons.CharacterIterator;
import dominiczek.aoc2020.commons.Commons;
import dominiczek.aoc2020.commons.Line;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day6 {


    public static void main(String[] args) throws IOException {


        int result = 0;

        List<Line> lines = Commons.readLinesWithSeparator("input6.txt");

        for(Line line:lines) {

            System.out.println(line);
            Map<Character, Integer> chars = new HashMap<>();

            for(Character c: CharacterIterator.of(line.getLine())) {
                Integer noP = chars.get(c);
                if(noP==null) {
                    noP = 1;
                } else {
                    noP++;
                }
                chars.put(c, noP);
            }


            for(Character c:chars.keySet()) {
                if(chars.get(c) == line.getLines().size()) {
                    result ++;
                }
            }
        }

        System.out.println(result);
    }
}
