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

import controller.Implementations.AddVisitorsControllerImpl.InsertListener;
import controller.Implementations.AddVisitorsControllerImpl.BackListener;
import model.Implementations.VisitorImpl;
import view.Components.PrisonManagerJFrame;
import view.Components.PrisonManagerJPanel;
import view.Components.SpringUtilities;
import view.Interfaces.Inter.AddVisitors;

/**
 * view in cui si aggiungono visitatori
 */
public class AddVisitorsView extends PrisonManagerJFrame implements AddVisitors{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8964073612262207713L;
	int rank;
	String pattern = "MM/dd/yyyy";
    SimpleDateFormat format = new SimpleDateFormat(pattern);
	final PrisonManagerJPanel south;
	final JButton insert = new JButton("Inserisci");
	final PrisonManagerJPanel north;
	final JLabel name = new JLabel("Nome : ");
	final JTextField name1 = new JTextField(6);
	final JLabel surname = new JLabel("Cognome :");
	final JTextField surname1 = new JTextField(6);
	final JLabel birthDate = new JLabel("Data di nascita (mm/gg/aaaa) : ");
	final JTextField birthDate1 = new JTextField(6);
	final PrisonManagerJPanel center;
	final JButton back = new JButton("Indietro");
	final JLabel title = new JLabel("Inserisci visitatore");
	final JLabel prisonerID = new JLabel("Id prigioniero incontrato  : ");
	final JTextField prisonerID1 = new JTextField(6);
	
	/**
	 * costruttore
	 * @param rank il rank della guardia che sta visualizzando il programma
	 */
	public AddVisitorsView(int rank)
	{	
		this.rank=rank;
		this.setSize(450, 400);
		this.getContentPane().setLayout(new BorderLayout());
		north = new PrisonManagerJPanel(new FlowLayout());
		north.add(title);
		this.getContentPane().add(BorderLayout.NORTH,north);
		center = new PrisonManagerJPanel(new SpringLayout());
		center.add(name);
		center.add(name1);
		center.add(surname);
		center.add(surname1);	
		center.add(birthDate);
		center.add(birthDate1);
		birthDate1.setText("01/01/2017");
		center.add(prisonerID);
		center.add(prisonerID1);
		prisonerID1.setText("0");
		SpringUtilities.makeCompactGrid(center,
                4, 2, //rows, cols
                6, 6,        //initX, initY
                6, 6);       //xPad, yPad
		this.getContentPane().add(BorderLayout.CENTER,center);
		south = new PrisonManagerJPanel(new FlowLayout());
		south.add(insert);
		south.add(back);
		this.getContentPane().add(BorderLayout.SOUTH,south);
		this.setVisible(true);
	}
	
	public VisitorImpl getVisitor(){
		Date date=null;
		try {
			date = format.parse(birthDate1.getText());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		VisitorImpl v = new VisitorImpl(name1.getText(),surname1.getText(),date,Integer.valueOf(prisonerID1.getText()));
	 return v;
	}

	public int getRank() {
		return this.rank;
	}
	public void displayErrorMessage(String error){
		JOptionPane.showMessageDialog(this, error);
	}
	
	public void addBackListener(BackListener backListener){
		back.addActionListener(backListener);
	}
	
	public void addInsertVisitorListener(InsertListener insertListener){
		insert.addActionListener(insertListener);
	}
	 
	
	

}
