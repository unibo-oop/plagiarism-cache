package todo.vm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class ActionTest {
    @Test
    public void testToString() {
        assertEquals("NONE", Action.none().toString());
        assertEquals("PICK_INPUT", Action.pickInput().toString());
    }

    @Test
    public void testEquals() {
        assertEquals(Action.none(), Action.none());
        assertNotEquals(Action.none(), Action.pickInput());
        assertEquals(Action.locateMemoryAddress(1), Action.locateMemoryAddress(1));
        assertNotEquals(Action.locateMemoryAddress(1), Action.locateMemoryAddress(2));
    }

    @Test
    public void testGetKind() {
        assertEquals(Action.none().getKind(), ActionKind.NONE);
        assertEquals(Action.pickInput().getKind(), ActionKind.PICK_INPUT);
        assertEquals(Action.dropOutput().getKind(), ActionKind.DROP_OUTPUT);
        assertEquals(Action.locateMemoryAddress(0).getKind(), ActionKind.LOCATE_MEMORY_ADDRESS);
        assertEquals(Action.copyFrom(0).getKind(), ActionKind.COPY_FROM);
        assertEquals(Action.copyTo(0).getKind(), ActionKind.COPY_TO);
    }

    @Test
    public void testGetMemoryAddress() {
        assertEquals(Action.locateMemoryAddress(0).getMemoryAddress(), 0);
        assertEquals(Action.copyFrom(0).getMemoryAddress(), 0);
        assertEquals(Action.copyTo(0).getMemoryAddress(), 0);
    }

    @Test(expected = IllegalStateException.class)
    public void testGetMemoryAddressWithInvalidAction() {
        Action.dropMainHand().getMemoryAddress();
    }
}
