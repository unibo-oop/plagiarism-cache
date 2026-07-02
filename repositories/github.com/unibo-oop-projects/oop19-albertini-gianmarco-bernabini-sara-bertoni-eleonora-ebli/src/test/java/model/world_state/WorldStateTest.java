package model.world_state;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.util.Pair;
import model.word.Word;
import model.word.WordImpl;

public class WorldStateTest {

    /**
     * we are going to create 4 words.
     */
    private Word w1 = new WordImpl("sky", 0.1, new Pair<Double, Double>(10.5, 0.0));
    private Word w2 = new WordImpl("banana", 0.05, new Pair<Double, Double>(13.5, 0.0));
    private Word w3 = new WordImpl("chanel", 0.7, new Pair<Double, Double>(18.0, 0.0));
    private Word w4 = new WordImpl("xylophone", 0.05, new Pair<Double, Double>(10.5, 0.0));

    /**
     * test if the world add correctly words.
     */
    @Test
    public void testAddWordsToTheSet() {
        final World world = new WorldImpl();
        assertTrue(world.isOver());

        world.getWordSet().add(w1);
        world.getWordSet().add(w2);
        world.getWordSet().add(w3);
        world.getWordSet().add(w4);

        assertFalse(world.isOver());

    }

    /**
     * 
     * @throws IllegalArgumentException
     * 
     *test if world set correctly the active word
     *
     */
    @Test
    public void testActiveWord() {

        final World world = new WorldImpl();
        world.currentActiveWord();

        world.getWordSet().add(w1);
        world.getWordSet().add(w2);
        world.getWordSet().add(w3);
        world.getWordSet().add(w4);

        List<Word> list = new LinkedList<>();
        for (Word i : world.getWordSet()) {
            list.add(i);
        }
        list.get(0).setActive(true);
        Word w = list.get(0);
        for (var i : world.getWordSet()) {
            if (i.equals(list.get(0))) {
                i.setActive(true);
            }
        }
        world.currentActiveWord();
        assertTrue(w.equals(world.currentActiveWord().get()));
        world.changeActive();
        assertFalse(world.isOver());
        world.resetSet();
        assertTrue(world.isOver());

    }

    /**
     * test if the play is in gameOver state.
     */
    @Test
    public void testGamestate() {
        final World world = new WorldImpl();
        Word w1 = new WordImpl("sky", 0.1, new Pair<Double, Double>(10.5, 0.0));
        world.getWordSet().add(w1);

        assertFalse(world.getGameState().isGameOver());
        Word w2 = new WordImpl("banana", 0.1, new Pair<Double, Double>(13.5, 110.0));
        world.getWordSet().add(w2);
        assertTrue(world.getGameState().isGameOver());

    }

    /**
     * test the update method.
     */
    @Test
    public void testUpdate() {
        final World world = new WorldImpl();
        world.update();
        assertFalse(world.getUnicorn().isAiming());
        assertTrue(world.isWordSetPause());

        world.setWordSetPause(false);
        world.getWordSet().add(w1);
        world.getWordSet().add(w2);
        world.getWordSet().add(w3);
        world.getWordSet().add(w4);
        Pair<Double, Double> w = new Pair<Double, Double>(w1.getPosition().getKey(), w1.getPosition().getValue() + w1.getSpeed());
        world.update();
        assertEquals(w.getValue(), w1.getPosition().getValue());

    }

}
