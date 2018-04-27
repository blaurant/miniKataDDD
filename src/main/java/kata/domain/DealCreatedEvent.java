package kata.domain;

public class DealCreatedEvent extends DealDomainEvent {

    public DealCreatedEvent(Deal deal) {
        super(deal);
    }

}
