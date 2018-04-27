package DDD.framework;

public interface DomainEvent {

    DomainEvent NO_EVENT = new DomainEvent() {
        @Override
        public String toString() {
            return "NO_EVENT";
        }
    };

    DomainEvent[] NO_EVENTS = new DomainEvent[]{};

}
