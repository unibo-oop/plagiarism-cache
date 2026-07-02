package spacesurvival.model.gui.settings;

import spacesurvival.model.gui.Visibility;
import spacesurvival.model.EngineImage;
import spacesurvival.model.gui.EngineGUI;
import spacesurvival.utilities.LinkActionGUI;
import spacesurvival.utilities.dimension.ScaleOf;
import spacesurvival.utilities.dimension.Screen;

import java.awt.Rectangle;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implements the model for the settings GUI.
 */
public class EngineSettings implements EngineGUI {
    /**
     * Dimension of the setting GUI.
     */
    public static final Rectangle RECTANGLE = Screen.RECTANGLE_MEDIUM;
    /**
     * Title of the setting GUI.
     */
    public static final String TITLE = "SETTINGS";

    /**
     * Left direction text for button.
     */
    public static final String DIR_SX = "<";

    /**
     * Right direction text for button.
     */
    public static final String DIR_DX = ">";

    /**
     * Starting skin index.
     */
    public static final int INDEX_INIT_SKIN = 0;

    /**
     * Step for change skin.
     */
    public static final int STEP_INDEX_SKIN = 1;

    private final LinkActionGUI mainAction;
    private final LinkActionGUI actionBack;
    private final List<UnitSettingsGUI> unitNames;

    private final List<SkinSpaceShip> listSkin;
    private final EngineImage skin;
    private int chooseSkin;

    private Visibility visibility = Visibility.HIDDEN;

    /**
     * Constructor for a GUI settings model.
     */
    public EngineSettings() {
        this.mainAction = LinkActionGUI.LINK_SETTING;
        this.actionBack = LinkActionGUI.LINK_BACK;

        this.listSkin = Arrays.asList(SkinSpaceShip.values());
        this.chooseSkin = INDEX_INIT_SKIN;
        this.skin = new EngineImage(ScaleOf.ICON_SKIN, (int) RECTANGLE.getWidth(), this.listSkin.get(chooseSkin).getSkin());

        this.unitNames = List.of(UnitSettingsGUI.values());
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
        return List.of(this.actionBack);
    }

    /**
     * Get back button link.
     * @return LinkActionGUI.
     */
    public LinkActionGUI getBackLink() {
        return this.actionBack;
    }

    /**
     * Return the title of the settings GUI.
     * 
     * @return a string representing the settings GUI title
     */
    public String getTitleGUI() {
        return TITLE;
    }

    /**
     * List of text for units settings.
     * @return List String.
     */
    public List<String> getListTextUnit() {
        return this.unitNames.stream().map(UnitSettingsGUI::getTitle).collect(Collectors.toList());
    }

    /**
     * Get text for back button.
     * @return text of button.
     */
    public String getTextBtnBack() {
        return this.actionBack.getIdName();
    }

    /**
     * Get skin spaceship.
     * @return SkinSpaceShip.
     */
    public SkinSpaceShip getSkinShip() {
        return this.listSkin.get(this.chooseSkin);
    }

    /**
     * Get engineImage of skin.
     * @return EngineImage.
     */
    public EngineImage getEngineSkinShip() {
        this.skin.setPath(this.listSkin.get(this.chooseSkin).getSkin());
        return this.skin;
    }

    /**
     * Change skin direction right.
     */
    public void changeSkinDx() {
        this.chooseSkin = this.chooseSkin + STEP_INDEX_SKIN < SkinSpaceShip.values().length 
                ? this.chooseSkin + STEP_INDEX_SKIN : INDEX_INIT_SKIN;
    }

    /**
     * Change skin direction left.
     */
    public void changeSkinSx() {
        this.chooseSkin = this.chooseSkin - STEP_INDEX_SKIN >= INDEX_INIT_SKIN 
                ? this.chooseSkin - STEP_INDEX_SKIN : SkinSpaceShip.values().length - STEP_INDEX_SKIN;
    }
}
