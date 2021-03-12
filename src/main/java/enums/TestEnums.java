package enums;

import org.junit.Test;

public class TestEnums {

    public static void main(String[] args) {



    }

    @Test
    public void testSpicinessEnums(){

    }

    @Test
    public void testShrubbery(){
        for (Shrubbery shrubbery: Shrubbery.values()){
            System.out.println(shrubbery.ordinal());
            System.out.println(shrubbery.name());
        }
        for (String s: "HANGING CRAWLING GROUNDD".split(" ")){
            Shrubbery shrubbery = Enum.valueOf(Shrubbery.class, s);
            System.out.println(shrubbery.name());
        }
    }


}
