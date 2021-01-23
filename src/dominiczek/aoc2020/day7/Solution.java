package dominiczek.aoc2020.day7;

import dominiczek.aoc2020.commons.Commons;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Solution {

    private static Map<String, String> rulesMap = new HashMap<>();


    private static int countBags(String id) {
        String rule = rulesMap.get(id);

        if(rule.equals("no other bags")) {
            return 0;
        } else {
            String[] ids = rule.split("bag,|bags,|bags|bag");

            int result = 0;

            for(String rule_id:ids) {
                Scanner scanner = new Scanner(rule_id);

                int count = scanner.nextInt();
                rule_id = scanner.nextLine().trim();

                result +=count;
                result += count * countBags(rule_id);
            }
            return result;
        }
    }

    private static boolean findShinyGold(String id) {
        String rule = rulesMap.get(id);

        if(rule.contains("shiny gold")) {
            return true;
        }

        if(rule.equals("no other bags")) {
            return false;
        } else {
            String[] ids = rule.split("bag,|bags,|bags|bag");

            for(String rule_id:ids) {
                Scanner scanner = new Scanner(rule_id);

                scanner.nextInt();
                rule_id = scanner.nextLine().trim();
                if(findShinyGold(rule_id)) {
                    return true;
                }
            }
            return false;
        }
    }

    public static void main(String[] args) throws IOException {
        List<String> lines = Commons.readLines("input7.txt");

        for(String line:lines) {
            String id = line.split("bags contain")[0].trim();
            String rule = line.split("bags contain")[1].trim();
            rule = rule.substring(0, rule.length()-1);
            rulesMap.put(id, rule);
        }

        int result = 0;
        for(String ruleId:rulesMap.keySet()) {
            if(findShinyGold(ruleId)) {
                result++;
            }
        }
        System.out.println(result);
        System.out.println(countBags("shiny gold"));
    }

}
