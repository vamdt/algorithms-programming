import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int N;
    private Node first;
    private Node last;

    private class Node {
        private Item item;
        private Node prev;
        private Node next;

        public void reset() {
            this.item = null;
            this.prev = null;
            this.next = null;
        }
        @Override
        public String toString() {
            return "Node{" + "item=" + item + '}';
        }
    }

    public Deque() {
        first = null;
        last = null;
        N = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return N == 0;
    }

    // return the number of items on the deque
    public int size() {
       return N;
    }

    // insert the item at the front
    public void addFirst(Item item) {
        if (item == null) throw new NullPointerException();
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        if (!isEmpty()) {
            first.next = oldFirst;
            oldFirst.prev = first;
        } else {
            last = first;
        }
        N++;
    }
    // insert the item at the end
    public void addLast(Item item) {
        if (item == null) throw new NullPointerException();
        Node oldLast = last;
        last = new Node();
        last.item = item;
        if (!isEmpty()) {
            last.prev = oldLast;
            oldLast.next = last;
        } else {
           first = last;
        }
        N++;
    }
    // delete and return the item at the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = first.item;
        Node next = first.next;
        first.reset();
        first = next;
        if (first != null) {
            first.prev = null;
        } else {
            last = null;
        }
        N--;
        return item;
    }

    // delete and return the item at the end
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = last.item;
        Node prev = last.prev;
        last.reset();
        last = prev;
        if (last != null) {
            last.next = null;
        } else {
            first = null;
        }
        N--;
        return item;
    }

    // return an iterator over items in order from front to end
    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private Node current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
            @Override
            public Item next() {
                if (!hasNext()) throw new NoSuchElementException();
                Item item = current.item;
                current = current.next;
                return item;
            }
        };
    }

    // unit testing
    public static void main(String[] args) {
        Deque<Integer> d = new Deque<Integer>();
        d.addFirst(1);
        d.addFirst(9);
        d.addLast(12);
        d.addLast(15);
        d.addLast(15);
        d.addLast(15);
        d.addLast(15);
        d.addLast(15);
        d.addLast(15);
        d.removeFirst();
        d.removeLast();
        d.removeLast();
        d.removeFirst();
    }

}
