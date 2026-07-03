package view.Interfaces.Inter;

import controller.Implementations.MainControllerImpl.InsertPrisonerListener;
import controller.Implementations.MainControllerImpl.LogoutListener;
import controller.Implementations.MainControllerImpl.MoreFunctionsListener;
import controller.Implementations.MainControllerImpl.RemovePrisonerListener;
import controller.Implementations.MainControllerImpl.SupervisorListener;
import controller.Implementations.MainControllerImpl.ViewPrisonerListener;

public interface Main {

	/**
	 * aggiunge il logout listener
	 * @param logoutListener
	 */
	public void addLogoutListener(LogoutListener logoutListener);
	
	/**
	 * aggiunge l'insert prisoner listener
	 * @param insertPrisonerListener
	 */
	public void addInsertPrisonerListener(InsertPrisonerListener insertPrisonerListener);
	
	/**
	 * aggiunge il remove prisoner listener
	 * @param removePrisonerListener
	 */
	public void addRemovePrisonerListener(RemovePrisonerListener removePrisonerListener);
	
	/**
	 * aggiunge il view prisoner listener
	 * @param viewPrisonerListener
	 */
	public void addViewPrisonerListener(ViewPrisonerListener viewPrisonerListener);
	
	/**
	 * aggiunge il more functions listener
	 * @param moreFListener
	 */
	public void addMoreFunctionsListener(MoreFunctionsListener moreFListener);
	
	/**
	 * aggiunge il supervisor listener
	 * @param supervisorListener
	 */
	public void addSupervisorListener(SupervisorListener supervisorListener);
	
	/**
	 * restituisce il rank
	 * @return il rank
	 */
	public int getRank();
}
