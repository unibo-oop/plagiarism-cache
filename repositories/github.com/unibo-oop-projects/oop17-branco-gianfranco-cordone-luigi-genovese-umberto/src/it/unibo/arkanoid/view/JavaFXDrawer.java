package it.unibo.arkanoid.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import it.unibo.arkanoid.subject.GravitySubject;
import it.unibo.arkanoid.subject.IndestructibleBrick;
import it.unibo.arkanoid.subject.MultipleBrick;
import it.unibo.arkanoid.subject.PowerUp;
import it.unibo.arkanoid.subject.SimpleBrick;
import it.unibo.arkanoid.subject.Subject;
import it.unibo.arkanoid.utility.Vector2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

/**
 * Class used to print in a Game Screen.
 * 
 */
public final class JavaFXDrawer implements Drawer {

    private static final double WORLD_OFFSET = 7.5;
    private final Canvas canvas;
    private final double worldWidth;
    private final double worldHeight;
    private final Pane canvasParent;
    private final Image currentBackGround;
    private List<Subject> cachedSubjects;

    /**
     * Constructor for JavaFX drawer.
     * 
     * @param canvas
     *            Canvas component to draw on it.
     * @param worldWidth
     *            The width of the game's world.
     * @param worldHeight
     *            The height of the game's world.
     * @param canvasParent
     *            The container pane of canvas.
     */
    public JavaFXDrawer(final Canvas canvas, final double worldWidth, final double worldHeight,
            final Pane canvasParent) {
        super();
        this.canvas = canvas;
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
        this.canvasParent = canvasParent;
        this.canvasParent.widthProperty().addListener(c -> this.resizeCanvas());
        this.canvasParent.heightProperty().addListener(c -> this.resizeCanvas());
        this.currentBackGround = getBackGround();
        this.cachedSubjects = new ArrayList<>();
        this.scaleCanvas();
    }

    private Image getBackGround() {
        final Random random = new Random();
        final List<ImageID> listBackground = ImageID.getBackgrounds();
        return ImageLoaders.getImageLoader().getImage(listBackground.get(random.nextInt(listBackground.size())));
    }

    private void resizeCanvas() {
        final double parentWidth = this.canvasParent.getWidth();
        final double parentHeight = this.canvasParent.getHeight();
        final double ratio = parentWidth / parentHeight;
        final double modelRatio = this.worldWidth / this.worldHeight;

        if (ratio < modelRatio) {
            this.canvas.setWidth(parentWidth);
            this.canvas.setHeight(parentWidth / modelRatio);
        } else {
            this.canvas.setHeight(parentHeight);
            this.canvas.setWidth(parentHeight * modelRatio);
        }
        this.canvas.getGraphicsContext2D().restore();
        this.scaleCanvas();
        this.clear();
        this.render(this.cachedSubjects);
    }

    private void scaleCanvas() {
        final GraphicsContext graphicContext = this.canvas.getGraphicsContext2D();
        graphicContext.save();
        graphicContext.translate(canvas.getWidth() / 2, canvas.getHeight());
        graphicContext.scale(canvas.getWidth() / this.worldWidth, canvas.getHeight() / this.worldHeight);
        graphicContext.translate(0, -WORLD_OFFSET);
    }

    private void render(final Subject subject) {
        switch (subject.getSubjectType()) {
        case PADDLE:
            drawImage(subject, ImageID.PADDLE_BLUE);
            break;
        case BRICK:
            if (subject instanceof SimpleBrick) {
                drawImage(subject, ImageID.BRICK_SIMPLE);
            } else if (subject instanceof IndestructibleBrick) {
                drawImage(subject, ImageID.BRICK_INDESTRUCTIBLE);
            } else if (subject instanceof MultipleBrick) {
                drawImage(subject, ImageID.BRICK_MULTILIFE);
            }
            break;
        case BALL:
            drawImage(subject, ImageID.BALL);
            break;
        case POWER_UP:
            final PowerUp powerUp = (PowerUp) ((GravitySubject) subject).getDecoratedSubject();
            switch (powerUp.getPowerUpType()) {
            case ThreeBall:
                drawImage(subject, ImageID.THREEBALL_POWERUP);
                break;
            case DecreasePaddle:
                drawImage(subject, ImageID.REDUCE_POWERUP);
                break;
            case EnlargePaddle:
                drawImage(subject, ImageID.ENLARGE_POWERUP);
                break;
            case IncreseVelocityBall:
                drawImage(subject, ImageID.VELOCITY_POWERUP);
                break;
            default:
                break;
            }
            break;
        default:
            break;
        }
    }

    private void drawImage(final Subject subject, final ImageID image) {
        final Image imageSubject = ImageLoaders.getImageLoader().getImage(image);
        final Vector2D position = subject.getPosition()
                .sumVector(new Vector2D(-subject.getWidth() / 2, subject.getHeight() / 2));
        canvas.getGraphicsContext2D().drawImage(imageSubject, position.getX(), -position.getY(), subject.getWidth(),
                subject.getHeight());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        canvas.getGraphicsContext2D().drawImage(currentBackGround, -this.worldWidth / 2,
                WORLD_OFFSET - this.worldHeight, this.worldWidth, this.worldHeight);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final List<Subject> subjects) {
        subjects.forEach(this::render);
        this.cachedSubjects = subjects;
    }

}
