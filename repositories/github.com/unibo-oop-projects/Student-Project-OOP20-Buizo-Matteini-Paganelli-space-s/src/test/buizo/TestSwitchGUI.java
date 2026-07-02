package test.buizo;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import javax.swing.JFrame;

import org.junit.Test;

import spacesurvival.controller.gui.command.CmdGUI;
import spacesurvival.controller.gui.command.SwitchGUI;
import spacesurvival.model.gui.StaticFactoryEngineGUI;
import spacesurvival.model.gui.Visibility;
import spacesurvival.model.gui.menu.EngineMenu;
import spacesurvival.view.GUI;
import spacesurvival.view.menu.GUIMenu;
import spacesurvival.view.menu.factorymethod.GUIMenuStandard;

public class TestSwitchGUI {

    @Test
    public void testSwitchGUI() {
        final EngineMenu engine = StaticFactoryEngineGUI.createEngineMenu();
        final GUIMenu gui = new GUIMenuStandard().create();

        final SwitchGUI switcher = new SwitchGUI(engine, gui);

        switcher.turn(Visibility.VISIBLE);

        assertEquals(Visibility.VISIBLE, engine.getVisibility());
        assertEquals(Visibility.VISIBLE.isVisible(), ((JFrame) gui).isVisible());

        switcher.changeVisibility();
        assertEquals(Visibility.HIDDEN, engine.getVisibility());
        assertEquals(Visibility.HIDDEN.isVisible(), ((JFrame) gui).isVisible());

        new CmdGUI() {
            @Override
            public void execute(final GUI gui) {
                gui.setVisible(true);
            }
        }.execute(gui);
        assertNotEquals(((JFrame) gui).isVisible(), engine.isVisible());
    }
}
