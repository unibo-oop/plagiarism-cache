package view.Interfaces;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import controller.Implementations.ViewGuardControllerImpl.BackListener;
import controller.Implementations.ViewGuardControllerImpl.ViewGuardListener;
import view.Components.PrisonManagerJFrame;
import view.Components.PrisonManagerJPanel;
import view.Components.SpringUtilities;
import view.Interfaces.Inter.ViewGuard;

public class ViewGuardView extends PrisonManagerJFrame implements ViewGuard{

	/**
	 * 
	 */
	private static final long serialVersionUID = 86423038519594644L;
	final PrisonManagerJPanel south;
	final JButton view = new JButton("Vedi profilo");
	final JButton back = new JButton("Indietro");
	final PrisonManagerJPanel north;
	final JLabel guardID = new JLabel("ID Guardia");
	final JTextField guardID1 = new JTextField(2);
	final JLabel name;
	final JLabel name1;
	final JLabel surname;
	final JLabel surname1 ;
	final JLabel birthDate;
	final JLabel birthDate1;
	final JLabel rankl;
	final JLabel rank1;
	final JLabel telephone;
	final JLabel telephone1;
	final PrisonManagerJPanel center;
	private int rank;
	
	/**
	 * costruttore
	 * @param rank il rank della guardia che sta visualizzando il programma
	 */
	public ViewGuardView(int rank){
		this.rank=rank;

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
		birthDate = new JLabel("Data di nascita :	");
		birthDate1 = new JLabel();
		center.add(birthDate);
		center.add(birthDate1);
		rankl = new JLabel("Grado:	");
		rank1 = new JLabel();
		center.add(rankl);
		center.add(rank1);
		telephone = new JLabel("Numero di telefono:	");
		telephone1 = new JLabel();
		center.add(telephone);
		center.add(telephone1);
		SpringUtilities.makeCompactGrid(center,
                5, 2, //rows, cols
                6, 6,        //initX, initY
                6, 6);       //xPad, yPad
		this.getContentPane().add(BorderLayout.CENTER,center);
		north = new PrisonManagerJPanel(new FlowLayout());
		north.add(guardID);
		north.add(guardID1);
		this.getContentPane().add(BorderLayout.NORTH,north);
		south = new PrisonManagerJPanel(new FlowLayout());
		south.add(view);
		south.add(back);
		this.getContentPane().add(BorderLayout.SOUTH,south);
		this.setSize(500, 230);
		this.setVisible(true);
	}
	
	public int getRank(){
		return this.rank;
	}
	
	public void addViewListener(ViewGuardListener viewListener){
		view.addActionListener(viewListener);
	}

	public void addBackListener(BackListener backListener){
		back.addActionListener(backListener);
	}
	public void displayErrorMessage(String error){
		JOptionPane.showMessageDialog(this, error);
	}
	
	public int getID(){
		if(guardID1.getText().equals(""))
			return -1;
		return Integer.valueOf(guardID1.getText());
	}
	
	public void setName(String name){
		this.name1.setText(name);
	}
	
	public void setSurname(String surname){
		this.surname1.setText(surname);
	}
	public void setBirth(String birth){
		this.birthDate1.setText(birth);
	}
	public void setRank(String rank1){
		this.rank1.setText(rank1);
	}
	public void setTelephone(String telephone){
		this.telephone1.setText(telephone);
	}

}
