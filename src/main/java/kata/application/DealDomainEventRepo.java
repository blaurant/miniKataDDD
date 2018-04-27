package kata.application;

import kata.domain.DealDomainEvent;
import kata.domain.Deal;

import java.util.Optional;

@DDD.Repository(rootAggregate = DealDomainEvent.class)
public interface DealDomainEventRepo {

    void save(DealDomainEvent dealDomainEvent);

    Optional<Deal> byId(Deal.Id id);
}
