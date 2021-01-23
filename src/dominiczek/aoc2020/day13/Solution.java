package dominiczek.aoc2020.day13;

import dominiczek.aoc2020.commons.Commons;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static boolean isOk(List<Long> schedulesTime, Long time) {
        for(int j=0;j<schedulesTime.size();j++) {
            Long schedule = schedulesTime.get(j) ;
            if(schedule>=0) {


            }

        }
        return true;
    }

    private static void check(long x)
    {

        System.out.println("0: " + x%37);
        System.out.println("27: " + (x+27)%41);
        System.out.println("37: " +  (x+37)%601);
        System.out.println("49: " + (x+49)%19);
        System.out.println("54: " + (x+54)%17);
        System.out.println("60: " + (x+60)%23);
        System.out.println("66: " + (x+66)%29);
        System.out.println("68: " + (x+68)%443 );
        System.out.println("81: " +  (x+81)%13);
    }


    private static long isOk5a(long x)
    {
        long a = (x+27)%41 + (x+49)%19 +(x+68)%443+(x+81)%13;

        return a;
    }



    public static void main(String[] args) throws IOException {

        List<String> lines = Commons.readLines("input13.txt");
        String scheduleRaw = lines.get(1);
        String[] schedules = scheduleRaw.split(",");
        List<Long> schedulesTime = new ArrayList<>();

        for(int i=0;i<schedules.length;i++) {
            String line = schedules[i];
            if(!line.equals("x")) {
                Long schedule = Long.parseLong(line);
                schedulesTime.add(schedule);
            }
        }

        long res = 0;

        for(long i=1;i<Long.MAX_VALUE;i++) {

            if(isOk(schedulesTime, i)) {

                System.out.println(i);
                break;
            }

        }

        for(long i=1;i<Long.MAX_VALUE;i++) {
            long x = -37 + 252145343 * i;
            if(isOk5a(x) == 0) {
                res = x;
                System.out.println(x);
                break;
            }

        }

        check(res);


    }



}
