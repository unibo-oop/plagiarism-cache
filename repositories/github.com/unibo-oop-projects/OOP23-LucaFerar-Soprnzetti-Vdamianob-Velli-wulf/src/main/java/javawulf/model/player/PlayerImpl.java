package javawulf.model.player;

import java.util.List;
import java.util.logging.Logger;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.util.ArrayList;

import javawulf.model.BoundingBox;
import javawulf.model.BoundingBoxImpl;
import javawulf.model.Direction;
import javawulf.model.AbstractEntity;
import javawulf.model.Coordinate;
import javawulf.model.CoordinateImpl;
import javawulf.model.BoundingBox.CollisionType;
import javawulf.model.item.AmuletPiece;
import javawulf.model.map.Map;
import javawulf.model.map.TileType;
import javawulf.model.powerup.PowerUpHandler;
import javawulf.model.powerup.PowerUpHandlerImpl;

/**
 * PlayerImpl is an implementation of Player.
 */
public final class PlayerImpl extends AbstractEntity implements Player {

    private static final int DAMAGE = -1;
    private final PlayerHealth health;
    private final Score score;
    private final Sword sword;
    private static final int NUMBER_OF_PIECES = 4;
    private final List<AmuletPiece> piecesCollected;
    private PlayerColor color;
    private final PowerUpHandler powerUpHandler;
    private static final int PLAYER_STUN = 4;

    /**
     * Creates a new Player.
     * 
     * @param startingX The starting position on the X axis of the Player
     * @param startingY The starting position on the Y axis of the Player
     * @param health The amount of healtg the player starts the game with
     * @param startingPoints The amount of points the player starts the game with
     */
    public PlayerImpl(final int startingX, final int startingY, final int health, final int startingPoints) {
        super(new CoordinateImpl(startingX, startingY), CollisionType.PLAYER, Player.DEFAULT_SPEED);
        this.score = new ScoreImpl(startingPoints);
        this.setDirection(Direction.DOWN);
        this.health = new PlayerHealthImpl(health);
        this.sword = new SwordImpl(this.getPosition(), this.getDirection());
        this.piecesCollected  = new ArrayList<>(NUMBER_OF_PIECES);
        this.powerUpHandler = new PowerUpHandlerImpl();
        this.color = PlayerColor.NONE;
    }

    @Override
    public void attack() {
        this.sword.activate();
    }

    @Override
    public void move(final Direction direction, final Map map) {
        final Coordinate current = this.getPosition();
        final int delta = this.getSpeed() * MOVEMENT_DELTA;
        final BoundingBox preview = new BoundingBoxImpl(current.getX() + (int) (direction.getX() * delta),
            current.getY() + (int) (direction.getY() * delta), OBJECT_SIZE, OBJECT_SIZE, CollisionType.PLAYER);
        final var tiles = map.getTileTypes(preview);
        if (!tiles.contains(TileType.WALL)) {
            this.setPosition(new CoordinateImpl(current.getX() + (int) (direction.getX() * delta),
                current.getY() + (int) (direction.getY() * delta)));
            this.getBounds().setCollisionArea(preview.getCollisionArea());
            this.setDirection(direction);
            this.sword.move(this.getPosition(), direction);
        } else {
            throw new IllegalStateException("There is a wall");
        }
    }

    @Override
    public boolean isHit(final BoundingBox box) {
        if (super.isHit(box)) {
            this.health.setHealth(DAMAGE);
            if (isDefeated()) {
                this.getBounds().changeCollisionType(CollisionType.INACTIVE);
                Logger.getLogger(PlayerImpl.class.getName()).fine("Oh no! You Died. GAME OVER");
            } else {
                this.setStun(PLAYER_STUN);
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void collectAmuletPiece(final AmuletPiece piece) {
        if (this.piecesCollected.size() == NUMBER_OF_PIECES) {
            throw new IllegalStateException("Already gotten all fragments of the amulet");
        } else {
            this.piecesCollected.add(piece);
        }
    }

    @Override
    @SuppressFBWarnings(
        value = {
            "M", "V", "EI"
        },
        justification = "PlayerHealth returns all its info to the controller"
    )
    public PlayerHealth getPlayerHealth() {
        return this.health;
    }

    @Override
    public PowerUpHandler getPowerUpHandler() {
        return this.powerUpHandler;
    }

    private boolean isDefeated() {
        return this.health.getHealth() <= 0;
    }

    @Override
    @SuppressFBWarnings(
        value = {
            "M", "V", "EI"
        },
        justification = "The score returns all its info to the controller"
    )
    public Score getScore() {
        return this.score;
    }

    @Override
    @SuppressFBWarnings(
        value = {
            "M", "V", "EI"
        },
        justification = "The sword returns all its info to the controller and has to be modified by it"
    )
    public Sword getSword() {
        return this.sword;
    }

    @Override
    public String getColor() {
        return this.color.getColor();
    }

    @Override
    public void setColor(final PlayerColor color) {
        this.color = color;
    }

    @Override
    public int getNumberOfPieces() {
        return this.piecesCollected.size();
    }

    @Override
    protected boolean control(final BoundingBox box) {
        return box.getCollisionType().equals(CollisionType.ENEMY)
            && this.getBounds().getCollisionType().equals(CollisionType.PLAYER);
    }

    @Override
    protected CollisionType originalCollisionType() {
        return CollisionType.PLAYER;
    }

    @Override
    public boolean hasPlayerWon(final Map map) {
        final var tiles = map.getTileTypes(this.getBounds());
        return tiles.contains(TileType.PORTAL) && this.getNumberOfPieces() == NUMBER_OF_PIECES;
    }

}
