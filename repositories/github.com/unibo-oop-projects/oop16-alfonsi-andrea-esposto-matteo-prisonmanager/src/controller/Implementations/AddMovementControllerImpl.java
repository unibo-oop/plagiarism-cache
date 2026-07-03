package controller.Implementations;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import model.Implementations.MovementImpl;
import view.Interfaces.AddMovementView;
import view.Interfaces.MoreFunctionsView;

/**
 * controller che gestisce la addMovementView
 */
public class AddMovementControllerImpl {

	static AddMovementView addMovementView;
	
	/**
	 * costruttore 
	 * @param addMovementView la view
	 */
	public AddMovementControllerImpl(AddMovementView addMovementView){
		AddMovementControllerImpl.addMovementView=addMovementView;
		AddMovementControllerImpl.addMovementView.addBackListener(new BackListener());
		AddMovementControllerImpl.addMovementView.addInsertListener(new InsertListener());
	}
	
	/** torna alla view precedente */
	public class BackListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			addMovementView.dispose();
			new MoreFunctionsControllerImpl(new MoreFunctionsView(addMovementView.getRank()));
		}
		
	}
	
	/**
	 * listener che inserisce un movimento
	 */
	public static class InsertListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			//prendo il movimento dalla view
			MovementImpl m = new MovementImpl(addMovementView.getDesc(),addMovementView.getValue(),addMovementView.getSymbol().charAt(0));
			//se l'amount è inferiore a zero stampo l'errore
			if(m.getAmount()<=0){
				addMovementView.displayErrorMessage("Input invalido");
				return;
			}
			// recupero tutti i movimenti del passato
			List<MovementImpl> movements = getMovements();
			//aggiungo il nuovo movimento
			movements.add(m);
			// salvo la lista aggiornata
			try {
				setMovements(movements);
			} catch (IOException e) {
				e.printStackTrace();
			}
			addMovementView.displayErrorMessage("Movimento inserito");
		}
		
		/**
		 * ritorna la lista dei movimenti di bilancio
		 * @return lista dei movimenti
		 */
		public static List<MovementImpl> getMovements(){
			//recupero il file dove sono salvati i movimenti
			File f = new File("res/AllMovements.txt");
			//se è vuoto restituisco una lista vuota
			if(f.length()==0){
				List<MovementImpl>list=new ArrayList<>();
				return list;
			}
			//altrimenti leggo il contenuto
			FileInputStream fi = null;
			try {
				fi = new FileInputStream(f);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			ObjectInputStream is = null;
			try {
				is = new ObjectInputStream(fi);
			} catch (IOException e) {
				e.printStackTrace();
			}
			List<MovementImpl> list = new ArrayList<>();
			try{
				while(true){
					MovementImpl s = (MovementImpl) is.readObject();
					list.add(s);
				}
			}catch(EOFException eofe){} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			//ritorno la lista
			return list;
		}
		
		/**
		 * salva la lista aggiornata di movimenti
		 * @param list lista dei movimenti
		 * @throws IOException
		 */
		public void setMovements(List<MovementImpl> list) throws IOException{
			//recupero il file contenente i movimenti
			File f = new File("res/AllMovements.txt");
			FileOutputStream fo = new FileOutputStream(f);
			ObjectOutputStream os = new ObjectOutputStream(fo);
			//elimino il vecchio contenuto
			os.flush();
			fo.flush();
			//salvo nel file la lista aggiornata
			for(MovementImpl s : list){
				os.writeObject(s);
			}
			os.close();
		}
	}
	
}
	
	
	

