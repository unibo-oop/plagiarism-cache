package spacesurvival.view.dead.factorymethod;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;

import spacesurvival.utilities.path.Background;
import spacesurvival.view.dead.FactoryGUIDead;
import spacesurvival.view.dead.GUIDead;
import spacesurvival.view.dead.concrete.ConcreteDeadGUI;
import spacesurvival.view.utilities.GraphicsLayoutUtils;
import spacesurvival.view.utilities.FactoryGUIs;

/**
 * Implements the creation of the compact dead GUI.
 */
public class GUIDeadStandard implements FactoryGUIDead {

    /**
     * {@inheritDoc}
     */
    @Override
    public GUIDead create() {
        final ConcreteDeadGUI concrete = new ConcreteDeadGUI();

        concrete.setFontTitleGUI(GraphicsLayoutUtils.getFontForDead(GraphicsLayoutUtils.SIZE_FONT_H0));
        concrete.setFontGUI(GraphicsLayoutUtils.getFontForDead(GraphicsLayoutUtils.SIZE_FONT_H2));
        concrete.setForegroundTitle(Color.RED);
        concrete.setForegroundGUI(Color.RED);
        concrete.setImageBackground(Background.DEAD2);

        concrete.setLayout(new BorderLayout());

        concrete.getBtnActionLinks().forEach(FactoryGUIs::setTransparentJButton);

        concrete.add(FactoryGUIs.encapsulatesInPanelFlow(concrete.getLabelTitle()), BorderLayout.CENTER);

        final int lastBtn = 1;
        concrete.add(FactoryGUIs.createPanelGridBagUnionComponentsHorizontal(List.of(concrete.getBtnActionLinks().get(lastBtn)),
                FactoryGUIs.INSET_H1),
                BorderLayout.SOUTH);

        return concrete;
    }

}
