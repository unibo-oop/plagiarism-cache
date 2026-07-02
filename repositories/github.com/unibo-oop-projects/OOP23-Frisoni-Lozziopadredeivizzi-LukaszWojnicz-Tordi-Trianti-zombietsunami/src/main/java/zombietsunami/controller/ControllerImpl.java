package zombietsunami.controller;

import java.util.List;

import zombietsunami.api.Controller;
import zombietsunami.api.Model;
import zombietsunami.Pair;
import zombietsunami.model.MapData;
import zombietsunami.model.ModelImpl;
import zombietsunami.view.VControllerImpl;
import zombietsunami.view.api.VController;

/**
 * This class is the Controller of the MVC and implements the Controller
 * interface
 * {@link zombietsunami.api.Controller}.
 */
public final class ControllerImpl implements Controller {

    private Model model;

    @Override
    public void setModel() {
        this.model = new ModelImpl();
    }

    @Override
    public void setView() {
        final VController viewController = new VControllerImpl();
        viewController.set(this);
    }

    @Override
    public int getScreenCol() {
        return MapData.getMaxScCol();
    }

    @Override
    public int getScreenRow() {
        return MapData.getMaxScRow();
    }

    @Override
    public int getTitleSize() {
        return MapData.getTitSize();
    }

    @Override
    public int getFPS() {
        return MapData.getFPS();
    }

    @Override
    public int getScreenWidth() {
        return MapData.getScreenW();
    }

    @Override
    public int getScreenHigh() {
        return MapData.getScreenH();
    }

    @Override
    public int getZombieMapX() {
        return this.model.getZombieMapX();
    }

    @Override
    public List<Integer> mapList() {
        return this.model.getMapList();
    }

    @Override
    public List<String> tileElements() {
        return this.model.getTileElem();
    }

    @Override
    public List<Pair<Integer, Integer>> screenTilePos() {
        return this.model.getScreenTilePos();
    }

    @Override
    public int getZombieMapY() {
        return this.model.getZombieMapY();
    }

    @Override
    public void updateZombie() {
        this.model.updateZombie();
    }

    @Override
    public int getWorldCol() {
        return MapData.getMaxWorldCol();
    }

    @Override
    public int getWorldRow() {
        return MapData.getMaxWorldRow();
    }

    @Override
    public int getWorldWidth() {
        return MapData.getWorldWidth();
    }

    @Override
    public int getWorldHight() {
        return MapData.getWorldHeight();
    }

    @Override
    public int getStrength() {
        return this.model.getStrength();
    }

    @Override
    public void setPersonFromMap() {
        this.model.setPersonFromMap();
    }

    @Override
    public List<Integer> getPersonList() {
        return this.model.getPersonList();
    }

    @Override
    public List<Integer> personList() {
        return this.model.getPersonList();
    }

    @Override
    public void collisionZombiePersons() {
        this.model.collisionZombiePersons();
    }

    @Override
    public int getZombieScreenX() {
        return this.model.getZombieScreenX();
    }

    @Override
    public int getZombieScreenY() {
        return this.model.getZombieScreenY();
    }

    @Override
    public void jumpPress() {
        this.model.jumpPress();
    }

    @Override
    public void updateJumpZombie() {
        this.model.updateJumpZombie();
    }

    @Override
    public boolean isJumping() {
        return this.model.isJumping();
    }

    @Override
    public List<Integer> obstacleList() {
        return this.model.getObstacleList();
    }

    @Override
    public void setEndPos(final int endX) {
        this.model.setEndPos(endX);
    }

    @Override
    public boolean isWin() {
        return this.model.isWin();
    }

    /**
     * Returns the list of bombs from the map.
     */
    @Override
    public void fillBombListFromMap() {
        this.model.fillBombListFromMap();
    }

    @Override
    public void fillBreakableListFromMap() {
        this.model.fillBreakableListFromMap();
    }

    @Override
    public boolean isGameOver() {
        return this.model.isGameOver();
    }

    @Override
    public boolean isNotEnough() {
        return this.model.isNotEnough();
    }

    @Override
    public boolean isStrenghtZero() {
        return this.model.isStrenghtZero();
    }

    @Override
    public void collisionZombieObstacle() {
        this.model.collisionZombieObstacle();
    }
}
