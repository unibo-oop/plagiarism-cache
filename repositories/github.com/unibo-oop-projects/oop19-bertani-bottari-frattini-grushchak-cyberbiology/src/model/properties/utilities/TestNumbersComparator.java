package model.properties.utilities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

public class TestNumbersComparator {
    
    @Test
    public void test() {
        assertTrue(NumbersComparator.isBiggerThan(0.5F, 0.04999999999999d));
        assertFalse(NumbersComparator.isBiggerThan(0.02d, 0.02000000000001d));
        assertTrue(NumbersComparator.isBiggerOrEqulalThan(9999, 9999));
        assertTrue(NumbersComparator.isBiggerOrEqulalThan(0.009f, 0.008f));
        assertTrue(NumbersComparator.isBiggerThan(0.1f, -0.9f));
        
    }

}
