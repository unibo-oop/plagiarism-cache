package tests.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Triple;
import org.junit.Test;

import model.CareMementoTaker;
import model.GameLoader;
import model.LoadingManager;
import model.Model;
import model.ModelImpl;
import model.ModelMemento;
import model.player.Player;
import model.player.PlayerImpl;
import model.region.Region;
import model.region.RegionImpl;
import model.state.State;
import model.state.StateImpl;
import utils.CircularList;
import utils.CircularListImpl;
import utils.enumerations.Color;
import utils.enumerations.ControlType;
import utils.enumerations.MapType;

/**
 * Tests {@link model.Model}.
 */
public class TestModelImpl {
    //CHECKSTYLE:OFF:checkstyle:magicnumber
    private static final int ASIA_BONUS = 7;
    private static final int EUROPE_NAMERICAS_BONUS = 5;
    private static final int AFRICA_BONUS = 3;
    private static final int SAMERICA_OCEANIA_BONUS = 2;
    private static final String[] REGIONS_NAMES = {"Asia", "North America", "Europe", "Africa", "South America", "Oceania"};

    private final  CircularList<Player> playersList = new CircularListImpl<>(new ArrayList<>(Arrays.asList(new PlayerImpl("Tom Cruise", Color.RED, ControlType.HUMAN, 0),
            new PlayerImpl("Nicolas Cage", Color.RED, ControlType.HUMAN, 0),
            new PlayerImpl("Scarlett Johansson", Color.RED, ControlType.HUMAN, 0),
            new PlayerImpl("Ian McKellen", Color.RED, ControlType.HUMAN, 0),
            new PlayerImpl("Emma Watson", Color.RED, ControlType.HUMAN, 0))));
    private final Set<Region> regionsSet = new HashSet<>(Arrays.asList(new RegionImpl(REGIONS_NAMES[0], ASIA_BONUS),
            new RegionImpl(REGIONS_NAMES[1], EUROPE_NAMERICAS_BONUS),
            new RegionImpl(REGIONS_NAMES[2], EUROPE_NAMERICAS_BONUS),
            new RegionImpl(REGIONS_NAMES[3], AFRICA_BONUS),
            new RegionImpl(REGIONS_NAMES[4], SAMERICA_OCEANIA_BONUS),
            new RegionImpl(REGIONS_NAMES[5], SAMERICA_OCEANIA_BONUS)));
    private final Set<State> statesSet = new HashSet<>(Arrays.asList(new StateImpl("Nottingham", this.regionsSet.stream().filter(e -> e.getName().equals(REGIONS_NAMES[5])).findFirst().get()),
            new StateImpl("Gotham city", this.regionsSet.stream().filter(e -> e.getName().equals(REGIONS_NAMES[1])).findFirst().get()),
            new StateImpl("Cloud City", this.regionsSet.stream().filter(e -> e.getName().equals(REGIONS_NAMES[3])).findFirst().get()),
            new StateImpl("Zootropolis", this.regionsSet.stream().filter(e -> e.getName().equals(REGIONS_NAMES[4])).findFirst().get()),
            new StateImpl("Hill Valley", this.regionsSet.stream().filter(e -> e.getName().equals(REGIONS_NAMES[1])).findFirst().get()),
            new StateImpl("Hogsmeade", this.regionsSet.stream().filter(e -> e.getName().equals(REGIONS_NAMES[2])).findFirst().get()),
            new StateImpl("Toontown", this.regionsSet.stream().filter(e -> e.getName().equals(REGIONS_NAMES[0])).findFirst().get()),
            new StateImpl("Springfield", this.regionsSet.stream().filter(e -> e.getName().equals(REGIONS_NAMES[1])).findFirst().get())));

    /**
     * Tests {@link model.Model#getPlayers()} and {@link model.Model#getStates()} methods.
     * Checks that the arguments passed in input are maintained the same in the output.
     */
    @Test
    public void testArgumentsConsistency() {
        final Model model = new ModelImpl(this.playersList, this.statesSet, this.regionsSet, MapType.ClassicMap);
        assertTrue("Incoherence with players lists", this.playersList.equals(model.getPlayers()));
        assertTrue("Incoherence with states sets", this.statesSet.equals(model.getStates()));
        final Set<Region> regionsToCompare = model.getStates().stream().map(e -> e.getRegion()).collect(Collectors.toSet());
        assertEquals("Incoherence with regions sets", regionsToCompare, this.regionsSet);
    }

    /**
     * Tests {@link model.Model#shiftPlayer()}, {@link model.Model#reorganize()} and {@link model.Model#getActualPlayer()} methods.
     */
    @Test
    public void testOperationsOnPlayersList() {
        final List<Player> playersListClone = new ArrayList<>();
        this.playersList.forEach(e -> playersListClone.add(e));
        final Model model = new ModelImpl(this.playersList, this.statesSet, this.regionsSet, MapType.ClassicMap);
        final String errorMessage = "Shift of players list doesn't work";

        model.shiftPlayer();
        assertFalse(errorMessage, playersListClone.get(0).equals(model.getPlayers().getHead()));
        assertTrue(errorMessage, playersListClone.get(1).equals(model.getPlayers().getHead()));
        assertTrue(errorMessage, playersListClone.get(1).equals(model.getActualPlayer()));
        model.shiftPlayer();
        assertFalse(errorMessage, playersListClone.get(1).equals(model.getPlayers().getHead()));
        assertTrue(errorMessage, playersListClone.get(2).equals(model.getPlayers().getHead()));
        assertTrue(errorMessage, playersListClone.get(2).equals(model.getActualPlayer()));
        model.shiftPlayer();
        model.shiftPlayer();
        model.shiftPlayer();
        assertFalse(errorMessage, playersListClone.get(4).equals(model.getPlayers().getHead()));
        assertTrue(errorMessage, playersListClone.get(0).equals(model.getPlayers().getHead()));
        assertTrue(errorMessage, playersListClone.get(0).equals(model.getActualPlayer()));

        model.shiftPlayer();
        model.shiftPlayer();
        model.reorganize();
        assertFalse("Reorganization of players list doesn't work", playersListClone.get(2).equals(model.getPlayers().getHead()));
        assertTrue("Reorganization of players list doesn't work", playersListClone.get(0).equals(model.getPlayers().getHead()));
        assertTrue("Reorganization of players list doesn't work", playersListClone.get(0).equals(model.getActualPlayer()));
    }

    /**
     * Tests {@link model.Model#autoSave()} and {@link model.Model#saveOnFile(String)} methods.
     */
    @Test
    public void testSavingGame() {
        final Model model = new ModelImpl(this.playersList, this.statesSet, this.regionsSet, MapType.ClassicMap);

        model.shiftPlayer();
        model.shiftPlayer();
        model.autoSave();
        final ModelMemento memento = CareMementoTaker.getInstance().getMemento();
        final CircularList<Player> playersListToCompare = memento.getPlayers();
        final Set<Region> regionsSetToCompare = memento.getRegions();
        final Set<State> statesSetToCompare = memento.getStates();
        assertTrue("There is no match between the players lists", model.getPlayers().equals(playersListToCompare));
        assertTrue("There is no match between the regions sets", this.regionsSet.equals(regionsSetToCompare));
        assertTrue("There is no match between the states sets", model.getStates().equals(statesSetToCompare));

        model.shiftPlayer();
        //These checks must be true because the real save of the list has not been make yet.
        assertTrue("There is no match between the first players of the players lists", model.getPlayers().getHead().equals(playersListToCompare.getHead()));
        model.reorganize();
        assertTrue("There is no match between the first players of the players lists", model.getPlayers().getHead().equals(playersListToCompare.getHead()));

        model.shiftPlayer();
        model.shiftPlayer();
        model.saveOnFile("pippo");
        final ModelMemento mementoLoaded = LoadingManager.getInstance().loadGame(new File(LoadingManager.getInstance().getSaveDirectory() + "\\pippo.rsk"));
        final List<String> playersNames = model.getPlayers().toStream().map(e -> e.getName()).collect(Collectors.toList());
        final List<Color> playersColors = model.getPlayers().toStream().map(e -> e.getColor()).collect(Collectors.toList());
        final List<ControlType> playersControlType = model.getPlayers().toStream().map(e -> e.playerType()).collect(Collectors.toList());
        final List<Integer> playersTanksToDeploy = model.getPlayers().toStream().map(e -> e.getTotalTanksToDeploy()).collect(Collectors.toList());
        final Set<String> statesNames = model.getStates().stream().map(e -> e.getName()).collect(Collectors.toSet());
        final Set<String> regionsNames = this.regionsSet.stream().map(e -> e.getName()).collect(Collectors.toSet());
        //In these checks the list is loaded thus the objects where the information is placed are different but
        //the content of those objects is the same.
        assertFalse("Wrong correspondence between players lists", model.getPlayers().equals(mementoLoaded.getPlayers()));
        assertFalse("Wrong correspondence between states sets", model.getStates().equals(mementoLoaded.getStates()));
        assertFalse("Wrong correspondence between regions sets", this.regionsSet.equals(mementoLoaded.getRegions()));
        mementoLoaded.getPlayers().forEach(e -> assertTrue("Wrong correspondence between players name lists", playersNames.contains(e.getName())));
        mementoLoaded.getPlayers().forEach(e -> assertTrue("Wrong correspondence between players color lists", playersColors.contains(e.getColor())));
        mementoLoaded.getPlayers().forEach(e -> assertTrue("Wrong correspondence between players control type lists", playersControlType.contains(e.playerType())));
        mementoLoaded.getPlayers().forEach(e -> assertTrue("Wrong correspondence between players tanks to deploy lists", playersTanksToDeploy.contains(e.getTotalTanksToDeploy())));
        mementoLoaded.getStates().forEach(e -> assertTrue("Wrong correspondence between states name lists", statesNames.contains(e.getName())));
        mementoLoaded.getRegions().forEach(e -> assertTrue("Wrong correspondence between regions name lists", regionsNames.contains(e.getName())));
    }

    /**
     * Tests {@link model.Model#resetGame()} method.
     */
    @Test
    public void testResetGame() {
        final List<Triple<String, Color, ControlType>> playersInfo = Arrays.asList(new ImmutableTriple<>("Tom Cruise", Color.RED, ControlType.HUMAN),
                new ImmutableTriple<>("Nicolas Cage", Color.YELLOW, ControlType.HUMAN),
                new ImmutableTriple<>("Scarlett Johansson", Color.PURPLE, ControlType.AI),
                new ImmutableTriple<>("Ian McKellen", Color.BLACK, ControlType.AI),
                new ImmutableTriple<>("Emma Watson", Color.RED, ControlType.HUMAN));
        Model model = null;
        try {
            model = GameLoader.getGameLoader().initGame(playersInfo, MapType.ClassicMap);
        } catch (Exception e) {
            System.out.println("Exception catched: " + e.getMessage());
        }
        Objects.requireNonNull(model);
        model.resetGame();
        try {
            model = GameLoader.getGameLoader().initGame(playersInfo, MapType.ClassicMap);
        } catch (Exception e) {
            System.out.println("Exception catched: " + e.getMessage());
        }
    }

}
