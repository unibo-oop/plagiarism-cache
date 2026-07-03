package view;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import controller.ModelWrapper;
import util.ImageLoaderProxy.ImageID;
import util.Pair;
import view.render.CanvasDrawer;
import view.render.CanvasDrawerImpl;
import view.util.ViewStaticUtilities;

/**
 * Implementation of GameView Interface.
 */
public final class ViewImpl implements View {

    private CanvasDrawer drawerReference;

    private static ViewImpl singleton;

    private ViewImpl() {
    }

    /**
     * Provides the lazy initialized singleton of this class.
     * 
     * @return the singleton of this class
     */
    public static ViewImpl get() {
        if (Objects.isNull(singleton)) {
            singleton = new ViewImpl();
        }
        return singleton;
    }

    @Override
    public void init(final boolean savedGame, final double width, final double height) {
        CanvasDrawerImpl.setModelSizes(width, height);
        this.foundSavedGame(savedGame);
        ViewManagerImpl.initMainGUI(Optional.empty());
    }

    @Override
    public void setDynamicCollection(final List<ModelWrapper> collection) {
        drawerReference.setDynamicCollection(collection);
        drawerReference.draw();
    }

    @Override
    public void setStaticCollection(final List<ModelWrapper> collection) {
        drawerReference.setStaticCollection(collection);
    }

    @Override
    public void setPlayerLife(final double life) {
        drawerReference.setPlayerLife(life);
    }

    @Override
    public void setMap(final Map<Pair<Integer, Integer>, ImageID> world) {
        drawerReference.setMap(world);
    }

    @Override
    public void roomChanging() {
        ViewManagerImpl.get().receiveGameEvent("rc");
    }

    @Override
    public void gameOver() {
        ViewManagerImpl.get().receiveGameEvent("go");
    }

    @Override
    public void foundSavedGame(final boolean value) {
        ViewManagerImpl.setGameRunning(value);
    }

    @Override
    public int getFPSSelected() {
        return ViewStaticUtilities.getSelectedFPS();
    }

    @Override
    public boolean isGodModeSelected() {
        return ViewStaticUtilities.isGodModeSelected();
    }

    /**
     * Set a CanvasDrawer variable.
     * 
     * @param drawer
     *            the object used to draw model objects on canvas.
     */
    public void setDrawerReference(final CanvasDrawer drawer) {
        this.drawerReference = drawer;
    }

}
