package it.unibo.oop.bbgmm.Utilities;

import java.awt.*;

/**
 * @author Manuel
 */

public class Resolution {
    private static final int SMALL_HEIGHT = 768;
    private static final int SMALL_WIDTH = 1024;
    private static final Toolkit tk = Toolkit.getDefaultToolkit();
    private static final Dimension d = tk.getScreenSize();
    private static int width = 1024;
    private static int height = 768;
    private static boolean fullScreen;

    public Resolution() {
        fullScreen=false;
    }

    public static int getSmallHeight(){return SMALL_HEIGHT;}

    public static int getSmallWidth(){return SMALL_WIDTH;}

    public static int getHeight() {
        return height;
    }

    public static int getWidth() {
        return width;
    }

    public static void setSmallResolution(){
        width = 1024;
        height = 768;
        fullScreen = false;
    }

    public static void setFullResolution(){
        width = d.width;
        height = d.height;
        fullScreen = true;
    }

    public static boolean isFullScreen(){return fullScreen;}
}
