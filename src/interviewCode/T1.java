package interviewCode;

import java.util.*;

public class T1 {
    public static int[][] solve1(int[][] matrix) {
        if (matrix.length == 0) return null;
        int m = matrix.length, n = matrix[0].length;
        int numof1 = 0;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < m; i++)
            for (int j = n - numof1; j >= 0; j--) {
                if (j == n) j = n - 1;
                if (matrix[i][j] == 0) break;
                if (matrix[i][j] == 1 && n - j == numof1)
                    list.add(i);
                if (matrix[i][j] == 1 && n - j > numof1) {
                    numof1++;
                    list.clear();
                    list.add(i);
                }
            }
        int[][] result = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            result[i][0] = list.get(i);
            result[i][1] = numof1;
        }
        return result;
    }

    public static boolean solve2(String s) {
        Stack<Character> stack = new Stack<>();
        String a = ")]}";
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case '(':
                    if (stack.isEmpty() || stack.peek() != ']') return false;
                    stack.push(')');
                    break;
                case '[':
                    if (stack.isEmpty() || stack.peek() != '}') return false;
                    stack.push(']');
                    break;
                case '{':
                    if (!stack.isEmpty() && stack.peek() != '}') return false;
                    stack.push('}');
                    break;
                default:
                    if (a.contains(String.valueOf(s.charAt(i))) && (stack.isEmpty() || stack.pop() != s.charAt(i))) // 如果是右括号, 那么stack 中最上面的应该是匹配的左括号
                        return false;

            }
        }
        return stack.isEmpty();
    }

    public static int solve3() {
        Set<String> set = new HashSet<>();
        return helper(0, 0, set);
    }

    public static boolean move(int direction) {
        return true;
    }

    public static int helper(int x, int y, Set<String> set) {
        set.add(x + ", " + y);
        if (!set.contains(x + ", " + (y + 1)) && move(0)) {
            helper(x, y + 1, set);
            move(0);
        }
        if (!set.contains((x + 1) + ", " + y) && move(1)) {
            helper(x + 1, y, set);
            move(1);
        }
        if (!set.contains(x + ", " + (y - 1)) && move(2)) {
            helper(x, y - 1, set);
            move(2);
        }
        if (!set.contains((x - 1) + ", " + y) && move(3)) {
            helper(x - 1, y, set);
            move(3);
        }
        return set.size();
    }

    public static int[] solve4(int[][] a) {

        // 默认从小到大
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> a[o[0]][o[1]]));
        int max = Integer.MIN_VALUE, start = 0, end = Integer.MAX_VALUE;
        for (int i = 0; i < a.length; i++) {
            pq.offer(new int[]{i, 0});
            max = Math.max(max, a[i][0]);
        }
        while (pq.size() == a.length) {
            int e[] = pq.poll(), row = e[0], col = e[1];
            if (end - start > max - a[row][col]) {
                start = a[row][col];
                end = max;
            }

            if (col + 1 != a[row].length) {
                pq.offer(new int[]{row, col + 1});
                max = Math.max(max, a[row][col + 1]);
            }
        }
        return new int[]{start, end};
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solve4(new int[][]{{1, 3, 5}, {4, 8}, {2, 5}})));
//        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(Comparator.comparingInt())

    }

}
