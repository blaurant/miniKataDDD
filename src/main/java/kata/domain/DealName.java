package kata.domain;

import DDD.framework.SimpleValueObject;

@DDD.ValueObject
public class DealName extends SimpleValueObject<String> {

    public DealName(String name) {
        super(name);
    }
}
