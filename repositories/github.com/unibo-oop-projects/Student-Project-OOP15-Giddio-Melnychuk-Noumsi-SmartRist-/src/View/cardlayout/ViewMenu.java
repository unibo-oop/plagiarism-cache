package View.cardlayout;

import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import pro_ristorante_oop.Piatti;
import pro_ristorante_oop.TypePlate;

public class ViewMenu extends JPanel {
	
	private static final int index = 6;
	
	JButton btnOrdina = null;
	JComboBox comboBox = null;
	JComboBox comboBox_1 = null;
	JComboBox comboBox_2 = null;
	JComboBox comboBox_3 = null;
	JComboBox comboBox_4 = null;
	private JTextField textField;
    
	public ViewMenu(List<Piatti> piatti)
	{
		
        this.setLayout(null);
        List<String> primolist = new LinkedList<>();
        List<String> secondolist = new LinkedList<>();
        List<String> antipastolist = new LinkedList<>();
        List<String> drinklist = new LinkedList<>();
        List<String> dolcelist = new LinkedList<>();
        
        textField = new JTextField();
		textField.setBounds(10, 123, 104, 20);
		this.add(textField);
		textField.setColumns(10);
		JLabel lblNome = new JLabel("NUMERO TAVOLO");
		lblNome.setBounds(10, 112, 46, 14);
		this.add(lblNome);
        
		this.comboBox = new JComboBox();
		for(int i = 0;i<piatti.size();i++){
			if(piatti.get(i).getType().equals(TypePlate.PRIMO)){
				primolist.add(piatti.get(i).getname());
			}
		}
		for(int i = 0;i<primolist.size();i++){
				this.comboBox.setModel(new DefaultComboBoxModel(primolist.toArray(new String[] {} )));
		}
		comboBox.setBounds(50, 43, 69, 20);
		
		this.add(comboBox);

		JLabel lblPrimo = new JLabel("Primo");
		lblPrimo.setBounds(50, 11, 46, 14);
		this.add(lblPrimo);

		this.comboBox_1 = new JComboBox();
		for(int i = 0;i<piatti.size();i++){
			if(piatti.get(i).getType().equals(TypePlate.SECONDO)){
				secondolist.add(piatti.get(i).getname());
			}
		}
		for(int i = 0;i<secondolist.size();i++){
				this.comboBox_1.setModel(new DefaultComboBoxModel(secondolist.toArray(new String[] {} )));
		}
		comboBox_1.setBounds(150, 43, 80, 20);
		this.add(comboBox_1);

		JLabel lblSecondo = new JLabel("Secondo");
		lblSecondo.setBounds(150, 11, 80, 14);
		this.add(lblSecondo);

		this.comboBox_2 = new JComboBox();
		for(int i = 0;i<piatti.size();i++){
			if(piatti.get(i).getType().equals(TypePlate.ANTIPASTO)){
				antipastolist.add(piatti.get(i).getname());
			}
		}
		for(int i = 0;i<antipastolist.size();i++){
				this.comboBox_2.setModel(new DefaultComboBoxModel(antipastolist.toArray(new String[] {} )));
		}
		comboBox_2.setBounds(250, 43, 80, 20);
		this.add(comboBox_2);

		JLabel lblAntipasto = new JLabel("Antipasto\r\n");
		lblAntipasto.setBounds(250, 11, 80, 14);
		this.add(lblAntipasto);

		this.comboBox_3 = new JComboBox();
		for(int i = 0;i<piatti.size();i++){
			if(piatti.get(i).getType().equals(TypePlate.DRINK)){
				drinklist.add(piatti.get(i).getname());
			}
		}
		for(int i = 0;i<drinklist.size();i++){
				this.comboBox_3.setModel(new DefaultComboBoxModel(drinklist.toArray(new String[] {} )));
		}
		comboBox_3.setBounds(350, 43, 80, 20);
		this.add(comboBox_3);

		JLabel lblDrink = new JLabel("Drink\r\n");
		lblDrink.setBounds(350, 11, 80, 14);
		this.add(lblDrink);

		this.comboBox_4 = new JComboBox();
		for(int i = 0;i<piatti.size();i++){
			if(piatti.get(i).getType().equals(TypePlate.DOLCE)){
				dolcelist.add(piatti.get(i).getname());
			}
		}
		for(int i = 0;i<dolcelist.size();i++){
				this.comboBox_4.setModel(new DefaultComboBoxModel(dolcelist.toArray(new String[] {} )));
		}
		comboBox_4.setBounds(450, 44, 80, 20);
		this.add(comboBox_4);

		JLabel lblDessert = new JLabel("Dessert");
		lblDessert.setBounds(450, 11, 80, 14);
		this.add(lblDessert);

		// quando si ordina
		this.btnOrdina = new JButton("ORDINA");
		this.btnOrdina.setBounds(50, 200, 89, 23);
		this.add(this.btnOrdina);
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
	public String getTav()
	{
		return this.textField.getText().trim();
	}
	public static int getIndex()
	{
		return index;
	}


}

