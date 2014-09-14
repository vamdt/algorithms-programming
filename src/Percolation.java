//package com.vamdt.percolation;


public class Percolation {

    private WeightedQuickUnionUF uf = null;
    private WeightedQuickUnionUF ufBackwash = null;
    private boolean[] grids = null;
    private int gridsLength;


    // create N-by-N grid, with all sites blocked
    public Percolation(int N)  {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        uf = new WeightedQuickUnionUF(N * N  + 2);
        ufBackwash = new WeightedQuickUnionUF(N * N + 2);
        grids = new boolean[N * N + 2];
        gridsLength = N;
        for (int i = 1; i <= gridsLength; i++) {
            uf.union(getGrid(1, i), N*N);             // top row union to virtual top
            uf.union(getGrid(gridsLength, i), N*N+1); // bottom row union to virtual bottom
            ufBackwash.union(getGrid(1, i), N*N);     // backwash only top row union to virtual top
        }
    }

    /**
     *  open site (row i, column j) if it is not already
     */
    public void open(int i, int j) {
        checkRange(i, j);
        if (isOpen(i, j)) {
            return;
        }
        grids[getGrid(i, j)] = true;
        if (i >= 1 && i < gridsLength && isOpen(i+1, j)) {

            uf.union(getGrid(i, j), getGrid(i + 1, j));
            ufBackwash.union(getGrid(i, j), getGrid(i + 1, j));
        }
        if (i > 1 && i <= gridsLength && isOpen(i-1, j)) {
            uf.union(getGrid(i, j), getGrid(i - 1, j));
            ufBackwash.union(getGrid(i, j), getGrid(i - 1, j));
        }

        if (j >= 1 && j < gridsLength && isOpen(i, j+1)) {
            uf.union(getGrid(i, j), getGrid(i, j + 1));
            ufBackwash.union(getGrid(i, j), getGrid(i, j + 1));
        }

        if (j > 1 && j <= gridsLength && isOpen(i, j-1)) {
            uf.union(getGrid(i, j), getGrid(i, j - 1));
            ufBackwash.union(getGrid(i, j), getGrid(i, j - 1));
        }
    }

    public boolean isOpen(int i, int j) {
        checkRange(i, j);
        return grids[getGrid(i, j)];
    }

    public boolean isFull(int i, int j) {
        checkRange(i, j);
        return (isOpen(i, j) && ufBackwash.connected(getGrid(i, j), virtualTop()));
    }

    public boolean percolates()  {
        if (gridsLength == 1) return isOpen(1, 1);
        return uf.connected(virtualTop(), virtualBottom());
    }

    private void checkRange(int i, int j) {
        if (i > gridsLength || i < 1 || j > gridsLength || j < 1) {
            throw new IndexOutOfBoundsException();
        }
    }

    private int getGrid(int x, int y) {
        return (x-1) * (gridsLength)+ y-1;
    }

    private int virtualTop() {
        return gridsLength * gridsLength;
    }

    private  int virtualBottom() {
        return virtualTop() + 1;
    }

    public static void main(String[] args) {
        Percolation p = new Percolation(20);
        p.open(1, 1);
        p.open(2, 1);
        StdOut.println(p.uf.connected(p.getGrid(1, 1), p.getGrid(2, 1)));
    }

}
