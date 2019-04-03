package linkedlist;

import linkedlist.list.ListNode;

import java.util.HashMap;
import java.util.Map;

public class ListOperation {

    // 从链表中删除重复数据
    public static void deleteduplicate(ListNode head){
        Map<Integer, Integer> map = new HashMap<>();
        ListNode tmp = head;
        ListNode pre = null;
        while(tmp != null){
            if(map.containsKey(tmp.val)){
                pre.next = tmp.next;
            }
            else{
                map.put(tmp.val, 1);
                pre = tmp;
            }
            tmp = tmp.next;
        }

    }

    // 找出单链表中的倒数第K 个元素
    public static ListNode findElem(ListNode head, int k){
        if(k < 1)
            return null;
        ListNode fast = head;
        ListNode slow = head;
        for(int i = 0; i < k-1 && fast != null; i++)
            fast = fast.next;
        if(fast == null) return null;
        while(fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }
        return slow;

    }

    // 反转链表
    public static ListNode reverse(ListNode head){
        ListNode newHead = null;
        ListNode cur = head;
        while(cur != null){
            ListNode next = cur.next;
            cur.next = newHead;
            newHead = cur;
            cur = next;
        }
        return newHead;
    }

    public static ListNode reverseRecursive(ListNode head){
        if(head == null || head.next == null) return head;
        ListNode next = head.next;
        ListNode newHead = reverseRecursive(next);
        next.next = head;
        head.next = null;
        return newHead;
    }

    // 从后往前输出单链表
    public static void printList(ListNode head){
        if(head != null){
            printList(head.next);
            System.out.println(head.val);
        }
    }

    // 寻找中间结点
    public static ListNode searchMid(ListNode head){
        ListNode fast = head;
        ListNode slow = head;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    // 检测是否有还
    public boolean isLoop(ListNode head){
        ListNode fast = head;
        ListNode slow = head;
        if(fast == null){
            return false;
        }
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow)
                return true;
        }
        return false;
    }

    // 入口
    public ListNode findLoopPort(ListNode head){
        ListNode slow = head, fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) break;
        }

        if(fast == null || fast.next == null)
            return null;
        slow = head;
        while(slow != fast){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    // 删除指定节点
    public boolean deleteNode(ListNode n){
        if(n == null || n.next == null) return false;
        n.val = n.next.val;
        n.next = n.next.next;
        return true;
    }



}
