package it.unibo.superpeach.gameentities.player;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Optional;

import it.unibo.superpeach.game.Scoreboard;
import it.unibo.superpeach.gameentities.GameObject;
import it.unibo.superpeach.gameentities.blocks.Block;
import it.unibo.superpeach.gameentities.blocks.BlockType;
import it.unibo.superpeach.gameentities.blocks.BlocksHandler;
import it.unibo.superpeach.gameentities.blocks.MapFixedBlock;
import it.unibo.superpeach.gameentities.enemies.EnemiesHandler;
import it.unibo.superpeach.gameentities.enemies.Enemy;
import it.unibo.superpeach.gameentities.powerups.PowerUp;
import it.unibo.superpeach.gameentities.powerups.PowerupsHandler;
import it.unibo.superpeach.gameentities.powerups.PowerUp.PowerUpType;

/**
 * This class implementes the player.
 * 
 * @author Patrick Sbrighi
 */
public abstract class Player implements GameObject {
    private static final int FALL_SPEED = 3;
    private static final int LIFE_START = 3;
    private static final int POINT_LUCKY_BRICK = 200;
    private static final int POINT_WIN = 2000;
    private static final int POINT_KILLED_ENEMY = 300;
    private static final int POINT_FLAG_POLE = 500;
    private static final int POINT_FLAG_TIP = 1000;
    private static final int CONSECUTIVE_JUMP = 2;
    private static final int MIN_X = 240;
    private static final int MAX_X = 3504;
    private static final int TICK_FOR_STAR = 700;
    private static final int PADDING = 5;
    private int width;
    private int height;
    private int x;
    private int y;
    private int scale;
    private boolean jumped;
    private final Optional<BlocksHandler> blocksHandler;
    private final Optional<EnemiesHandler> enemiesHandler;
    private final Optional<PowerupsHandler> powerupsHandler;
    private final Optional<Scoreboard> scoreboard;
    private int moveX;
    private int moveY;
    private final int paddingBound;
    private int point;
    private int life;
    private boolean addedPointFlag;
    private boolean addedPointWin;
    private final int respawnX;
    private final int respawnY;
    private int consecutiveJumps;
    private boolean hasWon;
    private boolean hasLost;
    private PowerUpType currentPowerUp;
    private PowerUpType lastPowerUp;
    private int numTickStar;

    /**
     * Class constructor.
     * 
     * @param x               X
     * @param y               Y
     * @param width           Widht
     * @param height          Height
     * @param scale           Scale dimension
     * @param blocksHandler   Blocks handler
     * @param enemiesHandler  Enemies handler
     * @param powersUpHandler PowerUps handler
     * @param scoreboard      Scoreboard
     */
    public Player(final int x, final int y, final int width, final int height, final int scale,
            final BlocksHandler blocksHandler,
            final EnemiesHandler enemiesHandler, final PowerupsHandler powersUpHandler, final Scoreboard scoreboard) {
        this.width = width * scale;
        this.height = height * scale;
        this.x = x * scale;
        this.y = y * scale;
        this.scale = scale;
        this.jumped = false;
        this.blocksHandler = Optional.of(blocksHandler);
        this.moveX = 0;
        this.moveY = 0;
        this.paddingBound = PADDING * this.scale;
        this.enemiesHandler = Optional.of(enemiesHandler);
        this.scoreboard = Optional.of(scoreboard);
        this.point = 0;
        this.life = LIFE_START;
        this.addedPointFlag = false;
        this.addedPointWin = false;
        this.respawnX = x;
        this.respawnY = y;
        this.consecutiveJumps = 0;
        this.hasWon = false;
        this.hasLost = false;
        this.currentPowerUp = null;
        this.numTickStar = 0;
        this.powerupsHandler = Optional.of(powersUpHandler);
        this.lastPowerUp = null;
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

    /**
     * This method returns the actual score.
     * 
     * @return Return the actual score
     */
    public final int getScore() {
        return this.point;
    }

    /**
     * This method returns the value of vertical movement.
     * 
     * @return Return 0 if player doesn't move, num < 0 if player is jumping, num >
     *         0 if player is falling
     */
    public final int getMoveY() {
        return this.moveY;
    }

    /**
     * This method returns the valure of orizzontal movement.
     * 
     * @return Return 0 if player doesn't move, num<0 if player is moveing to the
     *         left, num>0 if player is moveing to the right
     */
    public final int getMoveX() {
        return this.moveX;
    }

    /**
     * This method returns the actual power up.
     * 
     * @return Return null is there's no actual power up or a power up
     */
    public final PowerUpType getPowerUp() {
        return this.currentPowerUp;
    }

    @Override
    public final Rectangle getBoundingBox() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    /**
     * This method returns if the player is jumping.
     * 
     * @return Return if player is jumping
     */
    public final boolean hasJumped() {
        return this.jumped;
    }

    /**
     * This method returns if the player won.
     * 
     * @return Return if the player won
     */
    public final boolean hasWon() {
        return this.hasWon;
    }

    /**
     * This method returns if the player lost.
     * 
     * @return Return if the player lost
     */
    public final boolean hasLost() {
        return this.hasLost;
    }

    @Override
    public final void setX(final int x) {
        if (x >= MIN_X && x <= MAX_X) {
            this.x = x * this.scale;
        }
    }

    @Override
    public final void setY(final int y) {
        this.y = y * this.scale;
    }

    @Override
    public final void setHeight(final int height) {
        this.height = height;
    }

    @Override
    public final void setWidth(final int width) {
        this.width = width;
    }

    /**
     * This method allows to set the vertical movement.
     * 
     * @param moveY The new value of vertical movement
     */
    public final void setMoveY(final int moveY) {
        this.moveY = moveY;
    }

    /**
     * This method allows to set the horizontal movement.
     * 
     * @param moveX The new value of horizonatl movement
     */
    public final void setMoveX(final int moveX) {
        this.moveX = moveX;
    }

    @Override
    public final void setScale(final int scale) {
        this.scale = scale;
    }

    /**
     * This method allows to set if the player is jumping.
     * 
     * @param jumped Is player jumping
     */
    public final void setHasJumped(final boolean jumped) {
        this.jumped = jumped;
    }

    /**
     * This method controls if player if the player has not yet finished the
     * available jumps.
     */
    public final void incrementConsecutiveJump() {
        if (this.consecutiveJumps + 1 <= CONSECUTIVE_JUMP) {
            this.consecutiveJumps++;
        }
    }

    /**
     * This method returns if player can jump.
     * 
     * @return Return if player can jump
     */
    public final boolean canJump() {
        return this.consecutiveJumps < CONSECUTIVE_JUMP;
    }

    /**
     * This method makes the player falling.
     */
    public final void fall() {
        moveY = FALL_SPEED;
    }

    /**
     * This method removes star power up when time runs out.
     */
    public final void starTimeOver() {
        if (currentPowerUp == PowerUpType.STAR) {
            if (numTickStar >= TICK_FOR_STAR) {
                currentPowerUp = lastPowerUp;
                lastPowerUp = PowerUpType.STAR;
                numTickStar = 0;
            } else {
                numTickStar++;
            }
        }
    }

    /**
     * This method controls the collisions with the other object.
     */
    public final void collision() {
        collisionsWithBlocks();
        collisionsWithEnemies();
        collisionsWithPowersUp();
    }

    private void collisionsWithBlocks() {
        for (final MapFixedBlock block : blocksHandler.get().getBlocks()) {
            if (block.getType() == BlockType.PIPE_LEFT || block.getType() == BlockType.PIPE_RIGHT
                    || block.getType() == BlockType.PIPE_TOP_LEFT || block.getType() == BlockType.PIPE_TOP_RIGHT
                    || block.getType() == BlockType.STONE || block.getType() == BlockType.TERRAIN) {
                collisionsWithStaticBlocks(block);
            } else if (block.getType() == BlockType.BRICK || block.getType() == BlockType.POPPED_LUCKY
                    || block.getType() == BlockType.LUCKY) {
                collisionsWithLuckBrickPopped(block);
            } else if (block.getType() == BlockType.DEATH_BLOCK) {
                collisionsWithDeathBlock(block);
            } else if (block.getType() == BlockType.CASTLE_DOOR_BOT || block.getType() == BlockType.CASTLE_DOOR_TOP) {
                collisionsWithCastle(block);
            } else if (block.getType() == BlockType.FLAG_TIP) {
                collisionsWithFlagTip(block);
            } else if (block.getType() == BlockType.FLAG_POLE) {
                collisionsWithFlagPole(block);
            }
        }
    }

    private void collisionsWithFlagPole(final MapFixedBlock block) {
        if ((block.getBoundingBox().intersects(getRightBound())
                || block.getBoundingBox().contains(getRightBound())
                || block.getBoundingBox().intersects(getLeftBound())
                || block.getBoundingBox().contains(getLeftBound())
                || block.getBoundingBox().intersects(getTopBound())
                || block.getBoundingBox().contains(getTopBound())
                || block.getBoundingBox().intersects(getBottomBound())
                || block.getBoundingBox().contains(getBottomBound())) && !addedPointFlag) {
            addPoints(POINT_FLAG_POLE);
            addedPointFlag = true;
        }
    }

    private void collisionsWithFlagTip(final MapFixedBlock block) {
        if ((block.getBoundingBox().intersects(getTopBound())
                || block.getBoundingBox().intersects(getLeftBound())) && !addedPointFlag) {
            addPoints(POINT_FLAG_TIP);
            addedPointFlag = true;
        }
    }

    private void collisionsWithCastle(final MapFixedBlock block) {
        if (block.getBoundingBox().intersects(getBottomBound())
                || block.getBoundingBox().intersects(getTopBound())
                || block.getBoundingBox().intersects(getLeftBound())
                || block.getBoundingBox().intersects(getRightBound())) {
            if (!addedPointWin) {
                addPoints(POINT_WIN);
                addedPointWin = true;
            }
            this.hasWon = true;
        }
    }

    private void collisionsWithDeathBlock(final MapFixedBlock block) {
        if (block.getBoundingBox().intersects(getBottomBound()) || block.getBoundingBox().intersects(getTopBound())
                || block.getBoundingBox().intersects(getLeftBound())
                || block.getBoundingBox().intersects(getRightBound())) {
            loseLifeOrPowerUp();
        }
    }

    private void collisionsWithLuckBrickPopped(final MapFixedBlock block) {
        if (block.getBoundingBox().contains(getTopBound()) || block.getBoundingBox().intersects(getTopBound())) {
            setYCollisionTop(block);
            if (currentPowerUp != null && block.getType() == BlockType.BRICK) {
                addPoints(POINT_LUCKY_BRICK);
                blocksHandler.get().removeFixedBlock(block);
            } else if (block.getType() == BlockType.LUCKY) {
                addPoints(POINT_LUCKY_BRICK);
                block.popLuckyBlock(powerupsHandler.get(), blocksHandler.get());
            }
        } else if (block.getBoundingBox().contains(getBottomBound())) {
            setYCollisionBottom(block);
            resetCosecutiveJump();
        } else if (block.getBoundingBox().contains(getLeftBound())) {
            setXCollisionLeft(block);
        } else if (block.getBoundingBox().contains(getRightBound())) {
            setXCollisionRight(block);
        } else if (block.getBoundingBox().intersects(getBottomBound())) {
            setYCollisionBottom(block);
            resetCosecutiveJump();
        } else if (block.getBoundingBox().intersects(getLeftBound())) {
            setXCollisionLeft(block);
        } else if (block.getBoundingBox().intersects(getRightBound())) {
            setXCollisionRight(block);
        }
    }

    private void collisionsWithStaticBlocks(final MapFixedBlock block) {
        if (block.getBoundingBox().contains(getBottomBound())) {
            setYCollisionBottom(block);
            resetCosecutiveJump();
        } else if (block.getBoundingBox().contains(getTopBound())) {
            setYCollisionTop(block);
        } else if (block.getBoundingBox().contains(getLeftBound())) {
            setXCollisionLeft(block);
        } else if (block.getBoundingBox().contains(getRightBound())) {
            setXCollisionRight(block);
        } else if (block.getBoundingBox().intersects(getBottomBound())) {
            setYCollisionBottom(block);
            resetCosecutiveJump();
        } else if (block.getBoundingBox().intersects(getTopBound())) {
            setYCollisionTop(block);
        } else if (block.getBoundingBox().intersects(getLeftBound())) {
            setXCollisionLeft(block);
        } else if (block.getBoundingBox().intersects(getRightBound())) {
            setXCollisionRight(block);
        }
    }

    private void collisionsWithEnemies() {
        for (final Enemy enemy : enemiesHandler.get().getEnemies()) {
            if (touchedEnemy(enemy) && currentPowerUp != PowerUpType.STAR) {
                loseLifeOrPowerUp();
            } else if (killedEnemy(enemy) || touchedEnemy(enemy) && currentPowerUp == PowerUpType.STAR) {
                addPoints(POINT_KILLED_ENEMY);
                enemiesHandler.get().removeEnemy(enemy);
            }
        }
    }

    private void collisionsWithPowersUp() {
        for (final PowerUp power : powerupsHandler.get().getPowerups()) {
            if (touchedPowerUp(power)) {
                switch (power.getPowerUpType()) {
                    case COIN:
                        scoreboard.get().collectCoin();
                        break;
                    case RED_MUSHROOM:
                        if (currentPowerUp != PowerUpType.RED_MUSHROOM
                                && !(currentPowerUp == PowerUpType.STAR && lastPowerUp == PowerUpType.RED_MUSHROOM)) {
                            lastPowerUp = currentPowerUp;
                        }
                        redMushroomTrasformation();
                        break;
                    case STAR:
                        lastPowerUp = currentPowerUp;
                        currentPowerUp = PowerUpType.STAR;
                        break;
                    case LIFE_MUSHROOM:
                        if (life < LIFE_START) {
                            life++;
                            scoreboard.get().restoreHeart();
                        }
                        break;
                    default:
                        break;
                }
                power.die();
            }
        }
    }

    private void resetCosecutiveJump() {
        this.consecutiveJumps = 0;
    }

    private void addPoints(final int point) {
        this.point += point;
    }

    private boolean touchedPowerUp(final PowerUp power) {
        return power.getBoundingBox().intersects(getBottomBound()) || power.getBoundingBox().intersects(getTopBound())
                || power.getBoundingBox().intersects(getLeftBound())
                || power.getBoundingBox().intersects(getRightBound());
    }

    private void redMushroomTrasformation() {
        if (!(currentPowerUp == PowerUpType.STAR && lastPowerUp == PowerUpType.RED_MUSHROOM)) {
            if (currentPowerUp != PowerUpType.RED_MUSHROOM) {
                setHeight(height * 2);
            }
            if (currentPowerUp != PowerUpType.STAR) {
                currentPowerUp = PowerUpType.RED_MUSHROOM;
            } else {
                lastPowerUp = PowerUpType.RED_MUSHROOM;
            }
        }
    }

    private void loseLifeOrPowerUp() {
        if (currentPowerUp == PowerUpType.RED_MUSHROOM) {
            lastPowerUp = currentPowerUp;
            this.height /= 2;
            currentPowerUp = null;
            setY(respawnY);
        } else if (currentPowerUp == PowerUpType.STAR) {
            if (lastPowerUp == PowerUpType.RED_MUSHROOM) {
                lastPowerUp = currentPowerUp;
                currentPowerUp = PowerUpType.RED_MUSHROOM;
            } else {
                lastPowerUp = currentPowerUp;
                currentPowerUp = null;
            }
            setY(respawnY);
        } else {
            life--;
            scoreboard.get().removeHeart();
            if (life < 0) {
                this.hasLost = true;
            }
            setX(respawnX);
            setY(respawnY);
        }
    }

    private boolean touchedEnemy(final Enemy enemy) {
        return enemy.getBoundingBox().intersects(getTopBound()) || enemy.getBoundingBox().intersects(getLeftBound())
                || enemy.getBoundingBox().intersects(getRightBound());
    }

    private boolean killedEnemy(final Enemy enemy) {
        return enemy.getBoundingBox().intersects(getBottomBound());
    }

    private void setYCollisionTop(final Block block) {
        setY(block.getY() / block.getScale() + block.getHeight() / block.getScale());
    }

    private void setYCollisionBottom(final Block block) {
        setY(block.getY() / block.getScale() - getHeight() / getScale());
    }

    private void setXCollisionLeft(final Block block) {
        setX((block.getX() + block.getWidth()) / getScale());
    }

    private void setXCollisionRight(final Block block) {
        setX(block.getX() / block.getScale() - getWidth() / getScale());
    }

    private Rectangle getTopBound() {
        if (currentPowerUp == PowerUpType.RED_MUSHROOM
                || currentPowerUp == PowerUpType.STAR && lastPowerUp == PowerUpType.RED_MUSHROOM) {
            return new Rectangle(getX() + getWidth() / 2 - getWidth() / 4, getY(), getWidth() / 2,
                    (paddingBound) * ((paddingBound / getScale())));
        }
        return new Rectangle(getX() + getWidth() / 2 - getWidth() / 4, getY(), getWidth() / 2,
                (paddingBound / getScale()) * (paddingBound / 2));
    }

    private Rectangle getBottomBound() {
        if (currentPowerUp == PowerUpType.RED_MUSHROOM
                || currentPowerUp == PowerUpType.STAR && lastPowerUp == PowerUpType.RED_MUSHROOM) {
            return new Rectangle(getX() + getWidth() / 2 - getWidth() / 4, getY() + getHeight() - paddingBound,
                    getWidth() / 2, paddingBound);
        }
        return new Rectangle(getX() + getWidth() / 2 - getWidth() / 4, getY() + getHeight() - (paddingBound / 2),
                getWidth() / 2, paddingBound / 2);
    }

    private Rectangle getLeftBound() {
        return new Rectangle(getX(), getY() + paddingBound, paddingBound, getHeight() - 2 * paddingBound);

    }

    private Rectangle getRightBound() {
        return new Rectangle(getX() + getWidth() - paddingBound, getY() + paddingBound, paddingBound,
                getHeight() - 2 * paddingBound);
    }

    /**
     * This method implements the movement to the left.
     */
    public abstract void moveLeft();

    /**
     * This method implements the movement to the right.
     */
    public abstract void moveRight();

    /**
     * This method implements the jump.
     */
    public abstract void jump();

    @Override
    public abstract void render(Graphics g);

    @Override
    public abstract void tick();
}
