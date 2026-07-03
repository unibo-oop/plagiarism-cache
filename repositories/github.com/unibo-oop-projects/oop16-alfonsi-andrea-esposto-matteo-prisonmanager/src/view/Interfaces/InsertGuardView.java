package view.Interfaces;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import controller.Implementations.InsertGuardControllerImpl.BackListener;
import controller.Implementations.InsertGuardControllerImpl.InsertListener;
import model.Implementations.GuardImpl;
import model.Interfaces.Guard;
import view.Components.PrisonManagerJFrame;
import view.Components.PrisonManagerJPanel;
import view.Components.SpringUtilities;
import view.Interfaces.Inter.InsertGuard;

public class InsertGuardView extends PrisonManagerJFrame implements InsertGuard{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6919464397187101572L;
	
	final PrisonManagerJPanel south;
	final JButton insert = new JButton("Inserisci");
	final PrisonManagerJPanel north;
	final JLabel guardID = new JLabel("ID Guardia");
	final JTextField guardID1 = new JTextField(6);
	final JLabel name = new JLabel("Nome");
	final JTextField name1 = new JTextField(6);
	final JLabel surname = new JLabel("Cognome");
	final JTextField surname1 = new JTextField(6);
	final JLabel birthDate = new JLabel("Data di nascita (mm/gg/aaaa)");
	final JTextField birthDate1 = new JTextField(6);
	final PrisonManagerJPanel center;
	final JLabel guardRank = new JLabel("Grado (1-2-3)");
	final JTextField guardRank1 = new JTextField(8);
	final JLabel telephoneNum = new JLabel("Numero di telefono");
	final JTextField telephoneNum1 = new JTextField(8);
	final JLabel password = new JLabel("Password(6 caratt. min)");
	final JTextField password1 = new JTextField(8);
	final JButton back = new JButton("Indietro");
	final JLabel title = new JLabel("Inserisci una guardia");
	String pattern = "MM/dd/yyyy";
    SimpleDateFormat format = new SimpleDateFormat(pattern);
    Date date;
	int rank;
	
	/**
	 * costruttore
	 * @param rank il rank della guardia che sta visualizzando il programma
	 */
	public InsertGuardView(int rank){
		
		this.rank=rank;
		this.setSize(450, 400);
		this.getContentPane().setLayout(new BorderLayout());
		north = new PrisonManagerJPanel(new FlowLayout());
		north.add(title);
		this.getContentPane().add(BorderLayout.NORTH,north);
		center = new PrisonManagerJPanel(new SpringLayout());
		center.add(guardID);
		center.add(guardID1);
		guardID1.setText("0");
		center.add(name);
		center.add(name1);
		center.add(surname);
		center.add(surname1);	
		center.add(birthDate);
		center.add(birthDate1);
		birthDate1.setText("01/01/1980");
		center.add(telephoneNum);
		center.add(telephoneNum1);
		center.add(guardRank);
		center.add(guardRank1);
		guardRank1.setText("0");
		center.add(password);
		center.add(password1);
		SpringUtilities.makeCompactGrid(center,
                7, 2, //rows, cols
                6, 6,        //initX, initY
                6, 6);       //xPad, yPad
		this.getContentPane().add(BorderLayout.CENTER,center);
		south = new PrisonManagerJPanel(new FlowLayout());
		south.add(insert);
		south.add(back);
		this.getContentPane().add(BorderLayout.SOUTH,south);
		this.setVisible(true);
	}
	
	public void addBackListener(BackListener backListener){
		back.addActionListener(backListener);
	}
	
	public void displayErrorMessage(String error){
		JOptionPane.showMessageDialog(this, error);
	}
	
	public void addInsertListener(InsertListener insertListener){
		insert.addActionListener(insertListener);
	}

	public int getRank() {
		return this.rank;
	}
	
	public Guard getGuard(){
		Date birth = null;
		try {
			birth = format.parse(birthDate1.getText());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Guard g = new GuardImpl(name1.getText(),surname1.getText(),birth,Integer.valueOf(guardRank1.getText()),telephoneNum1.getText(),
				Integer.valueOf(guardID1.getText()),password1.getText());
		return g;
	}

}
