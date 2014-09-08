package com.vamdt.percolation;

/**
 * Created by ang on 2014/9/8.
 */
public class Percolation {
    public Percolation(int N)                // create N-by-N grid, with all sites blocked
    public void open(int i, int j)           // open site (row i, column j) if it is not already
    public boolean isOpen(int i, int j)      // is site (row i, column j) open?
    public boolean isFull(int i, int j)      // is site (row i, column j) full?
    public boolean percolates()              // does the system percolate?
    public static void main(String[] args)   // test client, optional
}
