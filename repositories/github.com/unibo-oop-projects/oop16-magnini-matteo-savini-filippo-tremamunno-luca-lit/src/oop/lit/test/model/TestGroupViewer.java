package oop.lit.test.model;
//CHECKSTYLE:OFF
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.Test;

import oop.lit.model.Action;
import oop.lit.model.ElementGroupModel;
import oop.lit.model.PlayerModel;
import oop.lit.model.SelectableElementGroupModel;
import oop.lit.model.elements.BoardElement;
import oop.lit.model.elements.GameElement;
import oop.lit.model.groups.AbstractSelectableElementGroup;
import oop.lit.model.groups.ElementGroup;
import oop.lit.model.groups.ElementGroupImpl;
import oop.lit.model.groups.GroupViewer;
import oop.lit.model.groups.SelectableElementGroup;

/**
 * A class used to test GroupViewer.
 */
public class TestGroupViewer {
    @Test
    public void testGV() {
        final GroupViewer gv = new GroupViewer();
        final NotifyCounter counter = new NotifyCounter();
        gv.attach(counter);
        final TestRemovableGroup rg = new TestRemovableGroup();
        final SelectableElementGroup<GameElement> sg1 = new TestRemovableGroup();
        final SelectableElementGroup<GameElement> sg2 = new TestRemovableGroup();
        final ElementGroup<BoardElement> g1 = new ElementGroupImpl<>(Optional.empty());
        final ElementGroup<GameElement> g2 = new ElementGroupImpl<>(Optional.empty());

        assertTrue(gv.getNonSelectableGroups().isEmpty());
        assertTrue(gv.getSelectableGroups().isEmpty());
        assertTrue(gv.showSelectable(sg1));
        assertFalse(gv.showSelectable(sg1));
        assertEquals(1, counter.getCount());
        assertTrue(gv.getNonSelectableGroups().isEmpty());
        assertEquals(buildSelectableGroupSet(sg1), gv.getSelectableGroups());
        assertTrue(gv.showGroup(sg1));
        assertEquals(2, counter.getCount());
        assertEquals(buildGroupSet(sg1), gv.getNonSelectableGroups());
        assertTrue(gv.getSelectableGroups().isEmpty());

        assertTrue(gv.showGroup(g1));
        assertTrue(gv.showGroup(g2));
        assertTrue(gv.showSelectable(sg2));
        assertTrue(gv.showSelectable(rg));
        assertEquals(6, counter.getCount());
        assertEquals(buildGroupSet(sg1, g1, g2), gv.getNonSelectableGroups());
        assertEquals(buildSelectableGroupSet(sg2, rg), gv.getSelectableGroups());
        rg.setVisualizable(false);
        assertEquals(buildSelectableGroupSet(sg2), gv.getSelectableGroups());
        assertEquals(7, counter.getCount());

        rg.setVisualizable(true); //controllo se per qualche motivo rendendo nuovamente visualizzabile il gruppo succede roba strana
        assertEquals(buildSelectableGroupSet(sg2), gv.getSelectableGroups());
        assertTrue(sg2.addElement(new TestElements.GE(Optional.empty()))); //il gruppo notifica il cambiamento, ma non deve succedere niente
        assertEquals(buildSelectableGroupSet(sg2), gv.getSelectableGroups());

        assertTrue(gv.stopShowing(g1));
        assertEquals(8, counter.getCount());
        assertFalse(gv.stopShowing(g1));
        assertEquals(8, counter.getCount());
        assertTrue(gv.stopShowing(new ElementGroupModel(sg2)));
        assertEquals(9, counter.getCount());
        assertTrue(gv.showGroup(rg));
        assertEquals(10, counter.getCount());
        assertTrue(gv.showSelectable(sg1));
        assertEquals(11, counter.getCount());
        assertTrue(gv.showGroup(sg1));
        assertEquals(12, counter.getCount());
        assertEquals(buildGroupSet(g2, rg, sg1), gv.getNonSelectableGroups());
        assertTrue(gv.getSelectableGroups().isEmpty());
        rg.setVisualizable(false);
        assertEquals(13, counter.getCount());
        assertEquals(buildGroupSet(g2, sg1), gv.getNonSelectableGroups());

        try {
            gv.showGroup(rg);
            fail("can't add not visualizable groups");
        } catch (Exception e) {
            if (e.getClass() != IllegalArgumentException.class) {
                fail("wrong exception thrown");
            }
        }
        try {
            gv.showSelectable(rg);
            fail("can't add not visualizable groups");
        } catch (Exception e) {
            if (e.getClass() != IllegalArgumentException.class) {
                fail("wrong exception thrown");
            }
        }

        assertTrue(gv.showSelectable(sg2));
        assertEquals(buildSelectableGroupSet(sg2), gv.getSelectableGroups());
        assertEquals(14, counter.getCount());
        assertTrue(gv.stopShowing(sg2));
        assertTrue(gv.getSelectableGroups().isEmpty());
        assertEquals(15, counter.getCount());
        assertTrue(gv.showSelectable(sg2));
        assertEquals(buildSelectableGroupSet(sg2), gv.getSelectableGroups());
        assertEquals(16, counter.getCount());
        assertTrue(gv.stopShowing(new SelectableElementGroupModel(sg2)));
        assertTrue(gv.getSelectableGroups().isEmpty());
        assertEquals(17, counter.getCount());
    }
    /**
     * Builds a set of ElementGroupModel from the provided groups
     * @param groups
     *      the groups that need to be in the set.
     * @return
     *      the set
     */
    public static Set<ElementGroupModel> buildGroupSet(final ElementGroup<?>... groups) {
        final Set<ElementGroupModel> res = new HashSet<>();
        for (final ElementGroup<?> group : groups) {
            res.add(new ElementGroupModel(group));
        }
        return res;
    }
    /**
     * Builds a set of SelectableElementGroupModel from the provided groups
     * @param groups
     *      the groups that need to be in the set.
     * @return
     *      the set
     */
    public static Set<SelectableElementGroupModel> buildSelectableGroupSet(final SelectableElementGroup<?>... groups) {
        final Set<SelectableElementGroupModel> res = new HashSet<>();
        for (final SelectableElementGroup<?> group : groups) {
            res.add(new SelectableElementGroupModel(group));
        }
        return res;
    }

    public static class TestRemovableGroup extends AbstractSelectableElementGroup<GameElement> {
        /**
         * 
         */
        private static final long serialVersionUID = 7225119857755466668L;
        private boolean visualizable = true;
        public TestRemovableGroup() {
            super(Optional.empty());
        }
        @Override
        public List<Action> getSelectedActions(final PlayerModel playingPlayer, final List<? extends PlayerModel> turnPlayers) {
            return Collections.emptyList();
        }
        @Override
        public boolean isVisualizable() {
            return this.visualizable;
        }
        public void setVisualizable(final boolean visualizable) {
            this.visualizable = visualizable;
            this.notifyObservers();
        }
    }
}
