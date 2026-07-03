package view.Interfaces;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;

import controller.Implementations.MainControllerImpl.InsertPrisonerListener;
import controller.Implementations.MainControllerImpl.LogoutListener;
import controller.Implementations.MainControllerImpl.MoreFunctionsListener;
import controller.Implementations.MainControllerImpl.RemovePrisonerListener;
import controller.Implementations.MainControllerImpl.SupervisorListener;
import controller.Implementations.MainControllerImpl.ViewPrisonerListener;
import view.Components.PrisonManagerJFrame;
import view.Components.PrisonManagerJPanel;
import view.Interfaces.Inter.Main;

public class MainView extends PrisonManagerJFrame implements Main{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2585136897389059255L;

	final PrisonManagerJPanel center;
	final JButton addPrisoner= new JButton("Aggiungi prigioniero");
	final JButton removePrisoner=new JButton("Rimuovi prigioniero");;
	final JButton viewPrisoner=new JButton("Vedi profilo prigioniero");
	final PrisonManagerJPanel south;
	final JButton highRankOnly = new JButton("Funzioni riservate (Grado 3)");
	final JButton moreFunctions = new JButton("Altre funzioni (grado 2)");
	final JButton logout=new JButton("Logout");
	final PrisonManagerJPanel north;
	final JLabel title=new JLabel("Prison Manager");
	private int rank;
	
	/**
	 * costruttore
	 * @param rank il rank della guardia che sta visualizzando il programma
	 */
	public MainView(int rank){
		this.rank=rank;
		this.setSize(550, 150);
		this.getContentPane().setLayout(new BorderLayout());
		center = new PrisonManagerJPanel(new FlowLayout());
		center.add(addPrisoner);
		center.add(removePrisoner);
		center.add(viewPrisoner);
		this.getContentPane().add(BorderLayout.CENTER,center);
		south=new PrisonManagerJPanel(new FlowLayout());
		south.add(moreFunctions);
		south.add(highRankOnly);
		south.add(logout);
		if(rank<2){
			moreFunctions.setEnabled(false);
		}
		if(rank<3){
			highRankOnly.setEnabled(false);
		}
		this.getContentPane().add(BorderLayout.SOUTH,south);
		north = new PrisonManagerJPanel(new FlowLayout());
		north.add(title);
		this.getContentPane().add(BorderLayout.NORTH,north);
		this.setVisible(true);
	}
	
	public void addLogoutListener(LogoutListener logoutListener){
		logout.addActionListener(logoutListener);
	}
	
	public void addInsertPrisonerListener(InsertPrisonerListener insertPrisonerListener){
		addPrisoner.addActionListener(insertPrisonerListener);
	}
	
	public void addRemovePrisonerListener(RemovePrisonerListener removePrisonerListener){
		removePrisoner.addActionListener(removePrisonerListener);
	}
	
	public void addViewPrisonerListener(ViewPrisonerListener viewPrisonerListener){
		viewPrisoner.addActionListener(viewPrisonerListener);
	}
	
	public void addMoreFunctionsListener(MoreFunctionsListener moreFListener){
		moreFunctions.addActionListener(moreFListener);
	}
	
	public void addSupervisorListener(SupervisorListener supervisorListener){
		highRankOnly.addActionListener(supervisorListener);
	}
	
	public int getRank(){
		return this.rank;
	}
}
