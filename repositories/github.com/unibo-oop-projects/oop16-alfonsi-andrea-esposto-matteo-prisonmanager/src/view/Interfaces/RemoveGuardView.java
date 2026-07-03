package view.Interfaces;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.Implementations.RemoveGuardControllerImpl.BackListener;
import controller.Implementations.RemoveGuardControllerImpl.RemoveGuardListener;
import view.Components.PrisonManagerJFrame;
import view.Components.PrisonManagerJPanel;
import view.Interfaces.Inter.RemoveGuard;

public class RemoveGuardView extends PrisonManagerJFrame implements RemoveGuard{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4172744505801106816L;
	
	final PrisonManagerJPanel north;
	final JLabel title = new JLabel("Rimuovi guardia");
	final PrisonManagerJPanel center;
	final JLabel guardID = new JLabel("ID Guardia");
	final JTextField id = new JTextField(6);
	final PrisonManagerJPanel south;
	final JButton remove=new JButton("Rimuovi");
	final JButton back=new JButton("Back");

	int rank;
	
	/**
	 * costruttore
	 * @param rank il rank della guardia che sta visualizzando il programma
	 */
	public RemoveGuardView(int rank){
		this.rank=rank;
		this.setSize(450, 150);
		this.getContentPane().setLayout(new BorderLayout());
		north = new PrisonManagerJPanel(new FlowLayout());
		north.add(title);
		this.getContentPane().add(BorderLayout.NORTH,north);
		center = new PrisonManagerJPanel(new FlowLayout());
		center.add(guardID);
		center.add(id);
		this.getContentPane().add(BorderLayout.CENTER,center);
		this.setVisible(true);
		south = new PrisonManagerJPanel(new FlowLayout());
		south.add(remove);
		south.add(back);
		this.getContentPane().add(BorderLayout.SOUTH,south);
		this.setVisible(true);
	}
	
	public int getID(){
		if(id.getText().equals(""))
			return -1;
		return Integer.valueOf(id.getText());
	}
	
	public void displayErrorMessage(String error){
		JOptionPane.showMessageDialog(this, error);
	}

	public int getRank() {		
		return this.rank;
	}
	
	

	public void addRemoveGuardListener(RemoveGuardListener removeGuardListener){
		remove.addActionListener(removeGuardListener);
	}

	public void addBackListener(BackListener backListener){
		back.addActionListener(backListener);
	}
}
