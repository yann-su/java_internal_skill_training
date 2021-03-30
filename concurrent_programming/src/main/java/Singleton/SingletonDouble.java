package Singleton;

public class SingletonDouble {


    private SingletonDouble(){
    }

    private static volatile SingletonDouble INSTANCE = null;

    public static SingletonDouble getINSTANCE() {
        if (INSTANCE == null){
            synchronized (SingletonDouble.class){
                if (INSTANCE == null){
                    INSTANCE = new SingletonDouble();
                }
            }
        }
        return INSTANCE;
    }
}
