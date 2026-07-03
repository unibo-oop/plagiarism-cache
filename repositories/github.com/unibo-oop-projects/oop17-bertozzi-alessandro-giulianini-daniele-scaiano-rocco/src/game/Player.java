package game;

import java.awt.Rectangle;
import utilities.Clamp;
import utilities.Pair;


/** 
 *creating the player class.
 */
public final class Player extends Entity implements PlayerInterface {

    private static final int DAMAGE_MULTIPLIER = 2;
    private static final int SHOT_DAMAGE = 10;
    private static final int WIDTH = GameImpl.GAMEAREA_WIDTH / 20;
    private static final int HEIGHT = WIDTH + WIDTH / 100;
    private static final int DEFINITLY_NOT_SHOT_COOLDOWN = 20;
    private int health = 100;
    private int shootCD = DEFINITLY_NOT_SHOT_COOLDOWN;
    private int shotReadyIn;
    private int shield;

    private final GameImpl game;

    /**
     * standard constructor for player class.
     * @param id id of the player
     * @param game game this player is bounded with 
     */
    public Player(final ID id, final GameImpl game) {
        super(new Pair<Integer, Integer>(GameImpl.GAMEAREA_WIDTH / 2, GameImpl.GAMEAREA_HEIGHT / 4 * 3), 0, 0, id);
        if (id == ID.PLAYER2) {
            this.getPosition().setX(GameImpl.GAMEAREA_WIDTH / 2);
            this.getPosition().setY(GameImpl.GAMEAREA_HEIGHT / 4);
        }
        this.game = game;
        setHitbox(new Rectangle(this.getPosition().getX(), this.getPosition().getY(), WIDTH, HEIGHT));
    }

    /**
     * does stuffs every time it's called.
     */
    public void update() {
        this.getPosition().setX(this.getPosition().getX() + this.getVelocity().getX());
        this.getPosition().setY(this.getPosition().getY() + this.getVelocity().getY());
        this.getPosition().setX(Clamp.clamp(this.getPosition().getX(), WIDTH / 2, GameImpl.GAMEAREA_WIDTH - WIDTH / 2));
        if (game.getMode() == GameMode.MULTIPLAYER) {
            if (this.getID() == ID.PLAYER) {
                this.getPosition().setY(Clamp.clamp(this.getPosition().getY(), GameImpl.GAMEAREA_HEIGHT / 2 + HEIGHT / 2, GameImpl.GAMEAREA_HEIGHT - HEIGHT / 2));

            } else {
                this.getPosition().setY(Clamp.clamp(this.getPosition().getY(), HEIGHT / 2, GameImpl.GAMEAREA_HEIGHT / 2 - HEIGHT / 2));
            }
        } else {
            this.getPosition().setY(Clamp.clamp(this.getPosition().getY(), HEIGHT / 2, GameImpl.GAMEAREA_HEIGHT - HEIGHT / 2));
        }
        this.setHitbox(new Rectangle(this.getPosition().getX() - WIDTH / 2, this.getPosition().getY() - HEIGHT / 2, WIDTH, HEIGHT));
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public void collide(final GameObject entity) {
        if (game.getMode() == GameMode.SINGLEPLAYER) {
            if (this.shield == 0) {
                this.health -= DAMAGE_MULTIPLIER * game.getLevel();
            } else if (this.shield > 0 && this.shield < DAMAGE_MULTIPLIER * game.getLevel()) {
                final int tempDamageOverShieldValue = DAMAGE_MULTIPLIER * game.getLevel() - this.shield;
                this.health -= tempDamageOverShieldValue;
                this.shield = 0;
            } else {
                this.shield -= DAMAGE_MULTIPLIER * game.getLevel();
            }
        } else {
            if (this.shield == 0) {
                this.health -= SHOT_DAMAGE;
            } else if (this.shield > 0 && this.shield < SHOT_DAMAGE) {
                final int tempDamageOverShieldValue = SHOT_DAMAGE - this.shield;
                this.health -= tempDamageOverShieldValue;
                this.shield = 0;
            } else {
                this.shield -= SHOT_DAMAGE;
            }
        }
        if (this.health <= 0) {
            this.setDead();
        }
    }

    @Override
    public void resetPosition() {
        this.setPosition(new Pair<Integer, Integer>(GameImpl.GAMEAREA_WIDTH / 2, GameImpl.GAMEAREA_HEIGHT / 2));
    }

    @Override
    public void setHealth(final int hps) {
        this.health = hps;
    }

    @Override
    public void setShotCD(final int cd) {
        this.shootCD = cd;
    }

    @Override
    public int getShotCD() {
        return this.shootCD;
    }

    @Override
    public void setShield(final int shieldAmount) {
        this.shield = shieldAmount;
    }

    @Override
    public int getShield() {
        return this.shield;
    }

    @Override
    public void setShotReadyIn(final int time) {
        this.shotReadyIn = time;
    }

    @Override
    public int getShotReadyIn() {
        return this.shotReadyIn;
    }
}
