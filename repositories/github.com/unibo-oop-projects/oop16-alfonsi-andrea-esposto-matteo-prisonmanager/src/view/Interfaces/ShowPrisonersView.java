package view.Interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controller.Implementations.ShowPrisonersControllerImpl.BackListener;
import controller.Implementations.ShowPrisonersControllerImpl.ComputeListener;
import view.Components.PrisonManagerJFrame;
import view.Components.PrisonManagerJPanel;
import view.Interfaces.Inter.ShowPrisoners;

public class ShowPrisonersView extends PrisonManagerJFrame implements ShowPrisoners{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7096756054745433188L;
	
	final PrisonManagerJPanel north;
	final JLabel from=new JLabel("Reclusi Da (mm/gg/aaaa)");
	final JLabel to=new JLabel("A (mm/gg/aaaa)");
	final JTextField from1 = new JTextField(6);
	final JTextField to1 = new JTextField(6);
	final PrisonManagerJPanel center;
    JTable table = new JTable();
	final PrisonManagerJPanel south;
	final JButton compute=new JButton("Calcola");
	final JButton back=new JButton("Indietro");
    JScrollPane js;
	int rank;
	String pattern = "MM/dd/yyyy";
    SimpleDateFormat format = new SimpleDateFormat(pattern);
	
    /**
     * costruttore
     * @param rank il rank della guardia che sta visualizzando il programma
     */
	public ShowPrisonersView(int rank){
		this.rank=rank;
		this.setSize(800, 370);
		this.getContentPane().setLayout(new BorderLayout());
		north = new PrisonManagerJPanel(new FlowLayout());
		north.add(from);
		north.add(from1);
		from1.setText("01/31/1980");
		north.add(to);
		north.add(to1);
		to1.setText("01/31/2021");
		this.getContentPane().add(BorderLayout.NORTH,north);
		center = new PrisonManagerJPanel(new FlowLayout());
		js=new JScrollPane(table);
		this.getContentPane().add(BorderLayout.CENTER,center);
		south = new PrisonManagerJPanel(new FlowLayout());
		south.add(compute);
		south.add(back);
		this.getContentPane().add(BorderLayout.SOUTH,south);
		this.setVisible(true);
	}
	
    public void createTable(JTable table){
    	center.removeAll();
    	this.table=table;
    	table.setPreferredScrollableViewportSize(new Dimension(700,200));
        js=new JScrollPane(table);
    	center.add(js);
    	js.revalidate();
    	this.setVisible(true);
    }

	public int getRank() {
		return rank;
	}
	
	public void addBackListener(BackListener backListener){
		back.addActionListener(backListener);
	}
	
	public void addComputeListener(ComputeListener computeListener){
		compute.addActionListener(computeListener);
	}
	
	public Date getFrom(){
		Date date = null;
		try {
			date = format.parse(from1.getText());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public Date getTo(){
		Date date = null;
		try {
			date = format.parse(to1.getText());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
}
