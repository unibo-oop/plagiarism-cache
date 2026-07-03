package model;
import model.enumerations.*;
import model.interfaces.*;

import java.io.Serializable;
import java.time.LocalDate;

public class SubscriptionImpl implements Subscription, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7643829568498267678L;
	private int code;
	private SubscriptionType type;
	private LocalDate signingDate;
	private LocalDate expirationDate;
	private double price;
	private LocalDate paymentDate; //da modificare

	public SubscriptionImpl(int code, SubscriptionType type, LocalDate signingDate, LocalDate expirationDate,/* double price*/ LocalDate paymentDate) {
		super();
		this.code = code;
		this.type = type;
		this.signingDate = signingDate;
		this.expirationDate = expirationDate;
		//this.price = price;
		this.paymentDate = paymentDate;
		this.price  = type.getPrice();
	}

	@Override
	public int getCode() {
		return this.code;
	}

	@Override
	public SubscriptionType getType() {
		return this.type;
	}

	@Override
	public LocalDate getSigningDate() {
		return this.signingDate;
	}

	@Override
	public LocalDate getExpirationDate() {
		return this.expirationDate;
	}
	
	@Override
	public void setPaymentDate(LocalDate date) {
		this.paymentDate = date;
	}
	
	@Override
	public LocalDate getPaymentDate() {
		return this.paymentDate;
	}
	
	@Override
	public double getPrice() {
		return this.price;
	} 

	@Override
	public boolean isActive() {
		final LocalDate date = LocalDate.now();
		if(date.compareTo(this.expirationDate) < 0) {
			return true;
		}
		return false;
	}
	
	public static class SubscriptionBuilder {
		private int code;
		private SubscriptionType type;
		private LocalDate signingDate;
		private LocalDate expirationDate;
		//private double price;
		private LocalDate paymentDate; //da modificare
		
		public SubscriptionBuilder code(int code) {
			this.code = code;
			return this;
		}
		
		public SubscriptionBuilder type(SubscriptionType type) {
			this.type = type;
			return this;
		}
		
		public SubscriptionBuilder signingDate(LocalDate signingDate) {
			this.signingDate = signingDate;
			return this;
		}
		
		public SubscriptionBuilder expirationDate(LocalDate expirationDate) {
			this.expirationDate = expirationDate;
			return this;
		}
		
		/*public SubscriptionBuilder price(double price) {
			this.price = type.getPrice();
			return this;
		}*/
		
		public SubscriptionBuilder paymentDate(LocalDate paymentDate) {
			this.paymentDate = paymentDate;
			return this;
		}
		
		public SubscriptionImpl build() {
			return new SubscriptionImpl(this.code, this.type, this.signingDate, this.expirationDate, /*this.price,*/ this.paymentDate);
		}
	}
}
