
package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.entities.Brick;
import model.utilities.BrickStatus;
import model.utilities.Position;

/**
 * test class for Brick.
 *
 */
public class TestBrick {
    private static final Position BRICK_POS = new Position(50, 50);
    private static final int BRICK_WIDTH = 10;
    private static final int BRICK_HEIGHT = 10;
    private static final int BRICK_DURABILITY = 1;
    private static final String PATH = "Images/brick/crashBrick.png";
    private static final BrickStatus STATUS = BrickStatus.DESTRUCTIBLE;
    private static final int BALL_DAMAGE = 1;

    private Brick brick;
    /**
     * Initialize fields before the test start.
     */
    @BeforeEach
    public void createEntity() {
        this.brick = createBrick();
    }

    /**
     * test brick creation.
     * @return brick new Brick
     */
    @Test
    public Brick createBrick() {
        return new Brick.Builder()
                .durability(BRICK_DURABILITY)
                .height(BRICK_HEIGHT)
                .width(BRICK_WIDTH)
                .position(BRICK_POS)
                .status(STATUS)
                .texture(PATH)
                .build();
    }

    /**
     * Checking if the builder sets all the fields correctly.
     */
    @Test
    public void brickCreation() {
        assertEquals(BRICK_DURABILITY, brick.getDurability());
        assertEquals(BRICK_HEIGHT, brick.getHeight());
        assertEquals(BRICK_WIDTH, brick.getWidth());
        assertEquals(BRICK_POS, brick.getPos());
        assertEquals(STATUS, brick.getStatus());
        assertEquals(0, brick.getSpeed());
    }

    /**
     * Damage the brick and tests its durability.
     */
    @Test
    public void brickDamage() {
        this.brick.decreaseDurability(BALL_DAMAGE);
        assertEquals(0, this.brick.getDurability());
    }
}
