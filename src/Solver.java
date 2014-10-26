public class Solver {

    private SearchNode goal;

    private class SearchNode implements Comparable<SearchNode> {

        private Board borad;
        private int moves;
        private SearchNode prev;

        public SearchNode(Board board, SearchNode prev, int moves) {
            this.borad = board;
            this.prev = prev;
            this.moves = moves;
        }

        public Board getBorad() {
            return borad;
        }

        public int getMoves() {
            return moves;
        }

        public SearchNode getPrev() {
            return prev;
        }

        @Override
        public int compareTo(SearchNode that) {
            return this.borad.manhattan() + this.moves - that.borad.manhattan() - that.moves;
        }
    }

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        MinPQ<SearchNode> queue = new MinPQ<SearchNode>();
        MinPQ<SearchNode> twinQueue = new MinPQ<SearchNode>();

        SearchNode node = new SearchNode(initial, null, 0);
        SearchNode twinNode = new SearchNode(initial.twin(), null, 0);

        while (!node.getBorad().isGoal() && !twinNode.getBorad().isGoal()) {
            for (Board board : node.getBorad().neighbors()) {
                if (node.getPrev() == null || !board.equals(node.getPrev().getBorad())) {
                    queue.insert(new SearchNode(board, node, node.getMoves() + 1));
                }
            }

            for (Board board : twinNode.getBorad().neighbors()) {
                if (twinNode.getPrev() == null || !board.equals(twinNode.getPrev().getBorad())) {
                    twinQueue.insert(new SearchNode(board, twinNode, twinNode.getMoves() + 1));
                }
            }
            node = queue.delMin();
            twinNode = twinQueue.delMin();
        }

        if (node.getBorad().isGoal()) {
            goal = node;
        } else {
            goal = null;
        }

    }

    // is the initial board solvable?
    public boolean isSolvable() {
        return goal != null;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (!isSolvable()) return -1;
        return goal.getMoves();
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (!isSolvable()) return null;

        Stack<Board> stack = new Stack<Board>();
        for (SearchNode node = goal; node != null; node = node.getPrev()) {
            stack.push(node.getBorad());
        }
        return stack;
    }

    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
