package JavaSE;

public class test {
public static void change (int[] a){
    int[] b=a;
    b[0] = 100;
}

    public static void main(String[] args){
    int[] a = new int[]{0};
        change(a);
        System.out.println(a[0]);
    }
}
