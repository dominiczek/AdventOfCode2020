package dominiczek.aoc2020.commons;

import java.util.Iterator;

public class CharacterIterator implements Iterator<Character>, Iterable<Character> {

    private final String str;
    private int pos = 0;

    public static CharacterIterator of(String str) {
        return new CharacterIterator(str);
    }

    public CharacterIterator(String str) {
        this.str = str;
    }



    public boolean hasNext() {
        return pos < str.length();
    }

    public Character next() {
        return str.charAt(pos++);
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<Character> iterator() {
        return this;
    }
}