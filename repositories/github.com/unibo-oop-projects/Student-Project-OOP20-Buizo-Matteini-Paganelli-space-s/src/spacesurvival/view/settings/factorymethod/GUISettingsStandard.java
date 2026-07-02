package spacesurvival.view.settings.factorymethod;

import spacesurvival.model.gui.settings.EngineSettings;
import spacesurvival.utilities.dimension.ScaleOf;
import spacesurvival.utilities.path.Background;
import spacesurvival.utilities.path.Icon;
import spacesurvival.utilities.DesignJComponent;
import spacesurvival.view.utilities.GraphicsLayoutUtils;
import spacesurvival.view.settings.FactoryGUISettings;
import spacesurvival.view.settings.GUISettings;
import spacesurvival.view.settings.concrete.ConcreteSettingsGUI;
import spacesurvival.view.utilities.FactoryGUIs;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.JPanel;

/**
 * Implements the creation of the compact settings GUI.
 */
public class GUISettingsStandard implements FactoryGUISettings {

    /**
     * {@inheritDoc}
     */
    @Override
    public GUISettings create() {
        final ConcreteSettingsGUI concreteSettings = new ConcreteSettingsGUI();
        concreteSettings.setFontTitleGUI(GraphicsLayoutUtils.getFontForTitle(GraphicsLayoutUtils.SIZE_FONT_H2));
        concreteSettings.setFontTitleUnit(GraphicsLayoutUtils.FONT_STANDARD_H4);
        concreteSettings.setFontGUI(GraphicsLayoutUtils.FONT_STANDARD_H5);
        concreteSettings.setForegroundGUI(GraphicsLayoutUtils.COLOR_4);
        concreteSettings.setBorder(GraphicsLayoutUtils.COLOR_4, 3);
        concreteSettings.setImageBackground(Background.MAIN);

        concreteSettings.setLayout(new BorderLayout());

        concreteSettings.add(FactoryGUIs.encapsulatesInPanelFlow(concreteSettings.getLabeTitle()), BorderLayout.NORTH);
        concreteSettings.add(FactoryGUIs.encapsulatesInPanelFlow(concreteSettings.getBtnBack()), BorderLayout.SOUTH);

        FactoryGUIs.setIconJButtonFromRate(concreteSettings.getBtnBack(), Icon.BACK,
                ScaleOf.ICON_MEDIUM, EngineSettings.RECTANGLE.width);
        concreteSettings.setTransparentComponent();

        final GridBagConstraints lim = FactoryGUIs.createGBConstraintsBase();

        final JPanel panelContainPanel = FactoryGUIs.createPanelTransparent(new GridBagLayout());

        panelContainPanel.add(FactoryGUIs.createPanelGridBagUnionComponentsVertical(List.of(
                FactoryGUIs.encapsulatesInPanelFlow(concreteSettings.getPanelSkin())), DesignJComponent.SETTINGS_INSET), lim);

        concreteSettings.add(panelContainPanel, BorderLayout.CENTER);
        return concreteSettings;
    }
}
