/**
 * page 246
 * 如果希望按长度递增的顺序对字符串进行排序，可以使用Array.sort的另外一个版本,接收一个数组和一个比较器作为参数
 * 比较器是实现了Comparator接口的类的实例
 */
package page246ComparatorInterface;

import java.util.Arrays;
import java.util.Comparator;

public class Demo {
    public static void main(String[] args) {
        LengthComparator lengthComparator = new LengthComparator();
        String[] strList = { "Book", "a", "Nut", "Something", "Cake" };
        Arrays.sort(strList, lengthComparator);
        System.out.println(Arrays.toString(strList));
    }
}

class LengthComparator implements Comparator<String> {
    public int compare(String str1, String str2) {
        return str1.length() - str2.length();
    }
}

