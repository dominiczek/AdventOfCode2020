package dominiczek.aoc2020.day23;


public class CircleLinkedListIterator<T> {

    Entry<T> entry;


    public CircleLinkedListIterator(Entry<T> start) {
        this.entry = start;
    }

    public boolean hasNext() {
        return entry.next != null;
    }

    public T next() {
        T tmp = entry.value;
        entry = entry.next;
        return tmp;
    }

    public T get() {
        return entry.value;
    }

}
