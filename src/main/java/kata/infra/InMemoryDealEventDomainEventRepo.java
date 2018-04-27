package kata.infra;

import DDD.framework.DomainEvent;
import DDD.framework.DealDomainEventList;
import kata.*;
import kata.application.DealDomainEventRepo;
import kata.domain.Deal;
import kata.domain.DealDomainEvent;

import java.util.Optional;

import static DDD.framework.DomainEvent.NO_EVENTS;

public class InMemoryDealEventDomainEventRepo implements DealDomainEventRepo, EventHandler {

    DealDomainEventList dealDomainEvents = new DealDomainEventList();

    @Override
    public void save(DealDomainEvent dealDomainEvent) {
        dealDomainEvents = dealDomainEvents.append(dealDomainEvent);
    }

    @Override
    public Optional<Deal> byId(Deal.Id id) {
        return getLastDeal(dealDomainEvents.onlyWith(id));
    }

    // hack ! the trick for this kata : a ref is in every Event, so just take one
    private Optional<Deal> getLastDeal(DealDomainEventList dealDomainEvents) {
        if (!dealDomainEvents.last().isPresent())
            return Optional.empty();
        else
            return Optional.of(dealDomainEvents.last().get().value());
    }

    @Override
    public void injectDispatcher(EventDispatcher eventDispatcher) {
    }

    @Override
    public DomainEvent[] onDomainEvent(DomainEvent domainEvent) {
        if (domainEvent instanceof DealDomainEvent) {
            dealDomainEvents = dealDomainEvents.append((DealDomainEvent) domainEvent);
        }
        return NO_EVENTS;
    }

}
