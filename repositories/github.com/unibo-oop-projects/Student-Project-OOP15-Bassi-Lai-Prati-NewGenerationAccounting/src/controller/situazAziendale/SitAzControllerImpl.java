/**
 * 
 */
package controller.situazAziendale;

import java.awt.Dimension;

import controller.IViewObserver;
import controller.main.MainControllerImpl;
import dataEnum.Natures;
import dataModel.DBDataModel;
import model.FinancialSituationModelImpl;
import view.situazAziendale.SitAzView;

/**
 * implementazione del controller della situazione aziendale con stato
 * patrimoniale, conto economico e commento.
 * 
 * @author Pentolo
 *
 */
public class SitAzControllerImpl implements IViewObserver {

	private final FinancialSituationModelImpl model;
	private final SitAzView view;

	/**
	 * @param db
	 *            il database
	 * @param title
	 *            il titolo della finestra
	 */
	public SitAzControllerImpl(final DBDataModel db, final String title) {
		this.model = new FinancialSituationModelImpl(db);
		final float totAttiv = model.getTot(Natures.ATTIVITA);
		final float totPassiv = model.getTot(Natures.PASSIVITA);
		final float totCosti = model.getTot(Natures.COSTO);
		final float totRicavi = model.getTot(Natures.RICAVO);
		this.view = new SitAzView(title, new Dimension(700, 750), model.AnalisiFinanziaria(), model.Attivita(),
				model.Saldi_Attivita(), model.Passivita(), model.Saldi_Passivita(), model.Costi(), model.Saldi_Costi(),
				model.Ricavi(), model.Saldi_Ricavi(), totAttiv, totPassiv, totCosti, totRicavi);
		this.view.setObserver(this);
		view.start();
	}

	@Override
	public void chiusura() {
		view.close();
		new MainControllerImpl(model.saveDBAndClose());
	}

}
