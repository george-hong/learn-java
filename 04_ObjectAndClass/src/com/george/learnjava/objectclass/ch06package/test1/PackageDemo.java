/**
 * p140 要想将类放入包中，就必须将包名放在源文件的开头
 * 如果没有在源文件中纺织package语句，那么这个源文件中的类就属于无名包
 * 当两个包中的class重名时，需要使用完整类名
 * p143 包访问
 * 标记为public的部分可以由任意类使用
 * 标记为private的部分，只能由定义他的类使用
 * 如果两个标记都没有，这个部分（类、方法、变量）可以由同一个包中的所有方法访问
 */
package com.george.learnjava.objectclass.ch06package.test1;

import com.george.learnjava.objectclass.ch06package.test2.*;
// 以下语句为静态导入，这样导入之后就可以直接使用out上面的方法
import static java.lang.System.out;

public class PackageDemo {
    public static void main(String[] args) {
        com.george.learnjava.objectclass.ch06package.test2.PackageDemo demo2 = new com.george.learnjava.objectclass.ch06package.test2.PackageDemo();
        // public成员，可以访问
        out.println(demo2.publicAttr);
        // 无修饰符的成员，外部包无法访问
//        out.println(demo2.noDescAttr);
        // private属性，无法访问
//        out.println(demo2.selfAttr);
    }
}

