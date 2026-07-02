package spacesurvival.model.gui.help;

import spacesurvival.model.EngineImage;
import spacesurvival.utilities.dimension.ScaleOf;
import spacesurvival.utilities.path.Helps;
import spacesurvival.utilities.DesignJComponent;

import java.util.List;

/**
 * Implements enumeration of help units.
 */
public enum UnitsHelp {

    /**
     * Move help unit.
     */
    PANEL_MOVE(DesignJComponent.NAME_UNIT_HELP_MOVEMENT, List.of(
            new EngineImage(ScaleOf.ICON_HELP_PLURAL, EngineHelp.RECTANGLE.width, Helps.WASD),
            new EngineImage(ScaleOf.ICON_HELP_PLURAL, EngineHelp.RECTANGLE.width, Helps.ROW))),

    /**
     * Shot help unit.
     */
    PANEL_SHOT(DesignJComponent.NAME_UNIT_HELP_SHOT, List.of(
            new EngineImage(ScaleOf.ICON_HELP_PLURAL, EngineHelp.RECTANGLE.width, Helps.SPACEBAR),
            new EngineImage(ScaleOf.ICON_HELP_SINGULAR, EngineHelp.RECTANGLE.width, Helps.KEY_K))),

    /**
     * Pause help unit.
     */
    PANEL_PAUSE(DesignJComponent.NAME_UNIT_HELP_PAUSE, List.of(
            new EngineImage(ScaleOf.ICON_HELP_SINGULAR, EngineHelp.RECTANGLE.width, Helps.PAUSE_KEY)));

    private final String title;
    private final List<EngineImage> pathFiles;

    /**
     * Create a help unit from a title and an engineImage list.
     * 
     * @param title for help unit.
     * @param pathFiles a list of enigineImage.
     */
    UnitsHelp(final String title, final List<EngineImage> pathFiles) {
        this.title = title;
        this.pathFiles = pathFiles;
    }

    /**
     * Get name for title.
     * 
     * @return text title.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Get a list of engineImage.
     * 
     * @return List of EngineImage a list of engineImage.
     */
    public List<EngineImage> getPathFiles() {
        return this.pathFiles;
    }

    /**
     * Describes the help unit with its title and images.
     */
    @Override
    public String toString() {
        return "HelpNamePanels{" 
                + "name='" +  title + '\''
                + ", pathFiles=" + pathFiles + '}';
    }
}
