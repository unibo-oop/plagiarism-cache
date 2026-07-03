package controller.Interfaces;

import java.util.List;

import model.Interfaces.Prisoner;

public interface MoreFunctionsController {

	/**
	 * crea il grafico numero uno
	 */
	public void createChart1();
	
	/**
	 * data una lista di prigionieri ritorna l'anno maggiore tra le date di fine prigionia 
	 * @param list di prigionieri
	 * @return anno di fine prigionia maggiore
	 */
	public int getMax(List<Prisoner>list);
	
	/**
	 * crea il grafico numero 2
	 */
	public void createChart2();
}
