package obj;

public class Test {

    //多态的表现，
    public static void main(String[] args) {
        Animal animal = null;

        //成员方法，编译时看左边，运行时看右边
        animal = new Cat();
        animal.run();
        animal = new Dog();
        animal.run();
        //成员字段 编译看左边，运行还看左边
        System.out.println(animal.name);


    }
}
