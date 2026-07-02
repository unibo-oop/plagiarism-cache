package vg.model;

import vg.model.entity.ShapedEntity;
import vg.model.entity.dynamicEntity.player.Tail;
import vg.model.entity.Entity;
import vg.model.entity.dynamicEntity.DynamicEntity;
import vg.model.entity.dynamicEntity.bullet.Bolt;
import vg.model.entity.dynamicEntity.bullet.EnemyBolt;
import vg.model.entity.dynamicEntity.enemy.Boss;
import vg.model.entity.dynamicEntity.player.Player;
import vg.model.entity.staticEntity.StaticEntity;
import vg.utils.Direction;
import vg.utils.MassTier;
import vg.utils.V2D;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * {@inheritDoc}
 * The Type used to implement {@link Map} is {@link V2D}.
 * This class implements {@link Serializable} for {@link MapFactory}.
 */
public class MapImpl implements Map<V2D>, Serializable {
    /**
     * The player.
     * @see Player
     */
    private Player player;
    /**
     * The boss.
     * @see Boss
     */
    private Boss boss;

    /**
     * Set of all static entities on the map.
     * @see StaticEntity
     */
    private final Set<StaticEntity> staticEntitySet;
    /**
     * Set of all dynamic entities on the map,
     * excluding the player and the boss.
     * @see DynamicEntity
     */
    private final Set<DynamicEntity> dynamicEntitySet;

    /**
     * The Set of {@link V2D} position that will indicate
     * the current border of the map.
     * @see Map#getBorders()
     */
    private final Set<V2D> border;
    /**
     * Default max X coordinate.
     */
    public static final int MAXBORDERX = 200;
    /**
     * Default max Y coordinate.
     */
    public static final int MAXBORDERY = 150;

    private double getOccupiedPercentage;

    /**
     * Constructor for MapImpl.
     *
     * @param player           the player of the map
     * @param boss             the boss of the map
     * @param staticEntitySet  the set of all static entities
     * @param dynamicEntitySet the set of all dynamic entities
     * @param border           the border of the map
     */
    public MapImpl(final Player player, final Boss boss, final Set<StaticEntity> staticEntitySet, final Set<DynamicEntity> dynamicEntitySet, final Set<V2D> border) {
        this.player = player;
        this.boss = boss;
        this.staticEntitySet = staticEntitySet;
        this.dynamicEntitySet = dynamicEntitySet;
        this.border = border;
        this.getOccupiedPercentage = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getOccupiedPercentage() {
        return this.getOccupiedPercentage;
    }
    private void updateOccupiedPercentage() {
        //approximate and slow, to upgrade
        this.getOccupiedPercentage = ((double) IntStream.rangeClosed(0, MAXBORDERX).boxed().flatMap(e -> IntStream.rangeClosed(0, MAXBORDERY).boxed().flatMap(e2 -> Stream.of(new V2D(e, e2)))).filter(e -> !isInBorders(e)).count()) / (MAXBORDERX * MAXBORDERY);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Set<V2D> getBorders() {
        return this.border;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void updateBorders(final List<V2D> tail) {
        //internal check if the tail is actually completed
        //and the borders can be updated from it
        if (!isTailCompleted()) {
            throw new IllegalStateException("Attempted to call updateBorders while the tail is not completed");
        }

        var tr = createNewBorder(tail, getBoss().getPosition());
        this.getBorders().addAll(tr);
        this.getBorders().retainAll(tr);
        updateOccupiedPercentage();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Returns true if the player is in {@link #border}.
     * @return true if the player is on the boards
     */
    @Override
    public boolean isPlayerOnBorders() {
        return this.getBorders().contains(getPlayer().getPosition());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Set<V2D> getTail() {
        return new HashSet<>(getPlayer().getTail().getCoordinates());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isTailCompleted() {
        if (getPlayer().getTail().getLastCoordinate().isPresent()) {
            return getBorders().contains(getPlayer().getTail().getLastCoordinate().get());
        } else {
            return false;
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean toCapture(final Entity toCheck) {
        return false;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void removeEntity(final Entity toRemove) {
        if (toRemove instanceof DynamicEntity) {
           getAllDynamicEntities().remove(toRemove);
        } else if (toRemove instanceof StaticEntity) {
            getAllStaticEntities().remove(toRemove);
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void updateBonusTimer(double elapsedTime) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<StaticEntity> getAllStaticEntities() {
        return this.staticEntitySet;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Set<DynamicEntity> getAllDynamicEntities() {
        return this.dynamicEntitySet;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Set<DynamicEntity> getBolts() {
        return this.dynamicEntitySet.stream().filter(e -> e instanceof Bolt).collect(Collectors.toSet());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Set<DynamicEntity> getEnemyBolts() {
        return this.dynamicEntitySet.stream().filter(e -> e instanceof EnemyBolt).collect(Collectors.toSet());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Set<DynamicEntity> getFriendlyBolts() {
        return this.dynamicEntitySet.stream().filter(e -> e instanceof Bolt && !(e instanceof EnemyBolt)).collect(Collectors.toSet());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Boss getBoss() {
        return this.boss;
    }

    /**
     * Creates the new border using the {@link vg.model.entity.dynamicEntity.player.Tail} of the
     * {@link Player} and the position ({@link V2D}) of the {@link Boss}. This method will not update
     * the borders, it is called internally by {@link #updateBorders(List)}. A side note: the {@link Set}
     * can be extracted by {@link Tail#getCoordinates()}.
     * @param tail a set of {@link V2D}.
     * @param boss the {@link Boss} of the map.
     * @return {@link #getBorders()}
     */
    private List<V2D> createNewBorder(final List<V2D> tail, final V2D boss) {
        List<V2D> t0 = new ArrayList<>(getPlayer().getTail().getCoordinates());
        List<V2D> t1 = new ArrayList<>(getPlayer().getTail().getCoordinates());
        var adjBorderPoint = this.getBorders().stream().filter(e -> e.isAdj(getPlayer().getTail().getCoordinates().get(0))).collect(Collectors.toList());

        if (adjBorderPoint.size() != 2) {
           throw new IllegalStateException("Tail initial point has not exactly 2 adj points" + adjBorderPoint);
        }
        if (!t0.get(t0.size() - 1).isAdj(adjBorderPoint.get(0))) {
            Collections.reverse(t0);
        }
        if (!t1.get(t1.size() - 1).isAdj(adjBorderPoint.get(1))) {
            Collections.reverse(t1);
        }
        try {
            while (!t0.get(t0.size() - 1).equals(getPlayer().getTail().getLastCoordinate().get())) {
                t0.add(this.getBorders().stream().filter(e -> !t0.contains(e) && e.isAdj(t0.get(t0.size() - 1))).findFirst().orElseThrow());
            }
        } catch (NoSuchElementException e0) {
            if (!t0.get(0).isAdj(t0.get(t0.size() - 1))) {
                throw new IllegalStateException(player.getTail().getCoordinates().get(0) + "-----" + adjBorderPoint + "The list of points is not closed; t0: " + t0);
            }
        }
        try {
            t1.add(adjBorderPoint.get(1));
            while (!t1.get(t1.size() - 1).equals(getPlayer().getTail().getLastCoordinate().get())) {
                t1.add(this.getBorders().stream().filter(e -> !t1.contains(e) && e.isAdj(t1.get(t1.size() - 1))).findFirst().orElseThrow());
            }
        } catch (NoSuchElementException e1) {
            if (!t1.get(0).isAdj(t1.get(t1.size() - 1))) {
                throw new IllegalStateException("The list of points is not closed; t1: " + t1);
            }
        }
        if (isInBorders(boss, Set.copyOf(t1))) {
            if (isInBorders(boss, Set.copyOf(t0))) {
                throw new IllegalStateException("Error in algorithm: the boss in both generated borders");
            }
            return t1;
        } else if (isInBorders(boss, Set.copyOf(t0))) {
            return t0;
        } else {
            throw new IllegalStateException("Failed to create a new border (Boss too big?)");
        }
    }
    /* Old method, not used anymore, maybe can be inspirational
     *  to check if a point will be closed by the border
     * or not. Works only with points
     * @param pos the starting position from which to check
     * @param tail the tail that will become the new border
     * @param boss the boss of the map
     * @return true if the position will be outside the map
     *         and will be captured/deleted, false otherwise
     *
    public boolean isClosedByTail(final V2D pos, final Set<V2D> tail, final Boss boss) {

        if (tail.contains(pos)) {
            return false;
        }
        V2D step = new V2D(pos);
        var dx = pos.getX() - boss.getPosition().getX();
        var dy = pos.getY() - boss.getPosition().getY();

        for (int i = 0; i <= Math.abs(dx); i++) {
            if (tail.contains(step.sum(new V2D(Math.signum(-dx) * i, 0)))) {
                //the original point is between the tail and the boss
                return true;
            }
        }
        for (int i = 0; i <= Math.abs(dy); i++) {
            if (tail.contains(step.sum(new V2D(-dx, Math.signum(-dy) * i)))) {
                //the original point is between the tail and the boss

                return true;
            }
        }
        // if the cycles finished and tail not contains a point between pos and the boss
        return false;
    }
    */
    /**
     * Checks if a position is inside the current borders.
     * @param pos the position to check
     * @return true if the position is inside the borders, false otherwise
     */
    public boolean isInBorders(final V2D pos) {
        if (getBorders().contains(pos) || pos.getX() < 0) {
            return false;
        }
        return isInBorders(pos,getBorders());
    }

    /**
     * Attempt to create a more performing {@link #updateOccupiedPercentage()}.
     * Not finished so don't use it, but the idea is to compute an entire axis
     * at one instead of every point (how now {@link #isInBorders(V2D)}) do.
     * @param yPos the axis to compute
     * @return yOccupied/yTotal on a single axis
     */
    public double isInBorderAxis(final int yPos) {
        List<Integer> segments = new ArrayList<>();
        IntStream.rangeClosed(1, MapImpl.MAXBORDERX)
                .filter(e -> getBorders().contains(new V2D(e, yPos)) != getBorders().contains(new V2D(e - 1, yPos)))
                .peek(segments::add);


        if (!segments.isEmpty()) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * This was not easy at all even if it seems so,
     * bugs that need to be solved are: if a "segment" is
     * parallel to a y-axis, it will generate problems, for now
     * some are limited by {@link StageImpl#checkAllOutOfBounds()}.
     * The algorithmic idea is that from the first border the positions
     * are "inside" then outside, then inside again and so on.
     * @param pos to check
     * @param borders {@link #getBorders()}
     * @return true if is in borders, false otherwise
     */
    private boolean isInBorders(final V2D pos, final Set<V2D> borders) {

        List<Integer> segments = new ArrayList<>();
        var t = IntStream.rangeClosed(1, MapImpl.MAXBORDERX)
                .filter(e -> borders.contains(new V2D(e, pos.getY())) != borders.contains(new V2D(e - 1, pos.getY())))
                .peek(segments::add).filter(e -> pos.getX() < e).findFirst();
        if (t.isPresent()) {
            var s = new ArrayList<Integer>();
            s.add(segments.get(0));
            for (int i = 1; i < segments.size(); i++) {
                if (segments.get(i) - 1 != s.get(s.size() - 1)) {
                    s.add(segments.get(i));
                }
            }
            return s.indexOf(t.getAsInt()) % 2 == 1;
        } else {
            return false;
        }
    }
    /**
     * This method tell if a position is valid on the
     * map (not out of borders and is not on the same
     * position with other entities that has a mass
     * tier higher then {@link vg.utils.MassTier#NOCOLLISION}).
     * @param pos the position to check
     * @return true if the position can be occupied, false otherwise
     */
    public boolean isPositionValid(final V2D pos) {
        if (getAllStaticEntities().stream().anyMatch(e -> e.isInShape(pos))) {
            return false;
        }
        if (getAllDynamicEntities().stream().filter(e -> e.getMassTier() != MassTier.NOCOLLISION).anyMatch(e -> e.isInShape(pos))) {
            return false;
        }
        return isInBorders(pos);
    }
    /**
     * Overloading for checking if a position is valid but removing one
     * entity from checks. This is used in {@link MapImpl#getAfterCollisionDirection(DynamicEntity)}
     * where it checks for every entity show to "bounce" but it excludes itself from computations
     * (and the entities that has a mass tier lower).
     * @param pos the position to check
     * @param toRemove the entity to remove
     * @return true if the position is valid and can be occupied, false otherwise
     */
    public boolean isPositionValid(final V2D pos, final ShapedEntity toRemove) {
        try {
            if (getAllStaticEntities().stream().anyMatch(e -> e.isInShape(pos) && !e.equals(toRemove))) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (getAllDynamicEntities().stream()
                .filter(e -> e.getMassTier() != MassTier.NOCOLLISION && !e.equals(toRemove))
                .filter(e -> e.getMassTier().ordinal() >= toRemove.getMassTier().ordinal())
                .anyMatch(e -> e.isInShape(pos))) {
            return false;
        }
        return isInBorders(pos);
    }

    /**
     * This method will compute what is the correct direction
     * to take for an entity that is colliding.
     * @param de the entity to compute the new direction
     * @return the correct speed to apply to the entity
     */
    public V2D getAfterCollisionDirection(final DynamicEntity de) {
        var t = Stream.of(-1, 1).flatMap(e -> Stream.of(new V2D(e, 0), new V2D(0, e))).
                filter(e -> isPositionValid(e.sum(de.getPosition()), de)).reduce(new V2D(0, 0), V2D::sum);
        if (!(t.getX() == 0 || 0 == t.getY()) || t.getX() == t.getY()) {
            return de.getSpeed().mul(new V2D(-1, -1));
        } else {
            var x = t.getX() == 0 ? 1 : -1;
            var y = t.getY() == 0 ? 1 : -1;
            return de.getSpeed().mul(x, y);
        }
    }

    /**
     * This method add all the crossed points by the player movement to the tail.
     * @see Player
     * @see Tail
     * @see V2D
     */
    public void addTailPointsByPlayerSpeed() {
        if (getPlayer().getDirection().equals(Direction.NONE)) {
            return;
        }
        var t = getPlayer().getSpeed().mul(getPlayer().getDirection().getVector()).scalarMul(-1);
        while (!t.equals(new V2D(0, 0))) {

            getPlayer().getTail().addPoint(getPlayer().getPosition().sum(t));
            t = t.sum(getPlayer().getDirection().getVector());
        }
        getPlayer().getTail().addPoint(getPlayer().getPosition());
    }
    protected void setPlayer(Player p){
        this.player = p;
    }
    protected void setBoss(Boss b){
        this.boss = b;
    }
}
