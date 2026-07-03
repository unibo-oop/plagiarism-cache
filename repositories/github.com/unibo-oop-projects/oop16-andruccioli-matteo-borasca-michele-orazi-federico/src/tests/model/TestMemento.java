package tests.model;

import model.CareMementoTaker;
import model.LoadingManager;
import model.ModelMemento;
import model.bonus.BonusCard;
import model.state.State;
import utils.enumerations.MapType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import org.junit.Test;

/**
 * A class for testing {@link model.ModelMemento}, {@link model.CareMementoTaker} and {@link LoadingManager}.
 */
public class TestMemento {

    private static final int STATES_NUMBER = 7;
    /**
     * 
     */
    @Test
    public void mementoTest() {
        final TestFactory tf = new TestFactory();
        ModelMemento memento;
        memento = new ModelMemento(tf.getPlayers(), new HashSet<State>(tf.getStatesMap().values()), tf.getRegions(), new ArrayList<BonusCard>(), MapType.ClassicMap);
        CareMementoTaker.getInstance().addMemento(memento);
        LoadingManager.getInstance().saveGame(memento, "testMemento");
        memento = null;

        memento = LoadingManager.getInstance()
                .loadGame(new File(LoadingManager.getInstance().getSaveDirectory() + File.separator + "testMemento.rsk"));

        assertEquals(memento.getCards(), Collections.EMPTY_LIST);
        assertEquals(memento.getPlayers().size(), 3);
        assertEquals(memento.getPlayers().getHead().getName(), tf.getPlayers().getHead().getName());
        assertEquals(memento.getStates().size(), STATES_NUMBER);
        for (final State s : memento.getStates()) {
            assertTrue(tf.getStatesMap().containsKey(s.getName()));
        }
        assertEquals(memento.getRegions().size(), 2);
        assertEquals(memento.getPlayers().getHead().getName(), "Michele");
        assertEquals(memento.getMap(), MapType.ClassicMap);
    }
}
