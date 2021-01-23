package dominiczek.aoc2020.day23;

import java.util.HashMap;
import java.util.Map;

class Entry <T> {
    T value;
    Entry<T> next;
}

public class CircleLinkedList<T> {

    Entry<T> head;
    Entry<T> tail;

    Map<T, Entry<T>> map = new HashMap<>();

    public CircleLinkedList() {
        this.head = null;
        this.tail = null;
    }

    public CircleLinkedListIterator<T> iterator() {
        return new CircleLinkedListIterator<>(this.head);
    }

    public void addLast(T value) {
        Entry<T> newEntry = new Entry<>();

        map.put(value, newEntry);

        newEntry.value = value;


        if(this.head == null) {
            this.head = newEntry;
            this.tail = newEntry;
        } else {
            this.tail.next = newEntry;
            this.tail = newEntry;
        }

        newEntry.next = this.head;
    }

    public void addAfter(T afterValue, T value) {
        Entry<T> newEntry = new Entry<>();
        newEntry.value= value;

        map.put(value, newEntry);

        Entry<T> after = map.get(afterValue);
        Entry<T> tmpAfterNext = after.next;
        after.next = newEntry;
        newEntry.next = tmpAfterNext;
    }

    public T removeAfter(T afterValue) {

        Entry<T> after = map.get(afterValue);
        Entry<T> toRemove = after.next;
        after.next = toRemove.next;
        map.remove(toRemove.value);

        if(toRemove==this.head) {
            this.head = toRemove.next;
        }

        return toRemove.value;
    }

    public T getValueAfter(T value) {
        Entry<T> entry = map.get(value);
        return entry.next.value;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        if(this.head != null) {
            builder.append(this.head.value);
            Entry<T> current = this.head.next;
            while(current != this.head) {
                builder.append(", ");
                builder.append(current.value);
                current = current.next;
            }
        }
        builder.append("]");
        return builder.toString();


    }

    public static void main(String[] args) {
        CircleLinkedList<Integer> list = new CircleLinkedList<>();
        System.out.println(list.toString());
        list.addLast(12);
        System.out.println(list.toString());
        list.addLast(10);
        System.out.println(list.toString());
        list.addLast(11);
        System.out.println(list.toString());
        list.addLast(13);
        System.out.println(list.toString());
        list.addLast(14);
        System.out.println(list.toString());

        int rem = list.removeAfter(10);
        System.out.println(list.toString());
        list.addAfter(14, rem);
        System.out.println(list.toString());
        int rem2 = list.removeAfter(11);
        System.out.println(list.toString());
    }

}
