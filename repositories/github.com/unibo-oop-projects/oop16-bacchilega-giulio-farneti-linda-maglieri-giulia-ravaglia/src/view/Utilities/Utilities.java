package view.Utilities;

import view.Utilities.ScreenDimension.ScreenDim;

/**
 * 
 * Author: Giulia Maglieri.
 *
 */
public final class Utilities {
    /**
     * width of the scene of the boardLayout class.
     */
    public static final double BOARDSCENE_WIDTH = 750;
    /**
     * height of the scene of the boardLayout class.
     */
    public static final double BOARDSCENE_HEIGHT = 650;
    /**
     * number of rows and columns for the chessboard.
     */
    public static final double NUMBER_OF_TILE = 8;
    /**
     *  width of the chessboard.
     */
    public static final double WIDTH_CHESSBOARD = 600;
    /**
     * height of the chessboard.
     */
    public static final double HEIGHT_CHESSBOARD = 600;
    /**
     *  width of a single tile of the chessboard.
     */
    public static final double WIDTH_TILE = (WIDTH_CHESSBOARD / NUMBER_OF_TILE);
    /**
     * height of a single tile of the chessboard.
     */
    public static final double HEIGHT_TILE = (HEIGHT_CHESSBOARD / NUMBER_OF_TILE);
    /**
     * width of the sideMenu.
     */
    public static final double VBOX_WIDTH = 50;
    /**
     *  height of the top box panel.
     */
    public static final double HBOX_HEIGHT = 70;
    /**
     * fixed width for all the popup windows.
     */
    public static final double POPUP_WIDTH = 500;
    /**
     * fixed height for all the popup windows.
     */
    public static final double POPUP_HEIGHT = 500;
    /**
     * fixed dimension for the pawn's images.
     */
    public static final int DIMENSION = 55;
    /**
     * width of the buttons of the sideMenu.
     */
    public static final double BTN_WIDTH = 50;
    /**
     * height of the buttons of the sideMenu.
     */
    public static final double BTN_HEIGHT = 50;
    /**
     * space between vbox's element of the Victory panel.
     */
    public static final double SPACE_VBOX = 20;
    /**
     * parameter for a text effect in the Victory Panel.
     */
    public static final double REFRACTION = 0.8;
    /**
     * show duration of the victory panel.
     */
    public static final double DURATION = 9000;
    /**
     * space between hbox's element of the TimeUp panel.
     */
    public static final double SPACE_HBOX = 5;
    /**
     * show duration of the timeUp panel.
     */
    public static final double DURATION1 = 5000;
    /**
     * number of buttons in the side menu.
     */
    public static final double BUTTONS = 4;
    /**
     * space between hbox's element of the ChessBox panel.
     */
    public static final double SPACE_HBOX1 = 5;
    /**
     * width of the ImageView in chessbox.
     */
    public static final double IMAGE_WIDTH = 150;
    /**
     * height of the ImageView in chessbox.
     */
    public static final double IMAGE_HEIGHT = 120;
    /**
     * show duration of the chessbox panel.
     */
    public static final double DURATION2 = 4000;
    /**
     * width of the main menu.
     */
    public static final double HEIGHT_MENU = ScreenDim.getHeight() / 1.6;
    /**
     * height of the main menu.
     */
    public static final double WIDTH_MENU = ScreenDim.getHeight();
    /**
     * utilities number for the main menu.
     */
    public static final double UTILITIES_NUMBER = 1.6;
    /**
     * size of the icons in the options menu.
     */
    public static final double MENU_ICON = 40;
    /**
     * numbers of chapters of the rulesBook.
     */
    public static final int CHAPTERS_TOT = 15;
    /**
     * index of the chapter.
     */
    public static final int IDX_CHAPTERS = 6;
    /**
     * index sub-chapters chap two.
     */
    public static final int IDX_CHAPTWO = 6;
    /**
     * index sub-chapters chap three.
     */
    public static final int IDX_CHAPTHREE = 2;
    private Utilities() { }
}
