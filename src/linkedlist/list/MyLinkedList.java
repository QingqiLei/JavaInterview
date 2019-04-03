package linkedlist.list;

public class MyLinkedList {
    ListNode head = null;

    public void addNode(int d) {
        ListNode newNode = new ListNode(d);
        if (head == null) {
            head = newNode;
            return;
        }
        ListNode tmp = head;
        while (tmp.next != null)
            tmp = tmp.next;
        tmp.next = newNode;
    }

    public Boolean deleteNode(int index) {
        if (index < 1 || index > length()) return false;

        if (index == 1) {
            head = head.next;
            return true;
        }

        int i = 2;
        ListNode preNode = head;
        ListNode curNode = preNode.next;
        while(curNode != null){
            if(i== index){
                preNode.next = curNode.next;
                return true;
            }
            preNode = curNode;
            curNode = curNode.next;
            i++;
        }
        return true;
    }

    public int length() {
        int length = 0;
        ListNode tmp = head;
        while (tmp != null) {
            length++;
            tmp = tmp.next;
        }
        return length;
    }
}
