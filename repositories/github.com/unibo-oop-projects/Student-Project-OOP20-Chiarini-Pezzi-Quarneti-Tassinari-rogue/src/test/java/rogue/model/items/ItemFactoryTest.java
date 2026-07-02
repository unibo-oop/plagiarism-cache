package rogue.model.items;

import org.junit.Test;

import java.util.List;
import static org.junit.Assert.assertEquals;

public class ItemFactoryTest {

    @Test
    public void testItemsQuantity() {
        final ItemFactory itemF = new ItemFactoryImpl();
        final List<Item> items = itemF.getItems(4);

        assertEquals(4, items.size());
    }
}
