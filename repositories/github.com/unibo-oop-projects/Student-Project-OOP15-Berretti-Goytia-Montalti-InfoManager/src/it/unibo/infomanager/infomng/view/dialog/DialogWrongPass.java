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
 * Classe che definisce DialogWrongPass.
 * 
 * @author Alessandro
 *
 */
public class DialogWrongPass extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3994334871735209451L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblLaPasswordInserita = new JLabel(
			"La password inserita non corrisponde con l'username inserito, riprovare con un altra password");
	private JPanel buttonPane = new JPanel();
	private JButton okButton = new JButton("OK");
	private JButton cancelButton = new JButton("Cancel");

	/**
	 * Costruttore del DialogWrongPass.
	 * 
	 * @param o
	 *            Oggetto ObserverInterface
	 */
	// CHECKSTYLE:OFF: checkstyle:magicnumber
	public DialogWrongPass(final ObserverInterface o) {
		this.setTitle("Errore");
		this.setIconImage(Toolkit.getDefaultToolkit()
				.getImage(DialogWrongPass.class.getResource("/javax/swing/plaf/metal/icons/Error.gif")));
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setBounds(100, 100, 625, 174);
		this.getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.getContentPane().add(contentPanel, BorderLayout.CENTER);
		this.contentPanel.setLayout(new BorderLayout(0, 0));
		{
			this.lblLaPasswordInserita.setIcon(
					new ImageIcon(DialogWrongPass.class.getResource("/javax/swing/plaf/metal/icons/Warn.gif")));
			this.contentPanel.add(lblLaPasswordInserita, BorderLayout.CENTER);
		}
		{

			this.buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			this.getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{

				this.okButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						o.abilitaFrame(true);
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
						o.abilitaFrame(true);
						dispose();
					}
				});
				this.buttonPane.add(cancelButton);
			}
		}
	}

}
