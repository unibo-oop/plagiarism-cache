package spacesurvival.view.menu.factorymethod;

import spacesurvival.model.gui.menu.EngineMenu;
import spacesurvival.utilities.DesignJComponent;
import spacesurvival.utilities.dimension.ScaleOf;
import spacesurvival.view.utilities.GraphicsLayoutUtils;
import spacesurvival.view.menu.FactoryGUIMenu;
import spacesurvival.view.menu.GUIMenu;
import spacesurvival.view.menu.concrete.ConcreteMenuGUI;
import spacesurvival.view.menu.utilities.IconsButton;
import spacesurvival.view.utilities.FactoryGUIs;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

/**
 * Implements the creation of the compact menu GUI.
 */
public class GUIMenuCompact implements FactoryGUIMenu {

    /**
     * {@inheritDoc}
     */
    @Override
    public GUIMenu create() {
        final ConcreteMenuGUI menuConcrete = new ConcreteMenuGUI();

        menuConcrete.setFontGUI(GraphicsLayoutUtils.FONT_STANDARD_H5);
        menuConcrete.setForegroundGUI(GraphicsLayoutUtils.COLOR_4);
        menuConcrete.setFontTitleGUI(GraphicsLayoutUtils.getFontForTitle(GraphicsLayoutUtils.SIZE_FONT_H2));
        menuConcrete.setColumnsNamePlayer(DesignJComponent.SIZE_COLUMNS_TEXT);

        menuConcrete.setLayout(new GridBagLayout());
        int nBtnUsed = 0;

        final GridBagConstraints lim = FactoryGUIs.createGBConstraintsWithSpaceTitle(DesignJComponent.SIZE_SPACE_TITLE);
        menuConcrete.add(menuConcrete.getLabelTitle(), lim);

        FactoryGUIs.resetGridBagConstraints(lim);
        lim.gridy++;

        menuConcrete.getBtnActionLinks().forEach(FactoryGUIs::setTransparentJButton);

        menuConcrete.add(FactoryGUIs.createPanelFlowUnionComponents(List.of(menuConcrete.getTxfNamePlayer(),
                menuConcrete.getBtnActionLinks().get(nBtnUsed++))), lim);

        while (nBtnUsed < EngineMenu.N_BUTTONS) {
            lim.gridy++;
            menuConcrete.add(FactoryGUIs.createPanelFlowUnionComponents(List.of(menuConcrete.getBtnActionLinks().get(nBtnUsed++),
                    nBtnUsed + FactoryGUIs.STEP_INDEX < EngineMenu.N_BUTTONS 
                    ? menuConcrete.getBtnActionLinks().get(nBtnUsed++) : FactoryGUIs.getJComponentEmpty())), lim);
        }

        nBtnUsed = 0;
        while (nBtnUsed < EngineMenu.N_BUTTONS) {
            FactoryGUIs.setIconJButtonFromRate(menuConcrete.getButton(nBtnUsed),
                    IconsButton.values()[nBtnUsed++].getPath(), ScaleOf.ICON_FULL, menuConcrete.getWidth());
        }
        return menuConcrete;
    }

}
