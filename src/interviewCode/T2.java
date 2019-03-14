package interviewCode;

import java.io.IOException;
import java.util.*;


public class T2 {
    static int m;
    static ArrayList<Integer> parents = new ArrayList<>();  // (child, parent),
    static ArrayList<HashSet<Integer>> children = new ArrayList<>();  // parent (children )
    static ArrayList<Integer> dists = new ArrayList<>(); // from this to its parent

    public static void main(String[] args) throws IOException {
//        Scanner scanner = new Scanner(System.in);
//        m = scanner.nextInt(); //总长度
//        int n = scanner.nextInt(); // 城市个数
//        for (int i = 0; i < n; i++) {
//            dists.add(0);
//            parents.add(-1);
//            children.add(new HashSet<>());
//        }
//        for (int i = 0; i < n - 1; i++) {
//            int u = scanner.nextInt(), v = scanner.nextInt(), d = scanner.nextInt();
//            if (d > m) continue;
//            children.get(u).add(v);
//            parents.set(v, u);
//            dists.set(v, d);
//        }
//        // find root
//        int root = 0;
//        while (parents.get(root) != -1) root = parents.get(root);
//        System.out.println(dfs(root).last());
        solve2();
    }

    private static TreeSet<Integer> dfs(int root) {
        TreeSet<Integer> res = new TreeSet<>();
        int d = dists.get(root); //

        res.add(0);
        if (children.get(root).size() == 0) { // 没有子节点,
            res.add(d);
            return res;
        }
        // 每一个集合代表每个孩子节点的可选路径
        ArrayList<TreeSet<Integer>> sets = new ArrayList<>();
        for (int child : children.get(root)) sets.add(dfs(child));

        for (int i = 0; i < sets.size(); i++) {
            // 选中一个孩子分支的情况
            for (int path : sets.get(i)) {
                if (d + path <= m)
                    res.add(d + path);
            }
            // 选中两个孩子分支情况
            for (int j = i + 1; j < sets.size(); j++) { // 选中两个孩子分支, 分别是i 和 j
                for (int path1 : sets.get(i)) {
                    for (int path2 : sets.get(j)) {
                        if (path1 + path2 + d <= m)
                            res.add(path1 + path2 + d);
                    }
                }
            }
        }
        return res;
    }


    //    public static String solve(Scanner scanner){
//
//        return "1";
//    }
//    public static void main(String[] args) throws FileNotFoundException {
//        System.setOut(new PrintStream(("output.txt")));
//        Scanner scanner = new Scanner(System.in);
//        int times = scanner.nextInt();
//
//        long start = System.currentTimeMillis();
//        for(int t = 1;  t <= times; t++)
//            System.out.println(String.format("Case #%d: %s", t,solve(scanner)));
//        long end = System.currentTimeMillis();
//        System.err.println(String.format("Time used: %.3fs",(end - start)/1000.0));
//
//    }

    // 数三角形
    public static int solve2(){
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int[][] points = new int[num][2];
        for(int i = 0; i < num; i ++)
            for(int j = 0; j < 2; j++){
                points[i][j] = sc.nextInt();
            }
        int res = 0;
        for(int i = 0; i < num-2; i ++)
            for(int j = i +1; j < num-1; j++ )
                for(int k = j +1; k< num; k++){
                    if(isTriangle(points[i], points[j], points[k])) res ++;
                }
        System.out.println(res);
        return res;
    }
   private static boolean isTriangle(int[] a1, int[] a2, int[] a3){
        double lines[] = new double[3];
         lines[0] = Math.sqrt(Math.pow(a1[0] - a2[0],2) +   Math.pow(a1[1] - a2[1],2));
        lines[1] = Math.sqrt(Math.pow(a1[0] - a3[0],2) +   Math.pow(a1[1] - a3[1],2));
        lines[2] = Math.sqrt(Math.pow(a2[0] - a3[0],2) +   Math.pow(a2[1] - a3[1],2));

       Arrays.sort(lines);
       return lines[2] < (lines[0] + lines[1]);
  }



    // 字符串相乘
    public static String solve3() {
        Scanner sc = new Scanner(System.in);
        String str1 = sc.next();
        String str2 = sc.next();
        int tmp = 1;
        if (str1.startsWith("-")) {
            tmp *= -1;
            str1 = str1.substring(1);
        }
        if (str2.startsWith("-")) {
            tmp *= -1;
            str2 = str2.substring(1);
        }

        int m = str1.length(), n = str2.length();
        int[] pos = new int[m + n];

        for (int i = m - 1; i >= 0; i--)
            for (int j = n - 1; j >= 0; j--) {
                int mul = (str1.charAt(i) - '0') * (str2.charAt(j) - '0');
                int left = i + j, right = i + j + 1; // left 是进位
                int sum = mul + pos[right];
                pos[left] += sum / 10;
                pos[right] = sum % 10;
            }
        System.out.println(Arrays.toString(pos));
        StringBuilder sb = new StringBuilder();
        // 去掉前面的0, 不去掉中间和末尾的0
        for (int p : pos) if (sb.length() != 0 || p != 0) sb.append(p);
        String res = sb.length() == 0 ? "0" : sb.toString();

        return tmp == 1 ? res : "-" + res;
    }

    // 发饼干, 学生得到足够的饼干干活
    public static void solve4(){
        Scanner sc = new Scanner(System.in);
        int child = sc.nextInt();
        int arrchild[] = new int[child];
        for(int i = 0; i < child; i++)
            arrchild[i] = sc.nextInt();

        int teach = sc.nextInt();
        int arrtea[] = new int[teach];
        for(int i = 0; i < teach; i++)
            arrtea[i] = teach;

        Arrays.sort(arrchild);
        Arrays.sort(arrtea);
        int j = 0;
        int count = 0;
        for(int i = 0; i < teach; i++)
            if(j < child && arrchild[j] <= arrtea[i]){
                count++;
                j++;
            }
        System.out.println(count);
    }

}
