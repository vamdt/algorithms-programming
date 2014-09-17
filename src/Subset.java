public class Subset {
    public static void main(String[] args) {
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        StdOut.println(args[0]);
        int limit = Integer.parseInt(args[0]);
//        for (String s = StdIn.readString(); !s.isEmpty(); ) {
//            q.enqueue(s);
//        }
        q.enqueue("AA");
        q.enqueue("BB");
        q.enqueue("CC");
        q.enqueue("AA");
        q.enqueue("BB");
        q.enqueue("CC");
        q.enqueue("DD");
        q.enqueue("EE");
        for (int i = 0; i< limit; i++) {
            StdOut.println(q.dequeue());
        }
    }
}
