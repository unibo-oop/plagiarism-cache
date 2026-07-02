package model.player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.Test;
/**
 * Class test for the player.
 */
public class TestPlayer {
    /**
     * Test for player creation.
     */
    @Test
    public void testPlayer() {
        final Player player1 = new PlayerImpl("Mario");
        final Player player2 = new PlayerImpl("Mario");
        final Player player3 = new PlayerImpl("Luca");
        assertEquals("Players", player1, player2);
        assertNotEquals(player1, player3);
        assertEquals("Player's name", player1.getName(), "Mario");
        assertEquals("Creation Date_Year", player1.getDateCreation().getYear(), LocalDate.now().getYear());
        assertEquals("Creation Date_Month", player1.getDateCreation().getMonth(), LocalDate.now().getMonth());
        assertEquals("Creation Date_Day", player1.getDateCreation().getDayOfMonth(), LocalDate.now().getDayOfMonth());
        assertEquals("Date of the last access", player1.getTimeLastAccess(), LocalDateTime.now());
        player1.setTimeLastAccess();
        assertEquals("Date of the last access after setting", player1.getTimeLastAccess(), LocalDateTime.now());
        assertEquals("Date of the last access_Year", player1.getTimeLastAccess().getYear(), LocalDate.now().getYear());
        assertEquals("Date of the last access_Month", player1.getTimeLastAccess().getMonth(), LocalDate.now().getMonth());
        assertEquals("Date of the last access_Day", player1.getTimeLastAccess().getDayOfMonth(), LocalDate.now().getDayOfMonth());
    }
}
