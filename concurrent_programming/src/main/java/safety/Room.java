package safety;

public class Room {
    private volatile int counter = 0;

    public void increment(){
        synchronized (this){
            counter ++;
        }
    }

    public void decrement(){
        synchronized (this){
            counter --;
        }
    }

    public int getCounter(){
        synchronized (this){
            return counter;
        }
    }

}
