package safety;

public class ObRunnable02 implements Runnable {
    Room room = null;

    public ObRunnable02(Room room) {
        this.room = new Room();
    }

    @Override
    public void run() {
        for (int i = 0; i < 5000000; i++) {
            room.decrement();
        }
    }
}
