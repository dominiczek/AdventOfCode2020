package dominiczek.aoc2020.day22;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Solution {

    public static long calculateScore(List<Integer> deck) {
        long result =0;

        for(int i=0;i<deck.size();i++) {
            result+=deck.get(i)*(deck.size()-i);
        }

        return result;
    }

    public static boolean game(List<Integer> player1Deck, List<Integer> player2Deck) {
        HashSet<String> playedCards = new HashSet<>();

        while(true) {
            if(player1Deck.isEmpty()) {
                return false;
            } else if(player2Deck.isEmpty()) {
                return true;
            }

            int card1 = player1Deck.remove(0);
            int card2 = player2Deck.remove(0);


            if(card1>card2) {
                player1Deck.add(card1);
                player1Deck.add(card2);
            } else if(card2>card1) {
                player2Deck.add(card2);
                player2Deck.add(card1);
            }

        }
    }

    public static boolean gameRec(List<Integer> player1Deck, List<Integer> player2Deck, int level) {
        HashSet<String> playedCards = new HashSet<>();

        while(true) {
            if(player1Deck.isEmpty()) {
                return false;
            } else if(player2Deck.isEmpty()) {
                return true;
            }

            String state = player1Deck.toString()+"_"+player2Deck.toString();
            if(playedCards.contains(state)) {
                return true;
            } else {
                playedCards.add(state);
            }

            int card1 = player1Deck.remove(0);
            int card2 = player2Deck.remove(0);

            if(card1 <= player1Deck.size() && card2<=player2Deck.size()) {

                if(gameRec(new ArrayList<>(player1Deck.subList(0, card1)), new ArrayList<>(player2Deck.subList(0, card2)), level+1)) {
                    player1Deck.add(card1);
                    player1Deck.add(card2);
                } else {
                    player2Deck.add(card2);
                    player2Deck.add(card1);
                }

            } else {
                if(card1>card2) {
                    player1Deck.add(card1);
                    player1Deck.add(card2);
                } else if(card2>card1) {
                    player2Deck.add(card2);
                    player2Deck.add(card1);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {

        List<Integer> player1Deck = new ArrayList<>();
        List<Integer> player2Deck = new ArrayList<>();

        readData(player1Deck, player2Deck);

        game(player1Deck, player2Deck);
        System.out.println(calculateScore(player1Deck));

        player1Deck.clear();
        player2Deck.clear();
        readData(player1Deck, player2Deck);

        if(gameRec(player1Deck, player2Deck, 1)) {
            System.out.println(calculateScore(player1Deck));
        } else {
            System.out.println(calculateScore(player2Deck));
        }
    }

    private static void readData(List<Integer> player1Deck, List<Integer> player2Deck) throws IOException {
        List<String> rawData = Files.readAllLines(Paths.get("input22.txt"));
        int i=1;
        for(;i<rawData.size();i++) {
            if(rawData.get(i).contains("Player")) {
                break;
            }
            player1Deck.add(Integer.parseInt(rawData.get(i)));
        }
        i++;
        for(;i<rawData.size();i++) {
            if(rawData.get(i).contains("Player")) {
                break;
            }
            player2Deck.add(Integer.parseInt(rawData.get(i)));
        }
    }

}
