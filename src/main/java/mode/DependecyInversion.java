package mode;


//依赖转换模式，使用多态，屏蔽信息
interface IReceiver{
    String getMsg();
}

class Email implements IReceiver{
    private String msg;

    public Email(String msg) {
        this.msg = msg;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}

class Wechat implements IReceiver{

    private String msg;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Wechat(String msg) {
        this.msg = msg;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}

class Person{

    public static void main(String[] args) {
        Wechat wechat = new Wechat("hi");
        wechat.setName("zhangsan");
        Email email = new Email("jee,hi");
        Person person = new Person();
        person.receive(wechat);
        person.receive(email);
    }

    public void receive(IReceiver iReceiver){
        System.out.println(iReceiver.getMsg());
    }


}



