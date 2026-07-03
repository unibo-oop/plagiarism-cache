package controller.Implementations;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import controller.Interfaces.RemovePrisonerController;
import model.Implementations.CellImpl;
import model.Interfaces.Prisoner;
import view.Interfaces.MainView;
import view.Interfaces.RemovePrisonerView;

/**
 * controller della remove prisoner view
 */
public class RemovePrisonerControllerImpl implements RemovePrisonerController{

	static RemovePrisonerView removePrisonerView;
	
	/**
	 * costruttore
	 * @param removePrisonerView la view
	 */
	public RemovePrisonerControllerImpl(RemovePrisonerView removePrisonerView){
		RemovePrisonerControllerImpl.removePrisonerView=removePrisonerView;
		removePrisonerView.addRemoveListener(new RemoveListener());
		removePrisonerView.addBackListener(new BackListener());
	}
	
	/**
	 * listener che si occupa di rimuovare il prigioniero
	 */
	public class RemoveListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			removePrisoner();
		}
		
	
	}
	
	public void removePrisoner(){

		boolean found=false;
		List<Prisoner> currentPrisoners= new ArrayList<>();
		try {
			//salvo la lista dei prigionieri correnti
			currentPrisoners=MainControllerImpl.getCurrentPrisoners();
		} catch (ClassNotFoundException | IOException e1) {
			e1.printStackTrace();
		}
		//ciclo tutti i prigionieri
		for(Prisoner p : currentPrisoners){
			//se l'id corrisponde elimino il prigioniero
			if(p.getIdPrigioniero()==removePrisonerView.getID()){
				currentPrisoners.remove(p);
				removePrisonerView.displayErrorMessage("Prigioniero rimosso");
				//recupero la lista di celle
				List<CellImpl>list=InsertPrisonerControllerImpl.getCells();
				for(CellImpl c : list){
					if(p.getCellID()==c.getId()){
						//diminuisco il numero di prigionieri correnti nella cella del prigioniero rimosso
						c.setCurrentPrisoners(c.getCurrentPrisoners()-1);
					}
				}
				try {
					//salvo la lista aggiornata di celle
					InsertPrisonerControllerImpl.setCells(list);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				found=true;
				break;
			}
		}
		
		//salvo la lista aggiornata di prigionieri
		File f = new File("res/CurrentPrisoners.txt");
		FileOutputStream fo = null;
		try {
			fo = new FileOutputStream(f);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		ObjectOutputStream os = null;
		try {
			os = new ObjectOutputStream(fo);
			os.flush();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		for(Prisoner s : currentPrisoners){
			try {
				os.writeObject(s);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		try {
			os.close();
			fo.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if(!found){
			removePrisonerView.displayErrorMessage("Prigioniero non trovato");
		}
	}
	
	/**
	 * listener che apre la view precedente
	 */
	public class BackListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			removePrisonerView.dispose();
			new MainControllerImpl(new MainView(removePrisonerView.getRank()));
		}
		
	}
	
	
}