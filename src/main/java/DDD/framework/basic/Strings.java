package DDD.framework.basic;

import DDD.framework.collection.ASetOf;
import com.google.common.collect.ImmutableSet;

import java.util.List;
import java.util.Set;


public class Strings extends ASetOf<String> {

    public Strings() {
    }

    public Strings(ImmutableSet<String> set) {
        super(set);
    }

    public Strings(Set<String> set) {
        super(set);
    }

    public Strings(List<String> list) {
        super(list);
    }

    public Strings(String... arrayOfT) {
        super(arrayOfT);
    }

    public Strings(Iterable<String> iterable) {
        super(iterable);
    }

    public static String requireNotEmpty(String str) {
        return requireNotEmpty(str, "String is null or empty");
    }

    public static String requireNotEmpty(String str, String msg) {
        if (str == null) throw new IllegalArgumentException(msg);
        if (str.trim().isEmpty()) throw new IllegalArgumentException(msg);
        return str;
    }

    public static String requireLength(String str, int length) {
        requireNotEmpty(str);
        if (str.trim().length() < length) throw new IllegalArgumentException("String is too short");
        return str;
    }

    public static String requireLength(String str, int length, String msg) {
        requireNotEmpty(str);
        if (str.trim().length() < length) throw new IllegalArgumentException(msg);
        return str;
    }

    @Override
    public <Sub extends ASetOf<String>> Sub cons(Set<String> newSet) {
        return (Sub) new Strings(newSet);
    }
}
