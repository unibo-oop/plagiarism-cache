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

import model.Interfaces.Prisoner;
import model.Interfaces.Visitor;
import view.Interfaces.AddVisitorsView;
import view.Interfaces.MoreFunctionsView;

/**
 * controller della addVisitorsView
 */
public class AddVisitorsControllerImpl {
	
	static AddVisitorsView visitorsView;
	
	/**
	 * costruttore
	 * @param view la view
	 */
	public AddVisitorsControllerImpl(AddVisitorsView view)
	{
		AddVisitorsControllerImpl.visitorsView=view;
		visitorsView.addBackListener(new BackListener());
		visitorsView.addInsertVisitorListener(new InsertListener());
	}
	
	/**
	 * listener che fa tornare alla pagina precedente
	 */
	public class BackListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			visitorsView.dispose();
			new MoreFunctionsControllerImpl(new MoreFunctionsView(visitorsView.getRank()));
		}
		
	}
	
	/**
	 * listener che gestisce l'inserimento di visitatori
	 */
	public static class InsertListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {

			//recupero la lista dei visitatori e la salvo in una lista
			List<Visitor> visitors = null;
			try {
				visitors = getVisitors();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
			//salvo il visitatore inserito nella view
			Visitor vi = visitorsView.getVisitor();
			//controllo che non ci siano errori
			try {
				if(vi.getName().length()<2||vi.getSurname().length()<2 || !checkPrisonerID(vi))
					visitorsView.displayErrorMessage("Devi inserire un nome, un cognome e un prigioniero esistente");
				else{
					//inserisco il visitatore nella lista
					visitors.add(vi);
					visitorsView.displayErrorMessage("Visitatore inserito");
				}
				} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
				//salvo la lista aggiornata
				setVisitors(visitors);

			}
		
		/**
		 * ritorna la lista dei visitatori
		 * @return lista dei visitatori
		 * @throws IOException
		 * @throws ClassNotFoundException
		 */
		public static List<Visitor> getVisitors() throws IOException, ClassNotFoundException
		{
			File f = new File("res/Visitors.txt");
			//se il file è vuoto ritorno una lista vuota
			if(f.length()!=0){
				FileInputStream fi = new FileInputStream(f);
				ObjectInputStream oi = new ObjectInputStream(fi);
				
				List<Visitor> visit = new ArrayList<>();
				//salvo il cotenuto del file in una lista
				try{
					while(true){
						Visitor s = (Visitor) oi.readObject();
						visit.add(s);
					}
				}catch(EOFException eofe){}
				
				fi.close();
				oi.close();
				return visit;
			}
				return new ArrayList<Visitor>();
		}
		}
	
	/**
	 * controlla se l'id del prigioniero inserita è corretta
	 * @param v visitatore
	 * @return true se l'id è corretto
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public static boolean checkPrisonerID(Visitor v) throws ClassNotFoundException, IOException{
		List<Prisoner> lista = MainControllerImpl.getCurrentPrisoners();
		boolean found = false;
		//ciclo tutti i prigionieri
		for(Prisoner p : lista)
		{
			//se gli id conicidono restituisco true
			if(p.getIdPrigioniero() == v.getPrisonerID())
				{
					found=true;
					return found;
				}
			else
				continue;
		}
		return false;
	}
	
	/**
	 * salva la lista dei visitatori aggiornata
	 * @param visitors lista dei visitatori
	 */
	public static void setVisitors(List<Visitor> visitors){
		File f = new File("res/Visitors.txt");
		FileOutputStream fo = null;
		try {
			fo = new FileOutputStream(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ObjectOutputStream os = null;
		try {
			os = new ObjectOutputStream(fo);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//salvo su file la lista aggiornata di visitatori
		for(Visitor g1 : visitors){
			try {
				os.writeObject(g1);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			os.close();
			fo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}



