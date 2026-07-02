package View.cardlayout;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import pro_ristorante_oop.Pair;
import pro_ristorante_oop.Piatti;

public class ViewCuoco extends JPanel
{

	private static final int index = 2;
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnPrendeOrdine;
	private JButton btnCucina;
    private JList list;
    private JList list2;
	
	public ViewCuoco()
	{

		this.setLayout(null);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(22, 11, 46, 14);
		this.add(lblNome);

		JLabel lblCognome = new JLabel("Cognome");
		lblCognome.setBounds(22, 43, 46, 14);
		this.add(lblCognome);

		textField = new JTextField();
		textField.setBounds(95, 8, 86, 20);
		textField.setColumns(10);
		this.add(textField);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(95, 40, 86, 20);
		this.add(textField_1);

		this.list = new JList();
		list.setForeground(Color.WHITE);
		list.setBackground(Color.BLACK);
		list.setBounds(198, 10, 232, 253);
		this.add(list);
		this.list2 = new JList();
		list2.setForeground(Color.WHITE);
		list2.setBackground(Color.BLACK);
		list2.setBounds(198, 300, 232, 253);
		this.add(list2);
		this.btnPrendeOrdine = new JButton("Prende Ordine");
		this.btnPrendeOrdine.setBounds(22, 111, 101, 23);
		this.add(btnPrendeOrdine);

		this.btnCucina = new JButton("Cucina");
		btnCucina.setBounds(22, 158, 89, 23);
		this.add(btnCucina);

	}
	List<String> pi = new LinkedList<>();
	public void addOrder(Map<Pair<Integer,Integer>,List<Piatti>> ordine)
	{
		//@TODO Sascha: Read the data from the ordine into the fields of the cuoco view
		// for example I have added two dummy strings to check if that works.
		int j = 0;
		for(Pair<Integer,Integer> i : ordine.keySet()){
			j++;
			if(j==ordine.size()){
				List<Piatti> p = ordine.get(i);
				for(int k = 0;k<p.size();k++){
					pi.add(p.get(k).getname());
				}
			}
		}
		this.setPiats(pi.toArray((new String[] {} )));
	}
	public String[] getListPrende(){
		return pi.toArray((new String[] {} ));
		
	}
	public void clearList(){
		this.list = null;
	}
	public void setPiats(String[] val)
	{
		this.list.setModel(new AbstractListModel()
		{
			String[] values = val;

			public int getSize()
			{
				return values.length;
			}

			public Object getElementAt(int index)
			{
				return values[index];
			}
		});
	}
	

	public void setPiats2(String[] val)
	{
		this.list2.setModel(new AbstractListModel()
		{
			String[] values = val;

			public int getSize()
			{
				return values.length;
			}

			public Object getElementAt(int index)
			{
				return values[index];
			}
		});
	}
	public void addPrendeOrdineButtonListener(ActionListener listener)
	{
		this.btnPrendeOrdine.addActionListener(listener);
	}
	
	public void addCucinaButtonListener(ActionListener listener)
	{
		this.btnCucina.addActionListener(listener);
	}

	public String getName()
	{
		return this.textField.getText().trim();
	}

	public String getSurname()
	{
		return this.textField_1.getText().trim();
	}
	
	public static int getIndex()
	{
		return index;
	}

}
