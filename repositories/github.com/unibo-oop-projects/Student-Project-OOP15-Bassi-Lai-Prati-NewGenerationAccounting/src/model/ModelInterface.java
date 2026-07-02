package model;

import java.util.LinkedList;
import java.util.Map;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;

import dataModel.DBDataModel;
import dataModel.IDataTableModel;

/**
 * interfaccia che elenca i metodi comuni alle classi AccountsModel,
 * CompanyModel,CreaFattureModel,CostumersSuppliers, MovementsModel,
 * ProductsModel,ReceivablesPayablesModel
 * 
 * @author niky
 *
 */

public interface ModelInterface {

	/**
	 * operazione di aggiunta di un nuovo oggetto al dataBase del programma
	 * 
	 * @param elem
	 *            mappa contenente le informazioni sull'elemento da aggiungere
	 * @throws InstanceNotFoundException
	 * @throws IllegalArgumentException
	 * @throws InstanceAlreadyExistsException
	 */
	public void add(Map<String, Object> elem)
			throws IllegalArgumentException, InstanceAlreadyExistsException, InstanceNotFoundException;

	/**
	 * operazione per cercare e modificare un oggetto all'interno del dataBase
	 * 
	 * @param obj,
	 *            infoDaModificare l'oggetto da modificare e le nuove
	 *            informazioni
	 * @throws InstanceAlreadyExistsException
	 * @throws InstanceNotFoundException
	 * @throws IllegalArgumentException
	 */
	public void edit(IDataTableModel obj, Map<String, Object> infoDaModificare)
			throws InstanceNotFoundException, InstanceAlreadyExistsException, IllegalArgumentException;

	/**
	 * funzione per creare la mappa da passare alla load e applicare dei filtri
	 * alla ricerca
	 * 
	 * @return la mappa con le informazioni dei filtri
	 */
	public abstract Map<String, Object> getFilterMap();

	/**
	 * funzione che crea la mappa da far compilare all'utente per aggiungere /
	 * modificare un'oggetto della lista
	 * 
	 * @param obj
	 * @return mappa con le informazioni per le funzioni
	 */
	public abstract Map<String, Object> getMap(IDataTableModel obj);

	/**
	 * operazione per restituire alla view i dati del dataBase da mostrare
	 * all'utente
	 * 
	 * @return ritorna i dati richiesti
	 * 
	 */
	public abstract LinkedList<? extends IDataTableModel> load();

	public abstract LinkedList<? extends IDataTableModel> load(Map<String, Object> mappaFiltro)
			throws InstanceNotFoundException, IllegalArgumentException;

	/**
	 * operazione per rimuovere un oggetto dal dataBase
	 * 
	 * @param elem
	 *            elemento da modificare
	 * @throws InstanceNotFoundException
	 * @throws IllegalArgumentException
	 */
	public abstract void remove(IDataTableModel elem) throws InstanceNotFoundException, IllegalArgumentException;

	/**
	 * funzione di salvataggio dei dati del database
	 * 
	 */
	public abstract DBDataModel saveDBAndClose();

}
