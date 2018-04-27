package DDD.framework.stateengine;

import DDD.framework.collection.ASetOf;
import com.google.common.collect.ImmutableSet;

import java.util.List;
import java.util.Set;

public class Transitions extends ASetOf<Transition> {
    public Transitions() {
    }

    public Transitions(ImmutableSet<Transition> set) {
        super(set);
    }

    public Transitions(Set<Transition> set) {
        super(set);
    }

    public Transitions(List<Transition> list) {
        super(list);
    }

    public Transitions(Transition... arrayOfT) {
        super(arrayOfT);
    }

    public Transitions(Iterable<Transition> iterable) {
        super(iterable);
    }

    @Override
    public <Sub extends ASetOf<Transition>> Sub cons(Set<Transition> newSet) {
        return (Sub) new Transitions(newSet);
    }

    public Transition byName(String transitionName) {
        return filter(transition -> transition.name.equals(transitionName)).findAny().orElse(null);
    }
}
