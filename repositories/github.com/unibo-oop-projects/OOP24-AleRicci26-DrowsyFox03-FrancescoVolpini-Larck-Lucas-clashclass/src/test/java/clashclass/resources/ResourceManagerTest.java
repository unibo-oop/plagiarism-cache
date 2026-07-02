package clashclass.resources;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ResourceManagerTest {
    private static final int GENERIC_VALUE = 10;
    private ResourceManagerImpl resource;

     @BeforeEach
    void setUp() {
         resource = new ResourceManagerImpl(GENERIC_VALUE);
     }

     @Test
    void testResourceInitialized() {
         assertEquals(0, resource.getCurrentValue());
     }

     @Test
    void testResourceIncrease() {
         resource.increase(GENERIC_VALUE + 1);
         assertEquals(GENERIC_VALUE, resource.getCurrentValue());
     }

     @Test
    void testResourceDecrease() {
         resource.decrease(GENERIC_VALUE + 1);
         assertEquals(0, resource.getCurrentValue());
     }
}