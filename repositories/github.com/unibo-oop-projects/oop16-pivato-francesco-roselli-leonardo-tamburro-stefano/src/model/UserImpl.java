package model;
import model.enumerations.*;
import model.interfaces.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public class UserImpl implements User, Serializable {
	

	private static final long serialVersionUID = 4622707263377044690L;
	private Person person;
	private Subscription subscription;
	/* da cambiare in workOut e mettere opzionale */
	private Workout workout;
	private Map<Product,Integer> productsBought;
	
	public UserImpl() {
	}
	
	public UserImpl(Person person, Subscription subscription) {
		super();
		this.person = person;
		this.subscription = subscription;
		this.workout = new WorkoutImpl();
		this.productsBought = new HashMap<>();
	}

	@Override
	public Person getPerson() {
		return this.person;
	}

	@Override
	public Subscription getSubscription() {
		return this.subscription;
	}
	
	@Override
	public Workout getWorkout() {
		return this.workout;
	}

	@Override
	public Map<Product,Integer> getProductsBought() {
		return this.productsBought;
	}
	

	@Override
	public Status buyProduct(Product product, Integer quantity) {
		if(DataImpl.getInstance().checkAvailability(product, quantity)) {
			this.productsBought.merge(product, quantity, (x,y)-> x+y);
			DataImpl.getInstance().getBalance().sellProduct(LocalDate.now(), product, quantity);
			return Status.ALL_RIGHT;
		}
		return Status.QUANTITY_NOT_AVAILABLE;
	}

}
