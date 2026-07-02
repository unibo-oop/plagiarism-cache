package spacesurvival.model.gui.pause;

import spacesurvival.model.gui.EngineGUI;
import spacesurvival.model.gui.Visibility;
import spacesurvival.utilities.LinkActionGUI;
import spacesurvival.utilities.dimension.Screen;

import java.awt.Rectangle;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implements the model for the pause GUI.
 */
public class EnginePause implements EngineGUI {
    /**
     * Dimension of the pause GUI.
     */
    public static final Rectangle RECTANGLE = Screen.RECTANGLE_MINI;
    /**
     * Dimension of the pause GUI.
     */
    public static final String TITLE = "PAUSE";
    /**
     * Dimension of the pause GUI.
     */
    public static final int N_BUTTONS = LinksPause.values().length;

    private final LinkActionGUI mainAction;
    private final List<LinksPause>  linkButtons;

    private Visibility visibility;

    /**
     * Constructor for a GUI pause model.
     */
    public EnginePause() {
        this.mainAction = LinkActionGUI.LINK_PAUSE;
        this.linkButtons = Arrays.asList(LinksPause.values());
        this.visibility = Visibility.HIDDEN;
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
        return this.linkButtons.stream().map(LinksPause::getIdGUI).collect(Collectors.toList());
    }

    /**
     * Return the title of the pause GUI.
     * 
     * @return a string representing the pause GUI title
     */
    public String getTitleGUI() {
        return TITLE;
    }

    /**
     * Return a list of link's text.
     * 
     * @return a list of link's text.
     */
    public List<String> getListText() {
        return this.linkButtons.stream().map(LinksPause::getText).collect(Collectors.toList());
    }


}
