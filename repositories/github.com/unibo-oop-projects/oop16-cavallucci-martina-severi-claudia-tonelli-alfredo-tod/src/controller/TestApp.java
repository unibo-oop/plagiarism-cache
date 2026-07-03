package controller;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * 
 * Test application for start the application.
 *
 */
public final class TestApp {
    /**
     * 
     * @param args
     * args
     * @throws Exception
     * exception
     */
    private TestApp() {
    }
    /**
     * 
     * @param args
     * args
     * @throws Exception
     * exception
     */
    public static void main(final String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        MainControllerImpl.getSingleton().startApp();
    }

}
