package com.vamdt.percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.introcs.StdOut;

/**
 * Created by ang on 2014/9/8.
 */
public class Percolation {

    private WeightedQuickUnionUF uf = null;
    private int[][] grids = null;
    private int oCount = 0;

    // create N-by-N grid, with all sites blocked
    public Percolation(int N)  {
        // index n*n is virtual top
        // index n*n+1 is virtual bottom
        uf = new WeightedQuickUnionUF(N * N  + 2);
        grids = new int[N+1][N+1];
        for (int i=1; i< grids.length; i++) {
            uf.union( xyTo1D(1,i), N*N);
            uf.union( xyTo1D(grids.length-1,i), N*N+1);
        }
    }

    /**
     *  open site (row i, column j) if it is not already
     */
    public void open(int i, int j) {
        if (i>=grids.length || i<1 || j>=grids.length || j<1) {
            throw new IndexOutOfBoundsException();
        }
        if(grids[i][j] == 1) {
            return;
        }
        grids[i][j] = 1;
        oCount++ ;
        if (i>=1 && i<grids.length-1 && isOpen(i+1, j)) {

            uf.union( xyTo1D(i, j), xyTo1D(i+1, j));
        }
        if (i>1 && i<grids.length && isOpen(i-1, j)) {
            uf.union( xyTo1D(i, j), xyTo1D(i-1, j));
        }

        if(j>=1 && j<grids.length-1 && isOpen(i, j+1)) {
            uf.union(xyTo1D(i,j), xyTo1D(i, j+1));
        }

        if(j>1 && j<grids.length && isOpen(i, j-1)) {
            uf.union(xyTo1D(i,j), xyTo1D(i, j-1));
        }
    }

    public int openedCount()  {
        return oCount;
    }

    public boolean isOpen(int i, int j) {
        if (i>=grids.length || i<1 || j>=grids.length || j<1) {
            throw new IndexOutOfBoundsException();
        }
        return grids[i][j] == 1;
    }

    public boolean isFull(int i, int j) {
        if (i>=grids.length || i<1 || j>=grids.length || j<1) {
            throw new IndexOutOfBoundsException();
        }
        if (grids[i][j] != 1) {
            return  false;
        }
        if ( uf.connected( xyTo1D(i, j), (grids.length-1) *(grids.length-1) ) ) {
            return true;
        } else {
            return false;
        }
    }

    public boolean percolates()  {
        return uf.connected( (grids.length-1) * (grids.length-1), (grids.length-1)*(grids.length-1)+1 );
    }

    private int xyTo1D(int x, int y) {
        int length = grids.length-1;
        return (x-1) * length + y-1;
    }

    public static void main(String[] args) {
        Percolation p = new Percolation(20);
        p.open(1,1);
        p.open(2,1);
        StdOut.println( p.uf.connected( p.xyTo1D(1,1), p.xyTo1D(2,1) ) );
    }

}
