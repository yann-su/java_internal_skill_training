package mode;



interface Interface1 {
    void operator1();
}

interface Interface2{
    void operator2();
    void operator3();
}

interface Interface3{
    void operator4();
    void operator5();
}

class B implements Interface1,Interface2{

    @Override
    public void operator1() {
        System.out.println("B 实现了operator1");
    }

    @Override
    public void operator2() {
        System.out.println("B 实现了operator2");
    }

    @Override
    public void operator3() {
        System.out.println("B 实现了operator3");
    }
}

class D implements Interface1,Interface3{
    @Override
    public void operator1() {
        System.out.println("D 实现了operator1");
    }

    @Override
    public void operator4() {
        System.out.println("D 实现了operator4");
    }

    @Override
    public void operator5() {
        System.out.println("D 实现了operator5");
    }
}

class A {
    public void dependent1(Interface1 interface1){
        interface1.operator1();
    }
    public void dependent2(Interface2 interface2){
        interface2.operator2();
    }
    public void dependent3(Interface2 interface2){
        interface2.operator3();
    }
}

class C {
    public void dependent1(Interface1 interface1){
        interface1.operator1();
    }
    public void dependent2(Interface2 interface2){
        interface2.operator2();
    }
    public void dependent3(Interface2 interface2){
        interface2.operator3();
    }
}


public class Segregation {
    public static void main(String[] args) {
        A a = new A();
        a.dependent1(new B()); //A通过接口去依赖B
        a.dependent2(new B());
        a.dependent3(new B());
        C c = new C();


    }
}

