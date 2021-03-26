package obj;

public class Student extends Person{

    int score;
    //用于测试Heap的堆内存情况
//    private byte []big = new byte[1024*1024*10];

    public Student(String name, int age, int score) {
        super(name, age);
        this.score = score;
    }

    public Student() {
    }

    public static void main(String[] args) {
        Student student = new Student();
        System.out.println(student.age);
    }
}
