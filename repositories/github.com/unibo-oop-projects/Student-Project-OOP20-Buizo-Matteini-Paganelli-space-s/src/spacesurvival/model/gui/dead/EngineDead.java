package spacesurvival.model.gui.dead;

import spacesurvival.model.gui.EngineGUI;
import spacesurvival.model.gui.Visibility;
import spacesurvival.utilities.LinkActionGUI;
import spacesurvival.utilities.dimension.Screen;

import java.awt.Rectangle;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implements the model for the dead GUI.
 */
public class EngineDead implements EngineGUI {
    /**
     * Dimension of the dead GUI.
     */
    public static final Rectangle RECTANGLE = Screen.RECTANGLE_FULLSCREEN;

    /**
     * Title of the dead GUI.
     */
    public static final String TITLE = "GAME OVER";

    /**
     * Number of buttons of the menu GUI.
     */
    public static final int N_BUTTONS = LinksDead.values().length;

    private final LinkActionGUI mainAction;
    private final List<LinksDead> linkButtons;

    private Visibility visibility;

    /**
     * Constructor for a GUI dead model.
     */
    public EngineDead() {
        this.mainAction = LinkActionGUI.LINK_DEAD;

        this.linkButtons = Arrays.asList(LinksDead.values());
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
        return EngineDead.RECTANGLE;
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
    public List<LinkActionGUI> getLinks() {
        return this.linkButtons.stream().map(LinksDead::getAction).collect(Collectors.toList());
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
     * Return the title of the menu GUI.
     * 
     * @return a string representing the menu GUI title
     */
    public String getTitleGUI() {
        return TITLE;
    }

    /**
     * Return a list of link's text.
     * 
     * @return a list of link's text.
     */
    public List<String> getListTextLinks() {
        return this.linkButtons.stream().map(LinksDead::getText).collect(Collectors.toList());
    }
}
