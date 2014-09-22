/**
 * Created by ang on 2014/9/14.
 */
public class SortTest {
    public static void main(String[] args) {
        int[] a = new int[] {30, 88, 23, 63, 33, 29, 48, 99, 59, 25};

        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (int j = i+1; j < a.length; j++) {
                if (a[j] < a[min]) {
                    min = j;
                }
            }
            swap(a, i, min);
            StdOut.println();
            for (int m = 0; m < a.length; m++) {
                StdOut.print(a[m]);
                StdOut.print(' ');
            }
        }
        StdOut.println();
        int[] b = new int[] {26, 27, 17, 45, 87, 19, 92, 29, 86, 60, 10, 51};
        partition(b);

    }

    public static void partition(int[] arr) {
        int i, j, lo, hi;
        lo = i = 0;
        hi = arr.length - 1;
        j = hi + 1;
        while (true) {
            while (arr[++i] < arr[lo]) {
                if (i == hi) break;
            }
            while (arr[lo] < arr[--j]) {
                if (j == lo) break;
            }

            if (i >= j) break;
            arr[i] ^= arr[j];
            arr[j] ^= arr[i];
            arr[i] ^= arr[j];
        }

        arr[lo] ^= arr[j];
        arr[j] ^= arr[lo];
        arr[lo] ^= arr[j];
        for (int k = 0; k < arr.length; k++) {
            StdOut.printf("%d ", arr[k]);
        }
    }


    public static void swap(int[] arr, int index, int min) {
        int temp = arr[index];
        arr[index] = arr[min];
        arr[min] = temp;
    }
}
