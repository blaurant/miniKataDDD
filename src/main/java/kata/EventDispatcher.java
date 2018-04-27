package kata;

import DDD.framework.DomainEvent;
import io.reactivex.subjects.PublishSubject;

public class EventDispatcher {

    private final PublishSubject<DomainEvent> eventPublishSubject = PublishSubject.create();

    public void register(EventHandler eventHandler) {
        eventHandler.injectDispatcher(this);
        subscribe(eventHandler);
    }

    public void onNext(DomainEvent domainEvent) {
        eventPublishSubject.onNext(domainEvent);
    }

    public void subscribe(EventHandler eventHandler) {
        eventPublishSubject.subscribe(
                domainEvent -> {
                    DomainEvent[] domainEvents = eventHandler.onDomainEvent(domainEvent);
                    for (DomainEvent event : domainEvents)
                        onNext(event);
                });
    }
}
