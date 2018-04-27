package DDD.framework.stateengine;


import DDD.ValueObject;
import DDD.framework.DomainEvent;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import static DDD.framework.DomainEvent.NO_EVENT;
import static DDD.framework.basic.Objects.requireNotNull;

@ValueObject
public class StateEngine {

    public static final Consumer<StateEvent> NOOP_BEFORE_TRANSIT = (whatever) -> { };
    public static final Predicate<StateEvent> NOOP_TO_TEST_TRANSIT = (whatever) -> true;
    public static final Function<StateEvent, DomainEvent> NOOP_AFTER_TRANSIT = (whatever) -> DomainEvent.NO_EVENT;

    State currentState;
    State initialState;
    States states = new States();

    public StateEngine start() {
        requireNotNull(initialState);
        currentState = initialState;
        return this;
    }

    public void startAtState(String stateName) {
        requireNotNull(stateName);
        currentState = states.byName(stateName);
    }

    public StateEngine defineInitialState(String stateName) {
        requireNotNull(stateName);
        initialState = states.byName(stateName);
        return this;
    }

    public DomainEvent onStateAction(StateEvent stateEvent) {
        requireNotNull(stateEvent);
        requireNotNull(initialState, "initial state is not set");
        requireNotNull(currentState, "current state is not set");
        Transition transition = currentState.findTransition(stateEvent);
        if (transition == null) {
            System.out.println("NO TRANSITION for " + stateEvent.transitionName + "(current state is " + currentState.name + ")");
            return NO_EVENT;
        }
        if (!transition.canTransit(stateEvent)) {
            return NO_EVENT;
        }
        transition.executeBeforeTransit(stateEvent); // transition is decided, do this work on this transition before the state change
        currentState = transition.destination; //  - the transition itself -
        //DomainEvent domainEvent = stateEvent.executeOnStateThroughThisTransition(); // work porté par le stateevent ?
        DomainEvent domainEvent = transition.executeAfterTransit(stateEvent); // // work after transition when come from this Transition
        currentState.executeOnState(stateEvent); // work after transition whatever Transition
        return domainEvent;
    }

    public StateEngine addState(String stateName, Consumer<StateEvent> actionAfterTransit) {
        states = states.add(new State(stateName, actionAfterTransit));
        return this;
    }

    public StateEngine addState(String stateName) {
        return addState(stateName, StateEngine.NOOP_BEFORE_TRANSIT);
    }

    public StateEngine addTransition(String fromStateName, String transitionName, String toStateName,
                                     Consumer<StateEvent> executeBeforeTransit,
                                     Predicate<StateEvent> conditionForTransit,
                                     Function<StateEvent, DomainEvent> executeAfterTransit) {
        State departure = states.byName(fromStateName);
        State destination = states.byName(toStateName);
        Transition tr = new Transition(transitionName, destination, executeBeforeTransit, conditionForTransit, executeAfterTransit);
        departure.addTransition(tr, destination);
        return this;
    }

    public StateEngine addTransition(String fromStateName, String transitionName, String toStateName,
                                     Consumer<StateEvent> executeBeforeTransit) {
        // TODO change to execute_aftertransit !!
        addTransition(fromStateName, transitionName, toStateName,
                executeBeforeTransit, NOOP_TO_TEST_TRANSIT, NOOP_AFTER_TRANSIT);
        return this;
    }

    public StateEngine addTransition(String fromStateName, String transitionName, String toStateName) {
        return addTransition(fromStateName, transitionName, toStateName,
                NOOP_BEFORE_TRANSIT, NOOP_TO_TEST_TRANSIT, NOOP_AFTER_TRANSIT);
    }

    public StateEngine addStatelessTransition(String transitionName, String... stateNames) {
        for (String stateName : stateNames)
            addTransition(stateName, transitionName, stateName);
        return this;
    }

    public StateEngine addSameTransition(String[] fromStateNames, String transitionName, String toStateName) {
        for (String fromStateName : fromStateNames)
            addTransition(fromStateName, transitionName, toStateName);
        return this;
    }

    public String getCurrentState() {
        requireNotNull(currentState, "stateengine has not been initialized, no current state");
        return currentState.name;
    }

    public String getInitialState() {
        requireNotNull(initialState, "initial state has not been defined");
        return initialState.name;
    }
}
