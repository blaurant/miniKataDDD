package DDD.framework;

import DDD.framework.stateengine.StateEngine;

public abstract class LifeCycleEntity<ID> extends Entity<ID> {

    protected transient StateEngine lifeCycle;

    public LifeCycleEntity(ID id) {
        super(id);
        initLifeCycle();
    }

    private void initLifeCycle() {
        lifeCycle = new LifeCycle();
        createLifeCycle(lifeCycle);
        lifeCycle.start();
    }

    protected abstract void createLifeCycle(StateEngine lifeCycle);

    public String getState() {
        return lifeCycle.getCurrentState();
    }

}
