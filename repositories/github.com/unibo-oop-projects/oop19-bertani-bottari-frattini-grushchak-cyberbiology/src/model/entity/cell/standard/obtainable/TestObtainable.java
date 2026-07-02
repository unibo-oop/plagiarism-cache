package model.entity.cell.standard.obtainable;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import model.entity.cell.standard.age.AgeImpl;
import model.entity.cell.standard.age.AgeManipulation;

public class TestObtainable {

    /**
     * Test correct working of the builder.
     */
    @Test
    public void testCellBuilder() {
        AgeManipulation age = new AgeImpl(100);

        // starting age
        assertEquals(age.getAge(), 0);

        // adding age
        age.increment();
        assertEquals(age.getAge(), 1);

        // testing reset
        age.resetAge();
        assertEquals(age.getAge(), 0);

        // testing die
        for (int i = 0; i < 100; i++) {
            age.increment();
            assertEquals(age.isDead(), false);
        }
        age.increment();
        assertEquals(age.isDead(), true);
        age.increment();
        assertEquals(age.isDead(), true);

    }
}
