package kata;

import DDD.framework.DomainEvent;

public interface EventHandler {

    void injectDispatcher(EventDispatcher eventDispatcher);

    DomainEvent[] onDomainEvent(DomainEvent domainEvent);
}
