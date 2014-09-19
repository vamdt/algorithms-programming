public class Subset {
    public static void main(String[] args) {
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        int limit = Integer.parseInt(args[0]);
        String s = "";
        while (!StdIn.isEmpty()) {
            s = StdIn.readString();
            if (!s.isEmpty()) {
                q.enqueue(s);
            }
        }
        for (int i = 0; i < limit; i++) {
            StdOut.println(q.dequeue());
        }
    }
}
