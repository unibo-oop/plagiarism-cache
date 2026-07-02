package spacesurvival.model.gui.scoreboard;

import spacesurvival.model.gui.EngineGUI;
import spacesurvival.model.gui.Visibility;
import spacesurvival.utilities.LinkActionGUI;
import spacesurvival.utilities.dimension.Screen;

import java.awt.Rectangle;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implements the model for the scoreboard GUI.
 */
public class EngineScoreboard implements EngineGUI {
    /**
     * Dimension of the scoreboard GUI.
     */
    public static final Rectangle RECTANGLE = Screen.RECTANGLE_MEDIUM;

    /**
     * Title of the scoreboard GUI.
     */
    public static final String TITLE = "SCOREBOARD";

    private final LinkActionGUI id;
    private final LinkActionGUI linkBack;

    private final List<NameScoreboardGUI> nameButtons;

    private Visibility visibility;

    /**
     * Constructor for a GUI scoreboard model.
     */
    public EngineScoreboard() {
        this.id = LinkActionGUI.LINK_SCOREBOARD;
        this.linkBack = LinkActionGUI.LINK_BACK;
        this.nameButtons = Arrays.asList(NameScoreboardGUI.values());
        this.visibility = Visibility.HIDDEN;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LinkActionGUI getMainLink() {
        return this.id;
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
    public void setVisibility(final Visibility visibility) {
        this.visibility = visibility;
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
        return List.of(this.linkBack);
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
     * Get LinkActionGUI for back button.
     * 
     * @return LinkActionGUI for back button.
     */
    public LinkActionGUI getBackLink() {
        return this.linkBack;
    }

    /**
     * Return a list of text button.
     * 
     * @return a list of text button.
     */
    public List<String> getListText() {
        return this.nameButtons.stream().map(NameScoreboardGUI::getText).collect(Collectors.toList());
    }
}
