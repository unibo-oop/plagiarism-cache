/**
 *@author Ceccarelli 
 */
package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import view.observer.AddSubscriptionObserver;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import model.SubscriptionModel;

import javax.swing.JTextField;
import javax.swing.JButton;

public class AddSubscriptionPanelImpl extends JPanel implements AddSubscriptionPanel, ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField txtName;
	private JTextField txtSurname;
	private JLabel lblTitle;
	private JLabel lblAccountHolder;
	private JLabel lblName;
	private JLabel lblSurname;
	private JLabel lblType;
	private JButton btnAddSubcription;
	private AddSubscriptionObserver observer;
	private JButton btnClear;
	private JTable tblAllSubscription;
	private DefaultTableModel modelAllSubscription;
	private JScrollPane scpAllSubscription;
	private JLabel lblDescriptionBronze;
	private JLabel lblDescriptionSilver;
	private JLabel lblDescriptionGold;
	private JLabel lblDescriptionPlatinum;
	private JLabel lblSubscriptionType;

	/**
	 * Create the panel.
	 */
	public AddSubscriptionPanelImpl() {
		this.setLayout(null);
		setBackground(SystemColor.activeCaption);

		modelAllSubscription = new DefaultTableModel(new Object[][] {},
				new String[] { "Nome", "Cognome", "Tipo abbonamento", "Numero acquisti", "Codice abbonamento" });

		
		scpAllSubscription = new JScrollPane();
		scpAllSubscription.setBounds(400, 73, 474, 420);
		add(scpAllSubscription);

		tblAllSubscription = new JTable() {			
			private static final long serialVersionUID = 1L;

			@Override
			    public boolean isCellEditable(int row, int column) {
			        //all cells false
			        return false;
			    }
			}; 
		scpAllSubscription.setViewportView(tblAllSubscription);
		tblAllSubscription.setModel(modelAllSubscription);
		tblAllSubscription.getColumnModel().getColumn(0).setPreferredWidth(74);
		tblAllSubscription.getColumnModel().getColumn(1).setPreferredWidth(66);
		tblAllSubscription.getColumnModel().getColumn(2).setPreferredWidth(126);
		tblAllSubscription.setBorder(new LineBorder(new Color(0, 0, 0)));
		tblAllSubscription.setFont(new Font("Calibri", Font.PLAIN, 13));
		tblAllSubscription.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);

		lblTitle = new JLabel("Abbonamenti");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 30));
		lblTitle.setForeground(new Color(255, 69, 0));
		lblTitle.setBounds(10, 11, 880, 51);
		add(lblTitle);

		lblAccountHolder = new JLabel("Dati intestatario:");
		lblAccountHolder.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 15));
		lblAccountHolder.setBounds(30, 115, 171, 14);
		add(lblAccountHolder);

		lblName = new JLabel("Nome:");
		lblName.setFont(new Font("Calibri", Font.ITALIC, 13));
		lblName.setBounds(30, 140, 171, 14);
		add(lblName);

		lblSurname = new JLabel("Cognome:");
		lblSurname.setFont(new Font("Calibri", Font.ITALIC, 13));
		lblSurname.setBounds(30, 196, 171, 14);
		add(lblSurname);

		txtName = new JTextField();
		txtName.setFont(new Font("Calibri", Font.ITALIC, 13));
		txtName.setBounds(30, 165, 171, 20);
		add(txtName);
		txtName.setColumns(10);

		txtSurname = new JTextField();
		txtSurname.setFont(new Font("Calibri", Font.ITALIC, 13));
		txtSurname.setBounds(30, 220, 171, 20);
		add(txtSurname);
		txtSurname.setColumns(10);

		lblType = new JLabel("Tipo abbonamento");
		lblType.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 15));
		lblType.setBounds(30, 251, 171, 14);
		add(lblType);

		btnAddSubcription = new JButton("Aggiungi abbonameto");
		btnAddSubcription.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 16));
		btnAddSubcription.setBounds(30, 307, 180, 70);
		btnAddSubcription.addActionListener(this);
		add(btnAddSubcription);

		btnClear = new JButton("Pulisci tutto");
		btnClear.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 15));
		btnClear.setBounds(30, 443, 125, 50);
		btnClear.addActionListener(this);
		add(btnClear);

		lblDescriptionBronze = new JLabel("Bronzo : 1-20 libri\r\n\r\n");
		lblDescriptionBronze.setVerticalAlignment(SwingConstants.TOP);
		lblDescriptionBronze.setHorizontalAlignment(SwingConstants.LEFT);
		lblDescriptionBronze.setFont(new Font("Calibri", Font.ITALIC, 13));
		lblDescriptionBronze.setBounds(268, 115, 220, 28);
		add(lblDescriptionBronze);

		lblDescriptionSilver = new JLabel("Argento : 21-50 libri\r\n\r\n");
		lblDescriptionSilver.setVerticalAlignment(SwingConstants.TOP);
		lblDescriptionSilver.setHorizontalAlignment(SwingConstants.LEFT);
		lblDescriptionSilver.setFont(new Font("Calibri", Font.ITALIC, 13));
		lblDescriptionSilver.setBounds(268, 168, 220, 28);
		add(lblDescriptionSilver);

		lblDescriptionGold = new JLabel("Oro : 51-100 libri\r\n\r\n");
		lblDescriptionGold.setVerticalAlignment(SwingConstants.TOP);
		lblDescriptionGold.setHorizontalAlignment(SwingConstants.LEFT);
		lblDescriptionGold.setFont(new Font("Calibri", Font.ITALIC, 13));
		lblDescriptionGold.setBounds(268, 223, 220, 28);
		add(lblDescriptionGold);

		lblDescriptionPlatinum = new JLabel("Platino : 101-200 libri\r\n\r\n");
		lblDescriptionPlatinum.setVerticalAlignment(SwingConstants.TOP);
		lblDescriptionPlatinum.setHorizontalAlignment(SwingConstants.LEFT);
		lblDescriptionPlatinum.setFont(new Font("Calibri", Font.ITALIC, 13));
		lblDescriptionPlatinum.setBounds(268, 279, 220, 28);
		add(lblDescriptionPlatinum);

		lblSubscriptionType = new JLabel("Bronzo");
		lblSubscriptionType.setFont(new Font("Calibri", Font.ITALIC, 13));
		lblSubscriptionType.setBounds(30, 276, 171, 14);
		add(lblSubscriptionType);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object isPressed = e.getSource();
		if (isPressed == btnClear) {
			this.clearPanel();
		} else if (isPressed == btnAddSubcription) {
			if(!(txtName.getText().equals("")&& txtSurname.getText().equals("")))
			this.observer.addNewSubcriptionClicked(txtName.getText(), txtSurname.getText());
			else
				displayMessage("Riempire correttamente tutti i campi richisti");
		}

	}

	@Override
	public void attachObserver(AddSubscriptionObserver observer) {
		this.observer = observer;
		this.setAllSubscriptions();

	}

	@Override
	public void clearPanel() {
		this.txtName.setText("");
		this.txtSurname.setText("");
	}

	public void setAllSubscriptions() {
		clearTable(modelAllSubscription);
		Map<Integer, SubscriptionModel> tmp = this.observer.getAllSubscriptions();
		int i = 0;
		for (Integer entry : tmp.keySet()) {
			if (tmp.get(entry) == null) {
			} else {
				Object[] obj = { tmp.get(entry).getName(), tmp.get(entry).getSurname(),
						tmp.get(entry).getType(), tmp.get(entry).getBook(),tmp.keySet().toArray()[i]};
				((DefaultTableModel) modelAllSubscription).addRow(obj);

				tblAllSubscription.repaint();
				i++;
			}
		}
		if (tblAllSubscription.getRowCount() > 0) {
			tblAllSubscription.setRowSelectionInterval(0, 0);
		}
	}

		private void clearTable(DefaultTableModel model){
		for(int i = model.getRowCount()-1; i >= 0; i--){
			model.removeRow(i);
		}
	}

		@Override
		public void displayMessage(String message) {
			JOptionPane.showMessageDialog(null, message, "Attenzione", JOptionPane.PLAIN_MESSAGE);			
		}	
}