package model;

import static util.AlgorithmsUtils.breadthFirstSearch;
import static util.Coordinates.distance;
import static util.Coordinates.getCoordinatesRange;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;

import javafx.util.Pair;
import model.gamerules.GameRules;
import model.managers.BasicResourceManager;
import model.managers.SkillTreeManager;
import model.managers.SkillTreeManagerImpl;
import model.managers.TurnManager;
import model.managers.TurnManagerImpl;
import model.map.GameMapFactoryImpl;
import model.map.ModifiableGameMap;
import model.objects.GameObject;
import model.objects.structures.Capital;
import model.objects.structures.City;
import model.objects.structures.OwnableStructure;
import model.objects.structures.ResourceProducerScalable;
import model.objects.structures.Structure;
import model.objects.terrains.Terrain;
import model.objects.unit.GenericUnitType;
import model.objects.unit.Unit;
import model.objects.unit.vehicle.Vehicle;
import model.player.Player;
import model.resources.BasicResources;
import model.resources.Resource;
import model.skilltree.CapitalLevel;
import model.skilltree.SkillTreeAttribute;
import model.skilltree.UnitLevel;
import model.skilltree.WoodGoldBoost;
import util.Coordinates;

/**
 * an implementation of GameCommands.
 */
public class GameCommandsImpl implements GameCommands {

    private final GameRules rules;
    private final TurnManager turnManager;
    private final BasicResourceManager resources;
    private final SkillTreeManager skillTreesManager;
    private final ModifiableGameMap map;
    private boolean firstTurn = true;

    /**
     * the Game constructor with random map.
     * 
     * @param players the players of the game
     * 
     * @param rules the rules of the game
     */
    public GameCommandsImpl(final List<Player> players, final GameRules rules) {
        this(players, getDefaultGameMapGeneration(players, rules.getMapSize()), rules);
    }

    private static ModifiableGameMap getDefaultGameMapGeneration(final List<Player> players,
            final Pair<Integer, Integer> mapSize) {
        return new GameMapFactoryImpl().gameMapWithIslands(mapSize, new HashSet<>(players),
                new SkillTreeManagerImpl(players));
    }

    /**
<<<<<<< HEAD
     * the Game constructor.
     * 
     * @param players the players for the game
     * 
     * @param map the map of the game
     * 
     * @param rules   the rules for the game
     * 
=======
     * the Game constructor with passed map.
     * 
     * @param players the players of the game
     * @param map the map of the game
     * @param rules the rules of the game
>>>>>>> 31c9c75e7691a0643e8ba3fba3eb872a71fe674b
     */
    public GameCommandsImpl(final List<Player> players, final ModifiableGameMap map, final GameRules rules) {
        this.rules = rules;
        this.skillTreesManager = new SkillTreeManagerImpl(players);
        this.map = map;
        this.turnManager = new TurnManagerImpl(players);
        this.resources = new BasicResourceManager(players, this.rules.getInitialValues());
    }

    // ---------------------------map methods-----------------------

    /** {@inheritDoc} **/
    @Override
    public Pair<Integer, Integer> getMapSize() {
        return this.map.getMapSize();
    }

    /** {@inheritDoc} **/
    @Override
    public Terrain getTerrain(final Coordinates cords) {
        return this.map.getTerrain(cords);
    }

    /** {@inheritDoc} **/
    @Override
    public Optional<Structure> getStructure(final Coordinates cords) {
        return this.map.getStructure(cords);
    }

    /** {@inheritDoc} **/
    @Override
    public Optional<Unit> getUnit(final Coordinates cords) {
        return this.map.getUnit(cords);
    }

    /** {@inheritDoc} **/
    @Override
    public Map<Coordinates, List<GameObject>> toMap() {
        return this.map.toMap();
    }

    // ------------------------commands for units-------------------------

    /** {@inheritDoc} **/
    @Override
    public void moveUnit(final Coordinates from, final Coordinates to) {
        if (!getValidUnitMovements(from).contains(to)) {
            throw new IllegalArgumentException();
        }
        final Unit movingUnit = (Unit) this.map.getUnit(from).get();
        movingUnit.move();
        replaceUnitOnMap(from, to);
    }

    private void replaceUnitOnMap(final Coordinates from, final Coordinates to) {
        if (!this.map.getUnit(from).isPresent() || this.getUnit(to).isPresent()) {
            throw new IllegalArgumentException();
        }
        this.map.setUnit(to, this.map.getUnit(from).get());
        this.map.removeUnit(from);
    }

    /** {@inheritDoc} **/
    @Override
    public Optional<Coordinates> makeUnitFight(final Coordinates attackerCords, final Coordinates defenderCords) {
        if (!this.map.getUnit(attackerCords).isPresent() || !this.map.getUnit(defenderCords).isPresent()
                || !getValidUnitAttacks(attackerCords).contains(defenderCords)) {
            throw new IllegalArgumentException();
        }
        final Unit attackerUnit = this.map.getUnit(attackerCords).get();
        final Unit defenderUnit = this.map.getUnit(defenderCords).get();
        attackerUnit.attack();
        defenderUnit.takeDamage(calculateDamage(attackerCords, defenderCords));
        if (defenderUnit.isDead()) {
            this.map.removeUnit(defenderCords);
            removePopulation(defenderUnit);
            if (attackerUnit.movesAfterKill() && getInUnitMovementReachCases(attackerCords).contains(defenderCords)) {
                replaceUnitOnMap(attackerCords, defenderCords);
                return Optional.of(defenderCords);
            }
        } else if (getInUnitAttackReachCases(defenderCords).contains(attackerCords)) {
            attackerUnit.takeDamage(calculateDamage(defenderCords, attackerCords));
            if (attackerUnit.isDead()) {
                this.map.removeUnit(attackerCords);
                removePopulation(attackerUnit);
                return Optional.empty();
            }
        }
        return Optional.of(attackerCords);
    }

    private void removePopulation(final Unit unit) {
        if (unit instanceof Vehicle && ((Vehicle) unit).getPassenger().isPresent()) {
            removePopulation(((Vehicle) unit).getPassenger().get());
        } else {
            this.resources.decreaseResource(unit.getOwner().get(), BasicResources.POPULATION,
                    unit.getCost().getCost().get().get(BasicResources.POPULATION));
        }
    }

    private int calculateDamage(final Coordinates damagerUnitPosition, final Coordinates damagedUnitPosition) {
        final Optional<Structure> structure = this.map.getStructure(damagedUnitPosition);
        int damage = this.map.getUnit(damagerUnitPosition).get().getStrength();
        if (structure.isPresent() && structure.get() instanceof Capital
                && getUnit(damagedUnitPosition).get().getOwner().isPresent() && structure.get().getOwner().isPresent()
                && structure.get().getOwner().get().equals(getUnit(damagedUnitPosition).get().getOwner().get())) {
            damage *= ((Capital) structure.get()).getEnemyAttackReduction();
        }
        return damage;
    }

    /** {@inheritDoc} **/
    @Override
    public Set<Coordinates> getValidUnitAttacks(final Coordinates unitCords) {
        if (!this.map.getUnit(unitCords).isPresent()) {
            throw new IllegalArgumentException();
        }
        final Unit unit = this.map.getUnit(unitCords).get();
        if (!unit.getOwner().isPresent() || !unit.getOwner().get().equals(this.getCurrentPlayer())
                || !unit.canAttack()) {
            return new HashSet<>();
        }
        return getInUnitAttackReachCases(unitCords);
    }

    private Set<Coordinates> getInUnitAttackReachCases(final Coordinates unitCords) {
        final Unit unit = this.map.getUnit(unitCords).get();
        final Set<Coordinates> area = getCoordinatesRange(unitCords, unit.getAttackRange(),
                this.map.getMapSize().getKey(), this.map.getMapSize().getValue());
        return area.stream().filter(
                c -> this.map.getUnit(c).isPresent() && !this.map.getUnit(c).get().getOwner().equals(unit.getOwner()))
                .collect(Collectors.toSet());
    }

    /** {@inheritDoc} **/
    @Override
    public Set<Coordinates> getValidUnitMovements(final Coordinates unitCords) {
        if (!this.map.getUnit(unitCords).isPresent()) {
            throw new IllegalArgumentException();
        }
        final Unit unit = this.map.getUnit(unitCords).get();
        if (!unit.getOwner().isPresent() || !unit.getOwner().get().equals(this.getCurrentPlayer()) || !unit.canMove()) {
            return new HashSet<>();
        }
        return getInUnitMovementReachCases(unitCords);
    }

    // this.map.canStep(c, ((Vehicle) unit).getPassenger().get()))
    private Set<Coordinates> getInUnitMovementReachCases(final Coordinates unitCords) {
        final Unit unit = this.map.getUnit(unitCords).get();
        final Set<Coordinates> coordinatesInRange = getCoordinatesRange(unitCords, unit.getMovementRange(),
                this.map.getMapSize().getKey(), this.map.getMapSize().getValue());
        final Set<Coordinates> area = coordinatesInRange.stream().filter(c -> this.map.canStep(c, unit))
                .collect(Collectors.toSet());
        // costruire grafo basandosi su terreni calpestabili e adiacenti
        // sono coordinate adiacenti(distanza 1) e poi chiamare successors
        // partendo da strat aggiungi coorinates a distance 1 dove pg can step su mappa

        final MutableGraph<Coordinates> shipValidPositions = GraphBuilder.undirected().build();
        shipValidPositions.addNode(unitCords);
        area.forEach(c -> shipValidPositions.addNode(c));
        shipValidPositions.nodes().forEach(n1 -> shipValidPositions.nodes().stream().filter(n2 -> distance(n1, n2) == 1)
                .forEach(n2 -> shipValidPositions.putEdge(n1, n2)));
        // bfs che ritorna mappa nodi distanze, la filtro e tengo solo i nodi a distanza
        // inferiore a unit movement range
        final Set<Coordinates> result = breadthFirstSearch(unitCords, shipValidPositions).entrySet().stream()
                .filter(e -> e.getValue() <= unit.getMovementRange() && e.getValue() > 0).map(e -> e.getKey())
                .collect(Collectors.toSet());
        if (unit instanceof Vehicle) {
            final Set<Coordinates> temp = new HashSet<>(result);
            temp.forEach(c -> {
                result.addAll(coordinatesInRange.stream()
                        .filter(p -> this.map.canStep(p, ((Vehicle) unit).getPassenger().get()))
                        .filter(p -> getCoordinatesRange(unitCords, unit.getMovementRange(),
                                this.map.getMapSize().getKey(), this.map.getMapSize().getValue()).contains(p))
                        .collect(Collectors.toSet()));
            });
        }
        return result;
    }

    // ----------methods for creation---------------------------

    /**
     * Whenever a turn starts for a Player, some checks have to be made: - checks
     * for every ResourceProducer if it is owned by this player it has to produce
     * for him; - checks for every of his Unit if is on a Structure not owned by him
     * it has to be owned by him.
     */
    private void beginningOfTurn() {
        this.resources.resetMax(getCurrentPlayer());
        final Set<Coordinates> producersCords = new HashSet<>();
        final Set<Coordinates> structureCords = new HashSet<>();
        for (int i = 0; i < map.getMapSize().getKey(); i++) {
            for (int j = 0; j < map.getMapSize().getValue(); j++) {
                final Coordinates coords = new Coordinates(i, j);
                if (map.getStructure(coords).isPresent()) {
                    final Structure struct = map.getStructure(coords).get();
                    if (struct.getOwner().isPresent() && struct.getOwner().get().equals(this.getCurrentPlayer())) {
                        if (struct instanceof ResourceProducerScalable) {
                            producersCords.add(coords);
                        } else if (struct instanceof City) {
                            this.resources.increaseMax(getCurrentPlayer(), ((City) struct).getQuantity());
                        }
                    } else if (struct instanceof OwnableStructure
                            && ((OwnableStructure) struct).getConqueror().isPresent()
                            && ((OwnableStructure) struct).getConqueror().get().equals(this.getCurrentPlayer())) {
                        structureCords.add(coords);
                    }
                }
                getAllPlayerUnit().forEach(u -> u.reset());
            }
        }
        structureCords.forEach(s -> this.checkForConquer(s));
        producersCords.forEach(p -> this.consumeResourceProducers(p));
    }

    private List<Coordinates> getListOfMapCoordinates() {
        final List<Coordinates> listOfCoords = new LinkedList<>();
        for (int i = 0; i < map.getMapSize().getKey(); i++) {
            for (int j = 0; j < map.getMapSize().getValue(); j++) {
                listOfCoords.add(new Coordinates(i, j));
            }
        }
        return (listOfCoords);
    }

    private List<Unit> getAllPlayerUnit() {
        final List<Unit> unitList = new LinkedList<>();
        getListOfMapCoordinates().forEach(coords -> {
            if (map.getUnit(coords).isPresent()) {
                final Unit unit = map.getUnit(coords).get();
                if (unit.getOwner().isPresent() && unit.getOwner().get().equals(getCurrentPlayer())) {
                    unitList.add(unit);
                }
            }
        });
        return unitList;
    }

    private List<Unit> getAllPlayerPassengers() {
        return (getAllPlayerUnit().stream()
                .filter(u -> u instanceof Vehicle && ((Vehicle) u).getPassenger().isPresent())
                .map(u -> ((Vehicle) u).getPassenger().get()).collect(Collectors.toList()));
    }

    private List<Unit> getAllPlayerUnitAndPassenger() {
        return (Stream.concat(getAllPlayerPassengers().stream(), getAllPlayerUnit().stream())
                .collect(Collectors.toList()));
    }

    private Optional<Coordinates> getCurrentPlayerCapitalCoords() {
        return getListOfMapCoordinates().stream()
                .filter(coords -> (map.getStructure(coords).isPresent()
                        && (map.getStructure(coords).get() instanceof Capital)
                        && map.getStructure(coords).get().getOwner().isPresent()
                        && map.getStructure(coords).get().getOwner().get().equals(getCurrentPlayer())))
                .findFirst();
    }

    /**
     * @param player the player defeated from which remove the units
     */
    private void changeOwner(final Player player) {
        for (int i = 0; i < map.getMapSize().getKey(); i++) {
            for (int j = 0; j < map.getMapSize().getValue(); j++) {
                final Coordinates coords = new Coordinates(i, j);
                if (map.getStructure(coords).isPresent()) {
                    final Structure struct = map.getStructure(coords).get();
                    if (struct.getOwner().isPresent() && struct.getOwner().get().equals(player)) {
                        if (struct instanceof Capital) {
                            struct.removeOwner();
                        } else {
                            if (struct instanceof City) {
                                this.resources.increaseMax(getCurrentPlayer(), ((City) struct).getQuantity());
                            }
                            struct.setOwner(getCurrentPlayer());
                        }
                    }
                }
                if (map.getUnit(coords).isPresent() && map.getUnit(coords).get().getOwner().get().equals(player)) {
                    map.removeUnit(coords);
                }
            }
        }
        for (final Resource res : this.resources.getPlayerResourceMap(getCurrentPlayer()).keySet()) {
            if (!res.getName().equals(BasicResources.POPULATION.getName())) {
                this.resources.increaseResource(this.getCurrentPlayer(), res,
                        this.resources.getPlayerResourceMap(player).get(res));
            }
        }
    }

    /** {@inheritDoc} **/
    @Override
    public void nextTurn() {
        if (this.firstTurn) {
            this.beginningOfTurn();
            Optional.empty();
            this.firstTurn = false;
        } else if (this.turnManager.getWinner(this.map).isPresent()) {
            System.out.println(this.getCurrentPlayer().getName() + " won!");
        } else {
            this.turnManager.nextTurn();
            this.beginningOfTurn();
            Optional.empty();
        }
    }

    /**
     * @param coords the coordinates of the case to be scanned
     */
    private void consumeResourceProducers(final Coordinates coords) {
        final Player current = this.getCurrentPlayer();
        final Structure struct = map.getStructure(coords).get();
        final double boost = ((WoodGoldBoost) (skillTreesManager.getAllPlayerAttributes(getCurrentPlayer()).stream())
                .filter(a -> a instanceof WoodGoldBoost).findFirst().get()).getBoost();
        if (this.resources.getPlayerResourceMap(current).get(((ResourceProducerScalable) struct).getResource())
                + ((ResourceProducerScalable) struct).produce(boost) <= this.resources.getPlayerMaxResourceMap(current)
                        .get(((ResourceProducerScalable) struct).getResource())) {
            this.resources.increaseResource(current, ((ResourceProducerScalable) struct).getResource(),
                    ((ResourceProducerScalable) struct).getQuantity());
        }
        if (((ResourceProducerScalable) struct).isOver()) {
            map.removeStructure(coords);
        }
    }

    /**
     * @param coords the coordinates of the case to be scanned
     */
    private void checkForConquer(final Coordinates coords) {
        final OwnableStructure struct = (OwnableStructure) map.getStructure(coords).get();
        if (map.getUnit(coords).isPresent()
                && map.getUnit(coords).get().getOwner().get().equals(struct.getConqueror().get())) {
            if (struct instanceof Capital) {
                final Player defeated = struct.getOwner().get();
                this.changeOwner(defeated);
                this.turnManager.removePlayer(defeated);
                struct.endConquer();
            } else {
                if (struct instanceof City) {
                    this.resources.increaseMax(getCurrentPlayer(), ((City) struct).getQuantity());
                    struct.getOwner().ifPresent(p -> this.resources.decreaseMax(p, ((City) struct).getQuantity()));
                }
                struct.setOwner(this.getCurrentPlayer());
            }
        } else if (map.getUnit(coords).isPresent()
                && map.getUnit(coords).get().getOwner().get().equals(this.getCurrentPlayer())) {
            struct.endConquer();
        } else {
            struct.initiateConquer(this.getCurrentPlayer());
        }
    }

    /** {@inheritDoc} **/
    @Override
    public Optional<Player> getWinnerPlayer() {
        return this.turnManager.getWinner(this.map);
    }

    /** {@inheritDoc} **/
    @Override
    public Player getCurrentPlayer() {
        return this.turnManager.getTurnPlayer();
    }

    /** {@inheritDoc} **/
    @Override
    public String getPlayerInfo() {
        return this.getCurrentPlayer().getName() + "\n" + this.resources.getPlayerResourcesInfo(this.getCurrentPlayer())
                + "\nObjective: " + this.getCurrentPlayer().getObjective().getDescription();
    }

    /**
     * {@inheritDoc}
     * 
     * @throws IllegalArgumentException if canCreateUnitFromCity returns false.
     **/
    @Override
    public void createUnitFromCity(final Unit unit, final Coordinates cityPosition) {
        if (canCreateUnitFromCity(unit, cityPosition)) {
            this.map.setUnit(cityPosition, unit);
            applyCost(unit.getCost());
        } else {
            throw new IllegalStateException();
        }
    }

    /** {@inheritDoc} **/
    @Override
    public boolean canCreateUnitFromCity(final Unit unit, final Coordinates cityPosition) {
        if (unit.getUnitType().getGenericUnitType().equals(GenericUnitType.HERO) && (getAllPlayerUnitAndPassenger()
                .stream().anyMatch(u -> unit.getUnitType().equals(u.getUnitType())))) {
            return false;
        }
        return verifyCost(unit.getCost());
    }

    /** {@inheritDoc} **/
    @Override
    public boolean canCityCreate(final Coordinates cityPosition) {
        return !getUnit(cityPosition).isPresent() && getStructure(cityPosition).get().getOwner().isPresent()
                && getStructure(cityPosition).get().getOwner().get().equals(this.getCurrentPlayer());
    }

    /**
     * {@inheritDoc}
     * 
     * @throws IllegalStateException if the player's Skilltree doesn't have
     *                               UnitLevel attribute.
     **/
    @Override
    public List<Unit> getPossibleUnit() {
        final List<SkillTreeAttribute> attributes = this.skillTreesManager.getAllPlayerAttributes(getCurrentPlayer());
        if (attributes.stream().filter(a -> a instanceof UnitLevel).findFirst().isPresent()) {
            return (((UnitLevel) (attributes.stream().filter(a -> a instanceof UnitLevel).findFirst().get()))
                    .getPossibleUnit(getCurrentPlayer()));
        } else {
            throw new IllegalStateException();
        }
    }

    /** {@inheritDoc} **/
    @Override
    public List<SkillTreeAttribute> getSkillTreeUpgradableAttribute() {
        return skillTreesManager.getUpgradeblePlayerAttributes(getCurrentPlayer());
    }

    /**
     * {@inheritDoc}
     * 
     * @throws IllegalArgumentException if attribute can't be upgrade.
     **/
    @Override
    public void upgradeAttribute(final SkillTreeAttribute attribute) {
        if (attribute.canUpgrade()) {
            applyCost(attribute.getCost());
            attribute.upgrade();
            if (attribute instanceof CapitalLevel) {
                updatePlayerCapital((CapitalLevel) attribute);
            }
        }
    }

    private void updatePlayerCapital(final CapitalLevel capitalLevelAttribute) {
        if (getCurrentPlayerCapitalCoords().isPresent()) {
            final Coordinates coords = getCurrentPlayerCapitalCoords().get();
            final int quantityToBeRemoved = ((Capital) map.getStructure(coords).get()).getQuantity();
            map.removeStructure(coords);
            map.setStructure(coords, capitalLevelAttribute.getActualCapital(getCurrentPlayer()));
            this.resources.increaseMax(getCurrentPlayer(), ((Capital) map.getStructure(coords).get()).getQuantity());
            this.resources.decreaseMax(getCurrentPlayer(), quantityToBeRemoved);
        }
    }

    /** {@inheritDoc} **/
    @Override
    public boolean canUpgradeAttribute(final SkillTreeAttribute attributeToUpgrade) {
        return attributeToUpgrade.canUpgrade() && verifyCost(attributeToUpgrade.getCost());
    }

    private void applyCost(final Cost cost) {
        if (cost.getCost().isPresent()) {
            cost.getCost().get().entrySet().forEach(e -> {
                if (e.getKey().getName().equals("Population")) {
                    resources.increaseResource(getCurrentPlayer(), e.getKey(), e.getValue());
                } else {
                    resources.decreaseResource(getCurrentPlayer(), e.getKey(), e.getValue());
                }
            });
        }
    }

    private boolean verifyCost(final Cost cost) {
        return (!cost.getCost().isPresent() || (cost.getCost().isPresent() && cost.getCost().get().entrySet().stream()
                .filter(e -> (this.resources.getPlayerResourceMap(this.getCurrentPlayer()).containsKey(e.getKey()))
                        && ((e.getKey().getName().equals("Population"))
                                && (this.resources.getPlayerMaxResourceMap(this.getCurrentPlayer()).get(e.getKey())
                                        - this.resources.getPlayerResourceMap(this.getCurrentPlayer())
                                                .get(e.getKey())) >= e.getValue())
                        || (!(e.getKey().getName().equals("Population")) && this.resources
                                .getPlayerResourceMap(this.getCurrentPlayer()).get(e.getKey()) >= e.getValue()))
                .collect(Collectors.toList()).size() == cost.getCost().get().size()));
    }

    /** @{inheritDoc} **/
    @Override
    public int getResourceQuantityForPlayer(final Player player, final Resource resource) {
        return this.resources.getPlayerResourceMap(player).get(resource);
    }

}
