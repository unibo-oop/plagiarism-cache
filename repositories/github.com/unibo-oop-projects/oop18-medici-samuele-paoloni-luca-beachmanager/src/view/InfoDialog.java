package view;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;

/**
 * Classe generica per una Modale di informazione
 * @author Samuele Medici, samuele.medici2@studio.unibo.it (Mat. 0000718877 )
 *
 */
public abstract class InfoDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5158382337213871668L;

	// Utilizzate per ottenere le informazioni su grandezza frame
	private Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	
	/**
	 * 
	 * @param parent Frame dell'applicazione
	 */
	public InfoDialog(Frame parent) {
		super(parent, true);

		int width = 400;
		int height = 400;

		this.setSize(width, height);
		this.setLocation(dimension.width / 2 - width / 2, dimension.height / 2 - height / 2);

	}

	/**
	 * Setta la visiblità della modale
	 * @param isVisibile flag per la visibilità
	 */
	public void setVisibility(boolean isVisibile) {
		this.setVisible(isVisibile);
	}

	/**
	 * Setto il tasto ESC per la chiusura della modale
	 */
	protected JRootPane createRootPane() {
		JRootPane rootPane = new JRootPane();
		KeyStroke stroke = KeyStroke.getKeyStroke("ESCAPE");

		InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		inputMap.put(stroke, "ESCAPE");

		rootPane.getActionMap().put("ESCAPE", new AbstractAction() {
			public void actionPerformed(ActionEvent actionEvent) {
				setVisibility(false);
			}
		});

		return rootPane;
	}

}
