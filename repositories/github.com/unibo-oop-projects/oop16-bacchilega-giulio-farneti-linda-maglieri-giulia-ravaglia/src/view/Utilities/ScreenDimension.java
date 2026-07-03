package view.Utilities;
import javafx.stage.Screen;

/**
 * 
 * Author : Giulia Maglieri.
 *
 */
public class ScreenDimension {
    /**
     * 
     * 
     *
     */
    public static final class ScreenDim {
        private static final double SCREEN_WIDTH = Screen.getPrimary().getBounds().getWidth();
            private static final double SCREEN_HEIGHT = Screen.getPrimary().getBounds().getHeight();
 /**
   * constructor.
   */
   private ScreenDim() { }
       /**
        * 
        * @return double
        */
       public static double getWidth() {
           return SCREEN_WIDTH;
       }
       /**
        * 
        * @return double
        */
       public static double getHeight() {
           return SCREEN_HEIGHT;
       }
    }

}