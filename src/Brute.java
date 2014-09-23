import java.util.Arrays;

public class Brute {

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
            p.draw();
            points[i] = p;
        }
        test(points);
        StdDraw.show(0);
        StdDraw.setPenRadius();
    }

    private static void test(Point[] points) {

        int i = 0, j = 0, k = 0, l = 0;
        int N = points.length;
        Arrays.sort(points);
        for (; i < N; i++) {
            for (j = i + 1; j < N; j++) {
                for (k = j + 1; k < N; k++) {
                    for (l = k + 1; l < N; l++) {
                        if (collinear(points[i], points[j], points[k], points[l]))  {
                            drawLine(points[i], points[l]);
                        }
                    }
                }
            }
        }
    }

    private static void drawLine(Point fp, Point lp) {
        fp.drawTo(lp);
    }

    private static boolean collinear(Point fp, Point sp, Point tp, Point lp) {
        return fp.slopeTo(sp) == fp.slopeTo(tp) && fp.slopeTo(sp) == fp.slopeTo(lp);
    }
}
