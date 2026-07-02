package javagotchi.test.minigame;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertThat;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import org.apache.commons.lang3.Pair;
import org.junit.Before;
import org.junit.Test;

import javagotchi.controller.minigame.audio.Music;
import javagotchi.controller.minigame.file.SavedData;
import javagotchi.controller.minigame.file.SavedDataImpl;
import javagotchi.controller.minigame.main.MiniGame;
import javagotchi.model.Javagotchi;
import javagotchi.model.JavagotchiImpl;
import javagotchi.model.information.Avatar;
import javagotchi.model.information.Gender;
import javagotchi.model.minigame.GameGrid;
import javagotchi.model.minigame.GameGridImpl;
import javagotchi.view.minigame.mainview.MiniGameView;
import javagotchi.view.minigame.mainview.MiniGameViewImpl;

/**
 * 
 * @author marica
 *
 */
public class Tests {

    private static final int SCOREMARY = 300;
    private static final int SCOREFILIPPO = 445;
    private static final int SCOREKIRA = 320;
    private static SavedData save;
    private static Map<String, Integer> bScore;
    private static Javagotchi java;


    /**
     * Initialization.
     */
    @Before
    public void initialize() {
        java = new JavagotchiImpl("Mary", Gender.FEMALE, Avatar.FOX);
        save = new SavedDataImpl(java.getInformation().getName());
        bScore = new LinkedHashMap<>();
        bScore.put("Mary", SCOREMARY);
        bScore.put("Filippo", SCOREFILIPPO);
        bScore.put("Kira", SCOREKIRA);
        MiniGame.getFactoryController().getControllerMiniGame().getModel().setGotchi(java);
    }

    /**
     * Test of GameTable.
     */
    @Test
    public void testAddCoord() {
        final int numberCoord = 20;
        final GameGrid gameGrid = new GameGridImpl();
        assertEquals("Dimension of game grid", gameGrid.getCoords().size(), numberCoord);
        assertTrue("Coordinates of game button",
                gameGrid.getCoords().containsAll(Arrays.asList(Pair.of(0, 0), Pair.of(0, 1), Pair.of(0, 2),
                        Pair.of(0, 3), Pair.of(1, 0), Pair.of(1, 1), Pair.of(1, 2), Pair.of(1, 3), Pair.of(2, 0),
                        Pair.of(2, 1), Pair.of(2, 2), Pair.of(2, 3), Pair.of(3, 0), Pair.of(3, 1), Pair.of(3, 2),
                        Pair.of(3, 3), Pair.of(4, 0), Pair.of(4, 1), Pair.of(4, 2), Pair.of(4, 3))));

        IntStream.range(0, 3).boxed().forEach(i -> {
            gameGrid.move();
        });

        assertTrue("Coordinates of game button",
                gameGrid.getCoords().containsAll(Arrays.asList(Pair.of(3, 0), Pair.of(3, 1), Pair.of(3, 2),
                        Pair.of(3, 3), Pair.of(4, 0), Pair.of(4, 1), Pair.of(4, 2), Pair.of(4, 3), Pair.of(0, 0),
                        Pair.of(0, 1), Pair.of(0, 2), Pair.of(0, 3), Pair.of(1, 0), Pair.of(1, 1), Pair.of(1, 2),
                        Pair.of(1, 3), Pair.of(2, 0), Pair.of(2, 1), Pair.of(2, 2), Pair.of(2, 3))));

    }

    /**
     * Test of Music.
     */
    @Test
    public void testMusic() {
        final int secondMusic = 5;
        final Music music = MiniGame.getFactoryController().getMusic();
        music.startAudio();
        sleepThread(secondMusic);
        assertTrue("Music is active", music.isOn());
        music.stopAudio();
        assertFalse("Music is deactivated", music.isOn());
    }

    private void sleepThread(final int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
        }
    }

    /**
     * Test of write and read of Best Score File.
     */
    @Test
    public void testBestScore() {
        save.writeBestScore(bScore);
        assertEquals("All Best Score of Javagotchi ", save.readBestScore(), bScore);
    }

    /**
     * Test of write and read of Saved Game.
     */
    @Test
    public void testSaveData() {
        final MiniGameView viewMini = new MiniGameViewImpl();
        IntStream.range(0, 3).boxed().forEach(i -> {
            viewMini.repaintGameView();
        });
        final Integer score = Integer.valueOf(500);
        final Integer sec = Integer.valueOf(9);
        final List<Object> list = Arrays.asList(score, viewMini.getButtons(), sec);

        save.writeGame(score, viewMini.getButtons(), sec);
        assertEquals("Size of saved list object ", save.readGameSaved().size(), list.size());

        assertThat("Saved game contains the score and seconds of time", save.readGameSaved(), hasItems(score, sec));
    }

}
