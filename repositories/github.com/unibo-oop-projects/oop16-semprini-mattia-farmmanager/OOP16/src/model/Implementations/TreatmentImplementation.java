package model.Implementations;

import java.io.Serializable;
import java.time.Duration;
import java.util.Calendar;
import java.util.GregorianCalendar;
import model.Interfaces.PhytosanitaryModel;
import model.Interfaces.TreatmentModel;

public class TreatmentImplementation implements TreatmentModel, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5154463455972010283L;
	private PhytosanitaryModel phytosanitary;
	private Calendar date;
	private Calendar expire;
	private String descritpion;

	public TreatmentImplementation(PhytosanitaryModel phytosanitary, Calendar date, String description) {
		this.phytosanitary = phytosanitary;
		this.date = date;
		this.descritpion=description;
		this.calculateExpire();
	}


	@Override
	public void setPhytosanitary(PhytosanitaryModel phytosanitary) {
		this.phytosanitary = phytosanitary;
	}

	@Override
	public void setDate(Calendar date) {
		this.date = date;
		this.calculateExpire();
	}

	@Override
	public void setDescription(String description) {
		this.descritpion=description;		
	}


	@Override
	public PhytosanitaryModel getPhytosanitary() {
		return this.phytosanitary;
	}

	@Override
	public String getDescription() {
		return this.descritpion;
	}

	@Override
	public Calendar getDate() {
		return this.date;
	}

	@Override
	public boolean isReady() {
		return this.getLeftDays()<=0?true:false;
	}

	@Override
	public int getLeftDays() {
		int i = 0;
		Long result = Duration.between(new GregorianCalendar().toInstant(), expire.toInstant()).toDays();
		return result.intValue() < i?0:result.intValue();
	}
	
	private void calculateExpire(){
		this.expire = new GregorianCalendar(this.date.get(Calendar.YEAR), this.date.get(Calendar.MONTH), this.date.get(Calendar.DAY_OF_MONTH));
		this.expire.add(Calendar.DAY_OF_MONTH, this.phytosanitary.getExpire());
	}

}
