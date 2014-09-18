import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] q;
    private int N = 0;
    private int first = 0;

    public RandomizedQueue() {
        q = (Item[]) new Object[2];
    }
    public boolean isEmpty() {
        return N == 0;
    }
    public int size() {
        return N;
    }
    // add the item
    public void enqueue(Item item) {
        if (N == q.length) resize(q.length*2);
        q[first++] = item;
        N++;
    }

    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++) {
            temp[i] = q[i];
        }
        q = temp;
    }

    // delete and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        int index = StdRandom.uniform(N);
        Item item = q[index];
        N--;
        q[index] = q[N];
        q[N] = null;
        if (N > 0 && 4*N < q.length) resize(q.length / 2);           // wrap-around
        return item;
    }
    // return (but do not delete) a random item
    public Item sample() {
        return q[StdRandom.uniform(N)];
    }
    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }


    private class RandomIterator implements Iterator<Item> {
        private int[] idx = new int[N];
        private int current = 0;

        public RandomIterator() {
            for (int i = 0; i < idx.length; i++) {
                idx[i] = i;
            }
            StdRandom.shuffle(idx);
        }

        @Override
        public boolean hasNext() {
            return current < N;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = q[idx[current]];
            current++;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    // unit testing
    public static void main(String[] args) {
        RandomizedQueue<Integer> q = new RandomizedQueue<Integer>();
        q.enqueue(new Integer(3));
        q.enqueue(new Integer(4));
        q.enqueue(new Integer(5));
        q.enqueue(new Integer(9));
        StdOut.println("-----------------");
        StdOut.println(q.sample());
        StdOut.println(q.sample());
        StdOut.println(q.sample());
        StdOut.println("-----------------");
        for (int i : q) {
            StdOut.println(i);
        }
        StdOut.println("-----------------");
        StdOut.println(q.dequeue());
        StdOut.println(q.dequeue());
        StdOut.println(q.dequeue());
        StdOut.println(q.dequeue());
    }
}
