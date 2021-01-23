package dominiczek.aoc2020.day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Solution {

    private static int row(String row) {
        int result = 0;
        for(int i=0;i<7;i++) {
            Character c = row.charAt(i);

            if (c.equals('B')) {
                result += Math.pow(2,(6-i));
            }
        }

        return result;
    }

    private static int seat(String seat) {
        int result = 0;
        for(int i=0;i<3;i++) {
            Character c = seat.charAt(i);

            if (c.equals('R')) {
                result += Math.pow(2,(2-i));
            }
        }

        return result;
    }

    private static int getSeatNo(String pas) {
        return row(pas.substring(0,7)) * 8 + seat(pas.substring(7));
    }

    public static void main(String[] args) throws IOException {

        TreeSet<Integer> presentIds = new TreeSet<>();

        List<String> rawData = Files.readAllLines(Paths.get("input5.txt"));

        for(String pas:rawData) {
            presentIds.add(getSeatNo(pas));
        }

        System.out.println(presentIds.last());

        TreeSet<Integer> allIds = new TreeSet<>();
        for(int i=0;i<=presentIds.last();i++) {
            allIds.add(i);
        }
        allIds.removeAll(presentIds);

        System.out.println(allIds.last());
    }

}
