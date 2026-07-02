package it.unibo.aknightstale.views.entity;

import it.unibo.aknightstale.models.entity.Direction;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Objects;

import static javafx.scene.paint.Color.LIGHTGREEN;

/**
 * The type Character view.
 */
public abstract class CharacterView extends EntityViewImpl implements AnimatedEntityView {
    /**
     * The max width of the health bar.
     */
    protected static final int HEALTH_BAR_MAX_WIDTH = 25;

    private static final int HEALTH_BAR_HEIGHT = 5;

    private final String nameEntity;
    private final double widthImage;
    private final double heightImage;

    /**
     * The constant MAX_NUM_FRAME.
     */
    private static final int MAX_NUM_FRAME = 12;

    /**
     * The Idle right image name.
     */
    private String idleRight;
    /**
     * The Idle left image name.
     */
    private String idleLeft;
    /**
     * The Idle up image name.
     */
    private String idleUp;

    /**
     * The Idle down image name.
     */
    private String idleDown;

    /**
     * The Walk right image name.
     */
    private String walkRight;
    /**
     * The Walk left image name.
     */
    private String walkLeft;
    /**
     * The Walk up image name.
     */
    private String walkUp;
    /**
     * The Walk down image name.
     */
    private String walkDown;

    private Status status;

    private int frameNum;

    /**
     * Sets idle right.
     *
     * @param idleRight the idle right
     */
    public void setIdleRight(final String idleRight) {
        this.idleRight = idleRight;
    }

    /**
     * Sets idle left.
     *
     * @param idleLeft the idle left
     */
    public void setIdleLeft(final String idleLeft) {
        this.idleLeft = idleLeft;
    }

    /**
     * Sets idle up.
     *
     * @param idleUp the idle up
     */
    public void setIdleUp(final String idleUp) {
        this.idleUp = idleUp;
    }

    /**
     * Sets idle down.
     *
     * @param idleDown the idle down
     */
    public void setIdleDown(final String idleDown) {
        this.idleDown = idleDown;
    }

    /**
     * Sets walk right.
     *
     * @param walkRight the walk right
     */
    public void setWalkRight(final String walkRight) {
        this.walkRight = walkRight;
    }

    /**
     * Sets walk left.
     *
     * @param walkLeft the walk left
     */
    public void setWalkLeft(final String walkLeft) {
        this.walkLeft = walkLeft;
    }

    /**
     * Sets walk up.
     *
     * @param walkUp the walk up
     */
    public void setWalkUp(final String walkUp) {
        this.walkUp = walkUp;
    }

    /**
     * Sets walk down.
     *
     * @param walkDown the walk down
     */
    public void setWalkDown(final String walkDown) {
        this.walkDown = walkDown;
    }

    /**
     * Instantiates a new Character view.
     *
     * @param s          the s
     * @param nameImage  the name image
     * @param width      the width
     * @param height     the height
     * @param nameEntity the name entity
     */
    public CharacterView(final Status s, final String nameImage, final double width, final double height, final String nameEntity) {
        super(nameImage, width, height);
        this.nameEntity = nameEntity;
        this.status = s;
        this.widthImage = width;
        this.heightImage = height;
    }

    /**
     * Get the entity status.
     *
     * @return The entity status.
     */
    public Status getStatus() {
        return this.status;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setStatus(final Status s) {
        this.status = s;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawHealthBar(final GraphicsContext gc, final double x, final double y, final double health,
            final double maxHealth) {
        if (health < maxHealth) {
            gc.setFill(LIGHTGREEN);
            gc.fillRect(x, y, (health / maxHealth) * HEALTH_BAR_MAX_WIDTH, HEALTH_BAR_HEIGHT);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final Direction dir) {
        if (this.getStatus() == Status.WALK) {
            switch (dir) {
            case RIGHT:
                setImage(new Image(Objects.requireNonNull(CharacterView.class.getResourceAsStream(idleRight)), widthImage, heightImage, true, false),
                        new Image(Objects.requireNonNull(CharacterView.class.getResourceAsStream(walkRight)), widthImage, heightImage, true, false));
                break;
            case LEFT:
                setImage(new Image(Objects.requireNonNull(CharacterView.class.getResourceAsStream(idleLeft)), widthImage, heightImage, true, false),
                        new Image(Objects.requireNonNull(CharacterView.class.getResourceAsStream(walkLeft)), widthImage, heightImage, true, false));
                break;
            case DOWN:
                setImage(new Image(Objects.requireNonNull(CharacterView.class.getResourceAsStream(idleDown)), widthImage, heightImage, true, false),
                        new Image(Objects.requireNonNull(CharacterView.class.getResourceAsStream(walkDown)), widthImage, heightImage, true, false));
                break;
            case UP:
                setImage(new Image(Objects.requireNonNull(CharacterView.class.getResourceAsStream(idleUp)), widthImage, heightImage, true, false),
                        new Image(Objects.requireNonNull(CharacterView.class.getResourceAsStream(walkUp)), widthImage, heightImage, true, false));
                break;
            default:
            }
            this.frameNum++;
            if (!checkSpriteNum(MAX_NUM_FRAME * 2)) {
                this.frameNum = 0;
            }
        } else {
            super.setImage(new Image(getClass().getResourceAsStream(nameEntity + "_" + this.getStatus() + "_" + dir + ".png"), widthImage, heightImage,
                    true, false));
        }
    }

    private void setImage(final Image a, final Image b) {
        if (checkSpriteNum(MAX_NUM_FRAME)) {
            super.setImage(a);
        } else {
            super.setImage(b);
        }
    }

    private boolean checkSpriteNum(final int n) {
        return this.frameNum < n;
    }

}
