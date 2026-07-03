package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.Model;

/**
 * This Class implements {@link View}. This is the VIEW of MVC PATTERN, It
 * requires updates from model. This class creates and initializes all graphic
 * components of the Frame with specifics value.
 * 
 * @author Luca
 */
public class ViewImpl extends JFrame implements View {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// NTSC 1280x720 - SVGA 800x600 - XGA 1024x768
	public static final int WIDTH = 1024;
	public static final int HEIGHT = 760;
	public static final int ZERO = 0;
	public static final int SCALE = 2;
	public static final int VIEWPORT_X = ZERO;
	public static final int VIEWPORT_Y = ZERO;
	public static final int VIEWPORT_HEIGHT = HEIGHT * SCALE;
	public static final int VIEWPORT_WIDTH = WIDTH * SCALE;

	private final JPanel panel;
	private final BufferedImage viewport;
	private final Graphics2D graphics;
	private final Model model;

	/**
	 * Build the view and setup all Frame's components.
	 */
	public ViewImpl(final Model model) {
		super();
		this.model = model;
		this.panel = new JPanel();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(true); // hide window border.
		this.setSize(new Dimension(WIDTH, HEIGHT));
		this.getContentPane().add(this.panel);
		this.setLocation(0, 0);
		this.setResizable(false);
		this.setVisible(true);
		this.setFocusable(true);
		this.requestFocus();

		viewport = new BufferedImage(WIDTH, HEIGHT, 1);
		graphics = (Graphics2D) viewport.getGraphics();
	}

	@Override
	public void updateView() {
		this.model.getGraphics(graphics);
	}

	@Override
	public void addListener(final KeyListener listener) {
		this.addKeyListener(listener);
	}

	@Override
	public void printView() {
		Graphics viewportToScreen = this.panel.getGraphics();
		viewportToScreen.drawImage(viewport, VIEWPORT_X, ZERO, VIEWPORT_WIDTH, VIEWPORT_HEIGHT, null);
		viewportToScreen.dispose();
	}
}
