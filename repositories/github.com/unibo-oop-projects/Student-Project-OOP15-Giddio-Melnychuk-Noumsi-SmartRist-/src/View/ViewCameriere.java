package View;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewCameriere {
	
	Container contenuto = new Container();
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnOccupato;
	
	public ViewCameriere(String[] val){
		JFrame frame = new JFrame("Cameriere");
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(66, 8, 104, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(66, 39, 104, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 11, 46, 14);
		frame.getContentPane().add(lblNome);
		
		JLabel lblCognome = new JLabel("Cognome");
		lblCognome.setBounds(10, 42, 46, 14);
		frame.getContentPane().add(lblCognome);
		
		JList list = new JList();
		list.setModel(new AbstractListModel() {
			String[] values = val;
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setForeground(Color.WHITE);
		list.setBackground(Color.BLACK);
		list.setBounds(198, 10, 232, 253);
		frame.getContentPane().add(list);
			
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Root\\workspace\\progetto\\img\\ico-pointvert.png"));
		lblNewLabel.setBounds(115, 183, 27, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblStato = new JLabel("Stato");
		lblStato.setBounds(49, 183, 46, 14);
		frame.getContentPane().add(lblStato);
		
		JButton btnLibero = new JButton("Libero");
		btnLibero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Root\\workspace\\progetto\\img\\ico-pointvert.png"));
			}
		});
		btnLibero.setBounds(10, 140, 89, 23);
		frame.getContentPane().add(btnLibero);
		
		JButton btnOccupato = new JButton("Occupato\r\n");
		btnOccupato.setBounds(10, 218, 89, 23);
		frame.getContentPane().add(btnOccupato);
		btnOccupato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Root\\workspace\\progetto\\img\\150605104437964180.png"));
			}
		});
		
		frame.setSize(516, 500);
		frame.setVisible(true);
		
		
	}
	public void addButtonListener(ActionListener listener) {
		this.btnOccupato.addActionListener(listener);
	}
	
}
