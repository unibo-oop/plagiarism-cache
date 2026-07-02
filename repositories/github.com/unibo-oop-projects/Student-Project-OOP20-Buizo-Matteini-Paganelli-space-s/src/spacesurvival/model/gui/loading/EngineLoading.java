package spacesurvival.model.gui.loading;

import spacesurvival.model.gui.EngineGUI;
import spacesurvival.model.gui.Visibility;
import spacesurvival.utilities.LinkActionGUI;
import spacesurvival.utilities.dimension.Screen;
import java.awt.Rectangle;
import java.util.List;

public class EngineLoading implements EngineGUI {
    /**
     * Rectangle representing the full screen.
     */
    public static final Rectangle RECTANGLE = Screen.RECTANGLE_FULLSCREEN;

    /**
     * Title of the loading GUI.
     */
    public static final String TITLE = "SPACE SURVIVAL";

    private final LinkActionGUI mainAction;

    private int loading;
    private boolean load;

    private Visibility visibility;

    /**
     * Constructor for a GUI loading model.
     */
    public EngineLoading() {
        this.mainAction = LinkActionGUI.LINK_LOADING;
        this.loading = 0;
        this.load = false;
        this.visibility = Visibility.VISIBLE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LinkActionGUI getMainLink() {
        return this.mainAction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Rectangle getRectangle() {
        return RECTANGLE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Visibility getVisibility() {
        return this.visibility;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setVisibility(final Visibility state) {
        this.visibility = state;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isVisible() {
        return this.visibility.isVisible();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<LinkActionGUI> getLinks() {
        return List.of();
    }

    /**
     * Return the title of the loading GUI.
     * 
     * @return a string representing the loading GUI title
     */
    public String getTitleGUI() {
        return TITLE;
    }

    /**
     * Increment the loading bar.
     */
    public void incrLoading() {
        this.loading++;
    }

    /**
     * Get the loading progress.
     * 
     * @return an int representing the loading progress
     */
    public int getLoading() {
        return this.loading;
    }

    /**
     * Set the load true.
     */
    public void load() {
        this.load = true;
    }

    /**
     * Return the loading state.
     * 
     * @return true if is loaded
     */
    public boolean isLoad() {
        return this.load;
    }
}
