package view.Interfaces;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import controller.Implementations.ViewPrisonerControllerImpl.BackListener;
import controller.Implementations.ViewPrisonerControllerImpl.ViewProfileListener;
import view.Components.PrisonManagerJFrame;
import view.Components.PrisonManagerJPanel;
import view.Components.SpringUtilities;
import view.Interfaces.Inter.ViewPrisoner;

public class ViewPrisonerView extends PrisonManagerJFrame implements ViewPrisoner{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7065438206105719545L;
	
	final PrisonManagerJPanel south;
	final JButton view = new JButton("Vedi profilo");
	final JButton back = new JButton("Indietro");
	final PrisonManagerJPanel north;
	final JLabel prisonerID = new JLabel("ID Prigioniero");
	final JTextField prisonerID1 = new JTextField(2);
	final JLabel name;
	final JLabel name1;
	final JLabel surname;
	final JLabel surname1 ;
	final JLabel birthDate;
	final JLabel birthDate1;
	final JLabel start;
	final JLabel start1;
	final JLabel end;
	final JLabel end1;
	final PrisonManagerJPanel center;
	final PrisonManagerJPanel east;
	final JTextArea textArea;
	private int rank;
	
	/**
	 * costruttore
	 * @param rank il rank della guardia che sta visualizzando il programma
	 */
	public ViewPrisonerView(int rank){
		this.rank=rank;
		this.setSize(550, 350);
		this.getContentPane().setLayout(new BorderLayout());
		center = new PrisonManagerJPanel(new SpringLayout());
		name = new JLabel("Nome:		");
		name1 = new JLabel();
		center.add(name);
		center.add(name1);
		surname = new JLabel("Cognome:	");
		surname1 = new JLabel();
		center.add(surname);
		center.add(surname1);
		birthDate = new JLabel("Data di nascita:	");
		birthDate1 = new JLabel();
		center.add(birthDate);
		center.add(birthDate1);
		start = new JLabel("Inizio reclusione:	");
		start1 = new JLabel();
		center.add(start);
		center.add(start1);
		end = new JLabel("Fine reclusione:	");
		end1 = new JLabel();
		center.add(end);
		center.add(end1);
		SpringUtilities.makeCompactGrid(center,
                5, 2, //rows, cols
                6, 6,        //initX, initY
                6, 6);       //xPad, yPad
		this.getContentPane().add(BorderLayout.CENTER,center);
		north = new PrisonManagerJPanel(new FlowLayout());
		north.add(prisonerID);
		north.add(prisonerID1);
		this.getContentPane().add(BorderLayout.NORTH,north);
		east = new PrisonManagerJPanel(new FlowLayout());
		textArea = new JTextArea();
	    textArea.setEditable(false);
		east.add(textArea);
		this.getContentPane().add(BorderLayout.EAST, east);
		south = new PrisonManagerJPanel(new FlowLayout());
		south.add(view);
		south.add(back);
		this.getContentPane().add(BorderLayout.SOUTH,south);
		this.setVisible(true);
	}
	
	public void addViewListener(ViewProfileListener viewListener){
		view.addActionListener(viewListener);
	}

	public void addBackListener(BackListener backListener){
		back.addActionListener(backListener);
	}
	
	public int getID(){
		if(prisonerID1.getText().equals(""))
			return -1;
		return Integer.valueOf(prisonerID1.getText());
	}
	
	public void setProfile(String name, String surname, String birthDate, String start, String end){
		this.name1.setText(name);
		this.surname1.setText(surname);
		this.birthDate1.setText(birthDate);
		this.start1.setText(start);
		this.end1.setText(end);
	}
	
	public void displayErrorMessage(String error){
		JOptionPane.showMessageDialog(this, error);
	}
	
	public int getRank(){
		return this.rank;
	}

	public void setTextArea(List<String>list){
		textArea.setText("");
		for(String s : list){
			textArea.append(s + "\n");
		}
	}
}
