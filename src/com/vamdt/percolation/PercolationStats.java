package com.vamdt.percolation;

import edu.princeton.cs.introcs.StdOut;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

/**
 * Created by ang on 2014/9/10.
 */
public class PercolationStats {

    private double[] thresholds = null;
    /**
     * perform T independent computational experiments on an N-by-N grid
     * @param N  N * N grids
     * @param T  T times
     */
    public PercolationStats(int N, int T) {
        if(N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        thresholds = new double[T];
        for (int i=0; i<T; i++) {
            Percolation p = new Percolation(N);
            int count = 0;
            while (!p.percolates()) {
                p.open(StdRandom.uniform(1, N + 1), StdRandom.uniform(1, N + 1));
                count ++;
            }
            thresholds[i] = p.openedCount()*1.0/(N * N);
        }
    }

    /**
     * sample mean of percolation threshold
     */
    public double mean(){
        return StdStats.mean(thresholds);
    }                     //

    /**
     * sample standard deviation of percolation threshold
     */
    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    /**
     * returns lower bound of the 95% confidence interval
     */
    public double confidenceLo() {
        return mean() - ( ( 1.96 * stddev() ) / Math.sqrt(thresholds.length) );
    }

    /**
     * returns upper bound of the 95% confidence interval
     */
    public double confidenceHi()  {
        return mean() + ( ( 1.96 * stddev() ) / Math.sqrt(thresholds.length) );
    }

    public static void main(String[] args) {
        PercolationStats ps = new PercolationStats(200, 100);
        StdOut.println(ps.mean());
        StdOut.println(ps.stddev());
        StdOut.println(ps.confidenceLo());
        StdOut.println(ps.confidenceHi());
    }   // test client, described below
}
