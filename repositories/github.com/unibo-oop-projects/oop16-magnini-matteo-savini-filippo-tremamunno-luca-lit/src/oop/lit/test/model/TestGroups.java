package oop.lit.test.model;
//CHECKSTYLE:OFF
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

import oop.lit.model.Action;
import oop.lit.model.GameElementModel;
import oop.lit.model.PlayerModel;
import oop.lit.model.elements.GameElement;
import oop.lit.model.groups.AbstractSelectableElementGroup;
import oop.lit.model.groups.ElementGroup;
import oop.lit.model.groups.ElementGroupImpl;
import oop.lit.model.groups.OrderedSelectableGroup;
import oop.lit.model.groups.SelectableElementGroup;
import oop.lit.util.IllegalInputException;

/**
 * A class for testing ElementGroups.
 */
public class TestGroups {
    @Test
    public void testElementGroup() {
        final ElementGroup<GameElement> group = new ElementGroupImpl<>(Optional.empty());
        final NotifyCounter counter = new NotifyCounter();
        group.attach(counter);
        final GameElement e1 = new TestElements.GE(Optional.of("e1"));
        final GameElement e2 = new TestElements.GE(Optional.of("e2"));
        final GameElement e3 = new TestElements.GE(Optional.of("e3"));
        assertTrue(group.addElement(e1));
        assertEquals(1, counter.getCount());
        assertFalse(group.addElement(e1));
        assertEquals(1, counter.getCount());
        assertTrue(group.addElement(e3));
        assertTrue(group.addElement(e2));
        assertEquals(3, counter.getCount());
        assertEquals(Arrays.asList(e1, e3, e2), group.getElements());
        assertEquals(3, counter.getCount());

        assertFalse(group.removeElement(new TestElements.GE(Optional.of("e3"))));
        assertEquals(Arrays.asList(e1, e3, e2), group.getElements());
        assertEquals(3, counter.getCount());

        assertTrue(group.removeElement(e3));
        assertEquals(4, counter.getCount());
        assertEquals(Arrays.asList(e1, e2), group.getElements());

        assertTrue(group.removeElement(e2));
        assertEquals(5, counter.getCount());
        assertEquals(Arrays.asList(e1), group.getElements());

        assertTrue(group.addElement(e3));
        assertEquals(6, counter.getCount());
        assertEquals(Arrays.asList(e1, e3), group.getElements());

        group.clear();
        assertEquals(7, counter.getCount());
        assertEquals(Collections.emptyList(), group.getElements());
    }

    @Test
    public void testSelectableGroup() {
        final SelectableElementGroup<GameElement> group = new SG<>();
        final NotifyCounter counter = new NotifyCounter();
        group.attach(counter);
        final GameElement e1 = new TestElements.GE(Optional.of("e1"));
        final GameElement e2 = new TestElements.GE(Optional.of("e2"));
        final GameElement e3 = new TestElements.GE(Optional.of("e3"));
        assertTrue(group.addElement(e1));
        assertTrue(group.addElement(e2));
        assertTrue(group.addElement(e3));
        assertEquals(3, counter.getCount());
        assertEquals(Collections.emptyList(), group.getSelected());
        assertTrue(group.select(e1));
        assertEquals(4, counter.getCount());
        assertFalse(group.select(e1));
        assertEquals(4, counter.getCount());
        assertTrue(group.select((GameElementModel) e3));
        assertEquals(5, counter.getCount());
        assertTrue(group.select(e2));
        assertEquals(6, counter.getCount());
        assertFalse(group.select(new TestElements.GE(Optional.of("e2"))));
        assertEquals(6, counter.getCount());
        assertEquals(Arrays.asList(e1, e3, e2), group.getSelected());
        assertSame(e3, group.getSelected().get(1));

        assertTrue(group.deselect((GameElementModel) e1));
        assertEquals(7, counter.getCount());
        assertEquals(Arrays.asList(e3, e2), group.getSelected());
        group.clearSelection();
        assertEquals(8, counter.getCount());
        assertEquals(Collections.emptyList(), group.getSelected());
        assertTrue(group.select(e1));
        assertEquals(9, counter.getCount());
        assertEquals(Arrays.asList(e1), group.getSelected());
    }

    @Test
    public void testOrdered() throws IllegalInputException {
        final OSG<GameElement> group = new OSG<>();
        final NotifyCounter counter = new NotifyCounter();
        final GameElement e1 = new TestElements.GE(Optional.of("e1"));
        final GameElement e2 = new TestElements.GE(Optional.of("e2"));
        final GameElement e3 = new TestElements.GE(Optional.of("e3"));
        final GameElement e4 = new TestElements.GE(Optional.of("e3"));
        final GameElement e5 = new TestElements.GE(Optional.of("e3"));

        group.addElement(e1);
        group.addElement(e2);
        group.addElement(e3);
        group.addElement(e4);
        group.addElement(e5);

        assertFalse(group.getMoveToHeadAction().canBePerformed());
        assertFalse(group.getMoveToTailAction().canBePerformed());
        group.select(e1);
        group.select(e2);
        group.select(e4);
        assertFalse(group.getMoveToHeadAction().canBePerformed());
        assertTrue(group.getMoveToTailAction().canBePerformed());
        group.select(e5);
        group.deselect(e1);
        assertTrue(group.getMoveToHeadAction().canBePerformed());
        assertFalse(group.getMoveToTailAction().canBePerformed());
        group.deselect(e5);
        assertTrue(group.getMoveToHeadAction().canBePerformed());
        assertTrue(group.getMoveToTailAction().canBePerformed());
        group.select(e3);
        group.attach(counter);
        group.getMoveToHeadAction().perform();
        assertEquals(1, counter.getCount());
        assertEquals(Arrays.asList(e2, e3, e4, e1, e5), group.getElements());
        assertFalse(group.getMoveToHeadAction().canBePerformed());
        group.detach(counter);
        group.deselect(e4);
        group.select(e1);
        group.attach(counter);
        group.getMoveToTailAction().perform();
        assertEquals(2, counter.getCount());
        assertEquals(Arrays.asList(e4, e2, e3, e5, e1), group.getElements());
    }
    public static class SG<H extends GameElement> extends AbstractSelectableElementGroup<H> {
        /**
         * 
         */
        private static final long serialVersionUID = -983246134149623522L;
        public SG() {
            super(Optional.empty());
        }
        @Override
        public List<Action> getSelectedActions(final PlayerModel playingPlayer, final List<? extends PlayerModel> turnPlayers) {
            return null;
        }
    }
    public static class OSG<H extends GameElement> extends OrderedSelectableGroup<H> {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;

        public OSG() {
            super(Optional.empty());
        }
        @Override
        public Action getMoveToHeadAction() { //NOPMD
            return super.getMoveToHeadAction();
        }
        @Override
        public Action getMoveToTailAction() { //NOPMD
            return super.getMoveToTailAction();
        }
        @Override
        public List<Action> getSelectedActions(final PlayerModel playingPlayer, final List<? extends PlayerModel> turnPlayers) {
            return null;
        }
    }
}
