package offer;

import Tree.TreeNode;
import linkedlist.list.ListNode;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class Big {
    // 二维数组查找
    public boolean find(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;
        int i = 0, j = matrix[0].length - 1;
        while (i < matrix.length && j >= 0) {
            if (matrix[i][j] == target) return true;
            if (matrix[i][j] < target) i++;
            else j--;
        }
        return false;
    }

    public ArrayList<Integer> printListFromTail(ListNode pNode) {
        ArrayList<Integer> nodes = new ArrayList<>();
        if (pNode == null) return nodes;
        while (pNode != null) {
            nodes.add(pNode.val);
            pNode = pNode.next;

        }
        Collections.reverse(nodes);
        return nodes;
    }

    //  前序和中序  构建二叉树
    public TreeNode constructTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++)
            map.put(inorder[i], i);
        return helper(map, 0, 0, inorder.length - 1, preorder);

    }

    private TreeNode helper(Map<Integer, Integer> map, int preStart, int inStart, int inEnd, int[] preorder) {
        if (inStart > inEnd) return null;
        TreeNode root = new TreeNode(preorder[preStart]);
        int inIndex = map.get(preorder[preStart]);
        root.left = helper(map, preStart + 1, inStart, inIndex - 1, preorder);
        root.right = helper(map, preStart + inIndex - inStart + 1, inIndex + 1, inEnd, preorder);
        return root;
    }

    public TreeNode constructTree1(int[] inorder, int[] postorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++)
            map.put(inorder[i], i);
        return helper1(map, postorder.length - 1, 0, inorder.length - 1, postorder);
    }

    private TreeNode helper1(Map<Integer, Integer> map, int postEnd, int inStart, int inEnd, int[] postorder) {
        if (inStart > inEnd) return null;
        int inIndex = map.get(postorder[postEnd]);
        TreeNode root = new TreeNode(postorder[postEnd]);

        root.left = helper1(map, postEnd - inEnd - inIndex - 1 - inStart, inStart, inIndex - 1, postorder);
        root.right = helper1(map, postEnd - 1, inIndex + 1, inEnd, postorder);
        return root;
    }

    class TreeLinkNode {
        int val;
        TreeLinkNode left = null;
        TreeLinkNode right = null;
        TreeLinkNode next = null;

        TreeLinkNode(int val) {
            this.val = val;
        }
    }

    /*
    中序遍历的下一个节点, 如果一个节点有右边, 那么下一个节点就是右的最左节点(因为它是父节点)
    如果没有右边,:
    1. 如果节点是父节点的左子节点, 那么下一个节点就是它的父节点
    2. 如果节点是它父节点右子节点, 那么一直沿着父节点的一直向上遍历,直到找到一个是它父节点的左子节点,

     */
    public TreeLinkNode getNext(TreeLinkNode pNode) {
        if (pNode == null) return null;
        TreeLinkNode pNext;
        if (pNode.right != null) {
            TreeLinkNode pRight = pNode.right;
            while (pRight.left != null) pRight = pRight.left;
            pNext = pRight;
        } else {
            TreeLinkNode pCurrent = pNode, pParent = pNode.next;
            while (pParent != null && pCurrent == pParent.right) {
                pCurrent = pParent;
                pParent = pParent.next;
            }
            pNext = pParent;
        }
        return pNext;

    }

    // 用两个栈实现队列
    Stack<Integer> input = new Stack<>();
    Stack<Integer> output = new Stack<>();

    public void offer(int x) {
        input.push(x);
    }

    public int poll() {
        if (output.isEmpty()) {
            while (!input.isEmpty()) output.push(input.pop());
        }
        return output.pop();
    }

    // 斐波那契数列
    public int fibonacci(int n) {
        int f1 = 0, f2 = 1;
        if (n == 0) return f1;
        if (n == 1) return f2;

        for (int i = 2; i <= n; i++) {
            int tmp = f1 + f2;
            f1 = f2;
            f2 = tmp;
        }
        return f2;
    }

    // 青蛙跳台阶, 一次能跳1, 2 台阶, 问到n 台阶, 有几种跳法
    public int jumpFloor(int n) {
        int f1 = 1, f2 = 2;
        if (n == 1) return 1;
        if (n == 2) return 2;
        for (int i = 3; i <= n; i++) {
            int tmp = f1 + f2;
            f1 = f2;
            f2 = tmp;
        }
        return f2;
    }

    // 旋转数组最小数字, 没有重复
    public int getMinInRotateArray(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {  // !!! 相等就停止
            int mid = left + ((right - left) >> 1);
            if (arr[mid] > arr[right]) left = mid + 1;
            else right = mid;
        }
        return arr[right];
    }

    // 旋转数组最小数字, 有重复
    public int getMinInRotateArray1(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (arr[mid] > arr[right]) left = mid + 1;
            else if (arr[mid] < arr[right]) right = mid;
            else right--; // 当相等的时候, 就--
        }
        return arr[right];
    }

    // 旋转数组, 指定数字
    // target 大于小的, 小于大的,,  还有就是right = mid 要等于
    public static int search(int[] nums, int target) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == target) return mid;
            if (nums[left] < nums[mid])
                if (target >= nums[left] && target <= nums[mid]) right = mid; //等号
                else left = mid + 1;
            else if (nums[left] > nums[mid])
                if (target > nums[mid] && target < nums[left]) left = mid + 1;
                else right = mid;
            else left++;
        }
        return -1;
    }

    // 矩阵中的路径, 单词组合
    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        if (matrix == null || rows < 1 || cols < 1 || str == null) return false;
        boolean[] visited = new boolean[matrix.length];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (hasPathCore(matrix, rows, cols, i, j, str, 0, visited))
                    return true;
            }
        }
        return false;
    }

    private boolean hasPathCore(char[] matrix, int rows, int cols,
                                int i, int j, char[] str,
                                int pathLen, boolean[] visited) {
        int index = i * cols + j;
        if (pathLen == str.length) return true;
        if (i >= 0 && i < rows && j >= 0 && j < cols && matrix[i * cols + j] == str[pathLen] && !visited[i * cols + j]) {

            visited[i * cols + j] = true;
            if (hasPathCore(matrix, rows, cols, i, j - 1, str, pathLen + 1, visited) || hasPathCore(matrix, rows, cols, i, j + 1, str, pathLen + 1, visited) || hasPathCore(matrix, rows, cols, i - 1, j, str, pathLen + 1, visited) || hasPathCore(matrix, rows, cols, i + 1, j, str, pathLen + 1, visited))
                return true;
            visited[index] = false;
        }

        return false;
    }

    // 二进制中1 的个数
    public int numof1(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n &= (n - 1);
        }
        return count;
    }

    // 快速幂
    public double power1(double base, int exp) {
        if (base == 0.0) return 0.0;
        double res = 1;
        boolean isNegative = false;
        if (exp < 0) {
            if (exp == Integer.MIN_VALUE)
                exp = -(exp / 2);
            else
                exp = -exp;
            isNegative = true;
        }
        while (exp != 0) {
            if ((exp & 1) == 1) res *= base;
            exp >>= 1;
            base *= base;
        }
        return isNegative ? 1.0 / res : res;
    }

    public double power2(double base, int exp) {
        if (exp == 0) return 1;
        if (exp == Integer.MIN_VALUE)
            return power2(base * base, exp / 2);
        if (exp < 0) {
            base = 1 / base;
            exp = -exp;
        }
        if (exp % 2 == 1) return base * power2(base * base, exp / 2);
        else return power2(base * base, exp / 2);
    }

    // 删除链表节点
    public void delete(ListNode[] pHead, ListNode[] pDel) {
        if (pHead == null || pDel == null) return;
        if (pDel[0].next != null) {
            ListNode pNode = pDel[0].next; // 删除pNode, 替换pDel
            pDel[0].val = pNode.val;
            pDel[0].next = pNode.next;
            pNode.next = null;
        } else if (pDel == pHead) { // 在首个节点
            pDel[0] = null;
            pHead[0] = null;
        } else { //尾节点
            ListNode pNode = pHead[0];
            while (pNode.next != pDel[0]) pNode = pNode.next;
            pNode.next = null;
            pDel[0] = null;
        }
    }

    // 删除重复节点
    public ListNode deleteDuplicates(ListNode head) {
        ListNode pre = null;
        ListNode curr = head;
        while (curr != null) {
            pre = curr;
            curr = curr.next;
            while (curr != null && curr.val == pre.val) curr = curr.next;
            pre.next = curr;
        }
        return head;
    }

    // 不要重复的节点
    public ListNode deleteDuplicates1(ListNode head) {
        ListNode dummy = new ListNode(-1), cur = head, prev = dummy;
        dummy.next = head;
        while (cur != null) {
            cur = cur.next;
            while (cur != null && prev.next.val == cur.val) cur = cur.next;
            if (prev.next.next == cur) prev = prev.next;
            prev.next = cur;
        }
        return dummy.next;
    }

    // 让奇数位于偶数前面
    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public void reorderOddEven(int[] arr) {
        if (arr == null || arr.length < 2) return;
        int left = 0, right = arr.length - 1;
        while (left < right) {
            while (left < right && (arr[left] & 1) == 1) left++;
            while (left < right && (arr[right] & 1) == 0) right++;
            if (left < right) swap(arr, left, right);
        }
    }

    // 链表倒数第k个节点
    public ListNode findKthToTail(ListNode head, int k) {
        if (head == null || k < 1) return null;
        ListNode p1 = head, p2 = head;
        for (int i = 0; i < k - 1 && p1 != null; i++) p1 = p1.next;
        while (p1.next != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p2;
    }

    // 链表是否有环
    private ListNode meetingNode(ListNode head) {
        ListNode slow = head, fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            if (slow == fast) return fast;
            slow = slow.next;
            fast = fast.next.next;

        }
        return null;

    }

    //找出环的入口
    public ListNode findEntry(ListNode head) {
        if (head == null || head.next == null) return null;
        ListNode meetNode = meetingNode(head);
        if (meetNode == null) return null;
        int loop = 1;
        ListNode tmp = meetNode;
        while (tmp.next != meetNode) {
            tmp = tmp.next;
            loop++;
        }
        ListNode fast = head, slow = head;
        for (int i = 0; i < loop; i++) fast = fast.next;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }

    // 反转链表
    public ListNode reverse(ListNode head) {
        ListNode newHead = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = newHead;
            newHead = head;
            head = next;
        }
        return newHead;
    }

    public ListNode reverse1(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode next = head.next;
        ListNode newHead = reverse1(next);
        next.next = head;
        head.next = null; //!!!!
        return newHead;
    }

    // 合并两个有序链表
    public ListNode merge(ListNode head1, ListNode head2) {
        if (head1 == null) return head2;
        if (head2 == null) return head1;
        if (head1.val < head2.val) {
            head1.next = merge(head1.next, head2);
            return head1;
        } else {
            head2.next = merge(head1, head2.next);
            return head2;
        }
    }

    // 树1是否有树2
    // 第一步,在A中找到和树B根节点的值相同的节点R, 第二步, 判断树A中R节点子树和B树是否相同
    public boolean hasSubTree(TreeNode head1, TreeNode head2) {
        if (head1 == null || head2 == null) return false;
        boolean result = false;
        if (head1.val == head2.val) result = sameTree(head1, head2);
        if (!result) result = hasSubTree(head1.left, head2);
        if (!result) result = hasSubTree(head1.right, head2);
        return result;
    }

    private boolean sameTree(TreeNode head1, TreeNode head2) {
        if (head1 == null && head2 == null) return true;
        if (head1 == null || head2 == null) return false;
        if (head1.val == head2.val) return sameTree(head1.left, head2.left) && sameTree(head1.right, head2.right);
        return false;
    }

    //返回树的镜像
    // 对于每个根节点, 交换左右子树
    public void mirror(TreeNode head) {
        if (head == null) return;
        TreeNode left = head.left;
        TreeNode right = head.right;
        head.left = right;
        head.right = left;
        if (left != null) mirror(left);
        if (right != null) mirror(right);
    }

    // 树是以中间为镜像的
    public boolean isSymmetrical(TreeNode head) {
        return isSymmetrical(head, head);
    }

    private boolean isSymmetrical(TreeNode head1, TreeNode head2) {
        if (head1 == null && head2 == null) return true;
        if (head1 == null || head2 == null) return false;
        if (head1.val != head2.val) return false;
        return isSymmetrical(head1.left, head2.right) && isSymmetrical(head1.right, head2.left);
    }

    // 顺时针打印矩阵
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        if (matrix.length == 0 || matrix[0].length == 0) return ans;
        int top = 0;   // including, need to be handled
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;

        while (true) {
            for (int i = left; i <= right; i++) ans.add(matrix[top][i]);
            top++;
            if (left > right || top > bottom) break;
            for (int i = top; i <= bottom; i++) ans.add(matrix[i][right]);
            right--;
            if (left > right || top > bottom) break;
            for (int i = right; i >= left; i++) ans.add(matrix[bottom][i]);
            bottom++;
            if (left > right || top > bottom) break;
            for (int i = bottom; i >= top; i++) ans.add(matrix[i][left]);
            left++;
            if (left > right || top > bottom) break;
        }
        return ans;
    }

    // mini 栈
    private LinkedList<Integer> dataStack = new LinkedList<>();
    private LinkedList<Integer> minStack = new LinkedList<>();

    public void push(int num) {
        dataStack.push(num);
        if (minStack.isEmpty()) minStack.push(num);
        else minStack.push(Math.min(minStack.peek(), num));
    }

    public int pop() {
        minStack.pop();
        return dataStack.pop();
    }

    public int min() {
        return minStack.peek();
    }

    public ArrayList<Integer> boardFirst(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) return res;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            res.add(node.val);
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
        return res;
    }

    //分行从上到下打印二叉树
    public ArrayList<ArrayList<Integer>> RowbyRow(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> layerList = new ArrayList<>();
        if (pRoot == null) return layerList;

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(pRoot);
        TreeNode last = pRoot, nextLast = pRoot;

        ArrayList<Integer> layer = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode pNode = queue.poll();
            layer.add(pNode.val);
            if (pNode.left != null) {
                queue.offer(pNode.left);
                nextLast = pNode.left;
            }
            if (pNode.right != null) {
                queue.offer(pNode.right);
                nextLast = pNode.right;
            }
            if (pNode == last) {
                last = nextLast;
                layerList.add(layer);
                layer = new ArrayList<>();
            }
        }
        return layerList;
    }

    // 之字形打印二叉树
    public ArrayList<ArrayList<Integer>> print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> layerList = new ArrayList<>();
        if (pRoot == null) return layerList;

        LinkedList[] levels = new LinkedList[2];
        levels[0] = new LinkedList<TreeNode>();
        levels[1] = new LinkedList<TreeNode>();
        int current = 0, next = 1;
        levels[current].push(pRoot);

        ArrayList<Integer> layer = new ArrayList<>();
        while (!levels[0].isEmpty() || !levels[1].isEmpty()) {
            TreeNode pNode = (TreeNode) levels[current].pop();
            layer.add(pNode.val);

            if (current == 0) {
                if (pNode.left != null) levels[next].push(pNode.left);
                if (pNode.right != null) levels[next].push(pNode.right);
            } else {
                if (pNode.right != null) levels[next].push(pNode.right);
                if (pNode.left != null) levels[next].push(pNode.left);
            }

            if (levels[current].isEmpty()) {
                layerList.add(layer);
                layer = new ArrayList<>();
                current = 1 - current;
                next = 1 - next;
            }
        }
        return layerList;
    }

    // 判断合法二叉搜索树的后序遍历
    public static boolean verifySequenceOfBST(int[] sequence) {
        if (sequence == null || sequence.length == 0) return false;
        return verifyCore(sequence, 0, sequence.length - 1);
    }

    private static boolean verifyCore(int[] sequence, int left, int right) {
        if (left > right) return true;
        int root = sequence[right], startOfRight = left;
        while (startOfRight < right && sequence[startOfRight] < root) startOfRight++;
        for (int i = startOfRight; i < right; i++) if (sequence[i] < root) return false;
        return verifyCore(sequence, left, startOfRight - 1) && verifyCore(sequence, startOfRight, right - 1);
    }

    // 是否存在从根节点到叶子节点路径和为某值
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        if (root.left == null && root.right == null && sum - root.val == 0) return true;
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    // 找出从根节点到叶子节点路径和为某值
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList();
        pathSum(res, root, new ArrayList<Integer>(), sum);
        return res;
    }

    private void pathSum(List<List<Integer>> res, TreeNode root, List<Integer> tmp, int leftSum) {
        if (root == null) return;
        tmp.add(root.val);
        if (root.left == null && root.right == null && leftSum == root.val) res.add(new ArrayList<Integer>(tmp));
        pathSum(res, root.left, tmp, leftSum - root.val);
        pathSum(res, root.right, tmp, leftSum - root.val);
        tmp.remove(tmp.size() - 1);
    }

    // 树中存在和为sum 的路径
    public int pathSumiii(TreeNode root, int sum) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        return pathSumiii(root, 0, sum, map);

    }

    private int pathSumiii(TreeNode root, int currSum, int target, Map<Integer, Integer> preSUm) {
        if (root == null) return 0;
        currSum += root.val;
        int res = preSUm.getOrDefault(currSum - target, 0);
        preSUm.put(currSum, preSUm.getOrDefault(currSum, 0) + 1);
        res += pathSumiii(root.left, currSum, target, preSUm) + pathSumiii(root.right, currSum, target, preSUm);
        preSUm.put(currSum, preSUm.get(currSum) - 1);
        return res;
    }

    // 出现次数超过一半数字
    public static int moreThanHalfNum(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int result = arr[0];
        int times = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == result) times++;
            else if ((--times) == 0) {
                result = arr[i];
                times = 1;
            }
        }
        return result;
    }

    // 最小的k个数
    public ArrayList<Integer> getLeastNumbers(int[] input, int k) {
        ArrayList<Integer> res = new ArrayList<>();
        if (input == null || input.length == 0 || k <= 0 || k > input.length) return res;
        int left = 0, right = input.length - 1;
        int index = partition2(input, left, right);
        while (index != k - 1) {
            if (index > k - 1) right = index - 1;
            else left = index + 1;
            index = partition2(input, left, right);
        }
        for (int i = 0; i < k; i++) res.add(input[i]);
        return res;
    }

    public int partition2(int[] arr, int left, int right) {
        int i = left, j = right, base = arr[left], tmp;
        while (i < j) {
            while (arr[j] >= base && i < j) j--;
            while (arr[i] <= base && i < j) i++; // 因为是小于base的, 所以最后以i下标的交换
            if (i < j) {
                tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
        }
        arr[left] = arr[i];
        arr[i] = base;
        return i;
    }

    // 数据流中的中位数
    private int count = 0;
    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);

    public void insert(int num) {
        if (count % 2 == 1) {
            minHeap.offer(num);
            maxHeap.offer(minHeap.poll());
        } else { // 第一次往minHeap中加入
            maxHeap.offer(num);
            minHeap.offer(maxHeap.poll());
        }
        count++;
    }

    public Double getMedian() {
        return (count % 2 == 0) ? (maxHeap.peek() + minHeap.peek()) / 2.0 : 1.0 * (minHeap.peek());
    }

    // 子数组和最大
    public int maxofSubArray(int[] arr) {
        int curSum = arr[0];
        int maxSum = curSum;
        for (int i = 1; i < arr.length; i++) {
            if (curSum < 0) curSum = arr[i];
            else curSum += arr[i];
            maxSum = Math.max(curSum, maxSum);
        }
        return maxSum;
    }

    // 从1到n整数中1出现的次数
    public static int countNumberOfOne(int n) {
        if (n < 1) return 0;
        int counter = 0, base = 1, round = n;
        while (round > 0) {
            int weight = round % 10;
            round /= 10;
            counter += round * base;
            if (weight == 1) counter += (n % base + 1);
            else if (weight > 1) counter += base;
            base *= 10;
        }
        return counter;
    }

    //把数组排成最小的数

    public static String printMinNumber(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
        StringBuilder sb = new StringBuilder();
        for (int e : arr) sb.append(e);
        return sb.toString();
    }

    private static void swap1(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private static boolean compare(String a, String b) {
        String ab = a + b, ba = b + a;
        return ab.compareTo(ba) < 0;
    }

    private static int partition(int[] arr, int left, int right) {
        int j = left - 1;
        for (int i = left; i < right; i++)
            if (compare(String.valueOf(arr[i]), String.valueOf(arr[right])))
                swap1(arr, i, ++j);
        swap1(arr, right, ++j);
        return j;
    }

    private static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int index = partition(arr, left, right);
            quickSort(arr, left, index - 1);
            quickSort(arr, index + 1, right);
        }
    }

    // 快速排序

    public static void sort(int[] a, int left, int right) {
        if (left >= right) return;
        int i = partition1(a, left, right);
        sort(a, left, i - 1);
        sort(a, i , right);
    }

    private static int partition1(int[] a, int left, int right) {
       int pivot = a[(left + right) /2];
       while(left <= right){
           while(a[left] < pivot) left++;
           while(a[right] > pivot) right--;

           if(left <= right){
               exch(a,left,right);
               left++;
               right--;
           }

       }
        return left;
    }
    private static void exch(int[] a, int left, int right) {
        int temp = a[left];
        a[left] = a[right];
        a[right] = temp;

    }

    // 得到第n个丑数
    public static int getUglyNumber(int n){
        if(n < 1) return 0;
        int[] arr = new int[n];
        arr[0] = 1;
        int t2 = 0, t3 = 0, t5 = 0;
        for (int i = 1; i < n; i++){
            int m2 = arr[t2] * 2, m3 = arr[t3] * 3, m5 = arr[t5] * 5;
            arr[i] = Math.min(Math.min(m2, m3), m5);
            while (arr[t2] * 2 <= arr[i]) t2++;
            while (arr[t3] * 3 <= arr[i]) t3++;
            while (arr[t5] * 5 <= arr[i]) t5++;
        }
        return arr[n-1];
    }




    // 在有序数组中返回一个数的起始下标
    public int[] searchRange(int[] nums, int target) {
        int left = helper1(nums, target);
        if(left == nums.length || nums[left] != target  ) return new int[]{-1,-1};
        return new int[]{left, helper1(nums,target +1 )-1};
    }

    private int helper1(int[] nums, int target){
        int left = 0, right = nums.length-1 ;
        while(left <= right){
            int mid = left + ((right - left) >>1);
            if(nums[mid] >=target) right = mid -1;
            else left =mid +1;
        }
        return left;
    }

    // 二叉搜索树第k大的节点 (中序遍历递增)
    private static int number = 0;
    private static int count1 = 0;

    public int kthSmallest(TreeNode root, int k) {
        count1 = k;
        helper(root);
        return number;
    }

    public void helper(TreeNode n) {
        if (n.right != null) helper(n.right);
        count1--;
        if (count1 == 0) {
            number = n.val;
            return;
        }
        if (n.left != null) helper(n.left);
    }

    // 二叉树深度
    public static int treeDepth(TreeNode root){
        if (root == null) return 0;
        return Math.max(treeDepth(root.left) + 1, treeDepth(root.right) + 1);
    }

    // 平衡二叉树
    private static int getDepth(TreeNode root){
        if (root == null) return 0;
        int left = getDepth(root.left);
        if (left == -1) return -1;
        int right = getDepth(root.right);
        if (right == -1) return -1;
        return Math.abs(left - right) > 1 ? -1 : Math.max(left, right) + 1;
    }

    // 和为S的两个数字
    private static boolean findNumbers(int[] arr, int[] num1, int[] num2, int sum) {
        if (arr == null || arr.length < 1 || num1 == null || num2 == null)
            return false;
        int left = 0, right = arr.length - 1;
        while (left < right) {
            long curSum = arr[left] + arr[right];
            if (curSum == sum) {
                num1[0] = arr[left];
                num2[0] = arr[right];
                return true;
            } else if (curSum > sum) right--;
            else left++;
        }
        return false;
    }

    public static ArrayList<Integer> findNumbersWithSum(int[] arr, int sum) {
        ArrayList<Integer> numsList = new ArrayList<>(2);
        int[] num1 = new int[1], num2 = new int[1];
        if (findNumbers(arr, num1, num2, sum)) {
            numsList.add(num1[0]);
            numsList.add(num2[0]);
        }
        return numsList;
    }

    //和为S的连续正数序列
    private static ArrayList<Integer> getSequence(int small, int big){
        ArrayList<Integer> sequence = new ArrayList<>();
        for (int i = small; i <= big; i++) sequence.add(i);
        return sequence;
    }

    public static ArrayList<ArrayList<Integer>> findContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> sequencesList = new ArrayList<>();
        if (sum < 3) return sequencesList;
        int small = 1, big = 2, mid = sum / 2;
        int curSum = small + big;
        while (small <= mid){
            if (curSum == sum){
                sequencesList.add(getSequence(small, big));
                curSum += (++big);
            } else if (curSum > sum) curSum -= (small++);
            else curSum += (++big);
        }
        return sequencesList;
    }
    //左旋转字符串
    private static void reverse(char[] arr, int left, int right){
        while (left < right){
            char tmp = arr[left];
            arr[left++] = arr[right];
            arr[right--] = tmp;
        }
    }

    public static String leftRotateString(String str, int n){
        if (str == null || n < 0 || n > str.length()) return "";
        char[] arr = str.toCharArray();
        reverse(arr, 0, n - 1);
        reverse(arr, n, arr.length - 1);
        reverse(arr, 0, arr.length - 1);
        return new String(arr);
    }




    public static boolean isBalanced(TreeNode root){
        return getDepth(root) != -1;
    }








    public static void main(String[] args) {
//        System.out.println(verifySequenceOfBST(new int[]{5,7,6,9,11,10,8}));
//        Big b = new Big();
//        b.insert(1);
//        b.insert(2);
//        b.insert(3);
//        b.insert(4);
//        b.insert(5);
//        System.out.println(b.getMedian());
        int[] a = new int[]{15,3,9,8,5,2,7,1,6};
        sort(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
    }


}


