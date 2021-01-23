package dominiczek.aoc2020.day10;

import dominiczek.aoc2020.commons.Commons;

import java.io.IOException;
import java.math.BigInteger;
import java.util.*;

public class Solution {


    private static BigInteger power(int node, List<List<Integer>> map, Map<Integer, BigInteger> powers) {
        List<Integer> connections = map.get(node);

        if(powers.containsKey(node)) {
            return powers.get(node);
        }

        if(connections.size()==1) {
            BigInteger p = power(connections.get(0), map, powers);
            powers.put(node, p);
            return p;
        }
        if(connections.size()==0) {
            return new BigInteger("1");
        }


        BigInteger result = new BigInteger("0");
        for(Integer con:connections) {
            result = result.add( power(con, map, powers));
        }
        powers.put(node, result);
        return result;

    }

    private static BigInteger count(List<Long> lines) {

        List<List<Integer>> maps = new ArrayList<>();

        for(int i=0;i<lines.size();i++) {

            Long adapter = lines.get(i);
            ArrayList<Integer> list = new ArrayList<>();
            maps.add(list);
            for(int j=1;j<=3 && i+j<lines.size();j++) {
                Long next = lines.get(i + j);
                if(next - adapter <= 3) {
                    list.add(i+j);
                }
            }
        }

        return power(0, maps, new HashMap<>());

    }

    public static void main(String[] args) throws IOException {

        List<Long> lines = Commons.readLinesAsNumbers("input10.txt");

        lines.sort(Comparator.naturalOrder());

        int currentVoltage = 0;
        int oneDiff = 0;
        int threeDiff = 0;

        for(int i=0;i<lines.size();i++) {

            Long adapter = lines.get(i);

            long diff = adapter-currentVoltage;

            currentVoltage+=diff;

            if(diff== 1) {
                oneDiff++;
            } else if(diff==2) {
            } else if(diff==3) {
                threeDiff++;
            }
        }

        threeDiff++;

        System.out.println(oneDiff*threeDiff);
        System.out.println(count(lines));
    }
}
