package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

import javafx.util.Pair;
import model.GameCommands;
import model.GameCommandsImpl;
import model.gamerules.GameRules;
import model.gamerules.LastAliveGameMode;
import model.managers.SkillTreeManagerImpl;
import model.map.GameMapFactoryImpl;
import model.map.ModifiableGameMap;
import model.objects.structures.Carpentry;
import model.objects.structures.CityImpl;
import model.objects.structures.Harbor;
import model.objects.structures.Level1Capital;
import model.objects.structures.Mine;
import model.objects.structures.OwnableStructure;
import model.objects.structures.ResourceProducer;
import model.objects.terrains.BasicTerrain;
import model.objects.terrains.MountainTerrain;
import model.objects.terrains.Terrain;
import model.objects.terrains.WaterTerrain;
import model.objects.unit.Unit;
import model.objects.unit.vehicle.Vehicle;
import model.player.Player;
import model.player.PlayerImpl;
import model.races.HumansRace;
import model.races.OrcsRace;
import model.resources.BasicResources;
import util.Coordinates;
import util.rectangle.Rectangle;
import util.rectangle.RectangleImpl;

/**
 * Class for testing.
 */
public class Test {
    private static final Pair<Integer, Integer> MAP_SIZE = new Pair<Integer, Integer>(12, 18);
    private static final String P1_NAME = "Antonio";
    private static final String P2_NAME = "Andrea";
    private static final Coordinates P1_POSITION = new Coordinates(2, 2);
    private static final Coordinates P2_POSITION = new Coordinates(4, 2);
    private static final Coordinates MINE_POSITION = new Coordinates(2, 3);
    private static final Coordinates CARPENTRY_POSITION = new Coordinates(4, 3);
    private static final Coordinates HARBOR_POSITION = new Coordinates(4, 1);
    private static final Coordinates MOUNTAIN_POSITION = new Coordinates(3, 3);
    private static final Coordinates NEUTRAL_CITY_POSITION = new Coordinates(3, 2);
    private static final int INITIAL_VALUES = 250;

    private GameCommands game;
    private List<Player> players;
    private Map<Player, Coordinates> capitals;

    /**
     * initializes the game scenario(map, rules, players) to be tested.
     */
    @org.junit.Before
    public void before() {
        final GameRules rules = LastAliveGameMode.getLastAliveGameMode();
        this.players = new ArrayList<>();
        this.capitals = new HashMap<>();

        players.add(new PlayerImpl(P1_NAME, 1, new HumansRace(), rules.generateObjective()));
        players.add(new PlayerImpl(P2_NAME, 2, new OrcsRace(), rules.generateObjective()));
        capitals.put(players.get(0), P1_POSITION);
        capitals.put(players.get(1), P2_POSITION);

        // creates the map base
        final Rectangle<Terrain> bg = new RectangleImpl<Terrain>(MAP_SIZE.getValue(), MAP_SIZE.getKey(),
                (p) -> new WaterTerrain());
        bg.setSubrectangle(new Coordinates(2, 2),
                new RectangleImpl<Terrain>(MAP_SIZE.getValue() - 4, MAP_SIZE.getKey() - 4, (p) -> new BasicTerrain()));
        final Map<Coordinates, Terrain> base = bg.toMap();
        base.put(MOUNTAIN_POSITION, new MountainTerrain());

        final ModifiableGameMap map = new GameMapFactoryImpl().getEmptyMapFromBackground(base, MAP_SIZE);
        // set the structures
        map.setStructure(MINE_POSITION, new Mine());
        map.setStructure(CARPENTRY_POSITION, new Carpentry());
        map.setStructure(HARBOR_POSITION, new Harbor(new SkillTreeManagerImpl(players)));
        map.setStructure(NEUTRAL_CITY_POSITION, new CityImpl(Optional.empty()));

        // set the capitals
        map.setStructure(this.capitals.get(players.get(0)), new Level1Capital(Optional.of(players.get(0))));
        map.setStructure(this.capitals.get(players.get(1)), new Level1Capital(Optional.of(players.get(1))));
        this.game = new GameCommandsImpl(players, map, rules);
    }

    /**
     * the test of a whole game, during the game some of restrictions are tested.
     */
    @org.junit.Test
    public void wholeGameTest() {
        // first turn
        game.nextTurn();
        assertEquals(game.getCurrentPlayer(), players.get(0));
        IntStream.range(0, players.size()).forEach(p -> { // creating the first unit for each player
            final Player actual = players.get(p);
            assertEquals(game.getCurrentPlayer(), actual);
            assertTrue(game.canCreateUnitFromCity(game.getPossibleUnit().get(0), this.capitals.get(actual)));
            game.createUnitFromCity(game.getPossibleUnit().get(0), this.capitals.get(actual));
            assertEquals(game.getResourceQuantityForPlayer(actual, BasicResources.GOLD),
                    INITIAL_VALUES - game.getPossibleUnit().get(0).getCost().getCost().get().get(BasicResources.GOLD));
            assertEquals(game.getResourceQuantityForPlayer(actual, BasicResources.WOOD),
                    INITIAL_VALUES - game.getPossibleUnit().get(0).getCost().getCost().get().get(BasicResources.WOOD));
            assertEquals(game.getResourceQuantityForPlayer(actual, BasicResources.POPULATION),
                    0 + game.getPossibleUnit().get(0).getCost().getCost().get().get(BasicResources.POPULATION));
            assertTrue(game.getValidUnitAttacks(this.capitals.get(actual)).isEmpty());
            assertTrue(game.getValidUnitMovements(this.capitals.get(actual)).isEmpty());
            game.nextTurn();
        });
        // second turn
        // p1
        assertEquals(game.getCurrentPlayer(), players.get(0));
        // testing invalid movement
        assertFalse(game.getValidUnitMovements(P1_POSITION).contains(HARBOR_POSITION));
        try {
            game.moveUnit(P1_POSITION, HARBOR_POSITION);
            fail();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        assertEquals(game.getCurrentPlayer(), players.get(0));
        // moving to mine position and testing it's not yet conquered
        assertTrue(game.getValidUnitMovements(P1_POSITION).contains(MINE_POSITION));
        assertFalse(game.getStructure(MINE_POSITION).get().getOwner().isPresent());
        game.moveUnit(P1_POSITION, MINE_POSITION);
        assertFalse(game.getStructure(MINE_POSITION).get().getOwner().isPresent());
        assertTrue(game.canCreateUnitFromCity(game.getPossibleUnit().get(0), P1_POSITION));
        game.createUnitFromCity(game.getPossibleUnit().get(0), P1_POSITION);
        game.nextTurn();
        // p2
        // moving to carpentry position
        assertEquals(game.getCurrentPlayer(), players.get(1));
        assertTrue(game.getValidUnitMovements(P2_POSITION).contains(CARPENTRY_POSITION));
        game.moveUnit(P2_POSITION, CARPENTRY_POSITION);
        assertTrue(game.canCreateUnitFromCity(game.getPossibleUnit().get(0), P2_POSITION));
        game.createUnitFromCity(game.getPossibleUnit().get(0), P2_POSITION);
        game.nextTurn();
        // p1
        assertEquals(game.getCurrentPlayer(), players.get(0));
        // testing that the mine has been conquered
        assertTrue(game.getStructure(MINE_POSITION).get().getOwner().isPresent());
        assertTrue(game.getStructure(MINE_POSITION).get().getOwner().get()
                .equals(game.getUnit(MINE_POSITION).get().getOwner().get()));
        // going to conquer players
        game.moveUnit(P1_POSITION, NEUTRAL_CITY_POSITION);
        assertFalse(game.getStructure(NEUTRAL_CITY_POSITION).get().getOwner().isPresent());
        game.nextTurn();
        // p2
        assertEquals(game.getCurrentPlayer(), players.get(1));
        // testing that the carpentry has been conquered
        assertTrue(game.getStructure(CARPENTRY_POSITION).get().getOwner().isPresent());
        assertTrue(game.getStructure(CARPENTRY_POSITION).get().getOwner().get()
                .equals(game.getUnit(CARPENTRY_POSITION).get().getOwner().get()));
        // testing harbor functioning
        assertTrue(game.getValidUnitMovements(P2_POSITION).contains(HARBOR_POSITION));
        game.moveUnit(P2_POSITION, HARBOR_POSITION);
        assertTrue(game.getUnit(HARBOR_POSITION).get() instanceof Vehicle);
        // going to conquer players
        final int previousGold = game.getResourceQuantityForPlayer(players.get(0), BasicResources.GOLD);
        final int previousWood = game.getResourceQuantityForPlayer(players.get(1), BasicResources.WOOD);
        game.nextTurn();
        // p1
        assertEquals(game.getStructure(NEUTRAL_CITY_POSITION).get().getOwner().get(), game.getCurrentPlayer());
        // checking that gold has been produced
        assertEquals(previousGold + ((ResourceProducer) game.getStructure(MINE_POSITION).get()).getQuantity(),
                game.getResourceQuantityForPlayer(game.getCurrentPlayer(),
                        ((ResourceProducer) game.getStructure(MINE_POSITION).get()).getResource()));
        // checking that the unit can't climb the mountain
        assertFalse(game.getValidUnitMovements(NEUTRAL_CITY_POSITION).contains(MOUNTAIN_POSITION));
        game.nextTurn();
        // p2
        // checking that wood has been produced
        assertEquals(previousWood + ((ResourceProducer) game.getStructure(CARPENTRY_POSITION).get()).getQuantity(),
                game.getResourceQuantityForPlayer(game.getCurrentPlayer(),
                        ((ResourceProducer) game.getStructure(CARPENTRY_POSITION).get()).getResource()));
        game.moveUnit(CARPENTRY_POSITION,
                new Coordinates(CARPENTRY_POSITION.getX() - 1, CARPENTRY_POSITION.getY() + 1));
        game.nextTurn();
        // p1
        // checking that the unit can't go in water
        assertFalse(game.getValidUnitMovements(NEUTRAL_CITY_POSITION)
                .contains(new Coordinates(NEUTRAL_CITY_POSITION.getX(), NEUTRAL_CITY_POSITION.getY() - 1)));
        // checking fight and that the unit moved after killing the other one
        assertTrue(game
                .makeUnitFight(MINE_POSITION,
                        new Coordinates(CARPENTRY_POSITION.getX() - 1, CARPENTRY_POSITION.getY() + 1))
                .get().equals(new Coordinates(CARPENTRY_POSITION.getX() - 1, CARPENTRY_POSITION.getY() + 1)));
        game.nextTurn();
        // p2
        // test the decent of the unit from a vehicle
        game.moveUnit(HARBOR_POSITION, P2_POSITION);
        assertTrue(game.getUnit(P2_POSITION).get() instanceof Unit);
        assertFalse(game.getUnit(P2_POSITION).get().canMove());
        game.nextTurn();
        // p1
        assertFalse(game.getUnit(CARPENTRY_POSITION).isPresent());
        // making the unit conquer the carpentry of the other player
        game.moveUnit(new Coordinates(CARPENTRY_POSITION.getX() - 1, CARPENTRY_POSITION.getY() + 1), CARPENTRY_POSITION);
        assertFalse(game.getStructure(CARPENTRY_POSITION).get().getOwner().get().equals(game.getCurrentPlayer()));
        assertTrue(((OwnableStructure) game.getStructure(CARPENTRY_POSITION).get()).getConqueror().get().equals(game.getCurrentPlayer()));
        game.nextTurn();
        // p2
        // make the unit go away so that the other can conquer its capital
        game.moveUnit(P2_POSITION, new Coordinates(P2_POSITION.getX() + 1, P2_POSITION.getY()));
        game.nextTurn();
        // p1
        assertTrue(game.getStructure(CARPENTRY_POSITION).get().getOwner().get().equals(game.getCurrentPlayer()));
        // waiting 2 turns to produce in order to check skilltree improvement
        game.nextTurn();
        // p2
        game.nextTurn();
        // p1
        game.nextTurn();
        // p2
        game.nextTurn();
        // p1
        // upgrading the attribute to produce more resources of 25%
        final int beforeUpgradeWoodProduction = ((ResourceProducer) game.getStructure(CARPENTRY_POSITION).get()).getQuantity();
        game.getSkillTreeUpgradableAttribute().get(game.getSkillTreeUpgradableAttribute().size() - 1).upgrade();
        game.nextTurn();
        // p2
        game.nextTurn();
        // p1
        // checking that the quantity is increased
        assertTrue(beforeUpgradeWoodProduction < ((ResourceProducer) game.getStructure(CARPENTRY_POSITION).get()).getQuantity());
        // conquering the capital of the second player
        game.moveUnit(CARPENTRY_POSITION, P2_POSITION);
        assertFalse(game.getStructure(P2_POSITION).get().getOwner().get().equals(players.get(0)));
        assertTrue(((OwnableStructure) game.getStructure(P2_POSITION).get()).getConqueror().get().equals(game.getCurrentPlayer()));
        game.nextTurn();
        // p2
        game.nextTurn();
        // p1
        // checking that the conquer happened (capital become neutral)
        assertFalse(game.getStructure(P2_POSITION).get().getOwner().isPresent());
        // checking that the other player's unit disappeared
        assertFalse(game.getUnit(new Coordinates(P2_POSITION.getX() + 1, P2_POSITION.getY())).isPresent());
        // checking that the first player won
        assertTrue(game.getWinnerPlayer().get().equals(players.get(0)));
    }
}
