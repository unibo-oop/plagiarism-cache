package it.unibo.ninjafrog.game.utilities;

/**
 * Utility class which contains all the constants of the game.
 */
public final class GameConst {

    /**
     * Virtual screen width value.
     */
    public static final int WIDTH = 400;
    /**
     * Virtual screen height value.
     */
    public static final int HEIGHT = 208;

    /**
     * Box2D scale value. Acronym of Pixel Per Meters.
     */
    public static final float PPM = 100;

    /**
     * A bit representation of a Ground object, used to define it and what it can
     * collide with.
     */
    public static final short GROUND = 1;
    /**
     * A bit representation of the Ninja, used to define it and what it can collide
     * with.
     */
    public static final short NINJA = 2;
    /**
     * A bit representation of a Brick object, used to define it and what it can
     * collide with.
     */
    public static final short BRICK = 4;
    /**
     * A bit representation of a FruitBox object, used to define it and what it can
     * collide with.
     */
    public static final short FRUITBOX = 8;
    /**
     * A bit representation of a destroyed object.
     */
    public static final short DESTROYED = 16;
    /**
     * A bit representation of a Rino object, used to define it and what it can
     * collide with.
     */
    public static final short RINO = 32;
    /**
     * A bit representation of a Rino's head.
     */
    public static final short RINO_HEAD = 64;
    /**
     * A bit representation of a Turtle object, used to define it and what it can
     * collide with.
     */
    public static final short TURTLE = 128;
    /**
     * A bit representation of a Turtle's head.
     */
    public static final short TURTLE_HEAD = 256;
    /**
     * A bit representation of a Ground object, used to define it and what it can
     * collide with.
     */
    public static final short GROUND_OBJECT = 512;
    /**
     * A bit representation of the Ninja's head.
     */
    public static final short NINJA_HEAD = 1024;
    /**
     * A bit representation of a Fruit object, used to define it and what it can
     * collide with.
     */
    public static final short FRUIT = 2048;
    /**
     * A bit representation of a Finish Trophy object, used to define it and what it
     * can collide with.
     */
    public static final short FINISH = 4096;

    private GameConst() {
    }
}
