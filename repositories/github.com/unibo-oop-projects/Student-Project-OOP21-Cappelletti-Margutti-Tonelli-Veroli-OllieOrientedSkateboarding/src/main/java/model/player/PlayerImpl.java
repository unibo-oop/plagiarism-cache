package model.player;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import sound.Sound;
import sound.SoundFactory;

/**
 * 
 * Implementation of {@link Player}.
 *
 */
public final class PlayerImpl implements Player {
    /**
     * Width of the sprite.
     */
    public static final int MAIN_CHARACTER_WIDTH = 70;
    /**
     * Height of the sprite.
     */
    public static final int MAIN_CHARACTER_HEIGHT = 96;
    /**
     * Y coordinate of the land.
     */
    public static final double LAND = 440.0f;
    /**
     * X coordinate of the player.
     */
    public static final double PLAYER_X = 40.0f;
    /**
     * How much does the player jump.
     */
    public static final double JUMP_HEIGHT = 160.0f;
    private static final double DOUBLE_JUMP = 1.5f;
    private static final double GRAVITY = 4.5f;
    private static final String SEP = File.separator;
    private static final String FILE_NAME = System.getProperty("user.home") + SEP + "OOS_selectedSkin.txt";

    private double y = LAND;
    private JumpState jumpState = JumpState.NOT_JUMPING;
    private boolean isDoubleJumpActive;
    private double landHeight = LAND;
    private int numLives = 1;
    private boolean shieldActive;
    private int jumpCounter;
    private final Sound jumpSound;
    private double finalJumpY;
    private final Image image;

    /**
     * Creates the Player.
     * @param soundFactory the SoundFactory
     */
    public PlayerImpl(final SoundFactory soundFactory) {
        this.jumpSound = soundFactory.createJumpSound();
        this.image = new Image(readImagePathFromFile());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void jump() {
        if (jumpState == JumpState.NOT_JUMPING) {
            double jumpHeight = JUMP_HEIGHT;
            this.jumpState = JumpState.UP;
            this.jumpCounter++;
            this.jumpSound.play();
            if (this.isDoubleJumpActive) {
                jumpHeight = jumpHeight * DOUBLE_JUMP;
            }
            this.finalJumpY = Math.max(this.y - jumpHeight, 0);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateJump() {
        if (this.jumpState != JumpState.NOT_JUMPING) {
            if (this.y <= this.finalJumpY) {
                this.jumpState = JumpState.DOWN;
            }
            if (this.jumpState == JumpState.UP) {
                this.y = this.y - GRAVITY;
            } else {
                this.y = this.y + GRAVITY;
            }
            if (this.y >= this.landHeight) {
                this.jumpState = JumpState.NOT_JUMPING;
                this.y = landHeight;
            }
        }

        if (this.jumpState == JumpState.NOT_JUMPING && this.y < this.landHeight) {
            this.jumpState = JumpState.DOWN;
        }
    }

    /**
     * Reads the image path from file.
     * @return a String with the imagePath
     */
    private String readImagePathFromFile() {
        try (BufferedReader br = Files.newBufferedReader(Paths.get(FILE_NAME))) {
            return br.readLine();
        } catch (IOException e) {
           return "Player.png";
        }
    }

    /**
     * @return the y of player's head
     */
    private double getHeadY() {
        return this.y - MAIN_CHARACTER_HEIGHT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JumpState getJumpState() {
        return this.jumpState;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLives() {
        return this.numLives;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getJumpCounter() {
        return this.jumpCounter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Rectangle2D getBounds() {
        return new Rectangle2D(PLAYER_X, this.getHeadY(), MAIN_CHARACTER_WIDTH, MAIN_CHARACTER_HEIGHT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Image getImage() {
        return this.image;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isShieldActive() {
        return shieldActive;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDoubleJump(final boolean set) {
        this.isDoubleJumpActive = set;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLandHeight(final double h) {
        this.landHeight = h;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNumberOfLives(final int lives) {
        this.numLives += lives;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setShield(final boolean active) {
        this.shieldActive = active;
    }

}
