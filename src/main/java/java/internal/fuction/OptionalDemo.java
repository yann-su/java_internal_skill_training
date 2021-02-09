package java.internal.fuction;

import java.util.Optional;


/**
 * 使用这个类的目的是为了方式空指针防止数据污染，早期由谷歌提出，并在之后的版本中加入
 */
public class OptionalDemo {


    public static void main(String[] args) {
        Girl girl = new Girl("xiaohua");
        girl = null;
        Optional<Girl> optionalGirl = Optional.ofNullable(girl);
        System.out.println(optionalGirl);
        boolean empty = optionalGirl.isEmpty();
        System.out.println(empty);
        System.out.println(optionalGirl.isPresent());
        Girl girl1 = optionalGirl.orElse(girl);
        System.out.println(girl1 == null);
    }





}

class Girl{

    String name;

    public Girl(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Girl{" +
                "name='" + name + '\'' +
                '}';
    }
}

class Boy{

    String name;

    public Boy(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "name='" + name + '\'' +
                '}';
    }
}
