import java.util.Map;

public class Board {
    private int[][] blocks;
    // construct a board from an N-by-N array of blocks
    public Board(int[][] blocks) {
        this.blocks = blocks;
    }
    // (where blocks[i][j] = block in row i, column j)

    // board dimension N
    public int dimension() {
        return blocks.length * blocks.length;
    }

    // number of blocks out of place
    public int hamming() {
        int N = blocks.length;
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
        int N = blocks.length;
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

    }
    // does this board equal y?
    public boolean equals(Object y) {
        Board board = (Board) y;
        int N = blocks.length;
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
    // string representation of this board (in the output format specified below)
    public String toString() {

    }

    // unit tests (not graded)
    public static void main(String[] args) {

    }
}
