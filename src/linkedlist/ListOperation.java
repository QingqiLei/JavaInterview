package linkedlist;

import linkedlist.list.Node;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class ListOperation {

    // 从链表中删除重复数据
    public static void deleteduplicate(Node head){
        Map<Integer, Integer> map = new HashMap<>();
        Node tmp = head;
        Node pre = null;
        while(tmp != null){
            if(map.containsKey(tmp.data)){
                pre.next = tmp.next;
            }
            else{
                map.put(tmp.data, 1);
                pre = tmp;
            }
            tmp = tmp.next;
        }

    }

    // 找出单链表中的倒数第K 个元素
    public static Node findElem(Node head, int k){
        if(k < 1)
            return null;
        Node fast = head;
        Node slow = head;
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
    public static Node reverse(Node head){
        Node newHead = null;
        Node cur = head;
        while(cur != null){
            Node next = cur.next;
            cur.next = newHead;
            newHead = cur;
            cur = next;
        }
        return newHead;
    }

    public static Node reverseRecursive(Node head){
        if(head == null || head.next == null) return head;
        Node next = head.next;
        Node newHead = reverseRecursive(next);
        next.next = head;
        head.next = null;
        return newHead;
    }

    // 从后往前输出单链表
    public static void printList(Node head){
        if(head != null){
            printList(head.next);
            System.out.println(head.data);
        }
    }

    // 寻找中间结点
    public static Node searchMid(Node head){
        Node fast = head;
        Node slow = head;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    // 检测是否有还
    public boolean isLoop(Node head){
        Node fast = head;
        Node slow = head;
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
    public Node findLoopPort(Node head){
        Node slow = head, fast = head;
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
    public boolean deleteNode(Node n){
        if(n == null || n.next == null) return false;
        n.data = n.next.data;
        n.next = n.next.next;
        return true;
    }



}
