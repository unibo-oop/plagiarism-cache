package application;

import java.util.stream.IntStream;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import controller.Controller;
import controller.ControllerImpl;
import util.Enums;
import view.MainGUI;

/**
 * 
 * Application Class.
 *
 */
public final class Application {
    private static final int WARDROOMS = 20;
    private static final int OPROOMS = 6;

    private Application() { }

    private static void initialize(final Controller c) {

        for (final String elem : Enums.WardTypes.getWardNames()) {
            c.createWard(elem, WARDROOMS);
        }
        IntStream
            .range(1, OPROOMS)
            .forEach(i -> c.createOperatingRoom("Room " + i));
    }
    /**
     * 
     * @param args args
     */
    public static void main(final String[] args) {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        final Controller c = ControllerImpl.getController();
        initialize(c);
        new MainGUI(c);
    }
}
