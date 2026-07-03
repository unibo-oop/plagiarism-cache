package tests.utils;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.Test;

import javafx.util.Pair;
import model.player.PlayerInfo;
import model.state.StateInfo;
import utils.CircularList;
import utils.HistoryLog;
import utils.enumerations.MapType;
import view.View;

/**
 * Test of {@link HistoryLog}. 
 */
public class TestHistoryLog {

    private static final int MODIFIED_A = 5;
    private static final int MODIFIED_B = 6;

    /**
     * Tests the general working.
     */
    @Test
    public void test1() {
        final Model m = new Model(2, 3);
        try {
            m.modify(MODIFIED_A, MODIFIED_B);
        } catch (Exception e) {
            System.out.println("Exception catched: " + e);
        }
        HistoryLog.getLog().registerView(view);
        m.modify(MODIFIED_A, MODIFIED_B);
    }

    private static class Model {

        private int a;
        private int b;

        Model(final int a, final int b) {
            this.a = a;
            this.b = b;
        }

        public void modify(final int a, final int b) {
            HistoryLog.getLog().send("Modifies: a: " + this.a + " -> " + a + " | b: " + this.b + " -> " + b);
            this.a = a;
            this.b = b;
        }

    }

    private static View view = new View() {

        @Override
        public void startView() { }

        @Override
        public void updateStates() { }

        @Override
        public void updateInfoPlayer() { }

        @Override
        public void deployTanks() { }

        @Override
        public void revertAction() { }

        @Override
        public void updateDice(final List<Pair<Integer, Integer>> diceValue) { }

        @Override
        public void printError(final String error) { }

        @Override
        public void showVictory() { }

        @Override
        public void deployPhaseFinished() { }

        @Override
        public void aIAttackPhaseFinished() { }

        @Override
        public void updateLog(final String msg) {
            System.out.println(msg);
        }

        @Override
        public void setGameList(final CircularList<? extends PlayerInfo> circularList, final Set<? extends StateInfo> set, final Optional<MapType> map) { }

        @Override
        public void updateStates(final List<StateInfo> assignment) { }

        @Override
        public void drawMap() { }

        @Override
        public void disableAllComponents(final boolean check) { }

        @Override
        public void showMovementDialog() { }

    };
}
