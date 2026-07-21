package com.george.learnjava.objectclass.ch06package.test2;

public class PackageDemo {
    private int selfAttr = 123;
    public int publicAttr = 123;
    int noDescAttr = 123;
}

class Test {
    public static void main(String[] args) {
        PackageDemo demo = new PackageDemo();
        // 私有属性不可访问
//        System.out.print(demo.selfAttr);
        System.out.print(demo.publicAttr);
        System.out.print(demo.noDescAttr);
    }
}