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
            findLines(pointSlopes);
//            int m = 1, start = 0;
//            double lastSlope = pointSlopes[0].slopeTo(pointSlopes[1]);
//            for (int j = 2; j < pointSlopes.length; j++) { //point[i] == pointSlope[0]
//                double slope = pointSlopes[0].slopeTo(pointSlopes[j]);
//                if (slope == lastSlope) {
//                    m++;
//                } else {
//                    if (m > 2) {
//                        puts(pointSlopes, start, m);
//                    }
//                    start = j;
//                    m = 1;
//                }
//                lastSlope = slope;
//            }
//            if (m > 2) {
//                puts(pointSlopes, start, m);
//            }
        }
    }

    private static void findLines(Point[] points) {
        Point[] lines = new Point[points.length];
        lines[0] = points[0];
        double previousSlope = points[0].slopeTo(points[1]);
        int alignedPoints = 1;
        int start = 1;
        for (int i = 2; i < points.length; i++) {
            double slope = points[0].slopeTo(points[i]);
            if (slope == previousSlope) {
//                lines[++alignedPoints] = points[i];
                ++alignedPoints;
            } else {
                if (alignedPoints >= 3) {
//                    showLine(lines, alignedPoints + 1);
                    puts(points, start, alignedPoints);
                }
                alignedPoints = 1;
//                lines[1] = points[i];
                start = i;
            }
            previousSlope = slope;
        }

        if (alignedPoints >= 3) {
//            showLine(lines, alignedPoints + 1);
            puts(points, start, alignedPoints);
        }
    }

    private static void puts(Point[] points, int index, int length) {
        Arrays.sort(points, index, index+length-1);
        drawLine(points[index], points[index+length-1]);
        StdOut.printf("%s ", points[0]);
        for (int i = 0; i < length; i++) {
            StdOut.printf("-> %s ", points[index+i]);
        }
        StdOut.printf("\n");
    }

    private static void showLine(Point[] lines, int size) {
        Arrays.sort(lines, 1, size);
        if (lines[0].compareTo(lines[1]) < 0) {
            StdOut.printf("%s", lines[0]);
            for (int k = 1; k < size; k++) {
                Point point = lines[k];
                StdOut.printf(" -> %s", point);
            }
            StdOut.println();
            lines[0].drawTo(lines[size - 1]);
        }
    }

    private static void drawLine(Point fp, Point lp) {
        fp.drawTo(lp);
    }

    private static boolean collinear(Point fp, Point sp, Point tp, Point lp) {
        return fp.slopeTo(sp) == fp.slopeTo(tp) && fp.slopeTo(sp) == fp.slopeTo(lp);
    }
}
