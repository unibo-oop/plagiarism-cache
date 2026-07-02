package rogue.model.items.inventory;

import org.junit.Test;

import rogue.model.creature.Player;
import rogue.model.creature.PlayerFactoryImpl;
import rogue.model.items.food.Food;
import rogue.model.items.food.FoodImpl;
import rogue.model.items.food.FoodType;
import rogue.model.items.potion.Potion;
import rogue.model.items.potion.PotionImpl;
import rogue.model.items.potion.PotionType;
import rogue.model.items.scroll.Scroll;
import rogue.model.items.scroll.ScrollImpl;
import rogue.model.items.scroll.ScrollType;

import static org.junit.Assert.assertTrue;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

public class InventoryImplTest {

    private static final int INVENTORY_SIZE = 20;
    private static final int ITEM_AMOUNT_MAX = 10;

    private Player pl;

    @Test
    public void testAddItemAndGetItemPotion() throws OutOfInventoryException {
        pl = new PlayerFactoryImpl().create();
        final Inventory inv = new InventoryImpl(pl);
        final Potion potion = new PotionImpl(PotionType.POTION_I);
        /*
         * Add item to inventory, expect true and correct
         * inventory update.
         */
        try {
            assertTrue(inv.addItem(potion));
        } catch (InventoryIsFullException e) {
            e.printStackTrace();
        }
        assertTrue(inv.getItem(1).isPresent());
        assertFalse(inv.getItem(2).isPresent());
        assertEquals(potion, inv.getItem(1).get());
    }

    @Test
    public void testAddTwoItemScroll() throws OutOfInventoryException {
        pl = new PlayerFactoryImpl().create();
        final Inventory inv = new InventoryImpl(pl);
        final Scroll scroll = new ScrollImpl(ScrollType.SCROLL_II);
        final Scroll scroll2 = new ScrollImpl(ScrollType.SCROLL_V);
        /*
         * Add item to inventory, expect true and correct
         * inventory update.
         */
        try {
            assertTrue(inv.addItem(scroll));
        } catch (InventoryIsFullException e) {
            e.printStackTrace();
        }
        assertTrue(inv.getItem(1).isPresent());
        assertFalse(inv.getItem(2).isPresent());
        assertEquals(scroll, inv.getItem(1).get());
        /*
         * Add a different scroll, expect different item
         * to be stored in slot 2.
         */
        try {
            assertTrue(inv.addItem(scroll2));
        } catch (InventoryIsFullException e) {
            e.printStackTrace();
        }
        assertTrue(inv.getItem(1).isPresent());
        assertEquals(scroll, inv.getItem(1).get());
        assertTrue(inv.getItem(2).isPresent());
        assertEquals(scroll2, inv.getItem(2).get());
    }

    @Test
    public void testAddTwoOfSameItems() throws OutOfInventoryException {
        pl = new PlayerFactoryImpl().create();
        final Inventory inv = new InventoryImpl(pl);
        final Scroll scroll = new ScrollImpl(ScrollType.SCROLL_II);
        /*
         * Add two of the same items.
         * Expect true and correct quantity update.
         */
        try {
            assertTrue(inv.addItem(scroll));
            assertTrue(inv.addItem(scroll));
        } catch (InventoryIsFullException e) {
            e.printStackTrace();
        }
        assertEquals(2, inv.getAmount(1));
    }

    @Test
    public void testMaxOfSameItem() throws OutOfInventoryException {
        pl = new PlayerFactoryImpl().create();
        final Inventory inv = new InventoryImpl(pl);
        final Scroll scroll = new ScrollImpl(ScrollType.SCROLL_II);
        /*
         * Add 11 of same item, expect 11th add false
         * and correct update of quantity to 10
         */
        try {
            for (int i = 0; i < 10; i++) {
                assertTrue(inv.addItem(scroll));
            }
            assertFalse(inv.addItem(scroll));
        } catch (InventoryIsFullException e) {
            e.printStackTrace();
        }
        assertEquals(ITEM_AMOUNT_MAX, inv.getAmount(1));
    }

    @Test
    public void testGetAmount() throws OutOfInventoryException {
        pl = new PlayerFactoryImpl().create();
        final Inventory inv = new InventoryImpl(pl);
        final Scroll scroll = new ScrollImpl(ScrollType.SCROLL_II);
        /*
         * Add 3 scroll.
         */
        try {
            for (int i = 0; i < 3; i++) {
                assertTrue(inv.addItem(scroll));
            }
        } catch (InventoryIsFullException e) {
            e.printStackTrace();
        }
        assertEquals(3, inv.getAmount(1));
    }

    @Test
    public void testSwap() throws OutOfInventoryException {
        pl = new PlayerFactoryImpl().create();
        final Inventory inv = new InventoryImpl(pl);
        final Scroll scroll = new ScrollImpl(ScrollType.SCROLL_II);
        final Potion potion = new PotionImpl(PotionType.POTION_I);
        /*
         * Add a scroll and a potion.
         */
        try {
            assertTrue(inv.addItem(scroll));
            assertTrue(inv.addItem(potion));
        } catch (InventoryIsFullException e) {
            e.printStackTrace();
        }
        /*
         * check scroll in first slot and potion in second
         */
        assertEquals(scroll, inv.getItem(1).get());
        assertEquals(potion, inv.getItem(2).get());
        /*
         * swap them
         */
        assertTrue(inv.swap(1, 2));
        /*
         * check for swap.
         */
        assertEquals(potion, inv.getItem(1).get());
        assertEquals(scroll, inv.getItem(2).get());
        /*
         * now swap first with third slot which is empty.
         */
        assertTrue(inv.swap(1, 3));
        /*
         * check correct swap
         */
        assertTrue(inv.getItem(1).isEmpty());
        assertEquals(potion, inv.getItem(3).get());
        /*
         * try to swap two empty slots, expect false.
         */
        assertFalse(inv.swap(4, 1));
    }

    @Test
    public void testRemove() throws OutOfInventoryException, InventoryIsFullException {
        pl = new PlayerFactoryImpl().create();
        final Inventory inv = new InventoryImpl(pl);
        final Scroll scroll = new ScrollImpl(ScrollType.SCROLL_II);
        /*
         * Try to remove empty slot.
         */
        assertFalse(inv.remove(1));
        /*
         * Add scroll and remove it.
         */
        assertTrue(inv.addItem(scroll));
        assertTrue(inv.remove(1));
        /*
         * check the slot
         */
        assertTrue(inv.getItem(1).isEmpty()); 
    }

    @org.junit.Test(expected = OutOfInventoryException.class)
    public void testIndexOutOfInventoryUse() throws OutOfInventoryException {
        pl = new PlayerFactoryImpl().create();
        final Inventory inv = new InventoryImpl(pl);
        inv.useItem(INVENTORY_SIZE + 10);
    }

    @org.junit.Test(expected = OutOfInventoryException.class)
    public void testIndexOutOfInventoryRemove() throws OutOfInventoryException {
        pl = new PlayerFactoryImpl().create();
        final Inventory inv = new InventoryImpl(pl);
        inv.remove(INVENTORY_SIZE + 10);
    }

    @org.junit.Test(expected = OutOfInventoryException.class)
    public void testIndexOutOfInventoryGetAmount() throws OutOfInventoryException {
        pl = new PlayerFactoryImpl().create();
        final Inventory inv = new InventoryImpl(pl);
        inv.getAmount(INVENTORY_SIZE + 10);
    }

    @org.junit.Test(expected = OutOfInventoryException.class)
    public void testIndexOutOfInventorySwap() throws OutOfInventoryException {
        pl = new PlayerFactoryImpl().create();
        final Inventory inv = new InventoryImpl(pl);
        inv.swap(INVENTORY_SIZE + 10, 1);
    }

    @org.junit.Test(expected = OutOfInventoryException.class)
    public void testIndexOutOfInventoryGetItem() throws OutOfInventoryException {
        pl = new PlayerFactoryImpl().create();
        final Inventory inv = new InventoryImpl(pl);
        inv.getItem(INVENTORY_SIZE + 10);
    }

    @org.junit.Test(expected = InventoryIsFullException.class)
    public void testFullInventory() throws InventoryIsFullException, OutOfInventoryException {
        pl = new PlayerFactoryImpl().create();
        final Inventory inv = new InventoryImpl(pl);
        final Food food1 = new FoodImpl(FoodType.APPLE);
        final Food food2 = new FoodImpl(FoodType.BREAD);
        final Food food3 = new FoodImpl(FoodType.CAKE);
        final Food food4 = new FoodImpl(FoodType.CHEESE);
        final Food food5 = new FoodImpl(FoodType.HAMBURGER);
        final Food food6 = new FoodImpl(FoodType.SOUP);
        final Food food7 = new FoodImpl(FoodType.STEAK);
        final Potion potion1 = new PotionImpl(PotionType.POTION_I);
        final Potion potion2 = new PotionImpl(PotionType.POTION_II);
        final Potion potion3 = new PotionImpl(PotionType.POTION_III);
        final Potion potion4 = new PotionImpl(PotionType.POTION_IV);
        final Potion potion5 = new PotionImpl(PotionType.POTION_V);
        final Potion potion6 = new PotionImpl(PotionType.CORRUPT_POTION_I);
        final Potion potion7 = new PotionImpl(PotionType.CORRUPT_POTION_II);
        final Scroll scroll1 = new ScrollImpl(ScrollType.SCROLL_I);
        final Scroll scroll2 = new ScrollImpl(ScrollType.SCROLL_II);
        final Scroll scroll3 = new ScrollImpl(ScrollType.SCROLL_III);
        final Scroll scroll4 = new ScrollImpl(ScrollType.SCROLL_IV);
        final Scroll scroll5 = new ScrollImpl(ScrollType.SCROLL_V);
        final Scroll scroll6 = new ScrollImpl(ScrollType.CORRUPT_SCROLL_I);
        final Scroll scroll7 = new ScrollImpl(ScrollType.CORRUPT_SCROLL_II);
        /*
         * Fill the inventory.
         */
        for (int i = 0; i < 10; i++) {
            assertTrue(inv.addItem(food1));
        }
        for (int i = 0; i < 10; i++) {
            assertTrue(inv.addItem(food2));
        }
        for (int i = 0; i < 10; i++) {
            assertTrue(inv.addItem(food3));
        }
        for (int i = 0; i < 10; i++) {
            assertTrue(inv.addItem(food4));
        }
        for (int i = 0; i < 10; i++) {
            assertTrue(inv.addItem(food5));
        }
        for (int i = 0; i < 10; i++) {
            assertTrue(inv.addItem(food6));
        }
        for (int i = 0; i < 10; i++) {
            assertTrue(inv.addItem(food7));
        }
        for (int i = 0; i < 10; i++) {
            assertTrue(inv.addItem(potion1));
        }
        for (int i = 0; i < 10; i++) {
            assertTrue(inv.addItem(potion2));
        }
        for (int i = 0; i < 10; i++) {
            assertTrue(inv.addItem(potion3));
        }
        for (int i = 0; i < 10; i++) {
            assertTrue(inv.addItem(potion4));
        }
        for (int i = 0; i < 10; i++) {
            assertTrue(inv.addItem(potion5));
        }
        for (int i = 0; i < 10; i++) {
            assertTrue(inv.addItem(potion6));
        }
        for (int i = 0; i < 10; i++) {
            assertTrue(inv.addItem(potion7));
        }
        for (int i = 0; i < 10; i++) {
            assertTrue(inv.addItem(scroll1));
        }
        for (int i = 0; i < 10; i++) {
            assertTrue(inv.addItem(scroll2));
        }
        for (int i = 0; i < 10; i++) {
            assertTrue(inv.addItem(scroll3));
        }
        for (int i = 0; i < 10; i++) {
            assertTrue(inv.addItem(scroll4));
        }
        for (int i = 0; i < 10; i++) {
            assertTrue(inv.addItem(scroll5));
        }
        for (int i = 0; i < 10; i++) {
            assertTrue(inv.addItem(scroll6));
        }
        /*
         * Inventory should be full
         */
        inv.addItem(scroll7);
    }
}
