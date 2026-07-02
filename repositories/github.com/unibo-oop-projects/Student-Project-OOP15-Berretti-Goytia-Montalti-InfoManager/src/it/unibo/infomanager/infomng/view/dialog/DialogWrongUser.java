package it.unibo.infomanager.infomng.view.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import it.unibo.infomanager.infomng.view.interfaces.ObserverInterface;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

/**
 * Classe che definisce DIalogWrongUser.
 * 
 * @author Alessandro
 *
 */
public class DialogWrongUser extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5939201597436719902L;
	private final JPanel contentPanel = new JPanel();
	private JButton cancelButton = new JButton("Cancel");
	private JLabel lblLusernameInseritoNon = new JLabel(
			"l'Utente inserito non risulta essere registrato, riprovare con un altro Utente o provare a registrarsi");
	private JPanel buttonPane = new JPanel();
	private JButton okButton = new JButton("OK");

	/**
	 * Costruttore del DialogWrongUser.
	 * 
	 * @param o
	 *            Oggetto ObserverInterface
	 */
	// CHECKSTYLE:OFF: checkstyle:magicnumber
	public DialogWrongUser(final ObserverInterface o) {
		this.setTitle("Errore");
		this.setIconImage(Toolkit.getDefaultToolkit()
				.getImage(DialogWrongUser.class.getResource("/javax/swing/plaf/metal/icons/Error.gif")));
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.setBounds(100, 100, 672, 195);
		this.getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.getContentPane().add(contentPanel, BorderLayout.CENTER);
		this.contentPanel.setLayout(new BorderLayout(0, 0));
		{
			this.lblLusernameInseritoNon.setIcon(
					new ImageIcon(DialogWrongUser.class.getResource("/javax/swing/plaf/metal/icons/Warn.gif")));
			this.contentPanel.add(lblLusernameInseritoNon, BorderLayout.CENTER);
		}
		{
			this.buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			this.getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				this.okButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				this.buttonPane.add(okButton);
				this.getRootPane().setDefaultButton(okButton);
			}
			{
				this.cancelButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				this.buttonPane.add(cancelButton);
			}
		}
	}

}
