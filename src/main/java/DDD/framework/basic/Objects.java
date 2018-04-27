package DDD.framework.basic;

import java.util.Collection;

public class Objects {

    public static <T> T requireNotNull(T obj) {
        return requireNotNull(obj, "object is null");
    }

    public static <T> T requireNotNull(T obj, String message) {
        if (obj == null)
            throw new IllegalArgumentException(message);
        return obj;
    }

    public static <T> Collection<T> requireNotEmpty(Collection<T> obj, String message) {
        if (requireNotNull(obj, message).size() == 0)
            throw new IllegalArgumentException(message);
        return obj;
    }

    public static <T> void requireNotIdentical(T o1, T o2, String msg) {
        if (o1.equals(o2))
            throw new IllegalArgumentException(msg);
    }

    public static <T> Collection<T> requireSize(Collection<T> obj, int size) {
        if (requireNotNull(obj).size() != size)
            throw new IllegalArgumentException("bad size, must be " + size);
        return obj;
    }
}
