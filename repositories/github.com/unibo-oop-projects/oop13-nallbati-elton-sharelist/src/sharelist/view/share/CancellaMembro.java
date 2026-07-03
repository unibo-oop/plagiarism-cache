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
 * Cancella Membro di una lista
 * 
 * @author Elton Nallbati
 * @version 1.0
 */
public class CancellaMembro extends JDialog {

	private static final long serialVersionUID = 660502698170947650L;
	private final JPanel contentPanel = new JPanel();
	private boolean okState = false;
	
	public JTextField textField_CancellaMembro;

	/**
	 * Costruttore CancellaMembro
	 * 
	 * @param view
	 * 				View
	 */
	public CancellaMembro(ApplicationView view) {
		super(view);
		setTitle("Cancella Membro");
		
		setSize(new Dimension(400, 150));
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
				JLabel lblSicuroDiVoler = new JLabel("Sicuro di voler cancellare la condivisione col Membro?");
				GridBagConstraints gbc_lblSicuroDiVoler = new GridBagConstraints();
				gbc_lblSicuroDiVoler.insets = new Insets(0, 0, 5, 0);
				gbc_lblSicuroDiVoler.gridx = 1;
				gbc_lblSicuroDiVoler.gridy = 0;
				data.add(lblSicuroDiVoler, gbc_lblSicuroDiVoler);
			}
			{
				JLabel lable_Nome = new JLabel("Nome");
				GridBagConstraints gbc_lblNome = new GridBagConstraints();
				gbc_lblNome.insets = new Insets(0, 0, 0, 5);
				gbc_lblNome.anchor = GridBagConstraints.EAST;
				gbc_lblNome.gridx = 0;
				gbc_lblNome.gridy = 1;
				data.add(lable_Nome, gbc_lblNome);
			}
			{
				textField_CancellaMembro = new JTextField();
				textField_CancellaMembro.setEnabled(false);
				textField_CancellaMembro.setColumns(15);
				GridBagConstraints gbc_textField_CancellaMembro = new GridBagConstraints();
				gbc_textField_CancellaMembro.fill = GridBagConstraints.HORIZONTAL;
				gbc_textField_CancellaMembro.gridx = 1;
				gbc_textField_CancellaMembro.gridy = 1;
				data.add(textField_CancellaMembro, gbc_textField_CancellaMembro);
				textField_CancellaMembro.setColumns(15);
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
				JButton okButton = new JButton("SI");
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
				JButton cancelButton = new JButton("NO");
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
		this.textField_CancellaMembro.setText("");
	}
}