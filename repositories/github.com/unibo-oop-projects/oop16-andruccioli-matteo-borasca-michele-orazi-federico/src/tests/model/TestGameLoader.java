package tests.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import model.AIManager;
import model.AimManager;
import model.AttackManager;
import model.BonusCardManager;
import model.DeployManager;
import model.GameLoader;
import model.LoadingManager;
import model.Model;
import model.ModelImpl;
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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Triple;

/**
 * A class for testing {@link model.GameLoader}.
 */
public final class TestGameLoader {

    private static final int ASIA_BONUS = 7;
    private static final int EUROPE_NAMERICAS_BONUS = 5;
    private static final int AFRICA_BONUS = 3;
    private static final int SAMERICA_OCEANIA_BONUS = 2;
    private static final int UNDEFINED_AVAILEABLE_REGIONS = 18;
    private static final int DEFINED_AVAILEABLE_REGIONS = 24;
    private static final String[] REGIONS_NAMES = {"Asia", "North America", "Europe", "Africa", "South America", "Oceania"};

    private final List<Triple<String, Color, ControlType>> playersInfo = Arrays.asList(new ImmutableTriple<>("Tom Cruise", Color.RED, ControlType.HUMAN),
            new ImmutableTriple<>("Nicolas Cage", Color.YELLOW, ControlType.HUMAN),
            new ImmutableTriple<>("Scarlett Johansson", Color.PURPLE, ControlType.AI),
            new ImmutableTriple<>("Ian McKellen", Color.BLACK, ControlType.AI),
            new ImmutableTriple<>("Emma Watson", Color.RED, ControlType.HUMAN));;

    /**
     * Tests the performance of {@link model.GameLoader#initGame(List, MapType)} method and the correctness 
     * of {@link model.GameLoader#gameClosed()} method. 
     */
    @Test
    public void testPerformanceAndExceptionsLaunch() {
        this.resetGame();
        try {
            final long time = System.currentTimeMillis();
            GameLoader.getGameLoader().initGame(this.playersInfo, MapType.ClassicMap);
            System.out.println("initGame() chronometer: " + (System.currentTimeMillis() - time) + "msec");
            GameLoader.getGameLoader().initGame(this.playersInfo, MapType.ClassicMap); //Throws the IllegalStateException.
            this.resetGame();
            GameLoader.getGameLoader().initGame(this.playersInfo, MapType.ClassicMap);
        } catch (IOException | IllegalStateException e) {
            System.out.println("Exception catched: " + e.getMessage());
        }
        this.resetGame();
        try {
            GameLoader.getGameLoader().initGame(null, MapType.ClassicMap);
        } catch (Exception e) {
            System.out.println("Exception catched: " + e.getMessage());
        }
        this.resetGame();
        try {
            GameLoader.getGameLoader().initGame(this.playersInfo, null);
        } catch (Exception e) {
            System.out.println("Exception catched: " + e.getMessage());
        }
        this.resetGame();
    }

    /**
     * Tests the correctness of the creation of players list.
     */
    @Test
    public void testPlayersList() {
        this.resetGame();
        try {
            final Model model = GameLoader.getGameLoader().initGame(this.playersInfo, MapType.ClassicMap);
            final List<String> playersNames = this.playersInfo.stream().map(e -> e.getLeft()).collect(Collectors.toList());
            final List<Color> playersColors = this.playersInfo.stream().map(e -> e.getMiddle()).collect(Collectors.toList());
            final List<ControlType> playersContrType = this.playersInfo.stream().map(e -> e.getRight()).collect(Collectors.toList());
            model.getPlayers().forEach(p -> assertTrue("Incorrect creation of players list: incoherence with names", playersNames.contains(p.getName())));
            model.getPlayers().forEach(p -> assertTrue("Incorrect creation of players list: incoherence with colors", playersColors.contains(p.getColor())));
            model.getPlayers().forEach(p -> assertTrue("Incorrect creation of players list: incoherence with control type", playersContrType.contains(p.playerType())));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        this.resetGame();
    }

    /**
     * Tests the correctness of the creation of states and regions.
     */
    @Test
    public void testRegionsAndStates() {
        this.resetGame();
        final List<String> statesNames = new ArrayList<>();
        final Set<String> regionsNames = new HashSet<>();
        final Set<Integer> regionsBonus = new HashSet<>();
        try {
            final Model model = GameLoader.getGameLoader().initGame(this.playersInfo, MapType.ClassicMap);
            statesNames.addAll(Arrays.asList("Japan", "India", "Middle east", "Afghanistan", "Ural", "Siberia",
                    "Yakutsk", "Kamchatka", "Mongolia", "Irkutsk", "China", "Siam", "Southern Europe", "Western Europe", "Northern Europe",
                    "Ukraine", "Scandinavia", "Great Britain", "Iceland", "New Guinea", "Western Australia", "Eastern Australia", "Indonesia", 
                    "Madagascar", "South Africa", "East Africa", "Congo", "North Africa", "Egypt", "Greenland", "Western United States", 
                    "Eastern United States", "Central America", "Quebec", "Alberta", "Northwest Territory", "Ontario", "Alaska", "Peru",
                    "Argentina", "Venezuela", "Brazil"));
            regionsNames.addAll(Arrays.asList(REGIONS_NAMES));
            regionsBonus.addAll(Arrays.asList(ASIA_BONUS, EUROPE_NAMERICAS_BONUS, AFRICA_BONUS, SAMERICA_OCEANIA_BONUS));
            model.getStates().forEach(s -> assertTrue("Incorrect creation of States list: incoherence with names", statesNames.contains(s.getName())));
            model.getStates().forEach(s -> assertSame("Incorrect creation of States list: initial tanks not initialized", s.getTanks(), 1));
            model.getStates().forEach(s -> assertTrue("Incorrect creation of regions list: incoherence with names", regionsNames.contains(s.getRegion().getName())));
            model.getStates().forEach(s -> assertTrue("Incorrect creation of regions list: incoherence with bonus", regionsBonus.contains(s.getRegion().getBonusTanks())));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.resetGame();
    }

    /**
     * Tests the assignment of states to each player.
     */
    @Test
    public void testAssignmentStatesToPlayers() {
        this.resetGame();
        final Set<State> statesToCompare = new HashSet<>();
        try {
            final Model model = GameLoader.getGameLoader().initGame(this.playersInfo, MapType.ClassicMap);
            model.getPlayers().toStream().map(p -> p.getStates()).forEach(e -> statesToCompare.addAll(e));
            assertEquals("Incorret assignemtent of States to the players", statesToCompare, model.getStates());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        this.resetGame();
    }

    /**
     * Tests the {@link model.GameLoader#loadGame(String)} method.
     */
    //CHECKSTYLE:OFF:checkstyle:magicnumber 
    @Test 
    public void testLoadGame() {
        final Set<Region> regionsSet = new HashSet<>();
        regionsSet.add(new RegionImpl(REGIONS_NAMES[0], ASIA_BONUS));
        regionsSet.add(new RegionImpl(REGIONS_NAMES[1], EUROPE_NAMERICAS_BONUS));
        regionsSet.add(new RegionImpl(REGIONS_NAMES[2], EUROPE_NAMERICAS_BONUS));
        regionsSet.add(new RegionImpl(REGIONS_NAMES[3], AFRICA_BONUS));
        regionsSet.add(new RegionImpl(REGIONS_NAMES[4], SAMERICA_OCEANIA_BONUS));
        regionsSet.add(new RegionImpl(REGIONS_NAMES[5], SAMERICA_OCEANIA_BONUS));
        final Set<State> statesSet = new HashSet<>();
        statesSet.add(new StateImpl("Nottingham", regionsSet.stream().filter(e -> e.getName().equals(REGIONS_NAMES[5])).findFirst().get()));
        statesSet.add(new StateImpl("Gotham city", regionsSet.stream().filter(e -> e.getName().equals(REGIONS_NAMES[1])).findFirst().get()));
        statesSet.add(new StateImpl("Cloud City", regionsSet.stream().filter(e -> e.getName().equals(REGIONS_NAMES[3])).findFirst().get()));
        statesSet.add(new StateImpl("Zootropolis", regionsSet.stream().filter(e -> e.getName().equals(REGIONS_NAMES[4])).findFirst().get()));
        statesSet.add(new StateImpl("Hill Valley", regionsSet.stream().filter(e -> e.getName().equals(REGIONS_NAMES[1])).findFirst().get()));
        statesSet.add(new StateImpl("Hogsmeade", regionsSet.stream().filter(e -> e.getName().equals(REGIONS_NAMES[2])).findFirst().get()));
        statesSet.add(new StateImpl("Toontown", regionsSet.stream().filter(e -> e.getName().equals(REGIONS_NAMES[0])).findFirst().get()));
        statesSet.add(new StateImpl("Springfield", regionsSet.stream().filter(e -> e.getName().equals(REGIONS_NAMES[1])).findFirst().get()));
        final List<Player> players = new ArrayList<>();
        players.add(new PlayerImpl("Tom Cruise", Color.RED, ControlType.HUMAN, 0));
        players.add(new PlayerImpl("Nicolas Cage", Color.RED, ControlType.HUMAN, 0));
        players.add(new PlayerImpl("Scarlett Johansson", Color.RED, ControlType.HUMAN, 0));
        players.add(new PlayerImpl("Ian McKellen", Color.RED, ControlType.HUMAN, 0));
        players.add(new PlayerImpl("Emma Watson", Color.RED, ControlType.HUMAN, 0));
        final CircularList<Player> playersList = new CircularListImpl<>(players);
        for (final State state : statesSet) {
            playersList.getHead().addState(state);
            playersList.shift();
        }
        playersList.setHead(0);

        final Model m = new ModelImpl(playersList, statesSet, regionsSet, MapType.ClassicMap);
        final List<Set<Region>> l = new ArrayList<>();
        l.add(regionsSet);
        AimManager.getInstance().init(l, l, DEFINED_AVAILEABLE_REGIONS, UNDEFINED_AVAILEABLE_REGIONS, playersList);
        m.autoSave();
        m.saveOnFile("pippo");
        final Model m2 = GameLoader.getGameLoader().loadGame(new File(LoadingManager.getInstance().getSaveDirectory() + "\\pippo.rsk"));

        final Set<String> playersNames1 = m.getPlayers().toStream().map(e -> e.getName()).collect(Collectors.toSet());
        final Set<String> playersNames2 = m2.getPlayers().toStream().map(e -> e.getName()).collect(Collectors.toSet());
        assertEquals("Incorrect loading of players list: incoherence with names", playersNames1, playersNames2);

        final Set<State> playersStates1 = new HashSet<>();
        m2.getPlayers().toStream().map(p -> p.getStates()).forEach(e -> playersStates1.addAll(e));
        final Set<State> playersStates2 = new HashSet<>(); 
        m2.getPlayers().toStream().map(p -> p.getStates()).forEach(e -> playersStates2.addAll(e));
        assertEquals("Incorrect loading of players list: incoherence with players' States", playersStates1, playersStates2);
        assertEquals("Incorrect correspondence between players' States and States set", playersStates2, m2.getStates());

        final Set<Color> playersColor1 = m.getPlayers().toStream().map(e -> e.getColor()).collect(Collectors.toSet());
        final Set<Color> playersColor2 = m2.getPlayers().toStream().map(e -> e.getColor()).collect(Collectors.toSet());
        assertEquals("Incorrect loading of players list: incoherence with players' colors", playersColor1, playersColor2);

        final Set<String> statesNames1 = m.getStates().stream().map(e -> e.getName()).collect(Collectors.toSet());
        final Set<String> statesNames2 = m2.getStates().stream().map(e -> e.getName()).collect(Collectors.toSet());
        assertEquals("Incorrect loading of States list: incoherence with names", statesNames1, statesNames2);

        final Set<Region> regions2 = m2.getStates().stream().map(e -> e.getRegion()).collect(Collectors.toSet());
        final Set<String> regionsNames = regionsSet.stream().map(e -> e.getName()).collect(Collectors.toSet());
        regions2.forEach(e -> assertTrue("Incorrect loading of States list: incoherence with states' regions names", regionsNames.contains(e.getName())));
        this.resetGame();
    }

    private void resetGame() {
        GameLoader.getGameLoader().gameClosed();
        AimManager.getInstance().resetInitCalled();
        BonusCardManager.getInstance().resetInitCalled();
        AttackManager.getInstance().clear();
        DeployManager.getInstance().clear();
        AIManager.getInstance().reset();
    }

}
