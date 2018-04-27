package DDD.framework;

import DDD.framework.collection.ASetOf;
import com.google.common.collect.ImmutableSet;

import java.util.List;
import java.util.Set;

public class DomainEvents extends ASetOf<DomainEvent> {

    public DomainEvents(ImmutableSet<DomainEvent> set) {
        super(set);
    }

    public DomainEvents(Set<DomainEvent> set) {
        super(set);
    }

    public DomainEvents(List<DomainEvent> list) {
        super(list);
    }

    public DomainEvents(DomainEvent... arrayOfT) {
        super(arrayOfT);
    }

    public DomainEvents(Iterable<DomainEvent> iterable) {
        super(iterable);
    }

    @Override
    public <Sub extends ASetOf<DomainEvent>> Sub cons(Set<DomainEvent> newSet) {
        return (Sub) new DomainEvents(newSet);
    }

    public DomainEvent[] toArray() {
        return set.toArray(new DomainEvent[set.size()]);
    }
}
