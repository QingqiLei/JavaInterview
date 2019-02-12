package linkedlist.list;

public class MyLinkedList {
    Node head = null;

    public void addNode(int d) {
        Node newNode = new Node(d);
        if (head == null) {
            head = newNode;
            return;
        }
        Node tmp = head;
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
        Node preNode = head;
        Node curNode = preNode.next;
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
        Node tmp = head;
        while (tmp != null) {
            length++;
            tmp = tmp.next;
        }
        return length;
    }
}
