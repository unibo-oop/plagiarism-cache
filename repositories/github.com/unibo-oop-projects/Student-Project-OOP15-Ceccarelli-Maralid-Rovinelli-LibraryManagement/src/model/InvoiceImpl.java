package model;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * this class represented a invoice in a library . the receipt is made from
 * books,from the total,from the date and address of the library
 * 
 * @author mattia.rovinelli
 *
 */
public class InvoiceImpl implements InvoiceModel, Serializable {

	public static int CASH = 0;
	public static int CREDITCARD = 1;
	public static int BANCONMAT = 2;
	private static String[] paymentMethods = { "Carta di credito", "Contanti", "Bancomat" };

	private static final long serialVersionUID = 1L;
	private Map<BookModel, Integer> receipt;
	private double total;
	private Date date;
	private int payment;

	public InvoiceImpl(Map<BookModel, Integer> list, Date date, int payment) {
		this.receipt = list;
		this.date = date;
		this.payment = payment;
	}

	@Override
	public Map<BookModel, Integer> getReceipt() {
		return this.receipt;
	}

	@Override
	public double getTotal() {
		total = 0;
		Iterator<Entry<BookModel, Integer>> it = receipt.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<BookModel, Integer> book = (Entry<BookModel, Integer>) it.next();
			this.total += (book.getKey().getPrice() * book.getValue());
		}
		return total;
	}

	@Override
	public Date getDate() {
		return this.date;
	}

	@Override
	public String getPaymentMethod() {
		return paymentMethods[this.payment];
	}

	@Override
	public void setPaymentMethod(int payment) {
		this.payment = payment;

	}

	@Override
	public void setDate(Date date) {
		this.date = date;

	}

}
