package string;

import java.io.IOException;

public class StringBytesEquals {
    public static void main(String[] args) throws IOException {
        while (true) {
            byte[] bytes = new byte[1024];
            System.out.println("请输入内容");
            System.in.read(bytes);
            String s = new String(bytes);
            System.out.println("输入了：" + s);
            System.out.println("exit".equalsIgnoreCase(s));
        }
    }

}
