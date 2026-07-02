package controller.creaFattura;

import dataModel.Customers_Suppliers;

public interface ICreaFatturaController {

	void chiusura();

	/**
	 * crea la fattura
	 * 
	 * @param item
	 *            il cliente selezionato
	 */
	void create(Customers_Suppliers item);

	void refresh();

	void tasto0();

	void tasto1();

	void tasto2();

	void tasto3();

}