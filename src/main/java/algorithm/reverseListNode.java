package algorithm;

import data.structure.linked.LinkedList;
import entity.ListNode;

public class reverseListNode {

    public static ListNode reverse(ListNode head){
        if (head.next == null) return head;
        ListNode last = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(1,new ListNode(2,new ListNode(3,null)));

        ListNode reverse = reverse(node);

    }
}
