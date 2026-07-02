package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * The Class GameP.
 */
public class GameP extends JPanel {
	
	
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The view. */
	private final View view;
	
	/** The title. */
	private final String title;
	
	/** The background. */
	private final Background background = new Background(MenuP.TITLE);
	
	/** The Constant BUTTON_DIMENSION. */
	public static final Dimension BUTTON_DIMENSION = new Dimension(ViewImpl.SCREEN_WIDTH / 6,ViewImpl.SCREEN_HEIGHT/9);
	
	
	
	/**
	 * Instantiates a new game P.
	 *
	 * @param view the view
	 * @param title the title
	 */
	public GameP(final View view, String title) {
		super();
		this.view = view;
		this.title = title;
	}



	/**
	 * Display.
	 */
	public void display() {
		this.view.switchWindow(this, this.title);
	}
	
	
	/**
	 * Prepare button.
	 *
	 * @param title the title
	 * @param panel the panel
	 * @return the j button
	 */
	protected JButton prepareButton(final String title,final JPanel panel) {
		final JButton button = new JButton(title);
		button.setMaximumSize(new Dimension(BUTTON_DIMENSION.width,BUTTON_DIMENSION.height));
		button.setFont(new Font("Bauhaus 93", Font.HANGING_BASELINE, BUTTON_DIMENSION.height / 3));
		button.setAlignmentX(CENTER_ALIGNMENT);
		button.setBackground(Color.YELLOW);
		panel.add(button);
		return button;
		
	}
	
	/**
	 * Gets the view.
	 *
	 * @return the view
	 */
	protected View getView() {
		return this.view;
	}
	
	/**
	 * Paint component.
	 *
	 * @param g the g
	 */
	@Override
	protected void paintComponent(final Graphics g) {
		g.drawImage(this.background.loadImage(),0 ,0,this.getWidth(),this.getHeight(),this);
	}
}
