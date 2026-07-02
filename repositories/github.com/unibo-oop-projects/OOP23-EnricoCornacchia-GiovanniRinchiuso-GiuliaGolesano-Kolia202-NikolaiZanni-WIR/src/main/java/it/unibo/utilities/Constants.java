package it.unibo.utilities;

import it.unibo.common.Pair;

/**
 * Class for the constaints used.
 */
public final class Constants {
    /**
     * The name of the gamestate.
     */
    public static final String GAMESTATE = "GameState: ";

    /**
     * Private constructor.
     */
    private Constants() {
    }

    /**
     * Constaints of the edges positions.
     */
    public static class GameEdges {
        /**
         * The position of the left wall.
         */
        public static final double LEFT_WALL = 270.0;
        /**
         * The position of the right wall.
         */
        public static final double RIGHT_WALL = 540.0;
        /**
         * The position of the down wall.
         */
        public static final double UP_WALL = 0.0;
        /**
         * The position of the up wall.
         */
        public static final double DOWN_WALL_1 = 600.0;
    }

    /**
     * Felix constaints.
     */
    public static class Felix {
        /**
         * The position of starting Felix.
         */
        public static final Pair<Double, Double> FELIX_START = new Pair<>(400.0, 560.0);
        /**
         * The width of Felix.
         */
        public static final double FELIX_WIDTH = 30.0;
        /**
         * The height of Felix.
         */
        public static final double FELIX_HEIGHT = 40.0;
        /**
         * The points obtained by fixing a window.
         */
        public static final int FIXED_WINDOW_POINTS = 50;
    }

    /**
     * Ralph constaints.
     */
    public static class Ralph {
        /**
         * The position of starting Ralph of the first level.
         */
        public static final Pair<Double, Double> RALPH_START = new Pair<>(370.0, 210.0);
        /**
         * The difference between the position of Ralph and the position of his right
         * hand.
         */
        public static final Pair<Double, Double> RALPH_RIGHT_HAND = new Pair<>(70.0, 35.0);
        /**
         * The difference between the position of Ralph and the position of his left
         * hand.
         */
        public static final Pair<Double, Double> RALPH_LEFT_HAND = new Pair<>(0.0, 35.0);
        /**
         * The Ralph height.
         */
        public static final double RALPH_HEIGHT = 80;
        /**
         * The Ralph width.
         */
        public static final double RALPH_WIDTH = 80;
        /**
         * The Ralph speed in throwing bricks.
         */
        public static final long THROW_TIME = 4_000;
        /**
         * The divider for the level.
         */
        public static final double LEVEL_DIVIDER = 1.5;
    }

    /**
     * Brick constaints.
     */
    public static class Brick {
        /**
         * The width of the brick.
         */
        public static final double BRICK_WIDTH = 18;
        /**
         * The height of the brick.
         */
        public static final double BRICK_HEIGHT = 20;
        /**
         * The speed of the brick at level 1.
         */
        public static final double BRICK_SPEED_LEVEL_1 = 1;
        /**
         * The speed of the brick at level 2.
         */
        public static final double BRICK_SPEED_LEVEL_2 = 1.5;
        /**
         * The speed of the brick at level 3.
         */
        public static final double BRICK_SPEED_LEVEL_3 = 1.8;
    }

    /**
     * Window constaints.
     */
    public static class Window {
        /**
         * The width of the window.
         */
        public static final double WINDOW_WIDTH = 39;
        /**
         * The height of the window.
         */
        public static final double WINDOW_HEIGHT = 60;
    }

    /**
     * Cake constaints.
     */
    public static class Cake {
        /**
         * The width of the cake.
         */
        public static final double CAKE_WIDTH = 20;
        /**
         * The height of the cake.
         */
        public static final double CAKE_HEIGHT = 20;
        /**
         * The time of creation easy level.
         */
        public static final long CREATION_INTERVA_1_C = 11_000;
         /**
         * The time of creation medium level.
         */
        public static final long CREATION_INTERVA_2_C = 13_500;
         /**
         * The time of creation hard level.
         */
        public static final long CREATION_INTERVA_3_C = 16_000;
        /**
         * The offset x of the cake.
         */
        public static final double OFFSET_X = 10;
        /**
         * The offset y of the cake.
         */
        public static final double OFFSET_Y = 35;
    }

    /**
     * Bird constaints.
     */
    public static class Bird {
        /**
         * The width of the bird.
         */
        public static final double BIRD_WIDTH = 25;
        /**
         * The height of the bird.
         */
        public static final double BIRD_HEIGHT = 25;
        /**
         * The time of creation easy level.
         */
        public static final long CREATION_INTERVA_1_B = 10_000;
         /**
         * The time of creation medium level.
         */
        public static final long CREATION_INTERVA_2_B = 12_500;
         /**
         * The time of creation hard level.
         */
        public static final long CREATION_INTERVA_3_B = 15_000;
         /**
         * The position of the first floor Bird.
         */
        public static final double FLOOR_1_Y_B = 347;
        /**
         * The position of the second floor Bird.
         */
        public static final double FLOOR_2_Y_B = 426;
        /**
         * The position of the third floor Bird.
         */
        public static final double FLOOR_3_Y_B = 505;

    }

    /**
     * Buttons constaints.
     */
    public static class Button {
        /**
         * The position of the house button.
         */
        public static final Pair<Double, Double> HOME_BUTTON = new Pair<>(null, null);
        /**
         * The position of the Pause button.
         */
        public static final Pair<Double, Double> PAUSE_BUTTON = new Pair<>(null, null);
        /**
         * The position of the house button.
         */
        public static final Pair<Double, Double> QUIT_BUTTON = new Pair<>(null, null);
        /**
         * The position of the Pause button.
         */
        public static final Pair<Double, Double> CONTINUE_BUTTON = new Pair<>(null, null);
        /**
         * The height of the top image.
         */
        public static final double TOP_IMAGE_HEIGHT = 75;
        /**
         * The width of the top image.
         */
        public static final double TOP_IMAGE_WIDTH = 300;
        /**
         * The height of the pause button.
         */
        public static final double WIDTH_PAUSE_BUTTON = 40;
        /**
         * The width of the pause button.
         */
        public static final double HEIGHT_PAUSE_BUTTON = 40;
        /**
         * The hight of the under image.
         */
        public static final double UNDER_IMAGE_HEIGHT = 200;
        /**
         * The width of the under image.
         */
        public static final double UNDER_IMAGE_WIDTH = 450;
    }

    /**
     * Power ups constains.
     */
    public static class PowerUps {
        /**
         * Starting x of the bird.
         */
        public static final double BIRD_MAX_X = 800.0;
        /**
         * Strating x of the bird.
         */
        public static final double BIRD_MIN_X = 0.0;
        /**
         * Minimum y of the bird.
         */
        public static final double BIRD_MIN_Y = 200.0;
        /**
         * Maximum y of the bird.
         */
        public static final double BIRD_MAX_Y = 600.0;
        /**
         * Maximum x of the cake.
         */
        public static final double CAKE_MAX_X = 500.0;
        /**
         * Minimum y of the cake.
         */
        public static final double CAKE_MIN_X = 300.0;
        /**
         * The initial delay of the power ups.
         */
        public static final long INITIAL_DELAY = 5;
        /**
         * The period of the power ups.
         */
        public static final long PERIOD = 10;
    }

    /**
     * Floors constains.
     */
    public static class Floors {
        /**
         * The position of the first floor.
         */
        public static final double FLOOR_1_Y = 390.0;
        /**
         * The position of the second floor.
         */
        public static final double FLOOR_2_Y = 400.0;
        /**
         * The position of the third floor.
         */
        public static final double FLOOR_3_Y = 600.0;
    }

    /**
     * Windows constains.
     */
    public static class Windows {
        /**
         * Number of broken windows in the first level.
         */
        public static final int BROKEN_1 = 5;
        /**
         * Number of broken windows in the second level.
         */
        public static final int BROKEN_2 = 10;
        /**
         * Number of broken windows in the third level.
         */
        public static final int BROKEN_3 = 12;
        /**
         * Number of total windows.
         */
        public static final int NUM_WINDOWS = 15;
    }

    /**
     * Animations constaints.
     */
    public static class Animations {
        /**
         * Number of frames for Felix animation.
         */
        public static final int NUM_FRAMES_FELIX = 0;
        /**
         * Number of frames for Ralph animation.
         */
        public static final int NUM_FRAMES_RALPH = 0;
        /**
         * Number of frames for Brick fall animation.
         */
        public static final int NUM_FRAMES_BRICK = 0;
        /**
         * Number of frames for Cake spawn animation.
         */
        public static final int NUM_FRAMES_CAKE = 0;
        /**
         * Number of frames for Window fix animation.
         */
        public static final int NUM_FRAMES_WINDOW = 0;
        /**
         * Number of frames for Bird fly animation.
         */
        public static final int NUM_FRAMES_BIRD = 2;
    }
}
