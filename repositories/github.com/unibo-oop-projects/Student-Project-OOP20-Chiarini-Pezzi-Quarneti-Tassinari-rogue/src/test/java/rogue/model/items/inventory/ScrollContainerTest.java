package rogue.model.items.inventory;

import org.junit.Test;

import rogue.model.creature.Player;
import rogue.model.creature.PlayerFactoryImpl;
import rogue.model.items.scroll.Scroll;
import rogue.model.items.scroll.ScrollImpl;
import rogue.model.items.scroll.ScrollType;

import static org.junit.Assert.assertTrue;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

public class ScrollContainerTest {

    private Player pl;

    @Test
    public void testActivateScroll() {
        pl = new PlayerFactoryImpl().create();
        final Inventory inv = new InventoryImpl(pl);
        final Scroll scroll = new ScrollImpl(ScrollType.SCROLL_II);
        /*
         * check to active scroll.
         */
        assertTrue(inv.getScrollContainer().getActiveScroll().isEmpty());
        /*
         * activate a scroll.
         */
        inv.getScrollContainer().activateScroll(scroll);
        /*
         * check if activated.
         */
        assertEquals(scroll, inv.getScrollContainer().getActiveScroll().get());
    }

    @Test
    public void testReplaceScroll() {
        //this test also tests removeScroll and getActiveScroll
        pl = new PlayerFactoryImpl().create();
        final Inventory inv = new InventoryImpl(pl);
        final Scroll scroll = new ScrollImpl(ScrollType.SCROLL_II);
        final Scroll scroll2 = new ScrollImpl(ScrollType.SCROLL_II);
        /*
         * activate a scroll.
         */
        inv.getScrollContainer().activateScroll(scroll);
        /*
         * check if activated.
         */
        assertEquals(scroll, inv.getScrollContainer().getActiveScroll().get());
        /*
         * activate another one.
         */
        inv.getScrollContainer().activateScroll(scroll2);
        /*
         * check for new activated scroll.
         */
        assertEquals(scroll2, inv.getScrollContainer().getActiveScroll().get());
    }

    @Test
    public void testUpdateEffectDuration() {
        //this test also tests removeScroll and getActiveScroll
        pl = new PlayerFactoryImpl().create();
        final Inventory inv = new InventoryImpl(pl);
        final Scroll scroll = new ScrollImpl(ScrollType.SCROLL_II);
        /*
         * updateEffectDuration does nothing if no active scroll.
         */
        assertFalse(inv.getScrollContainer().updateEffectDuration(1));
        /*
         * activate a scroll.
         */
        inv.getScrollContainer().activateScroll(scroll);
        /*
         * check if activated.
         */
        assertEquals(scroll, inv.getScrollContainer().getActiveScroll().get());
        /*
         * reduce the scroll's duration by one.
         */
        final int before = inv.getScrollContainer().getActiveScrollDuration();
        assertTrue(inv.getScrollContainer().updateEffectDuration(2));
        assertEquals(before - 2, inv.getScrollContainer().getActiveScrollDuration());
    }

    @Test
    public void testUseActivateScroll() throws InventoryIsFullException, OutOfInventoryException {
        pl = new PlayerFactoryImpl().create();
        final Inventory inv = new InventoryImpl(pl);
        final Scroll scroll = new ScrollImpl(ScrollType.SCROLL_II);

        inv.addItem(scroll);
        inv.useItem(1);
        assertFalse(inv.getScrollContainer().getActiveScroll().isEmpty());
    }
}
