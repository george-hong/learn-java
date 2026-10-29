package common;

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

    public String toString() {
        return getClass().getName() + "[first=" + first.toString() + ",second=" + second.toString() + "]";
    }

    public static <T> page349Limited.Pair<T> makePair(Supplier<T> s) {
        return new page349Limited.Pair<>(s.get(), s.get());
    }

    public static <T> page349Limited.Pair<T> makePair2(Class<T> cl)  {
        try {
            return new page349Limited.Pair<>(cl.getConstructor().newInstance(), cl.getConstructor().newInstance());
        } catch (Exception e) {
            return new page349Limited.Pair<>();
        }
    }
}