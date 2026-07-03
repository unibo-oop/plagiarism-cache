package controller.Implementations;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import model.Interfaces.Guard;
import view.Interfaces.SupervisorFunctionsView;
import view.Interfaces.ViewGuardView;

/**
 * controller che gestisce la view guard view
 */
public class ViewGuardControllerImpl {

	static ViewGuardView viewGuardView;
	
	/**
	 * costruttore
	 * @param viewGuardView la view
	 */
	public ViewGuardControllerImpl(ViewGuardView viewGuardView){
		ViewGuardControllerImpl.viewGuardView=viewGuardView;
		viewGuardView.addBackListener(new BackListener());
		viewGuardView.addViewListener(new ViewGuardListener());
	}
	
	/**
	 * imposta la view in modo da mostrare la guardia
	 */
	public static class ViewGuardListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			List<Guard>list = null;
			boolean found = false;
			try {
				list=LoginControllerImpl.getGuards();
			} catch (ClassNotFoundException | IOException e1) {
				e1.printStackTrace();
			}
			viewGuards(list,found);
			
			
		}
		
	}
	
	/**
	 * mostra le caratteristiche della guardia nella gui
	 * @param list lista delle guardie
	 * @param found true se è stata trovata la guardia richiesta
	 */
	public static void viewGuards(List<Guard>list,boolean found){
		for(Guard g : list){
			if(g.getID()==viewGuardView.getID()){
				viewGuardView.setName(g.getName());
				viewGuardView.setSurname(g.getSurname());
				viewGuardView.setBirth(g.getBirthDate().toString());
				viewGuardView.setRank(String.valueOf(g.getRank()));
				viewGuardView.setTelephone(g.getTelephoneNumber());
				found=true;
			}
			
		}
		if(!found)
			viewGuardView.displayErrorMessage("Guardia non trovata");
	}
	
	/**
	 * listener che apre la view precedente
	 */
	public static class BackListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			viewGuardView.dispose();
			new SupervisorControllerImpl(new SupervisorFunctionsView(viewGuardView.getRank()));
		}
		
	}
}
