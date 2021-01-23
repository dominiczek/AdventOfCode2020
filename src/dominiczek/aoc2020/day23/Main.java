package dominiczek.aoc2020.day23;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;


public class Main {

    static CircleLinkedList<Integer> circle = new CircleLinkedList<>();

    public static void main(String[] args) throws IOException {

        long result = 0;

        int max = 1000000;
        int min = 1;
//        int currentIndex = 0;



        List<String> rawData = Files.readAllLines(Paths.get("input23.txt"));
        for(int i=0;i<rawData.get(0).length();i++) {
            int elem = Integer.parseInt(""+rawData.get(0).charAt(i));
            circle.addLast(elem);
        }
        System.out.println(circle);
        for(int i=9;i<max;i++) {
            circle.addLast(i+1);
        }
//        System.out.println(circle);

        CircleLinkedListIterator<Integer> current = circle.iterator();

//        int currentCupLabel =0;
        int destinationCup = 0;

        LinkedList<Integer> picked = new LinkedList<>();

        for(int i=0;i<10000000;i++) {

            picked.clear();

            int currentValue = current.get();
            System.out.println((i+1)+"-------------");
//            System.out.println("CUPS: " + circle);
//            System.out.println("CURRENT: " + currentValue);

            picked.addLast(circle.removeAfter(currentValue));
            picked.addLast(circle.removeAfter(currentValue));
            picked.addLast(circle.removeAfter(currentValue));
//            System.out.println("CUPS1: " + circle);

            destinationCup = currentValue-1;
            if (destinationCup<min) {
                destinationCup = max;
            }
            while (picked.contains(destinationCup)) {
                destinationCup--;
                if (destinationCup<min) {
                    destinationCup = max;
                }
            }

//            System.out.println("DEST: " + destinationCup);

            circle.addAfter(destinationCup,picked.get(0));
            circle.addAfter(picked.get(0),picked.get(1));
            circle.addAfter(picked.get(1),picked.get(2));

//            System.out.println("CUPS2: " + circle);

            current.next();

        }


        int v1 = circle.getValueAfter(1);
        int v2 = circle.getValueAfter(v1);

        System.out.println(v1);
        System.out.println(v2);

//        System.out.println(circle);



        System.out.println(result);

    }

}
