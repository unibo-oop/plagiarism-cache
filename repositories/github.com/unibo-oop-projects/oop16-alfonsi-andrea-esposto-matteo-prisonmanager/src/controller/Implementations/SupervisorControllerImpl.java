package controller.Implementations;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.Interfaces.InsertGuardView;
import view.Interfaces.MainView;
import view.Interfaces.RemoveGuardView;
import view.Interfaces.ShowPrisonersView;
import view.Interfaces.SupervisorFunctionsView;
import view.Interfaces.ViewGuardView;

/**
 * controller che gestisce la supervisor functions view
 */
public class SupervisorControllerImpl {

	static SupervisorFunctionsView supervisorFunctionsView;
	
	/**
	 * costruttore 
	 * @param supervisorFunctionsView la view
	 */
	public SupervisorControllerImpl(SupervisorFunctionsView supervisorFunctionsView){
		SupervisorControllerImpl.supervisorFunctionsView=supervisorFunctionsView;
		supervisorFunctionsView.addBackListener(new BackListener());
		supervisorFunctionsView.addShowPrisonersListener(new ShowPrisonersListener());
		supervisorFunctionsView.addInsertGuardListener(new InsertGuardListener());
		supervisorFunctionsView.addRemoveGuardListener(new RemoveGuardListener());
		supervisorFunctionsView.addviewGuardListener(new ViewGuardListener());
	}
	
	/**
	 * listener che apre la view precedente
	 */
	public static class BackListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			supervisorFunctionsView.dispose();
			new MainControllerImpl(new MainView(supervisorFunctionsView.getRank()));
		}
		
	}
	
	/**
	 * listener che apre la insert guard view
	 */
	public static class InsertGuardListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			supervisorFunctionsView.dispose();
			new InsertGuardControllerImpl(new InsertGuardView(supervisorFunctionsView.getRank()));
		}
		
	}
	
	/**
	 * listener che apre la remove guad view
	 */
	public static class RemoveGuardListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			supervisorFunctionsView.dispose();
			new RemoveGuardControllerImpl(new RemoveGuardView(supervisorFunctionsView.getRank()));
		}
		
	}
	
	/**
	 * listener che apre la show prisoners view
	 */
	public static class ShowPrisonersListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			supervisorFunctionsView.dispose();
			new ShowPrisonersControllerImpl(new ShowPrisonersView(supervisorFunctionsView.getRank()));
		}
		
	}
	
	/**
	 * listener che apre la view guard view
	 */
	public static class ViewGuardListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			supervisorFunctionsView.dispose();
			new ViewGuardControllerImpl(new ViewGuardView(supervisorFunctionsView.getRank()));
		}
		
	}
}
