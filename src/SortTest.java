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
    }
    public static void swap(int[] arr, int index, int min) {
        int temp = arr[index];
        arr[index] = arr[min];
        arr[min] = temp;
    }
}
