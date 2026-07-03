package breakout.model;

import java.util.Arrays;
import breakout.model.entities.Brick;
import breakout.model.entities.BrickType;
import breakout.model.entities.Wall;
import breakout.model.entities.Wall.WallPos;
import breakout.model.levels.BasicLevel;

/**
 * Implementation of the Classic BreakOut game.
 * 
 */
public class ClassicMode extends AbstractModel {

    private static final int FIRST_STEP_SPEED = 4;
    private static final int SECOND_STEP_SPEED = 12;

    private int hitsCounter; // Counts the ball's hit on bricks
    private boolean fivePointHit;
    private boolean sevenPointHit;
    private boolean topWallhit;
    private int levelCount = 1;

    /**
     * Constructor for a classic model.
     * 
     * @param lvl
     *            lvl
     */
    public ClassicMode(final BasicLevel lvl) {
        super(Arrays.asList(lvl));
        this.hitsCounter = 0;
        this.fivePointHit = false;
        this.sevenPointHit = false;
        this.topWallhit = false;
    }

    @Override
    public void updateAll(final double time) {
        super.updateAll(time);
        switch (this.getGameStatus()) {
        case Dead:
            this.hitsCounter = 0;
            this.topWallhit = false;
            break;
        case Won:
            this.hitsCounter = 0;
            this.fivePointHit = false;
            this.sevenPointHit = false;
            this.topWallhit = false;
            this.levelCount++;
            super.pause();
        default:
            break;

        }

    }

    @Override
    protected void gameUpdateOnBrickCollision(final Brick brick) {
        this.hitsCounter++;
        if (this.hitsCounter == FIRST_STEP_SPEED || this.hitsCounter == SECOND_STEP_SPEED) {
            this.speedUP();
        }
        if (brick.getType().equals(BrickType.FIVE_CLASSIC) && !this.fivePointHit
                || brick.getType().equals(BrickType.SEVEN_CLASSIC) && !this.sevenPointHit) {
            this.speedUP();
            this.fivePointHit = true;
            this.sevenPointHit = true;
        }
    }

    @Override
    protected void gameUpdateOnWallCollision(final Wall wall) {
        if (wall.getWorldPosition().equals(WallPos.UP) && !this.topWallhit) {
            this.shortPaddle();
            this.topWallhit = true;
        }
    }

    @Override
    protected void gameUpdateOnPaddleCollision() {
    }

    /**
     * Increases the speed of a quarter of the current speed.
     */
    private void speedUP() {
        final double currentSpeed = this.getBalls().get(0).getSpeed();
        this.getBalls().stream().forEach(b -> b.setSpeed(currentSpeed + currentSpeed / 4));
    }

    /**
     * Decreases by a quarter the paddle.
     */
    private void shortPaddle() {
        final double currentWidth = this.getPaddle().getWidth();
        this.getPaddle().setBounds(currentWidth - currentWidth / 4, this.getPaddle().getHeight());
    }

    @Override
    protected GameObjectFactory makeFactory() {
        return ClassicFactory.get();
    }

    /**
     * @return the number of levels completed
     */
    public int getLevelCount() {
        return this.levelCount;
    }

}
