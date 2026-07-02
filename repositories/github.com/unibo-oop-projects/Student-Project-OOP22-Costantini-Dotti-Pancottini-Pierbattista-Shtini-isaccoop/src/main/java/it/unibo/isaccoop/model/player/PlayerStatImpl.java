package it.unibo.isaccoop.model.player;

import it.unibo.isaccoop.graphics.GraphicsComponent;
import it.unibo.isaccoop.model.common.AbstractMapElement;

/**
 * Implement the interface PlayerStat.
 * */
public class PlayerStatImpl extends AbstractMapElement implements PlayerStat {

    /**
     * the player hearts.
     * */
    private int heart;

    /**
     * the player coin for shop.
     * */
    private int coin;

    /**
     * the player can increase the max number of available heart with power up.
     * */
    private int maxHeart;

    /**
     * the player speed in the game.
     * */
    private Double speed;

    /**
     * the time between two hit.
     * */
    private Double tears;

    /**
     * the damage that player deals.
     * */
    private Double damage;

    /**
     * create an enum to set initial stats of player.
     * */
    private enum PlayerValue {

        HEART(3),
        COIN(0),
        MAX_HEART(3),
        SPEED(1),
        TEARS(1000),
        DAMAGE(1);

        private int value;

        /**
         * @param value
         * set the value.
         * */
         PlayerValue(final int value) {
            this.value = value;
        }

        /**
         * @return the value of enum value.
         * */
        public int getValue() {
            return this.value;
        }
    }

    /**
     * Player constructor.
     * @param gr
     * */
    public PlayerStatImpl(final GraphicsComponent gr) {
        super(ElementsRadius.PLAYER, gr);
        this.heart = PlayerValue.HEART.getValue();
        this.coin = PlayerValue.COIN.getValue();
        this.maxHeart = PlayerValue.MAX_HEART.getValue();
        this.speed = Double.valueOf(PlayerValue.SPEED.getValue());
        this.tears = Double.valueOf(PlayerValue.TEARS.getValue());
        this.damage = Double.valueOf(PlayerValue.DAMAGE.getValue());
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public int getHeart() {
        return this.heart;
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public int getMaxHeart() {
        return this.maxHeart;
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public int getCoin() {
        return this.coin;
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public Double getSpeed() {
        return this.speed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double getDamage() {
        return this.damage;
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public Double getTears() {
        return this.tears;
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public void setHeart(final int heart) {
        this.heart = heart;
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public void setMaxHeart(final int maxHeart) {
        this.maxHeart = maxHeart;
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public void setCoin(final int coin) {
        this.coin = coin;
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public void setSpeed(final Double speed) {
        this.speed = speed;
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public void setTears(final Double tears) {
        if (tears < 100.0) {
            this.tears = 100.0;
        }
        this.tears = tears;
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public void setDamage(final Double damage) {
        this.damage = damage;
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public boolean isDead() {
        return this.heart <= 0;
    }

    /**
     * Get if the player is hitted or not.
     * @return if the player has exhausted the hearts.
     * */
    public boolean isHitted() {
        if (this.getHeart() >= 1) {
            this.setHeart(this.getHeart() - 1);
        }
        return this.isDead();
    }
}
