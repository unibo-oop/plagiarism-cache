/**
 * 
 */
package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.IViewObserver;

/**
 * classe astratta che estende da AbstractFrame creata per il menu principale e
 * le due situazioni aziendali
 * 
 * @author Pentolo
 *
 */
public abstract class AbstractWideView extends AbstractFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8661356282182241245L;

	private IViewObserver observer;

	/**
	 * @param title
	 *            of the view
	 * @param dimension
	 *            of the view
	 */
	public AbstractWideView(final String title, final Dimension dimension) {
		super(title, dimension);
		final JButton chiudi = new JButton("Chiudi");
		final JPanel footer = new JPanel(new FlowLayout());
		footer.add(chiudi);
		getMyFrame().getContentPane().add(footer, BorderLayout.SOUTH);
		chiudi.addActionListener(e -> {
			observer.chiusura();
		});
	}

	@Override
	protected void chiusura() {
		observer.chiusura();
	}

	/**
	 * restituisce il controller
	 * 
	 * @return the observer
	 */
	protected IViewObserver getObserver() {
		return observer;
	}

	/**
	 * @param observer
	 *            the observer to set
	 */
	public void setObserver(final IViewObserver observer) {
		this.observer = observer;
	}
}
