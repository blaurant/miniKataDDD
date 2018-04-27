package DDD.framework.collection;

import com.google.common.collect.ImmutableList;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.google.common.collect.ImmutableList.copyOf;
import static java.util.Objects.requireNonNull;

public class AListOf<T> implements Iterable<T>  {

    protected ImmutableList<T> list;

    public AListOf() {
        this.list = ImmutableList.of();
    }

    public AListOf(ImmutableList<T> list){
        this.list = requireNonNull(list);
    }

    public AListOf(List<T> list) {
        this.list = copyOf(requireNonNull(list));
    }

    public AListOf(T[] arrayOfT) {
        this.list = copyOf(requireNonNull(arrayOfT));
    }


    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }

    protected ImmutableList<T> append(T t) {
        return new ImmutableList.Builder().addAll(list).add(t).build();
    }

    protected ImmutableList<T> append(AListOf<T> t) {
        return new ImmutableList.Builder().addAll(list).addAll(t).build();
    }

    public static <T> AListOf<T> requireNotEmpty(AListOf<T> aListOf) {
        return AListOf.requireNotEmpty(aListOf, null);
    }

    public static <T> AListOf<T> requireNotEmpty(AListOf<T> aListOf, String msg) {
        return requireNonNull(aListOf, msg).requireNotEmpty(msg);
    }

    public ImmutableList<T> getList(){
        return list;
    }

    public AListOf<T> requireNotEmpty(String msg) {
        if (list.size() == 0)
            throw new IllegalArgumentException(msg);
        return this;
    }

    public AListOf<T> requireEmpty(String msg) {
        if (!list.isEmpty())
            throw new IllegalStateException(msg);
        return this;
    }

    public T uniqueOrNull() {
        if (list.isEmpty())
            return null;
        if (list.size() > 1)
            throw new IllegalStateException("this contains more than one element");
        return head();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AListOf<?> aListOf = (AListOf<?>) o;

        return list.equals(aListOf.list);

    }

    @Override
    public int hashCode() {
        return list.hashCode();
    }

    public boolean contains(T t) {
        return list.contains(t);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public boolean isNotEmpty() {
        return !isEmpty();
    }

    public int size() {
        return list.size();
    }

    public T first(T t) {
        return stream().findFirst().orElse(t);
    }

    public T head() {
        return first(null);
    }

    public Stream<T> stream() {
        return list.stream();
    }

    public Stream<T> streamFilter(Predicate<T> predicate) {
        return stream().filter(predicate);
    }

    public T find(T t) {
        return stream().filter(t1 -> t1.equals(t)).findFirst().orElse(null);
    }

    public Set<T> toSet() {
        return stream().collect(Collectors.toSet());
    }
}
