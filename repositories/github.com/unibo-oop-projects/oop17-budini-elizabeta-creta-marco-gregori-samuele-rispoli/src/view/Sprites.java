package view;

import java.awt.Dimension;

import javax.swing.ImageIcon;

import utilities.ImageLoader;

/**
 * An enum containing all the characters' sprites to be drawn, with a method to
 * retrieve an icon out of its image path.
 *
 */
public enum Sprites {
    /**
     * The gorilla sprite in idle position.
     */
    GORILLA_IDLE("gorilla_idle.png", new Dimension(60, 60)),
    /**
     * The gorilla sprite, facing right.
     */
    GORILLA_FACING_RIGHT("gorilla_r.png", new Dimension(60, 60)),
    /**
     * The princess sprite.
     */
    PRINCESS("princess.gif", new Dimension(50, 43)),
    /**
     * The main character sprite, facing right..
     */
    MARIO_FACING_RIGHT("mario_facing_right.png", new Dimension(25, 30)),
    /**
     * The main character sprite, facing left.
     */
    MARIO_FACING_LEFT("mario_facing_left.png", new Dimension(25, 30)),
    /**
     * The main character sprite, jumping right.
     */
    MARIO_JUMPING_RIGHT("mario_jumping_right.png", new Dimension(25, 30)),
    /**
     * The main character sprite, jumping left.
     */
    MARIO_JUMPING_LEFT("mario_jumping_left.png", new Dimension(25, 30)),
    /**
     * The main character sprite, walking right.
     */
    MARIO_WALKING_RIGHT("mario_walking_right.gif", new Dimension(25, 30)),
    /**
     * The main character sprite, walking left.
     */
    MARIO_WALKING_LEFT("mario_walking_left.gif", new Dimension(25, 30)),
    /**
     * The main character sprite, stationary on a stair.
     */
    MARIO_ON_STAIR("mario_on_stair.png", new Dimension(25, 30)),
    /**
     * The main character sprite, climbing the stairs.
     */
    MARIO_CLIMBING_STAIRS("mario_climbing.gif", new Dimension(25, 30)),
    /**
     * The main character sprite, climbing the stairs.
     */
    MARIO_DEATH("mario_death.gif", new Dimension(30, 30)),
    /**
     * The barrel sprite, rotating right.
     */
    BARREL_RIGHT("barrel_rolling_right.gif", new Dimension(20, 20)),
    /**
     * The barrel sprite, rotating left.
     */
    BARREL_LEFT("barrel_rolling_left.gif", new Dimension(20, 20)),
    /**
     * The barrelOnStair sprite, rotating.
     */
    BARREL_ON_STAIR_ROLLING("barrel_rolling_right.gif", new Dimension(20, 20)),
    /**
     * The barrel sprite, falling down the stairs.
     */
    BARREL_FALLING_ON_STAIRS("barrel_falling_on_stair.gif", new Dimension(25, 20)),
    
    TEST_FLOOR("floor_tile_test.png", new Dimension(100, 22)),
    TEST_STAIR("stair_test.png", new Dimension(12,50)),
    TEST_STAIR_TRIGGER("stair_trigger_test.png", new Dimension(1,2));

    private static final String SPRITES_FOLDER = "sprites/";
    private final String imagePath;
    private final ImageLoader loader = ImageLoader.getInstance();
    private final Dimension spriteDimension;

    Sprites(final String path, final Dimension dim) {
        this.imagePath = path;
        this.spriteDimension = dim;
    }

    /**
     * Load an ImageIcon out of the stored image path.
     * 
     * @return An imageIcon representing the entity's sprite.
     */
    public ImageIcon getIcon() {
        return this.loader.getImage(SPRITES_FOLDER + imagePath);
    }

    /**
     * Method to return the current sprite dimension.
     * 
     * @return A dimension representing the dimension of the sprite.
     */
    public Dimension getDimension() {
        return spriteDimension;
    }

}
