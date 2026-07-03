package model.Implementations;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import model.Interfaces.Movement;

/**
 * implementazione di un movimento
 */
public class MovementImpl implements Serializable, Movement {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8676396152502823263L;
	
	private String descr;
	private double amount;
	private SimpleDateFormat data;
	private Calendar c;
	private String data1;
	private char ch;
	
	/**
	 * costruttore di movimento
	 * @param descr descrizione
	 * @param amount ammontare
	 * @param ch segno
	 */
	public MovementImpl(String descr, double amount, char ch){
		this.amount=amount;
		this.descr=descr;
		this.data=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		c=Calendar.getInstance();
		data1=data.format(c.getTime());
		this.ch=ch;
	}
	@Override
	public String getDescr() {
		return this.descr;
	}

	@Override
	public void setDescr(String descr) {
		this.descr=descr;
	}

	@Override
	public double getAmount() {
		return this.amount;
	}

	@Override
	public void setAmount(double amount) {
		this.amount=amount;
	}

	@Override
	public char getChar() {
		return this.ch;
	}
	
	@Override
	public String getData1(){
		return this.data1;
	}
	
	public String toString() {
		return "Movimento [descr=" + descr + "segno"+ ch + ", amount=" + amount + ", data=" + data1 + "]";
	}
}
