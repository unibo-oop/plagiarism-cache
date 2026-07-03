package tests.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javafx.util.Pair;
import model.AimManager;
import model.BonusCardManager;
import model.player.Player;
import model.player.PlayerImpl;
import model.player.PlayerInfo;
import model.region.Region;
import model.region.RegionImpl;
import model.state.State;
import model.state.StateImpl;
import model.state.StateInfo;
import utils.CircularList;
import utils.CircularListImpl;
import utils.HistoryLog;
import utils.enumerations.Color;
import utils.enumerations.ControlType;
import utils.enumerations.MapType;
import view.View;

/**
 * 
 * Class used to easy the testing of other classing creating the needed structures.
 *
 */
public class TestFactory {

    private static final int ITALY_INITIAL_TANKS = 6;
    private static final int SPAIN_INITIAL_TANKS = 5;
    private static final int CINA_INITIAL_TANKS = 6;
    private static final int MIDEAST_INITIAL_TANKS = 4;
    private static final int DOUBLE_GARRISON_AIM = 5;
    private static final int INITIAL_TANKS = 20;
    private static final int REGION_TO_CONQUER = 6;
    private final CircularList<Player> players;
    private final Map<String, State> statesMap;
    private final Set<Region> regions;
    private final Set<State> states;

    /**
     * Constructs all the needed things.
     */
    public TestFactory() {
        // Initialize the list of players.
        this.players = new CircularListImpl<>(
                Arrays.asList(new PlayerImpl("Michele", Color.BLACK, ControlType.AI, INITIAL_TANKS),
                new PlayerImpl("Oraz", Color.GREEN, ControlType.AI, INITIAL_TANKS),
                new PlayerImpl("Raffa", Color.RED, ControlType.AI, INITIAL_TANKS)
                ));
        //Initialize the regions.
        this.regions = new HashSet<>();
        final Region europe = new RegionImpl("Europe", 5);
        final Region asia = new RegionImpl("Asia", 7);
        regions.add(europe);
        regions.add(asia);
        //Initialize states.
        final State italy = new StateImpl("Italy", europe);
        italy.addTanks(ITALY_INITIAL_TANKS);
        europe.addAssociatedState(italy);
        final State germany = new StateImpl("Germany", europe);
        germany.addTanks(1);
        europe.addAssociatedState(germany);
        final State spain = new StateImpl("Spain", europe);
        spain.addTanks(SPAIN_INITIAL_TANKS);
        europe.addAssociatedState(spain);
        final State scandinavia = new StateImpl("Scandinavia", europe);
        scandinavia.addTanks(1);
        europe.addAssociatedState(scandinavia);
        final State china = new StateImpl("China", asia);
        china.addTanks(CINA_INITIAL_TANKS);
        asia.addAssociatedState(china);
        final State japan = new StateImpl("Japan", asia);
        japan.addTanks(1);
        asia.addAssociatedState(japan);
        final State middleEast = new StateImpl("Middle east", asia);
        middleEast.addTanks(MIDEAST_INITIAL_TANKS);
        asia.addAssociatedState(middleEast);
        //Create the neighbours for each state:
        final Set<State> italyNeighbours = new HashSet<>(Arrays.asList(germany, spain, middleEast));
        final Set<State> germanyNeighbours = new HashSet<>(Arrays.asList(italy, spain, china, scandinavia));
        final Set<State> spainNeighbours = new HashSet<>(Arrays.asList(italy, germany));
        final Set<State> cinaNeighbours = new HashSet<>(Arrays.asList(germany, japan));
        final Set<State> japanNeighbours = new HashSet<>(Arrays.asList(china, middleEast));
        final Set<State> midEastNeighbours = new HashSet<>(Arrays.asList(italy, japan));
        final Set<State> scandinaviaNeighbours = new HashSet<>(Arrays.asList(germany));
        italy.addNeighbouringState(italyNeighbours);
        germany.addNeighbouringState(germanyNeighbours);
        spain.addNeighbouringState(spainNeighbours);
        china.addNeighbouringState(cinaNeighbours);
        japan.addNeighbouringState(japanNeighbours);
        middleEast.addNeighbouringState(midEastNeighbours);
        scandinavia.addNeighbouringState(scandinaviaNeighbours);
        this.statesMap = new HashMap<>();
        this.statesMap.put(italy.getName(), italy);
        this.statesMap.put(germany.getName(), germany);
        this.statesMap.put(spain.getName(), spain);
        this.statesMap.put(china.getName(), china);
        this.statesMap.put(japan.getName(), japan);
        this.statesMap.put(middleEast.getName(), middleEast);
        this.statesMap.put(scandinavia.getName(), scandinavia);
        this.states = new HashSet<>(this.statesMap.values());
        //Assign the states to the players.
        this.players.getHead().addState(italy);
        italy.setPlayer(this.players.getHead());
        this.players.getHead().addState(spain);
        spain.setPlayer(this.players.getHead());
        this.players.getHead().addState(japan);
        japan.setPlayer(this.players.getHead());
        this.players.getHead().addState(middleEast);
        middleEast.setPlayer(this.players.getHead());
        this.players.shift();
        this.players.getHead().addState(germany);
        germany.setPlayer(this.players.getHead());
        this.players.getHead().addState(scandinavia);
        scandinavia.setPlayer(this.players.getHead());
        this.players.shift();
        this.players.getHead().addState(china);
        china.setPlayer(this.players.getHead());
        this.players.shift();
        //Initialization of the singletons.
        BonusCardManager.getInstance().init(new ArrayList<State>(this.states));
        final List<Set<Region>> defined = new ArrayList<>();
        defined.add(this.regions);
        final List<Set<Region>> undefined = new ArrayList<>();
        undefined.add(this.regions);
        AimManager.getInstance().init(defined, undefined, REGION_TO_CONQUER, DOUBLE_GARRISON_AIM, this.players);
        HistoryLog.getLog().registerView(new View() {

            @Override
            public void updateStates() {
            }

            @Override
            public void updateLog(final String msg) {
            }

            @Override
            public void updateInfoPlayer() {
            }

            @Override
            public void updateDice(final List<Pair<Integer, Integer>> diceValue) {
            }

            @Override
            public void startView() {
            }

            @Override
            public void showVictory() {
            }

            @Override
            public void revertAction() {
            }

            @Override
            public void printError(final String error) {
            }

            @Override
            public void deployTanks() {
            }

            @Override
            public void deployPhaseFinished() {
            }

            @Override
            public void aIAttackPhaseFinished() {
            }

            @Override
            public void updateStates(final List<StateInfo> assignment) {
            }

            @Override
            public void drawMap() {
            }

            @Override
            public void setGameList(final CircularList<? extends PlayerInfo> circularList, final Set<? extends StateInfo> set,
                    final Optional<MapType> map) {
            }

            @Override
            public void disableAllComponents(final boolean check) { }

            @Override
            public void showMovementDialog() { }
        });
    }

    /**
     * @return
     *          The list of players.
     */
    public CircularList<Player> getPlayers() {
        return this.players;
    }

    /**
     * @return
     *          The set of states.
     */
    public Set<State> getStates() {
        return this.states;
    }

    /**
     * @return
     *          A map of <String, State>.
     */
    public Map<String, State> getStatesMap() {
        return this.statesMap;
    }

    /**
     * @return
     *          The set of the regions.
     */
    public Set<Region> getRegions() {
        return this.regions;
    }
}
