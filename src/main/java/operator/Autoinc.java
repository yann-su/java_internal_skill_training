package operator;

public class Autoinc {


    public static void main(String[] args) {
        int i = 0;
        System.out.println(i++); //返回值
        System.out.println(i);
        int j = 0;
        System.out.println(++j);  //先返回值，然后再
        System.out.println(j);
    }
}
