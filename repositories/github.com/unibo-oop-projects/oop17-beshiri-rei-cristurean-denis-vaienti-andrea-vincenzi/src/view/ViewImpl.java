package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import controller.event.KeyEvent;
import controller.observer.ButtonObserver;
import controller.observer.KeyObserver;
import controller.observer.Observer;
import controller.utility.Score;
import javafx.application.Application;
import javafx.application.Platform;
import controller.event.ButtonEvent;
import controller.event.Event;
import model.animated.Animated;
import model.room.Room;
import utility.Statistic;
import view.utility.SceneFactory;
import view.utility.ViewUtils;

/**
 * Class that represent view.
 *
 */
public final class ViewImpl implements View {

    private final List<Observer> observers;
    private DrawerManager drawer;
    private static View view;

    /**
     * Private constructor for this class.
     */
    private ViewImpl() {
        observers = new ArrayList<>();
    }

    /**
     * Singleton for ViewImpl.
     * 
     * @return Only instance of this class.
     */
    public static View get() {
        if (Objects.isNull(view)) {
            view = new ViewImpl();
        }
        return view;
    }

    /**
     * Set animated entities list for drawer manager.
     * 
     */
    @Override
    public void render(final List<Animated> list) {
        drawer.setAnimatedEntities(new ArrayList<>(list));
        Platform.runLater(() -> drawer.draw());
    }

    /**
     * Set room for drawer manager.
     */
    @Override
    public void roomChanged(final Room room) {
        drawer.setRoom(room);
    }

    /**
     * Set player life for drawer manager.
     */
    @Override
    public void playerLifeChanged(final int life) {
        drawer.setPlayerLife(life);
    }

    /**
     * Entry method to start view.
     */
    @Override
    public void viewStart() {
        Application.launch(LaunchClass.class, "");
    }

    /**
     * Event that notify events to controller.
     */
    @Override
    public void notifyEvent(final Event e) {
        if (e instanceof KeyEvent) {
            observers.stream().filter(x -> x instanceof KeyObserver).forEach(x -> ((KeyObserver) x).notifyEvent(e));
        } else if (e instanceof ButtonEvent) {
            observers.stream().filter(x -> x instanceof ButtonObserver)
                    .forEach(x -> ((ButtonObserver) x).notifyEvent(e));
        }
    }

    /**
     * Used to add observer for events.
     */
    @Override
    public void addObserver(final Observer obs) {
        observers.add(obs);
    }

    /**
     * Set drawer reference.
     */
    @Override
    public void setDrawer(final DrawerManager drawer) {
        this.drawer = drawer;
    }

    /**
     * Called when resize event occur.
     */
    @Override
    public void redraw() {
        Platform.runLater(() -> {
            drawer.resize();
            drawer.redrawAfterResize();
        });
    }

    /**
     * Set scoreboard list.
     */
    @Override
    public void setScoreBoard(final List<Score> score) {
        ViewUtils.setScoreBoard(score);
    }

    /**
     * Return status of god mode.
     */
    @Override
    public boolean isGodModeSelected() {
        return ViewUtils.isGodModeSelected();
    }

    /**
     * Return if infinity mode is selected.
     */
    @Override
    public boolean isSurvivalModeSelected() {
        return ViewUtils.isSurvivalModeSelected();
    }

    /**
     * Return reference to drawer manager.
     */
    @Override
    public DrawerManager getDrawerReference() {
        return drawer;
    }

    /**
     * Set initial height.
     */
    @Override
    public void setInitialHeight(final double h) {
        ViewManagerImpl.get().setHeight(h);
    }

    /**
     * Set initial width.
     */
    @Override
    public void setInitialWidth(final double w) {
        ViewManagerImpl.get().setWidth(w);
    }

    /**
     * Setter for world height in view utils.
     */
    @Override
    public void setWorldHeight(final double wh) {
        ViewUtils.setWorldHeight(wh);
    }

    /**
     * Setter for world width in view utils.
     */
    @Override
    public void setWorldWidth(final double ww) {
        ViewUtils.setWorldWidth(ww);
    }

    /**
     * Setter for world height proportion in view utils.
     */
    @Override
    public void setWorldHeightProportion(final double whProp) {
        ViewUtils.setWorldHeightProp(whProp);
    }

    /**
     * Setter for world width proportion in view utils.
     */
    @Override
    public void setWorldWidthProportion(final double wwProp) {
        ViewUtils.setWorldWidthProp(wwProp);
    }

    /**
     * Setter for wall minor dimension.
     */
    @Override
    public void setIWallMinorDimension(final double wallDimension) {
        ViewUtils.setWallMinorDimension(wallDimension);
    }

    /**
     * Method used to clear time canvas.
     */
    @Override
    public void initTimeCanvas() {
        if (!Objects.isNull(drawer)) {
            Platform.runLater(() -> drawer.initTimeCanvas());
        }
    }

    /**
     * Method used to notify that player is died.
     */
    @Override
    public void notifyGameOverEvent(final int points) {
        ViewUtils.setPoints(points);
        //ViewManagerImpl.get().pop();
        ViewManagerImpl.get().push(SceneFactory.createLostScene());
    }

    /**
     * Method used to notify victory of game by player.
     */
    @Override
    public void notifyVictoryGameEvent(final int points) {
        ViewUtils.setPoints(points);
        //ViewManagerImpl.get().pop();
        ViewManagerImpl.get().push(SceneFactory.createVictoryScene());
    }

    /**
     * Method used to set stats.
     */
    @Override
    public void refreshPlayerStats(final Statistic stats) {
        ViewUtils.setStats(stats);
    }
}
