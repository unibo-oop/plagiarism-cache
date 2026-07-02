package controller.event;

import controller.game.GameState;
import model.entities.PowerUp;
import model.leaderboard.BasicLifeOperationStrategy;
import model.leaderboard.BasicScoreOperationStrategy;
import model.leaderboard.LifeOperationStrategy;
import model.leaderboard.ScoreOperationStrategy;
import model.utilities.PowerUpTimer;
import model.utilities.PowerUpType;
import model.utilities.PowerUpUtilities;
import model.utilities.ScoreAttribute;

public class PowerUpController {
    private int ballDamage;
    private final PowerUp pwup;
    private final GameState state;
    private final LifeOperationStrategy lifeOperation;
    private final ScoreOperationStrategy scoreOperation;

    public PowerUpController(final PowerUp pwup, final GameState state) {
        this.pwup = pwup;
        this.state = state;
        this.lifeOperation = new BasicLifeOperationStrategy();
        this.scoreOperation = new BasicScoreOperationStrategy();
        this.setBallDamage(PowerUpUtilities.BALL_DAMAGE);
    }


    /**
     * activates the powerup.
     * @param pwup powerup that needs to be activated.
     */
    public void activatePowerUp(final PowerUp pwup) {
        if (pwup.getPowerUpType().equals(PowerUpType.DAMAGE_DOWN)) {
            addPoints(ScoreAttribute.NEGATIVE_POWERUP.getValue());
            this.setBallDamage(PowerUpUtilities.DEFAULT_BALL_DAMAGE + pwup.getDamageModifier());
            waitSeconds(pwup.getActiveTime(), pwup);
        } else if (pwup.getPowerUpType().equals(PowerUpType.DAMAGE_UP)) {
            addPoints(ScoreAttribute.POSITIVE_POWERUP.getValue());
            this.setBallDamage(PowerUpUtilities.DEFAULT_BALL_DAMAGE + pwup.getDamageModifier());
            waitSeconds(pwup.getActiveTime(), pwup);
        } else if (pwup.getPowerUpType().equals(PowerUpType.LIFE_DOWN)) {
            addPoints(ScoreAttribute.NEGATIVE_POWERUP.getValue());
            state.getPlayer().lifeOperation(lifeOperation, pwup.getLifeModifier());
        } else if (pwup.getPowerUpType().equals(PowerUpType.LIFE_UP)) {
            addPoints(ScoreAttribute.POSITIVE_POWERUP.getValue());
            state.getPlayer().lifeOperation(lifeOperation, pwup.getLifeModifier());
        } else if (pwup.getPowerUpType().equals(PowerUpType.SPEED_DOWN)) {
            addPoints(ScoreAttribute.POSITIVE_POWERUP.getValue());
            this.state.getBoard().getBalls().forEach(e -> e.setSpeed(e.getSpeed() + pwup.getSpeedModifier()));
            waitSeconds(pwup.getActiveTime(), pwup);
        } else if (pwup.getPowerUpType().equals(PowerUpType.SPEED_UP)) {
            addPoints(ScoreAttribute.NEGATIVE_POWERUP.getValue());
            this.state.getBoard().getBalls().forEach(e -> e.setSpeed(e.getSpeed() + pwup.getSpeedModifier()));
            waitSeconds(pwup.getActiveTime(), pwup);
        } 
    }

    /**
     * adds points to the player's score.
     * @param value value to be added to the player's score
     */
    private void addPoints(final int value) {
        this.state.getPlayer().scoreOperation(scoreOperation, value);
    }



    /**
     * deactivates the powerup.
     * @param pwup to be deactivated
     */
    public void deactivatePowerUp(final PowerUp pwup) {
        if (pwup.getPowerUpType().equals(PowerUpType.DAMAGE_DOWN) 
                ||  pwup.getPowerUpType().equals(PowerUpType.DAMAGE_UP)) {
            this.setBallDamage(PowerUpUtilities.DEFAULT_BALL_DAMAGE);
        } else if (pwup.getPowerUpType().equals(PowerUpType.SPEED_DOWN) 
                ||  pwup.getPowerUpType().equals(PowerUpType.SPEED_UP)) {
            this.state.getBoard().getBalls().forEach(e -> e.setSpeed(e.getSpeed() - pwup.getSpeedModifier()));
        }
    }

    /**
     * this method is used after powerup activation
     * to waits some seconds.
     * @param seconds amount of seconds to wait
     * @param pwup powerup that needs to wait 
     */
    public void waitSeconds(final long seconds, final PowerUp pwup) {
        new PowerUpTimer(seconds, this);
    }

    /**
     * getter for pwup.
     * @return PowerUp powerup
     */
    public PowerUp getPwup() {
        return this.pwup;
    }

    /**
     * getter for ball damage.
     * @return ballDamage the damage dealt by the ball;
     */
    public int getBallDamage() {
        return ballDamage;
    }

    /**
     * setter for ball damage.
     * @param ballDamage ballDamage to set.
     */
    public void setBallDamage(final int ballDamage) {
        this.ballDamage = ballDamage;
    }

}
