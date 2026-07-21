package com.george.learnjava.objectclass.ch02fieldinit;

/**
 * p 127
 * 初始化类的字段有三种方式
 * 1. 类定义中赋值
 * 2. 构造器赋值
 * 3. 初始化块赋值
 */
public class FieldInit {
    public static void main(String[] args) {
        Test test = new Test();
        Test2 test2 = new Test2();
        Test3 test3 = new Test3();
        System.out.println("test field:" + test.field);
        System.out.println("test2 field:" + test2.field);
        System.out.println("test3 field:" + test3.field);
    }
}

// 1. 类定义中赋值
class Test {
    public int field = 123;
}

// 2. 构造器赋值
class Test2 {
    public int field;

    Test2() {
        field = 123;
    }
}

// 3. 初始化块赋值
class Test3 {
    public int field;
    {
        field = 123;
    }
}
