package dominiczek.aoc2020.day2;

import dominiczek.aoc2020.commons.Commons;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;

public class Solution {

    public static void main(String[] args) throws IOException {
        List<String> rawData = Commons.readLines("input2.txt");

        System.out.println(getNumberOfValidPasswords(rawData));
        System.out.println(getNumberOfValidPasswordsNewPolicy(rawData));
    }

    public static int getNumberOfValidPasswords(List<String> rawData) {
        int result = 0;

        for(String rawLine:rawData) {
            Scanner scanner = new Scanner(rawLine);
            scanner.findInLine("(\\d+)-(\\d+) (\\w): (\\w+)");
            MatchResult matchResult = scanner.match();
            int from = Integer.valueOf(matchResult.group(1));
            int to = Integer.valueOf(matchResult.group(2));
            char character = matchResult.group(3).charAt(0);
            String password = matchResult.group(4);

            int count = 0;
            for(int i=0;i<password.length();i++) {
                if(password.charAt(i)==character) {
                    count++;
                }
            }
            if(count>=from && count<=to) {
                result++;
            }
         }
        return result;
    }

    public static int getNumberOfValidPasswordsNewPolicy(List<String> rawData) {
        int result = 0;

        for(String rawLine:rawData) {
            Scanner scanner = new Scanner(rawLine);
            scanner.findInLine("(\\d+)-(\\d+) (\\w): (\\w+)");
            MatchResult matchResult = scanner.match();
            int firstIndex = Integer.valueOf(matchResult.group(1));
            int secondIndex = Integer.valueOf(matchResult.group(2));
            char character = matchResult.group(3).charAt(0);
            String password = matchResult.group(4);

            if(password.charAt(firstIndex-1) == character ^ password.charAt(secondIndex-1) == character) {
                result++;
            }
        }
        return result;
    }
}
