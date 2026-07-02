package tmw.view.level;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Optional;

import javafx.scene.Camera;
import javafx.scene.ParallelCamera;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import tmw.common.CharacterStates;
import tmw.common.Dim2D;
import tmw.common.EntityDirection;
import tmw.common.Rec2D;
import tmw.controller.item.AbstractItemController;
import tmw.model.entities.GameEntity;
import tmw.view.MainView;

/**
 * Represents the view component of a gameWorld that is not loaded through fxml
 * classes. This class provides methods common to all levels. It should be
 * extended to create different view component for each level.
 * 
 * @version 1.3
 */
public class RoomView extends Observable implements GameLevelView {

    private final Scene scene;
    private final Canvas canvas;
    private final Camera camera;
    private final StackPane stack;
    private final MainView mainView;
    private Optional<Parent> hud = Optional.empty();
    private final List<CharacterStates> commandList = new LinkedList<>();

    private Image background;

    /**
     * Public constructor.
     * 
     * @param view      {@link MainView} main view reference
     * @param sceneSize {@link Dimension2D} world area dimension
     */
    public RoomView(final MainView view, final Dim2D sceneSize) {
        super();
        this.mainView = view;
        this.camera = new ParallelCamera();
        this.stack = new StackPane();
        this.scene = new Scene(stack, sceneSize.getWidth(), sceneSize.getHeight());
        this.canvas = new Canvas(sceneSize.getWidth(), sceneSize.getHeight());
        view.setCanvas(canvas);
        this.stack.getChildren().addAll(canvas);
        this.scene.setCamera(camera);
        listenUserInput();
        this.mainView.getStage().centerOnScreen();

        view.getStage().maximizedProperty().addListener(e -> {
            if (view.getStage().isMaximized()) {
                view.getStage().setFullScreen(false);
                view.getStage().setFullScreen(true);
                setChanged();
                notifyObservers(LevelViewEvents.FULLSCREEN);
            }
        });
    }

    @Override
    public final Scene getLevelScene() {
        return this.scene;
    }

    @Override
    public final Camera getCamera() {
        return this.camera;
    }

    @Override
    public final Optional<Parent> getHud() {
        return this.hud;
    }

    @Override
    public final void setHud(final Parent hud) {
        this.hud = Optional.ofNullable(hud);
        this.stack.getChildren().add(this.hud.get());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final GameEntity entity, final Rec2D boundary) {
        this.mainView.render(entity, boundary);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final GameEntity entity, final EntityDirection direction, final Rec2D boundary) {
        this.mainView.render(entity, direction, boundary);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final AbstractItemController itemController) {
        this.mainView.render(itemController);
    }

    @Override
    public final Dim2D getGameRes() {
        return this.mainView.getActualGameResolution();
    }

    @Override
    public final List<CharacterStates> getCommandList() {
        return this.commandList;
    }

    @Override
    public final MainView getMainView() {
        return this.mainView;
    }

    @Override
    public final void resetViewStack() {
        this.canvas.getGraphicsContext2D().clearRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());
        this.canvas.getGraphicsContext2D().drawImage(background, 0, 0, this.canvas.getWidth(), this.canvas.getHeight());
    }

    /**
     * Method to read user input.
     */
    private void listenUserInput() {
        this.scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
            case LEFT:
                this.commandList.add(CharacterStates.SHOOT_LEFT);
                break;
            case RIGHT:
                this.commandList.add(CharacterStates.SHOOT_RIGHT);
                break;
            case UP:
                this.commandList.add(CharacterStates.SHOOT_UP);
                break;
            case DOWN:
                this.commandList.add(CharacterStates.SHOOT_DOWN);
                break;
            case W:
                this.commandList.add(CharacterStates.MOVE_UP);
                break;
            case A:
                this.commandList.add(CharacterStates.MOVE_LEFT);
                break;
            case S:
                this.commandList.add(CharacterStates.MOVE_DOWN);
                break;
            case D:
                this.commandList.add(CharacterStates.MOVE_RIGHT);
                break;
            case DIGIT1:
                this.commandList.add(CharacterStates.ITEM1);
                break;
            case DIGIT2:
                this.commandList.add(CharacterStates.ITEM2);
                break;
            case DIGIT3:
                this.commandList.add(CharacterStates.ITEM3);
                break;
            case DIGIT4:
                this.commandList.add(CharacterStates.ITEM4);
                break;
            case DIGIT5:
                this.commandList.add(CharacterStates.ITEM5);
                break;
            case ENTER:
                setChanged();
                notifyObservers(LevelViewEvents.ZOOM);
                break;
            default:
                break;
            }
        });
    }

    @Override
    public final void setBackground(final Image img) {
        this.background = img;
    }
}

