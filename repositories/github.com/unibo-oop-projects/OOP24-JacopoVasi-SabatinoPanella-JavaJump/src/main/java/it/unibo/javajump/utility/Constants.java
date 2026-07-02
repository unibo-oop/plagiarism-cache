package it.unibo.javajump.utility;


/**
 * The class Constants, which contains all the constants used in the game.
 */
public final class Constants {

    //Game Model -----------------------------------------------------------------------------
    /**
     * The constant SCREEN_WIDTH: specifies the width of the screen.
     */
    public static final int SCREEN_WIDTH = 600;

    /**
     * The constant SCREEN_HEIGHT: specifies the height of the screen.
     */
    public static final int SCREEN_HEIGHT = 800;

    /**
     * The constant MIN_SPACING: specifies the minimum spacing between platforms.
     */
    public static final int MIN_SPACING = 50;

    /**
     * The constant MAX_SPACING: specifies the maximum spacing between platforms.
     */
    public static final int MAX_SPACING = 150;

    /**
     * The constant COIN_CHANCE: specifies the chance of a coin being spawned.
     */
    public static final float COIN_CHANCE = 0.3f;

    /**
     * The constant SCORE_FACTOR: specifies the score factor of the camera scrolling.
     */
    public static final float SCORE_FACTOR = 0.5f;

    /**
     * The constant GRAVITY: specifies the gravity applied to the player.
     */
    public static final float GRAVITY = 1350;

    /**
     * The constant ACCELERATION: specifies the acceleration applied to the player.
     */
    public static final float ACCELERATION = 3000;

    /**
     * The constant MAX_SPEED: specifies the maximum speed of the player.
     */
    public static final float MAX_SPEED = 400;

    /**
     * The constant DECELERATION: specifies the deceleration applied to the player.
     */
    public static final float DECELERATION = 15_000;


    //Controller ----------------------------------------------------------------------------

    /**
     * The constant GAME_TITLE: specifies the title of the game.
     */
    public static final String GAME_TITLE = "JAVA JUMP";

    /**
     * The constant FPS: specifies the frames per second.
     */
    public static final double FPS = 60;

    /**
     * The constant NANOSECONDS_PER_SECOND: specifies the number of nanoseconds in a second.
     */
    public static final double NANOSECONDS_PER_SECOND = 1_000_000_000;

    /**
     * The constant NULL_DIRECTION.
     */
    public static final int NULL_DIRECTION = 0;

    /**
     * The constant LEFT_DIRECTION.
     */
    public static final int LEFT_DIRECTION = -1;

    /**
     * The constant RIGHT_DIRECTION.
     */
    public static final int RIGHT_DIRECTION = 1;

    /**
     * The constant SLEEP_THREAD.
     */
    public static final int SLEEP_THREAD = 1;


//Camera Manager --------------------------------------------------------------------------

    /**
     * The constant OFFSET_INIT: describes the initial camera offset.
     */
    public static final int OFFSET_INIT = 0;

    /**
     * The constant HEIGHT_DIV: describes the height division used to define the desired scroll area.
     */
    public static final float HEIGHT_DIV = 2;

    /**
     * The constant WIDTH_DIV: describes the width division used to define the desired scroll area.
     */
    public static final float WIDTH_DIV = 0.05f;


//Collision Manager --------------------------------------------------------------------------

    /**
     * The constant COIN_SCORE_VALUE: specifies the score value when a coin is collected.
     */
    public static final int COIN_SCORE_VALUE = 50;


//Character ------------------------------------------------------------------------------

    /**
     * The constant VELOCITY_INIT: specifies the initial velocity.
     */
    public static final int VELOCITY_INIT = 0;


//PhysicsManager --------------------------------------------------------------------------

    /**
     * The constant NULL_VELOCITY: specifies the null velocity.
     */
    public static final int NULL_VELOCITY = 0;


//Moving Platform --------------------------------------------------------------------------

    /**
     * The constant NULL_PLATFORM_VELOCITY specifies the null platform velocity.
     */
    public static final int NULL_PLATFORM_VELOCITY = 0;


//Platform ---------------------------------------------------------------------------------

    /**
     * The constant TOP_PLATFORM_Y_INIT: specifies the initial y position of the top platform.
     */
    public static final int TOP_PLATFORM_Y_INIT = 0;


//Strategy ---------------------------------------------------------------------------------

    /**
     * The constant SPAWN_Y_INIT: specifies the initial y position of the platforms spawn point.
     */
    public static final int SPAWN_Y_INIT = 0;


//Generic Numbers --------------------------------------------------------------------------

    /**
     * The constant FLIP_MAX: specifies the maximum flip value.
     */
    public static final int FLIP_MAX = 1;

    /**
     * The constant FLIP_MIN: specifies the minimum flip value.
     */
    public static final int FLIP_MIN = -1;

    /**
     * The constant SCREEN_LEFT_MARGIN: specifies the left margin of the screen.
     */
    public static final int SCREEN_LEFT_MARGIN = 0;

    /**
     * The constant BG_HORIZONTAL_OFFSET_INIT: specifies the initial horizontal offset of the background.
     */
    public static final int BG_HORIZONTAL_OFFSET_INIT = 0;

    /**
     * The constant BG_TRANSITION_TIMER_INIT: specifies the initial transition timer of the background.
     */
    public static final int BG_TRANSITION_TIMER_INIT = 0;

    /**
     * The constant BG_HORIZONTAL_NULL_SPEED: specifies the null speed of the background.
     */
    public static final int BG_HORIZONTAL_NULL_SPEED = 0;

    /**
     * The constant RENDER_COIN_IDLE_ROW: specifies the row containing the idle frames of the coin sprite sheet.
     */
    public static final int RENDER_COIN_IDLE_ROW = 0;

    /**
     * The constant RENDER_COIN_COLLECT_ROW: specifies the row containing the collected frames of the coin sprite sheet.
     */
    public static final int RENDER_COIN_COLLECT_ROW = 1;

    /**
     * The constant RENDER_PLAYER_ANIM_TIMER_INIT: specifies the initial animation timer of the player.
     */
    public static final int RENDER_PLAYER_ANIM_TIMER_INIT = 0;

    /**
     * The constant RENDER_PLAYER_FRAME_GET_IMG_Y: specifies the y position of the frame to get from the sprite sheet.
     */
    public static final int RENDER_PLAYER_FRAME_GET_IMG_Y = 0;

    /**
     * The constant RENDER_PLAYER_FRAME_Y: specifies the y position of the player frame.
     */
    public static final int RENDER_PLAYER_FRAME_Y = 0;

    /**
     * The constant RENDER_PLAYER_FRAME_X: specifies the x position of the player frame.
     */
    public static final int RENDER_PLAYER_FRAME_X = 0;

    /**
     * The constant RENDER_UI_SCORE_CONTAINER_X: specifies the x position of the score container.
     */
    public static final int RENDER_UI_SCORE_CONTAINER_X = 0;

    /**
     * The constant RENDER_UI_SCORE_CONTAINER_Y: specifies the y position of the score container.
     */
    public static final int RENDER_UI_SCORE_CONTAINER_Y = 0;


//Factory ----------------------------------------------------------------------

    /**
     * The constant CHARACTER_WIDTH: specifies the width of the character.
     */
    public static final float CHARACTER_WIDTH = 48;

    /**
     * The constant CHARACTER_HEIGHT: specifies the height of the character.
     */
    public static final float CHARACTER_HEIGHT = 50;

    /**
     * The constant CHARACTER_JUMP_FORCE: specifies the jump force of the character.
     */
    public static final float CHARACTER_JUMP_FORCE = 700;

    /**
     * The constant STANDARD_PLATFORM_WIDTH: specifies the width of the standard platform.
     */
    public static final float STANDARD_PLATFORM_WIDTH = 100;

    /**
     * The constant PLATFORM_HEIGHT: specifies the height of the platform.
     */
    public static final float PLATFORM_HEIGHT = 10;

    /**
     * The constant RANDOM_PLATFORM_WIDTH: specifies the width of the random platform.
     */
    public static final float RANDOM_PLATFORM_WIDTH = 80;

    /**
     * The constant RANDOM_PLATFORM_RNG_FACTOR: specifies the random platform width range.
     */
    public static final int RANDOM_PLATFORM_RNG_FACTOR = 41;

    /**
     * The constant MOVING_PLATFORM_WIDTH: specifies the width of the moving platform.
     */
    public static final float MOVING_PLATFORM_WIDTH = 70;

    /**
     * The constant MOVING_PLATFORM_RANGE: specifies the range of the moving platform.
     */
    public static final float MOVING_PLATFORM_RANGE = 50;

    /**
     * The constant MOVING_PLATFORM_SPEED: specifies the speed of the moving platform.
     */
    public static final float MOVING_PLATFORM_SPEED = 30;

    /**
     * The constant MOVING_PLATFORM_WIDTH_RNG_FACTOR: specifies the random moving platform width range factor.
     */
    public static final int MOVING_PLATFORM_WIDTH_RNG_FACTOR = 45;

    /**
     * The constant MOVING_PLATFORM_RANGE_RNG_FACTOR: specifies the random moving platform range factor.
     */
    public static final float MOVING_PLATFORM_RANGE_RNG_FACTOR = 100;

    /**
     * The constant MOVING_PLATFORM_SPEED_RNG_FACTOR: specifies the random moving platform speed factor.
     */
    public static final int MOVING_PLATFORM_SPEED_RNG_FACTOR = 50;

    /**
     * The constant BREAKABLE_PLATFORM_WIDTH: specifies the width of the breakable platform.
     */
    public static final float BREAKABLE_PLATFORM_WIDTH = 80;

    /**
     * The constant BREAKABLE_PLATFORM_RNG_FACTOR: specifies the random breakable platform width range factor.
     */
    public static final int BREAKABLE_PLATFORM_RNG_FACTOR = 25;

    /**
     * The constant BOUNCE_PLATFORM_WIDTH: specifies the width of the bounce platform.
     */
    public static final float BOUNCE_PLATFORM_WIDTH = 80;

    /**
     * The constant BOUNCE_PLATFORM_RNG_FACTOR: specifies the random bounce platform width range factor.
     */
    public static final int BOUNCE_PLATFORM_RNG_FACTOR = 25;

    /**
     * The constant COIN_WIDTH: specifies the width of the coin.
     */
    public static final float COIN_WIDTH = 44;

    /**
     * The constant COIN_HEIGHT: specifies the height of the coin.
     */
    public static final float COIN_HEIGHT = 52;


// Collectibles Spawner ----------------------------------------------------------------

    /**
     * The constant COIN_X_DIV: specifies the division factor for the x position of the coin.
     */
    public static final float COIN_X_DIV = 2;

    /**
     * The constant COIN_X_MUL: specifies the multiplication factor for the x position of the coin.
     */
    public static final float COIN_X_MUL = 0.2f;

    /**
     * The constant COIN_OFFSET: specifies the offset for the y position of the coin.
     */
    public static final float COIN_OFFSET = 50;


//Difficulty -----------------------------------------------------------------------------

    /**
     * The constant SCORE_INIT: specifies the initial score.
     */
    public static final int SCORE_INIT = 0;

    /**
     * The constant HELL_MIN: specifies the minimum score for the hell difficulty setting.
     */
    public static final int HELL_MIN = 16_000;

    /**
     * The constant HELL_MAX: specifies the maximum score for the hell difficulty setting.
     */
    public static final int HELL_MAX = 25_000;

    /**
     * The constant VERY_HARD_MIN: specifies the minimum score for the very hard difficulty setting.
     */
    public static final int VERY_HARD_MIN = 8000;

    /**
     * The constant VERY_HARD_MAX: specifies the maximum score for the very hard difficulty setting.
     */
    public static final int VERY_HARD_MAX = 10_000;

    /**
     * The constant HARD_MIN: specifies the minimum score for the hard difficulty setting.
     */
    public static final int HARD_MIN = 4_000;

    /**
     * The constant HARD_MAX: specifies the maximum score for the hard difficulty setting.
     */
    public static final int HARD_MAX = 6000;

    /**
     * The constant MEDIUM_MIN: specifies the minimum score for the medium difficulty setting.
     */
    public static final int MEDIUM_MIN = 1500;

    /**
     * The constant MEDIUM_MAX: specifies the maximum score for the medium difficulty setting.
     */
    public static final int MEDIUM_MAX = 3000;


//Spawn Utils -----------------------------------------------------------------------------

    /**
     * The constant SPAWN_X_OFFSET: specifies the x offset for the player spawn.
     */
    public static final int SPAWN_X_OFFSET = 40;

    /**
     * The constant SPAWN_Y_OFFSET: specifies the y offset for the player spawn.
     */
    public static final int SPAWN_Y_OFFSET = 60;


// Random Spawn Strategy -------------------------------------------------------------------

    /**
     * The constant MAX_PLATFORM_WIDTH: specifies the maximum width of the platform.
     */
    public static final int MAX_PLATFORM_WIDTH = 120;

    /**
     * The constant GAP_EASY_ADDENDUM: specifies the gap addendum for the easy difficulties.
     */
    public static final int GAP_EASY_ADDENDUM = 10;

    /**
     * The constant GAP_HARD_ADDENDUM: specifies the gap addendum for the hard difficulties.
     */
    public static final int GAP_HARD_ADDENDUM = 30;

    /**
     * The constant GAP_INIT: specifies the initial gap.
     */
    public static final int GAP_INIT = 0;


// CleanUpManager ------------------------------------------------------------------------

    /**
     * The constant CLEAN_UP_MARGIN_OFFSET: specifies the offset for the offscreen objects removal margin.
     */
    public static final float CLEAN_UP_MARGIN_OFFSET = 50;


// Spawn Manager ----------------------------------------------------------------------------

    /**
     * The constant INITIAL_Y_SPAWN_OFFSET: specifies the initial y spawn offset.
     */
    public static final int INITIAL_Y_SPAWN_OFFSET = 150;

    /**
     * The constant INITIAL_PLATFORMS_NUMBER: specifies the initial number of platforms.
     */
    public static final int INITIAL_PLATFORMS_NUMBER = 10;

    /**
     * The constant PROCEDURAL_PLATFORMS_NUMBER: specifies the number of procedural platforms to keep spawning.
     */
    public static final int PROCEDURAL_PLATFORMS_NUMBER = 10;

    /**
     * The constant SPAWN_THRESHOLD: specifies the threshold for new objects spawning.
     */
    public static final float SPAWN_THRESHOLD = 400f;

    /**
     * The constant BOUNCE_FACTOR_MIN: specifies the minimum bounce factor possible.
     */
    public static final float BOUNCE_FACTOR_MIN = 1.5f;

    /**
     * The constant BOUNCE_FACTOR_MAX: specifies the maximum bounce factor possible.
     */
    public static final float BOUNCE_FACTOR_MAX = 2.4f;


// Game Model -------------------------------------------------------------------------------

    /**
     * The constant CHARACTER_CREATION_WIDTH_DIV: specifies the division factor for the width of the character position.
     */
    public static final float CHARACTER_CREATION_WIDTH_DIV = 2;

    /**
     * The constant CHARACTER_CREATION_HEIGHT_MUL: specifies the multiplication factor for the height of
     * the character position.
     */
    public static final float CHARACTER_CREATION_HEIGHT_MUL = 0.8f;


// Background Renderer ----------------------------------------------------------------------

    /**
     * The constant BG_EXTRA_TILES_NUMBER: specifies the number of extra tiles for the background fill.
     */
    public static final int BG_EXTRA_TILES_NUMBER = 2;


// Coin Render ------------------------------------------------------------------------------

    /**
     * The constant COIN_ANIM_TIMER_START: specifies the start value for the coin animation timer.
     */
    public static final float COIN_ANIM_TIMER_START = 0;

    /**
     * The constant COIN_ANIMATION_CYCLE_DURATION: specifies the duration of the coin animation cycle.
     */
    public static final int COIN_ANIMATION_CYCLE_DURATION = 6;

    /**
     * The constant COIN_ANIMATION_INDEX_MAX: specifies the maximum index for the coin animation.
     */
    public static final int COIN_ANIMATION_INDEX_MAX = 7;


// Player Render ---------------------------------------------------------------------------------

    /**
     * The constant PLAYER_ANIMATION_CYCLE_DURATION: specifies the duration of the player animation cycle.
     */
    public static final int PLAYER_ANIMATION_CYCLE_DURATION = 2;

    /**
     * The constant PLAYER_LANDING_START_FRAME: specifies the start frame position in sprite sheet
     * for the player landing.
     */
    public static final int PLAYER_LANDING_START_FRAME = 0;

    /**
     * The constant PLAYER_LANDING_END_FRAME: specifies the end frame position in sprite sheet for the player landing.
     */
    public static final int PLAYER_LANDING_END_FRAME = 1;

    /**
     * The constant PLAYER_JUMP_START_FRAME: specifies the start frame position in sprite sheet for the player jumping.
     */
    public static final int PLAYER_JUMP_START_FRAME = 2;

    /**
     * The constant PLAYER_JUMP_END_FRAME: specifies the end frame position in sprite sheet for the player jumping.
     */
    public static final int PLAYER_JUMP_END_FRAME = 3;


// Score Render ------------------------------------------------------------------------------

    /**
     * The constant SCORE_RENDER_X_POSITION: specifies the x position for the score text display.
     */
    public static final int SCORE_RENDER_X_POSITION = 10;

    /**
     * The constant SCORE_RENDER_Y_POSITION: specifies the y position for the score text display.
     */
    public static final int SCORE_RENDER_Y_POSITION = 20;

    /**
     * The constant HIGH_SCORE_RENDER_Y_POSITION: specifies the y position for the high score message text display.
     */
    public static final int HIGH_SCORE_RENDER_Y_POSITION = 45;

    /**
     * The constant HIGH_SCORE_RENDER_X_POSITION: specifies the x position for the high score message text display.
     */
    public static final int HIGH_SCORE_RENDER_X_POSITION = 10;

    /**
     * The constant SCORE_RENDER_TEXT: specifies the score text.
     */
    public static final String SCORE_RENDER_TEXT = "Score:   ";

    /**
     * The constant HIGH_SCORE_RENDER_TEXT: specifies the high score text.
     */
    public static final String HIGH_SCORE_RENDER_TEXT = "New High Score !!";


// Render Manager -----------------------------------------------------------------------------------

    /**
     * The constant RENDER_MANAGER_PLATFORM_OUTLINE_THICKNESS: specifies the thickness of the platform outline.
     */
    public static final float RENDER_MANAGER_PLATFORM_OUTLINE_THICKNESS = 2;

    /**
     * The constant RENDER_MANAGER_PLATFORM_ROUND_CORNER_WIDTH: specifies the width of the platform round corner.
     */
    public static final int RENDER_MANAGER_PLATFORM_ROUND_CORNER_WIDTH = 10;

    /**
     * The constant RENDER_MANAGER_PLATFORM_ROUND_CORNER_HEIGHT: specifies the height of the platform round corner.
     */
    public static final int RENDER_MANAGER_PLATFORM_ROUND_CORNER_HEIGHT = 10;

    /**
     * The constant RENDER_MANAGER_COIN_WIDTH: specifies the width of the coin image.
     */
    public static final int RENDER_MANAGER_COIN_WIDTH = 44;

    /**
     * The constant RENDER_MANAGER_COIN_HEIGHT: specifies the height of the coin image.
     */
    public static final int RENDER_MANAGER_COIN_HEIGHT = 52;

    /**
     * The constant RENDER_MANAGER_COIN_FRAME_DURATION: specifies the duration of the coin animation.
     */
    public static final float RENDER_MANAGER_COIN_FRAME_DURATION = 0.05f;

    /**
     * The constant RENDER_MANAGER_PLAYER_WIDTH: specifies the width of the player image.
     */
    public static final int RENDER_MANAGER_PLAYER_WIDTH = 48;

    /**
     * The constant RENDER_MANAGER_PLAYER_HEIGHT: specifies the height of the player image.
     */
    public static final int RENDER_MANAGER_PLAYER_HEIGHT = 50;

    /**
     * The constant RENDER_MANAGER_PLAYER_FRAME_DURATION: specifies the duration of the player animation.
     */
    public static final float RENDER_MANAGER_PLAYER_FRAME_DURATION = 0.2f;

    /**
     * The constant RENDER_MANAGER_BACKGROUND_ONE_PARALLAX_MODIFIER: specifies the parallax scrolling speed modifier
     * for the first background.
     */
    public static final float RENDER_MANAGER_BACKGROUND_ONE_PARALLAX_MODIFIER = 0.2f;

    /**
     * The constant RENDER_MANAGER_BACKGROUND_TWO_PARALLAX_MODIFIER: specifies the parallax scrolling speed modifier
     * for the second background.
     */
    public static final float RENDER_MANAGER_BACKGROUND_TWO_PARALLAX_MODIFIER = 0.4f;

    /**
     * The constant RENDER_MANAGER_BACKGROUND_ONE_X_SPEED: specifies the horizontal scrolling speed
     * for the first background.
     */
    public static final float RENDER_MANAGER_BACKGROUND_ONE_X_SPEED = 0;

    /**
     * The constant RENDER_MANAGER_BACKGROUND_TWO_X_SPEED: specifies the horizontal scrolling speed
     * for the second background.
     */
    public static final float RENDER_MANAGER_BACKGROUND_TWO_X_SPEED = 20;

    /**
     * The constant TRANSITION_DURATION_1: specifies the duration of the first transition (for the background).
     */
    public static final float TRANSITION_DURATION_1 = 0.8f;

    /**
     * The constant TRANSITION_DURATION_2: specifies the duration of the second transition (for the clouds).
     */
    public static final float TRANSITION_DURATION_2 = 1.2f;


// Audio Manager -----------------------------------------------------------------------------------

    /**
     * The constant MUSIC_VOLUME: specifies the volume of the music.
     */
    public static final float MUSIC_VOLUME = 0.7f;

    /**
     * The constant SOUND_EFFECTS_VOLUME: specifies the volume of the sound effects.
     */
    public static final float SOUND_EFFECTS_VOLUME = 0.75f;

    /**
     * The constant MUSIC_LOOP_START: specifies the starting point of the music track loop.
     */
    public static final int MUSIC_LOOP_START = 0;

    /**
     * The constant MUSIC_LOOP_END: specifies the ending point of the music track loop.
     */
    public static final float MUSIC_LOOP_END = 0.885f;

    /**
     * The constant AUDIO_FRAME_INIT: specifies the initial frame of the audio track.
     */
    public static final int AUDIO_FRAME_INIT = 0;

    /**
     * The constant AUDIO_STEPS: specifies the number of desired fadeout steps in the audio track.
     */
    public static final int AUDIO_STEPS = 50;

    /**
     * The constant AUDIO_SLEEP: specifies the sleep time between fadeout steps.
     */
    public static final int AUDIO_SLEEP = 1000;

    /**
     * The constant SOUNDS_POOL_SIZE_NUMBER: specifies the size of the sound effects pool.
     */
    public static final int SOUNDS_POOL_SIZE_NUMBER = 5;


// InGameView -----------------------------------------------------------------------------------

    /**
     * The constant GAMEPLAY_MESSAGE_TIME_TOGGLE: specifies the duration of the high-score message toggle.
     */
    public static final int GAMEPLAY_MESSAGE_TIME_TOGGLE = 1700;


// MenuView -----------------------------------------------------------------------------------

    /**
     * The constant MENU_VIEW_CENTER_DIV: specifies the center division in the menu view.
     */
    public static final int MENU_VIEW_CENTER_DIV = 2;

    /**
     * The constant MENU_VIEW_RECT_X: specifies the x position of the menu view background.
     */
    public static final int MENU_VIEW_RECT_X = 0;

    /**
     * The constant MENU_VIEW_RECT_Y: specifies the y position of the menu view background.
     */
    public static final int MENU_VIEW_RECT_Y = 0;

    /**
     * The constant MENU_VIEW_TITLE_IMG_WIDTH_SCALAR: specifies the width scalar of the title image.
     */
    public static final float MENU_VIEW_TITLE_IMG_WIDTH_SCALAR = 0.03f;

    /**
     * The constant MENU_VIEW_TITLE_IMG_HEIGHT_SCALAR: specifies the height scalar of the title image.
     */
    public static final int MENU_VIEW_TITLE_IMG_HEIGHT_SCALAR = 5;

    /**
     * The constant MENU_VIEW_TILE_W_SCALAR: specifies the width scalar of the menu view tiles.
     */
    public static final int MENU_VIEW_TILE_W_SCALAR = 1;

    /**
     * The constant MENU_VIEW_TILE_H_SCALAR: specifies the height scalar of the menu view tiles.
     */
    public static final int MENU_VIEW_TILE_H_SCALAR = 1;

    /**
     * The constant MENU_VIEW_START_TEXT: specifies the start game text.
     */
    public static final String MENU_VIEW_START_TEXT = "- Start Game (ENTER)";

    /**
     * The constant MENU_VIEW_QUIT_TEXT: specifies the quit game text.
     */
    public static final String MENU_VIEW_QUIT_TEXT = "- Quit (ESC)";

    /**
     * The constant MENU_VIEW_TEXT_X_CENTER: specifies the x position of the menu view text.
     */
    public static final int MENU_VIEW_TEXT_X_CENTER = 4;

    /**
     * The constant MENU_VIEW_START_TEXT_Y_OFFSET: specifies the y offset of the start game text.
     */
    public static final int MENU_VIEW_START_TEXT_Y_OFFSET = 30;

    /**
     * The constant MENU_VIEW_QUIT_TEXT_Y_OFFSET: specifies the y offset of the quit game text.
     */
    public static final int MENU_VIEW_QUIT_TEXT_Y_OFFSET = 70;

    /**
     * The constant MENU_VIEW_STOKE_WIDTH: specifies the width of the menu view stroke.
     */
    public static final float MENU_VIEW_STOKE_WIDTH = 2f;

    /**
     * The constant MENU_VIEW_STROKE_MITER_LIMIT: specifies the miter limit of the menu view stroke.
     */
    public static final int MENU_VIEW_STROKE_MITER_LIMIT = 0;

    /**
     * The constant MENU_VIEW_STROKE_ARRAY: specifies the array of the menu view stroke.
     */
    public static final int MENU_VIEW_STROKE_ARRAY = 9;

    /**
     * The constant MENU_VIEW_STROKE_DASH: specifies the dash of the menu view stroke.
     */
    public static final int MENU_VIEW_STROKE_DASH = 0;

    /**
     * The constant MENU_VIEW_ROUND_RECT_X_DIV: specifies the x division of the menu view round rect.
     */
    public static final int MENU_VIEW_ROUND_RECT_X_DIV = 3;

    /**
     * The constant MENU_VIEW_ROUND_RECT_X_OFF: specifies the x offset of the menu view round rect.
     */
    public static final int MENU_VIEW_ROUND_RECT_X_OFF = 20;

    /**
     * The constant MENU_VIEW_ROUND_RECT_Y_OFF: specifies the y offset of the menu view round rect.
     */
    public static final int MENU_VIEW_ROUND_RECT_Y_OFF = 120;

    /**
     * The constant MENU_VIEW_ROUND_RECT_WIDTH: specifies the width of the menu view round rect.
     */
    public static final int MENU_VIEW_ROUND_RECT_WIDTH = 400;

    /**
     * The constant MENU_VIEW_ROUND_RECT_HEIGHT: specifies the height of the menu view round rect.
     */
    public static final int MENU_VIEW_ROUND_RECT_HEIGHT = 40;

    /**
     * The constant MENU_VIEW_ROUND_RECT_ARC_W: specifies the arc width of the menu view round rect.
     */
    public static final int MENU_VIEW_ROUND_RECT_ARC_W = 10;

    /**
     * The constant MENU_VIEW_ROUND_RECT_ARC_H: specifies the arc height of the menu view round rect.
     */
    public static final int MENU_VIEW_ROUND_RECT_ARC_H = 10;

    /**
     * The constant MENU_VIEW_BEST_SCORE_TEXT: specifies the best score text.
     */
    public static final String MENU_VIEW_BEST_SCORE_TEXT = "Best Score:   ";

    /**
     * The constant MENU_VIEW_BEST_SCORE_X_DIV: specifies the x division of the menu view best score.
     */
    public static final int MENU_VIEW_BEST_SCORE_X_DIV = 3;

    /**
     * The constant MENU_VIEW_BEST_SCORE_Y_OFF: specifies the y offset of the menu view best score.
     */
    public static final int MENU_VIEW_BEST_SCORE_Y_OFF = 95;


//GameOverView ------------------------------------------------------------------------------------------

    /**
     * The constant GAME_OVER_ALPHA_INIT: specifies the initial alpha of the game over transition.
     */
    public static final float GAME_OVER_ALPHA_INIT = 0;

    /**
     * The constant GAME_OVER_DURATION_INIT: specifies the initial duration of the game over transition.
     */
    public static final float GAME_OVER_DURATION_INIT = 1;

    /**
     * The constant GAME_OVER_TIME_INIT: specifies the initial time of the game over transition.
     */
    public static final float GAME_OVER_TIME_INIT = 0;

    /**
     * The constant GAME_OVER_ALPHA: specifies the target alpha of the game over transition.
     */
    public static final float GAME_OVER_ALPHA = 1;

    /**
     * The constant GAME_OVER_CENTER_DIV: specifies the division of the game over center.
     */
    public static final int GAME_OVER_CENTER_DIV = 2;

    /**
     * The constant GAME_OVER_RECT_X: specifies the x position of the game over rectangle.
     */
    public static final int GAME_OVER_RECT_X = 0;

    /**
     * The constant GAME_OVER_RECT_Y: specifies the y position of the game over rectangle.
     */
    public static final int GAME_OVER_RECT_Y = 0;

    /**
     * The constant GAME_OVER_IMG_W_OFF: specifies the width offset of the game over image.
     */
    public static final float GAME_OVER_IMG_W_OFF = 1.72f;

    /**
     * The constant GAME_OVER_IMG_H_OFF: specifies the height offset of the game over image.
     */
    public static final float GAME_OVER_IMG_H_OFF = 0.15f;

    /**
     * The constant GAME_OVER_IMG_SCALE_OFF: specifies the scale of the game over image.
     */
    public static final float GAME_OVER_IMG_SCALE_OFF = 1.1f;

    /**
     * The constant GAME_OVER_NEW_TEXT: specifies the new best score text.
     */
    public static final String GAME_OVER_NEW_TEXT = "New Best Score:   ";

    /**
     * The constant GAME_OVER_SCORE_TEXT: specifies the game over score obtained by the player text.
     */
    public static final String GAME_OVER_SCORE_TEXT = "Your Score:   ";

    /**
     * The constant GAME_OVER_BEST_TEXT: specifies the best score text.
     */
    public static final String GAME_OVER_BEST_TEXT = "Best Score:   ";

    /**
     * The constant GAME_OVER_CONTINUE_TEXT: specifies the game over continue text.
     */
    public static final String GAME_OVER_CONTINUE_TEXT = "Press ENTER to continue...";

    /**
     * The constant GAME_OVER_TEXT_X_OFF: specifies the x offset of the game over text.
     */
    public static final float GAME_OVER_TEXT_X_OFF = 0.45f;

    /**
     * The constant GAME_OVER_TEXT_NEW_Y_OFF: specifies the y offset of the game over new text.
     */
    public static final int GAME_OVER_TEXT_NEW_Y_OFF = 50;

    /**
     * The constant GAME_OVER_TEXT_SCORE_Y_OFF: specifies the y offset of the game over score text.
     */
    public static final int GAME_OVER_TEXT_SCORE_Y_OFF = 50;

    /**
     * The constant GAME_OVER_TEXT_BEST_Y_OFF: specifies the y offset of the game over best text.
     */
    public static final int GAME_OVER_TEXT_BEST_Y_OFF = 80;

    /**
     * The constant GAME_OVER_TEXT_CONTINUE_Y_OFF: specifies the y offset of the game over continue text.
     */
    public static final int GAME_OVER_TEXT_CONTINUE_Y_OFF = 150;

    /**
     * The constant GAME_OVER_NEW_TEXT_ESC: specifies the new best score escape text.
     */
    public static final String GAME_OVER_NEW_TEXT_ESC = " !!";


// PauseView ------------------------------------------------------------------------------------------
    /**
     * The constant PAUSE_CONTINUE_Y: specifies the y position of the pause continue button.
     */
    public static final int PAUSE_CONTINUE_Y = 70;

    /**
     * The constant PAUSE_MAIN_MENU_Y: specifies the y position of the pause main menu button.
     */
    public static final int PAUSE_MAIN_MENU_Y = 100;

    /**
     * The constant PAUSE_QUIT_Y: specifies the y position of the pause quit button.
     */
    public static final int PAUSE_QUIT_Y = 130;

    /**
     * The constant PAUSE_SELECTION_X: specifies the x position of the pause selection.
     */
    public static final int PAUSE_SELECTION_X = 20;

    /**
     * The constant PAUSE_TEXT: specifies the pause text.
     */
    public static final String PAUSE_TEXT = "PAUSE";

    /**
     * The constant PAUSE_CONTINUE_TEXT: specifies the pause continue text.
     */
    public static final String PAUSE_CONTINUE_TEXT = "Continue";

    /**
     * The constant PAUSE_MAIN_MENU_TEXT: specifies the pause main menu text.
     */
    public static final String PAUSE_MAIN_MENU_TEXT = "Main Menu";

    /**
     * The constant PAUSE_QUIT_TEXT: specifies the pause quit text.
     */
    public static final String PAUSE_QUIT_TEXT = "Quit";

    /**
     * The constant PAUSE_SELECTION_TEXT: specifies the pause selection text.
     */
    public static final String PAUSE_SELECTION_TEXT = "- ";

    /**
     * The constant PAUSE_CENTER_DIV: specifies the pause center division.
     */
    public static final int PAUSE_CENTER_DIV = 2;

    /**
     * The constant PAUSE_WIDTH_OFF: specifies the pause width offset.
     */
    public static final int PAUSE_WIDTH_OFF = 50;


//MainView -----------------------------------------------------------------------------------------
    /**
     * The constant MAIN_VIEW_CENTER_DIV: specifies the main view center division.
     */
    public static final int MAIN_VIEW_CENTER_DIV = 2;

    /**
     * The constant MAIN_VIEW_AUDIO_FADE: specifies the main view audio fade.
     */
    public static final float MAIN_VIEW_AUDIO_FADE = 2f;

    /**
     * The constant MAIN_VIEW_DRAW_X_INIT: specifies the main view background draw x init.
     */
    public static final int MAIN_VIEW_DRAW_X_INIT = 0;

    /**
     * The constant MAIN_VIEW_DRAW_Y_INIT: specifies the main view background draw y init.
     */
    public static final int MAIN_VIEW_DRAW_Y_INIT = 0;

    /**
     * The constant MAIN_VIEW_RECT_X: specifies the main view rect x.
     */
    public static final int MAIN_VIEW_RECT_X = 0;

    /**
     * The constant MAIN_VIEW_RECT_Y: specifies the main view rect y.
     */
    public static final int MAIN_VIEW_RECT_Y = 0;


//COLOR CODING ---------------------------------------------------------------------------------------

    // 1- General colours

    /**
     * The constant BACKGROUND_DEFAULT_COLOR: specifies the default desired background color used in all the game.
     */
    public static final String BACKGROUND_DEFAULT_COLOR = "#05051C";

    /**
     * The constant GOLD_TEXT_COLOR: specifies the desired gold text color.
     */
    public static final String GOLD_TEXT_COLOR = "#EAC10C";

    /**
     * The constant RED_TEXT_COLOR: specifies the desired red text color.
     */
    public static final String RED_TEXT_COLOR = "#F84534";


    // 2- GameObject colours

    /**
     * The constant PLATFORM_HIGHLIGHT_COLOR: specifies the desired general platform highlight color.
     */
    public static final String PLATFORM_HIGHLIGHT_COLOR = "#D4C340";

    /**
     * The constant STANDARD_PLATFORM_COLOR: specifies the desired standard platform color.
     */
    public static final String STANDARD_PLATFORM_COLOR = "#4D9F50";

    /**
     * The constant MOVING_PLATFORM_COLOR: specifies the desired moving platform color.
     */
    public static final String MOVING_PLATFORM_COLOR = "#276B91";

    /**
     * The constant BREAKABLE_PLATFORM_COLOR: specifies the desired breakable platform color.
     */
    public static final String BREAKABLE_PLATFORM_COLOR = "#EA4B1E";

    /**
     * The constant BOUNCE_PLATFORM_COLOR: specifies the desired bouncy platform color.
     */
    public static final String BOUNCE_PLATFORM_COLOR = "#D15484";

    /**
     * The constant OUTLINE_COLOR: specifies the desired general outline color.
     */
    public static final String OUTLINE_COLOR = "#0E081E";


// Resources Paths ------------------------------------------------------------------------------------------

    // static path
    //public static final String RESOURCES_PATH = "src/main/resources/";

    /**
     * The constant RESOURCES_TITLE: specifies the title image file name.
     */
    public static final String RESOURCES_TITLE = "/JJ_Title.png";

    /**
     * The constant RESOURCES_SCORE_CONTAINER: specifies the score container image file name.
     */
    public static final String RESOURCES_SCORE_CONTAINER = "/Score_Underlay_small.png";

    /**
     * The constant RESOURCES_GAMEOVER: specifies the game over image file name.
     */
    public static final String RESOURCES_GAMEOVER = "/GameOver.png";

    /**
     * The constant RESOURCES_PLAYER: specifies the player image file name.
     */
    public static final String RESOURCES_PLAYER = "/Coffee-SheetBIG.png";

    /**
     * The constant RESOURCES_COIN: specifies the coin image file name.
     */
    public static final String RESOURCES_COIN = "/Coin-Sheet.png";

    /**
     * The constant RESOURCES_BACKGROUND_EASY: specifies the easy background image file name.
     */
    public static final String RESOURCES_BACKGROUND_EASY = "/Background_Easy.png";

    /**
     * The constant RESOURCES_BACKGROUND_MEDIUM: specifies the medium background image file name.
     */
    public static final String RESOURCES_BACKGROUND_MEDIUM = "/Background_Med.png";

    /**
     * The constant RESOURCES_BACKGROUND_HARD: specifies the hard background image file name.
     */
    public static final String RESOURCES_BACKGROUND_HARD = "/Background_Diff.png";

    /**
     * The constant RESOURCES_CLOUDS_EASY: specifies the easy clouds image file name.
     */
    public static final String RESOURCES_CLOUDS_EASY = "/Clouds_Easy.png";

    /**
     * The constant RESOURCES_CLOUDS_MEDIUM: specifies the medium clouds image file name.
     */
    public static final String RESOURCES_CLOUDS_MEDIUM = "/Clouds_Med.png";

    /**
     * The constant RESOURCES_CLOUDS_HARD: specifies the hard clouds image file name.
     */
    public static final String RESOURCES_CLOUDS_HARD = "/Clouds_Diff.png";

    /**
     * The constant RESOURCES_FONT_1: specifies the first font file name.
     */
    public static final String RESOURCES_FONT_1 = "/Daydream.ttf";

    /**
     * The constant RESOURCES_FONT_2: specifies the second font file name.
     */
    public static final String RESOURCES_FONT_2 = "/Daydream.ttf";

    /**
     * The constant RESOURCES_FONT_3: specifies the third font file name.
     */
    public static final String RESOURCES_FONT_3 = "/Daydream.ttf";

    /**
     * The constant SIZE_FONT_1: specifies the size of the first font.
     */
    public static final int SIZE_FONT_1 = 20;

    /**
     * The constant SIZE_FONT_2: specifies the size of the second font.
     */
    public static final int SIZE_FONT_2 = 15;

    /**
     * The constant SIZE_FONT_3: specifies the size of the third font.
     */
    public static final int SIZE_FONT_3 = 10;

    /**
     * The constant RESOURCES_MUSIC_1: specifies the music #1 file name.
     */
    public static final String RESOURCES_MUSIC_1 = "/GameMusic.wav";

    /**
     * The constant RESOURCE_BOUNCE_SFX: specifies the bounce sound file name.
     */
    public static final String RESOURCE_BOUNCE_SFX = "/Bounce.wav";

    /**
     * The constant RESOURCE_BREAK_SFX: specifies the break sound file name.
     */
    public static final String RESOURCE_BREAK_SFX = "/Break.wav";

    /**
     * The constant RESOURCE_DEFAULT_SFX: specifies the default sound file name.
     */
    public static final String RESOURCE_DEFAULT_SFX = "/Jump.wav";

    /**
     * The constant RESOURCE_COIN_SFX: specifies the coin sound file name.
     */
    public static final String RESOURCE_COIN_SFX = "/Coin.wav";

    /**
     * The constant IMAGE_IMPORT_ERROR_TEXT: specifies the text to be displayed when an image cannot be imported.
     */
    public static final String IMAGE_IMPORT_ERROR_TEXT = "GameAction Queue is full, cannot add: ";

    /**
     * Serial ID for GameFrame and GameView.
     */
    public static final long SERIAL_ID = 1L;

    /**
     * Private constructor for Constants, to avoid instantiation.
     *
     * @throws AssertionError when wrongly called.
     */
    private Constants() {
        throw new AssertionError("This is a utility class, it should not be instantiated!");
    }

}
