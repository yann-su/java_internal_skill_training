package com.example.kafka;

public class Test {

    public static void main(String[] args) {

        char[] chars = "/Users/backbook/IdeaProjects/flink/flink-dist/src/main/resources/".toCharArray();
        for (int i = 0; i < chars.length; i++) {
            System.out.print(Integer.valueOf(chars[i]).intValue()+" ");
        }


    }

}
