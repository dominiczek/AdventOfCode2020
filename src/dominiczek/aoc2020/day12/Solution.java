package dominiczek.aoc2020.day12;


import dominiczek.aoc2020.commons.Commons;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Solution {

    private final static int DIRECTION_E = 0;
    private final static int DIRECTION_N = 1;
    private final static int DIRECTION_W = 2;
    private final static int DIRECTION_S = 3;

    public static void part1(List<String> lines)  {

        int x = 0;
        int y = 0;

        int currentDir = DIRECTION_E;

        for(String line:lines) {
            Character command = line.charAt(0);
            Integer value = Integer.valueOf(line.substring(1));

            if(command.equals('E')) {
                x+=value;
            } else if(command.equals('W')) {
                x-=value;
            } else if(command.equals('S')) {
                y+=value;
            } else if(command.equals('N')) {
                y-=value;
            } else if(command.equals('F')) {
                if(currentDir == DIRECTION_E) {
                    x+=value;
                } else if(currentDir == DIRECTION_W) {
                    x-=value;
                } else if(currentDir == DIRECTION_S) {
                    y+=value;
                } else if( currentDir == DIRECTION_N) {
                    y-=value;
                }
            } else if(command.equals('L')) {
                if(value==90) {
                    currentDir = (currentDir+1)%4;
                } else if(value==180) {
                    currentDir = (currentDir+2)%4;
                } else if(value==270) {
                    currentDir = (currentDir+3)%4;
                }
            } else if(command.equals('R')) {
                if(value==90) {
                    currentDir = (currentDir+3)%4;
                } else if(value==180) {
                    currentDir = (currentDir+2)%4;
                } else if(value==270) {
                    currentDir = (currentDir+1)%4;
                }
            }
            System.out.println(currentDir);
        }

        System.out.println(Math.abs(x)+Math.abs(y));
    }

    public static void part2(List<String> lines)  {

        int x = 10;
        int y = 1;

        int shipX = 0;
        int shipY =0;


        for(String line:lines) {
            Character command = line.charAt(0);
            Integer value = Integer.valueOf(line.substring(1));

            if(command.equals('E')) {
                x+=value;
            } else if(command.equals('W')) {
                x-=value;
            } if(command.equals('S')) {
                y-=value;
            } if(command.equals('N')) {
                y+=value;
            } if(command.equals('F')) {
                shipX +=(x*value);
                shipY +=(y*value);
            } if(command.equals('L')) {
                int oldY=y;
                if(value==90) {
                    y=x;
                    x = -oldY;
                } if(value==180) {
                    y=-y;
                    x = -x;
                }  if(value==270) {
                    y=-x;
                    x = oldY;
                }
            } if(command.equals('R')) {
                int oldY=y;
                if(value==270) {
                    y=x;
                    x = -oldY;
                } if(value==180) {
                    y=-y;
                    x = -x;
                }  if(value==90) {
                    y=-x;
                    x = oldY;
                }
            }
        }
        System.out.println(Math.abs(shipX)+Math.abs(shipY));
    }


    public static void main(String[] args) throws IOException {

        List<String> lines = Commons.readLines("input12.txt");

        part1(lines);
        part2(lines);

    }

}
