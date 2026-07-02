package it.unibo.superpeach.gameentities.enemies;

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
 * Enemies abstract class.
 * 
 * @author Eraldo Tabaku
 */

public abstract class Enemy implements GameObject {

    private static final int FALL_SPEED = 1;
    private int paddingBOUND = 4;

    private int x;
    private int y;
    private int speed;
    private int scale;
    private int width;
    private int height;
    private boolean isFalling, direction, isAlive;
    private Optional<BlocksHandler> blocksHandler;
    private Texturer texturer = Game.getTexturer();
    private Optional<BufferedImage[]> sprites;

    /**
     * @param x             coordinate of the enemy.
     * @param y             coordinate of the enemy.
     * @param width         of the enemy in game.
     * @param height        of the enemy in game.
     * @param scale         of the game.
     * @param blocksHandler the blockHandler used by the enemy.
     */
    public Enemy(final int x, final int y, final int width, final int height, final int scale,
            final BlocksHandler blocksHandler) {
        this.x = x * scale;
        this.y = y * scale;
        this.scale = scale;
        this.width = width * scale;
        this.height = height * scale;
        this.blocksHandler = Optional.of(blocksHandler);
        this.direction = false;
        this.isAlive = true;
        this.paddingBOUND *= scale;
        this.sprites = Optional.empty();
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
    public final int getScale() {
        return scale;
    }

    /**
     * @return a boolean for the enemy falling or not status
     */
    public boolean isFalling() {
        return this.isFalling;
    }

    /**
     * this method returns the bottom bound rectangle of the enemy.
     * 
     * @return a Rectangle of the Bottom bound of the the enemy.
     */
    public Rectangle getBottomBound() {
        return new Rectangle(getX() + getWidth() / 2 - getWidth() / 4, getY() + (getHeight() - paddingBOUND),
                getWidth() / 2, paddingBOUND);
    }

    /**
     * this method returns the left bound rectangle of the enemy.
     * 
     * @return a rectangle of the left bound of the enemy.
     */
    public Rectangle getLeftBound() {
        return new Rectangle(getX(), getY() + paddingBOUND, paddingBOUND, getHeight() - 2 * paddingBOUND);
    }

    /**
     * this method returns the right bound of the enemy.
     * 
     * @return a rectangle of the right bound of the enemy.
     */
    public Rectangle getRightBound() {
        return new Rectangle(getX() + getWidth() - paddingBOUND, getY() + paddingBOUND, paddingBOUND,
                getHeight() - 2 * paddingBOUND);
    }

    @Override
    public final BufferedImage[] getSprites() {
        return this.sprites.get();
    }

    @Override
    public final Texturer getTexturer() {
        return texturer;
    }

    @Override
    public final int getWidth() {
        return this.width;
    }

    @Override
    public final int getHeight() {
        return this.height;
    }

    /**
     * @return BlockHandler used by the enemy.
     */
    public BlocksHandler getBlocksHandler() {
        return this.blocksHandler.get();
    }

    /**
     * @return enemy's speed.
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * @return enemy's direction.
     */
    public boolean isDirectionLeft() {
        return this.direction;
    }

    @Override
    public final Rectangle getBoundingBox() {
        return new Rectangle(x, y, width, height);
    }

    /**
     * @return boolean indicating enemies isAlive status.
     */
    public boolean isAlive() {
        return this.isAlive;
    }

    /**
     * @param fall to change enemy's status from falling to not falling.
     */
    public void setIsFalling(final boolean fall) {
        this.isFalling = fall;
    }

    @Override
    public final void setSprites(final BufferedImage[] sprites) {
        this.sprites = Optional.of(sprites);
    }

    @Override
    public final void setY(final int y) {
        this.y = y * scale;
    }

    @Override
    public final void setX(final int x) {
        this.x = x * scale;
    }

    @Override
    public final void setWidth(final int width) {
        this.width = width;
    }

    @Override
    public final void setHeight(final int height) {
        this.height = height;
    }

    /**
     * @param speed the speed to give to the enemy.
     */
    public void setSpeed(final int speed) {
        this.speed = speed;
    }

    /**
     * @param isFalling to change enemy's falling status.
     */
    public void setFalling(final boolean isFalling) {
        this.isFalling = isFalling;
    }

    /**
     * @param blocksHandler to set the BlockHandler used by the enemy.
     */
    public void setBlocksHandler(final BlocksHandler blocksHandler) {
        this.blocksHandler = Optional.of(blocksHandler);
    }

    /**
     * sets the scale of the enemy based on the game scale.
     * 
     * @param scale used by the game.
     */
    @Override
    public final void setScale(final int scale) {
        this.scale = scale;
    }

    /**
     * @param texturer used by the enemy for the sprite creation.
     */
    public void setTexturer(final Texturer texturer) {
        this.texturer = texturer;
    }

    /**
     * method used to change enemy's direction.
     */
    public void changeDirection() {
        this.direction = !this.direction;
    }

    /**
     * this method puts the enemy in the dead status.
     */
    public void die() {
        this.isAlive = false;
    }

    private void setYCollisionBottom(final Block block) {
        setY((block.getY() - getHeight()) / getScale());
        setFalling(false);
    }

    private void setXCollisionLeft(final Block block) {
        setX((block.getX() + block.getWidth()) / getScale());
        changeDirection();
    }

    private void setXCollisionRight(final Block block) {
        setX((block.getX() - getWidth()) / getScale());
        changeDirection();
    }

    /**
     * this method changes the enemy coordinates.
     */
    protected void updateCoords() {
        if (isDirectionLeft()) {
            this.x -= this.speed;
        } else {
            this.x += this.speed;
        }

        if (this.isFalling) {
            this.y += FALL_SPEED;
        }
    }

    /**
     * The collision method checks what type of block has contact with the enemies
     * in order to define their behavior.
     */
    public void collision() {
        for (final Block block : blocksHandler.get().getBlocks()) {
            if (block.getBoundingBox().intersects(getBottomBound()) && block.getType() == BlockType.DEATH_BLOCK) {
                die();
            }
            if (block.getType() == BlockType.PIPE_LEFT || block.getType() == BlockType.PIPE_RIGHT
                    || block.getType() == BlockType.PIPE_TOP_LEFT || block.getType() == BlockType.PIPE_TOP_RIGHT
                    || block.getType() == BlockType.STONE || block.getType() == BlockType.TERRAIN
                    || block.getType() == BlockType.POPPED_LUCKY || block.getType() == BlockType.ALT_BLOCK
                    || block.getType() == BlockType.LUCKY || block.getType() == BlockType.BRICK) {
                if (block.getBoundingBox().contains(getLeftBound())
                        || block.getBoundingBox().intersects(getLeftBound())) {
                    setXCollisionLeft(block);
                } else if (block.getBoundingBox().contains(getBottomBound())
                        || block.getBoundingBox().intersects(getBottomBound())) {
                    setYCollisionBottom(block);
                } else if (block.getBoundingBox().contains(getRightBound())
                        || block.getBoundingBox().intersects(getRightBound())) {
                    setXCollisionRight(block);
                }
            }
        }
    }

    /**
     * abstract method for image rendering.
     * 
     * @param g Graphics object used for the rendering.
     */
    @Override
    public abstract void render(Graphics g);

    /**
     * abstract method for the enemy tick.
     */
    @Override
    public abstract void tick();

}
