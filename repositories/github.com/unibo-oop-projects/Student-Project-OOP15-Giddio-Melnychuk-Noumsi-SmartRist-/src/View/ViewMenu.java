package View;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;
import javax.swing.JTextArea;
import javax.swing.JList;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.AbstractListModel;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JCheckBox;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.JButton;

public class ViewMenu extends JFrame
{

	JButton btnOrdina = null;
	JComboBox comboBox = null;
	JComboBox comboBox_1 = null;
	JComboBox comboBox_2 = null;
	JComboBox comboBox_3 = null;
	JComboBox comboBox_4 = null;
	
	public ViewMenu()
	{
		this.setSize(800, 400);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(170, 43, 143, 64);
		getContentPane().add(panel);
		panel.setLayout(null);

		this.comboBox = new JComboBox();
		comboBox.setBounds(50, 43, 69, 20);
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "pane", "pasta" }));
		panel.add(comboBox);

		JLabel lblPrimo = new JLabel("Primo");
		lblPrimo.setBounds(50, 11, 46, 14);
		panel.add(lblPrimo);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(323, 43, 143, 64);
		getContentPane().add(panel_1);

		this.comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] { "gdrgdr", "fgvbfg" }));
		comboBox_1.setBounds(50, 43, 83, 20);
		panel_1.add(comboBox_1);

		JLabel lblSecondo = new JLabel("Secondo");
		lblSecondo.setBounds(50, 11, 46, 14);
		panel_1.add(lblSecondo);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(17, 43, 143, 64);
		getContentPane().add(panel_2);

		this.comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] { "patatine", "booh" }));
		comboBox_2.setBounds(50, 43, 65, 20);
		panel_2.add(comboBox_2);

		JLabel lblAntipasto = new JLabel("Antipasto\r\n");
		lblAntipasto.setBounds(50, 11, 46, 14);
		panel_2.add(lblAntipasto);

		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBounds(491, 43, 143, 64);
		getContentPane().add(panel_3);

		this.comboBox_3 = new JComboBox();
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] { "asdcasc", "dgnfgn" }));
		comboBox_3.setBounds(50, 43, 83, 20);
		panel_3.add(comboBox_3);

		JLabel lblDrink = new JLabel("Drink\r\n");
		lblDrink.setBounds(50, 11, 46, 14);
		panel_3.add(lblDrink);

		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBounds(641, 43, 143, 64);
		getContentPane().add(panel_4);

		this.comboBox_4 = new JComboBox();
		comboBox_4.setModel(new DefaultComboBoxModel(new String[] { "ananas", "caramelle", "gelato" }));
		comboBox_4.setBounds(34, 44, 83, 20);
		panel_4.add(comboBox_4);

		JLabel lblDessert = new JLabel("Dessert");
		lblDessert.setBounds(50, 11, 46, 14);
		panel_4.add(lblDessert);

		// quando si ordina

		this.btnOrdina = new JButton("ORDINA");
		this.btnOrdina.setBounds(350, 200, 89, 23);
		getContentPane().add(this.btnOrdina);
	}
	
	public String[] getPiats()
	{
		myBox(this.comboBox_4);
		myBox(this.comboBox);
		myBox(this.comboBox_3);
		myBox(this.comboBox_2);
		myBox(this.comboBox_1);
		String[] piat = { this.comboBox.getSelectedItem().toString(), this.comboBox_1.getSelectedItem().toString(),
				this.comboBox_2.getSelectedItem().toString(), this.comboBox_3.getSelectedItem().toString(),
				this.comboBox_4.getSelectedItem().toString() };

		return piat;
	}
	
	protected void myBox(JComboBox comboBox)
	{
		if (comboBox.getSelectedItem() != null)
		{
			System.out.println(comboBox.getSelectedItem().toString());
		}
	}

	public void addButtonListener(ActionListener listener)
	{
		this.btnOrdina.addActionListener(listener);
	}

}
