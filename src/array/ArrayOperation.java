package array;

import java.util.Arrays;
import java.util.Random;

public class ArrayOperation {

    // 第二大的元素
    public static int Find2thMax(int data[]){
        int count = data.length;
        int maxnumber = data[0];
        int sec_max = Integer.MAX_VALUE;
        for(int i = 1; i < count ; i ++){
            if(data[i] > maxnumber){
                sec_max = maxnumber;
                maxnumber = data[i];
            }
            else
                if(data[i] > sec_max)
                    sec_max = data[i];
        }
        return sec_max;
    }

    // 和最大的子数组
    public static int maxSubArray(int arr[]){
        int maxSum = Integer.MAX_VALUE;
        int sum = 0;
        for(int i = 0;  i< arr.length; i++){
            if(sum < 0){
                sum = arr[i];
            }
            else
                sum += arr[i];

            if(sum > maxSum){
                maxSum = sum;
            }
        }
        return maxSum;
    }

    // 相加为20
    public static void findSum(int[] a, int sum){
        Arrays.sort(a);
        int begin = 0;
        int end = a.length -1;
        while(begin < end){
            if(a[begin] + a[end] < sum)
                begin ++;
            else if(a[begin] +a[end] > sum)
                end --;
            else{
                begin ++;
                end --;
            }
        }
    }

    public static void shiftK(int a[], int k){
        int n = a.length;
        k = k % n;
        reverse(a, 0,n-1);
        reverse(a, 0, n-k-1);
        reverse(a, n-k, n-1);

    }

    private static void reverse(int a[], int b, int e){ // including including
        for(;b < e; b++, e--){
            int temp = a[e];
            a[e] = a[b];
            a[b]  =temp;
        }
    }

    public int findKthLargest(int[] nums, int k){
        shuffle(nums);
        k = nums.length - k;
        int left = 0, right = nums.length -1;
        while(left < right){
            int j = partition(nums,left, right);
            if(j > k) right = j-1;
            else if(j < k) left = j +1;
            else break;
        }
        return nums[k];
    }

    private int partition(int[] a, int left, int right){
        int i = left, j = right +1; // we choose a[left], so excluding
        while(true){
            while(i < right && a[++i] < a[left]);
            while(j > left && a[--j] > a[left]);
            if(i >= j) break;
            exch(a, left,right);

        }
        exch(a,left, j);
        return j;
    }

    private void exch(int[] a, int left, int right){
        int temp = a[left];
        a[left] = a[right];
        a[right] = temp;
    }
    private void shuffle(int a[]){
        Random random = new Random();
        int len = a.length;
        for(int i = 1; i < a.length; i++)
            exch(a,i,random.nextInt(a.length));
    }


}
