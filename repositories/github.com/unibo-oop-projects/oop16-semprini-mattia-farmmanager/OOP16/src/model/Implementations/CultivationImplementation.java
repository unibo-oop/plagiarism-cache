package model.Implementations;

import java.io.Serializable;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import model.Interfaces.CultivationModel;
import model.Interfaces.FieldModel;
import model.Interfaces.PhytosanitaryModel;
import model.Interfaces.SupplyModel;
import model.Interfaces.TreatmentModel;

public class CultivationImplementation implements CultivationModel, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6349072428177721821L;
	private Calendar startDate;
	private Calendar endDate;
	private int nOfPlants;
	private SupplyModel supply;
	private FieldModel field;
	private String annotation;
	private State state;
	private List<TreatmentModel> treatments = new ArrayList<>();

	public CultivationImplementation(Calendar startDate, int nOfPlants, SupplyModel supply, FieldModel field,
			String annotation, State state) {
		this.startDate = startDate;
		this.supply = supply;
		this.nOfPlants = nOfPlants;
		this.field = field;
		this.state = state;
		this.annotation = annotation;
		this.calculateEndDate();
	}

	@Override
	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
		this.calculateEndDate();
	}

	@Override
	public void setNOfPlants(int nOfPlants) {
		this.nOfPlants = nOfPlants;
	}

	@Override
	public void setSupply(SupplyModel supply) {
		this.supply = supply;
	}

	@Override
	public void setField(FieldModel field) {
		this.field = field;
	}

	@Override
	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}

	@Override
	public Calendar getStartDate() {
		return this.startDate;
	}

	@Override
	public Calendar getEndDate() {
		return this.endDate;
	}

	@Override
	public int getNOfPlants() {
		return this.nOfPlants;
	}

	@Override
	public SupplyModel getSupply() {
		return this.supply;
	}

	@Override
	public FieldModel getField() {
		return this.field;
	}

	@Override
	public String getAnnotation() {
		return this.annotation;
	}

	@Override
	public boolean isReady() {
		return this.getLeftDays() <= 0 ? true : false;
	}

	@Override
	public int getLeftDays() {
		Long result = Duration.between(new GregorianCalendar().toInstant(), endDate.toInstant()).toDays();
		int result2 = 0;
		for (TreatmentModel t : this.treatments) {
			if (result2 < t.getLeftDays()) {
				result2 = t.getLeftDays();
			}
		}
		return result.intValue() < result2 ? result2 : result.intValue();
	}

	@Override
	public State getState() {
		return this.state;
	}

	@Override
	public void setState(State state) {
		if(this.state == State.DONE){
			throw new IllegalArgumentException();
		}else{
			this.state = state;
		}
	}

	private void calculateEndDate() {
		this.endDate = new GregorianCalendar(this.startDate.get(Calendar.YEAR), this.startDate.get(Calendar.MONTH),
				this.startDate.get(Calendar.DAY_OF_MONTH));
		this.endDate.add(Calendar.DAY_OF_MONTH, this.supply.getPlant().getNDays());
	}

	@Override
	public void addTreatment(PhytosanitaryModel phytosanitary, Calendar date, String description) throws Exception {
		for (TreatmentModel t : this.treatments) {
			if (t.getDate() == date && t.getPhytosanitary().getName().equals(phytosanitary.getName())) {
				throw new IllegalArgumentException("this treatment has been already added");
			}
		}
		if (date.getTime().compareTo(this.getStartDate().getTime()) < 0 || date.getTime().compareTo(this.endDate.getTime())>0) {
			throw new Exception();
		}
		TreatmentModel treatment = new TreatmentImplementation(phytosanitary, date, description);
		this.treatments.add(treatment); 
	}

	@Override
	public List<TreatmentModel> getTreatments() {
		return this.treatments;
	}
}
