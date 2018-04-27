package kata.domain;

import DDD.framework.collection.ASetOf;
import com.google.common.collect.ImmutableSet;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class Deals extends ASetOf<Deal> {

    public Deals() {
        super();
    }

    public Deals(ImmutableSet<Deal> set) {
        super(set);
    }

    public Deals(Set<Deal> set) {
        super(set);
    }

    public Deals(List<Deal> list) {
        super(list);
    }

    public Deals(Deal[] arrayOfT) {
        super(arrayOfT);
    }

    public Deals(Iterable<Deal> iterable) {
        super(iterable);
    }

    @Override
    public <Sub extends ASetOf<Deal>> Sub cons(Set<Deal> newSet) {
        return (Sub) new Deals(newSet);
    }

    public Optional<Deal> findBy(Deal.Id id) {
        return filter(cl -> cl.getId().equals(id)).findAny();
    }

}
