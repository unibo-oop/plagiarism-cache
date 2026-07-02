package model.attribute;

import static model.attribute.Trait.HAIRS;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import static model.attribute.Feature.*;

class TestAttribute {

    @Test
    public void testAttribute() {
        //assertThrows(IllegalArgumentException.class, () -> new AttributeImpl(null, null));
        final Attribute rufyHairs = AttributeFactory.from(HAIRS, Color.BLACK, Length.SHORT);
        assertTrue(rufyHairs.getTrait().equals(HAIRS));
        assertTrue(rufyHairs.getFeatures().contains(Color.BLACK));
        assertTrue(rufyHairs.getFeatures().contains(Length.SHORT));
        assertFalse(rufyHairs.getFeatures().contains(HairStyle.CURLY));
        final Attribute rufyHairs2 = AttributeFactory.from(HAIRS, Color.BLACK, Length.SHORT);
        assertTrue(rufyHairs.equals(rufyHairs2));
    }

}
