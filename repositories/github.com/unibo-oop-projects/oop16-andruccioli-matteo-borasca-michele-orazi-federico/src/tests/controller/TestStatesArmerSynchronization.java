package tests.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Triple;
import org.junit.Test;

import controller.ControllerFactory;
import controller.StatesArmer;
import controller.TanksAnalyst;
import javafx.util.Pair;
import model.DeployManager;
import model.GameLoader;
import model.Model;
import model.player.PlayerInfo;
import model.state.StateInfo;
import utils.CircularList;
import utils.HistoryLog;
import utils.enumerations.Color;
import utils.enumerations.ControlType;
import utils.enumerations.MapType;
import utils.enumerations.Status;
import view.View;

/**
 * Tests {@link controller.StatesArmer}.
 */
public class TestStatesArmerSynchronization {

    private static final int WAITING_TIME = 100;

    private final List<Triple<String, Color, ControlType>> playersInfo = Arrays.asList(new ImmutableTriple<>("Tom Cruise", Color.RED, ControlType.HUMAN),
            new ImmutableTriple<>("Nicolas Cage", Color.YELLOW, ControlType.HUMAN),
            new ImmutableTriple<>("Scarlett Johansson", Color.PURPLE, ControlType.HUMAN),
            new ImmutableTriple<>("Ian McKellen", Color.BLACK, ControlType.HUMAN),
            new ImmutableTriple<>("Emma Watson", Color.RED, ControlType.HUMAN));

    /**
     * Tests synchronization between {@link controller.StatesArmer} and {@link controller.TanksAnalyst}.
     */
    @Test
    public void testStatesArmer() {
        Model model = null;
        try {
            model = GameLoader.getGameLoader().initGame(playersInfo, MapType.ClassicMap);
            HistoryLog.getLog().registerView(this.dummyView);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        Objects.requireNonNull(model);
        final StatesArmer statesArmer = new StatesArmer(model, this.dummyView);
        statesArmer.start();
        while (!DeployManager.getInstance().isDeploymentPhaseFinished()) {
            try {
                Thread.sleep(WAITING_TIME);
            } catch (InterruptedException e) { }
            if (model.getActualPlayer().playerType() == ControlType.HUMAN) {
                assertTrue("Error: StatesArmer not alive", statesArmer.isAlive());
                assertSame("Error: StatesArmer not waiting", statesArmer.getState(), Thread.State.WAITING);
                assertSame("Incorrect status", ControllerFactory.getController().getGameStatus(), Status.INITIALIZATION);
                final Map<StateInfo, Integer> tmpMap = new HashMap<>();
                tmpMap.put(model.getActualPlayer().getStates().stream().findFirst().get(), model.getActualPlayer().getTanksToDeploy());
                final TanksAnalyst tanksAnalyst = new TanksAnalyst(model, this.dummyView, tmpMap);
                tanksAnalyst.start();
                try {
                    Thread.sleep(WAITING_TIME);
                } catch (InterruptedException e1) { }
            } else if (model.getActualPlayer().playerType() == ControlType.AI) {
                assertFalse("Error: StatesArmer is alive", statesArmer.isAlive());
                assertSame("Error: StatesArmer not terminated", statesArmer.getState(), Thread.State.TERMINATED);
            }
        }
        System.out.println("Thread state: " + statesArmer.getState());
        assertSame("Error: StatesArmer not terminated", statesArmer.getState(), Thread.State.TERMINATED);
        assertSame("Incorrect status", ControllerFactory.getController().getGameStatus(), Status.DEPLOYMENT);
    }

    private final View dummyView = new View() {

        @Override
        public void startView() { }

        @Override
        public void setGameList(final CircularList<? extends PlayerInfo> circularList, final Set<? extends StateInfo> set, final Optional<MapType> map) { }

        @Override
        public void updateStates() { }

        @Override
        public void updateInfoPlayer() { }

        @Override
        public void deployTanks() { }

        @Override
        public void revertAction() {
            System.out.println("Exception catched from TanksAnalyst.");
        }

        @Override
        public void updateStates(final List<StateInfo> assignment) { }

        @Override
        public void updateDice(final List<Pair<Integer, Integer>> diceValue) { }

        @Override
        public void printError(final String error) {
            System.out.println(error);
        }

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
        public void drawMap() { }

        @Override
        public void disableAllComponents(final boolean check) { }

        @Override
        public void showMovementDialog() { }

    };

}
