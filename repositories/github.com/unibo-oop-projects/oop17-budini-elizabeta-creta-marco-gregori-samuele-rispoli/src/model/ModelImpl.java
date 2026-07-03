package model;

import java.util.List;
import java.util.Optional;

import controller.levels.levelManager;
import model.entities.DynamicEntity;
import model.entities.EntityStatus;
import model.entities.FloorTile;
import model.entities.Movement;
import model.entities.Stair;
import model.levels.GameLevel;

public abstract class ModelImpl implements ModelInterface{
    

    public final static int HEIGHT = 540;
    public final static int WIDTH = 460;
    
    private final static double DIFFICULTY_OFFSET = 0.1;
    private final static int PLAYER_LIFE = 3;
    public final static Double GRAVITY = 0.09;
    
    
    //game info
    private static GameStatus gameStatus;
    private GameLevel currentLevel ;
    public static double gameDifficulty;
   
    //player info
    private int score;
    protected int currentLives;
    
    //level
    protected levelManager levelManager;
    
    protected static List<? extends Stair> stairs;
    protected static List<? extends FloorTile> floor;
    
    public ModelImpl() {
        this.score = 0;
        this.currentLives = PLAYER_LIFE;
        gameDifficulty = 1;
        levelManager = new levelManager();
        setCurrentLevel(levelManager.getNextLevel());
    }
    
    @Override
    public int getScore() {
        return this.score;
    }
    
    public void updateScore(int score) {
        this.score = this.getScore()+score;
    }
    
    @Override
    public int getLife() {
        return this.currentLives;
    }
    
    protected GameLevel getCurrentLevel() {
        return currentLevel;
    }
    
    public void setCurrentLevel(GameLevel currentLevel) {
        this.currentLevel = currentLevel;
    }
    
    /**
     * The function that checks collisions between all the entities.
     * 
     */
    protected abstract void checkCollisions();
    
    //TODO to watch later, could be deleted
    /**
     * The function that checks if the given entity is within the game borders.
     * 
     */
    public static Optional<Movement> borderCheck(final DynamicEntity entity) {
        if (entity.getHitbox().getMaxX() > WIDTH) {
            return Optional.of(Movement.RIGHT);
        }
        else if(entity.getHitbox().getMaxY() > HEIGHT){
            return Optional.of(Movement.DOWN);
        }
        else if(entity.getX() < 0 ){
            return Optional.of(Movement.LEFT);
        }
        else if(entity.getY() < 0) {
            return Optional.of(Movement.UP);
        }
        else {
            return Optional.empty();
        }
    }
    
    public GameStatus getGameStatus() {
        return gameStatus;
    }
    
    public void start() {
        gameStatus = GameStatus.Running;
    }
    public static GameStatus isRunning() {
        return gameStatus;
    }
    
    public void pause() {
        gameStatus = GameStatus.Pause;
    }
    
    public void gameOver() {
        gameStatus = GameStatus.Over ;
    }
    
    public void victory() {
        if(levelManager.isLast()) {
            updateGameDifficulty();
        }
        setCurrentLevel(levelManager.getNextLevel());
        setGameStatus(GameStatus.Won);
    }
    
    protected void setGameStatus(GameStatus currentStatus) {
        gameStatus = currentStatus;
    }
    
    public Boolean isOver() {
        return this.getLife() <= 0;
    }
    
    private void updateGameDifficulty() {
        gameDifficulty = gameDifficulty + DIFFICULTY_OFFSET;
    }
    
    /**
     * The function that check if the given entity can climb down the stairs.
     * 
     * @param entity
     *          the DynamicEntity that is making the request.
     *          
     * @return a boolean, true if can, false otherwise
     */
    public static boolean canClimbDown(final DynamicEntity entity) {
        if(entity == null) {
        }
        return stairs.stream()
                .filter(S -> 
                    (entity.isColliding(S.getUpperTriggerR()) 
                            && entity.isColliding(S.getUpperTriggerL()))
                    && entity.getHitbox().getMaxY()
                    == S.getUpperTriggerL().getHitbox().getMaxY())
                .findFirst()
                .isPresent()
                    ? true : false;
    }
    
    /**
     * The function that check if the given entity can climb up the stairs.
     * 
     * @param entity
     *          the DynamicEntity that is making the request.
     *          
     * @return a boolean, true if can, false otherwise
     */
    public static boolean canClimbUp(final DynamicEntity entity) {
        return stairs.stream()
                .filter(S -> 
                    entity.isColliding(S.getTriggerL())
                            && entity.isColliding(S.getTriggerR())
                    && entity.getHitbox().getMaxY()
                    == S.getHitbox().getMaxY())
                .findFirst()
                .isPresent()
                    ? true : false;
    }
}
