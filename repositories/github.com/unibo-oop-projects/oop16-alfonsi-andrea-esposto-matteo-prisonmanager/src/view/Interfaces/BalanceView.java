package view.Interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controller.Implementations.BalanceControllerImpl.BackListener;
import view.Components.PrisonManagerJFrame;
import view.Components.PrisonManagerJPanel;
import view.Interfaces.Inter.Balance;

public class BalanceView extends PrisonManagerJFrame implements Balance{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9027369697644712989L;
	
	final PrisonManagerJPanel center;
    final PrisonManagerJPanel south;
    final PrisonManagerJPanel north;
    final JButton back = new JButton("Indietro");
    final JLabel balance = new JLabel("Bilancio : ");
    JTable table = new JTable();
    
    private int rank;
    
    /**
     * costruttore 
     * @param rank il rank della guardia che sta visualizzando il programma
     */
    public BalanceView(int rank){
    	this.rank=rank;
		this.setSize(600, 400);
		
		this.getContentPane().setLayout(new BorderLayout());
		north = new PrisonManagerJPanel(new FlowLayout());
		north.add(balance);
		this.getContentPane().add(BorderLayout.NORTH,north);
		center = new PrisonManagerJPanel(new FlowLayout());
		this.getContentPane().add(BorderLayout.CENTER,center);
		south = new PrisonManagerJPanel(new FlowLayout());
		south.add(back);
		this.getContentPane().add(BorderLayout.SOUTH,south);
		this.setVisible(true);
    }

    public int getRank(){
    	return this.rank;
    }
    
    public void addBackListener(BackListener backListener){
		back.addActionListener(backListener);
	}
    
    public void setLabel(String bal){
    	balance.setText("Balance :  "+ bal);
    }
    
    public void createTable(JTable table){
    	this.table=table;
    	table.setPreferredScrollableViewportSize(new Dimension(550,260));
        JScrollPane js=new JScrollPane(table);
        js.setVisible(true);
    	center.add(js);
    }
}
