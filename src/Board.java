import java.util.Map;

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
    // does this board equal y?
    public boolean equals(Object y) {
        Board board = (Board) y;
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
        Stack<Board> stack = new Stack<Board>();
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
                s.append(String.format("%2d ", tiles[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }
}
