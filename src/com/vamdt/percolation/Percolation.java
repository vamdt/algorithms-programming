package com.vamdt.percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.introcs.StdOut;

public class Percolation {

    private WeightedQuickUnionUF uf = null;
    private WeightedQuickUnionUF ufBackwash = null;
    private int[][] grids = null;

    // create N-by-N grid, with all sites blocked
    public Percolation(int N)  {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        // index n*n is virtual top
        // index n*n+1 is virtual bottom
        uf = new WeightedQuickUnionUF(N * N  + 2);
        ufBackwash = new WeightedQuickUnionUF(N * N + 2);
        grids = new int[N+1][N+1];
        for (int i = 1; i < grids.length; i++) {
            uf.union(xyTo1D(1, i), N*N);
            uf.union(xyTo1D(grids.length-1, i), N*N+1);
            ufBackwash.union(xyTo1D(1, i), N*N);
        }
    }

    /**
     *  open site (row i, column j) if it is not already
     */
    public void open(int i, int j) {
        if (i >= grids.length || i < 1 || j >= grids.length || j < 1) {
            throw new IndexOutOfBoundsException();
        }
        if (grids[i][j] == 1) {
            return;
        }
        grids[i][j] = 1;
        if (i >= 1 && i < grids.length-1 && isOpen(i+1, j)) {

            uf.union(xyTo1D(i, j), xyTo1D(i+1, j));
            ufBackwash.union(xyTo1D(i, j), xyTo1D(i+1, j));
        }
        if (i > 1 && i < grids.length && isOpen(i-1, j)) {
            uf.union(xyTo1D(i, j), xyTo1D(i-1, j));
            ufBackwash.union(xyTo1D(i, j), xyTo1D(i-1, j));
        }

        if (j >= 1 && j < grids.length-1 && isOpen(i, j+1)) {
            uf.union(xyTo1D(i, j), xyTo1D(i, j+1));
            ufBackwash.union(xyTo1D(i, j), xyTo1D(i, j+1));
        }

        if (j > 1 && j < grids.length && isOpen(i, j-1)) {
            uf.union(xyTo1D(i, j), xyTo1D(i, j-1));
            ufBackwash.union(xyTo1D(i, j), xyTo1D(i, j-1));
        }
    }

    public boolean isOpen(int i, int j) {
        if (i >= grids.length || i < 1 || j >= grids.length || j < 1) {
            throw new IndexOutOfBoundsException();
        }
        return grids[i][j] == 1;
    }

    public boolean isFull(int i, int j) {
        if (i >= grids.length || i < 1 || j >= grids.length || j < 1) {
            throw new IndexOutOfBoundsException();
        }
        int virtualTop = (grids.length-1) * (grids.length-1);
        return (isOpen(i, j) && ufBackwash.connected(xyTo1D(i, j), virtualTop));
    }

    public boolean percolates()  {
        int virtualTop = (grids.length-1) * (grids.length-1);
        int virtualBottom = virtualTop + 1;
        return uf.connected(virtualTop, virtualBottom);
    }

    private int xyTo1D(int x, int y) {
        int length = grids.length-1;
        return (x-1) * length + y-1;
    }

    public static void main(String[] args) {
        Percolation p = new Percolation(20);
        p.open(1, 1);
        p.open(2, 1);
        StdOut.println(p.uf.connected(p.xyTo1D(1, 1), p.xyTo1D(2, 1)));
    }

}
