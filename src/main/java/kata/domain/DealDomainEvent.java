package kata.domain;

import DDD.framework.DomainEvent;
import DDD.framework.SimpleValueObject;

public abstract class DealDomainEvent extends SimpleValueObject<Deal> implements DomainEvent {

    public DealDomainEvent(Deal value) {
        super(value);
    }
}