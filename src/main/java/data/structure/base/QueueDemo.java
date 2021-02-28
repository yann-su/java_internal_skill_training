package data.structure.base;



import java.util.LinkedList;
import java.util.Queue;

public class QueueDemo {

    public static void main(String[] args) {
       Queue<Integer> queue =  new LinkedList<>();
       queue.add(1);
       queue.add(2);
       queue.add(3);
       queue.add(4);
       queue.add(5);
       queue.add(6);
       queue.add(7);
       Integer poll = queue.poll();
       Integer poll1 = queue.poll();
       Integer pol2 = queue.poll();
       Integer poll3 = queue.poll();
       System.out.println(poll);
       System.out.println(poll1);
       System.out.println(pol2);
       System.out.println(poll3);
       System.out.println(queue);



    }

}
