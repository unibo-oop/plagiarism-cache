package controller.Implementations;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JFrame;

import controller.Interfaces.MoreFunctionsController;
import model.Interfaces.Prisoner;
import view.Components.BarChart_AWT;
import view.Components.PieChart_AWT;
import view.Interfaces.AddMovementView;
import view.Interfaces.AddVisitorsView;
import view.Interfaces.BalanceView;
import view.Interfaces.MainView;
import view.Interfaces.MoreFunctionsView;
import view.Interfaces.ViewCellsView;
import view.Interfaces.ViewVisitorsView;

/**
 * controller della more functions view
 */
public class MoreFunctionsControllerImpl implements MoreFunctionsController{

	 MoreFunctionsView moreFunctionsView;
	
	 /**
	  * costruttore
	  * @param moreFunctionsView la view
	  */
	public MoreFunctionsControllerImpl(MoreFunctionsView moreFunctionsView){
		this.moreFunctionsView=moreFunctionsView;
		moreFunctionsView.addBackListener(new BackListener());
		moreFunctionsView.addAddMovementListener(new AddMovementListener());
		moreFunctionsView.addBalanceListener(new BalanceListener());
		moreFunctionsView.addChart1Listener(new Chart1Listener());
		moreFunctionsView.addChart2Listener(new Chart2Listener());
		moreFunctionsView.addAddVisitorsListener(new AddVisitorsListener());
		moreFunctionsView.addViewVisitorsListener(new ViewVisitorsListener());
		moreFunctionsView.addViewCellsListener(new ViewCellsListener());
	}
	
	/**
	 * listener che fa tornare alla pagina precedente
	 * @author Utente
	 *
	 */
	public class BackListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			moreFunctionsView.dispose();
			new MainControllerImpl(new MainView(moreFunctionsView.getRank()));
		}
		
	}
	
	/**
	 * listener che apre la add movement view
	 */
	public class AddMovementListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			moreFunctionsView.dispose();
			new AddMovementControllerImpl(new AddMovementView(moreFunctionsView.getRank()));
		}
		
	}
	
	/**
	 * listener che apre la balance view
	 */
	public class BalanceListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			moreFunctionsView.dispose();
			new BalanceControllerImpl(new BalanceView(moreFunctionsView.getRank()));
		}
		
	}
	
	public void createChart1(){

		//creo una mappa contenente gli anni e il numero dei prigioneri in quell anno
		Map<Integer,Integer>map=new TreeMap<>();
		//anno in cui ha aperto la prigione
		final int OPENING=2017;
		//salvo la lista di prigionieri
		List<Prisoner> list = null;
		try {
			list = MainControllerImpl.getPrisoners();
		} catch (ClassNotFoundException | IOException e1) {
			e1.printStackTrace();
		}
		//recupero l'anno massimo in cui un prigioniero è imprigionato
		int max=getMax(list);
		//ciclo tutti gli anni e modifico il numero di prigionieri
		for(int i=OPENING;i<=max;i++){
			int num = 0;;
			for(Prisoner p:list){
				Calendar calendar = Calendar.getInstance();
				Calendar calendar2 = Calendar.getInstance();
				calendar.setTime(p.getInizio());
				calendar2.setTime(p.getFine());
				if(calendar.get(Calendar.YEAR)<=i&&calendar2.get(Calendar.YEAR)>=i){
					num++;
				}		
			}
			map.put(i, num);
		}
		//creo il grafico
		BarChart_AWT chart = new BarChart_AWT(map,"Numero prigionieri per anno","Numero prigionieri per anno");
		chart.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}
	
	public int getMax(List<Prisoner>list){
		int max=0;
		for(Prisoner p: list){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(p.getFine());
			if(calendar.get(Calendar.YEAR)>max){
				max=calendar.get(Calendar.YEAR);
			}
		}
		return max;
	}
	
	/**
	 * listener che si occupa di aprire il primo grafico 
	 */
	public class Chart1Listener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			createChart1();
		}
	}
	
	public void createChart2(){

		//salvo le categorie di reati in un vettore che poi converto in lista
		String[] crimes = {"Reati contro gli animali","Reati associativi","Blasfemia e sacrilegio","Reati economici e finanziari","Falsa testimonianza","Reati militari","Reati contro il patrimonio","Reati contro la persona","Reati nell' ordinamento italiano","Reati tributari","Traffico di droga","Casi di truffe"};
		ArrayList<String>crimesList = new ArrayList<>(Arrays.asList(crimes));
		List<Prisoner> list = null;
		//recupero i prigionieri correnti in una lista
		try {
			list = MainControllerImpl.getCurrentPrisoners();
		} catch (ClassNotFoundException | IOException e1) {
			e1.printStackTrace();
		}
		
		//creo una mappa in cui inserire come chiave il reato e come valore il numero di prigionieri che l'anno commesso
		Map<String,Integer>map=new HashMap<>();
		for(String s : crimesList){
			map.put(s, 0);
		}
		for(Prisoner p: list){
			for(String s : p.getCrimini()){
				if(crimesList.contains(s)){
					map.put(s, map.get(s)+1);
				}
			}
		}
		//creo il grafico
		PieChart_AWT pie = new PieChart_AWT("Percentuale crimini commessi dai reclusi attuali",map);
		pie.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	/**
	 * listener che si occupa di lanciare il secondo grafico
	 */
	public class Chart2Listener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			createChart2();
		}
		
	}
	
	/**
	 * listener che apre l'add visitors view
	 */
	public class AddVisitorsListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			moreFunctionsView.dispose();
			new AddVisitorsControllerImpl(new AddVisitorsView(moreFunctionsView.getRank()));
		}
		
	}
	
	/**
	 * listener che apre la view visitors view
	 */
	public class ViewVisitorsListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			moreFunctionsView.dispose();
			new ViewVisitorsControllerImpl(new ViewVisitorsView(moreFunctionsView.getRank()));
		}
		
	}
	
	/**
	 * listener che apre la view cells view
	 */
	public class ViewCellsListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			moreFunctionsView.dispose();
			new ViewCellsControllerImpl(new ViewCellsView(moreFunctionsView.getRank()));
		}
		
	}
}
