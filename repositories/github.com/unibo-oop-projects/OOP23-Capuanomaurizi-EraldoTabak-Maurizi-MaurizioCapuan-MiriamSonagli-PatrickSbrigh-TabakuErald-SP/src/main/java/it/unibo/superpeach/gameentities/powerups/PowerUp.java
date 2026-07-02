package it.unibo.superpeach.gameentities.powerups;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Optional;

import it.unibo.superpeach.game.Game;
import it.unibo.superpeach.gameentities.GameObject;
import it.unibo.superpeach.gameentities.blocks.Block;
import it.unibo.superpeach.gameentities.blocks.BlockType;
import it.unibo.superpeach.gameentities.blocks.BlocksHandler;
import it.unibo.superpeach.graphics.Texturer;

/**
 * Power up class implementation.
 * 
 * @author Miriam Sonaglia
 */
public abstract class PowerUp implements GameObject {

    /**
     * eunum relative with all the power up types.
     */
    public enum PowerUpType {
        /**
         * Power up RED MUSHROOM.
         */
        RED_MUSHROOM,
        /**
         * Power up STAR.
         */
        STAR,
        /**
         * Power up LIFE MUSHROOM.
         */
        LIFE_MUSHROOM,
        /**
         * Power up COIN.
         */
        COIN
    }

    private static final int FALL_SPEED = 1;

    private int x;
    private int y;
    private int width;
    private int height;
    private int scale;
    private int movement;
    private boolean isFalling;
    private boolean isAlive;
    private boolean direction;
    private final PowerUpType powerUpType;
    private Optional<BlocksHandler> blocksHandler;

    private final int paddingBound;
    private Texturer texturer = Game.getTexturer();
    private Optional<BufferedImage[]> image = Optional.of(texturer.getPowerups());

    /**
     * Power ups constructor.
     * 
     * @param x
     * @param y
     * @param w
     * @param h
     * @param s
     * @param blocksHandler
     * @param type
     */
    public PowerUp(final int x, final int y, final int w, final int h, final int s,
            final BlocksHandler blocksHandler, final PowerUpType type) {
        this.x = x * s;
        this.y = y * s;
        this.width = w * s;
        this.height = h * s;
        this.scale = s;
        this.isFalling = false;
        this.isAlive = true;
        this.direction = false;
        this.powerUpType = type;
        this.blocksHandler = Optional.of(blocksHandler);
        this.paddingBound = 4 * scale;
    }

    @Override
    public abstract void render(Graphics g);

    @Override
    public abstract void tick();

    @Override
    public final Texturer getTexturer() {
        return texturer;
    }

    /**
     * sets the texture.
     * 
     * @param texturer
     */
    public final void setTextures(final Texturer texturer) {
        this.texturer = texturer;
    }

    @Override
    public final BufferedImage[] getSprites() {
        return this.image.get();
    }

    @Override
    public final void setSprites(final BufferedImage[] image) {
        this.image = Optional.of(image);
    }

    @Override
    public final void setX(final int x) {
        this.x = x * scale;
    }

    @Override
    public final void setY(final int y) {
        this.y = y * scale;
    }

    @Override
    public final void setWidth(final int w) {
        this.width = w;
    }

    @Override
    public final void setHeight(final int h) {
        this.height = h;
    }

    /**
     * @return power up type between:RED MUSHROOM, LIFE MUSHROOM, STAR and COIN.
     */
    public final PowerUpType getPowerUpType() {
        return powerUpType;
    }

    @Override
    public final int getX() {
        return this.x;
    }

    @Override
    public final int getY() {
        return this.y;
    }

    @Override
    public final int getWidth() {
        return this.width;
    }

    @Override
    public final int getHeight() {
        return this.height;
    }

    @Override
    public final int getScale() {
        return this.scale;
    }

    @Override
    public final void setScale(final int scale) {
        this.scale = scale;
    }

    /**
     * Sets the speed according to the GUI scale selected.
     * 
     * @param movement
     */
    public final void setMovement(final int movement) {
        this.movement = movement * scale;
    }

    /**
     * @return speed of the power up
     */
    public final int getMovement() {
        return this.movement;
    }

    /**
     * Sets if the power up is falling off a block or not.
     * 
     * @param fall
     */
    public final void setIsFalling(final boolean fall) {
        this.isFalling = fall;
    }

    /**
     * @return if the power up is falling or not.
     */
    public final boolean isFalling() {
        return this.isFalling;
    }

    /**
     * @return speed of the falling speed of the power up
     */
    public final int getFallSpeed() {
        return movement;
    }

    /**
     * @return the direction in which the power up is moving
     */
    public final boolean isDirectionLeft() {
        return this.direction;
    }

    /**
     * Changes the direction of the power up.
     */
    public final void changeDirection() {
        this.direction = !this.direction;
    }

    /**
     * Updates the coordinates of the power up
     * according to the direction and the state.
     */
    public final void updateCoords() {
        if (isDirectionLeft()) {
            this.x -= this.movement;
        } else {
            this.x += this.movement;
        }

        if (this.isFalling) {
            this.y += FALL_SPEED * scale;
        }
    }

    /**
     * @return if the power up is alive or not
     */
    public final boolean isAlive() {
        return this.isAlive;
    }

    /**
     * the power up dies.
     */
    public final void die() {
        this.isAlive = false;
    }

    @Override
    public final Rectangle getBoundingBox() {
        return new Rectangle(x, y, width, height);
    }

    /**
     * @return the botton bound of the power up.
     */
    public final Rectangle getBottomBound() {
        return new Rectangle(getX() + getWidth() / 2 - getWidth() / 4,
                getY() + (getHeight() - paddingBound),
                getWidth() / 2, paddingBound);
    }

    /**
     * @return the left bound of the power up
     */
    public final Rectangle getLeftBound() {
        return new Rectangle(getX(), getY() + paddingBound, paddingBound, getHeight() - 2 * paddingBound);
    }

    /**
     * @return the right bound of the power up
     */
    public final Rectangle getRightBound() {
        return new Rectangle(getX() + getWidth() - paddingBound, getY() + paddingBound, paddingBound,
                getHeight() - 2 * paddingBound);
    }

    /**
     * @return the padding bound of the power up.
     */
    public final int getPaddingBound() {
        return paddingBound;
    }

    /**
     * @return the blocks handler.
     */
    public final BlocksHandler getBlocksHandler() {
        return blocksHandler.get();
    }

    /**
     * sets the blocks handler.
     * 
     * @param blocksHandler
     */
    public final void setBlocksHandler(final BlocksHandler blocksHandler) {
        this.blocksHandler = Optional.of(blocksHandler);
    }

    /**
     * When the power ups collides with something on the bottom bound,
     * it doesn't fall.
     * 
     * @param block
     */
    private void setYCollisionBottom(final Block block) {
        setY(block.getY() / scale - getHeight() / scale);
        setIsFalling(false);
    }

    /**
     * When the power ups collides with something on the left bound,
     * it changes direction.
     * 
     * @param block
     */
    private void setXCollisionLeft(final Block block) {
        setX((block.getX() + block.getWidth()) / getScale());
        changeDirection();
    }

    /**
     * When the power ups collides with something on the right bound,
     * it changes direction.
     * 
     * @param block
     */
    private void setXCollisionRight(final Block block) {
        setX((block.getX() - getWidth()) / getScale());
        changeDirection();
    }

    /**
     * It handles the collisions between the power up and the blocks.
     */
    public final void collisions() {
        for (final Block block : blocksHandler.get().getBlocks()) {
            if (block.getType() == BlockType.DEATH_BLOCK && block.getBoundingBox().intersects(getBottomBound())) {
                die();
            }
            if (block.getType() == BlockType.PIPE_LEFT || block.getType() == BlockType.PIPE_RIGHT
                    || block.getType() == BlockType.PIPE_TOP_LEFT || block.getType() == BlockType.PIPE_TOP_RIGHT
                    || block.getType() == BlockType.STONE || block.getType() == BlockType.TERRAIN
                    || block.getType() == BlockType.POPPED_LUCKY || block.getType() == BlockType.LUCKY
                    || block.getType() == BlockType.BRICK) {
                if (block.getBoundingBox().contains(getLeftBound())
                        || block.getBoundingBox().intersects(getLeftBound())) {
                    setXCollisionLeft(block);
                } else if (block.getBoundingBox().contains(getRightBound())
                        || block.getBoundingBox().intersects(getRightBound())) {
                    setXCollisionRight(block);
                } else if (block.getBoundingBox().contains(getBottomBound())
                        || block.getBoundingBox().intersects(getBottomBound())) {
                    setYCollisionBottom(block);
                }
            }
        }
    }

}
