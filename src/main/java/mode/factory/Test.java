package mode.factory;

public class Test {
    public static void main(String[] args) {

        IWorkFactory iWorkFactory = new StudentWorkFactory();

        Work work = iWorkFactory.getWork();
        work.doWork();

        IWorkFactory iWorkFactory1 = new TeacherWorkFactory();
        iWorkFactory1.getWork().doWork();


    }

}
