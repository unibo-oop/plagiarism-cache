package test.buizo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import spacesurvival.controller.gui.ControllerMenu;
import spacesurvival.model.gui.StaticFactoryEngineGUI;
import spacesurvival.model.gui.Visibility;
import spacesurvival.model.gui.menu.EngineMenu;
import spacesurvival.view.menu.GUIMenu;
import spacesurvival.view.menu.factorymethod.GUIMenuStandard;

public class TestMenu {

    @Test
    public void testLinkMenu() {
        final EngineMenu engine = StaticFactoryEngineGUI.createEngineMenu();
        final GUIMenu gui = new GUIMenuStandard().create();
        final ControllerMenu controller = new ControllerMenu(engine, gui);

        controller.assignLinks();
        assertEquals(controller.getMainLink(), engine.getMainLink());

        controller.assignTexts();
        for (int i = 0; i < EngineMenu.N_BUTTONS; i++) {
            assertEquals(gui.getBtnActionLinks().get(i).getNextLink(), engine.getLinks().get(i));
            assertEquals(controller.getMainLink(), gui.getBtnActionLinks().get(i).getCurrentLink());
        }
    }

    @Test
    public void testTextMenu() {
        final EngineMenu engine = StaticFactoryEngineGUI.createEngineMenu();
        final GUIMenu gui = new GUIMenuStandard().create();
        final ControllerMenu controller = new ControllerMenu(engine, gui);

        controller.assignTexts();
        for (int i = 0; i < EngineMenu.N_BUTTONS; i++) {
            assertEquals(gui.getBtnActionLinks().get(i).getText(), engine.getListTextLinks().get(i));
        }
    }

    @Test
    public void testVisibilityMenu() {
        final EngineMenu engine = StaticFactoryEngineGUI.createEngineMenu();
        final GUIMenu gui = new GUIMenuStandard().create();
        final ControllerMenu controller = new ControllerMenu(engine, gui);

        controller.turn(Visibility.VISIBLE);

        assertTrue(engine.isVisible());
    }
}
