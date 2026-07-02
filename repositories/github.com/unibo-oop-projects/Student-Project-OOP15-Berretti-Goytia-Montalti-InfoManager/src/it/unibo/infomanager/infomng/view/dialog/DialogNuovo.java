package it.unibo.infomanager.infomng.view.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import it.unibo.infomanager.infomng.view.interfaces.ObserverInterface;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JRadioButton;
import java.awt.Toolkit;

/**
 * Classe che definisce DialogNuovo.
 * 
 * @author Alessandro
 *
 */
//CHECKSTYLE:OFF: checkstyle:magicnumber    
public class DialogNuovo extends JDialog implements DialogInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5569305998629847130L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNome = new JTextField(10);
	private JTextField txtCognome = new JTextField(20);
	private JTextField txtTelefono = new JTextField(10);
	private JTextField txtIndirizzo = new JTextField(30);
	private JLabel lblTelefono = new JLabel("Telefono");
	private JLabel lblNome = new JLabel("Nome(*)");
	private JLabel lblCognome = new JLabel("Cognome(*)");
	private JLabel lblIndirizzo = new JLabel("Indirizzo");
	private GridBagConstraints glblIndirizzo = new GridBagConstraints();
	private GridBagConstraints glblTelefono = new GridBagConstraints();
	private GridBagConstraints glblCognome = new GridBagConstraints();
	private GridBagConstraints gtxtNome = new GridBagConstraints();
	private GridBagConstraints glblNome = new GridBagConstraints();
	private GridBagConstraints gtxtIndirizzo = new GridBagConstraints();
	private GridBagConstraints gtxtCognome = new GridBagConstraints();
	private GridBagConstraints gtxtTelefono = new GridBagConstraints();
	private GridBagConstraints grdbtnFornitore = new GridBagConstraints();
	private GridBagConstraints grdbtnCliente = new GridBagConstraints();
	private GridBagConstraints glblNegozio = new GridBagConstraints();
	private GridBagConstraints gtxtNegozio = new GridBagConstraints();
	private GridBagConstraints glblEmail = new GridBagConstraints();
	private GridBagConstraints gtxtMail = new GridBagConstraints();
	private JButton okButton = new JButton("OK");
	private JButton cancelButton = new JButton("Cancel");
	private GridBagLayout gcontentPanel = new GridBagLayout();
	private JRadioButton rdbtnCliente = new JRadioButton("Cliente");
	private JRadioButton rdbtnFornitore = new JRadioButton("Fornitore");
	private JPanel buttonPane = new JPanel();
	private Map<String, String> map = new HashMap<>();
	private final JTextField txtMail = new JTextField(30);
	private final JLabel lblEmail = new JLabel("E-Mail");
	private final JTextField txtNegozio = new JTextField(30);
	private final JLabel lblNegozio = new JLabel("Negozio");

	@Override
	public Map<String, String> getDataString(final ObserverInterface o) {
		this.map.put("Nome", txtNome.getText());
		this.map.put("Cognome", txtCognome.getText());
		this.map.put("Telefono", txtTelefono.getText());
		this.map.put("Indirizzo", txtIndirizzo.getText());
		this.map.put("Email", txtMail.getText());
		this.map.put("Negozio", txtNegozio.getText());
		if (rdbtnCliente.isSelected()) {
			this.map.put("TipoDiRapporto", rdbtnCliente.getText());
		} else {
			this.map.put("TipoDiRapporto", rdbtnFornitore.getText());
		}
		return this.map;
	}

	private boolean checkText() {
		if (txtNome.getText().equals("")) {
			return false;
		} else {
			if (txtCognome.getText().equals("")) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Costruttore DialogNuovo.
	 * 
	 * @param o
	 *            Oggetto ObserverInterface
	 */
	public DialogNuovo(final ObserverInterface o) {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(DialogNuovo.class.getResource("/javax/swing/plaf/metal/icons/ocean/computer.gif")));
		txtNegozio.setColumns(10);
		txtMail.setColumns(10);
		this.setTitle("Nuovo Fornitore o Cliente");
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.setBounds(100, 100, 603, 265);
		this.getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.getContentPane().add(contentPanel, BorderLayout.CENTER);
		this.gcontentPanel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		this.gcontentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		this.gcontentPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE };
		this.gcontentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		this.contentPanel.setLayout(gcontentPanel);
		{
			this.glblNome.gridwidth = 4;
			this.glblNome.insets = new Insets(0, 0, 5, 5);
			this.glblNome.gridx = 0;
			this.glblNome.gridy = 0;
			this.contentPanel.add(lblNome, glblNome);
		}
		{
			this.gtxtNome.gridwidth = 2;
			this.gtxtNome.insets = new Insets(0, 0, 5, 0);
			this.gtxtNome.fill = GridBagConstraints.HORIZONTAL;
			this.gtxtNome.gridx = 4;
			this.gtxtNome.gridy = 0;
			this.txtNome.setEditable(true);
			this.contentPanel.add(txtNome, gtxtNome);
			this.txtNome.setColumns(10);
		}
		{
			this.glblCognome.gridwidth = 4;
			this.glblCognome.insets = new Insets(0, 0, 5, 5);
			this.glblCognome.gridx = 0;
			this.glblCognome.gridy = 2;
			this.contentPanel.add(lblCognome, glblCognome);
		}
		{
			this.gtxtCognome.insets = new Insets(0, 0, 5, 0);
			this.gtxtCognome.gridwidth = 2;
			this.gtxtCognome.fill = GridBagConstraints.HORIZONTAL;
			this.gtxtCognome.gridx = 4;
			this.gtxtCognome.gridy = 2;
			this.txtCognome.setEditable(true);
			this.contentPanel.add(txtCognome, gtxtCognome);
			this.txtCognome.setColumns(10);
		}
		{
			this.glblTelefono.gridwidth = 4;
			this.glblTelefono.insets = new Insets(0, 0, 5, 5);
			this.glblTelefono.gridx = 0;
			this.glblTelefono.gridy = 4;
			this.contentPanel.add(lblTelefono, glblTelefono);
		}
		{
			this.gtxtTelefono.gridwidth = 2;
			this.gtxtTelefono.insets = new Insets(0, 0, 5, 0);
			this.gtxtTelefono.fill = GridBagConstraints.HORIZONTAL;
			this.gtxtTelefono.gridx = 4;
			this.gtxtTelefono.gridy = 4;
			this.txtTelefono.setEditable(true);
			this.contentPanel.add(txtTelefono, gtxtTelefono);
			this.txtTelefono.setColumns(10);
		}
		{
			this.glblIndirizzo.gridwidth = 4;
			this.glblIndirizzo.insets = new Insets(0, 0, 5, 5);
			this.glblIndirizzo.gridx = 0;
			this.glblIndirizzo.gridy = 6;
			this.contentPanel.add(lblIndirizzo, glblIndirizzo);
		}
		{
			this.gtxtIndirizzo.insets = new Insets(0, 0, 5, 0);
			this.gtxtIndirizzo.gridwidth = 2;
			this.gtxtIndirizzo.fill = GridBagConstraints.HORIZONTAL;
			this.gtxtIndirizzo.gridx = 4;
			this.gtxtIndirizzo.gridy = 6;
			this.txtIndirizzo.setEditable(true);
			this.contentPanel.add(txtIndirizzo, gtxtIndirizzo);
			this.txtIndirizzo.setColumns(10);
		}
		{

			this.glblEmail.gridwidth = 3;
			this.glblEmail.insets = new Insets(0, 0, 5, 5);
			this.glblEmail.gridx = 0;
			this.glblEmail.gridy = 8;
			this.contentPanel.add(lblEmail, glblEmail);
		}
		{
			this.gtxtMail.gridwidth = 2;
			this.gtxtMail.insets = new Insets(0, 0, 5, 0);
			this.gtxtMail.fill = GridBagConstraints.HORIZONTAL;
			this.gtxtMail.gridx = 4;
			this.gtxtMail.gridy = 8;
			this.txtMail.setEditable(true);
			this.contentPanel.add(txtMail, gtxtMail);
		}

		this.rdbtnFornitore.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (rdbtnFornitore.isSelected()) {
					rdbtnCliente.setEnabled(false);
				} else {
					rdbtnCliente.setEnabled(true);
				}
			}
		});
		{
			this.glblNegozio.gridwidth = 4;
			this.glblNegozio.insets = new Insets(0, 0, 5, 5);
			this.glblNegozio.gridx = 0;
			this.glblNegozio.gridy = 10;
			this.contentPanel.add(lblNegozio, glblNegozio);
		}
		{

			this.gtxtNegozio.gridwidth = 2;
			this.gtxtNegozio.insets = new Insets(0, 0, 5, 5);
			this.gtxtNegozio.fill = GridBagConstraints.HORIZONTAL;
			this.gtxtNegozio.gridx = 4;
			this.gtxtNegozio.gridy = 10;
			this.contentPanel.add(txtNegozio, gtxtNegozio);
		}
		{
			this.grdbtnFornitore.insets = new Insets(0, 0, 0, 5);
			this.grdbtnFornitore.gridx = 4;
			this.grdbtnFornitore.gridy = 11;
			this.contentPanel.add(rdbtnFornitore, grdbtnFornitore);
		}

		this.rdbtnCliente.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (rdbtnCliente.isSelected()) {
					rdbtnFornitore.setEnabled(false);
				} else {
					rdbtnFornitore.setEnabled(true);
				}
			}
		});
		{
			this.grdbtnCliente.gridx = 5;
			this.grdbtnCliente.gridy = 11;
			this.contentPanel.add(rdbtnCliente, grdbtnCliente);
		}
		{
			this.buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			this.getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				this.okButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if (rdbtnCliente.isSelected()) {
							if (checkText()) {
								
								o.salvaCliente(getDataString(o));
								o.abilitaFrame(true);
								dispose();
							} else {
								o.mostraDialogCampoObbligatorio();
							}
						} else {
							if (checkText()) {
								o.salvaFornitore(getDataString(o));
								o.abilitaFrame(true);
								dispose();
							} else {
								o.mostraDialogCampoObbligatorio();
							}
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
						o.abilitaFrame(true);
						dispose();
					}
				});
				this.buttonPane.add(cancelButton);
			}
		}
	}

}
