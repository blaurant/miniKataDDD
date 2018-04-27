package kata.domain;

import org.junit.Test;

public class DealTest {

    @Test(expected = IllegalArgumentException.class)
    public void idConsistencyTest() {
        new Deal(null, new DealName("name"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void nameConsistencyTest() {
        new Deal(null);
    }
}