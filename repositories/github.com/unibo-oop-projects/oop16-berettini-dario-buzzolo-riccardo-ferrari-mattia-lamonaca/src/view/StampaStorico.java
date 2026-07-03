package view;

import java.util.ArrayList;

import controller.FightController;
import controller.FormController;
import model.Athlete;
import model.Match;

public interface StampaStorico {

	public void StampaStoricoForma(ArrayList<Athlete> listaAForma);
	
	public void StampaStoricoMatch(ArrayList<Match> listaMatch);
	
	public void createTable(Object[][] data, Object[] headers);
	
	public void addObserverForm(FormController controller);
	
	public void addObserverFight(FightController controller);
}
