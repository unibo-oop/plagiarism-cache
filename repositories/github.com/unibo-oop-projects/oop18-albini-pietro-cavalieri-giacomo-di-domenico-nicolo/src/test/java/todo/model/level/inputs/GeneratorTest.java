package todo.model.level.inputs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import todo.vm.Value;

public class GeneratorTest {

    @Test
    public void testIntegersGenerator() {
        final RandomIntegersGenerator generator = new RandomIntegersGenerator(new ArrayList<>());
        generator.setMin(1).setMax(20).setSize(12);
        generator.getNewInput().stream().map(Value::asNumber).forEach(nat -> {
            assertTrue(nat >= 1 && nat <= 20);
        });
        assertEquals(12, generator.getNewInput().size());
    }

}
