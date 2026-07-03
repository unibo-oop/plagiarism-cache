package view.Interfaces;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.Implementations.RemovePrisonerControllerImpl.BackListener;
import controller.Implementations.RemovePrisonerControllerImpl.RemoveListener;
import view.Components.PrisonManagerJFrame;
import view.Components.PrisonManagerJPanel;
import view.Interfaces.Inter.RemovePrisoner;

public class RemovePrisonerView extends PrisonManagerJFrame implements RemovePrisoner{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8578870382924181404L;
	
	final PrisonManagerJPanel center;
	final JButton remove = new JButton("Rimuovi");
	final JButton back = new JButton("Indietro");
	final PrisonManagerJPanel north;
	final JLabel prisonerID = new JLabel("ID Prigioniero");
	final JTextField prisonerID1 = new JTextField(2);
	private int rank;
	
	/**
	 * costruttore
	 * @param rank il rank della guardia che sta visualizzando il programma
	 */
	public RemovePrisonerView(int rank){
		this.rank=rank;
		this.setSize(450, 120);
		this.getContentPane().setLayout(new BorderLayout());
		north = new PrisonManagerJPanel(new FlowLayout());
		north.add(prisonerID);
		north.add(prisonerID1);
		this.getContentPane().add(BorderLayout.NORTH,north);
		center = new PrisonManagerJPanel(new FlowLayout());
		center.add(remove);
		center.add(back);
		this.getContentPane().add(BorderLayout.CENTER,center);
		this.setVisible(true);
	}
	
	public void addRemoveListener(RemoveListener removeListener){
		remove.addActionListener(removeListener);
	}

	public void addBackListener(BackListener backListener){
		back.addActionListener(backListener);
	}

	public int getID(){
		if(prisonerID1.getText().equals(""))
			return -1;
		return Integer.valueOf(prisonerID1.getText());
	}
	
	public void displayErrorMessage(String error){
		JOptionPane.showMessageDialog(this, error);
	}
	
	public int getRank(){
		return this.rank;
	}
}
