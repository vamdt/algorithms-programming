public class Board {
    private int[][] blocks;
    private int N;
    // construct a board from an N-by-N array of blocks
    public Board(int[][] blocks) {
        this.N = blocks.length;
        this.blocks = blocks;
    }
    // (where blocks[i][j] = block in row i, column j)

    // board dimension N
    public int dimension() {
        return N;
    }

    // number of blocks out of place
    public int hamming() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (blocks[i][j] == 0) continue;
                if (blocks[i][j] != (i*N + j + 1)) {
                    count++;
                }
            }
        }
        return count;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        int distances = 0;
        int deltaX, deltaY;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (blocks[i][j] == 0) continue;
                deltaX = (blocks[i][j] - 1) / N - i;
                deltaY = (blocks[i][j] - 1) % N - j;
                distances += Math.abs(deltaX) + Math.abs(deltaY);
            }
        }
        return distances;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return hamming() == 0;
    }
    // a boadr that is obtained by exchanging two adjacent blocks in the same row
    public Board twin() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N - 1; j++) {
                if (blocks[i][j] != 0 && blocks[i][j+1] != 0) {
                    int[][] bs = blocks.clone();
                    bs[i][j] ^= bs[i][j+1];
                    bs[i][j+1] ^= bs[i][j];
                    bs[i][j] ^= bs[i][j+1];
                    return new Board(bs);
                }
            }
        }
        return new Board(blocks);
    }

    private Board swap(int i, int j, int a, int b) {
        if (a < 0 || a >= N || b < 0 || b >= N) return null;
        int[][] bs = blocks.clone();
        bs[i][j] ^= bs[i][j+1];
        bs[i][j+1] ^= bs[i][j];
        bs[i][j] ^= bs[i][j+1];
        return new Board(bs);
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Board board = (Board) y;
        if (this.N != board.N) return false;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (blocks[i][j] != board.blocks[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
    // all neighboring boards
    public Iterable<Board> neighbors() {
        int i0, j0;
        int[] zeros = findZero();
        i0 = zeros[0];
        j0 = zeros[1];

        Stack<Board> stack = new Stack<Board>();

        Board board = this.swap(i0, j0, i0-1, j0);
        if (board != null) stack.push(board);

        board = this.swap(i0, j0, i0+1, j0);
        if (board != null) stack.push(board);

        board = this.swap(i0, j0, i0, j0+1);
        if (board != null) stack.push(board);

        board = this.swap(i0, j0, i0, j0-1);
        if (board != null) stack.push(board);
        return  stack;
    }

    private int[] findZero() {
        int[] ret = new int[2];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (blocks[i][j] == 0) {
                    ret[0] = i;
                    ret[1] = j;
                    return  ret;
                }
            }
        }
        return null;
    }

    // unit tests (not graded)
    public static void main(String[] args) {

    }

    /**
     * string representation of this board (in the output format specified below)
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", blocks[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

}
