/**
 * page 187
 * 1.ArrayList是一个有类型参数的泛型类，在添加或删除元素时，能够自动调整容量
 * 2.为了指定数组列表保存的元素类型，需要用一堆尖括号将类名括起来追加到ArrayList后面，例如ArrayList<Employee>
 * 3.使用add方法将元素添加到数组列表中
 * 4.如果已经知道数组可能存储的元素数量，就可以在填充数组之前调用ensureCapacity方法，这个方法将分配一个包含100个对象的内部数组，这样前100次add调用不会带来开销很大的内存开销
 * 5.size方法将返回数组列表中包含的实际元素个数，等价于数组的arr.length
 * 6.一旦能够确认数组列表的大小，就可以调用trimToSize方法将内存块的大小调整为保存当前元素数量所需要的存储空间
 * 7.不能通过[]来访问或更改数组列表的元素，要使用get和set方法
 * 8.有时需要在数组列表的中间增加元素，此时可以使用add方法兵提供一个索引参数n，位置n及之后的所有元素都要向后移动一个位置，为新元素留出空间
 * 9.如果插入的位置超过了arr.size()，则会抛出IndexOutOfBoundsException异常
 * 10.使用remove(int index)方法删除一个元素，这个元素之后的所有元素都向前移动一个位置，并且数组的大小减1
 * 11.插入和删除元素的效率高很低，对于较小的数组列表来说，不用担心这个问题，但如果存储的元素很多，又经常需要插入和删除元素，就应该考虑使用链表。
 * 12.可以使用for each循环变量数组列表的内容
 */
package page186GenericList;

import java.util.ArrayList;

public class Demo {
    public static void main(String[] args) {
        // 声明数组列表
        ArrayList<Employee> staff = new ArrayList<Employee>();
        // Java10中，可以使用bar关键字避免重复写类名
        var staff2 = new ArrayList<Employee>();
        // 如果没有使用bar关键字，则可以省略右边的类型参数
        ArrayList<Employee> staff3 = new ArrayList();
        // 在Java5以前的版本没有提供泛型类，而是一个保存Object类型元素的ArrayList类
        ArrayList staff4 = new ArrayList();

        Employee emp = new Employee("emp");
        Employee emp2 = new Employee("emp2");
        Employee emp3 = new Employee("emp3");
        Employee emp4 = new Employee("emp4");
        // 3.使用add方法将元素添加到数组列表中
        staff.add(emp);
        // 5.size方法将返回数组列表中包含的实际元素个数，等价于 .length
        System.out.println("staff.size():" + staff.size());
        // 这里将内部数组的大小设置为5，调用size()方法打印出来的大小还是实际元素数量1
        staff.ensureCapacity(5);
        System.out.println("staff.size():" + staff.size()); // staff.size():1
        // 6.一旦能够确认数组列表的大小，就可以调用trimToSize方法将内存块的大小调整为保存当前元素数量所需要的存储空间
        staff.trimToSize();
        // 7.不能通过[]来访问或更改数组列表的元素，要使用get和set方法
        staff2.add(emp);
        System.out.println("staff2.get(0):" + staff2.get(0));
        staff2.set(0, emp2);
        System.out.println("staff2.get(0):" + staff2.get(0));
        staff3.add(emp);
        staff3.add(emp2);
        staff3.add(emp3);
        System.out.println("staff3:" + staff3); // staff3:[emp, emp2, emp3]
        // 8.有时需要在数组列表的中间增加元素，此时可以使用add方法兵提供一个索引参数n，位置n及之后的所有元素都要向后移动一个位置，为新元素留出空间
        staff3.add(1, emp4);
        System.out.println("staff3添加emp4:" + staff3); // staff3添加emp4:[emp, emp4, emp2, emp3]
        // 9.如果插入的位置超过了arr.size()，则会抛出IndexOutOfBoundsException异常
        // staff3.add(10, emp4); // Exception in thread "main" java.lang.IndexOutOfBoundsException: Index: 10, Size: 4
        // 10.使用remove(int index)方法删除一个元素，这个元素之后的所有元素都向前移动一个位置，并且数组的大小减1
        staff3.remove(1);
        System.out.println("staff3删除emp4:" + staff3); // staff3删除emp4:[emp, emp2, emp3]
        // 12.可以使用for each循环变量数组列表的内容
        for (Employee staffValue : staff3) {
            System.out.println(staffValue);
        }
    }
}

class Employee {
    private String name;

    public Employee(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}