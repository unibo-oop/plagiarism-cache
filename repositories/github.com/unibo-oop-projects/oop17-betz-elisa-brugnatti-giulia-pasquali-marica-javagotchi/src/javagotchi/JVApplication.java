package javagotchi;

import javax.swing.SwingUtilities;

import javagotchi.controller.menu.MenuController;
import javagotchi.controller.menu.MenuControllerImpl;
import javagotchi.view.menu.MenuView;
import javagotchi.view.menu.MenuViewImpl;

final class JVApplication {
    private JVApplication() { } 

    /**
     * Main static method to start the application.
     * @param args args
     */
    public static void main(final String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                final MenuController controller = MenuControllerImpl.getControllerImpl();
                final MenuView view = new MenuViewImpl(controller);
                view.getMenuManager().showStartMenu();
                System.out.println("Starting Tamagotchi application...");
            }
        });
    }
}
