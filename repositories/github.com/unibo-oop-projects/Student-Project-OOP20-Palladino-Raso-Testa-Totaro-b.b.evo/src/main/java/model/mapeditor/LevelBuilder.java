package model.mapeditor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import model.entities.Brick;
import model.entities.Brick.Builder;
import model.entities.GameObject;
import model.entities.GameObjectEmpty;
import model.utilities.BrickStatus;
import model.utilities.ScreenUtilities;
import model.utilities.Pair;
import model.utilities.Position;
import resource.routing.BackGround;
import resource.routing.BallTexture;
import resource.routing.BrickTexture;
import resource.routing.PaddleTexture;
import resource.routing.PersonalSounds;
import resource.routing.PowerUpTexture;

/**
 * Model the level by select resource, put it on a grid and build all.
 *
 */
public class LevelBuilder {

    //build the map between bricks in the board and coordinates
    private final Map<GameObjectEmpty, Pair<Integer, Integer>> builderGrid = new HashMap<>();
    //build the map between bricks in the show and in the grid and coordinates
    private final Map<Pair<Integer, Integer>, Pair<GameObjectEmpty, Optional<Brick>>> gameGrid = new HashMap<>();

    private static final  int BUILDERBRICKDIMY = (int) (ScreenUtilities.CANVAS_HEIGHT / ScreenUtilities.BRICK_NUMBER_Y);
    private static final int BUILDERBRICK_DIMX = (int) (ScreenUtilities.CANVAS_WIDTH / ScreenUtilities.BRICK_NUMBER_X);
    private static final int GAMEBRICK_DIMY = (int) (ScreenUtilities.BOARD_HEIGHT / ScreenUtilities.BRICK_NUMBER_Y);
    private static final int GAMEBRICK_DIMX = (int) (ScreenUtilities.BOARD_WIDTH / ScreenUtilities.BRICK_NUMBER_X);

    private String levelName;
    private BackGround background;
    private PersonalSounds music;
    private BallTexture ball;
    private PaddleTexture paddle;

    public LevelBuilder() {
        int currentXpos = 0;
        for (int i = 0; i < ScreenUtilities.BRICK_NUMBER_X; i++) {
            int currentYpos = 0;
            for (int j = 0; j < ScreenUtilities.BRICK_NUMBER_Y; j++) {
                final Pair<Integer, Integer> coordinates = new Pair<>(i, j);
                final GameObjectEmpty objectEmpty = new GameObjectEmpty(new Position(currentXpos, currentYpos), BUILDERBRICKDIMY, BUILDERBRICK_DIMX);
                this.builderGrid.put(objectEmpty, coordinates);
                currentYpos += BUILDERBRICKDIMY;
            }
            currentXpos += BUILDERBRICK_DIMX;
        }
        currentXpos = 0;
        for (int i = 0; i < ScreenUtilities.BRICK_NUMBER_X; i++) {
            int currentYpos = 0;
            for (int j = 0; j < ScreenUtilities.BRICK_NUMBER_Y; j++) {
                final Pair<Integer, Integer> coordinates = new Pair<>(i, j);
                final GameObjectEmpty objectEmpty = new GameObjectEmpty(new Position(currentXpos, currentYpos), BUILDERBRICKDIMY, BUILDERBRICK_DIMX);
                this.gameGrid.put(coordinates, new Pair<>(objectEmpty, Optional.empty()));
                currentYpos += GAMEBRICK_DIMY;
            }
            currentXpos += GAMEBRICK_DIMX;
        }
    }

    /**
     * First compare the x, y coordinates of the click with the grid containing, then
     * the game object built on the dimensions of the current size of the screen 
     * by returning the number of the brick selected.
     * Check in the grid containing the game object built on the size of the board which coordinate was selected.
     * Checks if the paddle has already selected that brick 
     * if he had not selected I call the brick builder and build the brick with the form inputs 
     * and the dimensions of the GameObjectEmpty built on the size of the board
     * @param x mouse coordinates mouse x coordinate
     * @param y mouse coordinates mouse y coordinate
     * @param texture texture selected
     * @param state check if is destroyable or not
     * @param durability lives remaining before destroy the brick
     * @return current game grid state
     */
    public Pair<GameObjectEmpty, Boolean> brickSelected(final double x, final double y, 
                                                    final String texture, final BrickStatus state, final int durability) {
        Pair<GameObjectEmpty, Boolean> retState = new Pair<>(new GameObjectEmpty(new Position(0, 0), 0, 0), false);
        for (final GameObjectEmpty objectEmpty : this.builderGrid.keySet()) {
            if (x > objectEmpty.getPos().getX() && x < objectEmpty.getPos().getX() + objectEmpty.getWidth() && y > objectEmpty.getPos().getY()
                    && y < objectEmpty.getPos().getY() + objectEmpty.getHeight()) {
                final Pair<Integer, Integer> brickSelected = this.builderGrid.get(objectEmpty);
                if (this.gameGrid.get(brickSelected).getY().isPresent()) {
                    this.gameGrid.replace(brickSelected, new Pair<>(this.gameGrid.get(brickSelected).getX(), Optional.empty()));
                    retState = new Pair<>(objectEmpty, false);
                } else {
                    final String selectedTexture;
                    if (state.equals(BrickStatus.DROP_POWERUP)) {
                            selectedTexture = PowerUpTexture.getPowerUpTextureByName(texture).getPath();
                    } else if (state.equals(BrickStatus.NOT_DESTRUCTIBLE)) {
                            selectedTexture = BrickTexture.BRICK_TEXTURE_UNDESTRUCTIBLE.getPath();
                    } else {
                            selectedTexture = BrickTexture.getBrickTextureByName(texture).getPath();
                    }
                    final Builder brickBuilder = new Builder();
                    final GameObject gameObjectEmpty = this.gameGrid.get(brickSelected).getX();
                    final Brick brick = brickBuilder.position(new Position(gameObjectEmpty.getPos().getX(), gameObjectEmpty.getPos().getY()))
                                                     .height(this.gameGrid.get(brickSelected).getX().getHeight())
                                                     .width(this.gameGrid.get(brickSelected).getX().getWidth())
                                                     .status(state)
                                                     .texture(selectedTexture)
                                                     .durability(durability)
                                                     .build();
                    this.gameGrid.replace(brickSelected, new Pair<>(this.gameGrid.get(brickSelected).getX(), Optional.of(brick)));
                    retState = new Pair<>(objectEmpty, true);
                }
            }
        }
        return retState;
    }

    /**
     * 
     * @return level built
     */
    public Level build() {
        final Set<Brick> levelBrick = this.gameGrid.entrySet().stream()
                                                               .map(i -> i.getValue().getY())
                                                               .filter(i -> i.isPresent())
                                                               .map(i -> i.get())
                                                               .collect(Collectors.toSet());
        return new Level(levelBrick, levelName, music, background, ball, paddle);
    }

    /**
     * 
     * @param levelName name of the level
     */
    public void setLevelName(final String levelName) {
        this.levelName = levelName;
    }

    /**
     * 
     * @param backGround to set
     */
    public void setBackGround(final String backGround) {
        this.background = BackGround.getBackGroundByName(backGround);
    }

    /**
     * 
     * @param music to set
     */
    public void setMusic(final String music) {
        this.music = PersonalSounds.getSoundsByName(music);
    }

    /**
     * 
     * @param ball texture to set
     */
    public void setBall(final String ball) {
        this.ball = BallTexture.getBallTextureByName(ball);
    }

    /**
     * @param paddle texture to set
     */
    public void setPaddle(final String paddle) {
        this.paddle = PaddleTexture.getPaddleTextureByName(paddle);
    }

    /**
     * 
     * Delete all elements. 
     */
    public void deleteAll() {
        for (final var elem : this.gameGrid.keySet()) {
            this.gameGrid.replace(elem, new Pair<>(this.gameGrid.get(elem).getX(), Optional.empty()));
        }
    }
}
