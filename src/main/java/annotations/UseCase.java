package annotations;


import org.junit.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 表示注解信息保存的时长。可选的 **RetentionPolicy** 参数包括：
 * **SOURCE**：注解将被编译器丢弃；
 * **CLASS**：注解在 class 文件中可用，但是会被 VM 丢弃；
 * **RUNTIME**：VM 将在运行期也保留注解，因此可以通过反射机制读取注解的信息。
 */
@Retention(RetentionPolicy.RUNTIME)
//Target主要是词此应用文件可以用来哪些事物上，
@Target(ElementType.METHOD)
public @interface UseCase {
    int id();
    String description() default "no description";
}
