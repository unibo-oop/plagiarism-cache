package test;
/**
 * JUnit test
 */
import static org.junit.Assert.*;

import game.Players;
import game.Hostile;
import game.StaticImage;

public class Test {
    
    /* Test automatici con JUnit */
    
    @org.junit.Test
    public void testBasic() {
        
        Players players = new Players("/res/pacV.png",20,20,60,0,0);
        assertEquals(players.getSpeedX(), 0);
        assertEquals(players.getSpeedY(), 0);
        assertEquals(players.getLen(), 60);
        players.setLen(50);
        assertEquals(players.getLen(), 50);
        assertEquals(players.isInvulnerable(), false);
        players.setInvulnerable(true);
        assertEquals(players.isInvulnerable(), true);
        
        
        Hostile hostile = new Hostile("/res/rosa.png",20,20,60,0,0);
        hostile.setLen(50);
        assertEquals(hostile.getLen(), 50);
        assertFalse(hostile.isCrash());
        assertEquals(hostile.getX(), 20);
        assertEquals(hostile.getY(), 20);
        
        StaticImage image = new StaticImage("", 0, 0, 500, 100);
        assertEquals(image.isVisible(), true);
        image.setVisible(false);
        assertFalse(image.isVisible());
    }
        
}
