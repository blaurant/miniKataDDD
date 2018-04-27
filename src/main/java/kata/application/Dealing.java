package kata.application;

import DDD.framework.DomainEvent;
import kata.domain.DealCreatedEvent;
import kata.domain.DealRenamedEvent;
import kata.EventDispatcher;
import kata.EventHandler;
import kata.domain.Deal;
import kata.domain.DealName;

import static DDD.framework.basic.Objects.requireNotNull;

public class Dealing implements EventHandler {

    private EventDispatcher eventDispatcher;
    private DealDomainEventRepo dealDomainEventRepo;

    @Override
    public void injectDispatcher(EventDispatcher eventDispatcher) {
        this.eventDispatcher = eventDispatcher;
    }

    public void injectRepo(DealDomainEventRepo dealDomainEventRepo) {
        this.dealDomainEventRepo = dealDomainEventRepo;
    }

    @Override
    public DomainEvent[] onDomainEvent(DomainEvent domainEvent) {
        return DomainEvent.NO_EVENTS;
    }

    public void createDeal(Deal.Id dealId, DealName dealName) {
        Deal deal = new Deal(dealId, dealName);
        eventDispatcher.onNext(new DealCreatedEvent(deal));
    }

    public void rename(Deal.Id dealId, DealName dealName) {
        requireNotNull(dealId, "dealId is null");
        requireNotNull(dealName, "dealName is null");
        Deal deal = dealDomainEventRepo.byId(dealId).orElseThrow(() -> new UnknowDeal(dealId));
        deal.rename(dealName);
        eventDispatcher.onNext(new DealRenamedEvent(deal));
    }


}
