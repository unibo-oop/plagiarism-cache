package controller.Implementations;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JTable;

import model.Interfaces.Prisoner;
import view.Components.PrisonManagerJFrame;
import view.Interfaces.ShowPrisonersView;
import view.Interfaces.SupervisorFunctionsView;

/**
 * controller che gestisce la show prisoners view
 */
public class ShowPrisonersControllerImpl extends PrisonManagerJFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2056633481557914162L;
	
	static ShowPrisonersView showPrisonersView;
	
	/**
	 * controlle 
	 * @param showPrisonersView la view
	 */
	public ShowPrisonersControllerImpl(ShowPrisonersView showPrisonersView){
		ShowPrisonersControllerImpl.showPrisonersView=showPrisonersView;
		ShowPrisonersControllerImpl.showPrisonersView.addBackListener(new BackListener());
		ShowPrisonersControllerImpl.showPrisonersView.addComputeListener(new ComputeListener());
	}

	/**
	 * listener che apre la view precedente
	 */
	public static class BackListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			showPrisonersView.dispose();
			new SupervisorControllerImpl(new SupervisorFunctionsView(showPrisonersView.getRank()));
		}
		
	}
	
	/**
	 * crea una tabella contenente i prigionieri che tra le due date prese in input erano in prigione
	 */
	public static class ComputeListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			//salvo le date inserite nella view
			Date from=showPrisonersView.getFrom();
			Date to=showPrisonersView.getTo();
			List<Prisoner>list = null;
			List<Prisoner>inclusi = new ArrayList<>();
			try {
				//recupero la lista dei prigionieri
				list=MainControllerImpl.getPrisoners();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
			for(Prisoner p : list){
				//se il prigioniero è imprigionato tra le due date lo aggiungo alla lista inclusi
				if(p.getInizio().before(to)&&p.getFine().after(from)){
					inclusi.add(p);
				}
			}
			//creo una matrice con i prigionieri inclusi
			JTable table = new JTable();
			String[]vet={"id","nome","cognome","giorno di nascita","inizio prigionia","fine prigionia"};
			String[][]mat=new String[inclusi.size()][vet.length];
			for(int i=0;i<inclusi.size();i++){
				mat[i][0]=String.valueOf(inclusi.get(i).getIdPrigioniero());
				mat[i][1]=inclusi.get(i).getName();
				mat[i][2]=inclusi.get(i).getSurname();
				mat[i][3]=inclusi.get(i).getBirthDate().toString();
				mat[i][4]=inclusi.get(i).getInizio().toString();
				mat[i][5]=inclusi.get(i).getFine().toString();
			}
			//creo la tabella passandogli i dati della matrice
			table=new JTable(mat,vet);
			showPrisonersView.createTable(table);
		}
		
	}
}
