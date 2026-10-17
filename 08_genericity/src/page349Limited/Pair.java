package page349Limited;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Supplier;

public class Pair<T> {
    private T first;
    private T second;

    public Pair() {
        first = null;
        second = null;
    }

    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return this.first;
    }

    public T getSecond() {
        return this.second;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public void setSecond(T second) {
        this.second = second;
    }

    public static <T> Pair<T> makePair(Supplier<T> s) {
        return new Pair<>(s.get(), s.get());
    }

    public static <T> Pair<T> makePair2(Class<T> cl)  {
        try {
            return new Pair<>(cl.getConstructor().newInstance(), cl.getConstructor().newInstance());
        } catch (Exception e) {
            return new Pair<>();
        }
    }
}