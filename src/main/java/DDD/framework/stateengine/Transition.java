package DDD.framework.stateengine;


import DDD.ValueObject;
import DDD.framework.DomainEvent;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;

@ValueObject
public class Transition {

    public final String name;
    public final State destination;
    public final Consumer<StateEvent> executeBeforeTransit;
    private Function<StateEvent,DomainEvent> executeAfterTransit;
    private Predicate<StateEvent> conditionForTransit;

    public Transition(String name, State destination, Consumer<StateEvent> executeBeforeTransit) {
        this.name = requireNonNull(name);
        this.destination = requireNonNull(destination);
        this.executeBeforeTransit = requireNonNull(executeBeforeTransit);
    }

    public Transition(String name, State destination, Consumer<StateEvent> executeBeforeTransit, Predicate<StateEvent> conditionForTransit, Function<StateEvent,DomainEvent> executeAfterTransit) {
        this(name, destination, executeBeforeTransit);
        this.conditionForTransit = requireNonNull(conditionForTransit);
        this.executeAfterTransit = requireNonNull(executeAfterTransit);
    }

    public void executeBeforeTransit(StateEvent stateEvent) {
        executeBeforeTransit.accept(stateEvent);
    }

    public DomainEvent executeAfterTransit(StateEvent stateEvent) {
        return executeAfterTransit.apply(stateEvent);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transition that = (Transition) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    public boolean canTransit(StateEvent stateEvent) {
        if (conditionForTransit == null)
            return true;
        return conditionForTransit.test(stateEvent);
    }

}
