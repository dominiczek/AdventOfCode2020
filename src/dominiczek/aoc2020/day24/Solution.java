package dominiczek.aoc2020.day24;

import dominiczek.aoc2020.commons.Commons;

import java.io.IOException;
import java.util.*;


public class Solution {

    public static class Tile {

        private int ne = 0;
        private int nw = 0;

        public Tile(int ne, int nw) {
            this.ne = ne;
            this.nw = nw;
        }

        public Set<Tile> getNeighbours() {
            Set<Tile> result = new HashSet<>();

            result.add(new Tile(ne+1, nw));
            result.add(new Tile(ne-1, nw));
            result.add(new Tile(ne, nw+1));
            result.add(new Tile(ne, nw-1));
            result.add(new Tile(ne+1, nw-1));
            result.add(new Tile(ne-1, nw+1));

            return result;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Tile tile = (Tile) o;
            return ne == tile.ne &&
                    nw == tile.nw;
        }

        @Override
        public int hashCode() {
            return Objects.hash(ne, nw);
        }
    }


    private static List<Tile> readData(List<String> rawData) {
        List<Tile> allTiles = new ArrayList<>();

        for(String data:rawData) {
            data = data + " ";
            int ne = 0;
            int nw = 0;
            for (int i = 0; i < data.length() - 1; i++) {

                char c = data.charAt(i);
                char c2 = data.charAt(i + 1);
                if (c == 'e') {
                    ne++;
                    nw--;
                } else if (c == 'w') {
                    nw++;
                    ne--;
                } else if (c == 'n') {
                    if (c2 == 'e') {
                        ne++;
                        i++;
                    } else if (c2 == 'w') {
                        nw++;
                        i++;
                    }
                } else if (c == 's') {
                    if (c2 == 'e') {
                        nw--;
                        i++;
                    } else if (c2 == 'w') {
                        ne--;
                        i++;
                    }
                }
            }
            Tile tile = new Tile(ne, nw);
            allTiles.add(tile);
        }
        return allTiles;
    }


    public static void main(String[] args) throws IOException {
        List<String> rawData = Commons.readLines("input24.txt");

        List<Tile> allTiles = readData(rawData);

        HashSet<Tile> flippedTiles = new HashSet<>();
        for(Tile tile:allTiles) {

            if(flippedTiles.contains(tile)) {
                flippedTiles.remove(tile);
            } else {
                flippedTiles.add(tile);
            }
        }
        System.out.println(flippedTiles.size());

        HashSet<Tile> uniqueTiles = new HashSet<>(allTiles);

        for(int i =0;i<100;i++) {

            Set<Tile> newTiles = new HashSet<>();
            for (Tile tile : uniqueTiles) {
                newTiles.addAll(tile.getNeighbours());
            }
            uniqueTiles.addAll(newTiles);

            Set<Tile> toBeBlack = new HashSet<>();
            Set<Tile> toBeWhite = new HashSet<>();
            for (Tile tile : uniqueTiles) {
                Set<Tile> neighs = tile.getNeighbours();

                int blackNeighbours = 0;
                for (Tile neigh : neighs) {
                    if (flippedTiles.contains(neigh)) {
                        blackNeighbours++;
                    }
                }

                if (flippedTiles.contains(tile)) {
                    if (blackNeighbours == 0 || blackNeighbours > 2) {
                        toBeWhite.add(tile);
                    }
                } else {
                    if (blackNeighbours == 2) {
                        toBeBlack.add(tile);
                    }
                }

            }
            flippedTiles.removeAll(toBeWhite);
            flippedTiles.addAll(toBeBlack);
        }
        System.out.println(flippedTiles.size());
    }
}
