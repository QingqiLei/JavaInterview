package interviewCode;

import java.util.Scanner;

public class T5 {
    // 山谷
    public static int solve1(int[] A) {
        int res = 0, up = 0, down = 0;
        for (int i = 1; i < A.length; i++) {
            if (up > 0 && A[i - 1] > A[i] || A[i - 1] == A[i]) // new counting
                up = down = 0;
            if (A[i - 1] < A[i]) up++;
            if (A[i - 1] > A[i]) down++;
            if (up > 0 && down > 0 && up + down + 1 > res)
                res = up + down + 1;
        }
        return res;
    }

    public static void solve2(String s) {
        for (int i = 1; i < s.length(); i++) {
            String str = s.substring(0, i);
            StringBuffer temp = new StringBuffer("");
            for (int j = 0; j < s.length() / i; j++)
                temp.append(str);
            temp.append(s.substring(0, s.length() % i));
            if (temp.toString().equals(s)) {
                System.out.println(str);
                break;
            }
        }
    }

    public static int test3(int target) {
        int n = Math.abs(target);
        int k = (int) Math.ceil((Math.sqrt(8 * n + 1) - 1) / 2);
        int total = k * (k + 1) / 2;
        int d = total - n;
        if (d % 2 == 0)
            return k;
        else if (d + k + 1 % 2 == 0)
            return k + 1;
        else
            return k + 2;
    }

    public  static int minbushu(int z){
        int n = (int) Math.sqrt(2*Math.abs(z));
        for(;(n*(n+1)/2-z)%2!=0;n++);
        return n;
    }
// 手机靓号问题
    public static void test4(){
        Scanner scanner=new Scanner(System.in);
        int N=scanner.nextInt();
        int K=scanner.nextInt();
        String s=scanner.next();
        StringBuffer ans=null;
        int res=(int)1e9;
        for(int i=0;i<10;i++){
            StringBuffer t=new StringBuffer(s);
            int p=K,c=0;
            for(int j=0;j<10;j++){
                for(int l=0;l<N;l++){
                    if(p!=0&&t.charAt(l)-'0'==i+j){
                        p--;
                        t.setCharAt(l,(char)(i+'0'));
                        c=c+j;
                    }
                }
                if(j!=0)
                    for(int l=N-1;l>=0;l--)
                        if(p!=0&&t.charAt(l)-'0'==i-j){
                            p--;
                            t.setCharAt(l,(char)(i+'0'));
                            c=c+j;
                        }
            }
            if(c<res){
                res=c;
                ans=t;
            }


        }
        System.out.println(res);
        System.out.println(ans);
    }


    public static void main(String[] args) {
        System.out.println(minbushu(100));
    }

}
