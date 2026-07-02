/**
 * 
 */
package view.anaAziende;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingUtilities;

import controller.anaAziende.IAnaAziendeController;
import dataModel.Company;
import view.AnagraficaView;

/**
 * view per la anagrafica aziende
 * 
 * @author Pentolo
 *
 */
public class AnaAziendeView extends AnagraficaView<Company> {

	private static final long serialVersionUID = 5859979634610547926L;
	private final JPasswordField passwordField;

	/**
	 * @param lista
	 *            delle aziende
	 */
	public AnaAziendeView(final LinkedList<Company> lista) {
		this(lista, "Benvenuto in NGA");
	}

	/**
	 * @param list
	 *            lista delle aziende
	 * @param title
	 *            titolo della view
	 */
	public AnaAziendeView(final LinkedList<Company> list, final String title) {
		super(list, Company.getIntestazione(), title);
		passwordField = new JPasswordField(15);
		JButton accediButton = new JButton("Accedi");
		JPanel topPanel = new JPanel(new FlowLayout());
		topPanel.add(new JLabel("Password: "));
		topPanel.add(passwordField);
		topPanel.add(accediButton);
		getMyFrame().getContentPane().add(topPanel, BorderLayout.NORTH);
		SwingUtilities.getRootPane(getMyFrame()).setDefaultButton(accediButton);
		accediButton.addActionListener(e -> {
			Company item = null;
			try {
				item = (Company) getSelectedItem();
			} catch (Exception ex) {
				errorDialog("Errore", ex.getMessage());
			}
			if (item != null) {
				((IAnaAziendeController) getObserver()).accedi();
			} else {
				errorDialog("Attenzione, seleziona una riga per continuare!", "nessuna riga selezionata");
			}
		});
	}

	/**
	 * @return the password
	 */
	public char[] getInputPassword() {
		return passwordField.getPassword();
	}
}
