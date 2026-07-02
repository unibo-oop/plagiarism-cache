package it.unibo.infomanager.infomng.view;

import java.awt.Toolkit;

import it.unibo.infomanager.infomng.model.modelProvidersI;
import it.unibo.infomanager.infomng.view.interfaces.ObserverInterface;
/**
 * Classe che definisce viewFornitori.
 * @author Alessandro
 *
 */
public class FornitoriGUI extends ClientiGUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7744994891479399079L;
	private static final String TITOLO = "Fornitori";
	

/**
 * Costruttore del FornitoriGUI frame.
 * @param o
 * 			Oggetto ObserverInterface
 */
	public FornitoriGUI(final ObserverInterface o) {
		super(o);
		this.setTitle(TITOLO);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(FornitoriGUI.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
	}

	@Override
	public void setTextFields(final Object c) {
		modelProvidersI fornitore = (modelProvidersI) c;
		this.txtCognome.setText(fornitore.getLastName());
		this.txtNome.setText(fornitore.getName());
		this.txtEmail.setText(fornitore.getMail());
		this.txtTelefono.setText(fornitore.getPhone());
	}
	

}
