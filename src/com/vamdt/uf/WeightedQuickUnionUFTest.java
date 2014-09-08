package com.vamdt.uf;

import edu.princeton.cs.algs4.QuickFindUF;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.introcs.StdOut;

import java.lang.reflect.Field;

/**
 * Created by ang on 2014/9/7.
 */
public class WeightedQuickUnionUFTest {
    public static void main(String[] args) throws Exception {
        int N =10;
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N);
        //   8-0 3-2 5-0 4-9 9-2 7-5 5-6 8-9 9-1
        //
        //3-8 6-0 5-9 3-9 2-7 8-4 0-7 8-1 2-8
//        uf.union(3, 8);
//        uf.union(6, 0);
//        uf.union(5, 9);
//        uf.union(3, 9);
//        uf.union(2, 7);
//        uf.union(8, 4);
//        uf.union(0, 7);
//        uf.union(8, 1);
//        uf.union(2, 8);
        uf.union(0, 4);
        uf.union(0, 7);
        uf.union(5, 9);
        printAll(uf);
        QuickFindUF quf =  new QuickFindUF(10);
        //  0-4 3-6 0-8 2-6 1-4 4-9
        quf.union(0, 4);
        quf.union(3, 6);
        quf.union(0, 8);
        quf.union(2, 6);
        quf.union(1, 4);
        quf.union(4, 9);
    }

    public  static  void printAll(WeightedQuickUnionUF obj) throws Exception {
        Class kls = obj.getClass();
        Field f = kls.getDeclaredField("id");
        f.setAccessible(true);
        int[] sd = (int[]) f.get(obj);
        for (int i=0; i<sd.length; i++) {
            StdOut.print(sd[i]);
            StdOut.print(" ");
        }
    }
}
