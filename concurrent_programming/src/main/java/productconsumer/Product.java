package productconsumer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import java.util.LinkedList;

public class Product {

    public static void main(String[] args) {

        MessageQueue messageQueue = new MessageQueue(2);

        for (int i = 0; i < 3; i++) {
            int id  = i;
            new Thread(()->{
                messageQueue.put(new Message(id, "值"+id));
            },"product "+i).start();
        }

        new Thread(()->{
            while (true){
                messageQueue.take();
            }
        },"消费者").start();

    }


}
@Slf4j
class MessageQueue{

    private LinkedList<Message> linkedList = new LinkedList();

    private int capacity;

    public MessageQueue(int capacity){
        this.capacity = capacity;
    }

    public Message take()  {
        synchronized (linkedList){
            while (linkedList.isEmpty()){
                try {
                    log.info("queue is null consumer thread waiting");
                    linkedList.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //
            Message message = linkedList.removeFirst();
            log.info("消费"+message+"");
            linkedList.notifyAll();
            return message;
        }
    }

    public void put(Message message){
        synchronized (linkedList){
            //检查队列是否已经满了
            while (linkedList.size() == capacity){
                try {
                    log.info("queue is full product waiting");
                    linkedList.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //将消息加入尾部
            log.info(message+"");
            linkedList.addLast(message);
            linkedList.notifyAll();
        }
    }



}


@AllArgsConstructor
@ToString
@Data
final class Message{
    private int id;
    private Object value;

}