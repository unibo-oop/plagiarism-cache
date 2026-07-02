package spacesurvival.view.help.factorymethod;

import java.awt.BorderLayout;

import spacesurvival.model.gui.help.EngineHelp;
import spacesurvival.utilities.dimension.ScaleOf;
import spacesurvival.utilities.path.Background;
import spacesurvival.utilities.path.Icon;
import spacesurvival.utilities.DesignJComponent;
import spacesurvival.view.help.concrete.ConcreteHelpGUI;
import spacesurvival.view.help.FactoryGUIHelp;
import spacesurvival.view.help.GUIHelp;
import spacesurvival.view.utilities.GraphicsLayoutUtils;
import spacesurvival.view.utilities.FactoryGUIs;

/**
 * Implements the creation of the standard help GUI.
 */
public class GUIHelpStandard implements FactoryGUIHelp {

    /**
     * {@inheritDoc}
     */
    @Override
    public GUIHelp create() {
        final ConcreteHelpGUI helpConcrete = new ConcreteHelpGUI();
        helpConcrete.setFontTitleGUI(GraphicsLayoutUtils.getFontForTitle(GraphicsLayoutUtils.SIZE_FONT_H2));
        helpConcrete.setFontGUI(GraphicsLayoutUtils.FONT_STANDARD_H5);
        helpConcrete.setForegroundGUI(GraphicsLayoutUtils.COLOR_4);
        helpConcrete.setBorder(GraphicsLayoutUtils.COLOR_4, 3);
        helpConcrete.setImageBackground(Background.MAIN);

        helpConcrete.setLayout(new BorderLayout());

        helpConcrete.add(FactoryGUIs.encapsulatesInPanelFlow(helpConcrete.getLabelTitle()), BorderLayout.NORTH);
        helpConcrete.add(FactoryGUIs.encapsulatesInPanelFlow(helpConcrete.getBtnBack()), BorderLayout.SOUTH);

        helpConcrete.add(FactoryGUIs.createPanelGridBagUnionComponentsVertical(
                helpConcrete.getUnitHelps(), DesignJComponent.MIN_INSET),
                BorderLayout.CENTER);

        FactoryGUIs.setTransparentJButton(helpConcrete.getBtnBack());
        FactoryGUIs.setIconJButtonFromRate(helpConcrete.getBtnBack(), Icon.BACK,
                ScaleOf.ICON_MEDIUM, EngineHelp.RECTANGLE.width);
        return helpConcrete;
    }

}
