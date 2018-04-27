package kata.domain;

import DDD.framework.DomainEvent;
import DDD.framework.SimpleValueObject;
import kata.domain.Deal;

public class DealRenamedEvent extends SimpleValueObject<Deal> implements DomainEvent {

    public DealRenamedEvent(Deal deal) {
        super(deal);
    }

}
