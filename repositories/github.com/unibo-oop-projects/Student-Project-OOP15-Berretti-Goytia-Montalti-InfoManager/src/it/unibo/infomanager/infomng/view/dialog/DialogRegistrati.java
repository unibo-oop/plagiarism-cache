package it.unibo.infomanager.infomng.view.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import it.unibo.infomanager.infomng.view.interfaces.ObserverInterface;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Toolkit;

/**
 * Classe che definisce DialogRegistrati.
 * 
 * @author Alessandro
 *
 */
public class DialogRegistrati extends JDialog implements DialogInterface {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9034440890511608840L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldUsername = new JTextField(10);
	private JTextField textFieldEmail = new JTextField(10);
	private JTextField textFieldIndirizzo = new JTextField(10);
	private JTextField textFieldNegozio = new JTextField(10);
	private JPasswordField passwordField = new JPasswordField();
	private JLabel lblUserName = new JLabel("Username(*)");
	private JLabel lblPassword = new JLabel("Password(*)");
	private JLabel lblEmail = new JLabel("Email(*)");
	private JLabel lblIndirizzo = new JLabel("Indirizzo");
	private JLabel lblNegozio = new JLabel("Negozio");
	private JPasswordField passwordFieldConfirm = new JPasswordField(10);
	private GroupLayout gcontentPanel = new GroupLayout(contentPanel);
	private JPanel buttonPane = new JPanel();
	private JButton okButton = new JButton("OK");
	private JButton cancelButton = new JButton("Cancel");
	private Map<String, String> map = new HashMap<>();
	private JLabel lblConferma = new JLabel("Conferma Password(*)");

	/**
	 * Costruttore DialogRegistrati.
	 * 
	 * @param o
	 *            Oggetto ObserverInterface
	 */
	// CHECKSTYLE:OFF: checkstyle:magicnumber
	public DialogRegistrati(final ObserverInterface o) {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(DialogRegistrati.class.getResource("/javax/swing/plaf/metal/icons/ocean/computer.gif")));
		this.setBounds(100, 100, 310, 375);
		this.setTitle("Registrazione");
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.getContentPane().add(contentPanel, BorderLayout.CENTER);

		this.gcontentPanel.setHorizontalGroup(this.gcontentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gcontentPanel.createSequentialGroup().addContainerGap()
						.addGroup(gcontentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblIndirizzo, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
								.addComponent(lblEmail, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
								.addGroup(gcontentPanel.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(lblPassword, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblConferma, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(lblUserName,
												Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
						.addComponent(lblNegozio, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								gcontentPanel.createParallelGroup(Alignment.LEADING).addComponent(passwordField)
										.addComponent(passwordFieldConfirm)
										.addGroup(gcontentPanel.createParallelGroup(Alignment.LEADING, false)
												.addComponent(textFieldUsername, GroupLayout.DEFAULT_SIZE, 122,
														Short.MAX_VALUE)
												.addComponent(textFieldEmail).addComponent(textFieldIndirizzo)
												.addComponent(textFieldNegozio)))
						.addGap(263)));
		this.gcontentPanel
				.setVerticalGroup(this.gcontentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gcontentPanel.createSequentialGroup().addContainerGap()
								.addGroup(gcontentPanel.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblUserName, GroupLayout.PREFERRED_SIZE, 36,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(textFieldUsername, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gcontentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
								.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gcontentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblConferma, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
								.addComponent(passwordFieldConfirm, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gcontentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gcontentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblIndirizzo, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldIndirizzo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gcontentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNegozio, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldNegozio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
								.addContainerGap(48, Short.MAX_VALUE)));
		this.contentPanel.setLayout(gcontentPanel);

		{
			this.buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			this.getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				this.okButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						Map<String, String> valori = getDataString(o);
						if (valori != null) {
							o.salvaUtente(valori);
							o.mostraMenu();
							dispose();
						}
					}
				});
				this.buttonPane.add(okButton);
				this.getRootPane().setDefaultButton(okButton);
			}
			{
				this.cancelButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						o.start();
						dispose();
					}
				});
				this.buttonPane.add(cancelButton);
			}
		}
	}

	/**
	 * Metodo che mostra DialogCampoObbligatorio quando si genera un errore di inserimento nei campi obbligatori
	 * 
	 * @param o
	 *            Oggetto ObserverInterface
	 */
	private void creaDialogCampoObbligatorio(final ObserverInterface o) {
		DialogCampoObbligatorio errore = new DialogCampoObbligatorio(o);
		errore.setVisible(true);
	}

	/**
	 * Metodo che verifica il corretto inserimento dei campi obbligatori
	 * 
	 * @return Variabile Boolean false se il campo/i campi non sono inseriti, true se sono inseriti
	 */
	private boolean verificaCampiObbligatori() {
		if (textFieldUsername.getText().equals("")) {
			return false;
		} else {
			if (textFieldEmail.getText().equals("")) {
				return false;
			} else {
				if (passwordField.equals("")) {
					return false;
				} else {
					if (passwordFieldConfirm.equals("")) {
						return false;
					} else {
						return true;
					}
				}
			}
		}
	}

	@Override
	public Map<String, String> getDataString(final ObserverInterface o) {
		if (this.verificaCampiObbligatori() == true) {
			this.map.put("Username", textFieldUsername.getText());
			this.map.put("Email", textFieldEmail.getText());
			this.map.put("Indirizzo", textFieldIndirizzo.getText());
			this.map.put("Negozio", textFieldNegozio.getText());

			this.map.put("Password", new String(passwordField.getPassword()));
			return map;
		} else {
			this.creaDialogCampoObbligatorio(o);
			return null;
		}
	}
}
