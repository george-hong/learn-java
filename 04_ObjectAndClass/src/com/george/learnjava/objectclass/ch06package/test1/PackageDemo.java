/**
 *
 */
package com.george.learnjava.objectclass.ch06package.test1;

import com.george.learnjava.objectclass.ch06package.test2.*;
import static java.lang.System.out;

public class PackageDemo {
    public static void main(String[] args) {
        com.george.learnjava.objectclass.ch06package.test2.PackageDemo demo2 = new com.george.learnjava.objectclass.ch06package.test2.PackageDemo();
        out.println(demo2.publicAttr);
//        out.println(demo2.noDescAttr);
//        out.println(demo2.selfAttr);
    }
}

