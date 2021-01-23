package dominiczek.aoc2020.day8;

import dominiczek.aoc2020.commons.Commons;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {

    private static int accumulator = 0;

    private static boolean runProgram(List<String> lines, int lineChange) {

        accumulator = 0;

        Set<Integer> instrCounter = new HashSet<>();

        for(int i=0;i<lines.size();) {
            String line = lines.get(i);

            if(instrCounter.contains(i)) {
                return false;
            }
            instrCounter.add(i);

            Integer increment = Integer.valueOf( line.substring(4));
            if(line.startsWith("acc")) {
                accumulator += increment;
                i++;
            } else if (line.startsWith("jmp")) {
                if(i==lineChange) {
                    i++;
                } else {
                    i+=increment;
                }
            } else if (line.startsWith("nop")) {
                if(i==lineChange) {
                    accumulator += increment;
                    i++;
                } else {
                    i++;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {

        List<String> lines = Commons.readLines("input8.txt");

        runProgram(lines, -1);
        System.out.println(accumulator);

        for(int i=0;i<lines.size();i++) {
            if(runProgram(lines, i)) {
                break;
            }
        }
        System.out.println(accumulator);
    }
}
