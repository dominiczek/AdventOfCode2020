package dominiczek.aoc2020.day1;


import dominiczek.aoc2020.commons.Commons;

import java.io.IOException;
import java.util.List;

public class Solution {

    public static long findTwoNumbersSumTo2020(List<Long> numbers) {
        for(int i=0;i<numbers.size();i++) {
            long number1 = numbers.get(i);
            for(int j=i;j<numbers.size();j++) {
                long number2 = numbers.get(j);
                if(number1+number2 == 2020) {
                    return number1*number2;
                }
            }
        }
        throw new IllegalArgumentException("Not found");
    }

    public static long findThreeNumbersSumTo2020(List<Long> numbers) {
        for(int i=0;i<numbers.size();i++) {
            long number1 = numbers.get(i);
            for(int j=i;j<numbers.size();j++) {
                long number2 = numbers.get(j);
                for(int k=j;k<numbers.size();k++) {
                    long number3 = numbers.get(k);
                    if(number1+number2+number3 == 2020) {
                        return number1*number2*number3;
                    }
                }
            }
        }
        throw new IllegalArgumentException("Not found");
    }

    public static void main(String[] args) throws IOException {
        List<Long> numbers=  Commons.readLinesAsNumbers("input1.txt");

        System.out.println(findTwoNumbersSumTo2020(numbers));
        System.out.println(findThreeNumbersSumTo2020(numbers));
    }
}
