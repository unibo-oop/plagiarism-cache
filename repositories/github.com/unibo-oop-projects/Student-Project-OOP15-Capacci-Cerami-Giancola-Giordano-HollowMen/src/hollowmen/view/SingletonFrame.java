package hollowmen.view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
/**
 * The {@code SingletonFrame} class creates just one instance 
 * of the frame.
 * 
 * @author Juls
 */
public class SingletonFrame extends JFrame {
	
	private static final long serialVersionUID = 1310007697698107818L;
    private final int GRID_DIMENSION_ROW=1;
    private final int GRID_DIMENSION_COLUMN=1;
	private static final int GAP=200;
	private static int width;
	private static int height;
	
	private SingletonFrame() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(GRID_DIMENSION_ROW,GRID_DIMENSION_COLUMN));
		this.setSize(getWidth(), getHeight());
		this.setBackground(Color.BLACK);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setTitle("HOLLOW MEN");
		this.setVisible(true);
	}
	
	/**
	 * The {@code getInstance()} method gets the instance of the class {@link SingletonFrame}.
	 * @return the instance of the inner class {@link Singleton}.
	 */
	public static SingletonFrame getInstance() {
		return Singleton.INSTANCE;
	}

	/**
	 * The {@code Singleton} inner class creates the instance.
	 * @author Juls
	 *
	 */
	private static class Singleton {
		public static final SingletonFrame INSTANCE = new SingletonFrame();
	}
	
	/**
	 * This method sets the frame's width.
	 * @return {@code width}
	 */
	public static void setWidth(int width) {
		SingletonFrame.width = width;
	}
	
	/**
	 * This method sets the frame's height.
	 * @return {@code height}
	 */
	public static void setHeight(int height) {
		SingletonFrame.height = height;
	}
	
	/**
	 * This method gets the frame's width.
	 */
	public int getWidth() {
		return SingletonFrame.width;
	}
	
	/**
	 * This method gets the frame's height.
	 */
	public int getHeight() {
		return SingletonFrame.height+GAP;
	}
}
