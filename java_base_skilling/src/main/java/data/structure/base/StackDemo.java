package data.structure.base;

import java.util.Stack;

public class StackDemo {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        Stack stack = new Stack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);
        Object pop = stack.pop();
        Object pop1 = stack.pop();
        System.out.println(pop);
        System.out.println(pop1);
        System.out.println(stack);


    }

}
