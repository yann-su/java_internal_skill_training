package cas;
import sun.misc.Unsafe;

public class MyAtomicInterger {

    private volatile int value;
    private static final long valueOffset;

    static final Unsafe UNSAFE;
    static {
        UNSAFE = UnsafeAccessor.getUnsafe();
        try {
            valueOffset =  UNSAFE.objectFieldOffset(MyAtomicInterger.class.getDeclaredField("value"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public int getValue(){
        return value;
    }


    //原子操作，实际简单
    public void decrement(int amount){
        while (true){
            int prev = this.value;
            int next = prev - amount;
            if (UNSAFE.compareAndSwapInt(this,valueOffset,prev,next)) {
                break;
            }
        }
    }




}
