package bit;

public class BitOperation {

    // 2 的n 次方
    public static boolean isPower(int n){
        if(n < 1) return false;
        int i = 1;
        while(i <= n){
            if(i == n) return true;
            n <<= 1;
        }
        return false;
    }

    public static boolean isPowerOf2(int n){
        if(n < 1) return false;
        int m = n&(n-1);
        return m == 0;
    }

    public static int countOne(int n){
        int count = 0;
        while(n > 0){
            if(n != 0)
                n = n & (n -1);
            count ++;
        }
        return count;
    }
    public static void main(String[] args){
        System.out.println(countOne(10));
    }
}
