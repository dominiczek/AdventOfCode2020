package dominiczek.aoc2020.day25;

public class Solution {


    private static long transform(long target) {
        long value = 1;
        int loopSize=0;
        int subject = 7;
        while(value != target) {
            value *= subject;
            value = value % 20201227;
            loopSize++;
        }
        return loopSize;
    }

    private static long transform(long loopSize, long subject) {
        long value = 1;
        for(int i=0;i<loopSize;i++) {
            value *= subject;
            value = value % 20201227;
        }
        return value;
    }

    public static void main(String[] args) {
        long loopSize1 = transform(12578151L);
        System.out.println(loopSize1);
        long loopSize2 = transform(5051300L);
        System.out.println(loopSize2);

        System.out.println(transform(loopSize2, 12578151L));
        System.out.println(transform(loopSize1, 5051300L));
    }
}
