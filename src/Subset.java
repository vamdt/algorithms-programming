public class Subset {
    public static void main(String[] args) {
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        StdOut.println(args[0]);
        int limit = Integer.parseInt(args[0]);
        for (String s = StdIn.readString(); !s.isEmpty(); ) {
            q.enqueue(s);
        }
        for (String s : q) {
            StdOut.println(s);
        }
    }
}
