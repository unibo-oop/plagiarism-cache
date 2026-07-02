package controller;

import java.io.IOException;
import java.lang.IllegalStateException;
import java.io.InputStream;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;


/**
 * 
 * 	Utility class for Screen management
 *
 */
public class ScreenManager {

	public final static int PREF_HEIGHT = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height *9/10 ;
	public final static int PREF_WIDTH =  java.awt.Toolkit.getDefaultToolkit().getScreenSize().width *9/10;
	private final static int SCALE_X = 320;
	private final static int SCALE_Y = 180;
	
	public static int heightScreen;
	public static int widthScreen;
	private static StackPane root = null;
	private static Canvas canvas = null;
	
	/**
	 * Initialize ScreenManager
	 */
	public static void setupScreenManager() {
		if(root == null || canvas == null)
			throw new IllegalStateException("root and canvas have to be initialized");
		root.setPrefSize(PREF_WIDTH, PREF_HEIGHT);
		canvas.widthProperty().bind(root.widthProperty());
		canvas.heightProperty().bind(root.heightProperty());	
		heightScreen = PREF_HEIGHT;
		widthScreen = PREF_WIDTH;
	}
	
	/**
	 * 
	 * @param root
	 */
	public static void setRoot(StackPane root) {
		ScreenManager.root = root;
	}
	
	/**
	 * 
	 * @param canvas
	 */
	public static void setCanvas(Canvas canvas) {
		ScreenManager.canvas = canvas;
	}
	
	/**
	 * Return the image from specified location
	 * @param path
	 * 		String path location
	 * @return
	 * @throws IOException
	 */
	public static Image getImage(String path) throws IOException {
		InputStream is =ClassLoader.getSystemResourceAsStream(path);
		Image img = new Image(is);
		return img;
	}
	
	/**
	 * 
	 * @return
	 * 		Returns the higher x scale
	 */
	public static int getMaxScaleX() {
		return SCALE_X;
	}
	
	/**
	 * 
	 * @return
	 * 		Returns the higher y scale
	 */
	public static int getMaxScaleY() {
		return SCALE_Y;
	}

	/**
	 * 
	 * @param deltaY
	 * 		Delta y which has to be scale in screen
	 * @return
	 * 		Returns the scaled delta y 
	 */
	public static double getScaleY(double deltaY) {
		return ((heightScreen *deltaY)/SCALE_Y);
	}
	
	/**
	 * 
	 * @param deltaX
	 * 		Delta x which has to be scale in screen 
	 * @return
	 */
	public static double getScaleX(double deltaX) {
		return ((widthScreen *deltaX)/ SCALE_X);
	}
	
	

	
}
