package zombietsunami.model;

import java.util.List;

import zombietsunami.api.Collision;
import zombietsunami.api.MightWin;
import zombietsunami.api.GameOver;
import zombietsunami.api.Model;
import zombietsunami.Pair;
import zombietsunami.model.mapmodel.api.GameMap;
import zombietsunami.model.mapmodel.impl.GameMapImpl;
import zombietsunami.model.obstaclemodel.api.ObstacleManager;
import zombietsunami.model.obstaclemodel.impl.ObstacleManagerImpl;
import zombietsunami.model.zombiemodel.api.Zombie;
import zombietsunami.model.zombiemodel.impl.ZombieImpl;
import zombietsunami.model.personmodel.api.PersonsManager;
import zombietsunami.model.personmodel.impl.PersonsManagerImpl;

/**
 * This class is the Model of the MVC and implements the Model interface
 * {@link zombietsunami.api.Model}.
 */
public final class ModelImpl implements Model {

    private final GameMap gameMap;
    private final Zombie zombie;
    private final PersonsManager personsManager;
    private final MightWin win;
    private final GameOver gameOver;
    private final ObstacleManager obstacleManager;
    private final Collision collisionManager;

    /**
     * Allows to set the different elements belonging to the Model.
     */
    public ModelImpl() {
        this.gameMap = new GameMapImpl();
        this.zombie = new ZombieImpl();
        this.personsManager = new PersonsManagerImpl();
        this.win = new MightWinImpl();
        this.gameOver = new GameOverImpl();
        this.obstacleManager = new ObstacleManagerImpl();
        this.collisionManager = new CollisionImpl();
    }

    @Override
    public void fillBombListFromMap() {
        this.obstacleManager.fillBombListFromMap(gameMap.getLoadedObstacleList(), getScreenTilePos(), zombie.getStrength());
    }

    @Override
    public void updateZombie() {
        this.zombie.update();
    }

    @Override
    public List<Integer> getMapList() {
        return this.gameMap.getLoadedMapList();
    }

    @Override
    public List<String> getTileElem() {
        return this.gameMap.getTileElem();
    }

    @Override
    public List<Pair<Integer, Integer>> getScreenTilePos() {
        return this.gameMap.getScreenTilePos(MapData.getMaxWorldRow(), MapData.getMaxWorldCol(),
                MapData.getTitSize(),
                getZombieMapX(), getZombieMapY(), this.zombie.getScreenX(), this.zombie.getScreenY());
    }

    @Override
    public int getZombieMapX() {
        return this.zombie.getX();
    }

    @Override
    public int getZombieMapY() {
        return this.zombie.getY();
    }

    @Override
    public int getSpeed() {
        return this.zombie.getSpeed();
    }

    @Override
    public List<Integer> getPersonList() {
        return this.gameMap.getLoadedPersonList();
    }

    @Override
    public void setPersonFromMap() {
        this.personsManager.setPersonFromMap(gameMap.getLoadedPersonList(), getScreenTilePos(), zombie.getStrength());
    }

    @Override
    public void collisionZombiePersons() {
        this.collisionManager.collisionZombiePersons(personsManager.getPersonList(), MapData.getTitSize(), this.zombie,
                gameMap);
    }

    @Override
    public int getStrength() {
        return this.zombie.getStrength();
    }

    @Override
    public int getZombieScreenX() {
        return this.zombie.getScreenX();
    }

    @Override
    public int getZombieScreenY() {
        return this.zombie.getScreenY();
    }

    @Override
    public void jumpPress() {
        this.zombie.jumpPress();
    }

    @Override
    public void updateJumpZombie() {
        this.zombie.updateJumpZombie();
    }

    @Override
    public boolean isJumping() {
        return this.zombie.isJumping();
    }

    @Override
    public List<Integer> getObstacleList() {
        return this.gameMap.getLoadedObstacleList();
    }

    @Override
    public void setEndPos(final int endX) {
        this.win.setEndPos(endX);
    }

    @Override
    public boolean isWin() {
        return this.win.isWin();
    }

    @Override
    public void fillBreakableListFromMap() {
        this.obstacleManager.fillBreakableListFromMap(gameMap.getLoadedObstacleList(), getScreenTilePos(), zombie.getStrength());
    }

    @Override
    public boolean isGameOver() {
        return this.collisionManager.isGameOver();
    }

    @Override
    public boolean isNotEnough() {
        return this.gameOver.isNotEnough(isGameOver());
    }

    @Override
    public boolean isStrenghtZero() {
        return this.gameOver.isStrenghtZero(getStrength());
    }

    @Override
    public void collisionZombieObstacle() {
        this.collisionManager.collisionZombieObstacle(obstacleManager.getBombList(), 
            obstacleManager.getBreakableList(), MapData.getTitSize(), zombie, gameMap);
    }

}
