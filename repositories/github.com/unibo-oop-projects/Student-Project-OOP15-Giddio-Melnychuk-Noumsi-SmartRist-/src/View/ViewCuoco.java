package View;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.AbstractListModel;
import javax.swing.JButton;

public class ViewCuoco {
Container contenuto = new Container();
private JTextField textField;
private JTextField textField_1;
private JButton btnPrendeOrdine;

	public ViewCuoco (String[] val){
		JFrame frame = new JFrame("Cuoco");
		frame.getContentPane().setLayout(null);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(22, 11, 46, 14);
		frame.getContentPane().add(lblNome);
		
		JLabel lblCognome = new JLabel("Cognome");
		lblCognome.setBounds(22, 43, 46, 14);
		frame.getContentPane().add(lblCognome);
		
		textField = new JTextField();
		textField.setBounds(95, 8, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(95, 40, 86, 20);
		frame.getContentPane().add(textField_1);
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
		JButton btnPrendeOrdine = new JButton("Prende Ordine");
		btnPrendeOrdine.setBounds(22, 111, 101, 23);
		frame.getContentPane().add(btnPrendeOrdine);
		
		JButton btnCucina = new JButton("Cucina");
		btnCucina.setBounds(22, 158, 89, 23);
		frame.getContentPane().add(btnCucina);
		frame.setSize(500, 500);
		frame.setVisible(true);
	}
		public void addButtonListener(ActionListener listener) {
			this.btnPrendeOrdine.addActionListener(listener);
		}
		public String getName() {
	        return this.textField.getText().trim();
	    }
	 
	    public String getSurname() {
	        return this.textField_1.getText().trim();
	    }
	    
	        
}

