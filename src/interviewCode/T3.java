package interviewCode;

import java.util.Scanner;

public class T3 {

    // 定一个无序数组，包含正数、负数和0，要求从中找出3个数的乘积，使得乘积最大，
    public static void solve2() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long[] arr = new long[n];
        for (int i = 0; i < n; i++)
            arr[i] = scanner.nextLong();
        System.out.println(getMaxMul(arr));
    }

    private static long getMaxMul(long[] arr) {

        long max1 = 0, max2 = 0, max3 = 0, min1 = 0, min2 = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= max1) {
                max3 = max2;
                max2 = max1;
                max1 = arr[i];
                continue;
            } else if (arr[i] >= max2) {
                max3 = max2;
                max2 = arr[i];
                continue;
            } else if (arr[i] >= max3) {
                max3 = arr[i];
                continue;
            }

            if (arr[i] <= min1) {
                min2 = min1;
                min1 = arr[i];
                continue;
            } else if (arr[i] < min2) {
                min2 = arr[i];
                continue;
            }
        }
        return Math.max(max1 * max2 * max3, max1 * min1 * min2);
    }
    private static long getMaxMul1(long[] arr) {

        long max1 = getOnposition(arr, arr.length -1);
        long max2 = getOnposition(arr, arr.length-2);
        long max3 = getOnposition(arr,  arr.length-3);
        long min1 = getOnposition(arr, 0);
        long min2 = getOnposition(arr, 1);
        return Math.max(max1 * max2 * max3, max1 * min1 * min2);

    }

    static long getOnposition(long[] a, int r){
        int left = 0,  right = a.length -1;
        while(left < right){
            int j = partition(a, left, right);
            if(j > r) right = j-1;
            else if(j < r) left = j +1;
            else return a[r];
        }

        return a[r];
    }

    static int partition(long[] a, int l, int r){ // including
        int left = l, right = r + 1; // choose a[l]

        while(left != right){
            while( left < r && a[++left] < a[l]);
            while(right > l && a[l] < a[--right]);
            if(left >= right) break;
            swap(a,left,right);
        }
        swap(a,left,right);

        return left;
    }

    static void swap(long[] a, int l, int r){
        long temp = a[l];
        a[l] = a[r];
        a[r] = a[l];
    }
}
