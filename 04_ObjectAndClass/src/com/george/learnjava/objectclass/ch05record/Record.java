package com.george.learnjava.objectclass.ch05record;

/**
 * p 134 record 语法 为 record Point(int x, int y) {}
 * 可以为record添加方法，如果添加的方法和自动方法同名，则需要保持签名一致
 * 无法为record添加实例字段
 * record 简洁形式中不能读取、操作实例，只能修改参数变量
 */
class Record {
    public static void main(String[] args) {
        Point point = new Point(2, 4);
        System.out.println("point x:" + point.x());
        System.out.println("point y:" + point.y());

        Point2 point2 = new Point2(2, 4);
        System.out.println("point2 x:" + point2.x());
        System.out.println("point2 y:" + point2.y());

        Point4 point4 = new Point4(2, 4);
        System.out.println("point4 x:" + point4.x());
        System.out.println("point4 y:" + point4.y());
    }
}

record Point(int x, int y) {}
// 以上代码相当于
class Point2 {
    final private int x;
    final private int y;

    Point2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x() {
        return this.x;
    }

    public int y() {
        return this.y;
    }
}

record Point3(int x, int y) {
    // 可以为record添加方法
    public int getX() {
        return this.x;
    }
    // 也可以覆盖自动生成的方法，但是入参和返回值要跟自动方法保持一致
    public int x() {
        return this.x;
    }
    // 以下方法会报错
//    public int x() {
//        return null;
//    }

    // 无法为record添加实例字段
//    final private int z;
}

// record 构造器的简洁形式，简洁形式中不能读取、操作实例，只能修改参数变量
// 以下代码交换了入参x,y的值
record Point4(int x, int y) {
    Point4 {
        int temp = x;
        x = y;
        y = temp;
    }
}
