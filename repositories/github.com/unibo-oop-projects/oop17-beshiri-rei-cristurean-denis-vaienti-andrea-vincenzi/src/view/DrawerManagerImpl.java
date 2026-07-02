package view;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import controller.time.Time;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import model.animated.Animated;
import model.hitbox.CircleHitBox;
import model.hitbox.RectangularHitBox;
import model.room.MainRoom;
import model.room.Room;
import model.room.ShopRoom;
import utility.ImageType;
import view.utility.ProxyImageLoader;
import view.utility.Tupla;
import view.utility.ViewUtils;
import java.util.LinkedList;

/**
 * Drawer manager.
 *
 */
public class DrawerManagerImpl implements DrawerManager {

    private final Canvas gameCanvas;
    private final Canvas timerCanvas;
    private final Canvas lifeCanvas;
    private final GraphicsContext gcGameCanvas;
    private final GraphicsContext gcTimerCanvas;
    private final GraphicsContext gcLifeCanvas;
    private List<Animated> entities;
    private Room room;
    private int life;
    private Time time;

    /**
     * Constructor for drawer manager.
     * 
     * @param gameCanvas
     *            main canvas of the game.
     * @param timerCanvas
     *            canvas timer.
     * @param lifeCanvas
     *            canvas for player life.
     */
    public DrawerManagerImpl(final Canvas gameCanvas, final Canvas timerCanvas, final Canvas lifeCanvas) {
        this.gameCanvas = gameCanvas;
        this.timerCanvas = timerCanvas;
        this.lifeCanvas = lifeCanvas;
        gcGameCanvas = gameCanvas.getGraphicsContext2D();
        gcTimerCanvas = timerCanvas.getGraphicsContext2D();
        gcLifeCanvas = lifeCanvas.getGraphicsContext2D();
        resize();
    }

    /**
     * Event that occur when time change.
     */
    @Override
    public void notifyTimeChange(final Time t) {
        time = t;
        drawTime();
    }

    /**
     * Set player life.
     */
    @Override
    public void setPlayerLife(final int life) {
        this.life = life;
        drawPlayerLife();
    }

    /**
     * Set list of entities.
     */
    @Override
    public void setAnimatedEntities(final List<Animated> entities) {
        this.entities = entities;
    }

    /**
     * Actual room.
     */
    @Override
    public void setRoom(final Room room) {
        this.room = room;
        if (!Objects.isNull(room)) {
            drawRoom();
        }
    }

    /**
     * Method used to draw entire scene of the game.
     */
    @Override
    public synchronized void draw() {
        gcGameCanvas.clearRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());
        if (!Objects.isNull(room) && !Objects.isNull(entities)) {
            drawRoom();
            drawEntities();
        }
    }

    /**
     * Resize canvas dimension.
     */
    @Override
    public final void resize() {
        final Tupla<Double, Double> gameCanvasDimension = getScaledDimension(
                new Tupla<Double, Double>(gameCanvas.getWidth(), gameCanvas.getHeight()),
                new Tupla<Double, Double>(ViewManagerImpl.get().getStageWidth(),
                        ViewManagerImpl.get().getStageHeight() - ViewUtils.getStageDeltaHeight()),
                new Tupla<Double, Double>(ViewUtils.getWorldWidthProp(), ViewUtils.getWorldHeightProp()),
                new Tupla<Double, Double>(ViewUtils.getWorldWidth(), ViewUtils.getWorldHeight()));
        gameCanvas.setHeight(gameCanvasDimension.getY());
        gameCanvas.setWidth(gameCanvasDimension.getX());
        gameCanvas.setTranslateX((ViewManagerImpl.get().getStageWidth() - gameCanvas.getWidth()) / 2);
        gameCanvas.setTranslateY((ViewManagerImpl.get().getStageHeight() - gameCanvas.getHeight()
                - (gameCanvas.getWidth() / (ViewUtils.getBottomCanvasProp() * 2))) / 2);

        timerCanvas.setWidth(gameCanvas.getWidth() / 2);
        timerCanvas.setHeight(timerCanvas.getWidth() / ViewUtils.getBottomCanvasProp());
        timerCanvas.setTranslateX(ViewManagerImpl.get().getStageWidth()
                - ((ViewManagerImpl.get().getStageWidth() - gameCanvas.getWidth()) / 2) - timerCanvas.getWidth());
        timerCanvas.setTranslateY(gameCanvas.getTranslateY() + gameCanvas.getHeight());

        lifeCanvas.setWidth(gameCanvas.getWidth() / 2);
        lifeCanvas.setHeight(lifeCanvas.getWidth() / ViewUtils.getBottomCanvasProp());
        lifeCanvas.setTranslateX((ViewManagerImpl.get().getStageWidth() - gameCanvas.getWidth()) / 2);
        lifeCanvas.setTranslateY(gameCanvas.getTranslateY() + gameCanvas.getHeight());
    }

    /**
     * Method used to redraw after a resize event.
     */
    @Override
    public void redrawAfterResize() {
        draw();
        drawPlayerLife();
        if (!Objects.isNull(time)) {
            drawTime();
        }
    }

    /**
     * Initialize time canvas.
     */
    @Override
    public void initTimeCanvas() {
        time = new Time(0, 0);
        drawTime();
    }

    private synchronized void drawEntities() {
        final Tupla<Double, Double> scalingFactor = computeScaleFactor(
                new Tupla<Double, Double>(gameCanvas.getWidth(), gameCanvas.getHeight()),
                new Tupla<Double, Double>(ViewUtils.getWorldWidth(), ViewUtils.getWorldHeight()));
        gcGameCanvas.save();
        gcGameCanvas.scale(scalingFactor.getX(), scalingFactor.getY());
        entities.forEach(x -> drawHitBoxImage(x.getImageType(), (CircleHitBox) x.getHitBox(), gcGameCanvas));
        gcGameCanvas.restore();
    }

    private synchronized void drawRoom() {
        final Tupla<Double, Double> scalingFactor = computeScaleFactor(
                new Tupla<Double, Double>(gameCanvas.getWidth(), gameCanvas.getHeight()),
                new Tupla<Double, Double>(ViewUtils.getWorldWidth(), ViewUtils.getWorldHeight()));
        gcGameCanvas.save();
        gcGameCanvas.scale(scalingFactor.getX(), scalingFactor.getY());
        drawHitBoxImage(room.getBackgroundImage(),
                new RectangularHitBox(ViewUtils.getWallMinorDimension(), ViewUtils.getWallMinorDimension(),
                        ViewUtils.getWorldHeight() - 2 * ViewUtils.getWallMinorDimension(),
                        ViewUtils.getWorldWidth() - 2 * ViewUtils.getWallMinorDimension()),
                gcGameCanvas);
        room.getDoors()
                .forEach(x -> drawHitBoxImage(x.getImageType(), (RectangularHitBox) x.getHitBox(), gcGameCanvas));
        room.getWalls()
                .forEach(x -> drawHitBoxImage(x.getImageType(), (RectangularHitBox) x.getHitBox(), gcGameCanvas));

        if (room instanceof MainRoom) {
            final MainRoom mainRoom = (MainRoom) room;
            drawHitBoxImage(mainRoom.getButton().getImageType(), (CircleHitBox) mainRoom.getButton().getHitBox(),
                    gcGameCanvas);
        } else if (room instanceof ShopRoom) {
            final ShopRoom shopRoom = (ShopRoom) room;
            shopRoom.getItems()
                    .forEach(x -> drawHitBoxImage(x.getImageType(), (RectangularHitBox) x.getHitBox(), gcGameCanvas));
        }

        gcGameCanvas.restore();
    }

    private void drawTime() {
        gcTimerCanvas.clearRect(0, 0, timerCanvas.getWidth(), timerCanvas.getHeight());
        gcTimerCanvas.save();
        gcTimerCanvas.setTextAlign(TextAlignment.RIGHT);
        gcTimerCanvas.setTextBaseline(VPos.CENTER);
        gcTimerCanvas.setFill(Color.BLACK);
        gcTimerCanvas.setFont(new Font(timerCanvas.getHeight() / ViewUtils.getTextTimerProp()));
        gcTimerCanvas.fillText(time.toString(), Math.round(timerCanvas.getWidth() / 2),
                Math.round(timerCanvas.getHeight() / 2), timerCanvas.getWidth());
        gcTimerCanvas.restore();
    }

    private void drawPlayerLife() {
        final Tupla<Double, Double> scaleFactor = computeScaleFactor(
                new Tupla<Double, Double>(lifeCanvas.getWidth(), lifeCanvas.getHeight()),
                new Tupla<Double, Double>(ViewUtils.getLifeCanvasWidth(), ViewUtils.getLifeCanvasHeight()));
        gcLifeCanvas.save();
        gcLifeCanvas.clearRect(0, 0, lifeCanvas.getWidth(), lifeCanvas.getHeight());
        gcLifeCanvas.scale(scaleFactor.getX(), scaleFactor.getY());
        final int completedHearth = life / 2;
        final int halfHeath = life - 2 * completedHearth > 0 ? 1 : 0;
        final double imgBlock = ((completedHearth + halfHeath) * ViewUtils.getHearthWidth())
                + (completedHearth + halfHeath - 1) * ViewUtils.getLifeCanvasWidth()
                        / ViewUtils.getHearthSpaceProportion();
        final double yDistance = (ViewUtils.getLifeCanvasHeight() - ViewUtils.getHearthHeight()) / 2;
        final List<Double> xDistances = new LinkedList<>();
        IntStream.range(0, completedHearth + halfHeath)
                .mapToDouble(x -> (ViewUtils.getLifeCanvasWidth() - imgBlock) / 2 + x * ViewUtils.getHearthWidth()
                        + x * ViewUtils.getLifeCanvasWidth() / ViewUtils.getHearthSpaceProportion())
                .forEach(x -> xDistances.add(x));
        IntStream.range(0, completedHearth)
                .forEach(x -> drawHitBoxImage(ImageType.FULL_HEART, new RectangularHitBox(xDistances.get(x), yDistance,
                        ViewUtils.getHearthHeight(), ViewUtils.getHearthWidth()), gcLifeCanvas));
        if (halfHeath == 1) {
            drawHitBoxImage(ImageType.HALF_HEART, new RectangularHitBox(xDistances.get(xDistances.size() - 1),
                    yDistance, ViewUtils.getHearthHeight(), ViewUtils.getHearthWidth()), gcLifeCanvas);
        }
        gcLifeCanvas.restore();
    }

    private Tupla<Double, Double> getScaledDimension(final Tupla<Double, Double> canvasSize,
            final Tupla<Double, Double> boundary, final Tupla<Double, Double> prop, final Tupla<Double, Double> max) {

        double canvasWidth = canvasSize.getX();
        double canvasHeight = canvasSize.getY();

        if (canvasSize.getX() > boundary.getX()) {
            canvasWidth = boundary.getX();
            canvasHeight = (canvasWidth * prop.getY()) / prop.getX();
        } else if (canvasSize.getY() > boundary.getY()) {
            canvasHeight = boundary.getY();
            canvasWidth = (prop.getX() * canvasHeight) / prop.getY();
        }
        if (canvasSize.getX() < boundary.getX() && boundary.getY() >= boundary.getX() * prop.getY() / prop.getX()) {
            canvasWidth = boundary.getX();
            canvasHeight = (canvasWidth * prop.getY()) / prop.getX();
        } else if (canvasSize.getY() < boundary.getY()
                && boundary.getX() >= boundary.getY() * prop.getX() / prop.getY()) {
            canvasHeight = boundary.getY();
            canvasWidth = (prop.getX() * canvasHeight) / prop.getY();
        }
        if (canvasHeight > max.getY()) {
            canvasHeight = max.getY();
        }
        if (canvasWidth > max.getX()) {
            canvasWidth = max.getX();
        }
        return new Tupla<Double, Double>(canvasWidth, canvasHeight);
    }

    private Tupla<Double, Double> computeScaleFactor(final Tupla<Double, Double> canvasSize,
            final Tupla<Double, Double> boundarySize) {
        return new Tupla<Double, Double>(canvasSize.getX() / boundarySize.getX(),
                canvasSize.getY() / boundarySize.getY());
    }

    private void drawHitBoxImage(final ImageType img, final CircleHitBox hBox, final GraphicsContext gc) {
        final double upperLeftX = hBox.getX() - hBox.getRadius();
        final double upperLeftY = hBox.getY() - hBox.getRadius();
        gc.drawImage(ProxyImageLoader.get().getImage(img), upperLeftX, upperLeftY, 2 * hBox.getRadius(),
                2 * hBox.getRadius());
    }

    private void drawHitBoxImage(final ImageType img, final RectangularHitBox hBox, final GraphicsContext gc) {
        gc.drawImage(ProxyImageLoader.get().getImage(img), hBox.getX(), hBox.getY(), hBox.getWidth(), hBox.getHeight());
    }
}
