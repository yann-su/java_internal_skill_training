package java.internal.fuction;

import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.String.format;

public class LambdaTest {

    @Test
    public void test(){

        Runnable runnable = new Runnable() {
            public void run() {
                System.out.println("this is test");
            }
        };
        runnable.run();

    }

    public String quoteIdentifier(String identifier) {
        return "\"" + identifier + "\"";
    }


    public String getUpdateStatement(String tableName, String[] fieldNames, String[] conditionFields) {
        String setClause = Arrays.stream(fieldNames)
                .map(f -> format("%s = :%s", quoteIdentifier(f), f))
                .collect(Collectors.joining(", "));
        String conditionClause = Arrays.stream(conditionFields)
                .map(f -> format("%s = :%s", quoteIdentifier(f), f))
                .collect(Collectors.joining(" AND "));
        return "UPDATE " + quoteIdentifier(tableName) +
                " SET " + setClause +
                " WHERE " + conditionClause;
    }


    @Test
    public void Test01(){
        Stream.iterate(0,t -> t+5).skip(5).limit(5).forEach(System.out::println);

    }






}
