package obj;

public class Student extends Person{

    int score;

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
