package com.george.learnjava.objectclass.ch03staticfieldinit;

/**
 * p 130
 * 类静态字段的初始化有两种方式
 * 1. 在类静态字段直接初始化
 * 2. 通过 static {} 代码块进行初始化
 */
public class StaticFieldInit {
    public static void main(String[] args) {
        System.out.println("Test.test:" + Test.test);
        System.out.println("Test2.test:" + Test2.test);
    }
}

// 1. 在类静态字段直接初始化
class Test {
    static public int test = 123;
}

// 2. 通过 static {} 代码块进行初始化
class Test2 {
    static public int test;
    static {
        test = 123;
    }
}
