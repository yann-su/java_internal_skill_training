package data.structure.linked;

/**
 * 链表操作
 *
 * @param <Data>
 */
public class LinkedList<Data> {

    private class Node<Data> {
        //定义数据类型
        Data data;
        //定义
        Node<Data> next;

        public Node(Data data) {
            this.data = data;
        }
    }

    private Node head;

    private Node last;

    private int size;

    public Node get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("超出链表节点范围");
        }
        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    private void addLast(Data data) {
        Node insertNode = new Node(data);
        if (size == 0) {
            head = insertNode;
            last = insertNode;
        } else {
            last.next = insertNode;
            last = insertNode;
        }
        size++;
    }

    private void deleteLast() {
        //当判断只有一位数 Head的时候，进行删除操作的时候，可以直接将head和last直接置空
        if (size <= 0) {
            throw new IndexOutOfBoundsException("链表数据已经为空");
        } else if (size == 1) {
            head = null;
            last = null;
        } else {
            Node<Data> node = get(size - 2);
            node.next = null;
            last = node;
        }
        size--;
    }


    private void insert(Data data, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("超出链表节点范围");
        }
        Node insertNode = new Node(data);
        //前两个方法是用来进行插入操作
        if (size == 0) {
            head = insertNode;
            last = insertNode;
        } else if (index == size) {
            last.next = insertNode;
            last = insertNode;
        } else if (index == 0) {
            insertNode.next = head;
            head = insertNode;
        } else {
            Node prevNode = get(index - 1);
            insertNode.next = prevNode.next;
            prevNode.next = insertNode;
        }
        size++;
    }

    // TODO: 2021/2/23 递归思想代码，反复研究，增加自身对递归的理解
    /** 
     * 使用递归对单链表进行翻转（思想很重要，反复看）
     *    递归表达
     *    1 -> 2 -> 3 -> 4 -> null
     *    1 <- 2 <- 3 <- 4 <- last
     *  |    |    |     |
     *null  null  null  null
     * @return Node last(临时变量)
     */
    public Node<Data> reverse(Node head){
        if (head.next == null) return head;
        Node last = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }


    Node successsor = null;
    public Node<Data> reverseN(Node head, int n){
        if (n == 1){
            successsor = head.next;
            return head;
        }
        Node last = reverseN(head.next,n - 1);
        head.next.next = head;
        head.next = successsor;
        return last;
    }

    //利用前进到
    public Node<Data> reverseBetween(Node head, int m, int n) {
        // base case
        if (m == 1) {
            return reverseN(head, n);
        }
        // 前进到反转的起点触发 base case,分成一个子问题
        head.next = reverseBetween(head.next, m - 1, n - 1);
        return head;
    }

    public void print() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + "-->");
            temp = temp.next;
        }
        System.out.println("null");
    }

    public void printFor() {

        for (Node p = head; p != null; p = p.next) {
            System.out.print(p.data + "->");
        }
        System.out.print("null");

    }


    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList();
        linkedList.addLast(1);
        linkedList.addLast(2);
        linkedList.addLast(3);
        linkedList.addLast(4);
//        linkedList.deleteLast();

//        linkedList.deleteLast();
        linkedList.printFor();
        System.out.println();
        linkedList.head = linkedList.reverse(linkedList.head);
        linkedList.printFor();
        System.out.println();
        linkedList.head = linkedList.reverseN(linkedList.head,2);
        linkedList.printFor();
        System.out.println();
        linkedList.head = linkedList.reverseBetween(linkedList.head,2,3);
        linkedList.printFor();


    }


}
