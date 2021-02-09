package mode;


public class Singleton {
    public static void main(String[] args) {
        President president = President.getInstance();
    }

}

class LazySingleton {

    private static volatile LazySingleton instance = null;

    private LazySingleton(){
        //避免类在外部被实例化
    }

    public static synchronized LazySingleton getInstance(){
        if (instance == null){
            instance = new LazySingleton();
        }
        return instance;
    }

}

class HungrySingleton {

    private static final HungrySingleton instance = new HungrySingleton();

    private HungrySingleton(){

    }

    public static HungrySingleton getInstance() {
        return instance;
    }
}

class President{

    private static volatile President president = null;

    private President(){

    }

    public static synchronized President getInstance(){

        if (president == null){
            president = new President();
        }else {
            System.out.println("已经初始化总统对象");
        }
        return president;
    }

    public void getName(){
        System.out.println("我是美国总统: 特朗普");

    }



}


/**
 * 使用枚举进行创建单例对象
 */
class EnumSingleton{

    private EnumSingleton(){
    }

    public static EnumSingleton getInstance(){
        return Singleton.INSTANCE.getSingleton();
    }

    private enum Singleton{
        INSTANCE;

        private EnumSingleton singleton;

        Singleton(){
            singleton = new EnumSingleton();
        }
        public EnumSingleton getSingleton(){
            return singleton;
        }
    }


}
