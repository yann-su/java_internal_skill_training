package internal.fuction;

import interfaces.LambdaTestFuction;
import interfaces.LambdaTestFuction2;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.String.format;

public class LambdaDemo {

    public static void main(String[] args) {
        Function<Integer, Integer> function = i -> i + 1;

        Function<Integer, Integer> function1 = i -> i * 199;

        Integer apply = function.apply(1);
        System.out.println(apply);

        Runnable runnable = () -> System.out.println("1");
        runnable.run();

        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        consumer.accept("谎言和誓言的区别是什么呢");

        Consumer<String> consumer1 =
                (String st) -> {
                    System.out.println(st);
                };
        consumer1.accept("一个听的人当真了，一个是说的人当真了");


        Consumer<String> consumer2 = st -> {
            st = st + "sada";
            System.out.println(st);
        };

        consumer2.accept("一个听的人当真了，一个是说的人当真了");


        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        };
        int compare = comparator.compare(10, 21);
        System.out.println(compare);

        Comparator<Integer> comparator1 = (t1, t2) -> {
            System.out.println(t1);
            System.out.println(t2);
            return t1.compareTo(t2);
        };
        int compare1 = comparator1.compare(11231, 11231);
        System.out.println(compare1);


        Comparator<Integer> comparator2 = (t1, t2) -> t1.compareTo(t2);


        System.out.println(comparator2.compare(2313, 1231));

        LambdaTestFuction<Integer, Integer> lambdaTestFuction = s -> s * 1000;
        int run = lambdaTestFuction.run(10);
        System.out.println(run);

        LambdaTestFuction2<Integer, Integer> fuction2 = (t, e) -> t + e;
        int run1 = fuction2.run(10, 2);
        System.out.println(run1);

        happyTime(1000, new Consumer<Double>() {
            @Override
            public void accept(Double aDouble) {
                System.out.println(aDouble);
            }
        });

        happyTime(1000, i -> System.out.println(i));


        //利用以上例子，即可以表明java的lambda函数实际上是一个对象，只是进行封装,本质上就是匿名对象，不过底层实现做了优化
        List<String> filter = filter(Arrays.asList("北京", "天津", "海南"), new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.contains("京");
            }
        });
        System.out.println(filter);
        List<String> filter1 = filter(Arrays.asList("北京", "天津", "海南"), s -> s.contains("津"));
        System.out.println(filter1);


    }

    public static void happyTime(double money, Consumer<Double> consumer) {
        consumer.accept(money);
    }

    public static List<String> filter(List<String> strings, Predicate<String> predicate) {
        List<String> filtersArr = new ArrayList<>();
        for (String s : strings) {
            if (predicate.test(s)) {
                filtersArr.add(s);
            }
        }
        return filtersArr;
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
    public void Test01() {
        Stream.iterate(0, t -> t + 5).skip(5).limit(5).forEach(System.out::println);

    }


}

