import java.util.Arrays;

public class Fast {
    public static void main(String[] args) {
        // rescale coordinates and turn on animation mode
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);
        StdDraw.setPenRadius(0.01);  // make the points a bit larger

        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            Point p = new Point(x, y);
            StdOut.println(p);
            p.draw();
            points[i] = p;
        }
        StdOut.println("--------");
        test(points);
        StdDraw.show(0);
        StdDraw.setPenRadius();
    }

    private static void test(Point[] points) {
        Arrays.sort(points);
        for (int i = 0; i < points.length; i++) {
            Arrays.sort(points, i+1, points.length, points[i].SLOPE_ORDER);
            for (int j = i+1; j < points.length-2; j++) {
                if (collinear(points[i],  points[j], points[j+1], points[j+2])) {
                    drawLine(points[i], points[j+2]);
                }

            }
            Arrays.sort(points);
        }
    }


    private static void drawLine(Point fp, Point lp) {
        fp.drawTo(lp);
    }

    private static boolean collinear(Point fp, Point sp, Point tp, Point lp) {
        return fp.slopeTo(sp) == fp.slopeTo(tp) && fp.slopeTo(sp) == fp.slopeTo(lp);
    }
}
