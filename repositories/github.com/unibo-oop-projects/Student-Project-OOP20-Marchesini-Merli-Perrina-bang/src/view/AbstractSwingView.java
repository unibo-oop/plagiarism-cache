package view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * An abstract class implementing a view for the "Swing" graphic library.
 * 
 * @author Davide Merli
 *
 */
public abstract class AbstractSwingView implements View {

	private static final double PROPORTION = 1.5;

	private JFrame frame;
	protected JPanel panel = new JPanel();

	public AbstractSwingView(final JFrame frame) {
		this.frame = frame;
		if (!frame.isVisible()) {
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLocationByPlatform(true);
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			frame.setSize((int) (dim.getWidth() / PROPORTION), (int) (dim.getHeight() / PROPORTION));
		}
		frame.getContentPane().removeAll();
		frame.getContentPane().add(panel);
		this.initView();
		this.show();
	}

	@Override
	public void show() {
		this.frame.setVisible(true);
	}

	/**
	 * Initialize view properties and components
	 */
	abstract public void initView();

}
