package sharelist.view.share;

import sharelist.view.application.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * Invita Persona a partecipare ad una lista
 * 
 * @author Elton Nallbati
 * @version 1.0
 */
public class AggiungiPersona extends JDialog {

	private static final long serialVersionUID = 660502698170947650L;
	private final JPanel contentPanel = new JPanel();
	private boolean okState = false;
	
	private JTextField textField_NuovaPersona;

	/**
	 * Costruttore AggiungiPersona
	 * 
	 * @param view
	 * 				View
	 */
	public AggiungiPersona(ApplicationView view) {
		super(view);
		setTitle("Invita Persona");
		
		setSize(new Dimension(300, 150));
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		{
			JPanel data = new JPanel();
			contentPanel.add(data);
			GridBagLayout gbl_data = new GridBagLayout();
			gbl_data.columnWidths = new int[] {0, 0, 10};
			gbl_data.rowHeights = new int[] {0, 0, 10};
			gbl_data.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
			gbl_data.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
			data.setLayout(gbl_data);
			{
				JLabel lable_Email = new JLabel("Email");
				GridBagConstraints gbc_lable_Email = new GridBagConstraints();
				gbc_lable_Email.insets = new Insets(0, 0, 0, 5);
				gbc_lable_Email.anchor = GridBagConstraints.EAST;
				gbc_lable_Email.gridx = 0;
				gbc_lable_Email.gridy = 1;
				data.add(lable_Email, gbc_lable_Email);
			}
			{
				textField_NuovaPersona = new JTextField();
				textField_NuovaPersona.setColumns(15);
				GridBagConstraints gbc_textField_NuovaPersona = new GridBagConstraints();
				gbc_textField_NuovaPersona.fill = GridBagConstraints.HORIZONTAL;
				gbc_textField_NuovaPersona.gridx = 1;
				gbc_textField_NuovaPersona.gridy = 1;
				data.add(textField_NuovaPersona, gbc_textField_NuovaPersona);
				textField_NuovaPersona.setColumns(15);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JSeparator separator = new JSeparator();
				buttonPane.add(separator);
			}
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						okState = true;  
						setVisible(false);
					}
				});
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						okState = false;
						setVisible(false);
					}
				});
			}
		}	
	}
	
	/**
	 *  Ritorna true se abbiamo premuto OK
	 */
	public boolean getOKState(){
		return this.okState; 
	}
	
	/**
	 *  Reinizializza i componenti
	 */
	public void reinit(){
		this.textField_NuovaPersona.setText("");
	}
	
	/**
	 *  Ritorna email persona invitata
	 */
	public String getNuovaPersona(){
		return this.textField_NuovaPersona.getText();
	}
}