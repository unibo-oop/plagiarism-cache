package controller;

import javax.swing.SwingUtilities;

import utilities.FontLoader;
import utilities.ImageLoader;
import view.View;
import view.ViewImpl;

/**
 * Where everything starts.
 */
public final class Launcher {
    /**
     * 
     */
    private Launcher() { }

    /**
     * @param args the list of arguments of the main method
     */
    public static void main(final String[] args) {
        ImageLoader.getLoader().findImages();
        FontLoader.loadFont();
        final Controller controller = new ControllerImpl();
        final View view = new ViewImpl();
        controller.setView(view);
        view.attach(controller);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                view.start();
            }
        });
    }
}
