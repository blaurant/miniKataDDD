package DDD.framework.stateengine;


import DDD.framework.DomainEvent;

public class StateEvent {

    protected String transitionName;

    public StateEvent(String transitionName) {
        this.transitionName = transitionName;
    }

    public String getTransitionName() {
        return transitionName;
    }

    public DomainEvent executeOnStateThroughThisTransition() {
        return DomainEvent.NO_EVENT;
    }

}
