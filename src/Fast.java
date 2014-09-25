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
            points[i] = new Point(x, y);
            points[i].draw();
        }
        test(points);
        StdDraw.show(0);
        StdDraw.setPenRadius();
    }



    private static void test(Point[] points) {
        Arrays.sort(points);
        Point[] pointSlopes = new Point[points.length];
        System.arraycopy(points, 0, pointSlopes, 0, points.length);

        for (int i = 0; i < points.length; i++) {
            Arrays.sort(pointSlopes, points[i].SLOPE_ORDER);
            int m = 1, start = 1;
            double lastSlope = pointSlopes[0].slopeTo(pointSlopes[1]);
            for (int j = 2; j < pointSlopes.length; j++) { //point[i] == pointSlope[0]
                double slope = pointSlopes[0].slopeTo(pointSlopes[j]);
                if (slope == lastSlope) {
                    m++;
                } else {
                    if (m > 2) {
                        puts(pointSlopes, start, m);
                    }
                    start = j;
                    m = 1;
                }
                lastSlope = slope;
            }
            if (m > 2) {
                puts(pointSlopes, start, m);
            }
        }
    }

    private static void puts(Point[] points, int index, int length) {
        Point[] aps = new Point[length+1];
        aps[0] = points[0];
        for (int i = 1; i < aps.length; i++) {
            aps[i] = points[index+i-1];
        }
        Arrays.sort(aps, 1, length+1);

        if (aps[0].compareTo(aps[1]) < 0) {
            drawLine(aps[0], aps[length]);
            StdOut.printf("%s ", aps[0]);
            for (int i = 0; i < length; i++) {
                StdOut.printf("-> %s ", aps[i+1]);
            }
            StdOut.printf("\n");
        }
    }

    private static void drawLine(Point fp, Point lp) {
        fp.drawTo(lp);
    }
}
