package maingame.graphics;

import java.awt.Dimension;

import util.Vector2;
import util.Vector2Impl;

/** Classe Sprite. */

public class SpriteImpl implements Sprite {

    private final Dimension dimension;
    private int[] pixels;
    private SpriteSheet sheet;

    private static final Dimension DIM8 = new Dimension(8, 8);
    private static final Dimension DIM16 = new Dimension(16, 16);
    private static final Dimension DIM16X8 = new Dimension(16, 8);
    private static final Dimension DIM8X16 = new Dimension(8, 16);
    private static final Dimension DIM16X10 = new Dimension(16, 10);
    private static final Dimension DIM16X24 = new Dimension(16, 24);
    private static final Dimension DIM300X168 = new Dimension(300, 168);
    private static final Dimension PARTICLEDIM = new Dimension(2, 2);
    private static final int VOIDCOLOR = 0x000000;
    private static final int REDSUPERPARTCOLOR = 0xFF7F27;
    private static final int SUPERPARTCOLOR = 0x3F9EFF;
    private static final int PARTCOLOR = 0x7F7F7F;

    /** Sprite Void. */
    public static final Sprite VOID = SpriteImpl.spriteFromColor(DIM8, VOIDCOLOR);

    /** Sprite Grass1. */
    public static final Sprite GRASS1 = SpriteImpl.spriteFromSpriteSheet(DIM8, new Vector2Impl<Integer>(0, 0),
            SpriteSheetImpl.TILESET);
    /** Sprite Grass2. */
    public static final Sprite GRASS2 = SpriteImpl.spriteFromSpriteSheet(DIM8, new Vector2Impl<Integer>(1, 0),
            SpriteSheetImpl.TILESET);
    /** Sprite Grass3. */
    public static final Sprite GRASS3 = SpriteImpl.spriteFromSpriteSheet(DIM8, new Vector2Impl<Integer>(2, 0),
            SpriteSheetImpl.TILESET);
    /** Sprite Grass4. */
    public static final Sprite GRASS4 = SpriteImpl.spriteFromSpriteSheet(DIM8, new Vector2Impl<Integer>(3, 0),
            SpriteSheetImpl.TILESET);
    /** Sprite Grass vettore. */
    public static final Sprite[] GRASS_SPRITES = new Sprite[] { GRASS1, GRASS2, GRASS3, GRASS4 };
    /** Sprite grass-water. */
    public static final Sprite[] GRASS_WATER_SPRITES = SpriteSheetImpl.GRASS_WATER.getSprites();

    /** Sprite grass-sand. */
    public static final Sprite[] GRASS_SAND_SPRITES = SpriteSheetImpl.GRASS_SAND.getSprites();

    /** Sprite grass transitions. */
    public static final Sprite[][] GRASS_TRANSITION_SPRITES = new Sprite[][] { GRASS_WATER_SPRITES,
            GRASS_SAND_SPRITES };
    /** Sprite Sand1. */
    public static final Sprite SAND1 = SpriteImpl.spriteFromSpriteSheet(DIM8, new Vector2Impl<Integer>(0, 2),
            SpriteSheetImpl.TILESET);
    /** Sprite Sand2. */
    public static final Sprite SAND2 = SpriteImpl.spriteFromSpriteSheet(DIM8, new Vector2Impl<Integer>(1, 2),
            SpriteSheetImpl.TILESET);
    /** Sprite Sand3. */
    public static final Sprite SAND3 = SpriteImpl.spriteFromSpriteSheet(DIM8, new Vector2Impl<Integer>(2, 2),
            SpriteSheetImpl.TILESET);

    /** Sprite Sand4. */
    public static final Sprite SAND4 = SpriteImpl.spriteFromSpriteSheet(DIM8, new Vector2Impl<Integer>(3, 2),
            SpriteSheetImpl.TILESET);
    /** Sprite Sand step. */
    public static final Sprite SAND_STEP = SpriteImpl.spriteFromSpriteSheet(DIM8, new Vector2Impl<Integer>(0, 9),
            SpriteSheetImpl.TILESET);
    /** Sprite Sand vettore. */
    public static final Sprite[] SAND_SPRITES = new Sprite[] { SAND1, SAND2, SAND3, SAND4, SAND_STEP };
    /** Sprite sand-water. */
    public static final Sprite[] SAND_WATER_SPRITES = SpriteSheetImpl.SAND_WATER.getSprites();
    /** Sprite sand transitions. */
    public static final Sprite[][] SAND_TRANSITION_SPRITES = new Sprite[][] { SAND_WATER_SPRITES };
    /** Sprite Path1. */
    public static final Sprite PATH1 = SpriteImpl.spriteFromSpriteSheet(DIM8, new Vector2Impl<Integer>(0, 7),
            SpriteSheetImpl.TILESET);
    /** Sprite Path2. */
    public static final Sprite PATH2 = SpriteImpl.spriteFromSpriteSheet(DIM8, new Vector2Impl<Integer>(1, 7),
            SpriteSheetImpl.TILESET);
    /** Sprite Path3. */
    public static final Sprite PATH3 = SpriteImpl.spriteFromSpriteSheet(DIM8, new Vector2Impl<Integer>(0, 8),
            SpriteSheetImpl.TILESET);
    /** Sprite Path4. */
    public static final Sprite PATH4 = SpriteImpl.spriteFromSpriteSheet(DIM8, new Vector2Impl<Integer>(1, 8),
            SpriteSheetImpl.TILESET);
    /** Sprite Path vettore. */
    public static final Sprite[] PATH_SPRITES = new Sprite[] { PATH1, PATH2, PATH3, PATH4 };
    /** Sprite path-grass. */
    public static final Sprite[] PATH_GRASS_SPRITES = SpriteSheetImpl.PATH_GRASS.getSprites();
    /** Sprite path-water. */
    public static final Sprite[] PATH_WATER_SPRITES = SpriteSheetImpl.PATH_WATER.getSprites();
    /** Sprite path-sand. */
    public static final Sprite[] PATH_SAND_SPRITES = SpriteSheetImpl.PATH_SAND.getSprites();
    /** Sprite path transitions. */
    public static final Sprite[][] PATH_TRANSITION_SPRITES = new Sprite[][] { PATH_GRASS_SPRITES, PATH_SAND_SPRITES,
            PATH_WATER_SPRITES };
    /** Sprite pathRock1. */
    public static final Sprite PATH_ROCK1 = SpriteImpl.spriteFromSpriteSheet(DIM8, new Vector2Impl<Integer>(4, 9),
            SpriteSheetImpl.TILESET);
    /** Sprite pathRock2. */
    public static final Sprite PATH_ROCK2 = SpriteImpl.spriteFromSpriteSheet(DIM8, new Vector2Impl<Integer>(5, 9),
            SpriteSheetImpl.TILESET);
    /** Sprite pathRock3. */
    public static final Sprite PATH_ROCK3 = SpriteImpl.spriteFromSpriteSheet(DIM8, new Vector2Impl<Integer>(6, 9),
            SpriteSheetImpl.TILESET);
    /** Sprite pathRock4. */
    public static final Sprite PATH_ROCK4 = SpriteImpl.spriteFromSpriteSheet(DIM8, new Vector2Impl<Integer>(7, 9),
            SpriteSheetImpl.TILESET);
    /** Sprite pathRock sprites vettore. */
    public static final Sprite[] PATH_ROCK_SPRITES = new Sprite[] { PATH_ROCK1, PATH_ROCK2, PATH_ROCK3, PATH_ROCK4 };
    /** Sprite pathRock-grass. */
    public static final Sprite[] PATH_ROCK_GRASS_SPRITES = SpriteSheetImpl.PATH_ROCK_GRASS.getSprites();
    /** Sprite pathRock-water. */
    public static final Sprite[] PATH_ROCK_WATER_SPRITES = SpriteSheetImpl.PATH_ROCK_WATER.getSprites();
    /** Sprite pathRock-sand. */
    public static final Sprite[] PATH_ROCK_SAND_SPRITES = SpriteSheetImpl.PATH_ROCK_SAND.getSprites();
    /** Sprite pathRock transition. */
    public static final Sprite[][] PATH_ROCK_TRANSITION_SPRITES = new Sprite[][] { PATH_ROCK_GRASS_SPRITES,
            PATH_ROCK_SAND_SPRITES, PATH_ROCK_WATER_SPRITES };

    /** Sprite plateau1. */
    public static final Sprite PLATEAU1 = SpriteImpl.spriteFromSpriteSheet(DIM8, new Vector2Impl<Integer>(8, 27),
            SpriteSheetImpl.TILESET);
    /** Sprite plateau2. */
    public static final Sprite PLATEAU2 = SpriteImpl.spriteFromSpriteSheet(DIM8, new Vector2Impl<Integer>(9, 27),
            SpriteSheetImpl.TILESET);
    /** Sprite plateau3. */
    public static final Sprite PLATEAU3 = SpriteImpl.spriteFromSpriteSheet(DIM8, new Vector2Impl<Integer>(10, 27),
            SpriteSheetImpl.TILESET);
    /** Sprite plateau4. */
    public static final Sprite PLATEAU4 = SpriteImpl.spriteFromSpriteSheet(DIM8, new Vector2Impl<Integer>(11, 27),
            SpriteSheetImpl.TILESET);
    /** Sprite plateau sprites vettore. */
    public static final Sprite[] PLATEAU_SPRITES = new Sprite[] { PLATEAU1, PLATEAU2, PLATEAU3, PLATEAU4 };
    /** Sprite plateau-grass. */
    public static final Sprite[] PLATEAU_GRASS_SPRITES = SpriteSheetImpl.PLATEAU_GRASS.getSprites();
    /** Sprite plateau-sand. */
    public static final Sprite[] PLATEAU_SAND_SPRITES = SpriteSheetImpl.PLATEAU_SAND.getSprites();
    /** Sprite plateau-water. */
    public static final Sprite[] PLATEAU_WATER_SPRITES = SpriteSheetImpl.PLATEAU_WATER.getSprites();
    /** Sprite plateau transition. */
    public static final Sprite[][] PLATEAU_TRANSITION_SPRITES = new Sprite[][] { PLATEAU_GRASS_SPRITES,
            PLATEAU_SAND_SPRITES, PLATEAU_WATER_SPRITES };

    /** Sprite Cave1. */
    public static final Sprite CAVE1 = SpriteImpl.spriteFromSpriteSheet(DIM8, new Vector2Impl<Integer>(14, 0),
            SpriteSheetImpl.TILESET);
    /** Sprite Cave2. */
    public static final Sprite CAVE2 = SpriteImpl.spriteFromSpriteSheet(DIM8, new Vector2Impl<Integer>(15, 0),
            SpriteSheetImpl.TILESET);
    /** Sprite Cave3. */
    public static final Sprite CAVE3 = SpriteImpl.spriteFromSpriteSheet(DIM8, new Vector2Impl<Integer>(14, 1),
            SpriteSheetImpl.TILESET);
    /** Sprite Cave4. */
    public static final Sprite CAVE4 = SpriteImpl.spriteFromSpriteSheet(DIM8, new Vector2Impl<Integer>(15, 1),
            SpriteSheetImpl.TILESET);
    /** Sprite Cave sprites vettore. */
    public static final Sprite[] CAVE_SPRITES = new Sprite[] { CAVE1, CAVE2, CAVE3, CAVE4 };
    /** Sprite Cave-water. */
    public static final Sprite[] CAVE_WATER_SPRITES = SpriteSheetImpl.CAVE_WATER.getSprites();
    /** Sprite Cave transitions. */
    public static final Sprite[][] CAVE_TRANSITION_SPRITES = new Sprite[][] { CAVE_WATER_SPRITES };

    /** Sprite Wall Oriz. */
    public static final Sprite WALL_HORIZONTAL = SpriteImpl.spriteFromSpriteSheet(DIM8,
            new Vector2Impl<Integer>(14, 22), SpriteSheetImpl.TILESET);
    /** Sprite Wall Vertic. */
    public static final Sprite WALL_VERTICAL = SpriteImpl.spriteFromSpriteSheet(DIM8, new Vector2Impl<Integer>(14, 23),
            SpriteSheetImpl.TILESET);
    /** Sprite Wall UpLeftTop. */
    public static final Sprite WALL_ANGLE_UP_LEFT_TOP = SpriteImpl.spriteFromSpriteSheet(DIM8X16,
            new Vector2Impl<Integer>(12, 10), SpriteSheetImpl.TILESET);
    /** Sprite Wall UpRightTop. */
    public static final Sprite WALL_ANGLE_UP_RIGHT_TOP = SpriteImpl.spriteFromSpriteSheet(DIM8X16,
            new Vector2Impl<Integer>(13, 10), SpriteSheetImpl.TILESET);
    /** Sprite Wall DownLeftTop. */
    public static final Sprite WALL_ANGLE_DOWN_LEFT_TOP = SpriteImpl.spriteFromSpriteSheet(DIM8X16,
            new Vector2Impl<Integer>(12, 11), SpriteSheetImpl.TILESET);
    /** Sprite Wall DownRightTop. */
    public static final Sprite WALL_ANGLE_DOWN_RIGHT_TOP = SpriteImpl.spriteFromSpriteSheet(DIM8X16,
            new Vector2Impl<Integer>(13, 11), SpriteSheetImpl.TILESET);
    /** Sprite Wall UpLeft. */
    public static final Sprite WALL_ANGLE_UP_LEFT = SpriteImpl.spriteFromSpriteSheet(DIM8,
            new Vector2Impl<Integer>(15, 22), SpriteSheetImpl.TILESET);
    /** Sprite UpRight. */
    public static final Sprite WALL_ANGLE_UP_RIGHT = SpriteImpl.spriteFromSpriteSheet(DIM8,
            new Vector2Impl<Integer>(15, 23), SpriteSheetImpl.TILESET);
    /** Sprite Wall DownLeft. */
    public static final Sprite WALL_ANGLE_DOWN_LEFT = SpriteImpl.spriteFromSpriteSheet(DIM8,
            new Vector2Impl<Integer>(15, 21), SpriteSheetImpl.TILESET);
    /** Sprite Wall DownRight. */
    public static final Sprite WALL_ANGLE_DOWN_RIGHT = SpriteImpl.spriteFromSpriteSheet(DIM8,
            new Vector2Impl<Integer>(15, 20), SpriteSheetImpl.TILESET);
    /** Sprite Wall OrizUp. */
    public static final Sprite WALL_HORIZONTAL_UP = SpriteImpl.spriteFromSpriteSheet(DIM8X16,
            new Vector2Impl<Integer>(14, 10), SpriteSheetImpl.TILESET);

    /** Sprite Wall sprites vettore. */
    public static final Sprite[] WALL_SPRITES = new Sprite[] { WALL_HORIZONTAL, WALL_VERTICAL, WALL_ANGLE_UP_RIGHT,
            WALL_ANGLE_UP_LEFT, WALL_ANGLE_DOWN_RIGHT, WALL_ANGLE_DOWN_LEFT };
    /** Sprite Wall top sprites vettore. */
    public static final Sprite[] WALL_TOP_SPRITES = new Sprite[] { WALL_HORIZONTAL_UP, WALL_VERTICAL,
            WALL_ANGLE_UP_RIGHT_TOP, WALL_ANGLE_UP_LEFT_TOP, WALL_ANGLE_DOWN_RIGHT_TOP, WALL_ANGLE_DOWN_LEFT_TOP };

    /** Sprite Red Wall Oriz. */
    public static final Sprite RED_WALL_HORIZONTAL = SpriteImpl.spriteFromSpriteSheet(DIM8,
            new Vector2Impl<Integer>(14, 26), SpriteSheetImpl.TILESET);
    /** Sprite Red Wall Vertic. */
    public static final Sprite RED_WALL_VERTICAL = SpriteImpl.spriteFromSpriteSheet(DIM8,
            new Vector2Impl<Integer>(14, 27), SpriteSheetImpl.TILESET);
    /** Sprite Red Wall UpLeftTop. */
    public static final Sprite RED_WALL_ANGLE_UP_LEFT_TOP = SpriteImpl.spriteFromSpriteSheet(DIM8X16,
            new Vector2Impl<Integer>(12, 12), SpriteSheetImpl.TILESET);
    /** Sprite Red Wall UpRightTop. */
    public static final Sprite RED_WALL_ANGLE_UP_RIGHT_TOP = SpriteImpl.spriteFromSpriteSheet(DIM8X16,
            new Vector2Impl<Integer>(13, 12), SpriteSheetImpl.TILESET);
    /** Sprite Red Wall DownLeftTop. */
    public static final Sprite RED_WALL_ANGLE_DOWN_LEFT_TOP = SpriteImpl.spriteFromSpriteSheet(DIM8X16,
            new Vector2Impl<Integer>(12, 13), SpriteSheetImpl.TILESET);
    /** Sprite Red Wall DownRightTop. */
    public static final Sprite RED_WALL_ANGLE_DOWN_RIGHT_TOP = SpriteImpl.spriteFromSpriteSheet(DIM8X16,
            new Vector2Impl<Integer>(13, 13), SpriteSheetImpl.TILESET);
    /** Sprite Red Wall UpLeft. */
    public static final Sprite RED_WALL_ANGLE_UP_LEFT = SpriteImpl.spriteFromSpriteSheet(DIM8,
            new Vector2Impl<Integer>(15, 26), SpriteSheetImpl.TILESET);
    /** Sprite UpRight. */
    public static final Sprite RED_WALL_ANGLE_UP_RIGHT = SpriteImpl.spriteFromSpriteSheet(DIM8,
            new Vector2Impl<Integer>(15, 27), SpriteSheetImpl.TILESET);
    /** Sprite Red Wall DownLeft. */
    public static final Sprite RED_WALL_ANGLE_DOWN_LEFT = SpriteImpl.spriteFromSpriteSheet(DIM8,
            new Vector2Impl<Integer>(15, 25), SpriteSheetImpl.TILESET);
    /** Sprite Red Wall DownRight. */
    public static final Sprite RED_WALL_ANGLE_DOWN_RIGHT = SpriteImpl.spriteFromSpriteSheet(DIM8,
            new Vector2Impl<Integer>(15, 24), SpriteSheetImpl.TILESET);
    /** Sprite Red Wall OrizUp. */
    public static final Sprite RED_WALL_HORIZONTAL_UP = SpriteImpl.spriteFromSpriteSheet(DIM8X16,
            new Vector2Impl<Integer>(14, 12), SpriteSheetImpl.TILESET);
    /** Sprite Red Wall sprites vettore. */
    public static final Sprite[] RED_WALL_SPRITES = new Sprite[] { RED_WALL_HORIZONTAL, RED_WALL_VERTICAL,
            RED_WALL_ANGLE_UP_RIGHT, RED_WALL_ANGLE_UP_LEFT, RED_WALL_ANGLE_DOWN_RIGHT, RED_WALL_ANGLE_DOWN_LEFT };
    /** Sprite Red Wall top sprites vettore. */
    public static final Sprite[] RED_WALL_TOP_SPRITES = new Sprite[] { RED_WALL_HORIZONTAL_UP, RED_WALL_VERTICAL,
            RED_WALL_ANGLE_UP_RIGHT_TOP, RED_WALL_ANGLE_UP_LEFT_TOP, RED_WALL_ANGLE_DOWN_RIGHT_TOP,
            RED_WALL_ANGLE_DOWN_LEFT_TOP };

    /** Sprite Cave Wall Oriz. */
    public static final Sprite CAVE_WALL_HORIZONTAL = SpriteImpl.spriteFromSpriteSheet(DIM8,
            new Vector2Impl<Integer>(14, 16), SpriteSheetImpl.TILESET);
    /** Sprite Cave Wall Vertic. */
    public static final Sprite CAVE_WALL_VERTICAL = SpriteImpl.spriteFromSpriteSheet(DIM8,
            new Vector2Impl<Integer>(14, 17), SpriteSheetImpl.TILESET);
    /** Sprite Cave Wall UpLeftTop. */
    public static final Sprite CAVE_WALL_ANGLE_UP_LEFT_TOP = SpriteImpl.spriteFromSpriteSheet(DIM8X16,
            new Vector2Impl<Integer>(12, 7), SpriteSheetImpl.TILESET);
    /** Sprite Cave Wall UpRightTop. */
    public static final Sprite CAVE_WALL_ANGLE_UP_RIGHT_TOP = SpriteImpl.spriteFromSpriteSheet(DIM8X16,
            new Vector2Impl<Integer>(13, 7), SpriteSheetImpl.TILESET);
    /** Sprite Cave Wall DownLeftTop. */
    public static final Sprite CAVE_WALL_ANGLE_DOWN_LEFT_TOP = SpriteImpl.spriteFromSpriteSheet(DIM8X16,
            new Vector2Impl<Integer>(12, 8), SpriteSheetImpl.TILESET);
    /** Sprite Cave Wall DownRightTop. */
    public static final Sprite CAVE_WALL_ANGLE_DOWN_RIGHT_TOP = SpriteImpl.spriteFromSpriteSheet(DIM8X16,
            new Vector2Impl<Integer>(13, 8), SpriteSheetImpl.TILESET);
    /** Sprite Cave Wall UpLeft. */
    public static final Sprite CAVE_WALL_ANGLE_UP_LEFT = SpriteImpl.spriteFromSpriteSheet(DIM8,
            new Vector2Impl<Integer>(15, 16), SpriteSheetImpl.TILESET);
    /** Sprite Cave UpRight. */
    public static final Sprite CAVE_WALL_ANGLE_UP_RIGHT = SpriteImpl.spriteFromSpriteSheet(DIM8,
            new Vector2Impl<Integer>(15, 17), SpriteSheetImpl.TILESET);
    /** Sprite Cave Wall DownLeft. */
    public static final Sprite CAVE_WALL_ANGLE_DOWN_LEFT = SpriteImpl.spriteFromSpriteSheet(DIM8,
            new Vector2Impl<Integer>(15, 15), SpriteSheetImpl.TILESET);
    /** Sprite Cave Wall DownRight. */
    public static final Sprite CAVE_WALL_ANGLE_DOWN_RIGHT = SpriteImpl.spriteFromSpriteSheet(DIM8,
            new Vector2Impl<Integer>(15, 14), SpriteSheetImpl.TILESET);
    /** Sprite Cave Wall DownLeft1. */
    public static final Sprite CAVE_WALL_ANGLE_DOWN_LEFT1 = SpriteImpl.spriteFromSpriteSheet(DIM8,
            new Vector2Impl<Integer>(15, 19), SpriteSheetImpl.TILESET);
    /** Sprite Cave Wall DownRight1. */
    public static final Sprite CAVE_WALL_ANGLE_DOWN_RIGHT1 = SpriteImpl.spriteFromSpriteSheet(DIM8,
            new Vector2Impl<Integer>(15, 18), SpriteSheetImpl.TILESET);
    /** Sprite Cave Wall OrizUp. */
    public static final Sprite CAVE_WALL_HORIZONTAL_UP = SpriteImpl.spriteFromSpriteSheet(DIM8X16,
            new Vector2Impl<Integer>(14, 7), SpriteSheetImpl.TILESET);
    /** Sprite Cave Wall Oriz1. */
    public static final Sprite CAVE_WALL_HORIZONTAL1 = SpriteImpl.spriteFromSpriteSheet(DIM8,
            new Vector2Impl<Integer>(14, 18), SpriteSheetImpl.TILESET);
    /** Sprite Cave Wall sprites vettore. */
    public static final Sprite[] CAVE_WALL_SPRITES = new Sprite[] { CAVE_WALL_HORIZONTAL, CAVE_WALL_VERTICAL,
            CAVE_WALL_ANGLE_UP_RIGHT, CAVE_WALL_ANGLE_UP_LEFT, CAVE_WALL_ANGLE_DOWN_RIGHT, CAVE_WALL_ANGLE_DOWN_LEFT,
            CAVE_WALL_ANGLE_DOWN_RIGHT1, CAVE_WALL_ANGLE_DOWN_LEFT1, CAVE_WALL_HORIZONTAL1 };
    /** Sprite Cave Wall top sprites vettore. */
    public static final Sprite[] CAVE_WALL_TOP_SPRITES = new Sprite[] { CAVE_WALL_HORIZONTAL_UP, CAVE_WALL_VERTICAL,
            CAVE_WALL_ANGLE_UP_RIGHT_TOP, CAVE_WALL_ANGLE_UP_LEFT_TOP, CAVE_WALL_ANGLE_DOWN_RIGHT_TOP,
            CAVE_WALL_ANGLE_DOWN_LEFT_TOP };

    /** Sprite House Wall Oriz. */
    public static final Sprite HOUSE_WALL_HORIZONTAL = SpriteImpl.spriteFromSpriteSheet(DIM8,
            new Vector2Impl<Integer>(10, 22), SpriteSheetImpl.TILESET);
    /** Sprite House Wall Vertic. */
    public static final Sprite HOUSE_WALL_VERTICAL = SpriteImpl.spriteFromSpriteSheet(DIM8,
            new Vector2Impl<Integer>(10, 23), SpriteSheetImpl.TILESET);
    /** Sprite House Wall UpLeftTop. */
    public static final Sprite HOUSE_WALL_ANGLE_UP_LEFT_TOP = SpriteImpl.spriteFromSpriteSheet(DIM8X16,
            new Vector2Impl<Integer>(8, 10), SpriteSheetImpl.TILESET);
    /** Sprite House Wall UpRightTop. */
    public static final Sprite HOUSE_WALL_ANGLE_UP_RIGHT_TOP = SpriteImpl.spriteFromSpriteSheet(DIM8X16,
            new Vector2Impl<Integer>(9, 10), SpriteSheetImpl.TILESET);
    /** Sprite House Wall DownLeftTop. */
    public static final Sprite HOUSE_WALL_ANGLE_DOWN_LEFT_TOP = SpriteImpl.spriteFromSpriteSheet(DIM8X16,
            new Vector2Impl<Integer>(8, 11), SpriteSheetImpl.TILESET);
    /** Sprite House Wall DownRightTop. */
    public static final Sprite HOUSE_WALL_ANGLE_DOWN_RIGHT_TOP = SpriteImpl.spriteFromSpriteSheet(DIM8X16,
            new Vector2Impl<Integer>(9, 11), SpriteSheetImpl.TILESET);
    /** Sprite House Wall UpLeft. */
    public static final Sprite HOUSE_WALL_ANGLE_UP_LEFT = SpriteImpl.spriteFromSpriteSheet(DIM8,
            new Vector2Impl<Integer>(11, 22), SpriteSheetImpl.TILESET);
    /** Sprite House UpRight. */
    public static final Sprite HOUSE_WALL_ANGLE_UP_RIGHT = SpriteImpl.spriteFromSpriteSheet(DIM8,
            new Vector2Impl<Integer>(11, 23), SpriteSheetImpl.TILESET);
    /** Sprite House Wall DownLeft. */
    public static final Sprite HOUSE_WALL_ANGLE_DOWN_LEFT = SpriteImpl.spriteFromSpriteSheet(DIM8,
            new Vector2Impl<Integer>(11, 21), SpriteSheetImpl.TILESET);
    /** Sprite House Wall DownRight. */
    public static final Sprite HOUSE_WALL_ANGLE_DOWN_RIGHT = SpriteImpl.spriteFromSpriteSheet(DIM8,
            new Vector2Impl<Integer>(11, 20), SpriteSheetImpl.TILESET);
    /** Sprite House Wall DownLeft1. */
    public static final Sprite HOUSE_WALL_ANGLE_DOWN_LEFT1 = SpriteImpl.spriteFromSpriteSheet(DIM8,
            new Vector2Impl<Integer>(11, 25), SpriteSheetImpl.TILESET);
    /** Sprite House Wall DownRight1. */
    public static final Sprite HOUSE_WALL_ANGLE_DOWN_RIGHT1 = SpriteImpl.spriteFromSpriteSheet(DIM8,
            new Vector2Impl<Integer>(11, 24), SpriteSheetImpl.TILESET);
    /** Sprite House Wall OrizUp. */
    public static final Sprite HOUSE_WALL_HORIZONTAL_UP = SpriteImpl.spriteFromSpriteSheet(DIM8X16,
            new Vector2Impl<Integer>(10, 10), SpriteSheetImpl.TILESET);
    /** Sprite House Wall Oriz1. */
    public static final Sprite HOUSE_WALL_HORIZONTAL1 = SpriteImpl.spriteFromSpriteSheet(DIM8,
            new Vector2Impl<Integer>(10, 24), SpriteSheetImpl.TILESET);
    /** Sprite House Wall sprites vettore. */
    public static final Sprite[] HOUSE_WALL_SPRITES = new Sprite[] { HOUSE_WALL_HORIZONTAL, HOUSE_WALL_VERTICAL,
            HOUSE_WALL_ANGLE_UP_RIGHT, HOUSE_WALL_ANGLE_UP_LEFT, HOUSE_WALL_ANGLE_DOWN_RIGHT,
            HOUSE_WALL_ANGLE_DOWN_LEFT, HOUSE_WALL_ANGLE_DOWN_RIGHT1, HOUSE_WALL_ANGLE_DOWN_LEFT1,
            HOUSE_WALL_HORIZONTAL1 };
    /** Sprite House Wall top sprites vettore. */
    public static final Sprite[] HOUSE_WALL_TOP_SPRITES = new Sprite[] { HOUSE_WALL_HORIZONTAL_UP, HOUSE_WALL_VERTICAL,
            HOUSE_WALL_ANGLE_UP_RIGHT_TOP, HOUSE_WALL_ANGLE_UP_LEFT_TOP, HOUSE_WALL_ANGLE_DOWN_RIGHT_TOP,
            HOUSE_WALL_ANGLE_DOWN_LEFT_TOP };

    /** Sprite Ponte Verticale. */
    public static final Sprite VERTICAL_BRIDGE = SpriteImpl.spriteFromSpriteSheet(DIM8,
            new Vector2Impl<Integer>(10, 11), SpriteSheetImpl.TILESET);

    /** Sprite Ponte Orizzontale. */
    public static final Sprite HORIZONTAL_BRIDGE = SpriteImpl.spriteFromSpriteSheet(DIM8,
            new Vector2Impl<Integer>(0, 24), SpriteSheetImpl.TILESET);
    /** Sprite Vettore Ponte. */
    public static final Sprite[] VERTICAL_BRIDGE_SPRITES = SpriteSheetImpl.VERTICAL_BRIDGE.getSprites();

    /** Sprite Vettore Ponte. */
    public static final Sprite[] HORIZONTAL_BRIDGE_SPRITES = SpriteSheetImpl.HORIZONTAL_BRIDGE.getSprites();
    /** Sprite Floor(pavimento). */
    public static final Sprite FLOOR = SpriteImpl.spriteFromSpriteSheet(DIM8, new Vector2Impl<Integer>(8, 2),
            SpriteSheetImpl.TILESET);
    /** Sprite Vettore di Floor(pavimento). */
    public static final Sprite[] FLOOR_SPRITES = SpriteSheetImpl.FLOOR.getSprites();

    /** Sprite House. */
    public static final Sprite HOUSE = SpriteImpl.spriteFromSpriteSheet(new Dimension(64, 68),
            new Vector2Impl<Integer>(0, 3), SpriteSheetImpl.TILESET);

    /** Sprites bastone su grass. */
    public static final Sprite SHAFT = SpriteImpl.spriteFromSpriteSheet(DIM8, new Vector2Impl<Integer>(0, 6),
            SpriteSheetImpl.TILESET);
    /** Sprite bastone su sand. */
    public static final Sprite SHAFTSAND = SpriteImpl.spriteFromSpriteSheet(DIM8, new Vector2Impl<Integer>(1, 6),
            SpriteSheetImpl.TILESET);

    /** Sprite scala a pioli. */
    public static final Sprite LADDER = SpriteImpl.spriteFromSpriteSheet(new Dimension(16, 24),
            new Vector2Impl<Integer>(7, 2), SpriteSheetImpl.TILESET);

    /** Sprite pavimento legno1. */
    public static final Sprite WOOD_FLOOR1 = SpriteImpl.spriteFromSpriteSheet(DIM8, new Vector2Impl<Integer>(4, 10),
            SpriteSheetImpl.TILESET);
    /** Sprite pavimento legno2. */
    public static final Sprite WOOD_FLOOR2 = SpriteImpl.spriteFromSpriteSheet(DIM8, new Vector2Impl<Integer>(5, 10),
            SpriteSheetImpl.TILESET);
    /** Sprite pavimento legno3. */
    public static final Sprite WOOD_FLOOR3 = SpriteImpl.spriteFromSpriteSheet(DIM8, new Vector2Impl<Integer>(4, 11),
            SpriteSheetImpl.TILESET);
    /** Sprite pavimento legno4. */
    public static final Sprite WOOD_FLOOR4 = SpriteImpl.spriteFromSpriteSheet(DIM8, new Vector2Impl<Integer>(5, 11),
            SpriteSheetImpl.TILESET);
    /** Sprite pavimento legno Vettore. */
    public static final Sprite[] WOOD_FLOOR_SPRITES = new Sprite[] { WOOD_FLOOR1, WOOD_FLOOR2, WOOD_FLOOR3,
            WOOD_FLOOR4 };

    /** Sprite pavimento mattone1. */
    public static final Sprite BRICK1 = SpriteImpl.spriteFromSpriteSheet(DIM8, new Vector2Impl<Integer>(12, 18),
            SpriteSheetImpl.TILESET);
    /** Sprite pavimento mattone2. */
    public static final Sprite BRICK2 = SpriteImpl.spriteFromSpriteSheet(DIM8, new Vector2Impl<Integer>(13, 18),
            SpriteSheetImpl.TILESET);
    /** Sprite pavimento mattone3. */
    public static final Sprite BRICK3 = SpriteImpl.spriteFromSpriteSheet(DIM8, new Vector2Impl<Integer>(12, 19),
            SpriteSheetImpl.TILESET);
    /** Sprite pavimento mattone4. */
    public static final Sprite BRICK4 = SpriteImpl.spriteFromSpriteSheet(DIM8, new Vector2Impl<Integer>(13, 19),
            SpriteSheetImpl.TILESET);
    /** Sprite pavimento mattone Vettore. */
    public static final Sprite[] BRICK_SPRITES = new Sprite[] { BRICK1, BRICK2, BRICK3, BRICK4 };

    /** Sprite pavimento mattone1. */
    public static final Sprite OLD_BRICK1 = SpriteImpl.spriteFromSpriteSheet(DIM8, new Vector2Impl<Integer>(14, 4),
            SpriteSheetImpl.TILESET);
    /** Sprite pavimento mattone2. */
    public static final Sprite OLD_BRICK2 = SpriteImpl.spriteFromSpriteSheet(DIM8, new Vector2Impl<Integer>(15, 4),
            SpriteSheetImpl.TILESET);
    /** Sprite pavimento mattone3. */
    public static final Sprite OLD_BRICK3 = SpriteImpl.spriteFromSpriteSheet(DIM8, new Vector2Impl<Integer>(14, 4),
            SpriteSheetImpl.TILESET);
    /** Sprite pavimento mattone4. */
    public static final Sprite OLD_BRICK4 = SpriteImpl.spriteFromSpriteSheet(DIM8, new Vector2Impl<Integer>(15, 4),
            SpriteSheetImpl.TILESET);
    /** Sprite pavimento mattone Vettore. */
    public static final Sprite[] OLD_BRICK_SPRITES = new Sprite[] { OLD_BRICK1, OLD_BRICK2, OLD_BRICK3, OLD_BRICK4 };

    /** Sprite del letto. */
    public static final Sprite BED = SpriteImpl.spriteFromSpriteSheet(DIM16X24, new Vector2Impl<Integer>(4, 3),
            SpriteSheetImpl.TILESET);
    /** Sprite del pozzo. */
    public static final Sprite DESK = SpriteImpl.spriteFromSpriteSheet(DIM16, new Vector2Impl<Integer>(4, 10),
            SpriteSheetImpl.TILESET);
    /** Sprite del pozzo. */
    public static final Sprite BEDTABLE = SpriteImpl.spriteFromSpriteSheet(DIM16, new Vector2Impl<Integer>(5, 10),
            SpriteSheetImpl.TILESET);

    /** Sprite del pozzo. */
    public static final Sprite WELL = SpriteImpl.spriteFromSpriteSheet(DIM16, new Vector2Impl<Integer>(3, 5),
            SpriteSheetImpl.TILESET);
    /** Sprites ambientale rock. */
    public static final Sprite ROCK = SpriteImpl.spriteFromSpriteSheet(DIM8, new Vector2Impl<Integer>(4, 0),
            SpriteSheetImpl.TILESET);
    /** Sprites ambientale flower. */
    public static final Sprite FLOWER = SpriteImpl.spriteFromSpriteSheet(DIM8, new Vector2Impl<Integer>(5, 0),
            SpriteSheetImpl.TILESET);
    /** Sprites ambientale flower2. */
    public static final Sprite FLOWER2 = SpriteImpl.spriteFromSpriteSheet(DIM8, new Vector2Impl<Integer>(4, 2),
            SpriteSheetImpl.TILESET);
    /** Sprites ambientale flower3. */
    public static final Sprite FLOWER3 = SpriteImpl.spriteFromSpriteSheet(DIM8, new Vector2Impl<Integer>(5, 2),
            SpriteSheetImpl.TILESET);
    /** Sprites ambientale treetrunk. */
    public static final Sprite TREETRUNK = SpriteImpl.spriteFromSpriteSheet(DIM8, new Vector2Impl<Integer>(6, 2),
            SpriteSheetImpl.TILESET);
    /** Sprites ambientale treetop chioma albero. */
    public static final Sprite TREETOP = SpriteImpl.spriteFromSpriteSheet(DIM16, new Vector2Impl<Integer>(3, 0),
            SpriteSheetImpl.TILESET);
    /** Sprites ambientale treep chioma albero di tipo 2. */
    public static final Sprite TREETOP2 = SpriteImpl.spriteFromSpriteSheet(DIM16, new Vector2Impl<Integer>(4, 0),
            SpriteSheetImpl.TILESET);
    /** Sprites ambientale treep chioma albero di tipo 3. */
    public static final Sprite TREETOP3 = SpriteImpl.spriteFromSpriteSheet(new Dimension(16, 24),
            new Vector2Impl<Integer>(7, 3), SpriteSheetImpl.TILESET);
    /** Sprites ambientale treep chioma albero di tipo 3. */
    public static final Sprite TREETOP4 = SpriteImpl.spriteFromSpriteSheet(new Dimension(16, 24),
            new Vector2Impl<Integer>(4, 8), SpriteSheetImpl.TILESET);

    /** Sprite del palo di Torch. */
    public static final Sprite TORCH = SpriteImpl.spriteFromSpriteSheet(DIM8, new Vector2Impl<Integer>(7, 2),
            SpriteSheetImpl.TILESET);
    /** Sprite del palo di BlueTorch. */
    public static final Sprite BLUETORCH = SpriteImpl.spriteFromSpriteSheet(DIM8, new Vector2Impl<Integer>(9, 2),
            SpriteSheetImpl.TILESET);
    /** Sprite animata di Torchflame. */
    public static final AnimatedSprite TORCHFLAME = new AnimatedSprite(DIM8, 2, 15, SpriteSheetImpl.TORCHFLAME);
    /** Sprite animata di BlueTorchflame. */
    public static final AnimatedSprite BLUETORCHFLAME = new AnimatedSprite(DIM8, 2, 15, SpriteSheetImpl.BLUETORCHFLAME);

    /** Sprites del normal proiettile nell'Hud. */
    public static final Sprite PROJECTILE_HUD = SpriteImpl.spriteFromSpriteSheet(DIM8, new Vector2Impl<Integer>(0, 10),
            SpriteSheetImpl.TILESET);
    /** Sprites del super-proiettile nell'Hud. */
    public static final Sprite SUPERPROJECTILE_HUD = SpriteImpl.spriteFromSpriteSheet(DIM8,
            new Vector2Impl<Integer>(1, 10), SpriteSheetImpl.TILESET);

    /** Sprites del red super-proiettile nell'Hud. */
    public static final Sprite REDSUPERPROJECTILE_HUD = SpriteImpl.spriteFromSpriteSheet(DIM8,
            new Vector2Impl<Integer>(2, 10), SpriteSheetImpl.TILESET);

    /** Sprite proiettile normale. */
    public static final Sprite PROJECTILE = SpriteImpl.spriteFromSpriteSheet(DIM16, new Vector2Impl<Integer>(0, 0),
            SpriteSheetImpl.PROJECTILES);
    /** Sprite animata super-proiettile. */
    public static final AnimatedSprite SUPERPROJECTILE = new AnimatedSprite(DIM16, 2, 15,
            SpriteSheetImpl.SUPERPROJECTILE);
    /** Sprite animata super-proiettile rosso. */
    public static final AnimatedSprite REDSUPERPROJECTILE = new AnimatedSprite(DIM16, 2, 15,
            SpriteSheetImpl.REDSUPERPROJECTILE);

    /** Sprite del particle grigio. */
    public static final Sprite PARTICLE = SpriteImpl.spriteFromColor(PARTICLEDIM, PARTCOLOR);
    /** Sprite del particle azzurro. */
    public static final Sprite SUPERPARTICLE = SpriteImpl.spriteFromColor(PARTICLEDIM, SUPERPARTCOLOR);
    /** Sprite del particle rosso. */
    public static final Sprite REDSUPERPARTICLE = SpriteImpl.spriteFromColor(PARTICLEDIM, REDSUPERPARTCOLOR);

    /** Sprite dell'elmo. */
    public static final Sprite HELM = SpriteImpl.spriteFromSpriteSheet(DIM16X8, new Vector2Impl<Integer>(5, 0),
            SpriteSheetImpl.TILESET);
    /** Sprites della chiave. */
    public static final Sprite KEY = SpriteImpl.spriteFromSpriteSheet(DIM16X8, new Vector2Impl<Integer>(5, 1),
            SpriteSheetImpl.TILESET);

    /** Sprite dei cuori (salute del player) da renderizzare nell'Hud. */
    public static final AnimatedSprite HEART = new AnimatedSprite(DIM16X10, 2, 15, SpriteSheetImpl.HEARTBIT);
    /** Sprite di pozione. */
    public static final Sprite POTION = SpriteImpl.spriteFromSpriteSheet(DIM8, new Vector2Impl<Integer>(4, 1),
            SpriteSheetImpl.TILESET);
    /** Sprite di pozione grande. */
    public static final Sprite BIGPOTION = SpriteImpl.spriteFromSpriteSheet(DIM8, new Vector2Impl<Integer>(5, 1),
            SpriteSheetImpl.TILESET);

    /**
     * Sprite animato della cornice "di sangue" dopo che il player ha subito
     * gravi ferite.
     */

    public static final AnimatedSprite BLOOD = new AnimatedSprite(DIM300X168, 2, 15, SpriteSheetImpl.BLOOD);

    /**
     * Sprite animato della pioggia.
     * 
     */
    public static final AnimatedSprite RAIN = new AnimatedSprite(DIM300X168, 16, 5, SpriteSheetImpl.RAIN);

    /** Sprite animato dello scorrere dell'acqua. */
    public static final AnimatedSprite WATER_ANIMATION = new AnimatedSprite(DIM8, 4, 50, SpriteSheetImpl.WATER);

    /** Sprite animato della cascata. */
    public static final AnimatedSprite WATERFALL_ANIMATION = new AnimatedSprite(DIM8, 6, 8, SpriteSheetImpl.WATERFALL);

    /** Sprite animato dello scorrere dell'acqua scura. */
    public static final AnimatedSprite DARK_WATER_ANIMATION = new AnimatedSprite(DIM8, 4, 50,
            SpriteSheetImpl.DARK_WATER);

    /**
     * Costruttore per sprite che prende come param la dimensione.
     * 
     * @param dimension
     *            dimensione dello sprite.
     */
    public SpriteImpl(final Dimension dimension) {
        this.dimension = new Dimension(dimension);
    }

    /**
     * Costruttore per sprite da uno SpriteSheet.
     * 
     * @param dimension
     *            dimensione sprite.
     * @param position
     *            posizione nello sheet.
     * @param sheet
     *            sheet da cui prenderlo.
     * @return la Sprite
     */
    public static Sprite spriteFromSpriteSheet(final Dimension dimension, final Vector2<Integer> position,
            final SpriteSheet sheet) {
        if (dimension.getWidth() > sheet.getDimension().getWidth()
                || dimension.getHeight() > sheet.getDimension().getHeight()) {
            throw new IllegalArgumentException(
                    "Il nuovo sprite ha dimensione maggiore dello spritesheet da cui viene creato.");
        }
        final SpriteImpl s = new SpriteImpl(dimension);
        s.pixels = new int[(int) (dimension.getWidth() * dimension.getHeight())];
        s.sheet = sheet;
        s.loadFromSheet(new Vector2Impl<Integer>((int) (position.getX() * dimension.getWidth()),
                (int) (position.getY() * dimension.getHeight())));
        return s;
    }

    /**
     * Costruttore per sprite da un colore.
     * 
     * @param dimension
     *            dimensione sprite.
     * @param color
     *            colore.
     * @return la Sprite
     */
    public static Sprite spriteFromColor(final Dimension dimension, final int color) {

        final SpriteImpl s = new SpriteImpl(dimension);
        s.pixels = new int[(int) (dimension.getWidth() * dimension.getHeight())];
        for (int i = 0; i < dimension.getWidth() * dimension.getHeight(); i++) {
            s.pixels[i] = color;
        }
        return s;
    }

    /**
     * Costruttore per sprite da pixels.
     * 
     * @param dimension
     *            dimensione.
     * @param pixels
     *            vettore di pixels.
     * @return la Sprite
     */
    public static SpriteImpl spriteFromPixels(final Dimension dimension, final int[] pixels) {
        final SpriteImpl s = new SpriteImpl(dimension);
        s.pixels = pixels.clone();
        return s;
    }

    /**
     * Costruttore per sprite da un altro sprite.
     * 
     * @param dimension
     *            dimensione sprite.
     * @param position
     *            posizione da cui "ritagliarlo".
     * @param sprite
     *            sprite da cui "ritagliarlo".
     * @return la Sprite.
     */
    public static Sprite spriteFromSprite(final Dimension dimension, final Vector2<Integer> position,
            final Sprite sprite) {
        if (dimension.getWidth() > sprite.getDimension().getWidth()
                || dimension.getHeight() > sprite.getDimension().getHeight()) {
            throw new IllegalArgumentException(
                    "Il nuovo sprite ha dimensione maggiore dello sprite da cui viene creato.");
        }

        final SpriteImpl s = new SpriteImpl(dimension);
        s.pixels = new int[(int) (dimension.getWidth() * dimension.getHeight())];
        s.loadFromSprite(new Vector2Impl<Integer>(position.getX() * (int) dimension.getWidth(),
                position.getY() * (int) dimension.getHeight()), sprite);
        return s;
    }

    private void loadFromSheet(final Vector2<Integer> position) {
        for (int y = 0; y < dimension.getHeight(); y++) {
            for (int x = 0; x < dimension.getWidth(); x++) {
                pixels[x + y * (int) dimension.getWidth()] = sheet.getPixels()[(x + position.getX())
                        + (y + position.getY()) * (int) sheet.getDimension().getWidth()];
            }
        }
    }

    private void loadFromSprite(final Vector2<Integer> position, final Sprite sprite) {
        for (int y = 0; y < dimension.getHeight(); y++) {
            for (int x = 0; x < dimension.getWidth(); x++) {
                pixels[x + y * (int) dimension.getWidth()] = sprite.getPixels()[(x + position.getX())
                        + (y + position.getY()) * (int) dimension.getWidth()];
            }
        }
    }

    @Override
    public int[] getPixels() { // Non ritorno un nuovo array perchè l'eccessiva
                               // dimensione causa errore.
        return pixels;
    }

    @Override
    public void setPixels(final int... pixels) {
        this.pixels = pixels;
    }

    @Override
    public Dimension getDimension() {
        return new Dimension(dimension);
    }

    @Override
    public SpriteSheet getSheet() {
        return sheet;
    }

    @Override
    public void setSheet(final SpriteSheet sheet) {
        this.sheet = sheet;
    }

}