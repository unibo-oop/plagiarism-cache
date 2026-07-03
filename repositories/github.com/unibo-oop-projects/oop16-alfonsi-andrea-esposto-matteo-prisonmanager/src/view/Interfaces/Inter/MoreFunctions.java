package view.Interfaces.Inter;

import controller.Implementations.MoreFunctionsControllerImpl.AddMovementListener;
import controller.Implementations.MoreFunctionsControllerImpl.AddVisitorsListener;
import controller.Implementations.MoreFunctionsControllerImpl.BackListener;
import controller.Implementations.MoreFunctionsControllerImpl.BalanceListener;
import controller.Implementations.MoreFunctionsControllerImpl.Chart1Listener;
import controller.Implementations.MoreFunctionsControllerImpl.Chart2Listener;
import controller.Implementations.MoreFunctionsControllerImpl.ViewCellsListener;
import controller.Implementations.MoreFunctionsControllerImpl.ViewVisitorsListener;

public interface MoreFunctions {

	/**
	 * ritorna il rank
	 * @return il rank
	 */
	public int getRank();
	
	/**
	 * aggiunge il back listener
	 * @param backListener
	 */
	public void addBackListener(BackListener backListener);
	
	/**
	 * aggiunge l'add movement listener
	 * @param addMovementListener
	 */
	public void addAddMovementListener(AddMovementListener addMovementListener);
	
	/**
	 * aggiunge il balance listener
	 * @param balanceListener
	 */
	public void addBalanceListener(BalanceListener balanceListener);
	
	/**
	 * aggiunge l'add chart 1 listener
	 * @param chart1Listener
	 */
	public void addChart1Listener(Chart1Listener chart1Listener);

	/**
	 * aggiunge l'add chart 2 listener
	 * @param chart2Listener
	 */
	public void addChart2Listener(Chart2Listener chart2Listener);
	
	/**
	 * aggiunge l'add visitors listener
	 * @param addVisitorsListener
	 */
	public void addAddVisitorsListener(AddVisitorsListener addVisitorsListener);
	
	/**
	 * aggiunge l'add view visitors listener
	 * @param viewVisitorsListener
	 */
	public void addViewVisitorsListener(ViewVisitorsListener viewVisitorsListener);
	
	/**
	 * aggiunge il view cells listener
	 * @param viewCellsListener
	 */
	public void addViewCellsListener(ViewCellsListener viewCellsListener);
}
