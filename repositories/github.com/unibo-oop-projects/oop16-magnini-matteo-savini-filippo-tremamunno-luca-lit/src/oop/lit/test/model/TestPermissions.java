package oop.lit.test.model;
//CHECKSTYLE:OFF

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;

import org.junit.Test;

import oop.lit.model.PlayerModel;
import oop.lit.model.actions.AbstractAction;
import oop.lit.model.game.Player;
import oop.lit.model.util.permission.ActionsManager;
import oop.lit.model.util.permission.PermissionHolder.Permission;
import oop.lit.util.IllegalInputException;

/**
 * A class for testing ActionsManager.
 */
public class TestPermissions {
    @Test
    public void testActionsManager() {
        final ActionsManager am = new ActionsManager();
        final PlayerModel p1 = new Player("p1");
        final PlayerModel p2 = new Player("p2");
        final TestAction aa = new TestAction("a");
        final TestAction ab = new TestAction("b");
        final TestAction ac = new TestAction("c");
        final TestAction ad = new TestAction("d");
        final TestAction ae = new TestAction("e");

        assertTrue(am.addAction(aa));
        assertFalse(am.addAction(aa));
        assertTrue(am.addAction(ab));
        assertTrue(am.addAction(ac));
        assertEquals(3, am.getAllActions().size());
        assertTrue(am.addAction(ad));
        assertTrue(am.addAction(ae));
        assertEquals(5, am.getAllActions().size());

        assertEquals(0, am.getActions(p1, false).size());
        assertEquals(0, am.getActions(p1, true).size());
        am.setPermission(ad, p1, Permission.TURN_ONLY);
        try {
            am.setPermission(new TestAction("z"), p1, Permission.TURN_ONLY);
            fail("Action was not added");
        } catch (Exception e) {
            if (e.getClass() != IllegalArgumentException.class) {
                fail("Wrong exception");
            }
        }
        am.setPermission(ae, p1, Permission.ALWAYS);
        am.setPermission(ac, p1, Permission.TURN_ONLY);
        assertEquals(Arrays.asList(ac, ad, ae), am.getActions(p1, true));
        assertEquals(Arrays.asList(ae), am.getActions(p1, false));
        assertEquals(0, am.getActions(p2, true).size());
        am.setPermission(ae, p1, Permission.NEVER);
        assertEquals(Arrays.asList(ac, ad), am.getActions(p1, true));
        assertEquals(0, am.getActions(p1, false).size());

        am.setPermission(aa, p2, Permission.TURN_ONLY);
        am.setPermission(ab, p2, Permission.TURN_ONLY);
        am.setPermission(ac, p2, Permission.TURN_ONLY);
        assertEquals(0, am.getActions(p2, false).size());
        am.setPermission(ac, new Player("p2"), Permission.ALWAYS);
        assertEquals(Arrays.asList(ac), am.getActions(p2, false));
    }

    public static class TestAction extends AbstractAction {
        public TestAction(final String label) {
            super(label);
        }
        @Override
        public void perform() throws IllegalInputException {
        }
        
    }
}
