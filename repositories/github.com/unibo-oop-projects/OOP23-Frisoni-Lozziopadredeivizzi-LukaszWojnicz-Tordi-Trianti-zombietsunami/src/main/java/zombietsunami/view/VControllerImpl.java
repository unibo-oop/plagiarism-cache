package zombietsunami.view;

import java.util.List;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import zombietsunami.api.Controller;
import zombietsunami.Pair;
import zombietsunami.view.api.VController;

/**
 * Implementation of the VController interface for controlling zombies in the
 * Zombie Tsunami game.
 */
public final class VControllerImpl implements VController {

    private Controller control;

    @Override
    @SuppressFBWarnings(justification = "The controller fild of this class"
    + "must be the same as the one passed in the method, that will "
    + "change and get different results.")
    public void set(final Controller c) {
        this.control = c;

        View.start(this, getScreenWC(), getScreenHC());
    }

    @Override
    public int getScreenWC() {
        return this.control.getScreenWidth();
    }

    @Override
    public int getScreenHC() {
        return this.control.getScreenHigh();
    }

    @Override
    public int getFPSC() {
        return this.control.getFPS();
    }

    /**
     * Gets the x-coordinate of the zombie in the game map.
     *
     * @return The x-coordinate of the zombie.
     */
    @Override
    public int getZombieMapX() {
        return this.control.getZombieMapX();
    }

    /**
     * Gets the y-coordinate of the zombie in the game map.
     *
     * @return The y-coordinate of the zombie.
     */
    @Override
    public int getZombieMapY() {
        return this.control.getZombieMapY();
    }

    @Override
    public List<String> tileElementsC() {
        return this.control.tileElements();
    }

    @Override
    public int screenColC() {
        return this.control.getScreenCol();
    }

    @Override
    public int screenRowC() {
        return this.control.getScreenRow();
    }

    @Override
    public int titleSizeC() {
        return this.control.getTitleSize();
    }

    @Override
    public List<Pair<Integer, Integer>> screenTilePosC() {
        return this.control.screenTilePos();
    }

    @Override
    public List<Integer> mapIndexListC() {
        return this.control.mapList();
    }

    /**
     * Updates the position and state of the zombie in the game.
     */
    @Override
    public void updateZombie() {
        this.control.updateZombie();
    }

    @Override
    public int getWorldColC() {
        return this.control.getWorldCol();
    }

    @Override
    public int getWorldRowC() {
        return this.control.getWorldRow();
    }

    @Override
    public int getWorldWidthC() {
        return this.control.getWorldWidth();
    }

    @Override
    public int getWorldHightC() {
        return this.control.getWorldHight();
    }

    @Override
    public void setPersonFromMapC() {
        this.control.setPersonFromMap();
    }

    @Override
    public List<Integer> getPersonListC() {
        return this.control.getPersonList();
    }

    @Override
    public List<Integer> personIndexListC() {
        return this.control.personList();
    }

    /**
     * Gets the strength of the zombie.
     *
     * @return The strength of the zombie.
     */
    @Override
    public int getStrength() {
        return this.control.getStrength();
    }

    /**
     * Gets the x-coordinate of the zombie on the screen.
     *
     * @return The x-coordinate of the zombie on the screen.
     */
    @Override
    public int getZombieScreenX() {
        return this.control.getZombieScreenX();
    }

    /**
     * Gets the y-coordinate of the zombie on the screen.
     *
     * @return The y-coordinate of the zombie on the screen.
     */
    @Override
    public int getZombieScreenY() {
        return this.control.getZombieScreenY();
    }

    /**
     * Signals that the jump action has been initiated for the zombie.
     */
    @Override
    public void jumpPress() {
        this.control.jumpPress();
    }

    /**
     * Updates the jump action for the zombie.
     */
    @Override
    public void updateJumpZombie() {
        this.control.updateJumpZombie();
    }

    /**
     * Checks if the zombie is currently jumping.
     *
     * @return true if the zombie is jumping, false otherwise.
     */
    @Override
    public boolean isJumping() {
        return this.control.isJumping();
    }

    @Override
    public List<Integer> obstacleIndexListC() {
        return this.control.obstacleList();
    }

    @Override
    public void setEndPosC(final int endX) {
        this.control.setEndPos(endX);
    }

    @Override
    public boolean isWinC() {
        return this.control.isWin();
    }

    @Override
    public void fillBombListFromMapC() {
        this.control.fillBombListFromMap();
    }

    @Override
    public void fillBreakableListFromMapC() {
        this.control.fillBreakableListFromMap();
    }

    @Override
    public void collisionZombieOstacleC() {
        this.control.collisionZombieObstacle();
    }

    @Override
    public boolean isGameOver() {
        return this.control.isGameOver();
    }

    @Override
    public void collisionZombiePersonsC() {
        this.control.collisionZombiePersons();
    }

    @Override
    public boolean isNotEnoughC() {
        return this.control.isNotEnough();
    }

    @Override
    public boolean isStrenghtZeroC() {
        return this.control.isStrenghtZero();
    }
}
