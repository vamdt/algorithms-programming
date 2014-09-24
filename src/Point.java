/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new Comparator<Point>() {
        @Override
        public int compare(Point o1, Point o2) {
            double delta = slopeTo(o1) - slopeTo(o2);
            if (delta < 0.0) {
                return -1;
            } else if (delta > 0.0) {
                return 1;
            } else {
                return 0;
            }
        }
    };

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        double deltaX = that.x - this.x;
        double deltaY = that.y - this.y;
        if (deltaX == 0.0 && deltaY == 0.0) return Double.NEGATIVE_INFINITY;
        if (deltaX == 0.0) return Double.POSITIVE_INFINITY;
        if (deltaY == 0.0) return  0.0;
        return deltaY / deltaX;
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        if ((this.y < that.y) || (this.y == that.y && this.x < that.x)) {
            return -1;
        } else if (this.y == that.y && this.x == that.x) {
            return 0;
        } else {
            return 1;
        }
    }



    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */

        Point p= new Point(3, 2);
        Point q= new Point(3, 8);
        Point r= new Point(3, 4);
        StdOut.println(p.SLOPE_ORDER.compare(q, r));
        StdOut.println(p.slopeTo(q));
        StdOut.println(p.slopeTo(r));
        double delta = p.slopeTo(q) - p.slopeTo(r);
        StdOut.println(delta);
        StdOut.println(Double.POSITIVE_INFINITY - Double.POSITIVE_INFINITY);
    }
}
