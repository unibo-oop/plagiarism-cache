package spacesurvival.model.gui.help;

import spacesurvival.model.gui.Visibility;
import spacesurvival.model.EngineImage;
import spacesurvival.model.gui.EngineGUI;
import spacesurvival.utilities.DesignJComponent;
import spacesurvival.utilities.LinkActionGUI;
import spacesurvival.utilities.dimension.Screen;

import java.awt.Rectangle;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implements the model for the help GUI.
 */
public class EngineHelp implements EngineGUI {
    /**
     *  Dimension of the help GUI.
     */
    public static final Rectangle RECTANGLE = Screen.RECTANGLE_MEDIUM;

    /**
     * Number of help unit.
     */
    public static final int N_UNIT = UnitsHelp.values().length;

    /**
     * Title of the menu GUI.
     */
    public static final String TITLE = "HELP";

    private final LinkActionGUI mainAction;
    private final LinkActionGUI actionBack;

    private final List<UnitsHelp> listNameUnits;
    private final List<String> listName;

    private Visibility visibility;

    /**
     * Constructor for a GUI help model.
     */
    public EngineHelp() {
        this.mainAction = LinkActionGUI.LINK_HELP;
        this.actionBack = LinkActionGUI.LINK_BACK;
        this.listName = List.of(DesignJComponent.STRING_BACK_BUTTON);
        this.listNameUnits = Arrays.asList(UnitsHelp.values());
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
    public List<LinkActionGUI> getLinks() {
        return List.of(this.actionBack);
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
     * Return the title of the help GUI.
     * 
     * @return a string representing the help GUI title
     */
    public String getTitle() {
        return TITLE;
    }

    /**
     * Get LinkActionGUI for back button.
     * 
     * @return LinkActionGUI for back button.
     */
    public LinkActionGUI getBackLink() {
        return this.actionBack;
    }

    /**
     * Get a list of text for title help unit.
     * @return List of String a list of text for title help
     */
    public List<String> getListTitleUnits() {
        return this.listNameUnits.stream().map(UnitsHelp::getTitle).collect(Collectors.toList());
    }

    /**
     * Get a list of text for buttons.
     * @return List of String  a list of text.
     */
    public List<String> getListTextButtons() {
        return this.listName;
    }

    /**
     * Get a list of engineImage for image from title help unit.
     * @param unitTItle for search engineImage.
     * @return List of EngineImage a list of engineImage.
     */
    public List<EngineImage> getPathIconUnit(final String unitTItle) {
        return this.listNameUnits.stream().filter(unit -> unit.getTitle().contentEquals(unitTItle))
                .map(UnitsHelp::getPathFiles)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }
}
