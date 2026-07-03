package controller.Implementations;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import controller.Interfaces.ViewPrisonerController;
import model.Interfaces.Prisoner;
import view.Interfaces.MainView;
import view.Interfaces.ViewPrisonerView;

/**
 * controller della view prisoner view
 */
public class ViewPrisonerControllerImpl implements ViewPrisonerController{

	static ViewPrisonerView viewPrisonerView;
	
	/**
	 * costruttore
	 * @param viewPrisonerView la view
	 */
	public ViewPrisonerControllerImpl(ViewPrisonerView viewPrisonerView){
		ViewPrisonerControllerImpl.viewPrisonerView=viewPrisonerView;
		viewPrisonerView.addViewListener(new ViewProfileListener());
		viewPrisonerView.addBackListener(new BackListener());
	}
	
	/**
	 * listener che si occupa di far mostrare il prigioniero
	 */
	public class ViewProfileListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			showPrisoner();
		}
		
	}

	public void showPrisoner(){
		boolean found=false;
		if(String.valueOf(viewPrisonerView.getID()).isEmpty()){
			viewPrisonerView.displayErrorMessage("Devi inserire un ID");
		}
		List<Prisoner>prisoners=new ArrayList<>();
		 try {
			prisoners=MainControllerImpl.getPrisoners();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		 //ciclo tutti i prigionieri
		 for(Prisoner p : prisoners){
			 if(p.getIdPrigioniero()==viewPrisonerView.getID()){
				 //quando l'id corrisponde stampo i dati del prigioniero
				 viewPrisonerView.setProfile(p.getName(), p.getSurname(), p.getBirthDate().toString(), p.getInizio().toString(), p.getFine().toString());
				 viewPrisonerView.setTextArea(p.getCrimini());
				 found=true;
			 }
		 }
		 if(!found){
				viewPrisonerView.displayErrorMessage("Prigioniero non trovato");
		 }
	}
	
	/**
	 * listener che apre la view precedente
	 */
	public static class BackListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			viewPrisonerView.dispose();
			new MainControllerImpl(new MainView(viewPrisonerView.getRank()));
		}
		
	}
}
