package breakout.model.entities;

import java.util.EnumSet;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import breakout.model.AdvancedFactory;
import breakout.model.AdvancedMode;
import breakout.model.BallBouncer;
import breakout.model.GameStatus;

//FIXME Powerup timer do not stop when the game is paused
/**
 * Enum with all the possible power up that the player can take during the game.
 */
public enum PowerUpEffect implements PowerUpFunction {

    /**
     * Makes the paddle longer.
     */
    LONG_PADDLE(m -> {
        m.getPaddle().setBounds(m.getPaddle().getWidth() * PowerUpConstants.PADDLE_SCALE_FACTOR,
                m.getPaddle().getHeight());
    }, m -> {
        m.getPaddle().setBounds(m.getPaddle().getWidth() / PowerUpConstants.PADDLE_SCALE_FACTOR,
                m.getPaddle().getHeight());
    }, PowerUpConstants.LIFE_TIME),

    /**
     * Makes the paddle shorter.
     */
    SHORT_PADDLE(m -> {
        m.getPaddle().setBounds(m.getPaddle().getWidth() / PowerUpConstants.PADDLE_SCALE_FACTOR,
                m.getPaddle().getHeight());
    }, m -> {
        m.getPaddle().setBounds(m.getPaddle().getWidth() * PowerUpConstants.PADDLE_SCALE_FACTOR,
                m.getPaddle().getHeight());
    }, PowerUpConstants.LIFE_TIME),

    /**
     * Set the ball on fire.
     */
    FIREBALL(m -> m.getBalls().iterator().forEachRemaining(b -> b.setBounce(BallBouncer.FIRE_BOUNCE)),
             m -> m.getBalls().iterator().forEachRemaining(b -> b.setBounce(BallBouncer.ADVANCED_BOUNCE)), 
             PowerUpConstants.LIFE_TIME),
    /**
     * Increse the ball speed.
     */
    SPEED_UP(m -> m.getBalls().iterator().forEachRemaining(b -> b.setSpeed(b.getSpeed() * PowerUpConstants.SPEED_SCALE_FACTOR)),
             m -> m.getBalls().iterator().forEachRemaining(b -> b.setSpeed(b.getSpeed() * (1 / PowerUpConstants.SPEED_SCALE_FACTOR))), 
             PowerUpConstants.LIFE_TIME),
    /**
     * Decrese the ball speed.
     */
    SPEED_DOWN(m -> m.getBalls().iterator().forEachRemaining(b -> b.setSpeed(b.getSpeed() * (1 / PowerUpConstants.SPEED_SCALE_FACTOR))), 
               m -> m.getBalls().iterator().forEachRemaining(b -> b.setSpeed(b.getSpeed() * PowerUpConstants.SPEED_SCALE_FACTOR)), 
               PowerUpConstants.LIFE_TIME),
    /**
     * Generate three balls.
     */
    THREE_BALL(m -> {
        final Ball mainBall = m.getBalls().get(0);
        while (m.getBalls().size() < 3) {
            final Ball newBall = AdvancedFactory.get().createBall();
            newBall.setSpeed(mainBall.getSpeed());
            newBall.setPosition(mainBall.getPosition().getX(), mainBall.getPosition().getY());
            newBall.getVelocity().setAngle(-(mainBall.getVelocity().getAngle()
                    + new Random().ints(PowerUpConstants.THREE_BALL_MIN_ANGLE, PowerUpConstants.THREE_BALL_MAX_ANGLE)
                                  .findAny().getAsInt()));
            m.addBall(newBall);
        }
    }),

    /**
     * Add 1 life.
     */
    LIFE_UP(m -> m.setLife(m.getLife() + 1)),

    /**
     * Creates projectiles.
     */
    BULLETS(m -> {
        final Runnable timerRunner = () -> {
            final Timer t = new Timer();
            t.schedule(new TimerTask() {
                private int firedAmmo;

                @Override
                public void run() {
                    final GameStatus modelStatus = m.getGameStatus();
                    if (modelStatus.equals(GameStatus.Running)) {
                        firedAmmo++;
                        if ((firedAmmo > PowerUpConstants.MUNITIONS)) {
                            t.cancel();
                        } else {
                            m.bulletSpawn();
                        }
                    } else if (modelStatus.equals(GameStatus.Dead) || modelStatus.equals(GameStatus.Over)) {
                        this.cancel();
                    }
                }
            }, 0, PowerUpConstants.FIRE_RATE);
        };
        final Thread bullets = new Thread(timerRunner);
        bullets.setDaemon(true);
        bullets.start();
    }, m -> { }, PowerUpConstants.LIFE_TIME);

    // __________TO BE RENDERED__________

    /*
     * RandomBounce(m -> m.getBalls().stream().iterator().forEachRemaining(b ->
     * b.setBounce(BallBouncer.RANDOM_BOUNCE)), m ->
     * m.getBalls().stream().iterator().forEachRemaining(b ->
     * b.setBounce(BallBouncer.CLASSIC_BOUNCE)), PowerUpConstants.LIFE_TIMEPowerUpConstants.LIFE_TIME);
     * 
     * ScorePlus100(m -> m.setScore(m.getScore() + 100));
     */

    // ____________________________________

    private final PowerUpFunction function;
    private final PowerUpFunction reverse;
    private final long duration;
    private Timer timer;

    /**
     * Constructor to create a new type of power up.
     * 
     * @param function
     *            the function to be applied on the model when this power up is
     *            activated.
     * @param reverse
     *            the function to be applied on the model when the effect ends.
     * @param duration
     *            the time duration(seconds) of the power up <br/>
     *            <b>note</b>: if the reverse function is not specified the
     *            power up is permanent.
     */
    PowerUpEffect(final PowerUpFunction function, final PowerUpFunction reverse, final int duration) {
        this.function = function;
        this.reverse = reverse;
        this.duration = duration;
    }

    /**
     * Creates a permanent power up.
     * 
     * @param function
     *            the function to be applied on the model when this power up is
     *            activated.
     */
    PowerUpEffect(final PowerUpFunction function) {
        this.function = function;
        this.reverse = m -> {
        };
        this.duration = 1;
    }

    /**
     * This method perform the effect of the power up for a certain amount of
     * time then it ends the effect of the power up.
     */
    @Override
    public void action(final AdvancedMode m) {
        this.function.action(m);
        this.timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                reverse.action(m);
                disable(m);
            }
        }, this.duration * 1000, 1);
    }

    /**
     * Disable the effect of the power up.
     * 
     * @param m
     *            an advance game model
     */
    public void disable(final AdvancedMode m) {
        if (this.timer != null) {
            this.timer.cancel();
        }
        m.removePowerUp(this);
    }

    /**
     * @return All the power up effects with a life time.
     */
    public static EnumSet<PowerUpEffect> getTemporized() {
        return EnumSet.of(PowerUpEffect.LONG_PADDLE, PowerUpEffect.SHORT_PADDLE, PowerUpEffect.SPEED_UP,
                PowerUpEffect.SPEED_DOWN, PowerUpEffect.BULLETS, PowerUpEffect.FIREBALL);
    }

    /**
     * 
     * @return all the permanent power up.
     */
    public static EnumSet<PowerUpEffect> getPermanent() {
        return EnumSet.of(PowerUpEffect.THREE_BALL, PowerUpEffect.LIFE_UP);
    }

    private final class PowerUpConstants {
        private static final int FIRE_RATE = 500;
        private static final float PADDLE_SCALE_FACTOR = 1.5f;
        private static final float SPEED_SCALE_FACTOR = 1.2f;
        private static final int THREE_BALL_MIN_ANGLE = 30;
        private static final int THREE_BALL_MAX_ANGLE = 150;
        private static final int LIFE_TIME = 5;
        private static final int MUNITIONS = 10;
    }
}
