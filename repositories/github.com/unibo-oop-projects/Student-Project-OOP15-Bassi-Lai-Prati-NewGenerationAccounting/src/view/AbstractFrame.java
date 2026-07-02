package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * classe astratta che contiene tutti i comportamenti di base dei frame del
 * progetto
 * 
 * @author Pentolo
 *
 */
public abstract class AbstractFrame extends JFrame {

	private static final long serialVersionUID = 638596561545905264L;

	private final JFrame myFrame;

	/**
	 * frame generico del progetto NGA
	 * 
	 * @param title
	 *            titolo della finestra
	 * @param dimension
	 *            dimensione finestra
	 */
	public AbstractFrame(final String title, final Dimension dimension) {
		myFrame = new JFrame("NGA - " + title);
		myFrame.setSize(dimension);
		myFrame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		myFrame.getContentPane().add(new JPanel(new BorderLayout()));
		myFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				chiusura();
			}
		});
		int x = (int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth() - dimension.getWidth()) / 2);
		int y = (int) ((Toolkit.getDefaultToolkit().getScreenSize().getHeight() - dimension.getHeight()) / 2);
		myFrame.setLocation(x, y);
	}

	protected abstract void chiusura();

	public void close() {
		myFrame.setVisible(false);
		myFrame.dispose();
	}

	public boolean confirmDialog(final String question, final String title) {
		return JOptionPane.showConfirmDialog(myFrame, question, title,
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
	}

	public void disableView() {
		this.myFrame.setEnabled(false);
	}

	public void enableView() {
		this.myFrame.setEnabled(true);
	}

	public void errorDialog(final String title, final String message) {
		JOptionPane.showMessageDialog(myFrame, message, title, JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * @return the myFrame
	 */
	protected final JFrame getMyFrame() {
		return myFrame;
	}

	public void start() {
		this.myFrame.setVisible(true);
	}
}
