package controller;

import java.util.Map;

import model.Model;
import model.SubscriptionImpl;
import model.SubscriptionModel;
import view.AddSubscriptionPanel;
import view.observer.AddSubscriptionObserver;

/**
 * @author erik_
 *
 */
public class InsertSubscriptionController implements AddSubscriptionObserver {

	private Model model;
	private AddSubscriptionPanel view;
	private SubscriptionModel subscription;

	public InsertSubscriptionController(Model model) {
		this.model = model;
	}

	public void setView(AddSubscriptionPanel sp) {
		this.view = sp;
		this.view.attachObserver(this);
	}

	@Override
	public void addNewSubcriptionClicked(String name, String surname) {
		subscription = new SubscriptionImpl(name, surname, "Bronzo", 0);
		model.subscriptions().addSubscription(subscription);
		this.view.setAllSubscriptions();
		this.view.displayMessage("Abbonamento aggiunto con successo");
	}

	@Override
	public Map<Integer, SubscriptionModel> getAllSubscriptions() {
		Map<Integer, SubscriptionModel> subscriptions = model.subscriptions().getSubscriptions();
		return subscriptions;
	}

}
