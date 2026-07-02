package model.players;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;

import org.junit.Test;

import controller.PlayerColor;

/**
 * Thi is a test class for PlayerCircularQueue class.
 */
public class TestPlayerCircularQueue {

    private static final Player PLAYERONE = new PlayerImpl("Matteo", PlayerColor.GREEN);
    private static final Player PLAYERTWO = new PlayerImpl("Luca", PlayerColor.ORANGE);
    private static final Player PLAYERTHREE = new PlayerImpl("Paolo", PlayerColor.RED);
    private static final Player EXTRAPLAYER = PLAYERONE;
    private static final int NUMBEROFPLAYERS = 3;
    private final PlayerCircularQueue pCQ = new PlayerCircularQueue(NUMBEROFPLAYERS);

    /**
     * Here we test if the pCQ can handle the right number of players.
     */
    @Test
    public void canAdd() {
        this.pCQ.offer(PLAYERONE);
        this.pCQ.offer(PLAYERTWO);
        this.pCQ.offer(PLAYERTHREE);
        assertTrue("Wrong size of the pCQ, probabli offer() failed", ((Integer) this.pCQ.size()).equals(NUMBEROFPLAYERS));
        this.pCQ.clear();
    }

    /**
     * We test if offer return the right boolean.
     */
    @Test
    public void stopAdding() {
        final String failMsg = "pCQ accepted more player than expected";
        this.pCQ.offer(PLAYERONE);
        this.pCQ.offer(PLAYERTWO);
        this.pCQ.offer(PLAYERTHREE);
        assertFalse(failMsg, this.pCQ.offer(EXTRAPLAYER));
        assertFalse(failMsg, this.pCQ.offer(EXTRAPLAYER));
        assertFalse(failMsg, this.pCQ.offer(EXTRAPLAYER));
        assertFalse(failMsg, this.pCQ.offer(EXTRAPLAYER));
        assertFalse(failMsg, this.pCQ.offer(EXTRAPLAYER));
        assertFalse(failMsg, this.pCQ.offer(EXTRAPLAYER));
        this.pCQ.clear();
    }

    /**
     * We test if can't remove the head of the queue.
     */
    @Test(expected = NoSuchElementException.class)
    public void removeFromEmpty() {
        this.pCQ.remove();
    }

    /**
     * We test the remove player possibility.
     */
    @Test
    public void removePlayer() {
        this.pCQ.offer(PLAYERONE);
        this.pCQ.offer(PLAYERTWO);
        this.pCQ.offer(PLAYERTHREE);
        assertEquals("Operation removeFromQueue gone bad", this.pCQ.removeFromQueue(PLAYERTWO), PLAYERTWO);
        assertEquals("Operation removeFromQueue gone bad", this.pCQ.removeFromQueue(PLAYERONE), PLAYERONE);
        assertEquals("Operation removeFromQueue gone bad", this.pCQ.removeFromQueue(PLAYERTHREE), PLAYERTHREE);
        this.pCQ.clear();
    }

    /**
     * We test the right sequence of player in peek operation.
     */

    @Test
    public void peekPoolTest() {
        this.pCQ.offer(PLAYERONE);
        this.pCQ.offer(PLAYERTWO);
        this.pCQ.offer(PLAYERTHREE);
        assertEquals("Wrong expected player in peek() operation", pCQ.peek(), PLAYERONE);
        assertEquals("Wrong expected player in poll() operation", pCQ.poll(), PLAYERONE);
        assertEquals("Wrong expected player in peek() operation", pCQ.peek(), PLAYERTWO);
        assertEquals("Wrong expected player in poll() operation", pCQ.poll(), PLAYERTWO);
        assertEquals("Wrong expected player in peek() operation", pCQ.peek(), PLAYERTHREE);
        assertEquals("Wrong expected player in poll() operation", pCQ.poll(), PLAYERTHREE);
        assertNotEquals("Peek operation gone bad", pCQ.peek(), PLAYERTHREE);
        assertNotEquals("Poll operation gone bad", pCQ.poll(), PLAYERTHREE);
        this.pCQ.clear();
    }

}
