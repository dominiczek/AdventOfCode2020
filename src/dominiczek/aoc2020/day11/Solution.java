package dominiczek.aoc2020.day11;

import dominiczek.aoc2020.commons.Commons;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Solution {

    public static void main(String[] args) throws IOException {
        List<String> lines = Commons.readLines("input11.txt");
        Character[][] array = readArray(lines);
        part1(array);
        part2(array);
    }

    private static Character[][] readArray(List<String> lines) {
        Character[][] array = new Character[lines.size()+2][lines.get(0).length()+2];

        Arrays.fill(array[0], '.');
        Arrays.fill(array[lines.size()+1], '.');
        for(int i=0;i<lines.size();i++) {
            String line = lines.get(i);
            Arrays.fill(array[i+1], '.');

            for(int j=0;j<line.length();j++) {
                Character c = line.charAt(j);

                array[i+1][j+1] = c;
            }
        }
        return array;
    }

    private static void part1(Character[][] array) {
        Seats seats = new Seats(array);
        while(true) {
            Seats newSeats = seats.move1();
            if(seats.isEqual(newSeats)) {
                break;
            }
            seats = newSeats;
        }
        System.out.println(seats.countOccupied());
    }

    private static void part2(Character[][] array) {
        Seats seats = new Seats(array);
        while(true) {
           Seats newSeats = seats.move2();
           if(seats.isEqual(newSeats)) {
               break;
           }
            seats = newSeats;
        }
        System.out.println(seats.countOccupied());
    }


}
