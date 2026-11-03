package common;

import java.util.Collection;

public class CollectionHelper {
    public static void print(Collection<?> c) {
        String result = "";
        boolean isFirst = true;
        for (var e : c) {
            if (isFirst) {
                result += e.toString();
                isFirst = false;
            } else {
                result += " " + e.toString();
            }
        }
        System.out.println(result);
    }
}
