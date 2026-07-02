package todo.utils;

import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class UniqueIdGeneratorTest {
    @Test
    public void testGeneratedIDsAreUnique() {
        assertNotEquals(UniqueIdGenerator.getInstance().next(), UniqueIdGenerator.getInstance().next());
        assertNotEquals(UniqueIdGenerator.getInstance().next().toString(),
                UniqueIdGenerator.getInstance().next().toString());
        assertNotEquals(UniqueIdGenerator.getInstance().next().hashCode(),
                UniqueIdGenerator.getInstance().next().hashCode());
    }
}
