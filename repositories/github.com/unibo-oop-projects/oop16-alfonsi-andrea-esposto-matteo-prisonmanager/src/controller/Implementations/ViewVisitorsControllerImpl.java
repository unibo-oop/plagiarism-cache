package controller.Implementations;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.JTable;

import controller.Interfaces.ViewVisitorsController;
import model.Interfaces.Visitor;
import view.Interfaces.MoreFunctionsView;
import view.Interfaces.ViewVisitorsView;

/**
 * controller della view visitors view
 * @author Utente
 *
 */
public class ViewVisitorsControllerImpl implements ViewVisitorsController{

	static ViewVisitorsView viewVisitorsView;
	
	/**
	 * costruttore
	 * @param viewVisitorsView la view
	 */
	public ViewVisitorsControllerImpl(ViewVisitorsView viewVisitorsView){
		ViewVisitorsControllerImpl.viewVisitorsView=viewVisitorsView;
		viewVisitorsView.addBackListener(new BackListener());
		viewVisitorsView.createTable(getTable());
	}
	
	/**
	 * listener che apre la view precedente
	 */
	public class BackListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			viewVisitorsView.dispose();
			new MoreFunctionsControllerImpl(new MoreFunctionsView(viewVisitorsView.getRank()));
		}
		
	}
	
	public JTable getTable(){
		//creo una lista in cui inserisco i visitatori
		List<Visitor>list=null;
		try {
			list=AddVisitorsControllerImpl.InsertListener.getVisitors();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
		//metto gli elementi della lista in una matrice
		String[]vet={"Nome","Cognome","Data di nascita","ID prigioniero visitato"};
		String[][]mat=new String[list.size()][vet.length];
		for(int i=0;i<list.size();i++){
			mat[i][0]=list.get(i).getName();
			mat[i][1]=list.get(i).getSurname();
			mat[i][2]=list.get(i).getBirthDate().toString();
			mat[i][3]=String.valueOf(list.get(i).getPrisonerID());
		}
		//creo la tabella passandogli la matrice
		JTable table=new JTable(mat,vet);
		return table;
	}
	
}
