package DDD.framework.stateengine;

import DDD.framework.collection.ASetOf;
import com.google.common.collect.ImmutableSet;

import java.util.List;
import java.util.Set;

public class States extends ASetOf<State> {

    public States() {
    }

    public States(ImmutableSet<State> set) {
        super(set);
    }

    public States(Set<State> set) {
        super(set);
    }

    public States(List<State> list) {
        super(list);
    }

    public States(State... arrayOfT) {
        super(arrayOfT);
    }

    public States(Iterable<State> iterable) {
        super(iterable);
    }

    @Override
    public <Sub extends ASetOf<State>> Sub cons(Set<State> newSet) {
        return (Sub) new States(newSet);
    }

    public State byName(String stateName) {
        return filter(state -> state.name.equals(stateName)).findAny().orElse(null);
    }
}
