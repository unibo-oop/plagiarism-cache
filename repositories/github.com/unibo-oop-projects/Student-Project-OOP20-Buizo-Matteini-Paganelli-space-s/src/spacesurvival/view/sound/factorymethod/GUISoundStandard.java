package spacesurvival.view.sound.factorymethod;

import java.awt.BorderLayout;

import spacesurvival.utilities.dimension.ScaleOf;
import spacesurvival.utilities.path.Background;
import spacesurvival.utilities.path.Icon;
import spacesurvival.utilities.dimension.Screen;
import spacesurvival.view.utilities.GraphicsLayoutUtils;
import spacesurvival.view.sound.FactoryGUISound;
import spacesurvival.view.sound.GUISound;
import spacesurvival.view.sound.concrete.ConcreteSoundGUI;
import spacesurvival.view.utilities.FactoryGUIs;

public class GUISoundStandard implements FactoryGUISound {

    /**
     * {@inheritDoc}
     */
    @Override
    public GUISound create() {
        final ConcreteSoundGUI soundGUI = new ConcreteSoundGUI();
        soundGUI.setFontTitleGUI(GraphicsLayoutUtils.getFontForTitle(GraphicsLayoutUtils.SIZE_FONT_H2));
        soundGUI.setFontGUI(GraphicsLayoutUtils.FONT_STANDARD_H5);
        soundGUI.setFontSpacingSlider(GraphicsLayoutUtils.FONT_STANDARD_H6);
        soundGUI.setForegroundGUI(GraphicsLayoutUtils.COLOR_4);
        soundGUI.setBounds(Screen.RECTANGLE_MEDIUM);
        soundGUI.setBorder(GraphicsLayoutUtils.COLOR_4, FactoryGUIs.THICKNESS_H0);
        soundGUI.setImageBackground(Background.MAIN);

        soundGUI.setLayout(new BorderLayout());
        FactoryGUIs.setTransparentJButton(soundGUI.getBtnBack());
        soundGUI.add(FactoryGUIs.encapsulatesInPanelFlow(soundGUI.getLabelTitle()), BorderLayout.NORTH);
        soundGUI.add(soundGUI.getMixerSound(), BorderLayout.CENTER);
        soundGUI.add(FactoryGUIs.encapsulatesInPanelFlow(soundGUI.getBtnBack()), BorderLayout.SOUTH);

        FactoryGUIs.setIconJButtonFromRate(soundGUI.getBtnBack(), Icon.BACK,
                ScaleOf.ICON_MEDIUM, soundGUI.getWidth());

        return soundGUI;
    }
}
