package entity;

public class ListNode {

    public ListNode next;

    public int val;

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "next=" + next +
                ", val=" + val +
                '}';
    }
}
