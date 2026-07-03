package view.Interfaces;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SpringLayout;

import controller.Implementations.MoreFunctionsControllerImpl.AddMovementListener;
import controller.Implementations.MoreFunctionsControllerImpl.AddVisitorsListener;
import controller.Implementations.MoreFunctionsControllerImpl.BackListener;
import controller.Implementations.MoreFunctionsControllerImpl.BalanceListener;
import controller.Implementations.MoreFunctionsControllerImpl.Chart1Listener;
import controller.Implementations.MoreFunctionsControllerImpl.Chart2Listener;
import controller.Implementations.MoreFunctionsControllerImpl.ViewCellsListener;
import controller.Implementations.MoreFunctionsControllerImpl.ViewVisitorsListener;
import view.Components.PrisonManagerJFrame;
import view.Components.PrisonManagerJPanel;
import view.Components.SpringUtilities;
import view.Interfaces.Inter.MoreFunctions;

public class MoreFunctionsView extends PrisonManagerJFrame implements MoreFunctions{

	/**
	 * 
	 */
	private static final long serialVersionUID = -960406183837784254L;

	final PrisonManagerJPanel north;
	final JLabel title=new JLabel("Altre funzioni");
	final PrisonManagerJPanel center;
	final JButton addMovement=new JButton("Aggiungi un movimento");
	final JButton viewBalance=new JButton("Guarda bilancio");
	final JButton viewFirstChart=new JButton("Grafico prigionieri per anno");
	final JButton viewSecondChart=new JButton("Grafico percentuale crimini");
	final JButton addVisitors = new JButton("Aggiungi un visitatore");
	final JButton viewVisitors = new JButton("Controlla visitatori");
	final JButton viewCells = new JButton("Guarda celle");
	final PrisonManagerJPanel south;
	final JButton back=new JButton("Indietro");
	private int rank;
	
	/**
	 * costruttore
	 * @param rank il rank della guardia che sta visualizzando il programma
	 */
	public MoreFunctionsView(int rank){
		this.rank=rank;
		this.setSize(500, 250);
		this.getContentPane().setLayout(new BorderLayout());
		north = new PrisonManagerJPanel(new FlowLayout());
		north.add(title);
		this.getContentPane().add(BorderLayout.NORTH,north);
		center = new PrisonManagerJPanel(new SpringLayout());
		center.add(addMovement);
		center.add(viewBalance);
		center.add(viewFirstChart);
		center.add(viewSecondChart);
		center.add(addVisitors);
		center.add(viewVisitors);
		center.add(viewCells);
		center.add(back);
		SpringUtilities.makeCompactGrid(center,
                4, 2, //rows, cols
                6, 6,        //initX, initY
                6, 6);       //xPad, yPad
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
	
	public void addAddMovementListener(AddMovementListener addMovementListener){
		addMovement.addActionListener(addMovementListener);
	}
	
	public void addBalanceListener(BalanceListener balanceListener){
		viewBalance.addActionListener(balanceListener);
	}
	
	public void addChart1Listener(Chart1Listener chart1Listener){
		viewFirstChart.addActionListener(chart1Listener);
	}

	public void addChart2Listener(Chart2Listener chart2Listener){
		viewSecondChart.addActionListener(chart2Listener);
	}
	public void addAddVisitorsListener(AddVisitorsListener addVisitorsListener){
		addVisitors.addActionListener(addVisitorsListener);
	}
	
	public void addViewVisitorsListener(ViewVisitorsListener viewVisitorsListener){
		viewVisitors.addActionListener(viewVisitorsListener);
	}
	
	public void addViewCellsListener(ViewCellsListener viewCellsListener){
		viewCells.addActionListener(viewCellsListener);
	}
}
