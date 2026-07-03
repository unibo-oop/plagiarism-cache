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
import java.util.Calendar;
import java.util.List;

import controller.Interfaces.InsertPrisonerController;
import model.Implementations.CellImpl;
import model.Implementations.PrisonerImpl;
import model.Interfaces.Prisoner;
import view.Interfaces.InsertPrisonerView;
import view.Interfaces.MainView;

/**
 * controller della view insertprisoner
 */
public class InsertPrisonerControllerImpl implements InsertPrisonerController{

	static InsertPrisonerView insertPrisonerView;
	
	/**
	 * costruttore
	 * @param insertPrisonerView la view
	 */
	public InsertPrisonerControllerImpl(InsertPrisonerView insertPrisonerView) {
		InsertPrisonerControllerImpl.insertPrisonerView=insertPrisonerView;
		insertPrisonerView.addInsertPrisonerListener(new InsertPrisonerListener());
		insertPrisonerView.addBackListener(new BackListener());
		insertPrisonerView.addAddCrimeListener(new AddCrimeListener());
		
	}
	
	public void insertPrisoner(){

		boolean correct=true;
		 List<Prisoner>prisoners=new ArrayList<>();
		 List<Prisoner>currentPrisoners=new ArrayList<>();
		 //recupero le liste di prigionieri correnti e storici
		 try {
			prisoners=MainControllerImpl.getPrisoners();
			currentPrisoners=MainControllerImpl.getCurrentPrisoners();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 	//recupero il prigioniero inserito nella view
			Prisoner p = new PrisonerImpl(insertPrisonerView.getName1(), insertPrisonerView.getSurname1(), insertPrisonerView.getBirth1(), insertPrisonerView.getPrisonerID1(), insertPrisonerView.getStart1(), insertPrisonerView.getEnd1(),insertPrisonerView.getList(),insertPrisonerView.getCellID());
			
			//controllo che non ci siano errori
			if(isSomethingEmpty(p)){
				insertPrisonerView.displayErrorMessage("Completa tutti i campi");
			}
			else{
				for(Prisoner p1 : prisoners){
					if(p1.getIdPrigioniero()==p.getIdPrigioniero()){
						insertPrisonerView.displayErrorMessage("ID già usato");
						correct=false;
					}
				}
				Calendar today = Calendar.getInstance();
				today.set(Calendar.HOUR_OF_DAY, 0); //
				if(correct==true){
				if(p.getInizio().after(p.getFine())||p.getInizio().before(today.getTime())||p.getBirthDate().after(today.getTime())){
					insertPrisonerView.displayErrorMessage("Correggi le date");
				}
				else{
					//aggiungo nuovo prigioniero in entrambe le liste
					prisoners.add(p);
					currentPrisoners.add(p);
					//recupero le celle salvate
					List<CellImpl>list=getCells();
					//controllo che la cella inserita non sia piena
					for(CellImpl c : list){
						if(p.getCellID()==c.getId()||p.getCellID()<0||p.getCellID()>49){
							if(c.getCapacity()==c.getCurrentPrisoners()){
								insertPrisonerView.displayErrorMessage("Prova con un'altra cella");
								return;
								}
						//aggiungo un membro alla cella
						c.setCurrentPrisoners(c.getCurrentPrisoners()+1);

						}
					}
					try {
						//salvo la lista di celle aggiornata
						setCells(list);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					//salvo le liste di prigionieri aggiornate
					setPrisoners(prisoners, currentPrisoners);
					insertPrisonerView.displayErrorMessage("Prigioniero inserito");
					insertPrisonerView.setList(new ArrayList<String>());
				}
				}
			}
	}
	
	/**
	 * listener che si occupa di inserire prigionieri
	 */
	public class InsertPrisonerListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			insertPrisoner();
		}
		
	}
	
	/**
	 * listener che fa tornare alla pagina precedente
	 */
	public class BackListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			insertPrisonerView.dispose();
			new MainControllerImpl(new MainView(insertPrisonerView.getRank()));
		}
		
	}
	
	/**
	 * listener che aggiunge un crimine alla lista dei crimini del prigioniero
	 */
	public class AddCrimeListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			//recupero i crimini inseriti nella view
			List<String>list=insertPrisonerView.getList();
			//controllo che il crimine inserito non fosse gia presente
			if(list.contains(insertPrisonerView.getCombo())){
				insertPrisonerView.displayErrorMessage("Crimine già inserito");
			}
			else{
				//inserisco il crimine
				list.add(insertPrisonerView.getCombo());
				insertPrisonerView.setList(list);
			}
		}
		
	}
	
	public boolean isSomethingEmpty(Prisoner p){
		if(p.getName().isEmpty()||p.getSurname().isEmpty()||p.getCrimini().size()==1){
			return true;
		}
		return false;
	}
	
	/**
	 * ritorna la situazione delle celle in una lista
	 * @return lista con le celle
	 */
	public static List<CellImpl> getCells(){
		File f = new File("res/Celle.txt");
		List<CellImpl>list=new ArrayList<>();
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
		try{
			while(true){
				//salvo la situazione delle celle in una lista
				CellImpl s = (CellImpl) is.readObject();
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
	 * salva la lista di celle aggiornata
	 * @param list lista di celle
	 * @throws IOException
	 */
	public static void setCells(List<CellImpl> list) throws IOException{
		File f = new File("res/Celle.txt");
		FileOutputStream fo = new FileOutputStream(f);
		ObjectOutputStream os = new ObjectOutputStream(fo);
		//cancella il vecchio contenuto del file
		os.flush();
		fo.flush();
		//scrive ogni cella sul file
		for(CellImpl c : list){
			os.writeObject(c);
		}
		os.close();
	}
	
	/**
	 * salva le liste dei prigionieri
	 * @param prisoners prigionieri di sempre
	 * @param currentPrisoners prigionieri correnti
	 */
	public static void setPrisoners(List<Prisoner>prisoners,List<Prisoner>currentPrisoners){

		File f = new File("res/Prisoners.txt");
		File f2 = new File("res/CurrentPrisoners.txt");
		FileOutputStream fo = null;
		FileOutputStream fo2 = null;
		try {
			fo = new FileOutputStream(f);
			fo2 = new FileOutputStream(f2);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ObjectOutputStream os = null;
		ObjectOutputStream os2 = null;
		try {
			os = new ObjectOutputStream(fo);
			os2 = new ObjectOutputStream(fo2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//salva il contenuto delle liste sui file
		for(Prisoner s : prisoners){
			try {
				os.writeObject(s);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		for(Prisoner s : currentPrisoners){
			try {
				os2.writeObject(s);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			os.close();
			os2.close();
			fo.close();
			fo2.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
