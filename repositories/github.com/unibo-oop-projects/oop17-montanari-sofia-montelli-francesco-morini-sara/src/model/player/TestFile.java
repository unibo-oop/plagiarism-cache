package model.player;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import model.data_access.FileDataAccessManager;
import model.data_access.FileDataAccessManagerImpl;

/**
 * Class test for the manage of the file.
 */
public class TestFile {
    /**
     * Name of the save file.
     */
    private static final String FILE_NAME = "prova.txt";
    /**
     * Player's test name.
     */
    private static final String MARIO = "Mario";
    /**
     * Player's test name.
     */
    private static final String LUCA = "Luca";
    /**
     * Player's test name.
     */
    private static final String GIOVANNI = "Giovanni";

    /**
     * This method is performed before any test.
     * @throws IOException if the deletion can't be performed
     */
    @Before
    public void init() throws IOException {
        final File file = new File(FILE_NAME);

        if (!file.delete()) {
            if (!file.createNewFile()) {
                throw new IOException();
            }
        }
    }

    /**
     * Test for file.
     */
    @Test
    public void testFile() {
        final FileDataAccessManager<Player> file = new FileDataAccessManagerImpl<>(FILE_NAME);
        final Player player1 = new PlayerImpl(MARIO);
        final Set<Player> set = new HashSet<Player>();
        assertEquals("File name", file.getFileName(), FILE_NAME);
        file.saveNewElement(player1);
        file.reloadFromDataSet();
        set.add(player1);
        assertEquals("Get player set after saving", file.getSet(), set);
        set.remove(player1);
        file.removeElement(player1);
        file.reloadFromDataSet();
        assertEquals("Get player set after removing", file.getSet(), set);
    }
    /**
     * Test for the method getPlayerNameOrderedList.
     * @throws InterruptedException is an exception the could throw the class Thread
     */
    @Test
    public void testNameOreder() throws InterruptedException {
        final PlayersManipulationDB dabaBase = new PlayersManipulationDBImpl(FILE_NAME);
        dabaBase.newPlayer(MARIO);
        Thread.sleep(1000 * 4);
        dabaBase.newPlayer(LUCA);
        Thread.sleep(1000 * 4);
        dabaBase.newPlayer(GIOVANNI);
        assertEquals("test after creation of the player only", new ArrayList<>(Arrays.asList(GIOVANNI, LUCA, MARIO)), dabaBase.getPlayerNameOrderedList());
        dabaBase.selectedPlayer(dabaBase.getPlayer(MARIO));
        assertEquals("test after selecting player", new ArrayList<>(Arrays.asList(MARIO, GIOVANNI, LUCA)), dabaBase.getPlayerNameOrderedList());
    }
}
