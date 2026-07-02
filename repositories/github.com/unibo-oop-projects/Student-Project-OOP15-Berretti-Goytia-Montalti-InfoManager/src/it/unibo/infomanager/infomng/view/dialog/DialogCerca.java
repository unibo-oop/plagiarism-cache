package it.unibo.infomanager.infomng.view.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import it.unibo.infomanager.infomng.controller.Navigator;
import it.unibo.infomanager.infomng.view.ClientiGUI;
import it.unibo.infomanager.infomng.view.FattureGUI;
import it.unibo.infomanager.infomng.view.FornitoriGUI;
import it.unibo.infomanager.infomng.view.RiunioniGUI;
import it.unibo.infomanager.infomng.view.ScontriniGUI;
import it.unibo.infomanager.infomng.view.interfaces.ObserverInterface;

import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;

/**
 * Classe che definisce DialogCerca.
 * 
 * @author Alessandro
 *
 */
public class DialogCerca extends JDialog implements DialogInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7712444928294496614L;
	private final JPanel contentPanel = new JPanel();
	private JPanel buttonPane = new JPanel();
	private JButton okButton = new JButton("OK");
	private JButton cancelButton = new JButton("Cancel");
	private final JLabel lblNome = new JLabel("Nome");
	private final JLabel lblCognome = new JLabel("Cognome");
	private final JLabel lblNumero = new JLabel("Numero");
	private final JTextField txtNome = new JTextField();
	private final JTextField txtCognome = new JTextField();
	private final JTextField txtNumero = new JTextField();
	private GroupLayout gcontentPanel = new GroupLayout(contentPanel);
	// CHECKSTYLE:OFF:
	protected Navigator<?> fornitori;
	// CHECKSTYLE:ON:

	/**
	 * Costruttore DialogCerca.
	 * 
	 * @param o
	 *            Oggetto ObserverInterface
	 */
	// CHECKSTYLE:OFF: checkstyle:magicnumber
	public DialogCerca(final ObserverInterface o, final JFrame frame) {
		txtNumero.setColumns(10);
		txtCognome.setColumns(10);
		txtNome.setColumns(10);

		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(DialogCerca.class.getResource("/javax/swing/plaf/metal/icons/ocean/computer.gif")));
		this.setBounds(100, 100, 492, 252);
		this.setTitle("Cerca");
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.getContentPane().add(contentPanel, BorderLayout.CENTER);
		this.gcontentPanel.setHorizontalGroup(this.gcontentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gcontentPanel.createSequentialGroup().addContainerGap()
						.addGroup(gcontentPanel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblNome, GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
								.addComponent(lblCognome, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
						.addComponent(lblNumero, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGap(18)
						.addGroup(gcontentPanel.createParallelGroup(Alignment.LEADING, false).addComponent(txtNumero)
								.addComponent(txtCognome)
								.addComponent(txtNome, GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE))
						.addContainerGap(54, Short.MAX_VALUE)));
		this.gcontentPanel.setVerticalGroup(this.gcontentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gcontentPanel.createSequentialGroup().addGap(34)
						.addGroup(gcontentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblNome)
								.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gcontentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblCognome)
								.addComponent(txtCognome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gcontentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblNumero)
								.addComponent(txtNumero, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addContainerGap(114, Short.MAX_VALUE)));
		contentPanel.setLayout(gcontentPanel);

		{
			this.buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			this.getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				this.okButton.setActionCommand("OK");
				this.buttonPane.add(okButton);
				this.okButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(final ActionEvent e) {
						if (frame instanceof FattureGUI) {
							o.cercaFatture(txtNumero.getText(), txtNome.getText(), txtCognome.getText());
						}
						if (frame instanceof ClientiGUI) {
							
							fornitori = o.cercaClienti(txtNome.getText());
							fornitori.avanti();

						}
						if (frame instanceof FornitoriGUI) {
							fornitori = o.cercaFornitori(txtNome.getText());
							fornitori.avanti();
						}
						if (frame instanceof RiunioniGUI) {
							try {
								o.cercaRiunioni(txtNumero.getText(), txtNome.getText());
							} catch (ParseException e1) {
								JOptionPane.showMessageDialog(frame, "Errore nel inserimento Data");
							}
						}
						if (frame instanceof ScontriniGUI) {
							try {
							o.cercaScontrini(txtNumero.getText(), txtNome.getText());
							} catch (NumberFormatException e1){
								JOptionPane.showMessageDialog(frame, "Errore inserire un numero di scontrino");
							} catch (NoSuchElementException e2){
								JOptionPane.showMessageDialog(frame, "Non esiste nessuno scontrino con questo numero");
							}
						}
						o.abilitaFrame(true);
						dispose();
					}
				});
			}
			{
				this.cancelButton.setActionCommand("Cancel");
				this.buttonPane.add(cancelButton);
				this.cancelButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(final ActionEvent e) {
						o.abilitaFrame(true);
						dispose();

					}
				});
			}
		}
	}

	@Override
	public Map<String, String> getDataString(final ObserverInterface o) {
		Map<String, String> mappa = new HashMap<>();
		mappa.put("Nome", this.txtNome.getText());
		mappa.put("Numero", this.txtNumero.getText());
		return mappa;
	}
	
}
