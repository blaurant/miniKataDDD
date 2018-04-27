package DDD.framework;

import java.util.ArrayList;
import java.util.List;

public class Audit {

    private List<DomainEvent> traces = new ArrayList<>();

    public void addTrace(DomainEvent domainEvent) {
        traces.add(domainEvent);
    }

    public List<DomainEvent> getTraces() {
        return traces;
    }
}
