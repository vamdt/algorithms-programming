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
            p.draw();
            points[i] = p;
        }
        test(points);
        StdDraw.show(0);
        StdDraw.setPenRadius();
    }



    private static void test(Point[] points) {
        Arrays.sort(points);
        int m = 0;
        for (int i = 0; i < points.length; i++) {
            Arrays.sort(points, i+1, points.length, points[i].SLOPE_ORDER);
            for (int j = i+1; j < points.length-2; j++) {
//                StdOut.println(i);
//                StdOut.println(j);
                if (collinear(points[i],  points[j], points[j+1], points[j+2])) {
//                    StdOut.print("----");
//                    puts(points[i], points[j], points[j + 1], points[j + 2]);
//                    StdOut.println();
//                    if (j - 2 >= 0) {
//                        StdOut.print("continue----");
//                        puts(points[j - 2], points[j - 1], points[j], points[j + 1]);
//                    }
//                    StdOut.println();
                    if (i > 0 && collinear(points[i], points[j-1], points[j], points[j+1])) {
                        continue;
                    }
                    puts(points[i],  points[j], points[j+1], points[j+2]);
                    m = 1;
                    while (j+2+m < points.length) {
                        if (collinear(points[i], points[j], points[j+1], points[j+2+m])) {
                            StdOut.printf(" -> %s", points[j+2+m]);
                            m++;
                        } else {
                            break;
                        }
                    }
                    drawLine(points[i], points[j + 1 + m]);
                    j = j + m;

                    StdOut.printf("\n");
                }
            }
            Arrays.sort(points);
        }
    }

    private static void puts(Point point, Point point1, Point point2, Point point3) {
        StdOut.printf("%s -> %s -> %s -> %s", point, point1, point2, point3);
    }


    private static void drawLine(Point fp, Point lp) {
        fp.drawTo(lp);
    }

    private static boolean collinear(Point fp, Point sp, Point tp, Point lp) {
        return fp.slopeTo(sp) == fp.slopeTo(tp) && fp.slopeTo(sp) == fp.slopeTo(lp);
    }
}
