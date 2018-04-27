package DDD.framework;

import DDD.framework.collection.AListOf;
import com.google.common.collect.ImmutableList;
import kata.domain.Deal;
import kata.domain.DealDomainEvent;

import java.util.List;
import java.util.Optional;

public class DealDomainEventList extends AListOf<DealDomainEvent> {

    public DealDomainEventList() {
        super();
    }

    public DealDomainEventList(ImmutableList<DealDomainEvent> list) {
        super(list);
    }

    public DealDomainEventList(List<DealDomainEvent> list) {
        super(list);
    }

    public DealDomainEventList(DealDomainEvent[] arrayOfT) {
        super(arrayOfT);
    }

    @Override
    public <Sub extends AListOf<DealDomainEvent>> Sub cons(List<DealDomainEvent> newList) {
        return (Sub) new DealDomainEventList(newList);
    }

    public DealDomainEventList onlyWith(Deal.Id id) {
        return filter(dealDomainEvent -> dealDomainEvent.value().getId().equals(id));
    }

    public Optional<DealDomainEvent> last() {
        if (list.isEmpty())
            return Optional.empty();
        else
            return Optional.of(list.get(list.size()-1));
    }
}
