package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Triple;

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
 * Manages the initialization of the game. It permits to initialize a new game or to load one.
 * Its methods can be called only one time per game.
 */
public final class GameLoader {

    private static final int DEFINED_REGION_TO = 4;
    private static final int UNDEFINED_REGION_TO = 6;
    private static final int BASIC_TANKS_NUMBER = 35;
    private static final int MIN_PLAYER_NUMBER = 3;
    private static final int TANKS_TO_DECREASE = 5;
    private boolean alreadyCalled;
    private Set<State> statesSet;
    private Set<Region> regionsSet;
    private Optional<CircularList<Player>> playersList;

    private static final GameLoader SINGLETON = new GameLoader();

    private GameLoader() {
        this.buildNewGame();
    }

    /**
     * @return the singleton object.
     */
    public static GameLoader getGameLoader() {
        return SINGLETON;
    }

    /**
     * Initializes the game creating the basic structures for {@linkplain Model} work.
     * @param playersInfo
     *            a list of information that includes the players names associated 
     *            with their {@link Color}, and with their {@link ControlType}.
     * @param mapType
     *            the chosen map type.
     * @throws IOException
     *            when one of the initialization files not is found.
     * @return a {@link Model} already initialized.
     */
    public Model initGame(final List<Triple<String, Color, ControlType>> playersInfo, final MapType mapType) throws IOException {
        if (this.alreadyCalled) {
            throw new IllegalStateException("IllegalStateException: game already initialized!");
        }
        Objects.requireNonNull(playersInfo, "NullPointerException: playersInfo required non-null.");
        Objects.requireNonNull(mapType, "NullPointerException: mapType required non-null.");
        this.alreadyCalled = true;

        final Set<String> statesNames = new HashSet<>();
        final Set<String> regionsNames = new HashSet<>();
        final Map<String, List<String>> stateNeighbor = new HashMap<>();
        final Map<String, String> stateRegion = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(GameLoader.class.getResourceAsStream(mapType.getStatesInitFile())))) {
            String line = reader.readLine();
            while (line != null) {
                final String[] info = line.split(":");
                statesNames.add(info[0]);
                regionsNames.add(info[1]);
                stateRegion.put(info[0], info[1]);
                stateNeighbor.put(info[0], Arrays.asList(info[2].split("#")));
                line = reader.readLine();
            }
        }

        //Regions creation.
        Map<String, Integer> regionBonus = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(GameLoader.class.getResourceAsStream(mapType.getRegionsInitFile())))) {
            regionBonus = reader.lines().map(l -> l.split(":")).collect(Collectors.toMap(e -> e[0], e -> Integer.parseInt(e[1])));
            for (final String regionName : regionsNames) {
                this.regionsSet.add(new RegionImpl(regionName, regionBonus.get(regionName)));
            }
        }

        //States creation.
        for (final String state: statesNames) {
            final String regionName = stateRegion.get(state);
            for (final Region region : this.regionsSet) {
                if (region.getName().equals(regionName)) {
                    final State st = new StateImpl(state, region);
                    st.addTanks(1);
                    this.statesSet.add(st);
                    region.addAssociatedState(st);
                }
            }
        }
        this.addNeighborStates(stateNeighbor);

        List<Set<Region>> info = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(GameLoader.class.getResourceAsStream(mapType.getAimManagerInitFile())))) {
             info = reader.lines().map(l -> l.split(":")).map(in -> {
                             final Set<Region> tuple = new HashSet<>();
                             tuple.add(this.regionsSet.stream().filter(e -> e.getName().equals(in[0])).findFirst().get());
                             tuple.add(this.regionsSet.stream().filter(e -> e.getName().equals(in[1])).findFirst().get());
                             return tuple;
                             }).collect(Collectors.toCollection(ArrayList::new));
        }

        this.playersList = Optional.of(this.initializePlayerList(playersInfo));
        this.statesAssignement();
        this.playersList.get().toStream().forEach(e -> e.setTotalTanksToDeploy(BASIC_TANKS_NUMBER - (this.playersList.get().size() - MIN_PLAYER_NUMBER) * TANKS_TO_DECREASE));
//        this.playersList.get().stream().forEach(e -> e.setTotalTanksToDeploy(1)); //Test mode
        this.initializeManagers();
        AimManager.getInstance().init(info.subList(0, DEFINED_REGION_TO), info.subList(DEFINED_REGION_TO, UNDEFINED_REGION_TO), 
                mapType.getDefTargetNum(), mapType.getUndefTargetNum(), this.playersList.get());
        BonusCardManager.getInstance().init(this.statesSet.stream().collect(Collectors.toList()));
 
        return new ModelImpl(this.playersList.get(), new HashSet<>(this.statesSet), this.regionsSet, mapType);
    }

    /**
     * Loads a game previously saved.
     * @param path
     *            the path for the file to load.
     * @return a {@link Model} already initialized.
     */
    public Model loadGame(final File path) {
       if (this.alreadyCalled) {
          throw new IllegalStateException("IllegalStateException: game already initialized!");
       }
       Objects.requireNonNull(path);
       this.alreadyCalled = true;

       final ModelMemento memento = LoadingManager.getInstance().loadGame(path);
       this.playersList = Optional.of(memento.getPlayers());
       this.statesSet = memento.getStates();
       this.regionsSet = memento.getRegions();
       this.initializeManagers();
       AimManager.getInstance().recovery(this.playersList.get());
       BonusCardManager.getInstance().recovery(memento.getCards());
       return new ModelImpl(this.playersList.get(), this.statesSet, memento.getRegions(), memento.getMap());
    }

    /**
     * Restores the flag to the initial state. This method can be called if a player decides 
     * to close the game but not the program.
     */
    public void gameClosed() {
        this.buildNewGame();
    }

    private void initializeManagers() {
        AttackManager.getInstance().init(this.playersList.get());
        DeployManager.getInstance().init(this.playersList.get());
        AIManager.getInstance().init(this.playersList.get(), this.statesSet);
    }

    private void addNeighborStates(final Map<String, List<String>> stateNeighbor) {
        for (final State state : this.statesSet) {
            final List<String> neighborsName = stateNeighbor.entrySet().stream().filter(e -> e.getKey().equals(state.getName())).map(e -> e.getValue()).findFirst().get();
            final Set<State> realNeighbors = this.statesSet.stream().filter(st -> neighborsName.contains(st.getName())).collect(Collectors.toSet());
            state.addNeighbouringState(realNeighbors);
        }
    }

    private CircularList<Player> initializePlayerList(final List<Triple<String, Color, ControlType>> playersInfo) {
        final List<Player> playersList = new ArrayList<>();
        playersInfo.stream().forEach(e -> playersList.add(new PlayerImpl(e.getLeft(), e.getMiddle(), e.getRight(), 0)));
        Collections.shuffle(playersList);
        return new CircularListImpl<>(playersList);
    }
 
    private void statesAssignement() {
        for (final State state : this.statesSet) {
            this.playersList.get().getHead().addState(state);
            this.playersList.get().shift();
        }
        this.playersList.get().setHead(0);
    }

    private void buildNewGame() {
        this.alreadyCalled = false;
        this.statesSet = new TreeSet<>((State st1, State st2) -> {
                                            final Random rand = new Random();
                                            return (st1.hashCode() - st2.hashCode()) + rand.nextInt();
                                        });
        this.regionsSet = new HashSet<>();
        this.playersList = Optional.empty();
    }

}
