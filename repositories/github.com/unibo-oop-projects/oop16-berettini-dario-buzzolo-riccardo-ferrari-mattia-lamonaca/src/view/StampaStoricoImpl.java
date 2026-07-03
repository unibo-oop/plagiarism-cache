package view;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import controller.FightController;
import controller.FormController;
import model.Athlete;
import model.Match;

public class StampaStoricoImpl extends JFrame implements StampaStorico{

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private FormController formController;
	@SuppressWarnings("unused")
	private FightController fightController;
	private JPanel main = new JPanel();
	private JLabel titleForm = new JLabel("Storico Forme");
	private JLabel titleFight = new JLabel("Storico Combattimenti");

	public StampaStoricoImpl(){
		
		super("Storico");
		this.setVisible(true);
		this.setResizable(false);
		this.setBounds(100, 100, 900, 700);
		main.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(main);
		main.setLayout(null);
		
	}
	public void StampaStoricoForma(ArrayList<Athlete> listaAForma){
		
		titleForm.setForeground(new Color(0, 0, 0));
		titleForm.setFont(new Font("Arial", Font.BOLD, 25));
		titleForm.setBounds(350, 15 , 400, 40);
		main.add(titleForm);
		
		Object [][] athletes = new Object[listaAForma.size()][3];
		Object [] headers = new Object[]{"Nome","Cognome","Voto"};
		int index=0;
		for(Athlete atleta : listaAForma){
				
				String votoForma = String.format("%.1f",atleta.getVotoForma());
				athletes[index][0] = atleta.getName();
				athletes[index][1] = atleta.getSurname();
				athletes[index][2] = votoForma;				
				index++;
		}
			
		createTable(athletes, headers);
	}
	public void createTable(Object[][] data, Object[] headers){

		DefaultTableModel tableModel;
		JTable historicalTable;
		JScrollPane historicalPane;
		
		tableModel = new DefaultTableModel(data,
				headers){	

			private static final long serialVersionUID = 2205643621514873449L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		
		historicalTable = new JTable(tableModel);
		historicalPane = new JScrollPane(historicalTable);
		historicalTable.setFont(new Font("Arial", 0, 15));
		JTableHeader header = historicalTable.getTableHeader();
		header.setFont(new Font("Arial", Font.BOLD, 20));
		historicalPane.setBounds(0, 70 , 895, 595);
		main.add(historicalPane);
	}
	
	public void addObserverForm(FormController controller) {
		this.formController = controller;
	}
	
	public void addObserverFight(FightController controller) {
		this.fightController = controller;
	}
	@Override
	public void StampaStoricoMatch(ArrayList<Match> listaMatch) {
		titleFight.setForeground(new Color(0, 0, 0));
		titleFight.setFont(new Font("Arial", Font.BOLD, 25));
		titleFight.setBounds(310, 15 , 400, 40);
		main.add(titleFight);
		
		Object [][] matches = new Object[listaMatch.size()][3];
		Object [] headers = new Object[]{"Atleta1","Risultato","Atleta2"};
		int index=0;
		for(Match coppia : listaMatch){
				
			matches[index][0]=coppia.getAtleta1();
			matches[index][1]=coppia.getRisultato();
			matches[index][2]=coppia.getAtleta2();
			index++;
		}
		createTable(matches,headers);
		
	}
	
}