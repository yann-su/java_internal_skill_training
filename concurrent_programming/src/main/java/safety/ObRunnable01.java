package safety;

public class ObRunnable01 implements Runnable{
    Room room = null;

    public ObRunnable01(Room room){
        this.room = new Room();
    }
    
    @Override
    public void run() {
        for (int i = 0; i < 5000000;i++){
            room.increment();
        }
    }
}
