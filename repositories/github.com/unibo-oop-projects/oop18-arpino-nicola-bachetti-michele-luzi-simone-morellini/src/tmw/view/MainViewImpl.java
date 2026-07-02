package tmw.view;

import javafx.event.EventHandler;
import javafx.geometry.Dimension2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import tmw.common.Dim2D;
import tmw.common.EntityDirection;
import tmw.common.Rec2D;
import tmw.controller.item.AbstractItemController;
import tmw.model.entities.GameEntity;
import tmw.model.entities.GameEntity.GameEntityType;
import tmw.model.item.consumable.HealingItemType;
import tmw.model.item.equipment.EquipmentType;
import tmw.model.item.powerup.PowerUpType;
import tmw.model.objects.BaseGameObject;
import tmw.model.objects.EscapeDoor;
import tmw.model.objects.Obstacle;

/**
 * This is the main view of the game. Manages game rendering; for each content
 * that asks to be rendered, this class knows how to do it. It's the only one
 * class which has access to game textures.
 * 
 * @version 1.4
 */

public class MainViewImpl implements MainView {

    private final Stage mainStage;
    private Canvas canvas;
    private GraphicsContext gc;
    private Dimension2D defaultGameResolution;
    private Dimension2D actualGameResolution;

    /**
     * Public constructor.
     * 
     * @param anyStage    {@link Stage} Stage in which main view should act
     * @param gameViewRes {@link Dimension2D} Game resolution
     */
    public MainViewImpl(final Stage anyStage, final Dimension2D gameViewRes) {

        this.mainStage = anyStage;
        this.defaultGameResolution = gameViewRes;
        this.actualGameResolution = defaultGameResolution;
        this.canvas = new Canvas(this.defaultGameResolution.getWidth(), this.defaultGameResolution.getHeight());
        this.canvas.widthProperty().bind(mainStage.widthProperty());
        this.canvas.heightProperty().bind(mainStage.heightProperty());
        this.gc = canvas.getGraphicsContext2D();
        mainStage.show();

        mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(final WindowEvent event) {
                exit();
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void exit() {
        System.exit(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateGameResolution() {
        if (this.mainStage.isFullScreen()) {
            this.actualGameResolution = new Dimension2D(this.mainStage.getMaxWidth(), this.mainStage.getMaxHeight());
        } else {
            this.actualGameResolution = defaultGameResolution;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final Image objImage, final Rec2D box) {
        this.gc.drawImage(objImage, box.getMinX(), box.getMinY(), box.getWidth(), box.getHeight());
//        this.gc.fillRect(box.getMinX(), box.getMinY(), box.getWidth(), box.getHeight());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final BaseGameObject obj) {

        if (obj instanceof EscapeDoor) {
            if (((EscapeDoor) obj).isTriggered()) {
                this.render(utils.ImageUtils.getDoorOpened(), obj.getBoundary());
            } else {
                this.render(utils.ImageUtils.getDoorClosed(), obj.getBoundary());
            }

            return;
        }

        if (obj instanceof Obstacle) {
            this.render(utils.ImageUtils.getObstacle(), obj.getBoundary());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final GameEntity entity, final EntityDirection direction, final Rec2D boundary) {

        if (entity.getType().equals(GameEntityType.MILK)) {
            this.render(utils.ImageUtils.getMilkImages().get(this.indexOfImage(direction)), boundary);
        }

        if (entity.getType().equals(GameEntityType.ABBRACCIO)) {
            this.render(utils.ImageUtils.getAbbraccioImages().get(this.indexOfImage(direction)), boundary);
        }

        if (entity.getType().equals(GameEntityType.STELLA)) {
            this.render(utils.ImageUtils.getStelleImages().get(this.indexOfImage(direction)), boundary);
        }

        if (entity.getType().equals(GameEntityType.BULLET)) {
            this.render(utils.ImageUtils.getEnemyBullet(), boundary);
        }

        if (entity.getType().equals(GameEntityType.PLAYER_BULLET)) {
            this.render(utils.ImageUtils.getPlayerBullet(), boundary);
        }

        if (entity.getType().equals(GameEntityType.BOSS)) {
            this.render(utils.ImageUtils.getGoccioleImages().get(this.indexOfImage(direction)), boundary);
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final AbstractItemController itemController) {

        /**
         * Checks all consumable items.
         */
        if (itemController.getItem().getName().equals(HealingItemType.LACTOSE_FREE_MILK.getName())) {
            this.render(utils.ImageUtils.getLactoseFreeMilk(), itemController.getItem().getBoundary());
        }

        if (itemController.getItem().getName().equals(HealingItemType.WHOLE_MILK.getName())) {
            this.render(utils.ImageUtils.getWholeMilk(), itemController.getItem().getBoundary());
        }

        if (itemController.getItem().getName().equals(HealingItemType.SKIMMED_MILK.getName())) {
            this.render(utils.ImageUtils.getSkimmedMilk(), itemController.getItem().getBoundary());
        }

        /**
         * Checks all equipments items.
         */
        if (itemController.getItem().getName().equals(EquipmentType.CHOCOLATE.getName())) {
            this.render(utils.ImageUtils.getChocolate(), itemController.getItem().getBoundary());
        }

        if (itemController.getItem().getName().equals(EquipmentType.COFFEE.getName())) {
            this.render(utils.ImageUtils.getCoffe(), itemController.getItem().getBoundary());
        }

        /**
         * Checks all powerUp items.
         */
        if (itemController.getItem().getName().equals(PowerUpType.SUGAR_CANE.getName())) {
            this.render(utils.ImageUtils.getSugarCane(), itemController.getItem().getBoundary());
        }

        if (itemController.getItem().getName().equals(PowerUpType.WHITE_SUGAR.getName())) {
            this.render(utils.ImageUtils.getSugar(), itemController.getItem().getBoundary());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final GameEntity entity, final Rec2D boundary) {

        if (entity.getType().equals(GameEntityType.BULLET)) {
            this.render(utils.ImageUtils.getEnemyBullet(), boundary);
        }

        if (entity.getType().equals(GameEntityType.PLAYER_BULLET)) {
            this.render(utils.ImageUtils.getPlayerBullet(), boundary);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dim2D getActualGameResolution() {
        return new Dim2D(this.actualGameResolution.getWidth(), this.actualGameResolution.getHeight());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCanvas(final Canvas canvas) {
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Canvas getCanvas() {
        return this.canvas;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Stage getStage() {
        return this.mainStage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dim2D getDefaultGameResolution() {
        return new Dim2D(this.defaultGameResolution.getWidth(), this.defaultGameResolution.getHeight());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setGameResolution(final double width, final double height) {
        this.defaultGameResolution = new Dimension2D(width, height);
        this.actualGameResolution = this.defaultGameResolution;
    }

    /**
     * This private method gives the correct image related to a specific direction.
     * 
     * @param direction {@link EntityDirection} actual direction
     * @return int value
     */
    private int indexOfImage(final EntityDirection direction) {
        switch (direction) {
        case GOING_DOWN:
            return 0;
        case GOING_LEFT:
            return 1;
        case GOING_UP:
            return 2;
        case GOING_RIGHT:
            return 3;
        default:
            return 0;
        }
    }
}
