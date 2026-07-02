package View.cardlayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.AbstractListModel;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import util.ImageLoader;

public class ViewCameriere extends JPanel
{

	//the index in the cardlayout stack of the main window
	private static final int index = 3;
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnOccupato;
	private JList list;
	
    public ViewCameriere(){
		
		
		textField = new JTextField();
		textField.setBounds(66, 8, 104, 20);
		this.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(66, 39, 104, 20);
		this.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 11, 46, 14);
		this.add(lblNome);
		
		JLabel lblCognome = new JLabel("Cognome");
		lblCognome.setBounds(10, 42, 46, 14);
		this.add(lblCognome);
		
		this.list = new JList();
		//Dummy val, was before in the constructor
		String[] val = new String[] {};
		this.list.setModel(new AbstractListModel() {
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
		list.setBounds(198, 300, 232, 253);
		this.add(list);
			
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/images/ico-pointvert.png")));
		lblNewLabel.setBounds(115, 183, 27, 14);
		this.add(lblNewLabel);
		
		JLabel lblStato = new JLabel("Stato");
		lblStato.setBounds(49, 183, 46, 14);
		this.add(lblStato);
		
		JButton btnLibero = new JButton("Libero");
		btnLibero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/images/ico-pointvert.png")));
			}
		});
		btnLibero.setBounds(10, 140, 89, 23);
		this.add(btnLibero);
		
		this.btnOccupato = new JButton("Occupato\r\n");
		btnOccupato.setBounds(10, 218, 89, 23);
		this.add(btnOccupato);
		btnOccupato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/images/150605104437964180.png")));
			}
		});
		
		
	}

	public void setMenuList(String[] menuList)
	{
		this.list.setModel(new AbstractListModel() {
			String[] values = menuList;
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
	}
	
	public void addButtonListener(ActionListener listener) {
		this.btnOccupato.addActionListener(listener);
	}
	
	public static int getIndex()
    {
    	return index;
    }
}
