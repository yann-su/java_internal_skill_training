package annotations.test;

import annotations.UseCase;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class UseCaseTracker {

    public static void trackeUseCase(List<Integer> useCases,Class<?> clazz){
        for (Method method : clazz.getDeclaredMethods()){
            UseCase uc = method.getAnnotation(UseCase.class);
            System.out.println(method.getName());
            if (uc != null){
                System.out.println("found use case"+uc.id()+"\n"+uc.description());
                useCases.remove(Integer.valueOf(uc.id()));
            }
        }
        useCases.forEach(i -> System.out.println("missing use case "+ i));
    }

    public static void getUseCaseAnnotations(String className) throws ClassNotFoundException {
        Class<?> aClass = Class.forName(className);
        for (Method method : aClass.getDeclaredMethods()){
            if (method.getName().equals("validatePassword")){
                UseCase annotation = method.getAnnotation(UseCase.class);
                System.out.println(annotation.id());
            }
        }

    }

    public static void main(String[] args) throws ClassNotFoundException {
        List<Integer> collect = IntStream.range(47, 51).boxed().collect(Collectors.toList());
//        trackeUseCase(collect,UseCaseDemo.class);

        //获取方法
        getUseCaseAnnotations("annotations.test.UseCaseDemo");
    }

}
