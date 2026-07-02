package bubbleshooter.view.scene.controller;

import bubbleshooter.controller.Controller;
import bubbleshooter.controller.input.HandlerAdapterMouseMoved;
import bubbleshooter.model.bubble.BubbleType;
import bubbleshooter.utility.PhysicHelper;
import bubbleshooter.utility.Settings;
import bubbleshooter.view.View;
import bubbleshooter.view.cannon.Cannon;
import bubbleshooter.view.cannon.DrawCannon;
import bubbleshooter.view.helpline.DrawHelpLine;
import bubbleshooter.view.images.ImagePath;
import bubbleshooter.view.rendering.BubbleDrawer;
import bubbleshooter.view.scene.FXMLPath;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * The Controller related to the game.fxml GUI.
 */
public class GameController extends AbstractController {

    private static final double MAXANGLE = 74.9;
    private static final double MINANGLE = -74.9;
    private static final double LIMITS = Settings.getGuiHeight() / 1.1;

    @FXML private Canvas canvas;
    @FXML private AnchorPane pane;
    @FXML private CheckBox helpCheckBox;
    @FXML private Button switchButton;

    private BubbleDrawer canvasDrawer;
    private DrawHelpLine drawHelpLine;
    private HandlerAdapterMouseMoved handlerAdapter;

    @Override
    public final void init(final Controller controller, final View view) {
        final DrawCannon drawCannon;
        final Point2D shootingBubblePosition;
        this.setController(controller);
        this.setView(view);

        shootingBubblePosition = new Point2D(
                this.getController().getBubbles().stream().filter(a -> a.getType().equals(BubbleType.SHOOTING_BUBBLE))
                        .findFirst().get().getPosition().getX() * (Settings.getGuiWidth() / this.getController().getWorldWidth()),
                this.getController().getBubbles().stream().filter(a -> a.getType().equals(BubbleType.SHOOTING_BUBBLE))
                        .findFirst().get().getPosition().getY() * (Settings.getGuiHeight() / this.getController().getWorldHeight()));

        this.drawHelpLine = new DrawHelpLine(this.pane, shootingBubblePosition);
 
        drawCannon = new DrawCannon(
                this.pane, 
                new Cannon(new Image(ImagePath.CANNON.getPath())), 
                this.getController(), this.getController().getBubbles().stream().filter(a -> a.getType().equals(BubbleType.SHOOTING_BUBBLE))
                .findFirst().get().getPosition(), 
                this.getController().getBubbles().stream().filter(a -> a.getType().equals(BubbleType.SHOOTING_BUBBLE))
                .findFirst().get().getRadius());

        this.handlerAdapter = new HandlerAdapterMouseMoved(drawCannon.getRotation(),
                this.drawHelpLine.getRotation(), new Point2D(this.drawHelpLine.getHelpLine().getStartX(),
                this.drawHelpLine.getHelpLine().getStartY()), this.drawHelpLine);

        this.pane.setOnMouseMoved(this.handlerAdapter);
        this.pane.setOnMouseDragged(this.handlerAdapter);
        this.pane.setOnMouseClicked(this.handlerAdapter);

        this.canvasDrawer = new BubbleDrawer(this.canvas);
        this.controlSwitchButton();

        this.pane.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(final MouseEvent event) {
                if (getController().getBubbles().stream().filter(a -> a.getType().equals(BubbleType.SHOOTING_BUBBLE))
                        .findFirst().get().getPosition().getX() == getController().getBubbles().stream()
                        .filter(a -> a.getType().equals(BubbleType.SHOOTING_BUBBLE)) .findFirst().get().getPosition().getX()
                        && checkAngle(handlerAdapter.getRotationAngle()) && event.getY() < LIMITS) {

                    getController().getBubbles().stream().filter(a -> a.getType().equals(BubbleType.SHOOTING_BUBBLE))
                    .findFirst().get().setDirection(PhysicHelper.calculateShootingDirection(
                            new Point2D(event.getX() * (getController().getWorldWidth() / Settings.getGuiWidth()),
                                    event.getY() * (getController().getWorldHeight() / Settings.getGuiHeight())),
                            getController().getBubbles().stream().filter(a -> a.getType().equals(BubbleType.SHOOTING_BUBBLE))
                            .findFirst().get().getPosition()));
                }
            }
        });
    }

    @Override
    public final void render() {
        this.resetCanvas();
        canvasDrawer.draw(this.getController().getBubbles());
    }

    /**
     * Method used to switch the {@link ShootingBubble}.
     */
    public final void switchBall() {
        this.getController().getSwitcherController().switchControl();
        this.controlSwitchButton();
    }

    /**
     * Method used to manage the button for the switch function.
     */
    public final void controlSwitchButton() {
        if (this.getController().getSwitcherController().isSwitchEnd()) {
            this.switchButton.setText("Ended");
            this.switchButton.setMouseTransparent(true);
        }
    }

    /**
     * Method used to manage the {@link HelpLine}.
     */
    public final void helpSelected() {
        if (this.helpCheckBox.isSelected()) {
            this.drawHelpLine.drawLine();
        } else {
            this.drawHelpLine.deleteLine();
        }
    }

    /**
     * Method to pause the game and load the pause scene.
     */
    public final void pause() {
        this.getController().getGameEngine().pauseLoop();
        this.setNextScene(FXMLPath.PAUSE);
        this.loadNextScene();
    }

    /**
     * Method used to restart the level.
     */
    public final void restart() {
        this.getController().getGameEngine().pauseLoop();
        this.getController().startGame(this.getController().getCurrentLevel());
        this.getController().getGameEngine().resumeLoop();
        this.getController().getSwitcherController().setInitialNumSwitch();
        this.switchButton.setText("Switch");
        this.switchButton.setMouseTransparent(false);
    }

    /**
     * @param angle The angle of rotation of the cannon.
     * @return false if the cannon goes below shooting bubble position.
     */
    public final boolean checkAngle(final double angle) {
        return !(angle > MAXANGLE || angle < MINANGLE);
    }

     /* 
     * Resets the canvas.
     */
    private void resetCanvas() {
        final GraphicsContext gc = this.canvas.getGraphicsContext2D();
        gc.restore();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.save();
        gc.scale(Settings.getGuiWidth() / this.getController().getWorldWidth(), Settings.getGuiHeight() / this.getController().getWorldHeight());

    }

}
