package javawulf.model.enemy;

import java.util.Random;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javawulf.model.Direction;
import javawulf.model.BoundingBox;
import javawulf.model.BoundingBox.CollisionType;
import javawulf.model.BoundingBoxImpl;
import javawulf.model.map.Map;
import javawulf.model.map.TileType;
import javawulf.model.Coordinate;
import javawulf.model.CoordinateImpl;
import javawulf.model.player.Player;

/**
 * The pawn represents the most basic enemy in the game, it
 * moves randomly around the map.
 */
public final class Pawn extends EnemyImpl {

    private static final int POINTS = 100;

    private final Random random = new Random();

    private boolean isAlive;
    private int timeToWait;
    private int tickCount;

    /**
     * Creates a pawn.
     * 
     * @param position the position of the pawn when created
     */
    @SuppressFBWarnings(
        value = {
            "H", "B", "DMI"
        },
        justification = "False positive, the random value is used twice in this constructor"
    )
    public Pawn(final Coordinate position) {
        super(position);
        this.isAlive = true;
        this.timeToWait = random.nextInt(4) + 1;
        this.setDirection(Direction.values()[this.timeToWait - 1]);
        this.tickCount = 0;
    }

    /**
     * @return true if the pawn is alive, false otherwise
     */
    public boolean isAlive() {
        return isAlive;
    }

    /**
     * @return the time to wait before changing direction
     */
    public int getTimeToWait() {
        return timeToWait;
    }

    /**
     * @return the tick count
     */
    public int getTickCount() {
        return this.tickCount;
    }

    @Override
    public void move(final Player p, final Map m) {

        final Coordinate current = this.getPosition();
        final int delta = this.getSpeed() * MOVEMENT_DELTA;

        final BoundingBox preview = new BoundingBoxImpl(current.getX() + (int) (this.getDirection().getX() * delta),
                current.getY() + (int) (this.getDirection().getY() * delta), OBJECT_SIZE, OBJECT_SIZE,
                CollisionType.PLAYER);
        final var tiles = m.getTileTypes(preview);
        if (!tiles.contains(TileType.WALL) && !tiles.contains(TileType.CENTRAL_ROOM)) {
            this.setPosition(new CoordinateImpl(current.getX() + (int) (this.getDirection().getX() * delta),
                    current.getY() + (int) (this.getDirection().getY() * delta)));
            this.getBounds().setCollisionArea(preview.getCollisionArea());
            this.setDirection(this.getDirection());
        } else {
            this.turnPawn(this.getDirection());
        }

    }

    @Override
    public void takeHit(final Player p) {
        if (super.isHit(p.getSword().getBounds())) {
            this.isAlive = false;
            p.getScore().addPoints(POINTS);
            this.getBounds().changeCollisionType(CollisionType.INACTIVE);
        }
    }

    @Override
    public void tick() {

        this.tickCount++;

        if (this.tickCount >= this.timeToWait) {
            this.turnPawn(this.getDirection());
        }
    }

    /**
     * Turns the pawn in a random direction that is different from the current.
     * 
     * @param d the current direction
     */
    private void turnPawn(final Direction d) {
        do {
            this.setDirection(Direction.values()[random.nextInt(4)]);
        } while (d.equals(this.getDirection()));
        this.timeToWait = random.nextInt(4) + 1;
        this.tickCount = 0;
    }

}
