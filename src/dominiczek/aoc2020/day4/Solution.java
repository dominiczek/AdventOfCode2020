package dominiczek.aoc2020.day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//1689
public class Solution {

    public static boolean basicValidatePassport(String passport) {
        String[] split = passport.split("\\s+");

        Map<String, String> data = Arrays.stream(split).collect(Collectors.toMap(s -> (s.split(":"))[0], o -> (o.split(":"))[1]));

        return data.keySet().containsAll(Arrays.asList("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"));
    }

    public static boolean validatePassport(String passport) {
        String[] split = passport.split("\\s+");

        Map<String, String> data = Arrays.stream(split).collect(Collectors.toMap(s -> (s.split(":"))[0], o -> (o.split(":"))[1]));

        if(!data.keySet().containsAll(Arrays.asList("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"))) {
            return false;
        }

        Long byr = Long.valueOf(data.get("byr"));
        if(byr<1920 || byr>2002) {
            return false;
        }

        Long iyr = Long.valueOf(data.get("iyr"));
        if(iyr<2010 || iyr>2020) {
            return false;
        }

        Long eyr = Long.valueOf(data.get("eyr"));
        if(eyr<2020 || eyr>2030) {
            return false;
        }

        String hgt = data.get("hgt");
        if(hgt.endsWith("cm")) {
            int height = Integer.valueOf(hgt.substring(0, hgt.length()-2));

            if(height>193 || height<150) {
                return false;
            }
        } else if(hgt.endsWith("in")) {
            int height = Integer.valueOf(hgt.substring(0, hgt.length()-2));
            if(height>76 || height<59) {
                return false;
            }
        } else {
            return false;
        }

        String hcl = data.get("hcl");

        if(hcl.startsWith("#") && hcl.length()==7) {
            String colorCode = hcl.substring(1);

            colorCode = colorCode.replaceAll("[0-9]+", "").replaceAll("[a-f]+", "");

            if(colorCode.length()>0) {
                return false;
            }
        } else {
            return false;
        }

        String ecl = data.get("ecl");

        if(!Arrays.asList("amb","blu","brn","gry","grn","hzl","oth").contains(ecl)) {
            return false;
        }

        String pid = data.get("pid");
        String pid2 = pid.replaceAll("[0-9]+", "");

        if(pid.length()!=9 || pid2.length()>0) {
            return false;
        }


        return true;
    }

    public static void main(String[] args) throws IOException {

        List<String> rawData = Files.readAllLines(Paths.get("input4.txt"));
        rawData.add("");

        List<String> passports = new ArrayList<>();

        String currentPassport = "";


        for(String line:rawData) {
            if(line.equals("")) {

                passports.add(currentPassport);
                currentPassport = "";
            } else {
                currentPassport += (line + " ");
            }
        }
        System.out.println(basicValidateAllPassports(passports));
        System.out.println(validateAllPassports(passports));

    }

    public static int validateAllPassports(List<String> passports) {
        int result = 0;
        for(String passport:passports) {
            if(validatePassport(passport)) {
                result++;
            }
        }
        return result;
    }

    public static int basicValidateAllPassports(List<String> passports) {
        int result = 0;
        for(String passport:passports) {
            if(basicValidatePassport(passport)) {
                result++;
            }
        }
        return result;
    }
}
