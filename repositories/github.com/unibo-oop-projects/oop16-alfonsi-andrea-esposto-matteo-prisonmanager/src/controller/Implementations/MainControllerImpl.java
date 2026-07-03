package controller.Implementations;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import model.Interfaces.Prisoner;
import view.Interfaces.InsertPrisonerView;
import view.Interfaces.LoginView;
import view.Interfaces.MainView;
import view.Interfaces.MoreFunctionsView;
import view.Interfaces.RemovePrisonerView;
import view.Interfaces.SupervisorFunctionsView;
import view.Interfaces.ViewPrisonerView;

/**
 * controller dela main view
 */
public class MainControllerImpl {

	MainView mainView;
	
	/**
	 * costruttore
	 * @param mainView la view
	 */
	public MainControllerImpl(MainView mainView){
		this.mainView=mainView;
		mainView.addLogoutListener(new LogoutListener());
		mainView.addInsertPrisonerListener(new InsertPrisonerListener());
		mainView.addRemovePrisonerListener(new RemovePrisonerListener());
		mainView.addViewPrisonerListener(new ViewPrisonerListener());
		mainView.addMoreFunctionsListener(new MoreFunctionsListener());
		mainView.addSupervisorListener(new SupervisorListener());
	}
		
	/**
	 * listener che fa tornare alla login view
	 */
	public class LogoutListener implements ActionListener{
			
		@Override
		public void actionPerformed(ActionEvent arg0) {
			mainView.dispose();
			new LoginControllerImpl(new LoginView());
			 
		}
	}
	
	/**
	 * listener che fa andare alla insert prisoner view
	 */
	public class InsertPrisonerListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			mainView.dispose();
			new InsertPrisonerControllerImpl(new InsertPrisonerView(mainView.getRank()));
		}
	}
	
	/**
	 * listener che fa andare alla remove prisoner view
	 */
	public class RemovePrisonerListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			 mainView.dispose();
			 new RemovePrisonerControllerImpl(new RemovePrisonerView(mainView.getRank()));
		}
	}
	
	/**
	 * listener che fa andare alla view prisoner view
	 */
	public class ViewPrisonerListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			 mainView.dispose();
			 new ViewPrisonerControllerImpl(new ViewPrisonerView(mainView.getRank()));
		}
	}
	
	/**
	 * metodo che ritorna la lista di prigionieri salvata
	 * @return lista di prigionieri 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static List<Prisoner> getPrisoners() throws IOException, ClassNotFoundException{
		File f = new File("res/Prisoners.txt");
		List<Prisoner> prisoners = new ArrayList<>();
		if(f.length()!=0){
		FileInputStream fi = new FileInputStream(f);
		ObjectInputStream oi = new ObjectInputStream(fi);
		
		
		try{
			while(true){
				Prisoner s = (Prisoner) oi.readObject();
				prisoners.add(s);
			}
		}catch(EOFException eofe){}
		
		fi.close();
		oi.close();
		}
		return prisoners;
	}
	
	/**
	 * metodo che ritorna la lista i prigionieri correnti
	 * @return lista di prigionieri correnti
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static List<Prisoner> getCurrentPrisoners() throws IOException, ClassNotFoundException{
		File f = new File("res/CurrentPrisoners.txt");
		List<Prisoner> prisoners = new ArrayList<>();
		if(f.length()!=0){
		FileInputStream fi = new FileInputStream(f);
		ObjectInputStream oi = new ObjectInputStream(fi);
			
		try{
			while(true){
				Prisoner s = (Prisoner) oi.readObject();
				prisoners.add(s);
			}
		}catch(EOFException eofe){}
		
		fi.close();
		oi.close();
		}
		return prisoners;
	}
	
	/**
	 * listener che fa andare alla more functions view
	 */
	public class MoreFunctionsListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			 mainView.dispose();
			 new MoreFunctionsControllerImpl(new MoreFunctionsView(mainView.getRank()));
		}
	}
		
	/**
	 * listener che fa andare alla supervisoner functions view
	 */
	public class SupervisorListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			 mainView.dispose();
			 new SupervisorControllerImpl(new SupervisorFunctionsView(mainView.getRank()));
		}
	}

}
