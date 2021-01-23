package dominiczek.aoc2020.day9;

import dominiczek.aoc2020.commons.Commons;

import java.io.IOException;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Solution {

    public static List<Long> listInt;
    public static int X = 25;


    private static boolean isSumOfLastX(int numberIndex) {
        Long number = listInt.get(numberIndex);
        for(int i=1;i<=X;i++) {
            for(int j=1;j<=X;j++) {
                if(listInt.get(numberIndex-i)+listInt.get(numberIndex-j) == number) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void findSet(int startIndex, Long number) {
        TreeSet<Long> result = new TreeSet<>();
        Long sum = listInt.get(startIndex);
        result.add(listInt.get(startIndex));
        for(int i=startIndex+1;i<listInt.size();i++) {

            Long res = listInt.get(i) + sum;
            if(res.equals(number)) {
                System.out.println(result.first()+result.last());
                break;
            } else if(listInt.get(i) + sum < number) {
                sum+=listInt.get(i);
                result.add(listInt.get(i));
                if(sum.equals(number)) {
                    System.out.println(result.first()+result.last());
                }
            } else {
                break;
            }
        }

    }

    public static void main(String[] args) throws IOException {

        List<String> lines = Commons.readLines("input9.txt");

        listInt = lines.stream().map(s -> Long.valueOf(s)).collect(Collectors.toList());

        Long number=0L;

        for(int i=0;i<listInt.size();i++) {
            number = listInt.get(i);

            if(i>=X+1) {
                if(!isSumOfLastX(i)) {
                    System.out.println(number);
                    break;
                }
            }
        }

        for(int i=0;i<listInt.size();i++) {
            findSet(i, number);
        }


    }




}
