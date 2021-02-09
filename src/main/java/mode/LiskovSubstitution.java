package mode;

//里氏替换原则是使用了java 多态，入参时候使用父类，成员方法编译时候看左边，运行时候看右边的原则
//里氏替换原则上讲子类对象介绍给父类对象，也就是子类可以替换父类的，而反过来父类不能替换子类
public class LiskovSubstitution {
    public static void main(String[] args) {

        Person01 person01 = new Person01();
        display(person01);

        Man man = new Man();
        display(man);


    }

    public static void display(Person01 person01){
        person01.display();
    }

}

class Person01{
    public void display(){
        System.out.println("this is person");
    }
}

class Man extends Person01{


    @Override
    public void display() {
        System.out.println("this is man");
    }
}
