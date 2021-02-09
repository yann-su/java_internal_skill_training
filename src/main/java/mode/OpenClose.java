package mode;


// 开闭原则，对于功能应该关闭，对于扩展功能应该开放，使用继承限制住
public class OpenClose {

    public static void main(String[] args) {
        Person02 person021 = new Person02("张三",19);
        Person02 person022 = new Person02("李四",19);
        ComparePerson comparePerson = new ComparePerson();
        comparePerson.setData(person021,person022);
        System.out.println(comparePerson.getMax());


        House house1 = new House(10,19,"泉州");
        House house2 = new House(10,21,"厦门");
        CompareHouse compareHouse = new CompareHouse();
        compareHouse.setData(house1,house2);
        System.out.println(compareHouse.getMax());



    }
}

class House{

    int width;
    int length;
    String addr;

    public House(int width, int length, String addr) {
        this.width = width;
        this.length = length;
        this.addr = addr;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public int getSquare() {
        return width * length;
    }


    @Override
    public String toString() {
        return "House{" +
                "width=" + width +
                ", length=" + length +
                ", addr='" + addr + '\'' +
                '}';
    }
}

class Person02{

    private String name;
    private int age;


    public Person02(String name,int age){
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person02{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

abstract class CompareObject{

    protected Object object1;
    protected Object object2;

    public void setData(Object arg1,Object arg2){
        if ( isAccept(arg1,arg2) ){
            object1 = arg1;
            object2 = arg2;
        }else {
            throw new RuntimeException(arg1 + " and "+arg2 + " isn't the them type ");
        }

    }

    protected abstract boolean isAccept(Object arg1,Object arg2);

    public abstract Object getMax();

    public abstract Object getMin();


}


class ComparePerson extends CompareObject{

    @Override
    protected boolean isAccept(Object arg1, Object arg2) {
        if((arg1 instanceof Person02) && (arg2 instanceof Person02)){
            return true;
        }
        return false;
    }

    @Override
    public Object getMax() {
        Person02 person021 = (Person02) this.object1;
        Person02 person022 = (Person02) this.object2;
        if (person021.getAge() > person022.getAge()){
            return person021;
        }
        return person022;
    }

    @Override
    public Object getMin() {
        if (((Person02) object1).getAge() > ((Person02) object2).getAge()){
            return (Person02) object1;
        }
        return (Person02) object2;
    }
}


class CompareHouse extends CompareObject {

    @Override
    protected boolean isAccept(Object arg1, Object arg2) {
        if ((arg1 instanceof House) && (arg2 instanceof House)) {
            return true;
        }
        return false;
    }

    @Override
    public Object getMax() {
        if (((House) object1).getSquare() > ((House) object2).getSquare()) {
            return (House) object1;
        }
        return (House) object2;
    }

    @Override
    public Object getMin() {
        if (((House) object1).getSquare() > ((House) object2).getSquare()) {
            return (House) object2;
        }
        return (House) object1;
    }

    public Object getCheaper() {

        return null;
    }

}


