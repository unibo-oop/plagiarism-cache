package jwhale.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import jwhale.commons.ItemType;
import jwhale.model.Image;
import jwhale.model.Item;
import jwhale.model.ItemFactoryImpl;
import jwhale.model.Network;
import jwhale.model.Volume;

public class ItemFactoryTest {

    private static final String NAME = "name";

    @Test
    public void imageFactoryTest() {
        final Item item = new ItemFactoryImpl().defaultItemCreate(NAME, ItemType.IMAGE);
        final Item item1 = new ItemFactoryImpl().specificItemCreate(NAME, "3.10", ItemType.IMAGE);
        assertTrue(item instanceof Image);
        assertTrue(item1 instanceof Image);
        assertEquals("name", item.getName());
        assertEquals("latest", item.getFeature());
        assertEquals("3.10", item1.getFeature());
    }

    @Test
    public void networkFactoryTest() {
        final Item item = new ItemFactoryImpl().defaultItemCreate(NAME, ItemType.NETWORK);
        final Item item1 = new ItemFactoryImpl().specificItemCreate(NAME, "driver", ItemType.NETWORK);
        assertTrue(item instanceof Network);
        assertFalse(item1 instanceof Image);
        assertEquals(NAME, item.getName());
        assertEquals("bridge", item.getFeature());
        assertEquals("driver", item1.getFeature());
    }

    @Test
    public void volumeFactoryTest() {
        final Item item = new ItemFactoryImpl().specificItemCreate(NAME, "mountPath", ItemType.VOLUME);
        assertTrue(item instanceof Volume);
        assertEquals(NAME, item.getName());
        assertEquals("mountPath", item.getFeature());
    }

}
