package kata.domain;

import org.junit.Test;

public class DealNameTest {

    @Test(expected = IllegalArgumentException.class)
    public void nameConsistencyTest() {
        new DealName(null);
    }
}
