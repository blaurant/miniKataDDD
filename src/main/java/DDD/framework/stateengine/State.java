package DDD.framework.stateengine;


import java.util.function.Consumer;

import static java.util.Objects.requireNonNull;

public class State {

    public static final Consumer<StateEvent> NOOP = (whatever) -> { };

    public final String name;
    public final Consumer<StateEvent> executeOnState;
    private Transitions outBounds = new Transitions();
    private Transitions inBounds = new Transitions();

    public State(String name, Consumer executeOnState) {
        this.name = requireNonNull(name);
        this.executeOnState = requireNonNull(executeOnState);
    }

    public State(String name) {
        this(name, NOOP);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return name.equals(state.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    public Transition findTransition(StateEvent stateEvent) {
        return findTransition(stateEvent.getTransitionName());
    }

    public Transition findTransition(String transitionName) {
        return outBounds.byName(transitionName);
    }

    public void executeOnState(StateEvent stateEvent) {
        executeOnState.accept(stateEvent);
    }

    public void addTransition(Transition transition, State toState) {
        if (findTransition(transition.name) != null)
            throw new IllegalArgumentException("state " + name + " already have an outbound transition " + transition.name);
        outBounds = outBounds.add(transition);
        toState.inBounds.add(transition);
    }
}
