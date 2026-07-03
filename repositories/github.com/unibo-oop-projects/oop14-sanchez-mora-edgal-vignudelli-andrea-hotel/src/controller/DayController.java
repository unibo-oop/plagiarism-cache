package controller;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

import controller.interfaces.IDayController;

public class DayController implements IDayController,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -685835079989239479L;
	private Calendar cal = new GregorianCalendar();

	public DayController() {
		cal = Calendar.getInstance();
	}

	@Override
	public void day(int a) {
		cal.add(Calendar.DAY_OF_MONTH, a);
	}

	@Override
	public void month(int a) {
		cal.add(Calendar.MONTH, a);
	}

	@Override
	public String getDateString() {
		if(cal.get(Calendar.DAY_OF_MONTH)<10 ){
			if((cal.get(Calendar.MONTH) + 1)<10){
				return ("0" + cal.get(Calendar.DAY_OF_MONTH) + "/" + "0" + (cal.get(Calendar.MONTH) + 1) + "/"
						+ cal.get(Calendar.YEAR));
			}else{
			return ("0" + cal.get(Calendar.DAY_OF_MONTH) + "/" + (cal.get(Calendar.MONTH) + 1) + "/"
					+ cal.get(Calendar.YEAR));
		}
		}
		if((cal.get(Calendar.MONTH) + 1)<10){
			return ("" + cal.get(Calendar.DAY_OF_MONTH) + "/" + "0" + (cal.get(Calendar.MONTH) + 1) + "/"
				+ cal.get(Calendar.YEAR));
		}
		return ("" + cal.get(Calendar.DAY_OF_MONTH) + "/" + (cal.get(Calendar.MONTH) + 1) + "/"
				+ cal.get(Calendar.YEAR));
		}

	public Calendar getDate() {
		return Calendar.getInstance();
	}

}